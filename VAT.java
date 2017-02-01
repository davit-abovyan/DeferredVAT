package am.vector.VATReoprt.core;

import java.util.Calendar;
import java.util.Date;

public final class VAT {
	/**
	 * Method to extract reporting period from given calendar
	 * 
	 * @param cal Calendar object with the given date
	 * @param isQuarterly Flag to determine whether it is quarterly VAT payer 
	 * @return byte from 0 to 11 for monthly payer and 1 to 4 for quarterly payer
	 */
	public static byte extractMonthOrQuarter(Calendar cal, boolean isQuarterly){
		if(isQuarterly){
			switch((byte) cal.get(Calendar.MONTH)){
				case 0:
				case 1:
				case 2:
					return 1;
				case 3:
				case 4:
				case 5:
					return 2;
				case 6:
				case 7:
				case 8:
					return 3;
				case 9:
				case 10:
				case 11:
					return 4;
				default:
					return 0;
			}
		} else {
			return (byte) cal.get(Calendar.MONTH);
		}
	}
	/**
	 * Checks whether the specified date is in the specified period 
	 * 
	 * @param date the date to be checked 
	 * @param currPeriod the period to 
	 * @param isQuarterly the flag to specify whether it is quarterly VAT payer or not
	 * @return true if the date is in period otherwise false
	 */
	public static boolean inPeriod(Date date, Period currPeriod, boolean isQuarterly){
		return currPeriod.equals(new Period(date,isQuarterly));
	}
}
