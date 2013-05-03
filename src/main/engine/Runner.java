package main.engine;

import java.util.ArrayList;

import java.util.List;
import java.util.Scanner;

import main.evaluator.TradeStrategyEvaluator;
import main.implementations.order.AlgorithmicTrade;
import main.interfaces.Orderbook;
import main.parser.SircaCSVParser;
import main.utils.Strategy;

/**
 * Runner class (Mostly user interface related).
 */
public class Runner {
	
	private static final int PERCENTAGE_SCALE = 100;
	private static final String PICK_STRATEGY = "run";
	private static final String QUIT = "quit";
	private static final String NEW_SIRCA_INPUT = "new";

	public static void main(String[] argv) {
		
		Scanner keyIn = new Scanner(System.in);
		String fileName = "";
		String strategy = "";
		String command = "";
		Strategy strat = null;
		Orderbook orderbook = null;
		
		while (true) {
			if (orderbook == null) {
				System.out.println("Please specify a sirca input file to work with. Enter the full filename with extension");
				System.out.println("E.g. sircaInput.csv");
				fileName = keyIn.next();
				orderbook = SircaCSVParser.input(fileName);
			} else if (command.isEmpty() && orderbook != null) {
				System.out.println("What would you like to do?");
				System.out.println("run - Runs the trade evaluator with a given strategy");
				System.out.println("new - Resets the current orderbook and asks for a new file");
				System.out.println("quit - Quits the application");
				command = keyIn.next();
				if (command.equals(PICK_STRATEGY)) {
					while (strategy.isEmpty()) {
						System.out.println("Please Choose a strategy");
						System.out.println("Current available strategies: ");
						System.out.println("'R' - Random");
						System.out.println("'M' - Momentum");
						System.out.println("'MR' - Mean Revision");
						strategy = keyIn.next();
						if (("R").equals(strategy)) {
							strat = Strategy.RANDOM;
						} else if (("M").equals(strategy)) {
							strat = Strategy.MOMENTUM;
						} else if (("MR").equals(strategy)) {
							strat = Strategy.MEAN_REVISION;
						} else {
							System.err.println("Please input a correct strategy");
							strategy = "";
						}
					}
					System.out.println("Please Enter quantity to trade");
					String volume = keyIn.next();
					System.out.println("Simulating...");
					List<AlgorithmicTrade> tradeList = new ArrayList<AlgorithmicTrade>();
					tradeList.addAll(orderbook.runStrategy(strat, volume));
					
					System.out.println("Evaluating strategy");
					TradeStrategyEvaluator evaluator = new TradeStrategyEvaluator(tradeList);
					
					double evaluation = evaluator.calculateProfitLoss();
					evaluation = evaluation*PERCENTAGE_SCALE;
					
					// print return to 2 decimal places.
					System.out.printf("Percentage return: %.2f%%\n", evaluation);
					
					strategy = "";
					command = "";
				} else if (command.equals(QUIT)) {
					System.out.println("Bye bye.");
					System.exit(0);
				} else if (command.equals(NEW_SIRCA_INPUT)) {
					System.out.println("Resetting.....");
					orderbook = null;
					command = "";
				} else {
					System.err.println("Please enter a valid command.");
					command = "";
				}
			}
		}
	}
}
