package main.implementations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import main.implementations.order.Order;
import main.interfaces.Orderbook;

public class OrderbookImpl implements Orderbook {

	private List<Order> bidList;
	private List<Order> askList;
	private Map<Order, Order> orderbook;

	public OrderbookImpl(List<Order> bidList, List<Order> askList) {
		this.bidList = bidList;
		this.askList = askList;
		this.orderbook = new HashMap<Order, Order>();
		populateOrderbook();
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
