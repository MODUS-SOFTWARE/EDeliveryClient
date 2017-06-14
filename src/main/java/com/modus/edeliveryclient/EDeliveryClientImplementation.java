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
import com.modus.edeliveryclient.jaxb.standardbusinessdocument.PapyrosDocument;
import com.modus.edeliveryclient.jaxb.standardbusinessdocument.SBDHFactory;
import com.modus.edeliveryclient.jaxb.standardbusinessdocument.Scope;
import com.modus.edeliveryclient.jaxb.standardbusinessdocument.StandardBusinessDocumentHeader;
import com.modus.edeliveryclient.models.Authorization;
import com.modus.edeliveryclient.models.Messages;
import com.modus.edeliveryclient.models.MessageId;
import com.modus.edeliveryclient.models.ResponseMessage;
import com.modus.edeliveryclient.models.SBDParams;
import com.modus.edeliveryclient.serialize.Serializer;
import gr.modus.edelivery.adapter.messages.MessageParams;
import gr.modus.edelivery.adapter.messages.PDispatchMessage;

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
    public CompletableFuture<ResponseMessage> createOutgoingDefaultImpl(StandardBusinessDocumentHeader sbdh, String payload, Authorization auth) throws JAXBException {
        return sbdConsumer.createOutgoingDefault(sbdh, payload, auth);
    }

    @Override
    public CompletableFuture<Object> getMessageDefault(String messageId, Authorization auth, boolean stringForm) throws JAXBException {
        return sbdConsumer.getMessageDefault(messageId, auth,stringForm);
    }

    @Override
    public CompletableFuture<Messages> getMesaggesPending(Authorization auth) {
        return sbdConsumer.getMesaggesPending(auth);
    }

    @Override
    public CompletableFuture<ResponseMessage> sendMessage(SBDParams sbdParams, MessageParams params, Authorization auth)
            throws JAXBException, MalformedURLException, DatatypeConfigurationException {
        // TODO Auto-generated method stub
        //PDispatchMessage p = new PDispatchMessage(params);
        //p .createREMDispatchType();
        //StandardBusinessDocument sbd = new StandardBusinessDocument(); 
        //String sbdStr="";
        //return sbdConsumer.sendMessafeDefault(sbdStr, auth);// createOutgoingDefault(sbdh, payload, auth);
        //StandardBusinessDocumentHeader sbdh ,MessageParams params, Authorization auth

        //StandardBusinessDocumentHeaderGenerator
        StandardBusinessDocumentHeader sbdHeader = new StandardBusinessDocumentHeader();
        /*
		 * businDocHeader = new StandardBusinessDocumentHeaderGenerator()
                .generateDocumentHeaderfromValues(headerVersion, participantIdentifierSenderScheme, participantIdentifierSenderValue,
                        participantIdentifierReceiverScheme, participantIdentifierReceiverValue,
                        documentIdStandard, docTypeVersion, documentInstanceIdentifier, documentType, businessScopes,
                        manifestDescr, manifestLanguage, maniTypeQualCode, uniformResourceIdentifier);
		 * */

        List<Scope> businessScopes = new ArrayList<Scope>();
        Scope scope1 = new Scope();
        scope1.setIdentifier(sbdParams.getScopeIdentifier());
        scope1.setInstanceIdentifier("Instance");
        scope1.setType(sbdParams.getScopeType());

        Scope scope2 = new Scope();
        scope2.setIdentifier(sbdParams.getScopeIdentifier2());
        scope2.setInstanceIdentifier("Instance");
        scope2.setType(sbdParams.getScopeType2());

        BusinessScope bScope1 = new BusinessScope();
        businessScopes.add(scope1);
        businessScopes.add(scope2);

        sbdHeader = new StandardBusinessDocumentHeaderGenerator()
                .generateDocumentHeaderfromValues(sbdParams.getHeaderVersion(), sbdParams.getParticipantIdentifierSenderScheme(),
                         sbdParams.getParticipantIdentifierSenderValue(),
                         sbdParams.getParticipantIdentifierReceiverScheme(),
                         sbdParams.getParticipantIdentifierReceiverValue(),
                         sbdParams.getDocumentIdStandard(),
                         sbdParams.getDocTypeVersion(),
                         sbdParams.getDocumentInstanceIdentifier(),
                         sbdParams.getDocumentType(),
                         businessScopes, sbdParams.getManifestDescr(),
                         sbdParams.getManifestLanguage(),
                         sbdParams.getManiTypeQualCode(),
                         sbdParams.getUniformResourceIdentifier());

        String payload = "";
        PDispatchMessage p = new PDispatchMessage(params);
        payload = p.createREMDispatchType();
        return sbdConsumer.createOutgoingDefault(sbdHeader, payload, auth);
    }

}
