import java.util.*;

public class MastermindSolver {

    private Mastermind master;
    private int size;

    private ArrayList<String> posNums = new ArrayList<String>(Arrays.asList("1","2","3","4","5","6","7","8","9"));
    private boolean[][] positions;

    public MastermindSolver(Mastermind m){
        master = m;
        size = master.getSize();
        positions = new boolean[posNums.size()][size];
        for (int k = 0; k < posNums.size();k++){
            for (int s = 0; s <size; s++){
                positions[k][s] = true;

            }
        }
    }

   /* public void Solve(){
        String str = "";
        for (Element e : solution()){
            str += e.toString();
        }
        System.out.println(str);
    }*/

    public void solution(){
        Element[] temp;
        while (!master.isSolved()) {
            Element[] attempt = new Element[size];
            int length = size;
            for (int k = 0; k < length; k++) {
                for (int p = 0; p < posNums.size(); p++) {
                    if (positions[p][k]) {
                        attempt[k] = new Element(posNums.get(k));
                        attempt[k].setVal(posNums.get(k));
                    }
                }
            }
            temp = attempt;
            master.eval(attempt);
            for (int s = 0; s < size; s++) {
                String str = attempt[s].getVal();
                if (str.equals(temp[s].toString()+" true")) {
                    for (int c = 0; c < size && c != s; c++) {
                        positions[Integer.parseInt(posNums.get(s))][c] = false;
                    }
                    for (int j = 0; j < posNums.size(); j++) {
                        positions[j][s] = false;
                    }
                } else if (str.equals(temp[s].toString() + " close but false"))
                    positions[Integer.parseInt(posNums.get(s))][s] = false;
                else {
                    posNums.remove(s);
                }

            }
            String str = "";
            for (int e = 0; e < temp.length; e++){
                str += temp[e].toString();
            }
            System.out.println(str);
        }


    }

}