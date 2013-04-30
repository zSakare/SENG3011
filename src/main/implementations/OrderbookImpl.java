package main.implementations;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import main.implementations.order.Order;
import main.implementations.order.AlgorithmicTrade;
import main.interfaces.OrderBuilder;
import main.interfaces.Orderbook;
import main.utils.TimeUtil;

/**
 * A list implementation of an orderbook. Contains all orders separated by ask, bid and trades. 
 */
public class OrderbookImpl implements Orderbook {

	private static final int FIRST_ELEMENT = 0;
	private static final String RANDOM_BROKER_ID = "6969";
	private static final String ORDER_BID = "B";
	private static final String ORDER_ASK = "A";
	
	private List<Order> bidList;
	private List<Order> askList;

	public OrderbookImpl(List<Order> bidList, List<Order> askList) {
		this.bidList = bidList;
		this.askList = askList;
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
	public List<Order> getBidList() {
		
		return bidList;
	}

	@Override
	public List<Order> getAskList() {

		return askList;
	}

	public AlgorithmicTrade newBid (String volume) { 
 
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
		
		double lowest = askList.get(FIRST_ELEMENT).getPrice();
		for (Order ask : askList) {
			 if (lowest < ask.getPrice()) { 
				 lowest = ask.getPrice();
			 }
		}
		
		OrderBuilder orderBuilder = new OrderBuilderImpl(instrument,
										date,
										lowest,
										volume,
										bidOrAsk,
										buyerBrokerId);
		
		Order newOrder = orderBuilder.build();
		bidList.add(newOrder);	
		
		return tradeMatcher(newOrder);
	}
	
	public AlgorithmicTrade newAsk (String volume) { 

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
		
		double highest = bidList.get(FIRST_ELEMENT).getPrice();
		for (Order bid : bidList) {
			 if (highest > bid.getPrice()) { 
				 highest = bid.getPrice();
			 }
		}
		
		OrderBuilder orderBuilder = new OrderBuilderImpl(instrument,
										date,
										highest,
										volume,
										bidOrAsk,
										sellerBrokerId);
		
		Order newOrder = orderBuilder.build();
		askList.add(newOrder);	
		
		return tradeMatcher(newOrder);
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
	
}
