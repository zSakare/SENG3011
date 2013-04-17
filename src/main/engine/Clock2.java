package main.engine;

import java.util.Random;
import java.sql.Time;

public class Clock2 {
	public static void main(String[] args) {
		
		for (int i=0; i< 100; i++){	
			final Random random = new Random();
			final int millisInDay = 24*60*60*1000;
			Time time = new Time((long)random.nextInt(millisInDay));
			System.out.println(time);
		}

	}
}	
