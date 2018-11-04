
/*
 	beginner - 51-60
	easy- 41-50
	medium - 31-40
	hard - 21-30
*/
import java.util.*;

public class GameBoard extends Board implements Game{

    private int numsLeft;
    private Scanner scan = new Scanner(System.in);
    private boolean solved = true;
    
    public GameBoard(Square[][] g, double x, double y, double size) {
        super(g, x, y, size);
        numsLeft = 0;
    }
    public GameBoard() {
    	super();
    }
    
    public void play() {
    	while(!solved&&!isSolved()) {
    		System.out.println("type the x coordinate");
    		int xcoord = scan.nextInt();
    		System.out.println("type the y coordinate");
    		int ycoord = scan.nextInt();    		
    		System.out.println("type the value you wish to replace");
    		int replaceVal = scan.nextInt();
    		if((xcoord < 9&& xcoord>=0)&&(ycoord < 9 && ycoord >=0)&&(replaceVal<9&&replaceVal>0)) {
    			super.grid[xcoord][ycoord].setValue(replaceVal);
    			System.out.println(this);
    		}
    		else { 
    			System.out.println("illegal arguments");
    			
    		}
    	}
    }

    public void makePuzzle(String s) {
    	solved = false;
        int subtractNums = 0;
        switch (s) {
            case "beginner":
                subtractNums = (int) (51 + Math.random() * 9);
                break;
            case "easy":
                subtractNums = (int) (41 + Math.random() * 9);
                break;
            case "medium":
                subtractNums = (int) (31 + Math.random() * 9);
                break;
            case "hard":
                subtractNums = (int) (25 + Math.random() * 9);
                break;
            default:
                return;
        }
        while (subtractNums > 0) {
            int randX = (int) (Math.random() * 9);
            int randY = (int) (Math.random() * 9);
            super.grid[randX][randY].setValue(0);
            ArrayList<Integer> posVals = new ArrayList<Integer>();
            for (int v = 1; v < 10; v++){
                posVals.add(v);
            }
            super.grid[randX][randY].setPossibleValues(posVals);

            subtractNums--;
            numsLeft++;
        }
    }

    public int getNumsLeft() {
        return numsLeft;
    }
    public boolean isSolved() {
    	for(int x = 0; x < super.grid.length;x++) {
    		for (int y = 0; y < super.grid[0].length; y++) {
    			Square temp = grid[x][y];
    			if(this.boxContains(temp.getValue(), x, y)||this.colContains(temp.getValue(), y)||this.rowContains(temp.getValue(), x)) return false; 
    		}
    	}
    	return true;
    }

}