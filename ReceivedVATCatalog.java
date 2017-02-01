package am.vector.VATReoprt.core;

import java.text.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;

import java.util.*;
import java.io.File;

public class ReceivedVATCatalog implements IReadInvoiceStrategy {
	private List<ReceivedVAT> receiveds;
	private Set<Period> allPeriods;
	private long mLastAdded=0;
	private boolean mIsQuarterly;
	public ReceivedVATCatalog(boolean isQuarterly){
		receiveds = new LinkedList<>();
		allPeriods = new HashSet<>();
		this.mIsQuarterly = isQuarterly;
	}
	public void setLastAdded(long last){
		this.mLastAdded = last;
	}
	public long getLastAdded(){
		return mLastAdded;
	}
	/*
	 * Reads XML File
	 */
	public void readXML(File XML){
		receiveds = new ArrayList<>();
		try{
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = builderFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(XML);
			doc.getDocumentElement().normalize();
			NodeList invList = doc.getElementsByTagName("SignedData");
			for(int i=0; i<invList.getLength();i++){
				ReceivedVAT newInvoice = new ReceivedVAT();
				Node xInvoice = invList.item(i);
				NodeList invDetails = xInvoice.getChildNodes();
				for(int j=0;j<invDetails.getLength();j++){
					switch(invDetails.item(j).getNodeName()){
					case "#text":
					case "Signature":
						break;
					case "Data":
						// SignableData node
						NodeList xData = invDetails.item(j).getFirstChild().getNextSibling().getChildNodes(); 
						for(int k=0;k<xData.getLength();k++){
							if(xData.item(k).getNodeName().equals("GeneralInfo")){
								NodeList li = xData.item(k).getChildNodes();
								for(int m=0;m<li.getLength();m++){
									switch(li.item(m).getNodeName()){
										case "InvoiceNumber":
											newInvoice.setInvoiceNovember(li.item(m).getFirstChild().getNextSibling().getTextContent());
											break;
										case "SupplyDate":
											String formatString;
											if(li.item(m).getTextContent().length()==10){
												formatString = "yyyy-MM-dd";
											} else {
												formatString = "yyyy-MM-ddX";
											}
											DateFormat format = new SimpleDateFormat(formatString, Locale.ENGLISH);
											Date supplyDate = format.parse(li.item(m).getTextContent());
											allPeriods.add(new Period(supplyDate,mIsQuarterly));
											Iterator<Period> it = allPeriods.iterator();
											while(it.hasNext()){
												Period period = it.next();
												if(VAT.inPeriod(supplyDate, period, mIsQuarterly)){
													newInvoice.setPeriod(period);
													break;
												}
											}
											newInvoice.setSupplyDate(supplyDate);
											break;
										default: break;
									} // end switch
								} // end for
							} // end if
							if(xData.item(k).getNodeName().equals("GoodsInfo")){
								// Total node
								NodeList li = xData.item(k).getLastChild().getPreviousSibling().getChildNodes();
								for(int m=0;m<li.getLength();m++){
									switch(li.item(m).getNodeName()){
										case "Price":
											newInvoice.setSum(Double.parseDouble(li.item(m).getTextContent()));
											break;
										case "VAT":
											newInvoice.setVAT(Double.parseDouble(li.item(m).getTextContent()));
											break;
										default: break;
									} // end switch
								} // end for
							} // end if
						} // end for							
						break;
					case "InvoiceMetadata":
						NodeList li = invDetails.item(j).getChildNodes();
						DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX",Locale.ENGLISH);
						boolean testForSign = false;
						for(int z=0;z<li.getLength();z++){
							switch(li.item(z).getNodeName()){
								case "SubmissionDate":
									mLastAdded = df.parse(li.item(z).getTextContent()).getTime();
									break;
								case "CoSignDate":
									newInvoice.setSignDate(df.parse(li.item(z).getTextContent()));
									testForSign = true;
									break;
								default: break;
							}//end switch
						}//end for
						if(!testForSign){
							newInvoice.setSignDate(df.parse("1970-01-01T00:00:00.000+04:00"));
						}//end if
					break;
					} // end switch
				} // end for
			/*	
				if (invNumber.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) invNumber;
					System.out.println(eElement.getElementsByTagName("Number").item(0).getTextContent());
				}
			 */
				receiveds.add(newInvoice);
			} // end for
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		for(int i=0;i<receiveds.size();i++){
			System.out.println(receiveds.get(i));
		}
	} // end ReadXML
} // end class 
