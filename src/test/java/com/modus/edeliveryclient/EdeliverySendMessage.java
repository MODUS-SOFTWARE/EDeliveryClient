package com.modus.edeliveryclient;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;

import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.modus.edeliveryclient.consumer.SbdConsumer;
import com.modus.edeliveryclient.consumer.SmpParticipantConsumer;
import com.modus.edeliveryclient.jaxb.jaxbwrapper.AttachmentTypeHelper;
import com.modus.edeliveryclient.jaxb.marshaller.DocumentTypeMarshallerTest;
import com.modus.edeliveryclient.jaxb.marshaller.StandardBusinessDocumentHeaderMarshallerGeneratorTest;
import com.modus.edeliveryclient.jaxb.standardbusinessdocument.PapyrosDocument;
import com.modus.edeliveryclient.jaxb.standardbusinessdocument.StandardBusinessDocument;
import com.modus.edeliveryclient.jaxb.standardbusinessdocument.StandardBusinessDocumentHeader;
import com.modus.edeliveryclient.models.Authorization;
import com.modus.edeliveryclient.models.ResponseMessage;
import com.modus.edeliveryclient.models.SBDParams;
import com.modus.edeliveryclient.serialize.Serializer;
import com.modus.edeliveryclient.serializer.JacksonSerializer;

import gr.modus.edelivery.adapter.messages.MessageParams;

public class EdeliverySendMessage {
	private static EDeliveryClient deliveryClient;

    private static PapyrosDocument papDoc;

    private static StandardBusinessDocument sbd;
    private static StandardBusinessDocumentHeader sbdh;
    private static AttachmentTypeHelper att;

    private final Authorization auth;
    private final Authorization wrongAuth;

    private static String messageId = "9933_test1-20170519130324418@local_delivery";
    
    public EdeliverySendMessage() {
        auth = new Authorization("sp1", "sp1");
        wrongAuth = new Authorization("wrong", "wrong");
        
    }

    @BeforeClass
    public static void setUpClass() {
        Serializer serializer = null;
        AsyncHttpClient httpClient = new DefaultAsyncHttpClient();
        String basepath = "http:192.168.20.10:8080/APREST";
        deliveryClient = new EDeliveryClientImplementation(httpClient,
                serializer,
                new SmpParticipantConsumer(httpClient, serializer, basepath),
                new SbdConsumer(httpClient, serializer, basepath));
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws DatatypeConfigurationException, IOException {
        new StandardBusinessDocumentHeaderMarshallerGeneratorTest().setUp();
        sbdh = new StandardBusinessDocumentHeaderMarshallerGeneratorTest().returnDocHead();
        new DocumentTypeMarshallerTest().setUp();
        att = new DocumentTypeMarshallerTest().returnAttach();
        sbd = new StandardBusinessDocument();
        papDoc = new PapyrosDocument();
    }

    @After
    public void tearDown() {
    }

     //TODO add test methods here.
     //The methods must be annotated with annotation @Test. For example:
    @Test
    public void sendMessage() throws InterruptedException, ExecutionException, JAXBException, IOException, DatatypeConfigurationException {

    	File filePayload = new File("C:\\eclipseProj\\edelivery\\EDeliveryClient\\src\\test\\resources\\RemDispatchWithSaml1.xml");
    	String payload = new String(Files.readAllBytes(filePayload.toPath())); //load payload.
    	MessageParams params = new MessageParams(); 
    	params.seteSensConfigFilename("C:\\eclipseProj\\edelivery\\GenericADAdapter-master\\main\\resource\\eSensConfig.xml");
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

   
  
    /*
    public void shouldGetMessage() throws InterruptedException, ExecutionException, JAXBException {
        System.out.println("Trying to get message");
        CompletableFuture<Object> result = deliveryClient.getMessageDefault(messageId, auth);
        System.out.println(result.get());
    }*/

}
