package main.implementations;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import main.implementations.order.AlgorithmicTrade;
import main.implementations.order.Order;
import main.interfaces.OrderBuilder;
import main.interfaces.Orderbook;
import main.utils.Strategy;
import main.utils.TimeUtil;

/**
 * A list implementation of an orderbook. Contains all orders separated by ask, bid and trades. 
 */
public class OrderbookImpl implements Orderbook {

	private static final int FIRST_ELEMENT = 0;
	private static final String RANDOM_BROKER_ID = "6969";
	private static final String ORDER_BID = "B";
	private static final String ORDER_ASK = "A";
	private static final int LOOK_BACK_N = 1000;
	private static final double THRESHOLD = 0.00000001; 
	
	private List<Order> bidList;
	private List<Order> askList;
	private List<Order> tradeList;

	public OrderbookImpl(List<Order> bidList, List<Order> askList, List<Order> tradeList) {
		this.bidList = bidList;
		this.askList = askList;
		this.tradeList = tradeList;
	}


	@Override
	public void setBidList(List<Order> bidList) {
		
		this.bidList = bidList;
	}

	@Override
	public void setAskList(List<Order> askList) {
		
		this.askList = askList;
	}

	@Override
	public void setTradeList(List<Order> tradeList) {
		
		this.tradeList = tradeList;
	}
	
	@Override
	public List<Order> getBidList() {
		
		return bidList;
	}

	@Override
	public List<Order> getAskList() {

		return askList;
	}

	@Override
	public List<Order> getTradeList() {
		return tradeList;	
	}

	public List<AlgorithmicTrade> runStrategy(Strategy strategy, String volume) {
		
		List<AlgorithmicTrade> tradeList = new ArrayList<AlgorithmicTrade>();
		if (Strategy.RANDOM == strategy) {
			tradeList.add(newRandomBid(volume));
			tradeList.add(newRandomAsk(volume));
		} else if (Strategy.MOMENTUM == strategy) {
			tradeList.addAll(newMomentumOrders(volume));
		} else if (Strategy.MEAN_REVISION == strategy) {
			tradeList.addAll(newMeanRevisionOrders(volume));
		}
		
		return tradeList;
	}

	public AlgorithmicTrade tradeMatcher (Order orderToCompare) { 
		
		Order matchedOrder = null;
		AlgorithmicTrade matchedTrade = null;
		
		if (orderToCompare.isBid()) { 
			for (Order ask : askList) { 
				if (orderToCompare.getPrice() == ask.getPrice()) {
					matchedOrder = ask; 
					matchedTrade = new AlgorithmicTrade(orderToCompare, matchedOrder);
				}
			}
		} else { 
			for (Order bid : bidList) { 
				if (orderToCompare.getPrice () == bid.getPrice()) { 
					matchedOrder = bid;
					matchedTrade = new AlgorithmicTrade(matchedOrder, orderToCompare);
				}
			}
		}
		
		return matchedTrade;
	}
	
	// ---------- PRIVATE HELPER METHODS -----------
	private AlgorithmicTrade newRandomBid(String volume) { 
 
		// Set all required fields
		String instrument = bidList.get(FIRST_ELEMENT).getInstrument();
		Date date = bidList.get(FIRST_ELEMENT).getDateTime();
		Calendar dateSetter = Calendar.getInstance();
		dateSetter.setTime(date);
		dateSetter.set(Calendar.HOUR_OF_DAY, TimeUtil.generateHour());
		dateSetter.set(Calendar.MINUTE, TimeUtil.generateMinute());
		dateSetter.set(Calendar.MILLISECOND, TimeUtil.generateMillis());
		dateSetter.set(Calendar.SECOND, TimeUtil.generateSeconds());
		date = dateSetter.getTime();
		String buyerBrokerId = RANDOM_BROKER_ID;
		String bidOrAsk = ORDER_BID;
		
		double lowest = findLowestPrice(date);
		
		// Build the order.
		OrderBuilder orderBuilder = new OrderBuilderImpl(instrument,
										date,
										lowest,
										volume,
										bidOrAsk,
										buyerBrokerId);
		Order newOrder = orderBuilder.build();
		bidList.add(newOrder);	
		
		// Output the order.
		printOrder(newOrder);
		return tradeMatcher(newOrder);
	}

	private void printOrder(Order newOrder) {
		String orderType = "";
		if (newOrder.isBid()) {
			orderType = "Bid";
		} else {
			orderType = "Ask";
		}
		
		System.out.println(orderType + " order generated. Price: " + newOrder.getPrice() + " Quantity: " + newOrder.getVolume());
	}


	private AlgorithmicTrade newRandomAsk(String volume) { 

		// Set all required fields
		String instrument = askList.get(FIRST_ELEMENT).getInstrument();
		Date date = askList.get(FIRST_ELEMENT).getDateTime();
		Calendar dateSetter = Calendar.getInstance();
		dateSetter.setTime(date);
		dateSetter.set(Calendar.HOUR_OF_DAY, TimeUtil.generateHour());
		dateSetter.set(Calendar.MINUTE, TimeUtil.generateMinute());
		dateSetter.set(Calendar.MILLISECOND, TimeUtil.generateMillis());
		dateSetter.set(Calendar.SECOND, TimeUtil.generateSeconds());
		date = dateSetter.getTime();
		String sellerBrokerId = RANDOM_BROKER_ID;
		String bidOrAsk = ORDER_ASK;
		
		double highest = findHighestPrice(date);
		
		OrderBuilder orderBuilder = new OrderBuilderImpl(instrument,
										date,
										highest,
										volume,
										bidOrAsk,
										sellerBrokerId);
		
		Order newOrder = orderBuilder.build();
		askList.add(newOrder);	
		
		printOrder(newOrder);
		return tradeMatcher(newOrder);
	}


	private double findHighestPrice(Date date) {
		List<Order> nullBids = new ArrayList<Order>();
		// Find the highest price just before the newly generated time.
		double highest = bidList.get(FIRST_ELEMENT).getPrice();
		for (Order bid : bidList) {
			// Check if our dates are after the bid list dates.
			try {
				if (date.after(bid.getDateTime())) { 
					if (highest < bid.getPrice()) { 
						highest = bid.getPrice();
					}
				}
			} catch (NullPointerException e) {
				// Bid order invalid, mark for cleanup
				nullBids.add(bid);
			}
		}
		
		// Clean up invalid bids
		bidList.removeAll(nullBids);
		return highest;
	}
	
	private double findLowestPrice(Date date) {
		List<Order> nullAsks = new ArrayList<Order>();
		// Find the lowest price just before the newly generated time.
		double lowest = askList.get(FIRST_ELEMENT).getPrice();
		for (Order ask : askList) {
			try {
				// Check if the our date is after the ask list dates.
				if (date.after(ask.getDateTime())) { 
					if (lowest > ask.getPrice()) { 
						lowest = ask.getPrice();
					}
				}
			} catch (NullPointerException e) {
				// Mark ask orders for clean up.
				nullAsks.add(ask);
			}
		}
		
		// Clean up null orders.
		askList.removeAll(nullAsks);
		return lowest;
	}
	
	private List<AlgorithmicTrade> newMomentumOrders(String volume) {
		
		List<AlgorithmicTrade> newTrades = new ArrayList<AlgorithmicTrade>();
		
		// Grab all the required order fields
		String instrument = askList.get(FIRST_ELEMENT).getInstrument();
		
		int maxIterations = tradeList.size()/LOOK_BACK_N;
		for (int j = 0; j < maxIterations; j++) {
			// Look back on N number of trades to work out the momentum of the market.
			double tradeReturn = 0.0;
			Order finalTrade = null;
			int shiftAcross = j*LOOK_BACK_N;
			for (int i = shiftAcross; i < LOOK_BACK_N+shiftAcross; i++) {
				Order previousTrade = tradeList.get(i); 
				Order tradeToCompare = tradeList.get(i+1);
				double calculatedReturn = ((tradeToCompare.getPrice() - previousTrade.getPrice())/previousTrade.getPrice());
				tradeReturn += calculatedReturn;
				finalTrade = tradeToCompare;
			}
			
			// Calculate the current moving average.
			double movingAverage = tradeReturn/LOOK_BACK_N;
			// Check if the moving average breaks the threshold.
			if (movingAverage >= THRESHOLD) {
				// Average broke positive threshold, indicates we should buy (stock getting better)
				OrderBuilder orderBuilder = new OrderBuilderImpl(instrument,
												finalTrade.getDateTime(),
												findLowestPrice(finalTrade.getDateTime()),
												volume,
												ORDER_BID,
												RANDOM_BROKER_ID);
				
				Order newOrder = orderBuilder.build();
				printOrder(newOrder);
				newTrades.add(tradeMatcher(newOrder));
			} else if (movingAverage <= -THRESHOLD) {
				// Average broke negative threshold, indicates we should sell (stock getting worse)
				OrderBuilder orderBuilder = new OrderBuilderImpl(instrument,
												finalTrade.getDateTime(),
												findHighestPrice(finalTrade.getDateTime()),
												volume,
												ORDER_ASK,
												RANDOM_BROKER_ID);
				
				Order newOrder = orderBuilder.build();
				printOrder(newOrder);
				newTrades.add(tradeMatcher(newOrder));
			}
		}
		
		// Match any new orders.
		return newTrades;
	}
	
	private List<AlgorithmicTrade> newMeanRevisionOrders(String volume) {
		
		List<AlgorithmicTrade> newTrades = new ArrayList<AlgorithmicTrade>();
		
		// Grab all the required order fields
		String instrument = askList.get(FIRST_ELEMENT).getInstrument();
		
		int maxIterations = tradeList.size()/LOOK_BACK_N;
		System.out.println(maxIterations);
		for (int j = 0; j < maxIterations; j++) {
			// Look back on N number of trades to work out the momentum of the market.
			double tradeReturn = 0.0;
			Order finalTrade = null;
			int shiftAcross = j*LOOK_BACK_N;
			for (int i = shiftAcross; i < LOOK_BACK_N+shiftAcross; i++) {
				Order previousTrade = tradeList.get(i); 
				Order tradeToCompare = tradeList.get(i+1);
				double calculatedReturn = ((tradeToCompare.getPrice() - previousTrade.getPrice())/previousTrade.getPrice());
				tradeReturn += calculatedReturn;
				finalTrade = tradeToCompare;
			}
			
			// Calculate the current moving average.
			double movingAverage = tradeReturn/LOOK_BACK_N;
			// Check if the moving average breaks the threshold.
			if (movingAverage >= THRESHOLD) {
				// Average broke positive threshold, indicates we should buy (stock getting better)
				OrderBuilder orderBuilder = new OrderBuilderImpl(instrument,
												finalTrade.getDateTime(),
												findHighestPrice(finalTrade.getDateTime()),
												volume,
												ORDER_ASK,
												RANDOM_BROKER_ID);
				
				Order newOrder = orderBuilder.build();
				printOrder(newOrder);
				newTrades.add(tradeMatcher(newOrder));
			} else if (movingAverage <= -THRESHOLD) {
				// Average broke negative threshold, indicates we should sell (stock getting worse)
				OrderBuilder orderBuilder = new OrderBuilderImpl(instrument,
												finalTrade.getDateTime(),
												findLowestPrice(finalTrade.getDateTime()),
												volume,
												ORDER_BID,
												RANDOM_BROKER_ID);
				
				Order newOrder = orderBuilder.build();
				printOrder(newOrder);
				newTrades.add(tradeMatcher(newOrder));
			}
		}
		
		// Match any new orders.
		return newTrades;
	}
	
}