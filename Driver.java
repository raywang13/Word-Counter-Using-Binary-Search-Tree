package project4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Driver {

	public static void main(String[] args) throws FileNotFoundException {
		String space = " ";
		String regex = "[.,;/]";
		BST a = new BST();
		boolean stop = false;
		ArrayList<String> stopWords = new ArrayList<String>();
		Scanner scan = new Scanner(new File("test2"));
		Scanner scanStop = new Scanner(new File("stop.txt"));
		
		//reads in the stop words and adds them to an ArrayList
		while(scanStop.hasNext()) {
			String stopWord = scanStop.next().trim();
			stopWords.add(stopWord);
		}
		
		//each time a new word is scanned in, it is checked with the ArrayList of stop words,
		//if the word is found in the stop words ArrayList, it will not be added to the BST
		while(scan.hasNext()) {
			stop = false;
			String nextWord = scan.next().toLowerCase().replaceAll(regex, space).trim();
			Word werd = new Word(nextWord, 1);
			Node werdNode = new Node(werd);
			for(int i = 0; i < stopWords.size(); i++) {
				if(stopWords.get(i).contains(nextWord)) {
					stop = true;
				}
			}
			if(stop == false) {
				a.add(werdNode, a.getRoot());
			}
		}
		
		
		Scanner userinput = new Scanner(System.in);
		int choice = 0;
		
		do {
			System.out.println("\nChoose from the following options...");
			System.out.println("1. Print the Binary Search Tree");
			System.out.println("2. Remove a word from Binary Search Tree");
			System.out.println("3. Find a word from Binary Search Tree");
			System.out.println("4. Exit the program.");
			choice = userinput.nextInt();
			
			switch(choice) {
			case 1:
				a.inOrder(a.getRoot());
				break;
			case 2:
				System.out.println("Please enter the word you are looking to remove.");
				String removeWord = userinput.next();
				a.remove(removeWord, a.getRoot());
				System.out.println("\nBST after removing node.");
				a.inOrder(a.getRoot());
				break;
			case 3:
				System.out.println("Please enter the word you are looking for...");
				String targetword = userinput.next();
				a.find(targetword, a.getRoot());
				break;
			case 4:
				System.out.println("Exiting program...");
				System.exit(0);
				break;
			default:
				System.out.println("Please enter a valid choice.");
				break;
			}
		} while(choice != 4);
		
	} //end main
	
}
