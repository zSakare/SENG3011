package main.gui.controller;

import main.evaluator.TradeStrategyEvaluator;
import main.interfaces.Orderbook;
import main.parser.SircaCSVParser;
import main.utils.Strategy;

public class Controller {
	private Orderbook orderbook;
	private TradeStrategyEvaluator evaluator;
	private Strategy strategy;
	private String volume;
	private static final int PERCENTAGE_SCALE = 100;
	
	public void setOrderbook(String fileName) {
		orderbook = SircaCSVParser.input(fileName);
	}

	public void setStrategy(String strategy) {
		if (("Random").equals(strategy)) {
			this.strategy = Strategy.RANDOM;
		} else if (("Momentum").equals(strategy)) {
			this.strategy = Strategy.MOMENTUM;
		} else if (("Mean-Revision").equals(strategy)) {
			this.strategy = Strategy.MEAN_REVISION;
		}
	}

	public void runStrategy() {
		if(strategy != null && volume != null){
			evaluator = new TradeStrategyEvaluator(orderbook.runStrategy(strategy, volume));
		}
	}
	
	public void setVolume(String volume) {
		this.volume = volume;
	}

	public void evaluate() {
		double evaluation = evaluator.calculateProfitLoss();

		evaluation = evaluation*PERCENTAGE_SCALE;
		
		// print return to 2 decimal places.
		System.out.printf("Percentage return: %.2f%%\n", evaluation);
	}
}
