/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modus.edeliveryclient.jaxb.standardbusinessdocument;

import com.modus.edeliveryclient.jaxb.egif_core_component.SignatureType;

import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 * @author Pantelispanka
 */
@XmlRootElement(name = "Signature")
public class Signature {

    private SignatureType signature;

    public SignatureType getSignature() {
        return signature;
    }

    public void setSignature(SignatureType signature) {
        this.signature = signature;
    }

}
