package main.implementations.order;

public class Trade {
	
	private Order bidOrder;
	private Order askOrder;
	
	public Trade(Order bidOrder, Order askOrder) {
		this.bidOrder = bidOrder;
		this.askOrder = askOrder;
	}
	
	public Order getBidOrder() {
		return bidOrder;
	}
	
	public Order getAskOrder() {
		return askOrder;
	}
	
	/**
	 * Calculate the value of the trade
	 * 
	 * @return value - as dollars with type double.
	 */
	public double evaluateTrade() {
		// Find out which one has the lower volume.
		int lowerVolume;
		
		if (bidOrder.getVolume() < askOrder.getVolume()) {
			lowerVolume = bidOrder.getVolume();
		} else {
			lowerVolume = askOrder.getVolume();
		}
		
		return bidOrder.getPrice()*lowerVolume;
	}
}
