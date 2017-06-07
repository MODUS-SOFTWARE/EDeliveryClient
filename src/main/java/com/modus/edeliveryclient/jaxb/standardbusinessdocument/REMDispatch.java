/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modus.edeliveryclient.jaxb.standardbusinessdocument;

import com.modus.edeliveryclient.jaxb.assertion.AdviceType;
//import com.modus.edeliveryclient.jaxb.assertion.AssertionType;
import com.modus.edeliveryclient.jaxb.assertion.AttributeStatementType;
import com.modus.edeliveryclient.jaxb.assertion.AttributeType;
import com.modus.edeliveryclient.jaxb.assertion.AudienceRestrictionType;
import com.modus.edeliveryclient.jaxb.assertion.AuthnContextType;
import com.modus.edeliveryclient.jaxb.assertion.AuthnStatementType;
import com.modus.edeliveryclient.jaxb.assertion.AuthzDecisionStatementType;
import com.modus.edeliveryclient.jaxb.assertion.BaseIDAbstractType;
import com.modus.edeliveryclient.jaxb.assertion.ConditionAbstractType;
import com.modus.edeliveryclient.jaxb.assertion.ConditionsType;
import com.modus.edeliveryclient.jaxb.assertion.EncryptedElementType;
import com.modus.edeliveryclient.jaxb.assertion.EvidenceType;
import com.modus.edeliveryclient.jaxb.assertion.NameIDType;
import com.modus.edeliveryclient.jaxb.assertion.OneTimeUseType;
import com.modus.edeliveryclient.jaxb.assertion.ProxyRestrictionType;
import com.modus.edeliveryclient.jaxb.assertion.StatementAbstractType;
import com.modus.edeliveryclient.jaxb.assertion.SubjectConfirmationDataType;
import com.modus.edeliveryclient.jaxb.assertion.SubjectConfirmationType;
import com.modus.edeliveryclient.jaxb.assertion.SubjectLocalityType;
import com.modus.edeliveryclient.jaxb.org.etsi.uri._02640.v2_.AttributedElectronicAddressType;
import com.modus.edeliveryclient.jaxb.org.etsi.uri._02640.v2_.NamesPostalAddressesType;
import com.modus.edeliveryclient.jaxb.org.etsi.uri._02640.v2_.SubmissionAcceptanceRejectionType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import oasis.names.tc.saml._2_0.assertion.ActionType;
import oasis.names.tc.saml._2_0.assertion.AssertionType;
import oasis.names.tc.saml._2_0.assertion.SubjectType;
import org.etsi.uri._02640.soapbinding.v1_.MsgMetaData;
import org.etsi.uri._02640.v2_.MessageDetailsType;
import org.etsi.uri._02640.v2_.REMEvidenceType;
import org.opensaml.saml2.core.Assertion;
import org.w3._2000._09.xmldsig_.DSAKeyValueType;
import org.w3._2000._09.xmldsig_.DigestMethodType;
import org.w3._2000._09.xmldsig_.KeyInfoType;
import org.w3._2000._09.xmldsig_.SignatureType;

import org.w3._2000._09.xmldsig_.ObjectFactory;
import org.w3._2000._09.xmldsig_.SignaturePropertyType;
import org.w3._2000._09.xmldsig_.SignatureValueType;
import org.w3._2000._09.xmldsig_.SignedInfoType;
import org.w3._2000._09.xmldsig_.X509DataType;

/**
 *
 * @author Pantelispanka
 */
@XmlRootElement(name = "REMDispatch")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({AssertionType.class})
public class REMDispatch {

//    private String format;
//    private String actualDoc;
//    @XmlElement
//    private AttributedElectronicAddressType elAddType;
//    @XmlElement(name = "NamesPostalAddresses_")
//    private NamesPostalAddressesType namePostalAddr;
//    @XmlElement(name = "SubmissionAcceptanceRejectionType_")
//    private SubmissionAcceptanceRejectionType subAcceptRejType;
//    @XmlElement(name = "Name_")
//    private String name;
//    @XmlElement(name = "AuthnContextDeclRef_")
//    private String authnContextDeclRef;
//    @XmlElement(name = "EvidenceType_")
//    private EvidenceType evidenceType;
//    @XmlElement(name = "NameIDType_")
//    private NameIDType nameIdType;
//    @XmlElement(name ="Attribute_")
//    private AttributeType attributeType;
//    @XmlElement(name = "Action_")
//    private ActionType actionType;
//    @XmlElement(name = "AuthnContext_")
//    private AuthnContextType authnContextType;
//    @XmlElement(name = "EncryptedID_")
//    private EncryptedElementType encryptedID;
//    @XmlElement(name = "EncryptedAssertion_")
//    private EncryptedElementType encryptedAssertion;
//    @XmlElement(name = "Statement_")
//    private StatementAbstractType statement;
//    @XmlElement(name = "AuthzDecisionStatement_")
//    private AuthzDecisionStatementType AuthzDecisionStatement;
//    @XmlElement(name = "BaseID_")
//    private BaseIDAbstractType BaseID;
//    @XmlElement(name = "Condition_")
//    private ConditionAbstractType condition;
//    @XmlElement(name = "EncryptedAttribute_")
//    private EncryptedElementType encryptedAttribute;
//    @XmlElement(name = "Subject_")
//    private SubjectType subject;
//    @XmlElement(name = "SubjectConfirmation_")
//    private SubjectConfirmationType sunjectConfirmation;
//    @XmlElement(name = "AudienceRestriction_")
//    private AudienceRestrictionType audienceRestriction;
//    
//    @XmlElement(name ="OneTimeUse_")
//    private OneTimeUseType oneTimeUse;
//    
//    @XmlElement(name = "AttributeValue_")
//    private Object attributeValue;
//    
//    @XmlElement(name = "Conditions_")
//    private ConditionsType conditions;
//    
//    @XmlElement(name = "SubjectLocality_")
//    private SubjectLocalityType subjectLocality;
//    
//    @XmlElement(name = "SubjectConfirmationData_")
//    private SubjectConfirmationDataType subjectConfirmationData;
//    
//    @XmlElement(name  = "attributeStatement_")
//    private AttributeStatementType attributeStatement;
//    
//    @XmlElement(name = "AssertionURIRef_")
//    private String assertionURIRef;
//    
//    @XmlElement(name = "AuthenticatingAuthority_")
//    private String authenticatingAuthority;
//    
//  
//    @XmlElement(name = "Advice_")
//    private AdviceType advice;
//    
//    @XmlElement(name = "AuthnStatement_")
//    private AuthnStatementType authnStatement;
//    
//    @XmlElement(name = "NameID_")
//    private NameIDType nameID;
//    
//    
//    @XmlElement(name = "AssertionIDRef_")
//    private String assertionIDRef;
//    
//    @XmlElement(name = "Audience_")
//    private String audience;
//    
//    @XmlElement(name = "ProxyRestriction_")
//    private ProxyRestrictionType proxyRestriction;
//    @XmlElement(name = "Assertion")
    
    
    
    
    @XmlElement(name = "MsgMetaData")
    private org.etsi.uri._02640.soapbinding.v1_.MsgMetaData messageMeta;
    
    
    @XmlElement(name = "REMEvidence")
    private org.etsi.uri._02640.v2_.REMEvidenceType remEvidence;

    @XmlElement(name = "Assertion")
    private AssertionType assertion;

    @XmlElement(name = "Signature")
    private SignatureType signType;

    @XmlElement(name = "MessageDetails")
    private MessageDetailsType mdt;

    @XmlElement(name = "SignatureValue")
    private org.w3._2000._09.xmldsig_.SignatureValueType dataType;

    @XmlElement(name = "SignedInfo")
    private org.w3._2000._09.xmldsig_.SignedInfoType signInfo;

    @XmlElement(name = "SignatureValue")
    private org.w3._2000._09.xmldsig_.SignatureValueType signValue;
    
//    @XmlElement(name = "KeyInfo")
//    private org.w3._2000._09.xmldsig_.KeyInfoType keyInfo;
//
//    @XmlElement(name = "DigestValue")
//    private org.w3._2000._09.xmldsig_.DSAKeyValueType dsakeyval;
//
//    @XmlElement(name = "DigestMethodAlgorithm")
//    private org.w3._2000._09.xmldsig_.DigestMethodType digestMethType;
//    
//    
//    @XmlElement(name = "X509Certificate")
//    private org.w3._2000._09.xmldsig_.X509DataType x509data;

    public MsgMetaData getMessageMeta() {
        return messageMeta;
    }

    public void setMessageMeta(MsgMetaData messageMeta) {
        this.messageMeta = messageMeta;
    }

    
    
    
    
    
    public SignatureValueType getSignValue() {
        return signValue;
    }

    public void setSignValue(SignatureValueType signValue) {
        this.signValue = signValue;
    }

    
    
    
//    public X509DataType getX509data() {
//        return x509data;
//    }
//
//    public void setX509data(X509DataType x509data) {
//        this.x509data = x509data;
//    }
//    
//    
//    
//    
//    
//    
//    public DSAKeyValueType getDsakeyval() {
//        return dsakeyval;
//    }
//
//    
//    
//    public void setDsakeyval(DSAKeyValueType dsakeyval) {
//        this.dsakeyval = dsakeyval;
//    }
//
//    public DigestMethodType getDigestMethType() {
//        return digestMethType;
//    }
//
//    public void setDigestMethType(DigestMethodType digestMethType) {
//        this.digestMethType = digestMethType;
//    }
//    
//    
//    
//    
//
//    public KeyInfoType getKeyInfo() {
//        return keyInfo;
//    }
//
//    public void setKeyInfo(KeyInfoType keyInfo) {
//        this.keyInfo = keyInfo;
//    }

    public SignatureValueType getDataType() {
        return dataType;
    }

    public void setDataType(SignatureValueType dataType) {
        this.dataType = dataType;
    }

    public SignedInfoType getSignInfo() {
        return signInfo;
    }

    public void setSignInfo(SignedInfoType signInfo) {
        this.signInfo = signInfo;
    }

    public MessageDetailsType getMdt() {
        return mdt;
    }

    public void setMdt(MessageDetailsType mdt) {
        this.mdt = mdt;
    }

    public REMEvidenceType getRemEvidence() {
        return remEvidence;
    }

    public void setRemEvidence(REMEvidenceType remEvidence) {
        this.remEvidence = remEvidence;
    }

    public SignatureType getSignType() {
        return signType;
    }

    public void setSignType(SignatureType signType) {
        this.signType = signType;
    }

    public AssertionType getAssertion() {
        return assertion;
    }

    public void setAssertion(AssertionType assertion) {        
        this.assertion = assertion;
    }

}
