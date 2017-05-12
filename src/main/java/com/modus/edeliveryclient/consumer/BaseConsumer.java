/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modus.edeliveryclient.consumer;


import com.modus.edeliveryclient.exception.EDeliveryException;
import com.modus.edeliveryclient.serialize.Serializer;
import com.modus.edeliveryclient.serialize.TypeReference;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.SequenceInputStream;
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
public abstract class BaseConsumer implements Closeable{
    
    protected final AsyncHttpClient httpClient;
    protected final Serializer serializer;
    protected String basepath;
//    protected final Marshaller marshaller;
    
    public BaseConsumer(AsyncHttpClient httpClient, Serializer serializer, String basepath){
        this.httpClient = httpClient;
        this.serializer = serializer;
        this.basepath = basepath;
//        this.marshaller = marshaller;
    }
    
    
//      public BaseConsumer(AsyncHttpClient httpClient, Marshaller marshaller, String basepath){
//        this.basepath = basepath;
//        this.marshaller = marshaller;
//        this.basepath = basepath;
//    }
    
    
    
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
