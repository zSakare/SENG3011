/**
 * TradeStrategyEvaluator.java
 * 
 * This class will look at the list of algorithmic trade and give some sort of payoff
 * (profit or loss) from using the trading strategy.
 * 
 * */

package main.evaluator;
import java.util.LinkedList;

public class TradeStrategyEvaluator {
	
	/**
	 * Constructor accepts list of algorithmic trades to evaluate.
	 * @param tradeList The list of algorithmic trades of TradeList type. 
	 * (TradeList type not yet implemented.)
	 * */
	public TradeStrategyEvaluator(TradeList tradeList) {
		tl = tradeList;
	}
	
	/**
	 * Gets the profit/loss value for the algorithmic trades within a trade list
	 * @param tradeList A list of trades of TradeList type.
	 * @return balance The profit or loss as a double value. 
	 * */
	public double calculateProfitLoss(TradeList tradeList) {
		double bid = 0;
		double ask = 0;
		// add all the bid and ask orders
		while (tl.hasNext()) {
			if (tl.trade.brokerID == brokerID && tl.trade.type == ASK) {
				ask += tl.trade.price * trade.volume;
			} else if (tl.trade.brokerID == brokerID && tl.trade.type == BID) {
				sell += tl.trade.price * trade.volume;
			}
		}
		return ask - bid;
	}
	
	private TradeList tl;
	private static int brokerID = 6969;	// just for lolz
	private static char BID = 'B';
	private static char ASK = 'A'
}
