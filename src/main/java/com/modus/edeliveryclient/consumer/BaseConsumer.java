/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modus.edeliveryclient.consumer;

import com.modus.edeliveryclient.exception.EDeliveryException;
import com.modus.edeliveryclient.models.Authorization;
import com.modus.edeliveryclient.serialize.Serializer;
import com.modus.edeliveryclient.serialize.TypeReference;
import com.modus.edeliveryclient.signings.ISignatures;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.StringJoiner;
import java.util.concurrent.CompletableFuture;
import javax.xml.bind.Marshaller;
import org.asynchttpclient.AsyncHandler;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.BoundRequestBuilder;
import org.asynchttpclient.HttpResponseBodyPart;
import org.asynchttpclient.HttpResponseHeaders;
import org.asynchttpclient.HttpResponseStatus;

/**
 *
 * @author Pantelispanka
 */
public abstract class BaseConsumer implements Closeable {

    protected final AsyncHttpClient httpClient;
    protected final Serializer serializer;
    protected String basepath;
    protected final ISignatures signatures;
//    protected final Marshaller marshaller;

    public BaseConsumer(AsyncHttpClient httpClient, Serializer serializer, String basepath, ISignatures signatures) {
        this.httpClient = httpClient;
        this.serializer = serializer;
        this.basepath = basepath;
        this.signatures = signatures;
//        this.marshaller = marshaller;
    }

    public <T> CompletableFuture<T> get(BoundRequestBuilder builder, TypeReference<T> c) {
        return builder.execute(new AsyncHandler<T>() {

            private InputStream sis;
            io.netty.handler.codec.http.HttpHeaders headers;

            @Override
            public AsyncHandler.State onStatusReceived(HttpResponseStatus status) throws Exception {
                int statusCode = status.getStatusCode();
//                if (statusCode >= 400) {
//                    return AsyncHandler.State.ABORT;
//                }
                return AsyncHandler.State.CONTINUE;
            }

            @Override
            public AsyncHandler.State onHeadersReceived(HttpResponseHeaders h) throws Exception {
                headers = h.getHeaders();
                return AsyncHandler.State.CONTINUE;
            }

            @Override
            public T onCompleted() throws Exception {
                return serializer.parse(sis, c);
            }

            @Override
            public void onThrowable(Throwable t) {
                throw new EDeliveryException(t);
            }

            @Override
            public AsyncHandler.State onBodyPartReceived(HttpResponseBodyPart httpResponseBodyPart) throws Exception {
                if (sis == null) {
                    sis = new ByteArrayInputStream(httpResponseBodyPart.getBodyPartBytes(), 0, httpResponseBodyPart.length());
                } else {
                    sis = new SequenceInputStream(sis, new ByteArrayInputStream(httpResponseBodyPart.getBodyPartBytes(), 0, httpResponseBodyPart.length()));
                }
                return AsyncHandler.State.CONTINUE;
            }
        }).toCompletableFuture();
    }

    private <T> CompletableFuture<T> post(BoundRequestBuilder builder, TypeReference<T> c) {
        return builder.execute(new AsyncHandler<T>() {

            private InputStream sis;
            io.netty.handler.codec.http.HttpHeaders headers;

            @Override
            public AsyncHandler.State onStatusReceived(HttpResponseStatus status) throws Exception {
                int statusCode = status.getStatusCode();
//                if (statusCode >= 400) {
//                    return AsyncHandler.State.ABORT;
//                }
                return AsyncHandler.State.CONTINUE;
            }

            @Override
            public AsyncHandler.State onHeadersReceived(HttpResponseHeaders h) throws Exception {
                headers = h.getHeaders();
                return AsyncHandler.State.CONTINUE;
            }

            @Override
            public T onCompleted() throws Exception {
                return serializer.parse(sis, c);
            }

            @Override
            public void onThrowable(Throwable t) {
                throw new EDeliveryException(t);
            }

            @Override
            public AsyncHandler.State onBodyPartReceived(HttpResponseBodyPart httpResponseBodyPart) throws Exception {
                if (sis == null) {
                    sis = new ByteArrayInputStream(httpResponseBodyPart.getBodyPartBytes(), 0, httpResponseBodyPart.length());
                } else {
                    sis = new SequenceInputStream(sis, new ByteArrayInputStream(httpResponseBodyPart.getBodyPartBytes(), 0, httpResponseBodyPart.length()));
                }
                return AsyncHandler.State.CONTINUE;
            }
        }).toCompletableFuture();
    }

    protected final String createPath(String... paths) {
        StringJoiner joiner = new StringJoiner("/");
        for (String path : paths) {
            joiner.add(path.substring(0 + (path.startsWith("/") ? 1 : 0),
                    path.length() - (path.endsWith("/") ? 1 : 0))
            );
        }
        return joiner.toString();
    }

    public <T> CompletableFuture<T> get(String path, TypeReference<T> c, String handle) {

        return get(httpClient
                .prepareGet(path)
                .addHeader("Accept", "application/json")
                .addHeader("Cookie", handle),
                c
        );
    }

//    public <T> CompletableFuture<T> get(String path, TypeReference<T> c, Authorization auth) {
//
//        String authorizationHeader;
//
//        try {
//            String authHeader = auth.getUsername().toString() + ":" + auth.getPassword().toString();
//            String authHeaderEncoded = Base64.getEncoder().encodeToString(authHeader.getBytes("utf-8"));
//            authorizationHeader = "Basic " + authHeaderEncoded;
//        } catch (UnsupportedEncodingException e) {
//            throw new EDeliveryException(e);
//        }
//
//        return get(httpClient
//                .prepareGet(path)
//                .addHeader("Accept", "application/json")
//                .addHeader("Authorization", authorizationHeader),
//                c
//        );
//    }

    @Override
    public void close() throws IOException {
        if (!this.httpClient.isClosed()) {
            this.httpClient.close();
        }
        if (!this.serializer.isClosed()) {
            this.serializer.close();
        }
    }

}
