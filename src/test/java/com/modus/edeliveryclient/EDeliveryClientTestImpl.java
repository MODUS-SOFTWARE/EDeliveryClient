/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modus.edeliveryclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.modus.edeliveryclient.consumer.SbdConsumer;
import com.modus.edeliveryclient.consumer.SmpParticipantConsumer;
import com.modus.edeliveryclient.models.Authorization;
import com.modus.edeliveryclient.models.ResponseMessage;
import com.modus.edeliveryclient.serialize.Serializer;
import com.modus.edeliveryclient.serializer.JacksonSerializer;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 *
 * @author Pantelispanka
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EDeliveryClientTestImpl {

    private static EDeliveryClient deliveryClient;

    private final String participantIdentifierScheme = "iso6523-actorid-upis";
    private final String ParticipantIdentifierValue = "9933:test1";

    private final Authorization auth;
    private final Authorization wrongAuth;

    public EDeliveryClientTestImpl() {
        auth = new Authorization("sp1", "sp1");
        wrongAuth = new Authorization("wrong", "wrong");
    }

    @BeforeClass
    public static void setUpClass() {
        Serializer serializer = new JacksonSerializer(new ObjectMapper());
        AsyncHttpClient httpClient = new DefaultAsyncHttpClient();
        String basepath = "http://192.168.20.10:8080/APREST";
        deliveryClient = new EDeliveryClientImplementation(httpClient,
                 serializer,
                 new SmpParticipantConsumer(httpClient, serializer, basepath),
                 new SbdConsumer(httpClient, serializer, basepath));
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void atestParticipantConsumerCreation() throws InterruptedException, ExecutionException {
        System.out.println("testing the creation of participant");
        CompletableFuture<ResponseMessage> result = deliveryClient
                .createParticipant(participantIdentifierScheme, ParticipantIdentifierValue, auth);
        assertTrue(result.get().getStatus() == 201);
        System.out.println(result.get().getMessage());
    }

    @Test
    public void atestParticipantConsumerConfict() throws InterruptedException, ExecutionException {
        System.out.println("testing the creation of participant");
        CompletableFuture<ResponseMessage> result = deliveryClient
                .createParticipant(participantIdentifierScheme, ParticipantIdentifierValue, auth);
        assertTrue(result.get().getStatus() == 409);
        System.out.println(result.get().getMessage());
    }

    @Test
    public void ctestDeleteParticipantShouldNotBeFound() throws InterruptedException, ExecutionException {
        System.out.println("Trying to delete a non existend participant");
        CompletableFuture<ResponseMessage> result = deliveryClient
                .deleteParticipantId("testToNot", "NotFound", auth);
        assertTrue(result.get().getStatus() == 404);
        System.out.println(result.get().getMessage());
    }

    @Test
    public void dtestDeleteParticipantShouldNotBeAuthorized() throws InterruptedException, ExecutionException {
        System.out.println("delete with wrong credentials");
        CompletableFuture<ResponseMessage> result = deliveryClient
                .deleteParticipantId("Test", "Auth", wrongAuth);
        assertTrue(result.get().getStatus() == 401);
        System.out.println(result.get().getMessage());
    }

    @Test
    public void etestDeleteParticipantShouldBeDeleted() throws InterruptedException, ExecutionException {
        System.out.println("Deleting Participant");
        CompletableFuture<ResponseMessage> result = deliveryClient
                .deleteParticipantId(participantIdentifierScheme, ParticipantIdentifierValue, auth);
        assertTrue(result.get().getStatus() == 200);
        System.out.println(result.get().getMessage());
    }

}
