package main.interfaces;

import java.util.List;
import java.util.Map;

import main.implementations.order.Order;
import main.parser.SircaOrder;

public interface Orderbook {

	/**
	 * Set the existing bid list in the orderbook to provided list of orders.
	 * This function should additionally remove all the existing bid order mappings in the orderbook
	 * and recreate the matching orders in the order book.
	 * @param bidList TODO
	 */
	public void setBidList(List<Order> bidList);
	

	/**
	 * Set the existing ask list in the orderbook to provided list of orders.
	 * This function should additionally remove all the existing ask order mappings in the orderbook
	 * and recreate the matching orders in the order book.
	 * 
	 * @param askList - the ask list to replace the current ask list with.
	 */
	public void setAskList(List<Order> askList);
	
	/**
	 * Returns the current bid list.
	 * 
	 * @return the bid list.
	 */
	public List<Order> getBidList();
	
	/**
	 * Returns the current ask list.
	 * 
	 * @return the ask list.
	 */
	public List<Order> getAskList();
	
	/**
	 * Returns the current orderbook.
	 * 
	 * @return the orderbook.
	 */
	public Map<Order, Order> getOrderbook();
	
	/**
	 * Finds a matching order in the orderbook based on the order provided.
	 * 
	 * @param order - the order to perform a search with.
	 * @return - returns the matching order of OPPOSITE action i.e. 
	 * if a bid order is input, an ask order is returned & vice versa.
	 */
	public Order getMatch(Order order);

}
