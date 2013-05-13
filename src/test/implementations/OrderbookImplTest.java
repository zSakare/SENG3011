package test.implementations;

import static org.junit.Assert.*;
import static org.junit.Assert.fail;

import java.util.Date;
import java.util.List;

import main.implementations.OrderBuilderImpl;
import main.implementations.OrderbookImpl;
import main.implementations.order.Order;
import main.interfaces.OrderBuilder;
import main.utils.Strategy;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class OrderbookImplTest {
	
	// 6 different lists for the 3 different types of trade strategies.
	private List<Order> randomBidList;
	private List<Order> randomAskList;
	private List<Order> bidMomentumList;
	private List<Order> askMomentumList;
	private List<Order> bidMeanRevList;
	private List<Order> askMeanRevList;
	
	private List<Order> randomTradeList;
	private List<Order> momentumTradeList;
	private List<Order> meanRevTradeList;
	
	private static String instrument = "DEF";
	private static Date date;
	private static double price; 
	private static String volume;
	private static String ORDER_BID = "B";
	private static String ORDER_ASK = "A";
	
	private static final String RANDOM_BROKER_ID = "6969";
	private static final String buyerBrokerID = RANDOM_BROKER_ID;
	private static final String sellerBrokerID = RANDOM_BROKER_ID;
	private static final String brokerID = RANDOM_BROKER_ID;
	
	private static final int MAX_ORDERS = 1000;
	
	
	// function to create orders for price volume and time
	public OrderbookImplTest () { 
		
		
	}
	
	public void randomBidListCreator () { 
		
		int numTimes = 0;
		Long randTime = date.getTime();
		
		while (numTimes < MAX_ORDERS) { 
			OrderBuilder orderBuilder = new OrderBuilderImpl(instrument,
					date,
					price,
					volume,
					ORDER_BID,
					buyerBrokerID);
			Order newOrder = orderBuilder.build();
			randomBidList.add(newOrder);
			price += 0.5;
			randTime += 5000;
			date.setTime(randTime);
			volume += 20;
			numTimes++;
		}
	}
	
	public void bidMomentumListCreator () { 
		
		int numTimes = 0;
		Long randTime = date.getTime();
		
		while (numTimes < MAX_ORDERS) { 
			OrderBuilder orderBuilder = new OrderBuilderImpl(instrument,
					date,
					price,
					volume,
					ORDER_ASK,
					sellerBrokerID);
			Order newOrder = orderBuilder.build();
			bidMomentumList.add(newOrder);
			price += 0.4;
			randTime += 4000;
			date.setTime(randTime);
			volume += 20;
			numTimes++;
		}
	}
	
	public void bidMeanRevListCreator () { 
		
		int numTimes = 0;
		Long randTime = date.getTime();
		
		while (numTimes < MAX_ORDERS) { 
			OrderBuilder orderBuilder = new OrderBuilderImpl(instrument,
					date,
					price,
					volume,
					ORDER_ASK,
					sellerBrokerID);
			Order newOrder = orderBuilder.build();
			bidMeanRevList.add(newOrder);
			price -= 0.4;
			randTime += 4000;
			date.setTime(randTime);
			volume += 20;
			numTimes++;
		}
	}
	
	public void randomAskListCreator () { 
		
		int numTimes = 0;
		Long randTime = date.getTime();
		
		while (numTimes < MAX_ORDERS) { 
			OrderBuilder orderBuilder = new OrderBuilderImpl(instrument,
					date,
					price,
					volume,
					ORDER_ASK,
					sellerBrokerID);
			Order newOrder = orderBuilder.build();
			randomAskList.add(newOrder);
			price -= 0.4;
			randTime += 4000;
			date.setTime(randTime);
			volume += 20;
			numTimes++;
		}
	}
	
	public void askMomentunListCreator () { 
		
		int numTimes = 0;
		Long randTime = date.getTime();
		
		while (numTimes < MAX_ORDERS) { 
			OrderBuilder orderBuilder = new OrderBuilderImpl(instrument,
					date,
					price,
					volume,
					ORDER_ASK,
					sellerBrokerID);
			Order newOrder = orderBuilder.build();
			askMomentumList.add(newOrder);
			price -= 0.4;
			randTime += 4000;
			date.setTime(randTime);
			volume += 20;
			numTimes++;
		}
	}
	
	public void askMeanRevListCreator () { 
		
		int numTimes = 0;
		Long randTime = date.getTime();
		
		while (numTimes < MAX_ORDERS) { 
			OrderBuilder orderBuilder = new OrderBuilderImpl(instrument,
					date,
					price,
					volume,
					ORDER_ASK,
					sellerBrokerID);
			Order newOrder = orderBuilder.build();
			askMeanRevList.add(newOrder);
			price += 0.4;
			randTime += 4000;
			date.setTime(randTime);
			volume += 20;
			numTimes++;
		}
	}
	
	public void posMomentumTradeListCreator () { 
		
		int numTimes = 0;
		Long randTime = date.getTime();
		
		while (numTimes < MAX_ORDERS) { 
			OrderBuilder orderBuilder = new OrderBuilderImpl(instrument,
					date,
					price,
					volume,
					brokerID);
			Order newOrder = orderBuilder.build();
			momentumTradeList.add(newOrder);
			price += 0.4;
			randTime += 4000;
			date.setTime(randTime);
			volume += 20;
			numTimes++;
		}
	}
	
	public void negMomentumTradeListCreator () { 
		
		momentumTradeList.clear();
		
		int numTimes = 0;
		Long randTime = date.getTime();
		
		while (numTimes < MAX_ORDERS) { 
			OrderBuilder orderBuilder = new OrderBuilderImpl(instrument,
					date,
					price,
					volume,
					brokerID);
			Order newOrder = orderBuilder.build();
			momentumTradeList.add(newOrder);
			price -= 0.4;
			randTime += 4000;
			date.setTime(randTime);
			volume += 20;
			numTimes++;
		}
	}
	
	public void posMeanRevTradeListCreator () { 
		
		int numTimes = 0;
		Long randTime = date.getTime();
		
		while (numTimes < MAX_ORDERS) { 
			OrderBuilder orderBuilder = new OrderBuilderImpl(instrument,
					date,
					price,
					volume,
					brokerID);
			Order newOrder = orderBuilder.build();
			meanRevTradeList.add(newOrder);
			price -= 0.4;
			randTime += 4000;
			date.setTime(randTime);
			volume += 20;
			numTimes++;
		}
	}
	
	public void negMeanRevTradeListCreator () { 
		
		momentumTradeList.clear();
		
		int numTimes = 0;
		Long randTime = date.getTime();
		
		while (numTimes < MAX_ORDERS) { 
			OrderBuilder orderBuilder = new OrderBuilderImpl(instrument,
					date,
					price,
					volume,
					brokerID);
			Order newOrder = orderBuilder.build();
			momentumTradeList.add(newOrder);
			price += 0.4;
			randTime += 4000;
			date.setTime(randTime);
			volume += 20;
			numTimes++;
		}
	}
	
	public void printList () { 
		
		for (Order order : randomBidList) {
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
		
		OrderbookImpl randomOrderBook = new OrderbookImpl(randomBidList, 
				randomAskList, 
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
