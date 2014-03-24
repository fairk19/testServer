package utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class TimeHelper{
	
	public static void sleep(int TICK_TIME){
		try {
			Thread.sleep(TICK_TIME);
		} 
		catch (InterruptedException e){
		}
	}
	
	public static long getCurrentTime(){
		return System.currentTimeMillis()/1000;
	}
	
	public static String getGMT() {
		SimpleDateFormat sdf= new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss zzz", Locale.ENGLISH);
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		Date date = new Date();
		return sdf.format(date);
	}

	public static String getTime(){
		Date date = new Date();
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(date);
		int hours = calendar.get(Calendar.HOUR_OF_DAY);
		int minutes = calendar.get(Calendar.MINUTE);
		int seconds = calendar.get(Calendar.SECOND);
		StringBuilder string = new StringBuilder();
		string.append("'");
		string.append(String.valueOf(hours));
		string.append(":");
		if(minutes<10)
			string.append("0");
		string.append(String.valueOf(minutes));
		string.append(":");
		if(seconds<10)
			string.append("0");
		string.append(String.valueOf(seconds));
		string.append("'");
		return string.toString();
	}
}