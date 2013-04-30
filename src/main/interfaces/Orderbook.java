package main.interfaces;

import java.util.List;
import java.util.Map;

import main.implementations.order.Order;
import main.implementations.order.AlgorithmicTrade;

/**
 * Orderbook interface which handles order generating and trade generating. 
 */
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
	 * Sets the trade list to the given trade list.
	 * 
	 * @param tradeList - the trades to populate the orderbook with.
	 */
	public void setTradeList(List<Order> tradeList);

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
	 * Returns the trade list of trades in the orderbook.
	 * 
	 * @return the trade list.
	 */
	public List<Order> getTradeList();
	
	/**
	 * Generate new ask order given a volume.
	 * 
	 * @param volume - number of stock to generate in order.
	 * @return trade - returns a matched trade.
	 */
	public AlgorithmicTrade newAsk(String volume);

	/**
	 * Generate new bid order given a volume.
	 * 
	 * @param volume - number of stock to generate in order.
	 * @return trade - returns a matched trade.
	 */
	public AlgorithmicTrade newBid(String volume);

}
