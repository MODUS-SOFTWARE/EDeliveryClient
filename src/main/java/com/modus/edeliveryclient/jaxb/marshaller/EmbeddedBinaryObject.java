/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modus.edeliveryclient.jaxb.marshaller;

import com.modus.edeliveryclient.jaxb.egif_core_component.BinaryObjectType;
import com.modus.edeliveryclient.jaxb.egif_core_component.EgifCoreComponentsFactory;

/**
 *
 * @author Pantelispanka
 */
public class EmbeddedBinaryObject {

    private BinaryObjectType embeddedObj;

    private EgifCoreComponentsFactory attFact = new EgifCoreComponentsFactory();

    public BinaryObjectType createBinaryObject(String characterSetCode, String encodingCode,
             String filename, String format, String mimeCode,String uri, byte[] document) {
        embeddedObj = attFact.createBinaryObjectType();
        embeddedObj.setCharacterSetCode(characterSetCode);
        embeddedObj.setEncodingCode(encodingCode);
        embeddedObj.setFileName(filename);
        embeddedObj.setFormat(format);
        embeddedObj.setMimeCode(mimeCode);
        embeddedObj.setUri(uri);
        embeddedObj.setValue(document);
        return embeddedObj;
    }

}
