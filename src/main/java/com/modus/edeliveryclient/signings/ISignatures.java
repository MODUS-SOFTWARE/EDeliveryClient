/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modus.edeliveryclient.signings;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import javax.xml.crypto.KeySelector;
import javax.xml.crypto.MarshalException;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignatureException;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;

/**
 *
 * @author modussa
 */
public interface ISignatures {
    
    public DOMSignContext signatureBuilder(File f) throws ParserConfigurationException,
            SAXException, IOException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, CertificateException,
            UnrecoverableEntryException, KeyStoreException, MarshalException,
            XMLSignatureException, TransformerConfigurationException, TransformerException;
    
    
    public XMLSignature signature() throws NoSuchAlgorithmException,
            InvalidAlgorithmParameterException,
            UnrecoverableEntryException,
            KeyStoreException,
            FileNotFoundException,
            IOException,
            CertificateException;
    
    public boolean checkSignature(File f) throws ParserConfigurationException, FileNotFoundException, SAXException, IOException, Exception;
    
    
}


