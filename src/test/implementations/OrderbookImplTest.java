package test.implementations;

import static org.junit.Assert.fail;
import main.implementations.OrderbookImpl;
import main.implementations.order.Order;
import main.utils.Strategy;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import test.helper.ListGenerator;

public class OrderbookImplTest {
	
	// function to create orders for price volume and time
	public OrderbookImplTest () { 
		
		
	}
	
	public void printList () { 
		
		for (Order order : ListGenerator.generateRandomBidList()) {
			System.out.println("price: " + order.getPrice());
		}
		
	}
	
	@Before
	public void setUp() throws Exception {
		
		System.out.println("Testing TradeStrategyEvaluator.");
	}

	@After
	public void tearDown() throws Exception {
		
		System.out.println("Testing Complete.");
	}

	@Test
	public void testRunRandomStrategy() {
		// test if someting in there
		// check if volume is same
		// test 1 new buy and ask bid
		
		System.out.println("Testing Random Strategy");
		
		OrderbookImpl randomOrderBook = new OrderbookImpl(ListGenerator.generateRandomBidList(), 
				ListGenerator.generateRandomAskList(),
				null);
		Strategy strategy = Strategy.RANDOM;
		
		randomOrderBook.runStrategy(strategy, "999");
		
		
		
		
		
		fail("Not yet implemented");
	}
	
	@Test
	public void testRunMomentumStrategy() {
		
		// if volume to trade is the same 
		// test if it's positive we buy
		// negative we sell
		
		fail("Not yet implemented");
	}
	
	@Test
	public void testRunMeanRevisionStrategy() {
		
		// same as moementum except 
		// test if it's negative we buy
		// if it's positive we sell
		
		fail("Not yet implemented");
	}

	@Test
	public void testTradeMatcher() {
		fail("Not yet implemented");
	}

}
