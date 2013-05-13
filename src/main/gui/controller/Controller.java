package main.gui.controller;

import main.evaluator.TradeStrategyEvaluator;
import main.interfaces.Orderbook;
import main.parser.SircaCSVParser;

public class Controller {
	private Orderbook orderbook;
	private TradeStrategyEvaluator evaluator;
	
	public void setOrderbook(String fileName) {
		orderbook = SircaCSVParser.input(fileName);
	}
}
