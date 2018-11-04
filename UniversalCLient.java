import java.util.*;

public class UniversalCLient {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String key = scan.nextLine().toLowerCase();
		Game game;
		switch(key) {
		
		case "anagrams":
			game = new Anagram(6);
			break;
			
		case "mastermind":
			game = new Mastermind(6);
			break;
		
		case "hangman":
			game = new HangMan(6);
			break;
			
		case "sudoku":
			game = new GameBoard();
		
		}
	}

}
