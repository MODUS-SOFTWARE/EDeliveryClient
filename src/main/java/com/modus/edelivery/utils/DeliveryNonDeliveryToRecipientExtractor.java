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

import com.modus.edeliveryclient.models.EvidenceParams;

import gr.modus.edelivery.adapter.messages.MessageParams;

public class DeliveryNonDeliveryToRecipientExtractor {
	String msgStr ; 
	public String getMsgStr() {
		return msgStr;
	}


	public void setMsgStr(String msgStr) {
		this.msgStr = msgStr;
	}

	DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder builder;
	Document dDoc;
	XPathFactory xpathFactory;
	XPath xPath ;
	public DeliveryNonDeliveryToRecipientExtractor(String msgStr){
	
		try {
			builder = domFactory.newDocumentBuilder();
			InputSource is = new InputSource(new StringReader(msgStr));
			dDoc = builder.parse(is);
			xpathFactory = XPathFactory.newInstance();
			xPath = xpathFactory.newInstance().newXPath();
			//String s = (String) xPath.evaluate("//*[local-name()='OriginalMsg']/text()", dDoc, XPathConstants.STRING);
			//System.out.println(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public EvidenceParams extractParams() throws XPathExpressionException{
		EvidenceParams params= new EvidenceParams();
		params.setEventcode(this.extractEventCode());
		params.setEvidence_identifier(this.extractEvidenceIdentifier());
		params.setEvidence_name(this.extractName());
		params.setEvidence_time(this.extractEventTime());
		params.setReference_document(this.extractMessageId());
		return params;
		
	}
	//http//:uri.etsi.org/REM/Event#Delivery
	//YDMED Access Point
	//MessageIdentifierByREMMD 627a2e9a-a146-461f-b62f-f22f5799fd55
	 //<EventTime>2017-06-19T15:50:09.148+03:00</EventTime>
	public String extractEventCode() throws XPathExpressionException{
		String s = (String) xPath.evaluate("/DeliveryNonDeliveryToRecipient/EventCode/text()", dDoc, XPathConstants.STRING);
		return s;
	}
	
	public String extractEvidenceIdentifier() throws XPathExpressionException{
		String s = (String) xPath.evaluate("/DeliveryNonDeliveryToRecipient/EvidenceIdentifier/text()", dDoc, XPathConstants.STRING);
		return s;
	}
	
	
	public String extractName() throws XPathExpressionException{
		String s = (String) xPath.evaluate("/DeliveryNonDeliveryToRecipient/EvidenceIssuerDetails/NamesPostalAddresses/NamePostalAddress/EntityName/Name/text()", dDoc, XPathConstants.STRING);
		return s;
	}
	public String extractEventTime() throws XPathExpressionException{
		String s = (String) xPath.evaluate("/DeliveryNonDeliveryToRecipient/EventTime/text()", dDoc, XPathConstants.STRING);
		return s;
	}
	
	public String extractMessageId() throws XPathExpressionException{
		String s = (String) xPath.evaluate("/DeliveryNonDeliveryToRecipient/SenderMessageDetails/MessageIdentifierByREMMD/text()", dDoc, XPathConstants.STRING);
		return s;
	}
	
	//
	
}
