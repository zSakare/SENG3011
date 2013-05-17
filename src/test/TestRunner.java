package test;

import test.evaluator.TradeStrategyEvaluatorTest;
import test.implementations.OrderbookImplTest;
import test.implementations.order.AlgorithmicTradeTest;

public class TestRunner {
	
	public static void main(String[] args) {
		TradeStrategyEvaluatorTest strategyEvaluatorTest = new TradeStrategyEvaluatorTest();
		AlgorithmicTradeTest algorithmicTradeTest = new AlgorithmicTradeTest();
		OrderbookImplTest orderbookImplTest = new OrderbookImplTest();
		
		// Trade strategy evaluator.
		strategyEvaluatorTest.testCalculateBreakEven();
		strategyEvaluatorTest.testCalculateNegative();
		strategyEvaluatorTest.testCalculatePositive();
		strategyEvaluatorTest.testCalculateProfitLoss();
		
		// Algorithmic Trade
		algorithmicTradeTest.testEvaluateTradeLowerAskVolume();
		algorithmicTradeTest.testEvaluateTradeLowerBidVolume();
		algorithmicTradeTest.testEvaluateTradeSameVolume();
		
		// Orderbook impl trade.
		orderbookImplTest.testNegMeanRevStrategy();
		orderbookImplTest.testNegMomentumStrategy();
		orderbookImplTest.testPosMeanRevStrategy();
		orderbookImplTest.testPosMomentumStrategy();
		orderbookImplTest.testRunRandomStrategy();
		orderbookImplTest.testTradeMatcher();
	}
}
