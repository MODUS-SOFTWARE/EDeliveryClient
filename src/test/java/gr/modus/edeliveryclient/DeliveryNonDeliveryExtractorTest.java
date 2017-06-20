package gr.modus.edeliveryclient;

import java.io.IOException;

import javax.xml.xpath.XPathExpressionException;

import org.junit.Test;

import com.google.gson.Gson;
import com.modus.edelivery.utils.DeliveryNonDeliveryToRecipientExtractor;
import com.modus.edelivery.utils.StreamUtils;

public class DeliveryNonDeliveryExtractorTest {
	@Test
	public void extractParams() throws IOException, XPathExpressionException {

		String simpleXml = "C:\\eclipseProj\\edelivery\\EDeliveryClient\\src\\test\\resources\\DeliveryNonDeliveryToRecipient.xml";
		
		String msg = StreamUtils.stream2String(simpleXml, "UTF-8");
		DeliveryNonDeliveryToRecipientExtractor disex = new DeliveryNonDeliveryToRecipientExtractor(msg);
		System.out.println(new Gson () .toJson(disex.extractParams())); 
	}
}
