 package main.engine;

import main.Order;
import main.interfaces.OrderBuilder;

public class OrderBuilderImpl implements OrderBuilder {

	
	private String security;
	private String brokerId;
	private String time;
	private double price;
	private int quantity;
	private char bidOrAsk;
	
	@Override
	public Order createOrder(boolean isBid, String security, String brokerId,
			String time, double price, int quantity) {
		// TODO Auto-generated method stub
		Order neworder = new Order();


		/** buy case*/
		if(isBid==true){
			this.security = security;
			this.brokerId = brokerId;
			this.time = time;
			this.price = price;
			this.quantity = quantity;
			this.bidOrAsk = 'B';
			
			
		/** sell case */	
		}else if(isBid==false){
			this.security = security;
			this.brokerId = brokerId;
			this.time = time;
			this.price = price;
			this.quantity = quantity;
			this.bidOrAsk = 'A';
			
			
		}
		return neworder;	

	}
}