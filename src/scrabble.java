import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class scrabble {

	ScrabbleNode[] charValues = new ScrabbleNode[26];
	ArrayList<ScrabbleNode> words = new ArrayList<ScrabbleNode>();
	ScrabbleNode[] sortedList;
	
	
	public void loadValues(String fileValues, String fileWords) throws FileNotFoundException {
		try {
			// letter value scanner
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(new File(fileValues));
			int value = 0;
			String cur = null;
			int index = 0;

			while (scanner.hasNextLine()) {

				if (scanner.hasNextInt()) {
					value = scanner.nextInt();
				}
				cur = scanner.next();

				if (cur != null && value != 0 && index < charValues.length) {
					if (cur.equals("-")) continue;
					ScrabbleNode node = new ScrabbleNode(cur, value);
					charValues[index++] = node;
				}
			}
			// word scanner
			scanner = new Scanner(new File(fileWords));
			while (scanner.hasNextLine()) {
				value = 0;
				cur = scanner.nextLine();
//				if (cur.length() > 7) continue;
//				System.out.println(cur);
				for (int i = 0; i < cur.length(); i++) {
					for (int j = 0; j < 26; j++) {
						if (cur.charAt(i) == Character.toLowerCase(charValues[j].getChar().charAt(0))) {
							value += charValues[j].getValue();
						}
					}
				}
				ScrabbleNode node = new ScrabbleNode(cur, value);
				words.add(node);
			}
			// sort word list
			MergeSort sort = new MergeSort();
			sortedList = sort.sort(words);
			words.removeAll(words);
			System.out.println(sortedList.length);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	
	public ScrabbleNode findBestWord(char[] letters) {
		char[] temp = new char[letters.length];
		// increment through words array
		for (int i = sortedList.length-1; i >= 0; i--) {
			// reset word
			for (int f = 0; f < letters.length; f++) temp[f] = letters[f];
			// check letters against highest scoring words
			int index = 0;
			for (int k = 0; k < sortedList[i].getChar().length(); k++) {
				//print line
//				System.out.println("thing: " + sortedList[i].getChar().length());
				for (int j = 0; j < letters.length; j++) {
					if (temp[j] == Character.toLowerCase(sortedList[i].getChar().charAt(index))) {
						index++;
						temp[j] = ' ';
						if (index == sortedList[i].getChar().length()) {
							System.out.println("highest scoring word: " + sortedList[i].getChar());
							System.out.println("score: " + sortedList[i].getValue());
							return sortedList[i];
						}
					}
				}
			}
		}
		System.out.println("no words found");
		return null;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		// assign random letters
		String alphabet = "abcdefghijklmnopqrstuvwxyz";
		char[] letters = {'v', 'a', 'n', 'c', 'j', 'o', 'l', 'e'};
//		Random random = new Random();
		
//		for (int i = 0; i < letters.length; i++) {
//			letters[i] = alphabet.charAt(random.nextInt(26));
//			System.out.println("letter = " + letters[i]);
//		}
		scrabble game = new scrabble();
		game.loadValues(args[0], args[1]);
		game.findBestWord(letters);
	}

}
