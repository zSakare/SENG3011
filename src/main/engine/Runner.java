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
	
	public static void main(String[] argv) {
		
		Orderbook orderbook = SircaCSVParser.input();
		Scanner keyIn = new Scanner(System.in);
		String strategy = "";
		
		while (strategy.isEmpty()) {
			System.out.println("Please Choose a strategy");
			System.out.println("Current available strategies: ");
			System.out.println("'R' - Random");
			System.out.println("'M' - Momentum");
			strategy = keyIn.next();
			Strategy strat = null;
			if (!("R").equals(strategy)) {
				strat = Strategy.RANDOM;
			} else if (!("M").equals(strategy)) {
				strat = Strategy.MOMENTUM;
			}
		}
		
		System.out.println("Please Enter quantity to trade");
		String volume = keyIn.next();
		System.out.println("Simulating...");
		// TODO: Abstract out later please.
		List<AlgorithmicTrade> tradeList = new ArrayList<AlgorithmicTrade>();
		tradeList.add(orderbook.newAsk(volume));
		tradeList.add(orderbook.newBid(volume));
		
		System.out.println("Evaluating strategy");
		TradeStrategyEvaluator evaluator = new TradeStrategyEvaluator(tradeList);
		
		double evaluation = evaluator.calculateProfitLoss();
		evaluation = evaluation*100;
		
		// print return to 2 decimal places.
		System.out.printf("Percentage return: %.2f%%\n", evaluation);
	}
}
