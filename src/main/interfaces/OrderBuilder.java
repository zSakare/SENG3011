package main.interfaces;

import main.Order;

public interface OrderBuilder {
	
	/**
	 * Default values:
	 */
	public static final String DEFAULT_INSTRUMENT = "BHP";
	public static final String DEFAULT_DATE = "20130205";
	public static final String DEFAULT_TIME = "00:00";
	public static final String DEFAULT_RECORD_TYPE = "ENTER";
	public static final double DEFAULT_PRICE = 37.75;
	public static final int DEFAULT_VOLUME = 3000;
	public static final int DEFAULT_UNDISCLOSED_VOLUME = 0;
	public static final int DEFAULT_VALUE = 113250;
	public static final String DEFAULT_QUALIFIERS = "";
	public static final int DEFAULT_TRANSACTIONID = 0;
	public static final String DEFAULT_BIDID = "6230017330349680000";
	public static final String DEFAULT_ASKID = "";
	public static final String DEFAULT_BID_OR_ASK = "B";
	public static final String DEFAULT_ENTRY_TIME = "";
	public static final double DEFAULT_OLD_PRICE = 0;
	public static final int DEFAULT_OLD_VOLUME = 0;
	public static final int DEFAULT_OLD_BUYER_BROKER = 266;
	public static final int DEFAULT_OLD_SELLER_BROKER = 0;
	
	/**
	 * Receives all the required identifiers for a order to be created.
	 * 
	 * @param isBid - a boolean to determine whether to create a bid order or ask order.
	 * @param security - the security to create an order for (company/stock name)
	 * @param brokerId - unique broker id (generate a random one)
	 * @param time - time in 24 hour time. (you may wish to convert this to a java Date object.
	 * @param price - price in dollars. 
	 * @param quantity - quantity of stock to sell/buy.
	 * @return - the created order with the appropriate type.
	 */
	public Order createOrder(boolean isBid, String security, String brokerId, String time, double price, int quantity);
	
}
