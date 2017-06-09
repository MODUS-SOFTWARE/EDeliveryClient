package gr.modus.edeliveryclient;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.bind.Unmarshaller;
import javax.xml.xpath.XPathExpressionException;

import org.junit.Test;

import com.google.gson.Gson;
import com.modus.edelivery.utils.SBDMessageWrapper;
import com.modus.edelivery.utils.StreamUtils;
import com.modus.edeliveryclient.jaxb.standardbusinessdocument.SBDHFactory;
import com.modus.edeliveryclient.jaxb.standardbusinessdocument.StandardBusinessDocument;


public class SBDMessageExtractorTest {
	
	public  StandardBusinessDocument extractSBDMessage() throws JAXBException { //σκάει μόνο με main 
		String msg = "";
		try{
			JAXBContext jaxbContext = JAXBContext.newInstance(StandardBusinessDocument.class, SBDHFactory.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			StandardBusinessDocument sbd = (StandardBusinessDocument) JAXBIntrospector
					.getValue(jaxbUnmarshaller.unmarshal(new StringReader(msg)));
			System.out.println(  new Gson().toJson(sbd));
			return sbd;
		}
		catch(Throwable ex){
			ex.printStackTrace();
		}
		return null;
	}

	
	@Test
	public  void extractSenderIdentifier()throws JAXBException, IOException, XPathExpressionException { 
		String simpleXml = "C:\\eclipseProj\\edelivery\\EDeliveryClient\\src\\test\\resources\\Sbd3.xml";
		String msg = StreamUtils.stream2String(simpleXml, "UTF-8");
		SBDMessageWrapper sbdWrap = new SBDMessageWrapper(msg); 
		String s = sbdWrap.extractSenderIdentifier();
		System.out.println(s);
	}
	
	@Test
	public  void extractSenderAuthority()throws JAXBException, IOException, XPathExpressionException { 
		String simpleXml = "C:\\eclipseProj\\edelivery\\EDeliveryClient\\src\\test\\resources\\Sbd3.xml";
		String msg = StreamUtils.stream2String(simpleXml, "UTF-8");
		SBDMessageWrapper sbdWrap = new SBDMessageWrapper(msg); 
		String s = sbdWrap.extractSenderAuthority();
		System.out.println(s);
	}
	
	
	@Test
	public  void extractReceiverAuthority()throws JAXBException, IOException, XPathExpressionException { 
		String simpleXml = "C:\\eclipseProj\\edelivery\\EDeliveryClient\\src\\test\\resources\\Sbd3.xml";
		String msg = StreamUtils.stream2String(simpleXml, "UTF-8");
		SBDMessageWrapper sbdWrap = new SBDMessageWrapper(msg); 
		String s = sbdWrap.extractReceiverIdentifier();
		System.out.println(s);
	}
	
	@Test
	public  void extractReceiverIdentifier()throws JAXBException, IOException, XPathExpressionException { 
		String simpleXml = "C:\\eclipseProj\\edelivery\\EDeliveryClient\\src\\test\\resources\\Sbd3.xml";
		String msg = StreamUtils.stream2String(simpleXml, "UTF-8");
		SBDMessageWrapper sbdWrap = new SBDMessageWrapper(msg); 
		String s = sbdWrap.extractReceiverAuthority();
		System.out.println(s);
	}
}
