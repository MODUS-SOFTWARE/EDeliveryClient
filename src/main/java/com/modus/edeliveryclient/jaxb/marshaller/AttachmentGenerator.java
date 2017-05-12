/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modus.edeliveryclient.jaxb.marshaller;

import com.modus.edeliveryclient.jaxb.egif_core_component.AttachmentType;
import com.modus.edeliveryclient.jaxb.egif_core_component.BinaryObjectType;
import com.modus.edeliveryclient.jaxb.egif_core_component.CodeType;
import com.modus.edeliveryclient.jaxb.egif_core_component.DocumentType;
import com.modus.edeliveryclient.jaxb.egif_core_component.EgifCoreComponentsFactory;
import com.modus.edeliveryclient.jaxb.egif_core_component.NameType;
import com.modus.edeliveryclient.jaxb.egif_core_component.PictureType;
import com.modus.edeliveryclient.jaxb.egif_core_component.TextType;

/**
 *
 * @author Pantelispanka
 */
public class AttachmentGenerator {

    private AttachmentType attachment;
    private BinaryObjectType binaryObj;
    private NameType nameType;
    private PictureType pictureType;
    private DocumentType documentType;
    
    private EgifCoreComponentsFactory attFact = new EgifCoreComponentsFactory();
    
    
    
    public AttachmentGenerator(){}
    
    
    
    public NameType createNameType(String languageCode, String languageValue){
        nameType = attFact.createNameType();
        nameType.setLanguageCode(languageCode);
        nameType.setValue(languageValue);
        return nameType;
    }
    
    public PictureType createPictureType(){
        pictureType = attFact.createPictureType();
        return pictureType;
    }
    
    
    public DocumentType createDocumentType(AttachmentType attachment){
        documentType = attFact.createDocumentType();
        documentType.getEmbeddedAttachment().add(attachment);
        return documentType;
    }
    
    
    public AttachmentType attachmentTypeGenerator(BinaryObjectType embeddedBinObj,
            NameType nameType, PictureType picture, DocumentType referenceDocument
            ,CodeType statusCode,TextType uriText ){
        
        binaryObj = attFact.createBinaryObjectType();
        
        
        
        attachment = attFact.createAttachmentType();
        attachment.getEmbeddedBinaryObject().add(embeddedBinObj);
        attachment.getName().add(nameType);
        attachment.getEmbeddedPicture().add(picture);
        attachment.getReferenceDocument().add(referenceDocument);
        attachment.getStatusCode().add(statusCode);
        attachment.getURI().add(uriText);
        return attachment;
        
    }

    
}
