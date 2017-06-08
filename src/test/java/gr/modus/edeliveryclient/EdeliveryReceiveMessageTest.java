package gr.modus.edeliveryclient;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;

import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.modus.edelivery.utils.SBDMessageWrapper;
import com.modus.edeliveryclient.EDeliveryClient;
import com.modus.edeliveryclient.EDeliveryClientImplementation;
import com.modus.edeliveryclient.consumer.SbdConsumer;
import com.modus.edeliveryclient.consumer.SmpParticipantConsumer;
import com.modus.edeliveryclient.jaxb.standardbusinessdocument.StandardBusinessDocument;
import com.modus.edeliveryclient.models.Authorization;
import com.modus.edeliveryclient.models.Messages;
import com.modus.edeliveryclient.serialize.Serializer;

public class EdeliveryReceiveMessageTest {

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
	public void getMessagePending() throws InterruptedException, ExecutionException {
		System.out.println("Messages Pending");
		CompletableFuture<Messages> result = deliveryClient.getMesaggesPending(auth);
		Messages msg = new Messages();
		msg = (Messages) result.get();
		System.out.println(new Gson().toJson(msg));
	}

	@Test
	public void getMessageOneByOne() throws InterruptedException, ExecutionException {
		System.out.println("Messages Pending");
		CompletableFuture<Messages> result = deliveryClient.getMesaggesPending(auth);
		Messages msg = new Messages();
		msg = (Messages) result.get();
		for(int i = 0 ; i< msg.getMessages().getMessageId().length  ; i ++){
			String s = msg.getMessages().getMessageId()[i];
			System.out.println(s);
			
		}
	}
	
	@Test
	public void getMessage() throws InterruptedException, ExecutionException, JAXBException {
		String messageId = "9933_test1-20170606144120188@local_delivery";

		System.out.println("Trying to get message");
		CompletableFuture<Object> result = deliveryClient.getMessageDefault(messageId, auth,true);
		String msg = (String)result.get();
		System.out.println(msg);
		SBDMessageWrapper wrapper= new SBDMessageWrapper(msg);
		String payLoad = wrapper.getPayload(true);
		System.out.print("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.print(payLoad);
	}
	
	@Test
	public void getPayLoadAsObject() throws InterruptedException, ExecutionException, JAXBException {
		String messageId = "9933_test1-20170606144120188@local_delivery";

		System.out.println("Trying to get message");
		CompletableFuture<Object> result = deliveryClient.getMessageDefault(messageId, auth,true);
		String msg = (String)result.get();
		System.out.println(msg);
		SBDMessageWrapper wrapper= new SBDMessageWrapper(msg);
		String payLoad = wrapper.getPayload(true);
		System.out.print("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.print(payLoad);
		
		
		
		
	}
	
	
	

}
