/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modus.edeliveryclient.jaxb.requestGenerator;

import com.modus.edelivery.utils.SBDMessageWrapper;
import com.modus.edeliveryclient.exception.EDeliveryException;
import com.modus.edeliveryclient.jaxb.standardbusinessdocument.SBDHFactory;
import com.modus.edeliveryclient.jaxb.standardbusinessdocument.StandardBusinessDocument;
import com.modus.edeliveryclient.jaxb.standardbusinessdocument.StandardBusinessDocumentHeader;
import com.modus.edeliveryclient.signings.ISignatures;
import com.modus.edeliveryclient.signings.XmlDsig;
import eu.noble.rem.jaxb.despatch.REMDispatchType;
import eu.noble.rem.jaxb.despatch.REMMDMessageType;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.crypto.MarshalException;
import javax.xml.crypto.dsig.XMLSignatureException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;

/**
 *
 * @author Pantelispanka
 */
public class RequestBodyGenerator {

    /**
     *
     * @param sbdh
     * @param remType
     * @param XmlDsig
     * @return an sbd in string with a signed rem dispatch context
     */
    public String generateRemDispatchBody(StandardBusinessDocumentHeader sbdh, REMDispatchType remType, ISignatures XmlDsig) {

        
        
        SBDMessageWrapper sbdWrap = new SBDMessageWrapper();

        String requestBody;

        try {
            File temp = File.createTempFile("tempfile", ".tmp");

            JAXBContext jaxbContext = JAXBContext.newInstance(StandardBusinessDocument.class, SBDHFactory.class);

            StandardBusinessDocument sbd = new StandardBusinessDocument();

            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            sbd.setStandardBusinessDocumentHeader(sbdh);

            jaxbMarshaller.marshal(sbd, temp);

            byte[] encoded = Files.readAllBytes(Paths.get(temp.getAbsolutePath()));
            String sbdString = new String(encoded, "UTF-8");

            sbdWrap.setSBDMessageStr(sbdString);

            File temp2 = File.createTempFile("tempfile2", ".tmp");

            JAXBContext jaxbContext2 = JAXBContext.newInstance(REMDispatchType.class, SBDHFactory.class,
                    eu.noble.rem.jaxb.despatch.ObjectFactory.class,
                    eu.noble.rem.jaxb.evidence.ObjectFactory.class,
                    eu.noble.rem.jaxb.xmldsig.ObjectFactory.class
            );

            eu.noble.rem.jaxb.despatch.ObjectFactory of = new eu.noble.rem.jaxb.despatch.ObjectFactory();

            JAXBElement<REMDispatchType> remdispJ = of.createREMDispatch(remType);

            Marshaller jaxbMarshaller2 = jaxbContext2.createMarshaller();

            jaxbMarshaller2.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbMarshaller2.marshal(remdispJ, temp2);

//            XmlDsig signat = new XmlDsig();

            XmlDsig.signatureBuilder(temp2);

//            signat.signatureBuilder(temp2);

            byte[] encoded2 = Files.readAllBytes(Paths.get(temp2.getAbsolutePath()));
            String remString = new String(encoded2, "UTF-8");

            int index1 = remString.indexOf("<REMDispatch");
            int index2 = remString.indexOf("/REMDispatch>");

            String remStringSigned = remString.substring(index1, index2 + 13);

            sbdWrap.appendPayload(remStringSigned);

            requestBody = sbdWrap.getSBDMessageStr();

        } catch (IOException | InvalidAlgorithmParameterException
                | KeyStoreException | NoSuchAlgorithmException
                | UnrecoverableEntryException | CertificateException
                | JAXBException | MarshalException | XMLSignatureException
                | ParserConfigurationException | TransformerException | SAXException e) {
            throw new EDeliveryException("Could not generate request body", e);
        }

        return requestBody;

    }

    public String generateRemMessageBody(StandardBusinessDocumentHeader sbdh, REMMDMessageType remMessage, ISignatures signatures) {

        SBDMessageWrapper sbdWrap = new SBDMessageWrapper();

        String requestBody;
        String requestBodyTemp;

        try {
            File temp = File.createTempFile("tempfile", ".tmp");

            JAXBContext jaxbContext = JAXBContext.newInstance(StandardBusinessDocument.class, SBDHFactory.class);

            StandardBusinessDocument sbd = new StandardBusinessDocument();

            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            sbd.setStandardBusinessDocumentHeader(sbdh);

            jaxbMarshaller.marshal(sbd, temp);

            byte[] encoded = Files.readAllBytes(Paths.get(temp.getAbsolutePath()));
            String sbdString = new String(encoded, "UTF-8");

            sbdWrap.setSBDMessageStr(sbdString);

            File temp2 = File.createTempFile("tempfile2", ".tmp");

            JAXBContext jaxbContext2 = JAXBContext.newInstance(REMMDMessageType.class, SBDHFactory.class,
                    eu.noble.rem.jaxb.despatch.ObjectFactory.class,
                    eu.noble.rem.jaxb.evidence.ObjectFactory.class,
                    eu.noble.rem.jaxb.xmldsig.ObjectFactory.class
            );

            eu.noble.rem.jaxb.despatch.ObjectFactory of = new eu.noble.rem.jaxb.despatch.ObjectFactory();

            JAXBElement<REMMDMessageType> remDispJ = of.createREMMDMessage(remMessage);

            Marshaller jaxbMarshaller2 = jaxbContext2.createMarshaller();

            jaxbMarshaller2.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbMarshaller2.marshal(remDispJ, temp2);

//            XmlDsig signat = new XmlDsig();
            
            signatures.signatureBuilder(temp2);
//            signat.signatureBuilder(temp2);

            byte[] encoded2 = Files.readAllBytes(Paths.get(temp2.getAbsolutePath()));
            String remMessageS = new String(encoded2, "UTF-8");

            int one = remMessageS.indexOf("?>");
            int two = remMessageS.indexOf("REMMDMessage>");
//            System.out.println(remMessageS.substring(one + 2, two + 13));

            sbdWrap.appendPayload(remMessageS.substring(one+2, two + 13));

            requestBodyTemp = sbdWrap.getSBDMessageStr();

        } catch (IOException | InvalidAlgorithmParameterException
                | KeyStoreException | NoSuchAlgorithmException
                | UnrecoverableEntryException | CertificateException
                | JAXBException | MarshalException | XMLSignatureException
                | ParserConfigurationException | TransformerException | SAXException e) {
            throw new EDeliveryException("Could not generate request body", e);
        }

        
        int oneT = requestBodyTemp.indexOf("?>");
        int twoT = requestBodyTemp.indexOf("Document>");
        requestBody = requestBodyTemp.substring(oneT+2, twoT + 9);
        
        return requestBody;

    }

}
