/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modus.edeliveryclient.signings;

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

//import org.opensaml.common.impl.AbstractSAMLObjectMarshaller;
//import org.opensaml.common.binding
/**
 *
 * @author Pantelispanka
 */
public class XmlDsig {

//    AbstractSAMLObjectMarshaller asm = new AbstractSAMLObjectMarshaller() {};
    public DOMSignContext signatureBuilder(File f) throws ParserConfigurationException, SAXException, IOException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, CertificateException, UnrecoverableEntryException, KeyStoreException, MarshalException, XMLSignatureException, TransformerConfigurationException, TransformerException {
        

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

        OutputStream os = new FileOutputStream("/Users/modussa/NetBeansProjects/EDeliveryClient/src/test/resources/SignedTests.xml");
        TransformerFactory tf = TransformerFactory.newInstance();
        javax.xml.transform.Transformer trans = tf.newTransformer();
        trans.transform(new DOMSource(doc), new StreamResult(os));

        return dsc;

    }

}
