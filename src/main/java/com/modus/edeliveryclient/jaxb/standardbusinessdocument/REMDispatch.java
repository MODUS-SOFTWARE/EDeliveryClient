/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modus.edeliveryclient.jaxb.standardbusinessdocument;

import eu.noble.rem.jaxb.despatch.REMDispatchType;
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
    
    
    
//    @XmlElement(name = "MsgMetaData")
//    private org.etsi.uri._02640.soapbinding.v1_.MsgMetaData messageMeta;
//
//    @XmlElement(name = "REMEvidence")
//    private org.etsi.uri._02640.v2_.REMEvidenceType remEvidence;
//
//    @XmlElement(name = "Assertion")
//    private AssertionType assertion;
//
//    @XmlElement(name = "Signature")
//    private SignatureType signType;
//
//    @XmlElement(name = "MessageDetails")
//    private MessageDetailsType mdt;
//
//    @XmlElement(name = "SignatureValue")
//    private org.w3._2000._09.xmldsig_.SignatureValueType dataType;
//
//    @XmlElement(name = "SignatureValue")
//    private org.w3._2000._09.xmldsig_.SignatureValueType signValue;
//
//    public MsgMetaData getMessageMeta() {
//        return messageMeta;
//    }
//
//    public void setMessageMeta(MsgMetaData messageMeta) {
//        this.messageMeta = messageMeta;
//    }
//
//    public SignatureValueType getSignValue() {
//        return signValue;
//    }
//
//    public void setSignValue(SignatureValueType signValue) {
//        this.signValue = signValue;
//    }
//
//    public SignatureValueType getDataType() {
//        return dataType;
//    }
//
//    public void setDataType(SignatureValueType dataType) {
//        this.dataType = dataType;
//    }
//
//
//    public MessageDetailsType getMdt() {
//        return mdt;
//    }
//
//    public void setMdt(MessageDetailsType mdt) {
//        this.mdt = mdt;
//    }
//
//    public REMEvidenceType getRemEvidence() {
//        return remEvidence;
//    }
//
//    public void setRemEvidence(REMEvidenceType remEvidence) {
//        this.remEvidence = remEvidence;
//    }
//
//    public SignatureType getSignType() {
//        return signType;
//    }
//
//    public void setSignType(SignatureType signType) {
//        this.signType = signType;
//    }
//
//    public AssertionType getAssertion() {
//        return assertion;
//    }
//
//    public void setAssertion(AssertionType assertion) {
//        this.assertion = assertion;
//    }

    public REMDispatchType getRemType() {
        return remType;
    }

    public void setRemType(REMDispatchType remType) {
        this.remType = remType;
    }

}
