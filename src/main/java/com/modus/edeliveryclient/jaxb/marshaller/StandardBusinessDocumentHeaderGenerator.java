/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modus.edeliveryclient.jaxb.marshaller;

import com.modus.edeliveryclient.jaxb.standardbusinessdocument.SBDHFactory;
import com.modus.edeliveryclient.jaxb.standardbusinessdocument.BusinessScope;
import com.modus.edeliveryclient.jaxb.standardbusinessdocument.DocumentIdentification;
import com.modus.edeliveryclient.jaxb.standardbusinessdocument.Manifest;
import com.modus.edeliveryclient.jaxb.standardbusinessdocument.ManifestItem;
import com.modus.edeliveryclient.jaxb.standardbusinessdocument.Partner;
import com.modus.edeliveryclient.jaxb.standardbusinessdocument.PartnerIdentification;
import com.modus.edeliveryclient.jaxb.standardbusinessdocument.Scope;
import com.modus.edeliveryclient.jaxb.standardbusinessdocument.StandardBusinessDocumentHeader;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.ZoneId;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author Pantelispanka
 * @author AG
 */
public class StandardBusinessDocumentHeaderGenerator {

//    StandardBusinessDocumentWrapper businessDocument = new StandardBusinessDocumentWrapper();
    SBDHFactory headerFactory = new SBDHFactory();

    public StandardBusinessDocumentHeader generateDocumentHeaderfromValues(float headerVersion,
            String participantIdentifierSenderScheme, String participantIdentifierSenderValue,
            String participantIdentifierReceiverScheme, String participantIdentifierReceiverValue,
            String documentIdStandard, int docTypeVersion,
            String documentInstanceIdentifier, String documentType,
             List<Scope> scopes, String manifestDescr, String manifestLanguage, String maniTypeQualCode,
             String UniformResourceIdentifier) throws DatatypeConfigurationException {

        StandardBusinessDocumentHeader header = headerFactory.createStandardBusinessDocumentHeader();

        /**
         * Sets the header Version
         *
         */
        String hVersion = Float.toString(headerVersion);
        header.setHeaderVersion(hVersion);

        /**
         * Creates and places sender
         *
         */
        Partner sender = new Partner();
        PartnerIdentification senderId = new PartnerIdentification();
        senderId.setValue(participantIdentifierSenderValue);
        senderId.setAuthority(participantIdentifierSenderScheme);
        sender.setIdentifier(senderId);
        header.getSender().add(sender);

        /**
         * Creates and places receiver
         *
         */
        Partner receiver = new Partner();
        PartnerIdentification receiverId = new PartnerIdentification();
        receiverId.setAuthority(participantIdentifierReceiverScheme);
        receiverId.setValue(participantIdentifierReceiverValue);
        receiver.setIdentifier(receiverId);
        header.getReceiver().add(receiver);

        /**
         * Creates and places Document Identification
         *
         */
        DocumentIdentification docInf = new DocumentIdentification();
        docInf.setStandard(documentIdStandard);
//        String typeVersion = Integer.toString(docTypeVersion);
        String typeVersion = Integer.toString(docTypeVersion);
        docInf.setTypeVersion(typeVersion);
        docInf.setInstanceIdentifier(documentInstanceIdentifier);
        docInf.setType(documentType);
        LocalDate localDate = LocalDate.now();
        GregorianCalendar gcal = GregorianCalendar.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        XMLGregorianCalendar xdate = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
        docInf.setCreationDateAndTime(xdate);
        header.setDocumentIdentification(docInf);

        ManifestItem mItem = new ManifestItem();
        mItem.setDescription(manifestDescr);
        mItem.setLanguageCode(manifestLanguage);
        mItem.setMimeTypeQualifierCode(maniTypeQualCode);
        mItem.setUniformResourceIdentifier(UniformResourceIdentifier);

        Manifest manifest = new Manifest();
        manifest.setNumberOfItems(BigInteger.ONE);
        manifest.getManifestItem().add(mItem);
        
        header.setManifest(manifest);
//        manifest.getManifestItem()

        /**
         * Creates and places business scope
         */
        BusinessScope bscope = new BusinessScope();
        header.setBusinessScope(bscope);
        
        for (Iterator<Scope> it = scopes.iterator(); it.hasNext();) {
            Scope businessScope = it.next();
//            Scope scope = new Scope();
//            BusinessScope bscope = new BusinessScope();
//            scope.setInstanceIdentifier(businessScope.getInstanceIdentifier());
//            scope.setType(businessScope.getType());
//            bscope.getScope().add(scope);
//            header.setBusinessScope(bscope);
            header.getBusinessScope().getScope().add(businessScope);
        }

        return header;

    }

//    public StandardBusinessDocumentHeader generateDocumentHeaderfromClass(){
//        
//        
//        
//        
//        
//    }
}
