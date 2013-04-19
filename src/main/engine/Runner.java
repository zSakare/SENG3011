package main.engine;

import main.parser.SircaCSVParser;
import java.util.Scanner;


public class Runner {
	public static void main(String[] argv) {
		SircaCSVParser.input();
		Scanner keyIn = new Scanner(System.in);
		System.out.println("Please Choose a strategy ('R' for random)");
		String c = keyIn.next();
		System.out.println("Please Enter quantity to trade");
		String quantity = keyIn.next();
		System.out.println("Simulating...");
		//newAskOrder(quantity);
		//newBidOrder(quantity);
		System.out.println("Evaluating strategy");
		//double evaluation = evaluateStrategy();
		//System.out.println("You made a profit of: " + evaluation);
	}
}
