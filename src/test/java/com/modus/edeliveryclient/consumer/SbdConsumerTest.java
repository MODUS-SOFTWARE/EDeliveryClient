/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modus.edeliveryclient.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.modus.edelivery.utils.SBDMessageWrapper;
import com.modus.edeliveryclient.EDeliveryClient;
import com.modus.edeliveryclient.EDeliveryClientImplementation;
import com.modus.edeliveryclient.jaxb.marshaller.REMDispatchMarshaller;
import com.modus.edeliveryclient.jaxb.marshaller.StandardBusinessDocumentHeaderGenerator;
import com.modus.edeliveryclient.jaxb.remdispatchhelpers.RemDispatchHelper;
import com.modus.edeliveryclient.jaxb.standardbusinessdocument.SBDHFactory;
import com.modus.edeliveryclient.jaxb.standardbusinessdocument.Scope;
import com.modus.edeliveryclient.jaxb.standardbusinessdocument.StandardBusinessDocument;
import com.modus.edeliveryclient.jaxb.standardbusinessdocument.StandardBusinessDocumentHeader;
import com.modus.edeliveryclient.models.Authorization;
import com.modus.edeliveryclient.models.ResponseMessage;
import com.modus.edeliveryclient.serialize.Serializer;
import com.modus.edeliveryclient.serializer.JacksonSerializer;
import com.modus.edeliveryclient.signings.XmlDsig;
import eu.noble.rem.jaxb.despatch.MsgMetaData;
import eu.noble.rem.jaxb.despatch.NormalizedMsg;
import eu.noble.rem.jaxb.despatch.OriginalMsgType;
import eu.noble.rem.jaxb.despatch.REMDispatchType;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Pantelispanka
 */
public class SbdConsumerTest {

    private static String keystorePath = "/Users/modussa/certificates/privateKey.store";
    private static String keystorePasword = "@#$M0dus";
    private static String pkEntry = "ftpkey";
    private static String keystoreInstance = "JKS";
    
    
    private static EDeliveryClient delClient;

    private static StandardBusinessDocument sbd;
    private static StandardBusinessDocumentHeader sbdh;
    private static REMDispatchType remType;
    
    private final Authorization auth;

    public SbdConsumerTest() {

        this.auth = new Authorization("sp1", "sp1");

    }

    @BeforeClass
    public static void setUpClass() throws DatatypeConfigurationException {
        Serializer serializer = new JacksonSerializer(new ObjectMapper());
        AsyncHttpClient httpClient = new DefaultAsyncHttpClient();
        String basepath = "http://192.168.20.10:8080/APREST";

        StandardBusinessDocumentHeaderGenerator sbdhg = new StandardBusinessDocumentHeaderGenerator();

        List<Scope> scopes = new ArrayList<>();
        Scope scope1 = new Scope();
        scope1.setInstanceIdentifier(" http://uri.etsi.org/02640/soapbinding/v2#::REMDispatch:2");
        scope1.setType("DOCUMENTID");
        scopes.add(scope1);
        Scope scope2 = new Scope();
        scope2.setInstanceIdentifier("http://uri.etsi.org/02640/soapbinding/v2#::REMMDMessage:2");
        scope2.setType("PROCESSID");
        scopes.add(scope2);

        SbdConsumerTest.sbdh = sbdhg.generateDocumentHeaderfromValues(0,
                "iso6523-actorid-upis", "9933:test1",
                "iso6523-actorid-upis", "9933:test1",
                "http://uri.etsi.org/02640/soapbinding/v2#", 2, "e275dd1d-f636-4c46-a4f4-489f162e334f", "REMDispatch", scopes,
                "manifestDescr", "manifestLanguage", "maniTypeQualCode", "UniformResourceIdentifier");

        XmlDsig signature = new XmlDsig(keystorePath, keystorePasword, pkEntry, keystoreInstance);
        
        delClient = new EDeliveryClientImplementation(httpClient, serializer,
                new SmpParticipantConsumer(httpClient, serializer, basepath, signature),
                new SbdConsumer(httpClient, serializer, basepath, signature), signature);
        
        
        
        
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws DatatypeConfigurationException, IOException, JAXBException {

        REMDispatchMarshaller remMarshaller = new REMDispatchMarshaller();

        remMarshaller.remMarshaller();

    }

    @After
    public void tearDown() {
    }

    @Test
    public void sendSBD() throws JAXBException, UnsupportedEncodingException, IOException, InterruptedException, ExecutionException, DatatypeConfigurationException {

        File f = new File("/Users/modussa/NetBeansProjects/EDeliveryClient/src/test/resources/remDispatchSigned.xml");

        JAXBContext jaxbContext = JAXBContext.newInstance(REMDispatchType.class, SBDHFactory.class,
                eu.noble.rem.jaxb.despatch.ObjectFactory.class,
                eu.noble.rem.jaxb.evidence.ObjectFactory.class,
                eu.noble.rem.jaxb.xmldsig.ObjectFactory.class);


        SBDMessageWrapper sbdWrap = new SBDMessageWrapper();

        File sbdFile = new File("/Users/modussa/NetBeansProjects/EDeliveryClient/src/test/resources/standardBusinessDocumentHeader.xml");

        byte[] encoded = Files.readAllBytes(Paths.get(sbdFile.getAbsolutePath()));
        String sbdString = new String(encoded, "UTF-8");
        
        byte[] encoded2 = Files.readAllBytes(Paths.get(f.getAbsolutePath()));
        String remString = new String(encoded2, "UTF-8");
        
        int index1 = remString.indexOf("<REMDispatch");
        int index2 = remString.indexOf("/REMDispatch>");
        
        String remString1 = remString.substring(index1, index2 + 13);
        
        sbdWrap.setSBDMessageStr(sbdString);
        sbdWrap.appendPayload(remString1);
        
        String payload;
        payload = sbdWrap.getSBDMessageStr();
        

        
        FileWriter fl = new FileWriter("/Users/modussa/NetBeansProjects/EDeliveryClient/src/test/resources/AllXml.xsd");
        BufferedWriter bf = new BufferedWriter(fl);
        bf.write(payload);
        bf.flush();
        bf.close();
        
        RemDispatchHelper remHelper = new RemDispatchHelper();

        NormalizedMsg normMs = remHelper.createNormalizedMessage("Comments", "Subbject");

        byte[] filetobit = Files.readAllBytes(new File("/Users/modussa/NetBeansProjects/EDeliveryClient/src/test/resources/test.txt").toPath());

        OriginalMsgType orMessag = remHelper.createOriginalMessage("ContentType", BigInteger.ONE, filetobit);

        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
        XMLGregorianCalendar now
                = datatypeFactory.newXMLGregorianCalendar(gregorianCalendar);

        MsgMetaData msmeta = remHelper.createRemDispatchMetaData(now,
                now,
                now, "Greece", "Greek", "gr", "postalCode", "stateOrProvince", "as-12-cdv-qw-2");

        REMDispatchType remType = remHelper.createRemDispatch("123-=123-123", msmeta, normMs, orMessag, null, null);
        
        

        CompletableFuture<ResponseMessage> ms = delClient.createOutgoingDefaultImpl(sbdh, remType, auth);
        
        ms.get();


    }

    
    @Test
    public void testRemMessage(){
    
        
    
    }
    
    
    
}
