package main.utils;

import java.util.Random;
import java.sql.Time;

public class TimeUtil {
	public static Time generateTime() {
	
		final Random random = new Random();
		final int millisInDay = 24*60*60*1000;
		Time time = new Time((long)random.nextInt(millisInDay));

		return time;
	}
}	
