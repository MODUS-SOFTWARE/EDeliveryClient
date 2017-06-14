///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.modus.edeliveryclient.jaxb.marshaller;
//
//
//
//import com.modus.edeliveryclient.jaxb.jaxbwrapper.AttachmentTypeHelper;
//import com.modus.edeliveryclient.jaxb.org.etsi.uri._02640.v2_.NamesPostalAddressesType;
//import com.modus.edeliveryclient.jaxb.standardbusinessdocument.REMDispatch;
//import com.modus.edeliveryclient.jaxb.standardbusinessdocument.SBDHFactory;
//import com.modus.edeliveryclient.jaxb.standardbusinessdocument.StandardBusinessDocument;
//import com.modus.edeliveryclient.jaxb.standardbusinessdocument.StandardBusinessDocumentHeader;
//import com.modus.edeliveryclient.signings.Saml;
//import com.modus.edeliveryclient.signings.XmlDsig;
//import java.io.File;
//import java.io.IOException;
//import javax.xml.bind.JAXBContext;
//import javax.xml.bind.JAXBException;
//import javax.xml.bind.Marshaller;
//import javax.xml.datatype.DatatypeConfigurationException;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import gr.modus.edelivery.adapter.messages.MessageParams;
//import java.util.GregorianCalendar;
//import javax.xml.crypto.dsig.dom.DOMSignContext;
//import javax.xml.datatype.DatatypeFactory;
//import javax.xml.datatype.XMLGregorianCalendar;
//import oasis.names.tc.saml._2_0.assertion.AssertionType;
//import oasis.names.tc.saml._2_0.assertion.ConditionsType;
//import oasis.names.tc.saml._2_0.assertion.NameIDType;
//import org.etsi.uri._01903.v1_3.CertIDType;
//import org.etsi.uri._02640.soapbinding.v1_.DeliveryConstraints;
//import org.etsi.uri._02640.soapbinding.v1_.Destinations;
////import oasis.names.tc.saml._2_0.assertion.NameIDType;
//import org.etsi.uri._02640.soapbinding.v1_.MsgMetaData;
//import org.etsi.uri._02640.soapbinding.v1_.Originators;
//
//import org.etsi.uri._02640.soapbinding.v1_.REMDispatchType;
//import org.etsi.uri._02640.v2_.CertIDAndSignatureType;
//import org.etsi.uri._02640.v2_.CertificateDetailsType;
//import org.etsi.uri._02640.v2_.EntityDetailsType;
//import org.etsi.uri._02640.v2_.EntityNameType;
//import org.etsi.uri._02640.v2_.MessageDetailsType;
//import org.etsi.uri._02640.v2_.NamePostalAddressType;
//import org.etsi.uri._02640.v2_.NamesPostalAddressListType;
//import org.etsi.uri._02640.v2_.PostalAddressType;
////import org.opensaml.saml2.core.NameIDType;
//import org.w3._2000._09.xmldsig_.DigestMethodType;
//import org.w3._2000._09.xmldsig_.KeyInfoType;
//import org.w3._2000._09.xmldsig_.SignatureValueType;
//
//public class REMDispatchMarshaller {
//
//    private static StandardBusinessDocumentHeader sbdh;
//    private static AttachmentTypeHelper att;
//
//    private static REMDispatch remDisp;
//
//    private static StandardBusinessDocument sbd;
//
//    public REMDispatchMarshaller() {
//    }
//
//    @BeforeClass
//    public static void setUpClass() {
//    }
//
//    @AfterClass
//    public static void tearDownClass() {
//    }
//
//    @Before
//    public void setUp() throws DatatypeConfigurationException, IOException {
//        new StandardBusinessDocumentHeaderMarshallerGeneratorTest().setUp();
//        sbdh = new StandardBusinessDocumentHeaderMarshallerGeneratorTest().returnDocHead();
//        new DocumentTypeMarshallerTest().setUp();
//        att = new DocumentTypeMarshallerTest().returnAttach();
//        sbd = new StandardBusinessDocument();
//        remDisp = new REMDispatch();
//
//    }
//
//    @After
//    public void tearDown() {
//    }
//
//    @Test
//    public void remDispatch() throws DatatypeConfigurationException, JAXBException {
//
//        File file = new File("/Users/modussa/NetBeansProjects/EDeliveryClient/src/test/resources/RemDispatchPart2.xsd");
//
//        org.etsi.uri._02640.soapbinding.v1_.ObjectFactory remFactory = new org.etsi.uri._02640.soapbinding.v1_.ObjectFactory();
//        org.etsi.uri._02640.v2_.ObjectFactory detailsFactory = new org.etsi.uri._02640.v2_.ObjectFactory();
//
//        
//        
//        REMDispatchType remDispatch = remFactory.createREMDispatchType();
//        remDispatch.setId("1234a-asdf-qwjn1");
//
//        MsgMetaData msgMeta = remFactory.createMsgMetaData();
//
//        DeliveryConstraints delCon = remFactory.createDeliveryConstraints();
//
//        GregorianCalendar gregorianCalendar = new GregorianCalendar();
//        DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
//        XMLGregorianCalendar now
//                = datatypeFactory.newXMLGregorianCalendar(gregorianCalendar);
//
//        delCon.setInitialSend(now);
//        delCon.setObsoleteAfter(now);
//        delCon.setOrigin(now);
//        msgMeta.setDeliveryConstraints(delCon);
//
//        Destinations dest = remFactory.createDestinations();
//        EntityDetailsType edt = detailsFactory.createEntityDetailsType();
//
//        NamesPostalAddressListType cdt = detailsFactory.createNamesPostalAddressListType();
//        PostalAddressType pad = detailsFactory.createPostalAddressType();
//        NamePostalAddressType npat = detailsFactory.createNamePostalAddressType();
//        pad.setCountryName("GR");
//        pad.setLang("GR");
//        pad.setLocality("Locality");
//        pad.setPostalCode("Postal code");
//        pad.setStateOrProvince("StateOrProvince");
//        npat.setPostalAddress(pad);
//        cdt.getNamePostalAddress().add(npat);
//
//        edt.setNamesPostalAddresses(cdt);
//        CertIDAndSignatureType sIdT = detailsFactory.createCertIDAndSignatureType();
//
//        dest.setRecipient(edt);
//
//        msgMeta.setDestinations(dest);
//
//        remDispatch.setMsgMetaData(msgMeta);
//
//        try {
//            JAXBContext jaxbContext = JAXBContext.newInstance(StandardBusinessDocument.class, SBDHFactory.class);
//            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
//            sbd.setStandardBusinessDocumentHeader(sbdh);
//
//            sbd.setAny(remFactory.createREMDispatch(remDispatch));
//            jaxbMarshaller.marshal(sbd, file);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//
////    @Test
////    public void testRemDispartchPart1() throws JAXBException {
////        File file = new File("/Users/modussa/NetBeansProjects/EDeliveryClient/src/test/resources/RemDispatchPart1.xsd");
////
////        try {
////            JAXBContext jaxbContext = JAXBContext.newInstance(StandardBusinessDocument.class, SBDHFactory.class);
////            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
////            sbd.setStandardBusinessDocumentHeader(sbdh);
////
////            REMDispatchType ty = new REMDispatchType();
////
////            MsgMetaData meta = new MsgMetaData();
////
////            org.etsi.uri._02640.soapbinding.v1_.ObjectFactory OF4 = new org.etsi.uri._02640.soapbinding.v1_.ObjectFactory();
////
////            org.etsi.uri._02640.v2_.REMEvidenceType et = new org.etsi.uri._02640.v2_.REMEvidenceType();
////
////            SignatureValueType svt = new SignatureValueType();
////            KeyInfoType kinf = new KeyInfoType();
////
////            org.w3._2000._09.xmldsig_.ObjectFactory objf = new org.w3._2000._09.xmldsig_.ObjectFactory();
//////            objf.
////
////            String eventCode = "EventCode";
////            et.setEventCode(eventCode);
////
////            et.setEvidenceIdentifier("EvidenceIdentifier");
////            et.setReplyTo("reply to");
////
////            MessageDetailsType mdt1 = new MessageDetailsType();
////
////            DigestMethodType dmt = new DigestMethodType();
////            dmt.setAlgorithm("Algorithm");
////            mdt1.setDigestMethod(dmt);
////            mdt1.setMessageSubject("Message Subject");
////            mdt1.setIsNotification(true);
////            mdt1.setUAMessageIdentifier("USMessageIdentifier");
////            mdt1.setMessageIdentifierByREMMD("MessageIdentifByREMMD");
////            et.setNotificationMessageDetails(mdt1);
////
////            MsgMetaData messageMeta = new MsgMetaData();
////            Originators origin = new Originators();
////
////            EntityDetailsType edt1 = new EntityDetailsType();
////            NamesPostalAddressesType npat = new NamesPostalAddressesType();
////            EntityNameType ent = new EntityNameType();
////            ent.setLang("Gre");
////
////            MessageParams mPar = new MessageParams();
////
////            org.etsi.uri._02640.v2_.ObjectFactory OF = new org.etsi.uri._02640.v2_.ObjectFactory();
////            org.etsi.uri._02640.soapbinding.v1_.ObjectFactory OF2 = new org.etsi.uri._02640.soapbinding.v1_.ObjectFactory();
////
////            AssertionType assertion = new AssertionType();
////
//////            assertion.set
//////            Saml saml = new Saml();
////
////            oasis.names.tc.saml._2_0.assertion.ObjectFactory OF3 = new oasis.names.tc.saml._2_0.assertion.ObjectFactory();
////
////            NameIDType nameID = OF3.createNameIDType();
////            nameID.setFormat("Format");
////            nameID.setNameQualifier("NameQualifier");
////            nameID.setSPNameQualifier("SPNameQual");
////            nameID.setSPProvidedID("SPProvider");
////            nameID.setValue("value");
////
////            ConditionsType conditions = OF3.createConditionsType();
////
////            GregorianCalendar gregorianCalendar = new GregorianCalendar();
////            DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
////            XMLGregorianCalendar now
////                    = datatypeFactory.newXMLGregorianCalendar(gregorianCalendar);
////
////            conditions.setNotBefore(now);
////            conditions.setNotOnOrAfter(now);
////
//////            assertion = saml.createAssertion(nameID, conditions);
////            assertion.setConditions(conditions);
////            assertion.setIssuer(nameID);
////
////            remDisp.setMessageMeta(messageMeta);
////
////            remDisp.setRemEvidence(et);
////            remDisp.setAssertion(assertion);
////            sbd.setAny(remDisp);
////
////            jaxbMarshaller.marshal(sbd, file);
////            XmlDsig sign = new XmlDsig();
//////            FileOutputStream xmlToSign = new FileOutputStream(remDisp.toString());
//////            FileInputStream xmlToSign = new FileInputStream(remDisp.toString();
////            DOMSignContext signed = sign.signatureBuilder(file);
////            System.out.println(signed.getParent().toString());
////
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
//////            AssertionType assertionType = new AssertionType();
//////            Assertion assertion = new Assertion() {
//////                @Override
//////                public Element sign(PublicKey pk, PrivateKey pk1) throws SAMLException {
//////                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//////                }
//////
//////                @Override
//////                public Element sign(X509Certificate xc, PrivateKey pk) throws SAMLException {
//////                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//////                }
//////
//////                @Override
//////                public Element sign(X509Certificate xc, PrivateKey pk, boolean bln) throws SAMLException {
//////                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//////                }
//////
//////                @Override
//////                public Element sign(DigestMethod dm, String string, PublicKey pk, PrivateKey pk1) throws SAMLException {
//////                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//////                }
//////
//////                @Override
//////                public Element sign(DigestMethod dm, String string, X509Certificate xc, PrivateKey pk) throws SAMLException {
//////                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//////                }
//////
//////                @Override
//////                public Element sign(DigestMethod dm, String string, X509Certificate xc, PrivateKey pk, boolean bln) throws SAMLException {
//////                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//////                }
//////
//////                @Override
//////                public void setMajorVersion(BigInteger bi) {
//////                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//////                }
//////
//////                @Override
//////                public void setMinorVersion(BigInteger bi) {
//////                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//////                }
//////
//////                @Override
//////                public void setVersion(String string) {
//////                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//////                }
//////
//////                @Override
//////                public Element toElement(Node node) throws XWSSecurityException {
//////                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//////                }
//////
//////                @Override
//////                public String getSamlIssuer() {
//////                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//////                }
//////
//////                @Override
//////                public String getAssertionID() {
//////                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//////                }
//////
//////                @Override
//////                public String getID() {
//////                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//////                }
//////
//////                @Override
//////                public String getVersion() {
//////                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//////                }
//////
//////                @Override
//////                public BigInteger getMajorVersion() {
//////                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//////                }
//////
//////                @Override
//////                public BigInteger getMinorVersion() {
//////                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//////                }
//////
//////                @Override
//////                public String getType() {
//////                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//////                }
//////
//////                @Override
//////                public Object getTokenValue() {
//////                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//////                }
//////            };
////
//////            
//////            assertionType.setSignature(signType);
//////            remDisp.setAssertion(assertionType);
//////            
//////            AdditionalDetailsType adty = vfac1.createAdditionalDetailsType();
//////            adty.setAssertion(assertionType);
////    }
//}
