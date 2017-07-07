/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modus.edeliveryclient.jaxb.unmarshaller;

import com.google.gson.Gson;
import com.modus.edeliveryclient.jaxb.standardbusinessdocument.REMDispatch;
import com.modus.edeliveryclient.jaxb.standardbusinessdocument.SBDHFactory;
import com.modus.edeliveryclient.jaxb.standardbusinessdocument.StandardBusinessDocument;
import eu.noble.rem.jaxb.despatch.REMDispatchType;
import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
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
public class StandardBusinessDocumentUnmarshallerTest {

    public StandardBusinessDocumentUnmarshallerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
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
    public void testUnmarshallingAll() {

        try {

            File file2 = new File("/Users/modussa/NetBeansProjects/EDeliveryClient/src/test/resources/AllXml.xsd");
            JAXBContext jaxbContext = JAXBContext.newInstance(StandardBusinessDocument.class, SBDHFactory.class,
                    eu.noble.rem.jaxb.despatch.ObjectFactory.class,
                    eu.noble.rem.jaxb.evidence.ObjectFactory.class,
                    eu.noble.rem.jaxb.xmldsig.ObjectFactory.class
            );

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            StandardBusinessDocument sbd = (StandardBusinessDocument) JAXBIntrospector.getValue(jaxbUnmarshaller.unmarshal(file2));

            System.out.println(sbd.getStandardBusinessDocumentHeader());
            
            JAXBElement<REMDispatchType> rem = (JAXBElement<REMDispatchType>) sbd.getAny();
            
            System.out.println(rem.getValue().getId());
            
            
            

        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }

}
