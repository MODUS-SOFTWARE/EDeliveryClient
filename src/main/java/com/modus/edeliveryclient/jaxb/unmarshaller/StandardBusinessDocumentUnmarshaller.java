/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modus.edeliveryclient.jaxb.unmarshaller;

import com.modus.edeliveryclient.jaxb.standardbusinessdocument.SBDHFactory;
import com.modus.edeliveryclient.jaxb.standardbusinessdocument.StandardBusinessDocument;
import java.io.InputStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author Pantelispanka
 * @author AG
 */
public class StandardBusinessDocumentUnmarshaller {

    public StandardBusinessDocumentUnmarshaller(){}
    
    
    public StandardBusinessDocument unmarshallJaxb(InputStream responseBody) throws JAXBException {

        JAXBContext jaxbContext = JAXBContext.newInstance(StandardBusinessDocument.class, SBDHFactory.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        StandardBusinessDocument sbd = (StandardBusinessDocument) JAXBIntrospector.getValue(jaxbUnmarshaller.unmarshal(responseBody));
        return sbd;

    }

}
