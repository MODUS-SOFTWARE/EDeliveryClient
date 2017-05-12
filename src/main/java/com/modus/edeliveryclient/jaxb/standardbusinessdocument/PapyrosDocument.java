/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modus.edeliveryclient.jaxb.standardbusinessdocument;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Pantelispanka
 */
@XmlRootElement
public class PapyrosDocument {

    private String format;
    private String actualDoc;

    public String getFormat() {
        return format;
    }

    @XmlAttribute
    public void setFormat(String format) {
        this.format = format;
    }

    public String getActualDoc() {
        return actualDoc;
    }

    @XmlElement
    public void setActualDoc(String actualDoc) {
        this.actualDoc = actualDoc;
    }

}
