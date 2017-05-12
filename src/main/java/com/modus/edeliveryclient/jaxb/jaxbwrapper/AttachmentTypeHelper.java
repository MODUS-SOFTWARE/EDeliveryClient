/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modus.edeliveryclient.jaxb.jaxbwrapper;

import com.modus.edeliveryclient.jaxb.egif_core_component.AttachmentType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Pantelispanka
 */
@XmlRootElement (name = "AttachmentElementHelper")
//@XmlAccessorType(XmlAccessType.FIELD)
public class AttachmentTypeHelper {
    
    private AttachmentType attachment;
    
    public AttachmentTypeHelper(){}
    
    @XmlElement(name = "Attachment")
    public AttachmentType getAttachment() {
        return attachment;
    }

    public void setAttachment(AttachmentType attachment) {
        this.attachment = attachment;
    }
    
    
}
