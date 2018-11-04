import objectdraw.*;
import java.util.*;

public class Board {

    protected Square[][] grid;

    //solutions grid that will not be changed as grid will, so it is easy to quickly check for answers
    private Square[][] solutions;

    private Square[] def;
    private Text[] defVals;

    private Text[][] sqVals;

    private FilledRect[][] borders;

    private double x;
    private double y;

    private double size;


    private ArrayList<Integer> vals = new ArrayList<Integer>();
    private int count;



    public Board(Square[][] g, double xP, double yP, double size, DrawingCanvas canvas) {

        x = xP;
        y = yP;

        grid = g;
        int div = grid.length;
        double inc = size / div;

        int value = 0;
        this.size = size;
        for (int j = 0; j < grid.length; j++) {
            for (int k = 0; k < grid[0].length; k++) {
                grid[j][k] = new Square(value, j, k, x, y, inc, canvas);
                x += inc;
            }
            x = xP;
            y += inc;
        }
        x = xP;
        y = yP;

        borders = new FilledRect[2][4];
        for (int r = 0; r < 4; r++) {

            borders[0][r] = new FilledRect(x-5,y-5,size,10,canvas);
            y += 3*inc;
        }
        y=yP;

        for (int c = 0; c < 4; c++) {

            borders[1][c] = new FilledRect(x-5,y-5,10,size+10,canvas);
            x += 3*inc;
        }
        x = xP;

        assignValues();
        sqVals = new Text[9][9];
        y = yP;
        for (int r = 0; r < div; r++) {
            for (int c = 0; c < div; c++) {
                sqVals[r][c] = new Text(grid[r][c].getValue(), x + inc / 2, y + inc / 2, canvas);
                sqVals[r][c].setFontSize((int) inc / 2);
                sqVals[r][c].setBold();
                sqVals[r][c].moveTo(x + inc / 2 - sqVals[r][c].getWidth() / 2, y + inc / 2 - sqVals[r][c].getHeight() / 2);
                x += inc;
            }
            x = xP;
            y += inc;
        }

        //reset x and y
        x = xP;
        y = yP;
        def = new Square[div];
        defVals = new Text[div];
        for (int k = 0; k < grid[0].length; k++) {
            defVals[k] = new Text(k+1, x, y + size*5/4, canvas);
            defVals[k].setFontSize((int) inc / 2);
            defVals[k].setBold();
            defVals[k].moveTo(x +inc/2 - defVals[k].getWidth() / 2, y + size*5/4 +inc/2- defVals[k].getHeight() / 2);



            def[k] = new Square(k+1, k, 9, x, y+size*5/4, inc, canvas);
            x+=inc;
        }

    }



    public void assignValues() {

        for (int row = 0; row < grid.length; row++) {

            for (int col = 0; col < grid[0].length; col++) {

                vals.clear();

                for (int v = 1; v <= 9; v++) {


                    if (!(boxContains(v, row, col )|| colContains(v, col) || rowContains(v, row) )) vals.add(v);
                }
                if (vals.size() == 0 && count < 10){
                    count++;
                    clearRow(row);
                    col = -1;
                }
                else if (count > 9){
                    System.out.println("---------------------------------------------------------------------------------------------");
                    for (int r = 0; r <= row; r++){
                        clearRow(r);
                        col = -1;
                        row = 0;
                    }
                    count = 0;
                }
                /*if (vals.size() == 0){
                    if (col > 1) col = col-2;
                    else {
                        row--;
                        col = 7;
                    }
                }*/
                else {
                    int random = (int) (Math.random() * vals.size());
                    System.out.println("random = " + random + " row = " + row + " col = " + col + " size = " + vals.size());

                    grid[row][col].setValue(vals.get(random));
                }
            }

        }
    }

  /*  public void printValues(){
        for (int row = 0; row < grid.length; row++){
            for (int col = 0; col < grid[0].length; col++){
                System.out.print(grid[row][col].getValue() + "  ");
            }
            System.out.println();
        }
    }
    */

    public boolean colContains(int v, int col){
        for (int r = 0; r < 9; r++){
            if (grid[r][col].getValue() == v){
                return true;
            }
        }
        return false;
    }

    public boolean rowContains(int v, int row){
        for (int c = 0; c < 9; c++){
            if (grid[row][c].getValue() == v){
                return true;
            }
        }
        return false;
    }

    public void clearRow(int r){
        for (int col = 0; col < 9; col++){
            grid[r][col].setValue(0);
        }
    }

    public boolean boxContains(int v, int r, int c){
        int rSet = r/3;
        int cSet = c/3;
        for (int row = rSet*3; row < rSet*3 + 3; row++){
            for (int col = cSet*3; col < cSet*3 + 3; col++){
                if (grid[row][col].getValue() == v){
                    return true;
                }

            }
        }
        return false;
    }

    public String toString() {
        String result = "";
        for (Square[] sq: grid) {
            result+="|";
            for(Square a: sq) {
                result+= a.toString()+", ";
            }
            result+="|\n";
        }
        return result;
    }

    public int[][] getValues(){
        int[][] values = new int[9][9];
        for (int j = 0; j < 9; j++){
            for (int k = 0; k <9; k++){
                values[j][k] = grid[j][k].getValue();

            }
        }
        return values;
    }

    public Square getSquare(int row, int col){
        return grid[row][col];
    }

    public void hideText(int row, int col){
        sqVals[row][col].hide();
    }

    public void setText(int row, int col, int value){
        sqVals[row][col].setText(value);
        sqVals[row][col].show();
        System.out.println("success");
    }

    public int getSolution(int row, int col){
        return solutions[row][col].getValue();
    }

    public Square getSolvent(int value){
        return def[value];
    }

    /*public void onMouseClick(Location point, boolean first){
        for (int r = 0; r < 9; r++){
            for (int c = 0; c < 9; c++){
                if (grid[r][c].)
                grid[r][c].onMouseClick(point,first);
            }
        }
        for (int v = 0; v < 9; v++){
            def[v].onMouseClick(point,first);
        }
    }*/




}