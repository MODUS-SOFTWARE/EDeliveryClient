/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modus.edeliveryclient.jaxb.marshaller;

import com.modus.edeliveryclient.jaxb.jaxbwrapper.StandardBusinessDocumentWrapper;
import com.modus.edeliveryclient.jaxb.standardbusinessdocument.BusinessScope;
import com.modus.edeliveryclient.jaxb.standardbusinessdocument.SBDHFactory;
import com.modus.edeliveryclient.jaxb.standardbusinessdocument.Scope;
import com.modus.edeliveryclient.jaxb.standardbusinessdocument.StandardBusinessDocument;
import com.modus.edeliveryclient.jaxb.standardbusinessdocument.StandardBusinessDocumentHeader;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static javafx.scene.input.KeyCode.T;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
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
public class StandardBusinessDocumentHeaderMarshallerGeneratorTest {

    private static StandardBusinessDocumentHeader businDocHeader;

    private static StandardBusinessDocument sbd;

    private static int headerVersion = 1;

    private final static String participantIdentifierSenderScheme = "iso6523-actorid-upis";
    private final static String participantIdentifierSenderValue = "9933:test1";
    private final static String participantIdentifierReceiverScheme = "iso6523-actorid-upis";
    private final static String participantIdentifierReceiverValue = "9933:test1";

    private final static String documentIdStandard = "urn:oasis:names:specification:ubl:schema:xsd:Invoice-2";
    private final static int docTypeVersion = 1;
    private final static String documentInstanceIdentifier = "627a2e9a-a146-461f-b62f-f22f5799fd55";
    private final static String documentType = "Invoice";

    private final static String scopeType = "DOCUMENTID";
    private final static String scopeIdentifier = "urn:oasis:names:specification:ubl:schema:xsd:Invoice-2:: Invoice##urn:www.cenbii.eu:transaction:biitrns010:ver2.0:extended:urn: www.peppol.eu:bis:peppol4a:ver2.0::2.1";
    private final static String scopeType2 = "PROCESSID";
    private final static String scopeIdentifier2 = "urn:www.cenbii.eu:profile:bii04:ver2.0";

    private final static String manifestDescr = "manifestDescr";
    private final static String manifestLanguage = " manifestLanguage";
    private final static String maniTypeQualCode = "maniTypeQualCode";
    private final static String uniformResourceIdentifier = "uniformResourceIdentifier";

    private final static List<Scope> businessScopes = new ArrayList<Scope>() {
        {
//            add(new Scope());
//            add(new Scope());
        }
    };

    public StandardBusinessDocumentHeaderMarshallerGeneratorTest() {

    }

    @BeforeClass
    public static void setUpClass() throws DatatypeConfigurationException {
        Scope scope1 = new Scope();
        scope1.setIdentifier(scopeIdentifier);
        scope1.setInstanceIdentifier("Instance");
        scope1.setType(scopeType);
//        scope
        BusinessScope bScope1 = new BusinessScope();
        bScope1.getScope().add(scope1);
        Scope scope2 = new Scope();
        scope2.setIdentifier(scopeIdentifier);
        scope2.setInstanceIdentifier("Instance");
        scope2.setType(scopeType2);
//        scope
        BusinessScope bScope2 = new BusinessScope();
        bScope2.getScope().add(scope2);
        businessScopes.add(scope2);
        businessScopes.add(scope1);
        businDocHeader = new StandardBusinessDocumentHeaderGenerator()
                .generateDocumentHeaderfromValues(headerVersion, participantIdentifierSenderScheme, participantIdentifierSenderValue,
                        participantIdentifierReceiverScheme, participantIdentifierReceiverValue,
                        documentIdStandard, docTypeVersion, documentInstanceIdentifier, documentType, businessScopes,
                        manifestDescr, manifestLanguage, maniTypeQualCode, uniformResourceIdentifier);
        sbd = new SBDHFactory().createStandardBusinessDocument();
        sbd.setStandardBusinessDocumentHeader(businDocHeader);

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws DatatypeConfigurationException {
        Scope scope1 = new Scope();
        scope1.setIdentifier(scopeIdentifier);
        scope1.setInstanceIdentifier("Instance");
        scope1.setType(scopeType);
        BusinessScope bScope1 = new BusinessScope();
        bScope1.getScope().add(scope1);
        Scope scope2 = new Scope();
        scope2.setIdentifier(scopeIdentifier);
        scope2.setInstanceIdentifier("Instance");
        scope2.setType(scopeType2);
        BusinessScope bScope2 = new BusinessScope();
        bScope2.getScope().add(scope2);
        businessScopes.add(scope2);
        businessScopes.add(scope1);
        businDocHeader = new StandardBusinessDocumentHeaderGenerator()
                .generateDocumentHeaderfromValues(headerVersion, participantIdentifierSenderScheme, participantIdentifierSenderValue,
                        participantIdentifierReceiverScheme, participantIdentifierReceiverValue,
                        documentIdStandard, docTypeVersion, documentInstanceIdentifier, documentType, businessScopes,
                        manifestDescr, manifestLanguage, maniTypeQualCode, uniformResourceIdentifier);
    }

    @After
    public void tearDown() {
    }

    public StandardBusinessDocumentHeader returnDocHead() {
        return businDocHeader;
    }

    /**
     * The file to be written
     */
//    /Users/modussa/Desktop/Marshalling.txt
    @Test
    public void generateHeaderXml() {
    	//C:\eclipseProj\edelivery\EDeliveryClient\src\test\resources\standardBusinessDocumentAllXMLtest.xsd
        File file = new File("C:\\eclipseProj\\edelivery\\EDeliveryClient\\src\\test\\resources\\standardBusinessDocumentAllXMLtest.xsd");

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(StandardBusinessDocument.class, SBDHFactory.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(sbd, file);
            
            jaxbMarshaller.marshal(sbd, System.out);

        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }

}
