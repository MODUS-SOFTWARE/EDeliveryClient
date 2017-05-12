/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modus.edeliveryclient.jaxb.marshaller;

import com.modus.edeliveryclient.jaxb.standardbusinessdocument.StandardBusinessDocumentHeader;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;



/**
 *
 * @author Pantelispanka
 * @author AG
 */
public class StandardBusinessDocumentHeaderMarshaller {
   
    
    public JAXBContext DocumentHeaderMarshaller(StandardBusinessDocumentHeader sbd) throws PropertyException, JAXBException{
        
            JAXBContext jaxbContext = JAXBContext.newInstance(StandardBusinessDocumentHeader.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            return jaxbContext;
            
    }
     
}
