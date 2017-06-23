/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modus.edeliveryclient.signings;

import com.modus.edeliveryclient.jaxb.standardbusinessdocument.REMDispatch;
import eu.noble.rem.jaxb.xmldsig.KeyInfoType;
import eu.noble.rem.jaxb.xmldsig.SignatureType;
import eu.noble.rem.jaxb.xmldsig.SignatureValueType;
import javax.xml.crypto.dsig.XMLSignatureException;
import javax.xml.crypto.MarshalException;
import java.security.KeyStoreException;
import java.security.cert.CertificateException;
import java.security.UnrecoverableEntryException;

import java.security.InvalidAlgorithmParameterException;

import java.security.NoSuchAlgorithmException;

import java.io.IOException;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.security.KeyStore;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.xml.crypto.dsig.CanonicalizationMethod;
import javax.xml.crypto.dsig.DigestMethod;
import javax.xml.crypto.dsig.Reference;
import javax.xml.crypto.dsig.SignatureMethod;
import javax.xml.crypto.dsig.SignedInfo;
import javax.xml.crypto.dsig.Transform;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignature.SignatureValue;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.keyinfo.X509Data;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

//import org.opensaml.common.impl.AbstractSAMLObjectMarshaller;
//import org.opensaml.common.binding
/**
 *
 * @author Pantelispanka
 */
public class XmlDsig {

    public XmlDsig() {
    }

    public DOMSignContext signatureBuilder(File f) throws ParserConfigurationException,
            SAXException, IOException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, CertificateException,
            UnrecoverableEntryException, KeyStoreException, MarshalException,
            XMLSignatureException, TransformerConfigurationException, TransformerException {

        XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM");
        KeyInfoFactory kif = fac.getKeyInfoFactory();

        Reference ref = null;
        try {
            ref = (Reference) fac.newReference("", fac.newDigestMethod(DigestMethod.SHA1, null),
                    Collections.singletonList(fac.newTransform(Transform.ENVELOPED, (TransformParameterSpec) null)),
                    null, null);

        } catch (Exception e) {
            e.printStackTrace();
        }
//        try {
//            ref = (Reference) fac.newReference("", fac.newDigestMethod(DigestMethod.SHA1, null),
//                    Collections.singletonList(fac.newTransform(Transform.XPATH2,new  XPathFilterParameterSpec("//REMDispatch[]"))),
//                    null, null);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        SignedInfo si = fac.newSignedInfo(fac.newCanonicalizationMethod(CanonicalizationMethod.INCLUSIVE,
                (C14NMethodParameterSpec) null),
                fac.newSignatureMethod(SignatureMethod.DSA_SHA1, null),
                Collections.singletonList(ref));

        // Load the KeyStore and get the signing key and certificate.
        KeyStore ks = KeyStore.getInstance("JKS");
        ks.load(new FileInputStream("/Users/modussa/certificates/privateKey.store"), "@#$M0dus".toCharArray());
        KeyStore.PrivateKeyEntry keyEntry = (KeyStore.PrivateKeyEntry) ks
                .getEntry("ftpkey", new KeyStore.PasswordProtection("@#$M0dus".toCharArray()));

        X509Certificate cert = null;
        try {
            cert = (X509Certificate) keyEntry.getCertificate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        List x509Content = new ArrayList();
        x509Content.add(cert.getSubjectX500Principal().getName());
        x509Content.add(cert);
        X509Data xd = kif.newX509Data(x509Content);
        KeyInfo ki = kif.newKeyInfo(Collections.singletonList(xd));

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        DOMSignContext dsc = null;
        Document doc = dbf.newDocumentBuilder().parse(f);
        try {

            dsc = new DOMSignContext(keyEntry.getPrivateKey(), doc.getDocumentElement());

            XMLSignature signature = fac.newXMLSignature(si, ki);
            signature.sign(dsc);
        } catch (Exception e) {
            e.printStackTrace();
        }

        OutputStream os = new FileOutputStream(f.getPath());
        TransformerFactory tf = TransformerFactory.newInstance();
        javax.xml.transform.Transformer trans = tf.newTransformer();
        trans.transform(new DOMSource(doc), new StreamResult(os));

        return dsc;

        
    }

    public REMDispatch signRem(REMDispatch remDispatch, File f) throws ParserConfigurationException,
            SAXException, IOException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, CertificateException,
            UnrecoverableEntryException, KeyStoreException, MarshalException,
            XMLSignatureException, TransformerConfigurationException, TransformerException, Exception {

        XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM");
        KeyInfoFactory kif = fac.getKeyInfoFactory();

        Reference ref = null;
        try {
            ref = (Reference) fac.newReference("", fac.newDigestMethod(DigestMethod.SHA1, null),
                    Collections.singletonList(fac.newTransform(Transform.ENVELOPED, (TransformParameterSpec) null)),
                    null, null);

        } catch (Exception e) {
            e.printStackTrace();
        }

        SignedInfo si = fac.newSignedInfo(fac.newCanonicalizationMethod(CanonicalizationMethod.INCLUSIVE,
                (C14NMethodParameterSpec) null),
                fac.newSignatureMethod(SignatureMethod.DSA_SHA1, null),
                Collections.singletonList(ref));

        // Load the KeyStore and get the signing key and certificate.
        KeyStore ks = KeyStore.getInstance("JKS");
        ks.load(new FileInputStream("/Users/modussa/certificates/privateKey.store"), "@#$M0dus".toCharArray());
        KeyStore.PrivateKeyEntry keyEntry = (KeyStore.PrivateKeyEntry) ks
                .getEntry("ftpkey", new KeyStore.PasswordProtection("@#$M0dus".toCharArray()));

        X509Certificate cert = null;
        try {
            cert = (X509Certificate) keyEntry.getCertificate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        List x509Content = new ArrayList();
        x509Content.add(cert.getSubjectX500Principal().getName());
        x509Content.add(cert);
        X509Data xd = kif.newX509Data(x509Content);
        KeyInfo ki = kif.newKeyInfo(Collections.singletonList(xd));

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        DOMSignContext dsc = null;
        Document doc = dbf.newDocumentBuilder().parse(f);
        try {

            dsc = new DOMSignContext(keyEntry.getPrivateKey(), doc.getDocumentElement());

            XMLSignature signature = fac.newXMLSignature(si, ki);
            signature.sign(dsc);
        } catch (Exception e) {
            e.printStackTrace();
        }

        OutputStream os = new FileOutputStream(f.getPath());
        TransformerFactory tf = TransformerFactory.newInstance();
        javax.xml.transform.Transformer trans = tf.newTransformer();
        trans.transform(new DOMSource(doc), new StreamResult(os));

        NodeList nl
                = doc.getElementsByTagNameNS(XMLSignature.XMLNS,
                        "Signature");
//        if (nl.getLength() == 0) {
//            throw new Exception("Cannot find Signature element");
//        }

//        if(nl.equals(signature)){
//            String test = "asdfas";  
//        };
//        String providerName = System.getProperty(
//                "jsr105Provider",
//                "org.jcp.xml.dsig.internal.dom.XMLDSigRI");
//        XMLSignatureFactory fact
//                = XMLSignatureFactory.getInstance("DOM",
//                        (Provider) Class.forName(providerName).newInstance());
//        
////        DOMValidateContext valContext = new DOMValidateContext(new KeyValueKeySelector(), nl.item(0));
//
//        XMLSignature signature
//                = fact.unmarshalXMLSignature(valContext);
//        boolean coreValidity = signature.validate(valContext);
//        remDispatch.setSignature();
        return remDispatch;

    }

//    public SignatureType signatureParser(File f) throws ParserConfigurationException, SAXException, IOException {
//
//        SignatureType signature = new SignatureType();
//        KeyInfoType kif = new KeyInfoType();
//        SignatureValueType signValType = new SignatureValueType();
//        SignedInfoType signInf = new SignedInfoType();
//
//        CanonicalizationMethodType canoni = new CanonicalizationMethodType();
//        SignatureMethodType signMethod = new SignatureMethodType();
//
////        signMethod.setAlgorithm(value);
////        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
////        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
////        Document doc = dBuilder.parse(f);
////        doc.getDocumentElement().normalize();
//        return signature;
//
//    }
    
    
    
    public XMLSignature signature() throws NoSuchAlgorithmException,
            InvalidAlgorithmParameterException,
            UnrecoverableEntryException,
            KeyStoreException,
            FileNotFoundException,
            IOException,
            CertificateException {

        SignatureType sign = new SignatureType();

        XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM");

        KeyInfoFactory kif = fac.getKeyInfoFactory();

        Reference ref = fac.newReference("", fac.newDigestMethod(DigestMethod.SHA1, null));

        SignedInfo si = fac.newSignedInfo(fac.newCanonicalizationMethod(
                CanonicalizationMethod.EXCLUSIVE,
                (C14NMethodParameterSpec) null), fac.newSignatureMethod(
                        SignatureMethod.DSA_SHA1, null), Collections.singletonList(ref));

        KeyStore ks = KeyStore.getInstance("JKS");
        ks.load(new FileInputStream("/Users/modussa/certificates/privateKey.store"), "@#$M0dus".toCharArray());
        KeyStore.PrivateKeyEntry keyEntry = (KeyStore.PrivateKeyEntry) ks
                .getEntry("ftpkey", new KeyStore.PasswordProtection("@#$M0dus".toCharArray()));

        X509Certificate sigCert = (X509Certificate) keyEntry.getCertificate();

        
        SignatureType sinTy = new SignatureType();
        
        
        
        KeyInfo ki = null;
        KeyInfoType kift = new KeyInfoType();
        try {
            List x509Content = new ArrayList();
            x509Content.add(sigCert.getSubjectX500Principal().getName());
            x509Content.add(sigCert);
            X509Data xd = kif.newX509Data(x509Content);
            
             
            
//            sinTy.getKeyInfo()
            
            ki = kif.newKeyInfo(Collections.singletonList(xd));
        }catch(Exception e){
            e.printStackTrace();
        }

        XMLSignature signature = fac.newXMLSignature(si, ki);
        
        
        
        SignatureValue sv =  signature.getSignatureValue();
        
        
                
        
        
        
        kift.getContent().add(ki);
        sinTy.setKeyInfo(kift);
        
        
        SignatureValueType svt = new SignatureValueType();
        
        
        
//        sinTy.setSignatureValue( (SignatureValueType)  sv.getValue());
        
        return signature;

    }

}
