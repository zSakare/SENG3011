package main.engine;

//import java.util.Date;

public class clock {
	private int clockHour, clockMinute, clockSecond;


public void randomizeClockTime() {
	clockHour = (int) (Math.random() * 24);
	clockMinute = (int) (Math.random() * 60);
	clockSecond = (int) (Math.random() * 60);
	System.out.println ( + clockHour + ":" + clockMinute + ":" + clockSecond);
}



}
