package com.modus.edelivery.utils;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class SBDMessageWrapper {
	String SBDMessageStr ;
	DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder builder;
	Document dDoc;
	XPathFactory xpathFactory;
	XPath xPath ;
	
	public SBDMessageWrapper(){}
	public SBDMessageWrapper(String msg){
		this.SBDMessageStr =msg;
		try {
			builder = domFactory.newDocumentBuilder();
			InputSource is = new InputSource(new StringReader(SBDMessageStr));
			dDoc = builder.parse(is);
			xpathFactory = XPathFactory.newInstance();
			xPath = xpathFactory.newInstance().newXPath();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getSBDMessageStr() {
		return SBDMessageStr;
	}


	public void setSBDMessageStr(String sBDMessageStr) {
		SBDMessageStr = sBDMessageStr;
	}


	/**
	 * H μέθοδος αυτή λαμβάνει το payload και το κάνει append σαν έγγραφο στο SBD. To SBD 
	 * πρέπει να είναι String.  
	 * @param payload
	 */
	public void appendPayload(String payload){
		String part1 = "";
		String part2 = "";
		if(this.SBDMessageStr.indexOf("</StandardBusinessDocumentHeader>")>-1){
			part1 = this.SBDMessageStr.substring(0, this.SBDMessageStr.indexOf("</StandardBusinessDocumentHeader>")+"</StandardBusinessDocumentHeader>".length());
			part2= this.SBDMessageStr.substring(this.SBDMessageStr.indexOf("</StandardBusinessDocumentHeader>")+"</StandardBusinessDocumentHeader>".length(),this.SBDMessageStr.length());
			this.SBDMessageStr = part1 + payload+ part2;
		}
	}
	
	public String getPayload(boolean remove){
		String part1 = "";
		String part2 = "";
		String part3="";
		
		int index1=this.SBDMessageStr.indexOf("</StandardBusinessDocumentHeader>");
		if(index1>-1){
			//part1 = this.SBDMessageStr.substring(0, this.SBDMessageStr.indexOf("</StandardBusinessDocumentHeader>")+"</StandardBusinessDocumentHeader>".length());
			int index2 =this.SBDMessageStr.indexOf("</StandardBusinessDocument>"); 
			if(index2>-1){
				part2=	this.SBDMessageStr.substring(index1+"</StandardBusinessDocumentHeader>".length(),index2-1);// TODO ίσως καλύτερα trim.
				if(remove){
					StringBuffer sb = new  StringBuffer(this.SBDMessageStr);
					sb.replace(index1, index2-1, "");
					this.SBDMessageStr = sb.toString();
				}
			}
		}
		return part2;
	}
	

	
	
	
	public String extractSenderIdentifier() throws XPathExpressionException{
		String s = (String) xPath.evaluate("/StandardBusinessDocument/StandardBusinessDocumentHeader/Sender/Identifier/text()", dDoc, XPathConstants.STRING);
		return s;
	}
	
	public String extractSenderAuthority() throws XPathExpressionException{
		String s = (String) xPath.evaluate("/StandardBusinessDocument/StandardBusinessDocumentHeader/Sender/Identifier/@Authority", dDoc, XPathConstants.STRING);
		return s;
	}
	
	public String extractReceiverIdentifier() throws XPathExpressionException{
		String s = (String) xPath.evaluate("/StandardBusinessDocument/StandardBusinessDocumentHeader/Receiver/Identifier/text()", dDoc, XPathConstants.STRING);
		return s;
	}
	public String extractReceiverAuthority() throws XPathExpressionException{
		String s = (String) xPath.evaluate("/StandardBusinessDocument/StandardBusinessDocumentHeader/Receiver/Identifier/@Authority", dDoc, XPathConstants.STRING);
		return s;
	}
}
