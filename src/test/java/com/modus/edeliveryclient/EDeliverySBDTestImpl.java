/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modus.edeliveryclient;

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
import com.modus.edeliveryclient.models.MessageId;
import com.modus.edeliveryclient.models.ResponseMessage;
import com.modus.edeliveryclient.serialize.Serializer;


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
import static org.junit.Assert.*;

/**
 *
 * @author Pantelispanka
 */
public class EDeliverySBDTestImpl {

    private static EDeliveryClient deliveryClient;

    private static PapyrosDocument papDoc;

    private static StandardBusinessDocument sbd;
    private static StandardBusinessDocumentHeader sbdh;
    private static AttachmentTypeHelper att;

    private final Authorization auth;
    private final Authorization wrongAuth;

    private static String messageId = "9933_test1-20170519130324418@local_delivery";
    
    public EDeliverySBDTestImpl() {
        auth = new Authorization("sp1", "sp1");
        wrongAuth = new Authorization("wrong", "wrong");
    }

    @BeforeClass
    public static void setUpClass() {
        Serializer serializer = null;
        AsyncHttpClient httpClient = new DefaultAsyncHttpClient();
        String basepath = "http://192.168.20.10:8080/APREST";
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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

   @Test
    public void shouldPostOutgoinMessage() throws InterruptedException, ExecutionException, JAXBException, IOException {

    	File filePayload = new File("C:\\eclipseProj\\edelivery\\EDeliveryClient\\src\\test\\resources\\RemDispatchWithSaml1.xml");
    	 String payload = new String(Files.readAllBytes(filePayload.toPath())); //load payload.
        CompletableFuture<ResponseMessage> result = deliveryClient
                .createOutgoingDefaultImpl(sbdh, payload, auth);
        System.out.println(result.get().getStatus());
    }

   
  
    
    public void shouldGetMessage() throws InterruptedException, ExecutionException, JAXBException {
        System.out.println("Trying to get message");
        CompletableFuture<Object> result = deliveryClient.getMessageDefault(messageId, auth,false);

        StandardBusinessDocument sbd = new StandardBusinessDocument();
        try{
            sbd = (StandardBusinessDocument) result.get();
        }catch(Exception e){
            e.printStackTrace();
        }
        
        System.out.println(result.get().getClass().toString());
    }

   
    public void shouldGetMessagePending() throws InterruptedException, ExecutionException{
        System.out.println("Messages Pending");
        CompletableFuture<Messages> result = deliveryClient.getMesaggesPending(auth);
        Messages msg = new Messages();
        msg = (Messages) result.get();
        System.out.println(new Gson().toJson(msg));
    }
    
    
    
}
