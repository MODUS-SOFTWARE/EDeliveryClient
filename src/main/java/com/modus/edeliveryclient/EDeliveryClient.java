/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modus.edeliveryclient;

import com.modus.edeliveryclient.jaxb.standardbusinessdocument.PapyrosDocument;
import com.modus.edeliveryclient.jaxb.standardbusinessdocument.StandardBusinessDocumentHeader;
import com.modus.edeliveryclient.models.Authorization;
import com.modus.edeliveryclient.models.Messages;
import com.modus.edeliveryclient.models.MessageId;
import com.modus.edeliveryclient.models.ResponseMessage;
import com.modus.edeliveryclient.models.SBDParams;

import gr.modus.edelivery.adapter.messages.MessageParams;

import java.net.MalformedURLException;
import java.util.concurrent.CompletableFuture;
import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;

/**
 *
 * @author Pantelispanka
 * @author AG
 */
public interface EDeliveryClient {

    public CompletableFuture<ResponseMessage> createParticipant(String participantIdentifierScheme, String participantIdentifierValue, Authorization auth);

    public CompletableFuture<ResponseMessage> deleteParticipantId(String participantIdentifierScheme, String participantIdentifierValue, Authorization auth);

    public CompletableFuture<ResponseMessage> createOutgoingDefaultImpl(StandardBusinessDocumentHeader sbdh, String payload, Authorization auth) throws JAXBException;

    public CompletableFuture<ResponseMessage> sendMessage(SBDParams sbdParams,MessageParams params, Authorization auth) throws JAXBException, MalformedURLException, DatatypeConfigurationException;
    
    public CompletableFuture<Object> getMessageDefault(String messageId, Authorization auth) throws JAXBException;

    public CompletableFuture<Messages> getMesaggesPending(Authorization auth);

}
