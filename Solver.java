import objectdraw.*;

import java.util.*;

public class Solver {

    private GameBoard board;
    private int[][] values;
    private boolean repeat,found;



    public Solver(GameBoard gBoard) {
        board = gBoard;
        values = board.getValues();


    }

    public GameBoard Solve() {
        found = true;
        int count = 0;
        while (found) {
            count++;
            found = false;
            //
            for (int r = 0; r < 9; r++) {
                for (int c = 0; c < 9; c++) {
                    soleSolve(r, c);
                }
            }
            //Runs uniqueSolve once for each possible value
            for (int v = 1; v <= 9; v++) {
                //     uniqueSolve(v);
            }
            System.out.println(count);
        }
        return board;

    }

    //soleSolve checks the possible values of a given square by eliminating all values that are already present in the square's row, column, and box
    public void soleSolve(int row, int col) {
        ArrayList<Integer> posValues = board.getSquare(row, col).getPossibleValues();

        //Runs method only if there are multiple possible values for the given square
        if (posValues.size()>1)
            for (int v = posValues.size()-1; v >= 0; v--) {
                boolean removed = false;
                for (int c = 0; c < 9 && !(removed); c++) {
                    //               System.out.println(v+" "+c);
                    if (posValues.get(v) == values[row][c] && values[row][c] != 0) {
                        posValues.remove(v);

                        removed = true;
                    }
                }
                if (!(removed))
                    for (int r = 0; r < 9 && !(removed); r++) {
//                System.out.println(v+" "+r);
                        if (posValues.get(v) == values[r][col] && values[r][col] != 0) {
                            posValues.remove(v);

                            removed = true;
                        }
                    }
                if (!(removed)) {
                    int rSet = row / 3;
                    int cSet = col / 3;
                    for (int r = rSet * 3; r < rSet * 3 + 3 && !(removed); r++) {
                        for (int c = cSet * 3; c < cSet * 3 + 3 && !(removed); c++) {
//                        System.out.println(v+" "+r+" "+c);
                            if (values[r][c] == posValues.get(v) && values[r][c] != 0) {
                                posValues.remove(v);

                                removed = true;
                            }

                        }
                    }
                }


            }
        if (posValues.size() == 1) {
            values[row][col] = posValues.get(0);
            board.getSquare(row, col).setValue(posValues.get(0));
            found = true;
            System.out.println("found value");
        } else if (posValues.size() > 1) board.getSquare(row, col).setPossibleValues(posValues);


    }

    //Solves based on eliminating possible locations for a certain value in each row, column, and box
    /*public void uniqueSolve(int value) {
        repeat = false;
        //all possible squares on the Sudoku grid
        ArrayList<Square> posSquares = new ArrayList<Square>();
        //all squares which currently carry the given value
        ArrayList<Square> valSquares = valSquares(value);
        //sets posSquares to all squares on the board without a value
        //for all squares with each value 1-9
        for (Square k : valSquares) {
            posSquares.clear();
            for (int r = 0; r < 9; r++) {
                for (int c = 0; c < 9; c++) {
                    if (board.getSquare(r, c).getValue() == 0)
                        posSquares.add(board.getSquare(r, c));
                }
            }
            for (int j = posSquares.size() - 1; j >= 0; j--) {
                Square s = posSquares.get(j);
                if (s.getRow() == k.getRow()) {
                    posSquares.remove(j);
                    j--;
                }
                else if (s.getCol() == k.getCol()) {
                    posSquares.remove(j);
                    j--;
                }
                else if (s.getBox() == k.getCol()) {
                    posSquares.remove(j);
                    j--;
                }
            }
            for (int j = posSquares.size() - 1; j >= 0; j--) {
                //Assumes none of the values in row, column, or box also have the given value as a possible value
                boolean rowSame = false;
                boolean colSame = false;
                boolean boxSame = false;
                for (int i = posSquares.size()-1; i >= 0; i--){
                    //Avoids comparing the square to itself
                    if (!(posSquares.get(i).compareTo(posSquares.get(j)))){
                        //Compares square j to all the other possible squares that could have the given value
                        //Checks for possible squares in the same column
                        if (posSquares.get(j).getCol() == posSquares.get(i).getCol())
                            colSame = true;
                        //Checks for possible squares in the same row
                        if (posSquares.get(j).getRow() == posSquares.get(i).getRow())
                            rowSame = true;
                        //Checks for possible squares in the same box
                        if (posSquares.get(j).getBox() == posSquares.get(i).getBox())
                            boxSame = true;
                    }
                }
               /* for (int l = valSquares.size()-1; l >= 0; l--) {
                    for (int c = 0; c < 9; c++){
                    }
                    if (posSquares.get(j).getRow() == valSquares.get(l).getRow())
                        rowSame = true;
                    if (posSquares.get(j).getCol() == valSquares.get(l).getCol())
                        colSame = true;
                    if (posSquares.get(j).getBox() == valSquares.get(l).getBox())
                        boxSame = true;
                }
                //If there is either no other square with the same possible value in the same row, box, or column, then a solution has been found
                if (!(rowSame) || !(colSame) || !(boxSame)) {
                    //Prints if a solution to possible square j is found
//                    System.out.println("found value");
                    //Boolean signals to repeat the method
                    // repeat = true;
//                   found = true;
                    //Changes the value on the board
                    board.getSquare(posSquares.get(j).getRow(), posSquares.get(j).getCol()).setValue(k.getValue());
                    //Adds to value grid used by SoleSolve
                    values[posSquares.get(j).getRow()][posSquares.get(j).getCol()] = k.getValue();
                }
            }
        }
        //Recursion
       if (repeat) {
            uniqueSolve(value);
        }
    }
    */



    public void gridInteraction() {
        for (int b = 1; b <= 9; b++) {

        }
    }


    //Creates an array list of all squares in the Sudoku board with the given value
    public ArrayList<Square> valSquares(int value) {
        ArrayList<Square> squares = new ArrayList<Square>();
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if (board.getSquare(r, c).getValue() == value) squares.add(board.getSquare(r, c));
            }
        }
        return squares;
    }

}