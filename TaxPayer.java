package am.vector.VATReoprt.core;

public class TaxPayer {
	private int mTIN;
	private String mName;
	public void setTIN(int TIN){
		this.mTIN = TIN; 
	}
	public int getTIN(){
		return mTIN;
	}
	public void setName(String name){
		this.mName = name;
	}
	public String getName(){
		return mName;
	}
}
