/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modus.edeliveryclient.jaxb.remdispatchhelpers;

import eu.noble.rem.jaxb.despatch.DeliveryConstraints;
import eu.noble.rem.jaxb.despatch.Destinations;
import eu.noble.rem.jaxb.despatch.Destinations.OtherRecipients;
import eu.noble.rem.jaxb.despatch.Informational;
import eu.noble.rem.jaxb.despatch.MsgIdentification;
import eu.noble.rem.jaxb.despatch.MsgMetaData;
import eu.noble.rem.jaxb.despatch.NormalizedMsg;
import eu.noble.rem.jaxb.despatch.OriginalMsgType;
import eu.noble.rem.jaxb.despatch.Originators;
import eu.noble.rem.jaxb.despatch.REMDispatchType;
import eu.noble.rem.jaxb.despatch.REMMDEvidenceListType;
import eu.noble.rem.jaxb.evidence.CertIDAndSignatureType;
import eu.noble.rem.jaxb.evidence.CertSignatureDetailsType;
import eu.noble.rem.jaxb.evidence.CertificateDetailsType;
import eu.noble.rem.jaxb.evidence.EntityDetailsType;
import eu.noble.rem.jaxb.evidence.NamePostalAddressType;
import eu.noble.rem.jaxb.evidence.NamesPostalAddressListType;
import eu.noble.rem.jaxb.evidence.PostalAddressType;
import eu.noble.rem.jaxb.saml.AssertionType;
import java.math.BigInteger;
import javax.xml.bind.JAXBElement;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author Pantelispanka
 */
public class RemDispatchHelper {

    public RemDispatchHelper() {
    }

    public MsgMetaData createRemDispatchMetaData(XMLGregorianCalendar initialSend,
            XMLGregorianCalendar obsoleteAfter,
            XMLGregorianCalendar origin,
            String countryName,
            String language, String locality,
            String postalCode, String stateOrProvince, String messageId) {

        eu.noble.rem.jaxb.despatch.ObjectFactory of = new eu.noble.rem.jaxb.despatch.ObjectFactory();

        eu.noble.rem.jaxb.evidence.ObjectFactory of1 = new eu.noble.rem.jaxb.evidence.ObjectFactory();

        MsgMetaData metaData = of.createMsgMetaData();

        DeliveryConstraints delCon = of.createDeliveryConstraints();
        delCon.setInitialSend(initialSend);
        delCon.setObsoleteAfter(obsoleteAfter);
        delCon.setOrigin(origin);
        metaData.setDeliveryConstraints(delCon);

        Destinations dest = of.createDestinations();

        EntityDetailsType entDet = of1.createEntityDetailsType();

        NamePostalAddressType nat = of1.createNamePostalAddressType();
        PostalAddressType pat = of1.createPostalAddressType();
        pat.setCountryName(countryName);
        pat.setLang(language);
        pat.setLocality(locality);
        pat.setPostalCode(postalCode);
        pat.setStateOrProvince(stateOrProvince);
        nat.setPostalAddress(pat);

        NamesPostalAddressListType lt = of1.createNamesPostalAddressListType();
        lt.getNamePostalAddress().add(nat);

        entDet.setNamesPostalAddresses(lt);
        dest.setRecipient(entDet);
        metaData.setDestinations(dest);

        MsgIdentification msgId = of.createMsgIdentification();
        msgId.setMessageID(messageId);

        metaData.setMsgIdentification(msgId);

        Originators originator = of.createOriginators();
        EntityDetailsType sender = of1.createEntityDetailsType();

        CertificateDetailsType cdt = of1.createCertificateDetailsType();

        CertIDAndSignatureType cst = of1.createCertIDAndSignatureType();

//        cdt.setCertID(value);
        sender.setNamesPostalAddresses(lt);

//        originator.setSender(entDet);
//        metaData.setOriginators(value);
        return metaData;

    }

    public NormalizedMsg createNormalizedMessage(String comments, String subject) {

        eu.noble.rem.jaxb.despatch.ObjectFactory of = new eu.noble.rem.jaxb.despatch.ObjectFactory();

        eu.noble.rem.jaxb.evidence.ObjectFactory of1 = new eu.noble.rem.jaxb.evidence.ObjectFactory();

        NormalizedMsg nms = of.createNormalizedMsg();

        Informational info = of.createInformational();
        info.setComments(comments);
        info.setSubject(subject);

        nms.setInformational(info);

        return nms;

    }

    public OriginalMsgType createOriginalMessage(String contentType, BigInteger size, byte[] actualmsg) {

        eu.noble.rem.jaxb.despatch.ObjectFactory of = new eu.noble.rem.jaxb.despatch.ObjectFactory();

        eu.noble.rem.jaxb.evidence.ObjectFactory of1 = new eu.noble.rem.jaxb.evidence.ObjectFactory();

        OriginalMsgType omt = of.createOriginalMsgType();

        omt.setContentType(contentType);
        omt.setSize(size);
        omt.setValue(actualmsg);

        return omt;

    }
    
    public REMDispatchType createRemDispatch(String id,MsgMetaData msmeta, NormalizedMsg normMsg
            , OriginalMsgType orMessag, AssertionType assertion, REMMDEvidenceListType evidList){
        
        eu.noble.rem.jaxb.despatch.ObjectFactory of = new eu.noble.rem.jaxb.despatch.ObjectFactory();
    
        REMDispatchType rem = of.createREMDispatchType();
        
        
        rem.setId(id);
        rem.setMsgMetaData(msmeta);
        rem.setNormalizedMsg(normMsg);
        rem.setOriginalMsg(orMessag);
        rem.setAssertion(assertion);
        rem.setREMMDEvidenceList(evidList);
        
        
        return rem;
        
    }
    

    public JAXBElement<REMDispatchType> generateRexmXml(REMDispatchType remDispatch){
        
        eu.noble.rem.jaxb.despatch.ObjectFactory of = new eu.noble.rem.jaxb.despatch.ObjectFactory();
        
        JAXBElement<REMDispatchType> remDisp = of.createREMDispatch(remDispatch);
        
        return remDisp;
    
    }
    
    
}
