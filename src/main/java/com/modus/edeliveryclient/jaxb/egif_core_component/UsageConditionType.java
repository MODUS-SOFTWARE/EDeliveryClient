//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.05.03 at 01:28:43 PM EEST 
//


package com.modus.edeliveryclient.jaxb.egif_core_component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UsageConditionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UsageConditionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AgeLimitation" type="{gr:gov:egif:data:standard:UnqualifiedDataType:1}TextType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ApplicableIndicator" type="{gr:gov:egif:data:standard:UnqualifiedDataType:1}IndicatorType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Description" type="{gr:gov:egif:data:standard:UnqualifiedDataType:1}TextType" minOccurs="0"/>
 *         &lt;element name="DurationMeasure" type="{gr:gov:egif:data:standard:UnqualifiedDataType:1}MeasureType" minOccurs="0"/>
 *         &lt;element name="GenderLimitation" type="{gr:gov:egif:data:standard:UnqualifiedDataType:1}TextType" minOccurs="0"/>
 *         &lt;element name="ID" type="{gr:gov:egif:data:standard:UnqualifiedDataType:1}IDType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Residence" type="{gr:gov:egif:data:standard:UnqualifiedDataType:1}TextType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="FamilyLimitation" type="{gr:gov:egif:data:standard:UnqualifiedDataType:1}TextType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="AppliedAmount" type="{gr:gov:egif:data:standard:UnqualifiedDataType:1}AmountType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="IdentifiedNumeric" type="{gr:gov:egif:data:standard:UnqualifiedDataType:1}NumericType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="RequiredIndicator" type="{gr:gov:egif:data:standard:UnqualifiedDataType:1}IndicatorType" minOccurs="0"/>
 *         &lt;element name="SpecifiedOrganization" type="{gr:gov:egif:data:standard:CoreComponents:Organization}OrganizationType" minOccurs="0"/>
 *         &lt;element name="PhysicalFeature" type="{gr:gov:egif:data:standard:CoreComponents:Feature}FeatureType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UsageConditionType", namespace = "gr:gov:egif:data:standard:CoreComponents:UsageCondition", propOrder = {
    "ageLimitation",
    "applicableIndicator",
    "description",
    "durationMeasure",
    "genderLimitation",
    "id",
    "residence",
    "familyLimitation",
    "appliedAmount",
    "identifiedNumeric",
    "requiredIndicator",
    "specifiedOrganization",
    "physicalFeature"
})
public class UsageConditionType {

    @XmlElement(name = "AgeLimitation")
    protected List<TextType> ageLimitation;
    @XmlElement(name = "ApplicableIndicator", type = Boolean.class)
    protected List<Boolean> applicableIndicator;
    @XmlElement(name = "Description")
    protected TextType description;
    @XmlElement(name = "DurationMeasure")
    protected MeasureType durationMeasure;
    @XmlElement(name = "GenderLimitation")
    protected TextType genderLimitation;
    @XmlElement(name = "ID")
    protected List<IDType> id;
    @XmlElement(name = "Residence")
    protected List<TextType> residence;
    @XmlElement(name = "FamilyLimitation")
    protected List<TextType> familyLimitation;
    @XmlElement(name = "AppliedAmount")
    protected List<AmountType> appliedAmount;
    @XmlElement(name = "IdentifiedNumeric")
    protected List<BigDecimal> identifiedNumeric;
    @XmlElement(name = "RequiredIndicator")
    protected Boolean requiredIndicator;
    @XmlElement(name = "SpecifiedOrganization")
    protected OrganizationType specifiedOrganization;
    @XmlElement(name = "PhysicalFeature")
    protected FeatureType physicalFeature;

    /**
     * Gets the value of the ageLimitation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ageLimitation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAgeLimitation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TextType }
     * 
     * 
     */
    public List<TextType> getAgeLimitation() {
        if (ageLimitation == null) {
            ageLimitation = new ArrayList<TextType>();
        }
        return this.ageLimitation;
    }

    /**
     * Gets the value of the applicableIndicator property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the applicableIndicator property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getApplicableIndicator().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Boolean }
     * 
     * 
     */
    public List<Boolean> getApplicableIndicator() {
        if (applicableIndicator == null) {
            applicableIndicator = new ArrayList<Boolean>();
        }
        return this.applicableIndicator;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link TextType }
     *     
     */
    public TextType getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link TextType }
     *     
     */
    public void setDescription(TextType value) {
        this.description = value;
    }

    /**
     * Gets the value of the durationMeasure property.
     * 
     * @return
     *     possible object is
     *     {@link MeasureType }
     *     
     */
    public MeasureType getDurationMeasure() {
        return durationMeasure;
    }

    /**
     * Sets the value of the durationMeasure property.
     * 
     * @param value
     *     allowed object is
     *     {@link MeasureType }
     *     
     */
    public void setDurationMeasure(MeasureType value) {
        this.durationMeasure = value;
    }

    /**
     * Gets the value of the genderLimitation property.
     * 
     * @return
     *     possible object is
     *     {@link TextType }
     *     
     */
    public TextType getGenderLimitation() {
        return genderLimitation;
    }

    /**
     * Sets the value of the genderLimitation property.
     * 
     * @param value
     *     allowed object is
     *     {@link TextType }
     *     
     */
    public void setGenderLimitation(TextType value) {
        this.genderLimitation = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the id property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getID().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IDType }
     * 
     * 
     */
    public List<IDType> getID() {
        if (id == null) {
            id = new ArrayList<IDType>();
        }
        return this.id;
    }

    /**
     * Gets the value of the residence property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the residence property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getResidence().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TextType }
     * 
     * 
     */
    public List<TextType> getResidence() {
        if (residence == null) {
            residence = new ArrayList<TextType>();
        }
        return this.residence;
    }

    /**
     * Gets the value of the familyLimitation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the familyLimitation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFamilyLimitation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TextType }
     * 
     * 
     */
    public List<TextType> getFamilyLimitation() {
        if (familyLimitation == null) {
            familyLimitation = new ArrayList<TextType>();
        }
        return this.familyLimitation;
    }

    /**
     * Gets the value of the appliedAmount property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the appliedAmount property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAppliedAmount().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AmountType }
     * 
     * 
     */
    public List<AmountType> getAppliedAmount() {
        if (appliedAmount == null) {
            appliedAmount = new ArrayList<AmountType>();
        }
        return this.appliedAmount;
    }

    /**
     * Gets the value of the identifiedNumeric property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the identifiedNumeric property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIdentifiedNumeric().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BigDecimal }
     * 
     * 
     */
    public List<BigDecimal> getIdentifiedNumeric() {
        if (identifiedNumeric == null) {
            identifiedNumeric = new ArrayList<BigDecimal>();
        }
        return this.identifiedNumeric;
    }

    /**
     * Gets the value of the requiredIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isRequiredIndicator() {
        return requiredIndicator;
    }

    /**
     * Sets the value of the requiredIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setRequiredIndicator(Boolean value) {
        this.requiredIndicator = value;
    }

    /**
     * Gets the value of the specifiedOrganization property.
     * 
     * @return
     *     possible object is
     *     {@link OrganizationType }
     *     
     */
    public OrganizationType getSpecifiedOrganization() {
        return specifiedOrganization;
    }

    /**
     * Sets the value of the specifiedOrganization property.
     * 
     * @param value
     *     allowed object is
     *     {@link OrganizationType }
     *     
     */
    public void setSpecifiedOrganization(OrganizationType value) {
        this.specifiedOrganization = value;
    }

    /**
     * Gets the value of the physicalFeature property.
     * 
     * @return
     *     possible object is
     *     {@link FeatureType }
     *     
     */
    public FeatureType getPhysicalFeature() {
        return physicalFeature;
    }

    /**
     * Sets the value of the physicalFeature property.
     * 
     * @param value
     *     allowed object is
     *     {@link FeatureType }
     *     
     */
    public void setPhysicalFeature(FeatureType value) {
        this.physicalFeature = value;
    }

}
