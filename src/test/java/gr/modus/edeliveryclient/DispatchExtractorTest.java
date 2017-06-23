package gr.modus.edeliveryclient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.w3c.dom.Document;

import com.modus.edelivery.utils.DispatchExtractor;
import com.modus.edelivery.utils.StreamUtils;

public class DispatchExtractorTest {

	@Test
	public void extractOrigin() throws IOException, XPathExpressionException {

		String simpleXml = "C:/eclipseProj/edelivery/EDeliveryClient/src/test/resources/RemDispatchWithSaml2.xml";
		
		String msg = StreamUtils.stream2String(simpleXml, "UTF-8");
		DispatchExtractor disex = new DispatchExtractor(msg);
		System.out.println(disex.extractOrigin()); 
	}
	
	@Test
	public void extractOriginatorName() throws IOException, XPathExpressionException {

		String simpleXml = "C:/eclipseProj/edelivery/EDeliveryClient/src/test/resources/RemDispatchWithSaml2.xml";
		
		String msg = StreamUtils.stream2String(simpleXml, "UTF-8");
		DispatchExtractor disex = new DispatchExtractor(msg);
		System.out.println(disex.extractOriginatorName()); 
	}
	
	
	@Test
	public void extractOriginatorEmail() throws IOException, XPathExpressionException {
		String simpleXml = "C:/eclipseProj/edelivery/EDeliveryClient/src/test/resources/RemDispatchWithSaml2.xml";
		
		String msg = StreamUtils.stream2String(simpleXml, "UTF-8");
		DispatchExtractor disex = new DispatchExtractor(msg);
		System.out.println(disex.extractOriginatorEmail()); 
	}

	@Test
	public void extractDestinationsName() throws IOException, XPathExpressionException {
		String simpleXml = "C:/eclipseProj/edelivery/EDeliveryClient/src/test/resources/RemDispatchWithSaml2.xml";
		
		String msg = StreamUtils.stream2String(simpleXml, "UTF-8");
		DispatchExtractor disex = new DispatchExtractor(msg);
		System.out.println(disex.extractDestinatorName()); 
	}

	@Test
	public void extractDestinationsEmail() throws IOException, XPathExpressionException {
		String simpleXml = "C:/eclipseProj/edelivery/EDeliveryClient/src/test/resources/RemDispatchWithSaml2.xml";
		
		String msg = StreamUtils.stream2String(simpleXml, "UTF-8");
		DispatchExtractor disex = new DispatchExtractor(msg);
		System.out.println(disex.extractDestinatorEmail()); 
	}
	
	
	@Test
	public void extractMsgId() throws IOException, XPathExpressionException {
		String simpleXml = "C:/eclipseProj/edelivery/EDeliveryClient/src/test/resources/RemDispatchWithSaml2.xml";
		
		String msg = StreamUtils.stream2String(simpleXml, "UTF-8");
		DispatchExtractor disex = new DispatchExtractor(msg);
		System.out.println(disex.extractMsgId()); 
	}
	
	@Test
	public void extractFilename() throws IOException, XPathExpressionException {
		String simpleXml = "C:/eclipseProj/edelivery/EDeliveryClient/src/test/resources/RemDispatchWithSaml2.xml";
		
		String msg = StreamUtils.stream2String(simpleXml, "UTF-8");
		DispatchExtractor disex = new DispatchExtractor(msg);
		System.out.println(disex.extractFilename()); 
	}
	
	@Test
	public void extractFile() throws IOException, XPathExpressionException {
		String simpleXml = "C:/eclipseProj/edelivery/EDeliveryClient/src/test/resources/RemDispatchWithSaml2.xml";
		
		String msg = StreamUtils.stream2String(simpleXml, "UTF-8");
		DispatchExtractor disex = new DispatchExtractor(msg);
		System.out.println(disex.extractFile()); 
	}
	
	
	@Test
	public void extractComments() throws IOException, XPathExpressionException {
		String simpleXml = "C:/eclipseProj/edelivery/EDeliveryClient/src/test/resources/RemDispatchWithSaml2.xml";
		
		String msg = StreamUtils.stream2String(simpleXml, "UTF-8");
		DispatchExtractor disex = new DispatchExtractor(msg);
		System.out.println(disex.extractComments()); 
	}
	
	
	@Test
	public void extractTitle() throws IOException, XPathExpressionException {
		String simpleXml = "C:/eclipseProj/edelivery/EDeliveryClient/src/test/resources/RemDispatchWithSaml2.xml";
		
		String msg = StreamUtils.stream2String(simpleXml, "UTF-8");
		DispatchExtractor disex = new DispatchExtractor(msg);
		System.out.println(disex.extractTitle()); 
	}
	
	
}
