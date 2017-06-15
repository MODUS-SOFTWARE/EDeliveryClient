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

import com.modus.edeliveryclient.models.SBDParams;

import gr.modus.edelivery.adapter.messages.MessageParams;

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
/*private   String participantIdentifierSenderScheme = "iso6523-actorid-upis";
    private   String participantIdentifierSenderValue = "9933:test1";
    private   String participantIdentifierReceiverScheme = "iso6523-actorid-upis";
    private   String participantIdentifierReceiverValue = "9933:test1";

    private   String documentIdStandard = "urn:oasis:names:specification:ubl:schema:xsd:Invoice-2";
    private   int docTypeVersion = 1;
    private   String documentInstanceIdentifier = "627a2e9a-a146-461f-b62f-f22f5799fd55";
    private   String documentType = "Invoice";

    private   String scopeType = "DOCUMENTID";
    private   String scopeIdentifier = "urn:oasis:names:specification:ubl:schema:xsd:Invoice-2:: Invoice##urn:www.cenbii.eu:transaction:biitrns010:ver2.0:extended:urn: www.peppol.eu:bis:peppol4a:ver2.0::2.1";
    private   String scopeType2 = "PROCESSID";
    private   String scopeIdentifier2 = "urn:www.cenbii.eu:profile:bii04:ver2.0";

    private   String manifestDescr = "manifestDescr";
    private   String manifestLanguage = " manifestLanguage";
    private   String maniTypeQualCode = "maniTypeQualCode";
    private   String uniformResourceIdentifier = "uniformResourceIdentifier";*/
	
	public SBDParams extractParams() throws XPathExpressionException{
		SBDParams params= new SBDParams();
		//params.setDocTypeVersion(docTypeVersion); TODO OTHER fields
		params.setParticipantIdentifierReceiverScheme(this.extractReceiverAuthority());
		params.setParticipantIdentifierReceiverValue(this.extractReceiverIdentifier());
		params.setParticipantIdentifierSenderScheme(this.extractSenderAuthority());
		params.setParticipantIdentifierSenderValue(this.extractSenderIdentifier());
		
		return params;
		
	}
}
