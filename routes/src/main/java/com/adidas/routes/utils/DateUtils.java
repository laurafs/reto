package com.adidas.routes.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	/**
	 * Returns total number of minutes between two dates
	 * @param initialDate
	 * @param finalDate
	 * @return
	 */
	public static long totalMinutes(Date initialDate ,Date finalDate){
		Calendar init=Calendar.getInstance();
		init.setTime(initialDate);
        Calendar end=Calendar.getInstance();
        end.setTime(finalDate);
        
	    long totalMinutos=0;
	    totalMinutos=((end.getTimeInMillis()-init.getTimeInMillis())/1000/60);
	    return totalMinutos;
	}
}
