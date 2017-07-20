/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modus.edeliveryclient.jaxb.remdispatchhelpers;

import eu.noble.rem.jaxb.evidence.AttributedElectronicAddressType;
import eu.noble.rem.jaxb.evidence.MessageDetailsType;
import eu.noble.rem.jaxb.evidence.PostalAddressType;
import eu.noble.rem.jaxb.evidence.REMEvidenceType;
import eu.noble.rem.jaxb.xmldsig.DigestMethodType;
import java.math.BigInteger;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author Pantelispanka
 */
public class RemEvidenceHelper {

    public REMEvidenceType createRemEvidenceType(String id, String eventCode, String evidenceIdentifier, String replyTo,
             PostalAddressType pat, XMLGregorianCalendar eventTime, AttributedElectronicAddressType aeat,
             String forwardedToExternalSystem,  MessageDetailsType senderMessageDetails,
             MessageDetailsType notifMessageDetails, BigInteger evidenceRefersToRecipient) {

        eu.noble.rem.jaxb.evidence.ObjectFactory evFact = new eu.noble.rem.jaxb.evidence.ObjectFactory();

        REMEvidenceType remEvid = evFact.createREMEvidenceType();

        remEvid.setId(id);
        remEvid.setEventCode(eventCode);
        remEvid.setEvidenceIdentifier(evidenceIdentifier);
        remEvid.setReplyTo(replyTo);
        remEvid.setEvidenceRefersToRecipient(evidenceRefersToRecipient);
        remEvid.setEventTime(eventTime);
        remEvid.setForwardedToExternalSystem(forwardedToExternalSystem);
        remEvid.setReplyToAddress(aeat);

        remEvid.setSenderMessageDetails(senderMessageDetails);
        remEvid.setNotificationMessageDetails(notifMessageDetails);
        
        
        
        return remEvid;

    }

    public PostalAddressType createPostalAddressType(String countryName,
            String lang, String locality, String postalCode, String stateOrProvince) {

        eu.noble.rem.jaxb.evidence.ObjectFactory evFact = new eu.noble.rem.jaxb.evidence.ObjectFactory();

        PostalAddressType pat = evFact.createPostalAddressType();
        pat.setCountryName(countryName);
        pat.setLang(lang);
        pat.setLocality(locality);
        pat.setPostalCode(postalCode);
        pat.setStateOrProvince(stateOrProvince);
        
        return pat;

    }

    public AttributedElectronicAddressType createAttributedElAddrType(String displayName, String scheme, String value) {

        eu.noble.rem.jaxb.evidence.ObjectFactory evFact = new eu.noble.rem.jaxb.evidence.ObjectFactory();

        AttributedElectronicAddressType aeat = evFact.createAttributedElectronicAddressType();

        aeat.setDisplayName(displayName);
        aeat.setScheme(scheme);
        aeat.setValue(value);
        
        return aeat;

    }

    
    public MessageDetailsType createMessageDetails(byte[] digestValue,DigestMethodType digestMethod
            , Boolean isNotific, String messageIdentifierByREMMD
            , String messageSubject, String UAMessageIdentifier){
        
        eu.noble.rem.jaxb.evidence.ObjectFactory evFact = new eu.noble.rem.jaxb.evidence.ObjectFactory();
        
        MessageDetailsType mdt = evFact.createMessageDetailsType();
        
        
        mdt.setIsNotification(isNotific);
        mdt.setMessageIdentifierByREMMD(messageIdentifierByREMMD);
        mdt.setMessageSubject(messageSubject);
        mdt.setUAMessageIdentifier(UAMessageIdentifier);
        mdt.setDigestMethod(digestMethod);
        mdt.setDigestValue(digestValue);
        
    
        return mdt;
        
    }
    
}
