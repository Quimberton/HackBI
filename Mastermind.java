import java.util.*;

public class Mastermind implements Game{

    private int numGuesses = 0, s;
    private String num, guess;
    private boolean solved = false;
    private String[] digits;
    private Element[] gDigits;
    private Scanner scan = new Scanner(System.in);

    public Mastermind(int size) {
        digits = new String[size];
        gDigits = new Element[size];
        if(size > 9) size = 9;
        if(size < 3) size = 3;
        s = size;
        num = makeNum(size);

    }

    public void play() {
        while(!solved) {
            guess = scan.nextLine();
            numGuesses++;
            gDigits = toArr(guess);
            if(guess.length()==num.length()) {
                eval(gDigits);
                System.out.println(this);
            }
            else System.out.println("Your guess is not the right size, try again.");
        }
        System.out.println("good job, it took you "+numGuesses+" tries to solve.");
    }
    public void eval(Element[] numG) {
        for(int x = 0; x < numG.length; x++) {
            if(!numG[x].isCorrect()) {
                if(numG[x].toString().equals(digits[x])) {numG[x].match();numG[x].setVal(numG[x].getVal());}
                else if(Wcontains(numG[x].toString())) numG[x].setVal(numG[x].getVal()+" close but");
            }
            if(isSolved()) {
                solved = true;
            }
        }

    }
    public String makeNum(int size) {
        String result = "";
        ArrayList<String> rdmNums = new ArrayList<String>(Arrays.asList("1","2","3","4","5","6","7","8","9"));
        for(int x = 0; x < rdmNums.size(); x++) {
            int index = (int)((rdmNums.size()-1)*Math.random());
            result += rdmNums.get(index);
            rdmNums.remove(index);
        }
        for(int x = 0; x < gDigits.length; x++) {
            digits[x] = result.substring(x, x+1);
        }
        return result;
    }
    public String toString() {
        String result = "";
        eval(gDigits);
        for(String st: digits) {
            result += st+", ";
        }
        result+= "\n";
        for(Element e: gDigits) {
            result+= e.toString()+", ";
        }
        return result;
    }
    public boolean Gcontains(String a) {
        for(int x = 0; x < gDigits.length ; x++) {
            if(a.equals(gDigits[x].toString()))return true;
        }
        return false;
    }
    public boolean Wcontains(String a) {
        for(int x = 0; x < digits.length ; x++) {
            if(a.equals(digits[x]))return true;
        }
        return false;
    }
    public Element[] toArr(String str) {
        Element[] temp = new Element[str.length()];
        for(int x = 0; x < str.length(); x++) {
            temp[x] = new Element(str.substring(x, x+1));
        }
        return temp;
    }
    public boolean isSolved() {
        for(Element e: gDigits) {
            if(!e.isCorrect())return false;
        }
        return true;
    }

    public boolean isCompleted() {
        return solved;
    }

    public int getSize(){
        return s;
    }
}