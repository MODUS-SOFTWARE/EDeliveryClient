/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modus.edeliveryclient;

import com.modus.edeliveryclient.consumer.SbdConsumer;
import com.modus.edeliveryclient.consumer.SmpParticipantConsumer;
import com.modus.edeliveryclient.serialize.Serializer;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;

/**
 *
 * @author Pantelispanka
 */
public class EDeliveryClientFactory {
    
    public EDeliveryClient create(String basepath, Serializer serializer){
        
        AsyncHttpClient httpClient = new DefaultAsyncHttpClient();
        return new EDeliveryClientImplementation(httpClient,
                serializer,
                new SmpParticipantConsumer(httpClient, serializer,basepath),
                new SbdConsumer(httpClient,serializer, basepath));
        
    }
    
    
}
