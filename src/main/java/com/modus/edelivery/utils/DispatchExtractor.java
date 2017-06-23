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

import gr.modus.edelivery.adapter.messages.MessageParams;

public class DispatchExtractor {
	String msgStr ; 
	DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder builder;
	Document dDoc;
	XPathFactory xpathFactory;
	XPath xPath ;
	public DispatchExtractor(String msgStr){
	
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
	
	
	public MessageParams extractParams() throws XPathExpressionException{
		MessageParams params= new MessageParams();
		params.setMsgId(this.extractMsgId());
		params.setFilename(this.extractFilename());
		params.setDestinatorEmail(this.extractDestinatorEmail());
		params.setDestinatorName(this.extractDestinatorName());
		params.setOriginatorName(this.extractOriginatorName());
		params.setOriginatorEmail(this.extractOriginatorEmail());
		params.setFile(this.extractFile());
		params.setComments(this.extractComments());
		params.setTitle(this.extractTitle());
		return params;
		
	}
	
	public String extractOrigin() throws XPathExpressionException{
		String s = (String) xPath.evaluate("//*[local-name()='Origin']/text()", dDoc, XPathConstants.STRING);
		return s;
	}
	
	public String extractOriginatorName() throws XPathExpressionException{
		String s = (String) xPath.evaluate("//*[local-name()='Originators']/*/NamesPostalAddresses/NamePostalAddress/EntityName/Name/text()", dDoc, XPathConstants.STRING);
		return s;
	}
	public String extractOriginatorEmail() throws XPathExpressionException{
		String s = (String) xPath.evaluate("//*[local-name()='Originators']/*/AttributedElectronicAddress/text()", dDoc, XPathConstants.STRING);
		return s;
	}
	public String extractDestinatorName() throws XPathExpressionException{
		String s = (String) xPath.evaluate("//*[local-name()='Destinations']/*/NamesPostalAddresses/NamePostalAddress/EntityName/Name/text()", dDoc, XPathConstants.STRING);
		return s;
	}
	public String extractDestinatorEmail() throws XPathExpressionException{
		String s = (String) xPath.evaluate("//*[local-name()='Destinations']/*/AttributedElectronicAddress/text()", dDoc, XPathConstants.STRING);
		return s;
	}
	public String extractMsgId() throws XPathExpressionException{
		String s = (String) xPath.evaluate("//*[local-name()='Message-ID']/text()", dDoc, XPathConstants.STRING);
		return s;
	}
	public String extractFilename() throws XPathExpressionException{
		String s = (String) xPath.evaluate("//*[local-name()='NormalizedMsg']/*[local-name()='Attachment']/@Filename", dDoc, XPathConstants.STRING);
		return s;
	}
	
	public String extractFile() throws XPathExpressionException{
		String s = (String) xPath.evaluate("//*[local-name()='NormalizedMsg']/*/*[local-name()='Embedded']/text()", dDoc, XPathConstants.STRING);
		return s;
	}
	public String extractTitle() throws XPathExpressionException{
		String s = (String) xPath.evaluate("//*[local-name()='NormalizedMsg']/*/*[local-name()='Subject']/text()", dDoc, XPathConstants.STRING);
		return s;
	}
	public String extractComments() throws XPathExpressionException{
		String s = (String) xPath.evaluate("//*[local-name()='NormalizedMsg']/*/*[local-name()='Comments']/text()", dDoc, XPathConstants.STRING);
		return s;
	}
	
}
