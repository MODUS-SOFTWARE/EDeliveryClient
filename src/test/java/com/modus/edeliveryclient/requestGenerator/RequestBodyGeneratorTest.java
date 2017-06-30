/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modus.edeliveryclient.requestGenerator;

import com.modus.edeliveryclient.jaxb.marshaller.StandardBusinessDocumentHeaderGenerator;
import com.modus.edeliveryclient.jaxb.remdispatchhelpers.RemDispatchHelper;
import com.modus.edeliveryclient.jaxb.requestGenerator.RequestBodyGenerator;
import com.modus.edeliveryclient.jaxb.standardbusinessdocument.REMDispatch;
import com.modus.edeliveryclient.jaxb.standardbusinessdocument.Scope;
import com.modus.edeliveryclient.jaxb.standardbusinessdocument.StandardBusinessDocument;
import com.modus.edeliveryclient.jaxb.standardbusinessdocument.StandardBusinessDocumentHeader;
import eu.noble.rem.jaxb.despatch.MsgMetaData;
import eu.noble.rem.jaxb.despatch.NormalizedMsg;
import eu.noble.rem.jaxb.despatch.OriginalMsgType;
import eu.noble.rem.jaxb.despatch.REMDispatchType;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author modussa
 */
public class RequestBodyGeneratorTest {

    private static StandardBusinessDocument sbd;
    private static StandardBusinessDocumentHeader sbdh;

    private static REMDispatch remDisp;

    private static String body;
    
    
    private static REMDispatchType remType;

    public RequestBodyGeneratorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws DatatypeConfigurationException, IOException {

        StandardBusinessDocumentHeaderGenerator sbdhg = new StandardBusinessDocumentHeaderGenerator();

        List<Scope> scopes = new ArrayList<>();
        Scope scope1 = new Scope();
        scope1.setInstanceIdentifier(" http://uri.etsi.org/02640/soapbinding/v2#::REMDispatch:2");
        scope1.setType("DOCUMENTID");
        scopes.add(scope1);
        Scope scope2 = new Scope();
        scope2.setInstanceIdentifier("urn:cef-eDelivery.europa.eu::generalERDS:ver1.0");
        scope2.setType("PROCESSID");
        scopes.add(scope2);

        RequestBodyGeneratorTest.sbdh = sbdhg.generateDocumentHeaderfromValues(0,
                "iso6523-actorid-upis", "9933:test1",
                "iso6523-actorid-upis", "9933:test1",
                "http://uri.etsi.org/02640/soapbinding/v2#", 2, "e275dd1d-f636-4c46-a4f4-489f162e334f", "REMDispatch", scopes,
                "manifestDescr", "manifestLanguage", "maniTypeQualCode", "UniformResourceIdentifier");

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

        RequestBodyGeneratorTest.remType = remHelper.createRemDispatch("123-=123-123", msmeta, normMs, orMessag, null, null);

    }

    @After
    public void tearDown() {
    }

    
    @Test
    public void testBodyGenerator() {

        RequestBodyGenerator rbg = new RequestBodyGenerator();

        String body = rbg.generateRemDispatchBody(sbdh, remType);

        
        System.out.print(body);
        
        RequestBodyGeneratorTest.body = body;
        
        
    }

}
