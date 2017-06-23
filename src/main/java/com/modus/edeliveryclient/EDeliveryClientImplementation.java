/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modus.edeliveryclient;

import com.modus.edelivery.standardbusinessdocumet.StandardBusinessDocument;
import com.modus.edelivery.standardbusinessdocumet.StandardBusinessDocument.StandardBusinessDocumentHeader.BusinessScope;
import com.modus.edeliveryclient.consumer.SbdConsumer;
import com.modus.edeliveryclient.consumer.SmpParticipantConsumer;
import com.modus.edeliveryclient.jaxb.marshaller.StandardBusinessDocumentHeaderGenerator;
import com.modus.edeliveryclient.jaxb.standardbusinessdocument.REMDispatch;
import com.modus.edeliveryclient.jaxb.standardbusinessdocument.SBDHFactory;
import com.modus.edeliveryclient.jaxb.standardbusinessdocument.Scope;
import com.modus.edeliveryclient.jaxb.standardbusinessdocument.StandardBusinessDocumentHeader;
import com.modus.edeliveryclient.models.Authorization;
import com.modus.edeliveryclient.models.Messages;
import com.modus.edeliveryclient.models.MessageId;
import com.modus.edeliveryclient.models.ResponseMessage;
import com.modus.edeliveryclient.models.SBDParams;
import com.modus.edeliveryclient.serialize.Serializer;
import eu.noble.rem.jaxb.despatch.REMDispatchType;


//import gr.modus.edelivery.adapter.messages.MessageParams;
//import gr.modus.edelivery.adapter.messages.PDispatchMessage;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;

import org.asynchttpclient.AsyncHttpClient;

/**
 *
 * @author Pantelispanka
 */
public class EDeliveryClientImplementation implements EDeliveryClient {

    private final Serializer serializer;
    private final AsyncHttpClient httpClient;

    private final SmpParticipantConsumer participantConsumer;
    private final SbdConsumer sbdConsumer;

    private String username;
    private String password;

    public EDeliveryClientImplementation(
            AsyncHttpClient client,
            Serializer serializer,
            SmpParticipantConsumer participantConsumer,
            SbdConsumer sbdConsumer) {
        this.httpClient = client;
        this.serializer = serializer;
        this.participantConsumer = participantConsumer;
        this.sbdConsumer = sbdConsumer;
    }

    @Override
    public CompletableFuture<ResponseMessage> createParticipant(String participantIdentifierScheme, String participantIdentifierValue, Authorization auth) {
        return participantConsumer.createParticipant(participantIdentifierScheme, participantIdentifierValue, auth);
    }

    @Override
    public CompletableFuture<ResponseMessage> deleteParticipantId(String participantIdentifierScheme, String participantIdentifierValue, Authorization auth) {
        return participantConsumer.deleteParticipantId(participantIdentifierScheme, participantIdentifierValue, auth);
    }

    @Override
    public CompletableFuture<ResponseMessage> createOutgoingDefaultImpl(StandardBusinessDocumentHeader sbdh, REMDispatchType remType, Authorization auth) {
        return sbdConsumer.createOutgoingDefault(sbdh, remType, auth);
    }

    @Override
    public CompletableFuture<Object> getMessageDefault(String messageId, Authorization auth) throws JAXBException {
        return sbdConsumer.getMessageDefault(messageId, auth);
    }

    @Override
    public CompletableFuture<Messages> getMesaggesPending(Authorization auth) {
        return sbdConsumer.getMesaggesPending(auth);
    }


}
