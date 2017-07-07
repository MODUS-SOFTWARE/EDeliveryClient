/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modus.edeliveryclient.jaxb.marshaller;

import com.modus.edeliveryclient.jaxb.remdispatchhelpers.RemDispatchHelper;
import com.modus.edeliveryclient.jaxb.standardbusinessdocument.REMDispatch;
import com.modus.edeliveryclient.jaxb.standardbusinessdocument.SBDHFactory;
import com.modus.edeliveryclient.jaxb.standardbusinessdocument.StandardBusinessDocument;
import com.modus.edeliveryclient.jaxb.standardbusinessdocument.StandardBusinessDocumentHeader;
import com.modus.edeliveryclient.signings.XmlDsig;
import eu.noble.rem.jaxb.despatch.AttachmentType;
import eu.noble.rem.jaxb.despatch.KeywordType;
import eu.noble.rem.jaxb.despatch.MsgMetaData;
import eu.noble.rem.jaxb.despatch.NormalizedMsg;
import eu.noble.rem.jaxb.despatch.NormalizedMsg.Text;
import eu.noble.rem.jaxb.despatch.OriginalMsgType;
import eu.noble.rem.jaxb.despatch.REMDispatchType;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.crypto.MarshalException;
import javax.xml.crypto.dsig.XMLSignatureException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xml.sax.SAXException;

public class REMDispatchMarshaller {

    private static String keystorePath = "/Users/modussa/certificates/privateKey.store";
    private static String keystorePasword = "@#$M0dus";
    private static String pkEntry = "ftpkey";
    private static String keystoreInstance = "JKS";
    
    private static StandardBusinessDocumentHeader sbdh;

    private static REMDispatch remDisp;

    private static StandardBusinessDocument sbd;

    public REMDispatchMarshaller() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws DatatypeConfigurationException, IOException {
        new StandardBusinessDocumentHeaderMarshallerGeneratorTest().setUp();
        sbdh = new StandardBusinessDocumentHeaderMarshallerGeneratorTest().returnDocHead();

        sbd = new StandardBusinessDocument();
        remDisp = new REMDispatch();

    }

    @After
    public void tearDown() {
    }

    @Test
    public void remMarshaller() throws DatatypeConfigurationException, IOException, JAXBException {

        RemDispatchHelper remHelper = new RemDispatchHelper();

        eu.noble.rem.jaxb.despatch.ObjectFactory of = new eu.noble.rem.jaxb.despatch.ObjectFactory();
        
        AttachmentType at = of.createAttachmentType();
        at.setContentDescription("ContentDiscription");
        at.setContentType("ContentType");
        at.setFilename("filename");
        at.setLang("Lang");
        at.setSize(BigInteger.ONE);
        at.setContentIDRef("ContentIdRef");
        
        
        Text text = of.createNormalizedMsgText();
        
        text.setFormat("Text format");
        text.setValue("Text value");
        
        KeywordType kt = of.createKeywordType();
        kt.setMeaning("KeyMeaning");
        kt.setScheme("Key Scheme");
        kt.setValue("Key Values");
        
        List<KeywordType> keys = new ArrayList<>();
        keys.add(kt);
        
        NormalizedMsg normMs = remHelper.createNormalizedMessage("Comments", "Subbject", at, text, keys);

        byte[] filetobit = Files.readAllBytes(new File("/Users/modussa/NetBeansProjects/EDeliveryClient/src/test/resources/test.txt").toPath());

        OriginalMsgType orMessag = remHelper.createOriginalMessage("ContentType", BigInteger.ONE, filetobit);

        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
        XMLGregorianCalendar now
                = datatypeFactory.newXMLGregorianCalendar(gregorianCalendar);

        MsgMetaData msmeta = remHelper.createRemDispatchMetaData(now,
                now,
                now, "Greece", "Greek", "gr", "postalCode", "stateOrProvince", "as-12-cdv-qw-2");

        REMDispatchType remDisp = remHelper.createRemDispatch("123-=123-123", msmeta, normMs, orMessag, null, null);

        File fs = new File("/Users/modussa/NetBeansProjects/EDeliveryClient/src/test/resources/remDispatch.xml");

        JAXBContext jaxbContext = JAXBContext.newInstance(REMDispatchType.class, SBDHFactory.class,
                eu.noble.rem.jaxb.despatch.ObjectFactory.class,
                eu.noble.rem.jaxb.evidence.ObjectFactory.class,
                eu.noble.rem.jaxb.xmldsig.ObjectFactory.class
        );

        JAXBElement<REMDispatchType> remdispJ = remHelper.generateRexmXml(remDisp);

        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        jaxbMarshaller.marshal(remdispJ, fs);

    }

    @Test
    public void signRemDisp() throws IOException,
             JAXBException, DatatypeConfigurationException,
             ParserConfigurationException, SAXException, NoSuchAlgorithmException,
             InvalidAlgorithmParameterException, CertificateException,
             UnrecoverableEntryException, KeyStoreException,
             MarshalException, XMLSignatureException, TransformerException {

        RemDispatchHelper remHelper = new RemDispatchHelper();

        eu.noble.rem.jaxb.despatch.ObjectFactory of = new eu.noble.rem.jaxb.despatch.ObjectFactory();
        
        AttachmentType at = of.createAttachmentType();
        at.setContentDescription("ContentDiscription");
        at.setContentType("ContentType");
        at.setFilename("filename");
        at.setLang("Lang");
        at.setSize(BigInteger.ONE);
        at.setContentIDRef("ContentIdRef");
        
        Text text = of.createNormalizedMsgText();
        
        text.setFormat("Text format");
        text.setValue("Text value");
        
        KeywordType kt = of.createKeywordType();
        kt.setMeaning("KeyMeaning");
        kt.setScheme("Key Scheme");
        kt.setValue("Key Values");
        
        List<KeywordType> keys = new ArrayList<>();
        keys.add(kt);
        
        NormalizedMsg normMs = remHelper.createNormalizedMessage("Comments", "Subbject", at, text, keys);

        byte[] filetobit = Files.readAllBytes(new File("/Users/modussa/NetBeansProjects/EDeliveryClient/src/test/resources/test.txt").toPath());

        OriginalMsgType orMessag = remHelper.createOriginalMessage("ContentType", BigInteger.ONE, filetobit);

        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
        XMLGregorianCalendar now
                = datatypeFactory.newXMLGregorianCalendar(gregorianCalendar);

        MsgMetaData msmeta = remHelper.createRemDispatchMetaData(now,
                now,
                now, "Greece", "Greek", "gr", "postalCode", "stateOrProvince", "as-12-cdv-qw-2");

        REMDispatchType remDisp = remHelper.createRemDispatch("123-=123-123", msmeta, normMs, orMessag, null, null);

        File fs = new File("/Users/modussa/NetBeansProjects/EDeliveryClient/src/test/resources/remDispatchSigned.xml");

        JAXBContext jaxbContext = JAXBContext.newInstance(REMDispatchType.class, SBDHFactory.class,
                eu.noble.rem.jaxb.despatch.ObjectFactory.class,
                eu.noble.rem.jaxb.evidence.ObjectFactory.class,
                eu.noble.rem.jaxb.xmldsig.ObjectFactory.class
        );

        JAXBElement<REMDispatchType> remdispJ = remHelper.generateRexmXml(remDisp);

        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        jaxbMarshaller.marshal(remdispJ, fs);

        XmlDsig sign = new XmlDsig(keystorePath, keystorePasword, pkEntry, keystoreInstance);

        sign.signatureBuilder(fs);

    }

}
