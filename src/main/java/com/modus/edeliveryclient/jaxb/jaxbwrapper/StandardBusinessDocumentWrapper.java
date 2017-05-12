/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modus.edeliveryclient.jaxb.jaxbwrapper;



import com.modus.edeliveryclient.jaxb.standardbusinessdocument.StandardBusinessDocumentHeader;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import org.w3c.dom.Element;


/**
 * <p>Java class for StandardBusinessDocument complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="StandardBusinessDocument">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.unece.org/cefact/namespaces/StandardBusinessDocumentHeader}StandardBusinessDocumentHeader" minOccurs="0"/>
 *         &lt;any processContents='lax' namespace='##other'/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StandardBusinessDocument", propOrder = {
    "StandardBusinessDocumentHeader",
    "any"
})
@XmlRootElement(name = "StandardBusinessDocument")
@XmlSeeAlso({AttachmentTypeHelper.class})
public class StandardBusinessDocumentWrapper {

    @XmlElement(name = "StandardBusinessDocumentHeader")
    protected StandardBusinessDocumentHeader standardBusinessDocumentHeader;
    @XmlAnyElement(lax = true)
    protected Object any;

    /**
     * Gets the value of the standardBusinessDocumentHeader property.
     * 
     * @return
     *     possible object is
     *     {@link StandardBusinessDocumentHeader }
     *     
     */
    public StandardBusinessDocumentHeader getStandardBusinessDocumentHeader() {
        return standardBusinessDocumentHeader;
    }

    /**
     * Sets the value of the standardBusinessDocumentHeader property.
     * 
     * @param value
     *     allowed object is
     *     {@link StandardBusinessDocumentHeader }
     *     
     */
    public void setStandardBusinessDocumentHeader(StandardBusinessDocumentHeader value) {
        this.standardBusinessDocumentHeader = value;
    }

    /**
     * Gets the value of the any property.
     * 
     * @return
     *     possible object is
     *     {@link Element }
     *     {@link Object }
     *     
     */
    public Object getAny() {
        return any;
    }

    /**
     * Sets the value of the any property.
     * 
     * @param value
     *     allowed object is
     *     {@link Element }
     *     {@link Object }
     *     
     */
    public void setAny(Object value) {
        this.any = value;
    }

}
