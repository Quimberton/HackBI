import objectdraw.*;

import java.awt.*;
import java.util.*;

public class Square {

    public Object onMouseClick;
    private int value;
    private int row;
    private int col;


    private FramedRect box;
    private DrawingCanvas canvas;
    private double size;
    private Location point;

    private boolean firstSelected;
    private boolean selected;
    private boolean secondSelected;

    private ArrayList<Integer> posVals = new ArrayList<Integer>();


    public Square(int v, int c, int r, double x, double y, double s, DrawingCanvas can) {
        value = v;
        row = r;
        col = c;

        size = s;
        canvas = can;
        point = new Location(x, y);


        box = new FramedRect(point, size, size, canvas);
    }

    public Square(int value, Location point, double s) {

        size = s;
        //canvas = c;
        this.point = new Location(point.getX(), point.getY());
        this.value = value;
        // box = new FramedRect(point, size, size, canvas);
    }

    public void setValue(int v) {
        value = v;
        posVals.clear();
    }

    public int getValue() {
        return value;
    }


    public ArrayList<Integer> getPossibleValues() {
        return posVals;
    }

    public void setPossibleValues(ArrayList<Integer> posValues) {
        this.posVals = posValues;
    }


    public String toString() {
        return Integer.toString(this.value);
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getBox() {
        int rSet = row / 3;
        int cSet = col / 3;
        if (rSet == 0) {
            if (cSet == 0) return 1;
            if (cSet == 1) return 2;
            return 3;
        }
        if (rSet == 1) {
            if (cSet == 0) return 4;
            if (cSet == 1) return 5;
            return 6;
        } else {
            if (cSet == 0) return 7;
            if (cSet == 1) return 8;
            return 9;
        }
    }

    public void onMouseClick(Location point, boolean first) {
        if (box.contains(point) && row == 9 && first){
            box.setColor(Color.BLUE);
            secondSelected = true;}
         else {
            secondSelected = false;
            box.setColor(Color.BLACK);
        }


        if (box.contains(point) && value == 0) {
            firstSelected = true;
            box.setColor(Color.BLUE);
        } else {
            firstSelected = false;
            box.setColor(Color.BLACK);
        }


    }

    public void setColor(Color color){
        box.setColor(color);
    }

    public boolean contains(Location point){
        return box.contains(point);
    }

    public boolean selected() {
        return selected;
    }

    public boolean firstSelected() {
        return firstSelected;
    }

    public boolean secondSelected() {
        return secondSelected;

    }


    public boolean compareTo(Square sq) {
        return (this.row == sq.getRow() && this.col == sq.getCol());
    }
}