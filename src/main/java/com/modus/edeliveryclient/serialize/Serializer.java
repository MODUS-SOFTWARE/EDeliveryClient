/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modus.edeliveryclient.serialize;

import java.io.Closeable;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;

/**
 *
 * @author Pantelispanka
 * @author AG
 */
public interface Serializer extends Closeable{
    
    public boolean isClosed();

    public void write(Object entity, OutputStream out);

    public void write(Object entity, Writer writer);

    public String write(Object entity);

    public <T> T parse(String content, Class<T> valueType);

    public <T> T parse(InputStream src, Class<T> valueType);

    public <T> T parse(InputStream src, TypeReference<T> valueType);
    
    
}
