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
import com.google.gson.Gson;
import com.modus.edeliveryclient.consumer.SbdConsumer;
import com.modus.edeliveryclient.consumer.SmpParticipantConsumer;
import com.modus.edeliveryclient.jaxb.jaxbwrapper.AttachmentTypeHelper;
import com.modus.edeliveryclient.jaxb.marshaller.DocumentTypeMarshallerTest;
import com.modus.edeliveryclient.jaxb.marshaller.StandardBusinessDocumentHeaderMarshallerGeneratorTest;
import com.modus.edeliveryclient.jaxb.standardbusinessdocument.PapyrosDocument;
import com.modus.edeliveryclient.jaxb.standardbusinessdocument.StandardBusinessDocument;
import com.modus.edeliveryclient.jaxb.standardbusinessdocument.StandardBusinessDocumentHeader;
import com.modus.edeliveryclient.models.Authorization;
import com.modus.edeliveryclient.models.Messages;
import com.modus.edeliveryclient.models.ResponseMessage;
import com.modus.edeliveryclient.models.SBDParams;
import com.modus.edeliveryclient.serialize.Serializer;
import com.modus.edeliveryclient.serializer.JacksonSerializer;

import gr.modus.edelivery.adapter.messages.MessageParams;

public class EdeliveryGetMessageTest {
	
	private static EDeliveryClient deliveryClient;

    private static PapyrosDocument papDoc;

    private static StandardBusinessDocument sbd;
    private static StandardBusinessDocumentHeader sbdh;
    private static AttachmentTypeHelper att;

    private final Authorization auth;
    private final Authorization wrongAuth;

    //private static String messageId = "9933_test1-20170519130324418@local_delivery";
    
    public EdeliveryGetMessageTest() {
        auth = new Authorization("sp1", "sp1");
        wrongAuth = new Authorization("wrong", "wrong");
        
    }

    
    @BeforeClass
    public static void setUpClass() {
        Serializer serializer = new JacksonSerializer(new ObjectMapper());
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

    

    @After
    public void tearDown() {
    }


    @Test
    public void getMessagePending() throws InterruptedException, ExecutionException{
        System.out.println("Messages Pending");
        CompletableFuture<Messages> result = deliveryClient.getMesaggesPending(auth);
        Messages msg = new Messages();
        msg = (Messages) result.get();
        System.out.println(new Gson().toJson(msg));
    }
   
  
    
    public void shouldGetMessage() throws InterruptedException, ExecutionException, JAXBException {
    	 String messageId = "9933_test1-20170519130324418@local_delivery";
    	
    	System.out.println("Trying to get message");
        CompletableFuture<Object> result = deliveryClient.getMessageDefault(messageId, auth,true);
        System.out.println(result.get());
    }


}
