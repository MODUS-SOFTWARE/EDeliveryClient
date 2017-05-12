/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modus.edeliveryclient.consumer;

import com.modus.edeliveryclient.exception.EDeliveryException;
import com.modus.edeliveryclient.models.Authorization;
import com.modus.edeliveryclient.models.Participant;
import com.modus.edeliveryclient.models.ResponseMessage;
import com.modus.edeliveryclient.models.ResponseModel;
import com.modus.edeliveryclient.serialize.Serializer;
import com.modus.edeliveryclient.serialize.TypeReference;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import javax.xml.bind.Marshaller;
import org.asynchttpclient.AsyncHttpClient;
import org.json.simple.JSONObject;
/**
 *
 * @author Pantelispanka
 */
public class SmpParticipantConsumer extends BaseConsumer {

//    private final static ObjectMapper mapper = new ObjectMapper();
    
    
    private final static String PARTICIPANT = "/api/v1/smp/participants";

    private final String participantPath;
    private String basepath;

//    private ResponseMessage rm;

    public SmpParticipantConsumer(AsyncHttpClient httpClient, Serializer serializer, String basepath) {
        super(httpClient, serializer, basepath);
        this.basepath = basepath;
        this.participantPath = createPath(basepath, PARTICIPANT);
    }

    public CompletableFuture<ResponseMessage> createParticipant(String participantIdentifierScheme, String participantIdentifierValue, Authorization auth) {
        String authorizationHeader;
        String response;
        ResponseMessage rm = new ResponseMessage();
        try {
            String authHeader = auth.getUsername() + ":" + auth.getPassword().toString();
            String authHeaderEncoded = Base64.getEncoder().encodeToString(authHeader.getBytes("utf-8"));
            authorizationHeader = "Basic " + authHeaderEncoded;
        } catch (UnsupportedEncodingException e) {
            throw new EDeliveryException(e);
        }
        JSONObject participant = new JSONObject();
        participant.put("ParticipantIdentifierScheme", participantIdentifierScheme);
        participant.put("ParticipantIdentifierValue", participantIdentifierValue);
        return httpClient.preparePost(participantPath)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", authorizationHeader)
                .setBody(participant.toString())
                .execute()
                .toCompletableFuture()
                .exceptionally(t -> {
                    throw new EDeliveryException(t);
                })
                .thenApply(resp -> {
                    int status = resp.getStatusCode();
                    
                    switch (status) {
                        case 201:
                            rm.setStatus(201);
                            rm.setMessage("Participant created");
                            break;
                        case 400:
                            rm.setStatus(400);
                            rm.setMessage("Bad Request");
                            break;
                        case 401:
                            rm.setStatus(401);
                            rm.setMessage(resp.getResponseBody());
                            break;
                        case 409:
                            rm.setStatus(409);
                            rm.setMessage("Confilct. The participant already exists");
                            break;
                        case 406:
                            rm.setStatus(406);
                            rm.setMessage("Not Acceptable: The payload is not of the correct format.");
                            break;
                    }
                    return rm;
                });

//                    try {
//                        ResponseModel<Map> rm = serializer.parse(resp.getResponseBodyAsStream(), new TypeReference<ResponseModel<Map>>() {
//                        });
//                        if (resp.getStatusCode() >= 400 || !rm.isSuccess()) {
//                            throw new EDeliveryException(resp.getStatusCode(), rm.getProcessMessage());
//                        };
//                    } catch (Exception e) {
//                        throw e;
//                    }
//                    String response = "Participant Created";
    }

    public CompletableFuture<ResponseMessage> deleteParticipantId(String participantIdentifierScheme, String participantIdentifierValue, Authorization auth) {
        String authorizationHeader;
        ResponseMessage rm = new ResponseMessage();
        try {
            String authHeader = auth.getUsername().toString() + ":" + auth.getPassword().toString();
            String authHeaderEncoded = Base64.getEncoder().encodeToString(authHeader.getBytes("utf-8"));
            authorizationHeader = "Basic " + authHeaderEncoded;
        } catch (UnsupportedEncodingException e) {
            throw new EDeliveryException(e);
        }
        String participantToDelete = participantPath + "/" + participantIdentifierScheme + "::" + participantIdentifierValue;
        return httpClient.prepareDelete(participantToDelete)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", authorizationHeader)
                .execute()
                .toCompletableFuture()
                .exceptionally(fn -> {
                    throw new EDeliveryException(fn);
                })
                .thenApply(resp -> {
                    int status = resp.getStatusCode();
                    switch (status) {
                        case 200:
                            rm.setStatus(200);
                            rm.setMessage("OK: The participant ID has been deleted from the SMP");
                            break;
                        case 401:
                            rm.setStatus(401);
                            rm.setMessage(resp.getResponseBody());
                            break;
                        case 404:
                            rm.setStatus(404);
                            rm.setMessage("The Participant does not exist");
                            break;
                    }
                    return rm;
                });

    }

}
