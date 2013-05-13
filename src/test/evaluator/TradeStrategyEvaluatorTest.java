package test.evaluator;

import java.util.List;

import main.evaluator.TradeStrategyEvaluator;
import main.implementations.order.AlgorithmicTrade;
import main.implementations.order.Order;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TradeStrategyEvaluatorTest {

	@Before
	public void setUp() throws Exception {
		System.out.println("Testing TradeStrategyEvalutor");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("Testing complete.");
	}

	@Test
	public void testCalculateProfitLoss() {
		System.out.println("Testing if profit calculation is correct.");
		TradeStrategyEvaluator evaluator = new TradeStrategyEvaluator(testTradeList());
	}

	private List<AlgorithmicTrade> testTradeList(List<Order> bidList, List<Order> askList) {
		List<AlgorithmicTrade> tradeList = null;
		
		return tradeList;
	}

}
