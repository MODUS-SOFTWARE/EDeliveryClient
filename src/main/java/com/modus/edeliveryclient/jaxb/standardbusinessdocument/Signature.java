///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.modus.edeliveryclient.jaxb.standardbusinessdocument;
//
//import javax.xml.bind.annotation.XmlElement;
//import javax.xml.bind.annotation.XmlRootElement;
//import org.w3._2000._09.xmldsig_.KeyInfoType;
//import org.w3._2000._09.xmldsig_.SignatureValueType;
//import org.w3._2000._09.xmldsig_.SignedInfoType;
//
///**
// *
// * @author Pantelispanka
// */
//
//@XmlRootElement(name = "Signature")
//public class Signature {
//    
//     @XmlElement(name = "SignatureValue")
//    private org.w3._2000._09.xmldsig_.SignatureValueType dataType;
//
//    @XmlElement(name = "SignedInfo")
//    private org.w3._2000._09.xmldsig_.SignedInfoType signInfo;
//    
//    @XmlElement(name = "KeyInfo")
//    private org.w3._2000._09.xmldsig_.KeyInfoType keyInfo;
//
//    public SignatureValueType getDataType() {
//        return dataType;
//    }
//
//    public void setDataType(SignatureValueType dataType) {
//        this.dataType = dataType;
//    }
//
//    public SignedInfoType getSignInfo() {
//        return signInfo;
//    }
//
//    public void setSignInfo(SignedInfoType signInfo) {
//        this.signInfo = signInfo;
//    }
//
//    public KeyInfoType getKeyInfo() {
//        return keyInfo;
//    }
//
//    public void setKeyInfo(KeyInfoType keyInfo) {
//        this.keyInfo = keyInfo;
//    }
//    
//    
//}
