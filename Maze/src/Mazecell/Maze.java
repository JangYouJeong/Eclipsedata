package Mazecell;

import java.util.Stack;

class MazeCell{
	public static final int length = 0;
	int row;
	int col;
	int direction;
	public MazeCell(int row,int col,int d) {
		this.row = row;
		this.col = col;
                this.direction = d;
        }
}

public class Maze {
	static int[][] initializeMaze() {
	int[][] maze= {
			 {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
			 {0, 0, 1, 0, 1, 0, 0, 0, 0, 1},
			 {1, 0, 0, 0, 1, 0, 1, 0, 1, 1},
			 {1, 1, 1, 0, 1, 0, 1, 0, 0, 1},
			 {1, 0, 1, 0, 1, 0, 1, 0, 1, 1},
			 {1, 0, 1, 0, 0, 0, 0, 0, 1, 1},
			 {1, 0, 1, 1, 1, 0, 1, 0, 1, 1},
			 {1, 0, 1, 0, 1, 0, 0, 0, 0, 1},
			 {1, 0, 0, 0, 0, 0, 1, 1, 0, 0},
			 {1, 1, 1, 1, 1, 1, 1, 1, 0, 1}

	   };
	   return maze;
	}
	static int checkMove(int[][]mark, int x, int y) {
            if ( y > 0) if (mark[y-1][x] == 0){
                return 0;
            }
            if (y < mark.length-1) if(mark[y+1][x] ==0 ){
                return 3;
            }
            if (x > 0) if(mark[y][x-1] ==0){
                return 1;
            }
            if (x < mark[0].length-1) if(mark[y][x+1] ==0 ){
                return 2;
            }
            return -1;
	}
        public static int[][] deepCopy(int[][] original, int n) {
            if (original == null) {
                return null;
            }

            int[][] result = new int[n][n];
            for (int i = 0; i < original.length; i++) {
                System.arraycopy(original[i], 0, result[i], 0, original[i].length);
            }
            return result;
        }

	static int[][] findMaze(int[][]path) {
	   int startX = 0;
	   int startY = 1;
           int endX = 8;
           int endY = 9;
	   Stack<MazeCell> stack = new Stack<MazeCell>();
	   int mark[][] = deepCopy(path,10);
           int ret[][] = deepCopy(path,10);
           mark[startY][startX] = 2;
           ret[startY][startX] = 2;
	   int x = startX;
	   int y = startY;
           boolean f = true;
	   while( f) {
                    //movable?
                    int d = checkMove(mark, x, y);
                    //showMaze(mark);
                    if (d != -1)
                    {
                        stack.push(new MazeCell(x,y,d));
                        switch(d){
                            case 0:
                                y--;
                                break;
                            case 1:
                                x--;
                                break;
                            case 2:
                                x++;
                                break;
                            case 3:
                                y++;
                                break;
                        }
                        mark[y][x] = 2;
                        ret[y][x] = 2;
                        if(x == endX && y == endY)  f= false;
                    }
                    else {
                        MazeCell mc = stack.pop();
                        mark[y][x] = 1;
                        ret[y][x] = 0;
                        x = mc.row;
                        y = mc.col;
                    }
            }
           
            return ret;
	}
	static void showMaze(int[][] path) {
            for (int i = 0; i < path.length; i++) {
                for (int j = 0; j < path[i].length; j++) {
                        System.out.print(" " + path[i][j]);
                        System.out.print(" ");
                }
                System.out.println("");
            }
        }
	
	
	public static void main(String[] args) {
            int [][]path = initializeMaze();
            showMaze(path);
            System.out.println("------------------------------");
            path = findMaze(path);
            showMaze(path);
            System.out.println("------------------------------");
	}
}
