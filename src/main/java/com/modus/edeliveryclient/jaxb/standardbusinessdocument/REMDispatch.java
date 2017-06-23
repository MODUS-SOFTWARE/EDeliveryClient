/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modus.edeliveryclient.jaxb.standardbusinessdocument;

import eu.noble.rem.jaxb.despatch.REMDispatchType;
import eu.noble.rem.jaxb.xmldsig.SignatureType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 *
 * @author Pantelispanka
 */
@XmlRootElement(name = "REMDispatch")
@XmlAccessorType(XmlAccessType.FIELD)
//@XmlSeeAlso({AssertionType.class})
public class REMDispatch {

//    @XmlElement(name = "REMDispatchValue")
    private REMDispatchType remType;

    
    @XmlElement(name = "Signature")
    private SignatureType signature;

    
    public REMDispatchType getRemType() {
        return remType;
    }

    public void setRemType(REMDispatchType remType) {
        this.remType = remType;
    }

    public SignatureType getSignature() {
        return signature;
    }

    public void setSignature(SignatureType signature) {
        this.signature = signature;
    }

}
