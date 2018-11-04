
public class Element {
    private String num;
    private boolean correct = false;

    public Element(String n) {
        num = n;
    }
    public void match() {
        correct = true;
    }
    public boolean isCorrect() {
        return correct;
    }
    public String toString() {
        return num;
    }
    public void setVal(String str) {
        num = (str+" "+correct);
    }
    public String getVal() {
        return this.num;
    }
}