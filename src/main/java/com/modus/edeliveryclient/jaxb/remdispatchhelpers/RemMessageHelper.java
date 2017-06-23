/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modus.edeliveryclient.jaxb.remdispatchhelpers;

import eu.noble.rem.jaxb.despatch.REMMDEvidenceListType;
import eu.noble.rem.jaxb.despatch.REMMDMessageType;
import eu.noble.rem.jaxb.evidence.REMEvidenceType;
import eu.noble.rem.jaxb.xmldsig.SignatureType;
import javax.xml.bind.JAXBElement;

/**
 *
 * @author Pantelispanka
 */
public class RemMessageHelper {

    public REMMDMessageType createMessage(String messageId,
            REMEvidenceType evid, SignatureType signature) {

        eu.noble.rem.jaxb.despatch.ObjectFactory of = new eu.noble.rem.jaxb.despatch.ObjectFactory();
        
        eu.noble.rem.jaxb.evidence.ObjectFactory of2 = new eu.noble.rem.jaxb.evidence.ObjectFactory();
        eu.noble.rem.jaxb.despatch.REMMDMessageType remMessage = of.createREMMDMessageType();

        remMessage.setId(messageId);
        REMMDEvidenceListType remList = of.createREMMDEvidenceListType();

        JAXBElement<REMEvidenceType> e = of2.createAcceptanceRejectionByRecipient(evid);
        remList.getSubmissionAcceptanceRejectionAndRelayREMMDAcceptanceRejectionAndRelayREMMDFailure().add(e);
        remMessage.setREMMDEvidenceList(remList);
        remMessage.setSignature(signature);
        
        
        return remMessage;
        
    }

}
