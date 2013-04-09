package main.parser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import main.implementations.OrderBuilderImpl;
import main.implementations.OrderbookImpl;
import main.implementations.order.Order;
import main.interfaces.OrderBuilder;
import main.interfaces.Orderbook;
import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;

public class SircaCSVParser {
	
	public static void input() {
		ColumnPositionMappingStrategy strat = new ColumnPositionMappingStrategy();
		strat.setType(SircaOrder.class);
		String[] columns = new String[] {"instrument", "date", "time", "recordType", "price", "volume", "undisclosedVolume", "value","qualifiers", "transactionId", "bidId","askId", "bidOrAsk","entryTime","oldPrice", "oldVolume", "buyerBrokerId", "sellerBrokerId" }; // the fields to bind do in your JavaBean
		strat.setColumnMapping(columns);
		 
		CsvToBean csv = new CsvToBean();
		 
		String csvFilename = "./resources/sircaInput.csv";
		CSVReader csvReader = null;
		try {
			csvReader = new CSVReader(new FileReader(csvFilename), ',', '\'', 2);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		List list = csv.parse(strat, csvReader);
		
		// bid and ask lists.
		List<Order> bidList = new ArrayList<Order>();
		List<Order> askList = new ArrayList<Order>();
		long timeBefore = System.currentTimeMillis();
		for (Object object : list) {
		    SircaOrder order = (SircaOrder) object;
		    OrderBuilder orderBuilder = new OrderBuilderImpl(order);
		    if (("B").equals(order.getBidOrAsk())) {
		    	bidList.add(orderBuilder.build());
		    } else if (("A").equals(order.getBidOrAsk())) {
		    	askList.add(orderBuilder.build());
		    }
	    }
		
		Orderbook orderbook = new OrderbookImpl(bidList, askList);
		long timeAfter = System.currentTimeMillis();
		
		System.out.println("Total time taken: " + (timeAfter - timeBefore));
	}
}


