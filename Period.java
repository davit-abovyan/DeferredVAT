package am.vector.VATReoprt.core;

import java.util.*;

public final class Period {
	public byte monthOrQuarter;
	public short year;
	public boolean isQuarterly;
	Period(byte monthOrQuarter, short year, boolean isQuarterly){
		this.monthOrQuarter = monthOrQuarter;
		this.year = year;
		this.isQuarterly = isQuarterly;
	}
	Period(Date period, boolean isQuarterly){
		Calendar cal = Calendar.getInstance();
		cal.setTime(period);
		this.monthOrQuarter = VAT.extractMonthOrQuarter(cal,isQuarterly);
		this.year = (short) cal.get(Calendar.YEAR);
		this.isQuarterly = isQuarterly;
	}
	public byte getMonth(){
		return monthOrQuarter;
	}
	public short getYear(){
		return year;
	}

	@Override
	public boolean equals(Object o){
		if(!(o instanceof Period)){
			return false;
		} else {
			Period per = (Period) o;
			return monthOrQuarter == per.monthOrQuarter && year == per.year;
		} //end if
	}
	@Override
	public String toString(){
		return monthOrQuarter+"_"+year;
	}
	@Override
	public int hashCode(){
		return (int) year*100+monthOrQuarter;
	}
}
