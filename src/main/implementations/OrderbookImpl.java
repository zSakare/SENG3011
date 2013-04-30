package main.implementations;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import main.implementations.order.Order;
import main.implementations.order.Trade;
import main.interfaces.OrderBuilder;
import main.interfaces.Orderbook;
import main.utils.TimeUtil;

public class OrderbookImpl implements Orderbook {


	private static final int FIRST_ELEMENT = 0;
	private static final String RANDOM_BROKER_ID = "6969";
	private static final String ORDER_BID = "B";
	private static final String ORDER_ASK = "A";
	private List<Order> bidList;
	private List<Order> askList;
	private Map<Order, Order> orderbook;
	

	public OrderbookImpl(List<Order> bidList, List<Order> askList) {
		this.bidList = bidList;
		this.askList = askList;
		this.orderbook = new HashMap<Order, Order>();
		//populateOrderbook();
	}
	
	@Override
	public void setBidList(List<Order> bidList) {
		
		this.bidList = bidList;
		populateOrderbook();
	}

	@Override
	public void setAskList(List<Order> askList) {
		
		this.askList = askList;
		populateOrderbook();
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
	public Map<Order, Order> getOrderbook() {

		return orderbook;
	}

	@Override
	public Order getMatch(Order order) {
		
		// Check if the order is a bid or ask order.
		if (order.isBid()) {
			Order askOrder = null;
			// Is a bid order.
			if (orderbook.containsKey(order) && bidList.contains(order)) {
				// if it does, then returns the value which the key is mapped to
				askOrder = orderbook.get(order);
			}	

			return askOrder;
		} else {
			Order buyOrder = null;
			// Is an ask order.
			if (orderbook.containsValue(order) && askList.contains(order)) {
				//Find the matching bid order.
				Iterator it = orderbook.entrySet().iterator();
				while (it.hasNext() && buyOrder == null) {
					Map.Entry<Order, Order> orderPair = (Map.Entry<Order, Order>) it.next();
					if (orderPair.getValue().equals(order)) {
						buyOrder = orderPair.getKey();
					}
				}
			}
			return buyOrder;
		}
	}

	/*
	public void newBid (String instrument, Date dateTime, String recordType, double price,
							int volume, int undisclosedVolume, double value, String qualifiers,
							String transactionId, String bidId, String askId, boolean isBid,
							Date entryTime, double oldPrice, int oldVolume, String buyerBrokerId,
							String sellerBrokerId) {
	*/
	
	public Trade newBid (String volume) { 
 
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
			if (date.before(ask.getDateTime())) { 
				if (lowest < ask.getPrice()) { 
					lowest = ask.getPrice();
				}
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
	
	public Trade newAsk (String volume) { 

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
			if (date.before(bid.getDateTime())) { 
				if (highest > bid.getPrice()) { 
					highest = bid.getPrice();
				}
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
	
	public Trade tradeMatcher (Order orderToCompare) { 
		
		Order matchedOrder = null;
		Trade matchedTrade = null;
		
		if (orderToCompare.isBid()) { 
			for (Order ask : askList) { 
				if (orderToCompare.getPrice() == ask.getPrice()) {
					matchedOrder = ask; 
					matchedTrade = new Trade(orderToCompare, matchedOrder);
				}
			}
		} else { 
			for (Order bid : bidList) { 
				if (orderToCompare.getPrice () == bid.getPrice()) { 
					matchedOrder = bid;
					matchedTrade = new Trade(matchedOrder, orderToCompare);
				}
			}
		}
		
		return matchedTrade;
	}
	
	
	
	/**
	 * Helper function that cleans up the current state of the orderbook and repopulates it based on the new bid or ask list.
	 */
	private void populateOrderbook() {
		orderbook.clear();
		for (Order bidOrder : this.bidList) {
			for (Order askOrder : askList) {
				// Check if same security and price.
				if (bidOrder.getInstrument().equals(askOrder.getInstrument()) && bidOrder.getPrice() == askOrder.getPrice()) {
					// Map the orders if one does not already exist. If it does, compare dates and match the most recent.
					if (orderbook.containsKey(bidOrder)) {
						Order currentAskOrder = orderbook.get(bidOrder);
						if (currentAskOrder.getDateTime().getTime() > askOrder.getDateTime().getTime()) {
							// Current ask order mapping is later than the ask order, replace it.
							orderbook.remove(bidOrder);
							orderbook.put(bidOrder, askOrder);
						}
					} else {
						// Simply put in new mapping.
						orderbook.put(bidOrder, askOrder);
					}
				}
			}
		}
	}
}
