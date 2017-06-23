/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modus.edeliveryclient.jaxb.marshaller;

import com.modus.edelivery.utils.SBDMessageWrapper;
import com.modus.edeliveryclient.jaxb.egif_core_component.AttachmentType;
import com.modus.edeliveryclient.jaxb.egif_core_component.BinaryObjectType;
import com.modus.edeliveryclient.jaxb.egif_core_component.CodeType;
import com.modus.edeliveryclient.jaxb.egif_core_component.DocumentType;
import com.modus.edeliveryclient.jaxb.egif_core_component.NameType;
import com.modus.edeliveryclient.jaxb.egif_core_component.PersonType;
import com.modus.edeliveryclient.jaxb.egif_core_component.PictureType;
import com.modus.edeliveryclient.jaxb.egif_core_component.TextType;
import com.modus.edeliveryclient.jaxb.standardbusinessdocument.REMDispatch;
import com.modus.edeliveryclient.jaxb.standardbusinessdocument.SBDHFactory;
import com.modus.edeliveryclient.jaxb.standardbusinessdocument.StandardBusinessDocument;
import com.modus.edeliveryclient.jaxb.standardbusinessdocument.StandardBusinessDocumentHeader;
import eu.noble.rem.jaxb.despatch.OriginalMsgType;
import eu.noble.rem.jaxb.despatch.REMDispatchType;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigInteger;
import java.nio.file.Files;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.datatype.DatatypeConfigurationException;
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
public class StandardBusinessDocumentMarshallerTest {

//    private static MyPrefixMapper prefixMapper; 
    private static StandardBusinessDocumentHeader sbdh;
    
    private static REMDispatch remDisp;
    
    private static StandardBusinessDocument sbd;

    public StandardBusinessDocumentMarshallerTest() {
    }

    @BeforeClass
    public static void setUpClass() throws DatatypeConfigurationException, IOException {
        new StandardBusinessDocumentHeaderMarshallerGeneratorTest().setUp();
        sbdh = new StandardBusinessDocumentHeaderMarshallerGeneratorTest().returnDocHead();
       
        sbd = new StandardBusinessDocument();
        remDisp = new REMDispatch();
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
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
    public void generateHeaderXml() throws JAXBException {
        //File file = new File("/Users/modussa/NetBeansProjects/EDeliveryClient/src/test/resources/standardBusinessDocumentAllXMLtest.xsd");
    	File file = new File("/Users/modussa/NetBeansProjects/EDeliveryClient/src/test/resources/standardBusinessDocumentAllXMLtest.xsd");
        try {
//            prefixMapper = new MyPrefixMapper();
            JAXBContext jaxbContext = JAXBContext.newInstance(StandardBusinessDocument.class, SBDHFactory.class,
                    eu.noble.rem.jaxb.despatch.ObjectFactory.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            sbd.setStandardBusinessDocumentHeader(sbdh);
            REMDispatchType remType = new REMDispatchType();
            remType.setId("11111");
            OriginalMsgType omt = new OriginalMsgType();
            omt.setContentType("sadcf");
            omt.setSize(BigInteger.ONE);
            remType.setOriginalMsg(omt);
            remDisp.setRemType(remType);
            
            
            
            eu.noble.rem.jaxb.despatch.ObjectFactory rem = new eu.noble.rem.jaxb.despatch.ObjectFactory();
            JAXBElement<REMDispatchType> re1 = rem.createREMDispatch(remType);
//            remDisp.setFormat(".txt");
//            remDisp.setActualDoc("Aqsdqwedikbn`1@!~#4!!@qwdsf!@#^6b1%^$%&*BDBFG@#$78DFBHQENT^*()$ADSFC");
            sbd.setAny(re1);
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, false);
//            try{
//                jaxbMarshaller.setProperty("com.sun.xml.bind.marshaller.namespacePrefixMapper", prefixMapper);
//            }catch(Exception e){
//                e.printStackTrace();
//            }
//            String s = new String();
            jaxbMarshaller.marshal(sbd, file);
           //jaxbMarshaller.marshal(sbd, s);

            //jaxbMarshaller.marshal(sbd, System.out);

        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }
    
    
    
    
    
    
    public void getPayload() throws JAXBException, IOException {
        File file = new File("/Users/modussa/NetBeansProjects/EDeliveryClient/src/test/resources/StandardBusinessDocumentWithRemDispatchWithSaml1.xml");
        File filePayloadOut = new File("/Users/modussa/NetBeansProjects/EDeliveryClient/src/test/resources/RemDispatchWithSaml1_Out.xml");
        File filePayloadOriginal = new File("/Users/modussa/NetBeansProjects/EDeliveryClient/src/test/resources/RemDispatchWithSaml1.xml");
        //String payload = new String(Files.readAllBytes(filePayload.toPath()));
        String sbd = new String(Files.readAllBytes(file.toPath())); //load payload.

        SBDMessageWrapper sbdWrapper = new SBDMessageWrapper();
        sbdWrapper.setSBDMessageStr(sbd);
        String payload = sbdWrapper.getPayload(false);
        System.out.println("payload:"+payload);
        String payloadOriginal = new String(Files.readAllBytes(filePayloadOriginal.toPath())); //load payload.
        System.out.println("payloads Are equal :"+payloadOriginal.equals(payload));
        PrintWriter out = new PrintWriter(filePayloadOut);
        try{
        	out.print(payload);out.flush();
        }
        finally{
        	out.close();
        }

       

    }

}
