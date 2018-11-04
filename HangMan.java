import java.util.Scanner;
import java.util.ArrayList;

public class HangMan implements Game{
	private int numLives = 8;
	private String word;
	private boolean solved = false;
	private char[] wChars;
	private ArrayList<Character> guesses = new ArrayList<Character>();
	private WordStreamIterator iter = new WordStreamIterator("words10000.txt");
	private Scanner scan = new Scanner(System.in);
	
	public HangMan(int numLetters) {
		int num = (int)(Math.random()*10000);
		String a = (String)iter.get(num);
			while(a.length()!= numLetters) {
				num = (int)(Math.random()*20);
				a = (String)iter.get(num);
			}
		word = a;
		wChars = new char[word.length()];
		for(int temp = 0; temp < wChars.length; temp++) wChars[temp] = '_';
	}
	public void play() {
		while(numLives>0&&!solved) {
			 String guessStr = scan.nextLine().toLowerCase();
			 if(guessStr.length()==1&&!guesses.contains(guessStr.charAt(0))) {
				 guesses.add(guessStr.charAt(0));
				 if(word.indexOf(guesses.get(guesses.size()-1))!=-1) {
					fillArr(guesses.get(guesses.size()-1));
					eval();
				 }
				 else {
					 numLives--;
				 }
			 }
			 else System.out.println(guessStr+" try another value.");
			 System.out.println(this);
		}
		if(numLives==0)System.out.println("You lost, better luck next time!\n The word was "+ word);
		else System.out.println("You're a winner!");
	}
	public String toString() {
		String result ="";
		for(char a: wChars)result+=a+" ";
		result = result.substring(0, result.length()-1);
		result+="";
		return result;
	}
	
	public void eval() {
		for(int index = 0; index < wChars.length; index++) if(wChars[index]=='_')return;
		solved = true;		
	}
	public void fillArr(char guess) {
		for(int index = 0; index < word.length(); index++) {
			if(guess==word.charAt(index))wChars[index]= guess;
		}
	}
}