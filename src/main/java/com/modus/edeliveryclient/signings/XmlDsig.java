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
import java.security.Key;
import java.security.KeyException;
import java.security.KeyStore;
import java.security.PublicKey;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.xml.crypto.AlgorithmMethod;
import javax.xml.crypto.KeySelector;
import javax.xml.crypto.KeySelectorException;
import javax.xml.crypto.KeySelectorResult;
import javax.xml.crypto.XMLCryptoContext;
import javax.xml.crypto.XMLStructure;
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
import javax.xml.crypto.dsig.dom.DOMValidateContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.keyinfo.KeyValue;
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
public class XmlDsig implements ISignatures{

//    private String keystorePath = "/Users/modussa/certificates/privateKey.store";
//    private String keystorePasword = "@#$M0dus";
//    private String pkEntry = "ftpkey";
//    private String keystoreInstance = "JKS";
    
    private String keystorePath;
    private String keystorePasword;
    private String pkEntry;
    private String keystroreInstance;
    
    public XmlDsig(String keystorePath, String keystorePassword
            , String pkEntry, String keystroreInstance) {
        this.keystorePasword = keystorePassword;
        this.keystorePath= keystorePath;
        this.pkEntry = pkEntry;
        this.keystroreInstance = keystroreInstance;
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


        SignedInfo si = fac.newSignedInfo(fac.newCanonicalizationMethod(CanonicalizationMethod.INCLUSIVE,
                (C14NMethodParameterSpec) null),
                fac.newSignatureMethod(SignatureMethod.DSA_SHA1, null),
                Collections.singletonList(ref));

        // Load the KeyStore and get the signing key and certificate.
        KeyStore ks = KeyStore.getInstance(keystroreInstance);
        ks.load(new FileInputStream(keystorePath), keystorePasword.toCharArray());
        KeyStore.PrivateKeyEntry keyEntry = (KeyStore.PrivateKeyEntry) ks
                .getEntry(pkEntry, new KeyStore.PasswordProtection(keystorePasword.toCharArray()));

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
        KeyStore ks = KeyStore.getInstance(keystroreInstance);
        ks.load(new FileInputStream(keystorePath), keystorePasword.toCharArray());
        KeyStore.PrivateKeyEntry keyEntry = (KeyStore.PrivateKeyEntry) ks
                .getEntry(pkEntry, new KeyStore.PasswordProtection(keystorePasword.toCharArray()));

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

        return remDispatch;

    }


    public XMLSignature signature() throws NoSuchAlgorithmException,
            InvalidAlgorithmParameterException,
            UnrecoverableEntryException,
            KeyStoreException,
            FileNotFoundException,
            IOException,
            CertificateException  {

        SignatureType sign = new SignatureType();

        XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM");

        KeyInfoFactory kif = fac.getKeyInfoFactory();

        Reference ref = fac.newReference("", fac.newDigestMethod(DigestMethod.SHA1, null));

        SignedInfo si = fac.newSignedInfo(fac.newCanonicalizationMethod(
                CanonicalizationMethod.EXCLUSIVE,
                (C14NMethodParameterSpec) null), fac.newSignatureMethod(
                        SignatureMethod.DSA_SHA1, null), Collections.singletonList(ref));

        KeyStore ks = KeyStore.getInstance(keystroreInstance);
        ks.load(new FileInputStream(keystorePath), keystorePasword.toCharArray());
        KeyStore.PrivateKeyEntry keyEntry = (KeyStore.PrivateKeyEntry) ks
                .getEntry(pkEntry, new KeyStore.PasswordProtection(keystorePasword.toCharArray()));

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
        } catch (Exception e) {
            e.printStackTrace();
        }

        XMLSignature signature = fac.newXMLSignature(si, ki);

        SignatureValue sv = signature.getSignatureValue();

        kift.getContent().add(ki);
        sinTy.setKeyInfo(kift);

        SignatureValueType svt = new SignatureValueType();

//        sinTy.setSignatureValue( (SignatureValueType)  sv.getValue());
        return signature;

    }

    public boolean checkSignature(File f) throws ParserConfigurationException, FileNotFoundException, SAXException, IOException, Exception {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        Document doc
                = dbf.newDocumentBuilder().parse(new FileInputStream(f));

        // Find Signature element
        NodeList nl
                = doc.getElementsByTagNameNS(XMLSignature.XMLNS, "Signature");
        if (nl.getLength() == 0) {
            throw new Exception("Cannot find Signature element");
        }

        // Create a DOM XMLSignatureFactory that will be used to unmarshal the
        // document containing the XMLSignature
        XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM");

        // Create a DOMValidateContext and specify a KeyValue KeySelector
        // and document context
        DOMValidateContext valContext = new DOMValidateContext(new KeyValueKeySelector(), nl.item(0));

        // unmarshal the XMLSignature
        XMLSignature signature = fac.unmarshalXMLSignature(valContext);

        // Validate the XMLSignature (generated above)
        boolean coreValidity = signature.validate(valContext);

        // Check core validation status
        if (coreValidity == false) {    
            System.err.println("Signature failed core validation");
            boolean sv = signature.getSignatureValue().validate(valContext);
            System.out.println("signature validation status: " + sv);
            // check the validation status of each Reference
            Iterator i = signature.getSignedInfo().getReferences().iterator();
            for (int j = 0; i.hasNext(); j++) {
                boolean refValid
                        = ((Reference) i.next()).validate(valContext);
                
                System.out.println("ref[" + j + "] validity status: " + refValid);
                
            }
        } else {
            System.out.println("Signature passed core validation");
        }
        return coreValidity;
    }

    private static class KeyValueKeySelector extends KeySelector {

        public KeySelectorResult select(KeyInfo keyInfo,
                KeySelector.Purpose purpose,
                AlgorithmMethod method,
                XMLCryptoContext context)
                throws KeySelectorException {
            if (keyInfo == null) {
                throw new KeySelectorException("Null KeyInfo object!");
            }
            SignatureMethod sm = (SignatureMethod) method;
            List list = keyInfo.getContent();

            for (int i = 0; i < list.size(); i++) {
                XMLStructure xmlStructure = (XMLStructure) list.get(i);
                if (xmlStructure instanceof KeyValue) {
                    PublicKey pk = null;
                    try {
                        pk = ((KeyValue) xmlStructure).getPublicKey();
                    } catch (KeyException ke) {
                        throw new KeySelectorException(ke);
                    }

                    // make sure algorithm is compatible with method
                    if (algEquals(sm.getAlgorithm(), pk.getAlgorithm())) {
                        return new SimpleKeySelectorResult(pk);
                    }

                } else if (xmlStructure instanceof X509Data) {
                    PublicKey pk = null;
                    for (Object data : ((X509Data) xmlStructure).getContent()) {
                        if (data instanceof X509Certificate) {
                            pk = ((X509Certificate) data).getPublicKey();
                            if (algEquals(sm.getAlgorithm(), pk.getAlgorithm())) {
                                return new SimpleKeySelectorResult(pk);
                            }
                        }
                    }

                }
            }
            throw new KeySelectorException("No KeyValue element found!");
        }

        //@@@FIXME: this should also work for key types other than DSA/RSA
        static boolean algEquals(String algURI, String algName) {
            if (algName.equalsIgnoreCase("DSA")
                    && algURI.equalsIgnoreCase(SignatureMethod.DSA_SHA1)) {
                return true;
            } else if (algName.equalsIgnoreCase("RSA")
                    && algURI.equalsIgnoreCase(SignatureMethod.RSA_SHA1)) {
                return true;
            } else {
                return false;
            }
        }
    }

    private static class SimpleKeySelectorResult implements KeySelectorResult {

        private PublicKey pk;

        SimpleKeySelectorResult(PublicKey pk) {
            this.pk = pk;
        }

        public Key getKey() {
            return pk;
        }
    }

}
