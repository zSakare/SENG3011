package test.implementations;

import static org.junit.Assert.fail;
import main.implementations.OrderbookImpl;
import main.implementations.order.AlgorithmicTrade;
import main.implementations.order.Order;
import main.utils.Strategy;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import test.helper.ListGenerator;

public class OrderbookImplTest {
	
<<<<<<< HEAD
	// 6 different lists for the 3 different types of trade strategies.
	private List<Order> randomBidList;
	private List<Order> randomAskList;
	private List<Order> bidMomentumList;
	private List<Order> askMomentumList;
	private List<Order> bidMeanRevList;
	private List<Order> askMeanRevList;
	
	private List<Order> momentumTradeList;
	private List<Order> meanRevTradeList;
	
	private static String instrument = "DEF";
	private static Date date;
	private static double price = 150.00; 
	private static String volume;
	private static String ORDER_BID = "B";
	private static String ORDER_ASK = "A";
	
	private static final String RANDOM_BROKER_ID = "6969";
	private static final String buyerBrokerID = RANDOM_BROKER_ID;
	private static final String sellerBrokerID = RANDOM_BROKER_ID;
	private static final String brokerID = RANDOM_BROKER_ID;
	
	private static final int MAX_ORDERS = 1000;
	
	
=======
>>>>>>> e4592d125a6d0f11fc52860d433138fd812ffa70
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

	/**
	 * Tests the Random Strategy by checking if the volume for the
	 * generated Bids and Asks orders are the same. 
	 * It also checks whether list provided after running the 
	 * strategy is not empty.
	 */
	@Test
	public void testRunRandomStrategy() {
		
		System.out.println("Testing Random Strategy...");
		
		OrderbookImpl randomOrderBook = new OrderbookImpl(ListGenerator.generateRandomBidList(), 
				ListGenerator.generateRandomAskList(),
				null);
		Strategy strategy = Strategy.RANDOM;
		String randVolume = "999";
		
		List<AlgorithmicTrade> randomTradeList = randomOrderBook.runStrategy(strategy, randVolume);
		assertNotNull("Checking randomTradeList is not empty...", randomTradeList);
		
		for (AlgorithmicTrade algorithmicTrade : randomTradeList) {
			if (algorithmicTrade.getBidOrder().getBuyerBrokerId() == "6969") {
				// Checking volumes are the same (this has to be true)
				assert (algorithmicTrade.getBidOrder().getVolume() == 999);
			} else { 
				assert (algorithmicTrade.getAskOrder().getVolume() == 999);
			}
		}
		System.out.println("Random Strategy Test Passed.");
	}
	
	/**
	 * Tests the Momentum Strategy for a Positive trend.
	 * It also checks whether list provided after running the 
	 * strategy is not empty.
	 * We also check to make sure that a Bid has been created to match
	 * the positive trend.
	 */
	@Test
	public void testPosMomentumStrategy() {
		
		System.out.println("Testing Positive Momentum Strategy...");
		
		OrderbookImpl momentumOrderbook = new OrderbookImpl (bidMomentumList, 
				askMomentumList, momentumTradeList);
		Strategy strategy = Strategy.MOMENTUM;
		String randVolume = "999";
		
		List<AlgorithmicTrade> posMomentumTradeList = momentumOrderbook.runStrategy(strategy, randVolume);
		assertNotNull("Checking MomentumTradeList is not empty...", posMomentumTradeList);
		
		for (AlgorithmicTrade algorithmicTrade : posMomentumTradeList) {
			if (algorithmicTrade.getBidOrder().getBuyerBrokerId() == "6969") { 
				assert (algorithmicTrade.getBidOrder().getVolume() == 999);
			}
		}
		System.out.println("Positive Momentum Strategy Test Passed.");
	}
	
	/**
	 * Tests the Momentum Strategy for a Negative trend.
	 * It also checks whether list provided after running the 
	 * strategy is not empty.
	 * We also check to make sure that an Ask has been created to match
	 * the Negative trend.
	 */
	@Test
	public void testNegMomentumStrategy() {
		
		System.out.println("Testing Negative Momentum Strategy...");
		
		OrderbookImpl momentumOrderbook = new OrderbookImpl (bidMomentumList, 
				askMomentumList, momentumTradeList);
		Strategy strategy = Strategy.MOMENTUM;
		String randVolume = "999";
		
		List<AlgorithmicTrade> negMomentumTradeList = momentumOrderbook.runStrategy(strategy, randVolume);
		assertNotNull("Checking MomentumTradeList is not empty...", negMomentumTradeList);
		
		for (AlgorithmicTrade algorithmicTrade : negMomentumTradeList) {
			if (algorithmicTrade.getAskOrder().getSellerBrokerId() == "6969") { 
				assert (algorithmicTrade.getAskOrder().getVolume() == 999);
			}
		}
		System.out.println("Negative Momentum Strategy Test Passed.");
	}
	
	/**
	 * Tests the Mean Revision Strategy for a Positive trend.
	 * It also checks whether list provided after running the 
	 * strategy is not empty.
	 * We also check to make sure that an Ask has been created to match
	 * the positive trend.
	 */
	@Test
	public void testPosMeanRevStrategy() {
		
		System.out.println("Testing Positive Mean Revision Strategy...");
		
		OrderbookImpl meanRevOrderbook = new OrderbookImpl (bidMeanRevList, 
				askMeanRevList, meanRevTradeList);
		Strategy strategy = Strategy.MEAN_REVISION;
		String randVolume = "999";
		
		List<AlgorithmicTrade> posMeanRevTradeList = meanRevOrderbook.runStrategy(strategy, randVolume);
		assertNotNull("Checking MeanRevTradeList is not empty...", posMeanRevTradeList);
		
		for (AlgorithmicTrade algorithmicTrade : posMeanRevTradeList) {
			if (algorithmicTrade.getAskOrder().getSellerBrokerId() == "6969") { 
				assert (algorithmicTrade.getAskOrder().getVolume() == 999);
			}
		}
		System.out.println("Positive Mean Revision Strategy Test Passed.");
	}
	
	/**
	 * Tests the Mean Revision Strategy for a Negative trend.
	 * It also checks whether list provided after running the 
	 * strategy is not empty.
	 * We also check to make sure that a Bid has been created to match
	 * the negative trend.
	 */
	@Test
	public void testNegMeanRevStrategy() {
		
		System.out.println("Testing Negative Mean Revision Strategy...");
		
		OrderbookImpl meanRevOrderbook = new OrderbookImpl (bidMeanRevList, 
				askMeanRevList, meanRevTradeList);
		Strategy strategy = Strategy.MEAN_REVISION;
		String randVolume = "999";
		
		List<AlgorithmicTrade> negMeanRevTradeList = meanRevOrderbook.runStrategy(strategy, randVolume);
		assertNotNull("Checking MeanRevTradeList is not empty...", negMeanRevTradeList);
		
		for (AlgorithmicTrade algorithmicTrade : negMeanRevTradeList) {
			if (algorithmicTrade.getAskOrder().getBuyerBrokerId() == "6969") { 
				assert (algorithmicTrade.getBidOrder().getVolume() == 999);
			}
		}
		System.out.println("Negative Mean Revision Strategy Test Passed.");
	}

	@Test
	public void testTradeMatcher() {
		
		
		
	}

}
