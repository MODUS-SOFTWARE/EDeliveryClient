/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modus.edeliveryclient.jaxb.marshaller;


import com.modus.edeliveryclient.jaxb.jaxbwrapper.AttachmentTypeHelper;
import com.modus.edeliveryclient.jaxb.org.etsi.uri._02640.v2_.NamesPostalAddressesType;
import com.modus.edeliveryclient.jaxb.standardbusinessdocument.REMDispatch;
import com.modus.edeliveryclient.jaxb.standardbusinessdocument.SBDHFactory;
import com.modus.edeliveryclient.jaxb.standardbusinessdocument.StandardBusinessDocument;
import com.modus.edeliveryclient.jaxb.standardbusinessdocument.StandardBusinessDocumentHeader;
import com.modus.edeliveryclient.signings.XmlDsig;
import java.io.File;
import java.io.IOException;
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

import com.sun.xml.wss.saml.SAMLAssertionFactory;
import com.sun.xml.wss.saml.SAMLException;
import gr.modus.edelivery.adapter.messages.MessageParams;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.X509Certificate;
import java.util.List;
import javax.xml.crypto.Data;
import javax.xml.crypto.MarshalException;
import javax.xml.crypto.URIDereferencer;
import javax.xml.crypto.XMLStructure;
import javax.xml.crypto.dsig.CanonicalizationMethod;
import javax.xml.crypto.dsig.DigestMethod;
import javax.xml.crypto.dsig.Manifest;
import javax.xml.crypto.dsig.Reference;
import javax.xml.crypto.dsig.SignatureMethod;
import javax.xml.crypto.dsig.SignatureProperties;
import javax.xml.crypto.dsig.SignatureProperty;
import javax.xml.crypto.dsig.SignedInfo;
import javax.xml.crypto.dsig.Transform;
import javax.xml.crypto.dsig.XMLObject;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.XMLValidateContext;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.crypto.dsig.spec.DigestMethodParameterSpec;
import javax.xml.crypto.dsig.spec.SignatureMethodParameterSpec;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import javax.xml.namespace.QName;
import oasis.names.tc.saml._2_0.assertion.AdviceType;
import oasis.names.tc.saml._2_0.assertion.AssertionType;
import org.etsi.uri._02231.v2_.AnyType;
import org.etsi.uri._02640.soapbinding.v1_.DeliveryConstraints;
import org.etsi.uri._02640.soapbinding.v1_.MsgMetaData;
import org.etsi.uri._02640.soapbinding.v1_.Originators;

import org.etsi.uri._02640.soapbinding.v1_.REMDispatchType;
import org.etsi.uri._02640.soapbinding.v1_.REMMDEvidenceListType;
import org.etsi.uri._02640.v2_.CertificateDetailsType;
import org.etsi.uri._02640.v2_.EntityDetailsType;
import org.etsi.uri._02640.v2_.EntityNameType;
import org.etsi.uri._02640.v2_.EventReasonsType;
import org.etsi.uri._02640.v2_.MessageDetailsType;
import org.etsi.uri._02640.v2_.REMEvidenceType;
import static org.joda.time.DateMidnight.now;
import org.joda.time.DateTime;
import org.opensaml.common.SAMLVersion;
import org.opensaml.saml1.core.impl.AssertionBuilder;
import org.opensaml.saml2.core.Advice;
import org.opensaml.saml2.core.Assertion;
import org.opensaml.saml2.core.AttributeStatement;
import org.opensaml.saml2.core.AuthnStatement;
import org.opensaml.saml2.core.AuthzDecisionStatement;
import org.opensaml.saml2.core.Conditions;
import org.opensaml.saml2.core.Issuer;
import org.opensaml.saml2.core.Statement;
import org.opensaml.saml2.core.Subject;
import org.opensaml.saml2.core.impl.AssertionImpl;
import org.w3._2000._09.xmldsig_.CanonicalizationMethodType;
import org.w3._2000._09.xmldsig_.DSAKeyValueType;
import org.w3._2000._09.xmldsig_.DigestMethodType;
import org.w3._2000._09.xmldsig_.KeyInfoType;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3._2000._09.xmldsig_.ObjectFactory;
import org.w3._2000._09.xmldsig_.SignatureMethodType;
import org.w3._2000._09.xmldsig_.SignatureType;
import org.w3._2000._09.xmldsig_.SignatureValueType;
import org.w3._2000._09.xmldsig_.SignedInfoType;
import org.w3._2000._09.xmldsig_.X509DataType;

public class REMDispatchMarshaller {

    private static StandardBusinessDocumentHeader sbdh;
    private static AttachmentTypeHelper att;

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
        new DocumentTypeMarshallerTest().setUp();
        att = new DocumentTypeMarshallerTest().returnAttach();
        sbd = new StandardBusinessDocument();
        remDisp = new REMDispatch();

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
    public void testRemDispartchPart1() throws JAXBException {
        File file = new File("/Users/modussa/NetBeansProjects/EDeliveryClient/src/test/resources/RemDispatchPart1.xsd");

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(StandardBusinessDocument.class, SBDHFactory.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            sbd.setStandardBusinessDocumentHeader(sbdh);

            REMDispatchType ty = new REMDispatchType();

            org.etsi.uri._02640.v2_.REMEvidenceType et = new org.etsi.uri._02640.v2_.REMEvidenceType();

            
           
            

            SignatureValueType svt = new SignatureValueType();
            KeyInfoType kinf = new KeyInfoType();

            org.w3._2000._09.xmldsig_.ObjectFactory objf = new org.w3._2000._09.xmldsig_.ObjectFactory();
//            objf.

            

            String eventCode = "EventCode";
            et.setEventCode(eventCode);

            et.setEvidenceIdentifier("EvidenceIdentifier");
            et.setReplyTo("reply to");

            MessageDetailsType mdt1 = new MessageDetailsType();

            DigestMethodType dmt = new DigestMethodType();
            dmt.setAlgorithm("Algorithm");
            mdt1.setDigestMethod(dmt);
            mdt1.setMessageSubject("Message Subject");
            mdt1.setIsNotification(true);
            mdt1.setUAMessageIdentifier("USMessageIdentifier");
            mdt1.setMessageIdentifierByREMMD("MessageIdentifByREMMD");
            et.setNotificationMessageDetails(mdt1);

            

            
            
            
            MsgMetaData messageMeta = new MsgMetaData();
            Originators origin = new Originators();
            
            
            EntityDetailsType edt1 = new EntityDetailsType();
            NamesPostalAddressesType npat = new NamesPostalAddressesType();
            EntityNameType ent = new EntityNameType();
            ent.setLang("Gre");

            MessageParams mPar = new MessageParams();

            

 
            
            org.etsi.uri._02640.v2_.ObjectFactory OF = new org.etsi.uri._02640.v2_.ObjectFactory();
            org.etsi.uri._02640.soapbinding.v1_.ObjectFactory OF2 = new org.etsi.uri._02640.soapbinding.v1_.ObjectFactory();
            
            
            
            
           
            
            
//            mmd.setDeliveryConstraints();
//            
//            Assertion assertion = null; 
//            Advice adv = null;
//            Conditions cond = null;
//            DateTime now = new DateTime();
//            now.dayOfMonth();
//            now.dayOfWeek();
//            now.dayOfYear();
//            
//            cond.setNotBefore(now.toDateTime());
//            
////            assertion.setConditions();
//            assertion.setAdvice(assertion);
            
            
            remDisp.setMessageMeta(messageMeta);
            
//            remDisp.setSignValue(signVal);
//            remDisp.setDsakeyval(dsaKey);
//            remDisp.setDigestMethType(digMeTy);
//            remDisp.setKeyInfo(kinf);
//            remDisp.setSignInfo(signInfo);
//            remDisp.setMdt(mdt);
            remDisp.setRemEvidence(et);
            sbd.setAny(remDisp);
            
            
            
            jaxbMarshaller.marshal(sbd, file);
            XmlDsig sign = new XmlDsig();
//            FileOutputStream xmlToSign = new FileOutputStream(remDisp.toString());
//            FileInputStream xmlToSign = new FileInputStream(remDisp.toString();
            DOMSignContext signed = sign.signatureBuilder(file);
            System.out.println(signed.getParent().toString());
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
//            AssertionType assertionType = new AssertionType();
//            Assertion assertion = new Assertion() {
//                @Override
//                public Element sign(PublicKey pk, PrivateKey pk1) throws SAMLException {
//                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                }
//
//                @Override
//                public Element sign(X509Certificate xc, PrivateKey pk) throws SAMLException {
//                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                }
//
//                @Override
//                public Element sign(X509Certificate xc, PrivateKey pk, boolean bln) throws SAMLException {
//                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                }
//
//                @Override
//                public Element sign(DigestMethod dm, String string, PublicKey pk, PrivateKey pk1) throws SAMLException {
//                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                }
//
//                @Override
//                public Element sign(DigestMethod dm, String string, X509Certificate xc, PrivateKey pk) throws SAMLException {
//                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                }
//
//                @Override
//                public Element sign(DigestMethod dm, String string, X509Certificate xc, PrivateKey pk, boolean bln) throws SAMLException {
//                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                }
//
//                @Override
//                public void setMajorVersion(BigInteger bi) {
//                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                }
//
//                @Override
//                public void setMinorVersion(BigInteger bi) {
//                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                }
//
//                @Override
//                public void setVersion(String string) {
//                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                }
//
//                @Override
//                public Element toElement(Node node) throws XWSSecurityException {
//                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                }
//
//                @Override
//                public String getSamlIssuer() {
//                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                }
//
//                @Override
//                public String getAssertionID() {
//                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                }
//
//                @Override
//                public String getID() {
//                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                }
//
//                @Override
//                public String getVersion() {
//                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                }
//
//                @Override
//                public BigInteger getMajorVersion() {
//                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                }
//
//                @Override
//                public BigInteger getMinorVersion() {
//                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                }
//
//                @Override
//                public String getType() {
//                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                }
//
//                @Override
//                public Object getTokenValue() {
//                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                }
//            };

//            
//            assertionType.setSignature(signType);
//            remDisp.setAssertion(assertionType);
//            
//            AdditionalDetailsType adty = vfac1.createAdditionalDetailsType();
//            adty.setAssertion(assertionType);
    }

}
