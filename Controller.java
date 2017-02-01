package am.vector.VATReoprt.core;

import java.util.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import java.io.File;

/*
 * controller of main operations, singleton
 */
public class Controller {
	private boolean mIsQuarterly = false; // is quarterly VAT payer
	private int mSelfTIN = 0;			// the TIN of the company
	private List<TaxPayer> taxpayers;	// list of tax payers issuing or receiving invoices
	IReadInvoiceStrategy VATcontrol;	// currently selected invoices catalog
	/*
	 * Constructor
	 */
	public Controller(boolean isQuarterly, int selfTIN){
		this.mIsQuarterly = isQuarterly;
		this.mSelfTIN = selfTIN;
		taxpayers = new LinkedList<>();
	}
	public void setIsQuarterly(boolean isQuarterly){
		this.mIsQuarterly = isQuarterly;
	}
	public boolean getIsQuarterly(){
		return mIsQuarterly;
	}
	public void setSelfTIN(int selfTIN){
		this.mSelfTIN = selfTIN;
	}
	public int getSelfTIN(){
		return mSelfTIN;
	}
	/*
	 * selects the concrete invoice catalog
	 */
	public void load(){
		VATcontrol = new ReceivedVATCatalog(mIsQuarterly);
	}
	public void readXMLFile(File XML){
		VATcontrol.readXML(XML);
	}
}
