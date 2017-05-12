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
 * <p>Java class for CalculationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CalculationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Formula" type="{gr:gov:egif:data:standard:UnqualifiedDataType:1}TextType" minOccurs="0"/>
 *         &lt;element name="ApplicableRate" type="{gr:gov:egif:data:standard:UnqualifiedDataType:1}RateType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Description" type="{gr:gov:egif:data:standard:UnqualifiedDataType:1}TextType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ApplicablePeriod" type="{gr:gov:egif:data:standard:CoreComponents:Period}PeriodType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CalculationType", namespace = "gr:gov:egif:data:standard:CoreComponents:Calculation", propOrder = {
    "formula",
    "applicableRate",
    "description",
    "applicablePeriod"
})
public class CalculationType {

    @XmlElement(name = "Formula")
    protected TextType formula;
    @XmlElement(name = "ApplicableRate")
    protected List<BigDecimal> applicableRate;
    @XmlElement(name = "Description")
    protected List<TextType> description;
    @XmlElement(name = "ApplicablePeriod")
    protected List<PeriodType> applicablePeriod;

    /**
     * Gets the value of the formula property.
     * 
     * @return
     *     possible object is
     *     {@link TextType }
     *     
     */
    public TextType getFormula() {
        return formula;
    }

    /**
     * Sets the value of the formula property.
     * 
     * @param value
     *     allowed object is
     *     {@link TextType }
     *     
     */
    public void setFormula(TextType value) {
        this.formula = value;
    }

    /**
     * Gets the value of the applicableRate property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the applicableRate property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getApplicableRate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BigDecimal }
     * 
     * 
     */
    public List<BigDecimal> getApplicableRate() {
        if (applicableRate == null) {
            applicableRate = new ArrayList<BigDecimal>();
        }
        return this.applicableRate;
    }

    /**
     * Gets the value of the description property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the description property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDescription().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TextType }
     * 
     * 
     */
    public List<TextType> getDescription() {
        if (description == null) {
            description = new ArrayList<TextType>();
        }
        return this.description;
    }

    /**
     * Gets the value of the applicablePeriod property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the applicablePeriod property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getApplicablePeriod().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PeriodType }
     * 
     * 
     */
    public List<PeriodType> getApplicablePeriod() {
        if (applicablePeriod == null) {
            applicablePeriod = new ArrayList<PeriodType>();
        }
        return this.applicablePeriod;
    }

}
