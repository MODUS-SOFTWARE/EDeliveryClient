/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modus.edeliveryclient;

import com.modus.edeliveryclient.jaxb.standardbusinessdocument.PapyrosDocument;
import com.modus.edeliveryclient.jaxb.standardbusinessdocument.StandardBusinessDocumentHeader;
import com.modus.edeliveryclient.models.Authorization;
import com.modus.edeliveryclient.models.ResponseMessage;
import java.util.concurrent.CompletableFuture;
import javax.xml.bind.JAXBException;

/**
 *
 * @author Pantelispanka
 * @author AG
 */
public interface EDeliveryClient {

    public CompletableFuture<ResponseMessage> createParticipant(String participantIdentifierScheme, String participantIdentifierValue, Authorization auth);
    
    public CompletableFuture<ResponseMessage> deleteParticipantId(String participantIdentifierScheme, String participantIdentifierValue, Authorization auth);

    public CompletableFuture<ResponseMessage> createOutgoingDefaultImpl(StandardBusinessDocumentHeader sbdh, PapyrosDocument papDoc, Authorization auth) throws JAXBException;

}
