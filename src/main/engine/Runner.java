package main.engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import main.evaluator.TradeStrategyEvaluator;
import main.implementations.order.Trade;
import main.interfaces.Orderbook;
import main.parser.SircaCSVParser;


public class Runner {
	public static void main(String[] argv) {
		Orderbook orderbook = SircaCSVParser.input();
		Scanner keyIn = new Scanner(System.in);
		String strategy = "";
		while (strategy.isEmpty()) {
			System.out.println("Please Choose a strategy ('R' for random)");
			strategy = keyIn.next();
			if (!("R").equals(strategy)) {
				strategy = "";
			}
		}
		System.out.println("Please Enter quantity to trade");
		String volume = keyIn.next();
		System.out.println("Simulating...");
		// TODO: Abstract out later please.
		List<Trade> tradeList = new ArrayList<Trade>();
		tradeList.add(orderbook.newAsk(volume));
		tradeList.add(orderbook.newBid(volume));
		
		System.out.println("Evaluating strategy");
		TradeStrategyEvaluator evaluator = new TradeStrategyEvaluator(tradeList);
		
		double evaluation = evaluator.calculateProfitLoss();
		evaluation = evaluation*100;
		System.out.println("You made a profit of: " + evaluation + "%");
	}
}
