package main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;

public class SircoCSVParser {
	
	public static void main(String[] argv) {
		System.out.println("Hello World");
		input();
		
		}
	
	
	public static void input(){
		ColumnPositionMappingStrategy strat = new ColumnPositionMappingStrategy();
		strat.setType(Order.class);
		String[] columns = new String[] {"instrument", "date", "time", "recordType", "price", "volume", "undisclosedVolume", "value","qualifiers", "transactionId", "bidId","askId", "bidOrAsk","entryTime","oldPrice", "oldVolume", "buyerBrokerId", "sellerBrokerId" }; // the fields to bind do in your JavaBean
		strat.setColumnMapping(columns);
		 
		CsvToBean csv = new CsvToBean();
		 
		String csvFilename = "resources/sircoInput.csv";
		CSVReader csvReader = null;
		try {
			csvReader = new CSVReader(new FileReader(csvFilename));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		 
		List list = csv.parse(strat, csvReader);
		for (Object object : list) {
		    Order order = (Order) object;
		    System.out.println(order.getDate());
		    System.out.println(order.getInstrument());
		    System.out.println(order.getAskId());
	    }
	}
}


