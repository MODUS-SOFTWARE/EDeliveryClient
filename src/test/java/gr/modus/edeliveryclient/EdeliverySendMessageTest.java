package gr.modus.edeliveryclient;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;

import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.junit.Before;
import org.junit.Test;
import com.modus.edeliveryclient.EDeliveryClient;
import com.modus.edeliveryclient.EDeliveryClientImplementation;
import com.modus.edeliveryclient.consumer.SbdConsumer;
import com.modus.edeliveryclient.consumer.SmpParticipantConsumer;

import com.modus.edeliveryclient.models.Authorization;
import com.modus.edeliveryclient.models.ResponseMessage;
import com.modus.edeliveryclient.models.SBDParams;
import com.modus.edeliveryclient.serialize.Serializer;

import gr.modus.edelivery.adapter.messages.MessageParams;

public class EdeliverySendMessageTest {

	AsyncHttpClient httpClient;
	Serializer serializer;
	String basepath = "http://192.168.20.10:8080/APREST";
	String user = "sp1";
	String password = "sp1";
	Authorization auth;
	EDeliveryClient deliveryClient;

	@Before
	public void setUp() throws DatatypeConfigurationException, IOException {
		this.httpClient = new DefaultAsyncHttpClient();
		this.serializer = null;
		auth = new Authorization(user, password);
		deliveryClient = new EDeliveryClientImplementation(httpClient, serializer,
				new SmpParticipantConsumer(httpClient, serializer, basepath),
				new SbdConsumer(httpClient, serializer, basepath));
	}

	@Test
	public void sendMessage() throws InterruptedException, ExecutionException, JAXBException, IOException,
			DatatypeConfigurationException {

		File filePayload = new File(
				"C:\\eclipseProj\\edelivery\\EDeliveryClient\\src\\test\\resources\\RemDispatchWithSaml1.xml");
		String payload = new String(Files.readAllBytes(filePayload.toPath())); // load
																				// payload.
		MessageParams params = new MessageParams();
		params.seteSensConfigFilename(
				"C:\\eclipseProj\\edelivery\\GenericADAdapter-master\\main\\resource\\eSensConfig.xml");
		params.setOriginatorName("panos");
		params.setOriginatorEmail("panos@modus.gr");
		params.setDestinatorName("anagnosg");
		params.setDestinatorName("anagnosg@modus.gr");
		params.setFilename("F:\\testDocument\\test_upload.pdf");
		params.setMsgId("123");
		params.setMsgIdentification("1234");
		params.setNormalizedDocSubject("Esen");
		params.setNormalizedDocComments("comments");
		params.setSamSenderId("123");
		SBDParams sbdParams = new SBDParams();
		CompletableFuture<ResponseMessage> result = deliveryClient.sendMessage(sbdParams, params, auth);

		System.out.println(result.get().getStatus());
	}
}
