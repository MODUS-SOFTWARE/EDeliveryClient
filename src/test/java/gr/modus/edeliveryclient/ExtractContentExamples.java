package gr.modus.edeliveryclient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;


import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;


public class ExtractContentExamples {

	@Test
	public void extractInfo1() throws IOException {
		String simpleStr = "C:/eclipseProj/edelivery/EDeliveryClient/src/test/resources/testSimp.xml";
		String simpleXml = "C:/eclipseProj/edelivery/EDeliveryClient/src/test/resources/employee.xml";
		String fileStr =			"C:\\eclipseProj\\edelivery\\EDeliveryClient\\src\\test\\resources\\standardBusinessDocumentAllXMLtest.xml";
		String filePayload = "C:\\eclipseProj\\edelivery\\EDeliveryClient\\src\\test\\resources\\RemDispatchWithSaml1.xml";
		String fileOuput = "C:\\eclipseProj\\edelivery\\EDeliveryClient\\src\\test\\resources\\StandardBusinessDocumentWithRemDispatchWithSaml1.xml";
		DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = domFactory.newDocumentBuilder();
			Document dDoc = builder.parse(simpleXml);
			XPathFactory xpathFactory = XPathFactory.newInstance();

			XPath xPath = xpathFactory.newInstance().newXPath();
			String s = (String) xPath.evaluate("/Employees/Employee[@id='" +1+ "']/age/text()", dDoc, XPathConstants.STRING);
			System.out.println(s);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void extractInfo2() throws IOException {
		
		String simpleXml = "C:/eclipseProj/edelivery/EDeliveryClient/src/test/resources/testSimp.xml";
		DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = domFactory.newDocumentBuilder();
			Document dDoc = builder.parse(simpleXml);
			XPathFactory xpathFactory = XPathFactory.newInstance();

			XPath xPath = xpathFactory.newInstance().newXPath();
			String s = (String) xPath.evaluate("/config/Request/requestqueue/text()", dDoc, XPathConstants.STRING);
			System.out.println(s);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void extractInfoRemDispathWithNamepath1() throws IOException {
		
		String simpleXml = "C:/eclipseProj/edelivery/EDeliveryClient/src/test/resources/remDispatchPart1.xml";
		DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = domFactory.newDocumentBuilder();
			Document dDoc = builder.parse(simpleXml);
			XPathFactory xpathFactory = XPathFactory.newInstance();

			XPath xPath = xpathFactory.newInstance().newXPath();
			//"//*[local-name()='requestURL']/text()"
			String s = (String) xPath.evaluate("//*[local-name()='Origin']/text()", dDoc, XPathConstants.STRING);
			System.out.println(s);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	@Test
	public void extractInfoRemDispathWithNamepath2() throws IOException {
		
		String simpleXml = "C:/eclipseProj/edelivery/EDeliveryClient/src/test/resources/RemDispatchWithSaml2.xml";
		DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = domFactory.newDocumentBuilder();
			Document dDoc = builder.parse(simpleXml);
			XPathFactory xpathFactory = XPathFactory.newInstance();

			XPath xPath = xpathFactory.newInstance().newXPath();
			//"//*[local-name()='requestURL']/text()"
			//ns7:Destinations AttributedElectronicAddress 
			String s = (String) xPath.evaluate("//*[local-name()='Destinations']/*/AttributedElectronicAddress/text()", dDoc, XPathConstants.STRING);
			System.out.println(s);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	@Test
	public void extractInfoRemDispathWithNamepath3() throws IOException {
		
		String simpleXml = "C:/eclipseProj/edelivery/EDeliveryClient/src/test/resources/RemDispatchWithSaml2.xml";
		DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = domFactory.newDocumentBuilder();
			Document dDoc = builder.parse(simpleXml);
			XPathFactory xpathFactory = XPathFactory.newInstance();

			XPath xPath = xpathFactory.newInstance().newXPath();
			//"//*[local-name()='requestURL']/text()"
			//ns7:Destinations AttributedElectronicAddress 
			String s = (String) xPath.evaluate("//*[local-name()='MsgMetaData']/MsgIdentification/Message-ID/text()", dDoc, XPathConstants.STRING);
			System.out.println(s);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	@Test
	public void extractInfoRemDispathWithNamepathDocument() throws IOException {
		
		String simpleXml = "C:/eclipseProj/edelivery/EDeliveryClient/src/test/resources/RemDispatchWithSaml2.xml";
		DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = domFactory.newDocumentBuilder();
			Document dDoc = builder.parse(simpleXml);
			XPathFactory xpathFactory = XPathFactory.newInstance();

			XPath xPath = xpathFactory.newInstance().newXPath();
			//"//*[local-name()='requestURL']/text()"
			//ns7:Destinations AttributedElectronicAddress 
			String s = (String) xPath.evaluate("//*[local-name()='OriginalMsg']/text()", dDoc, XPathConstants.STRING);
			System.out.println(s);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	

	
}
