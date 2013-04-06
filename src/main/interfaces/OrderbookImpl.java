package main.interfaces;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import main.Order;

public class OrderbookImpl implements Orderbook {

	@Override
	public void setBidList(List<Order> bidList) {
		
		this.bidList = bidList;
		orderbook.clear();
		
		for (Order sameBid : bidList) { 
			for (Order sameAsk : askList) { 
				orderbook.put(sameBid, sameAsk);
			}
		}
	}

	@Override
	public void setAskList(List<Order> askList) {
		
		this.askList = askList;
		orderbook.clear();
		
		for (Order sameAsk : askList) { 
			for (Order sameBid: bidList) { 
				orderbook.put(sameAsk, sameBid);
			}
		}
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
		
		// checks if contains in map and bidList
		if (orderbook.containsKey(order) && bidList.contains(order)) {
			// if it does, then returns the value which the key is mapped to
			// hence using get
			Order key = orderbook.get(order);
			return key;
		} else if (orderbook.containsValue(order) && askList.contains(order)) { 
			Order key = orderbook.get(order);
			return key;
		} else { 
			return null;
		}
	}

	List<Order> bidList;
	List<Order> askList;
	Map<Order, Order> orderbook;
	
}
