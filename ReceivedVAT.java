package am.vector.VATReoprt.core;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class ReceivedVAT {
	private Date mSupplyDate; // date of delivery
	private int mTIN;			// TIN of invoice issuer
	private String mInvoiceNumber; // invoice Number without first capital letter
	private Date mSignDate;		// date of issuing invoice
	private double mVAT;		// VAT amount
	private double mSum;		// total amount without VAT
	private Period period;		// VAT period corresponding to the invoice
	private List<DeferedState> states; // list of consequent states of the invoice
	/*
	 * Constructor
	 */
	public ReceivedVAT(){
		states = new LinkedList<>();
	}
	public ReceivedVAT(Date supply, double sum,int TIN, String number, Date sign, double VAT){
		this.mSupplyDate = supply;
		this.mTIN = TIN;
		this.mInvoiceNumber = number;
		this.mSignDate = sign;
		this.mVAT = VAT;
		this.mSum = sum;
		states = new LinkedList<>();
	}
	public void setSupplyDate(Date supplyDate){
		this.mSupplyDate = supplyDate;
	}
	public Date getSupplyDate(){
		return mSupplyDate;
	}
	public void setTIN(int TIN){
		this.mTIN = TIN;
	}
	public int getTIN(){
		return mTIN;
	}
	public void setInvoiceNovember(String invoiceNumber){
		this.mInvoiceNumber = invoiceNumber;
	}
	public String getInvoiceNumber(){
		return mInvoiceNumber;
	}
	public void setSignDate(Date signDate){
		this.mSignDate = signDate;
	}
	public Date getSignDate(){
		return mSignDate;
	}
	public void setVAT(double VAT){
		this.mVAT = VAT;
	}
	public double getVAT(){
		return mVAT;
	}
	public void setSum(double sum){
		this.mSum = sum;
	}
	public double getSum(){
		return mSum;
	}
	public void setPeriod(Period period){
		this.period = period;
	}
	public Period getPeriod(){
		return this.period;
	}
	/*
	public InvState checkState(Period period){

		if()
		return
		 
	}
	*/
	@Override
	public String toString(){
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		return String.format("Invoice N:%s, Total %.2f, VAT %.2f - Supply: %s, Sign: %s ",
				mInvoiceNumber, mSum, mVAT,	df.format(mSupplyDate),	df.format(mSignDate));
	}
	@Override
	public int hashCode(){
		return Integer.parseInt(mInvoiceNumber.substring(1));
	}
	@Override
	public boolean equals(Object inv){
		ReceivedVAT temp = (ReceivedVAT) inv;
		return mInvoiceNumber.equals(temp.getInvoiceNumber());
	}
	
}
