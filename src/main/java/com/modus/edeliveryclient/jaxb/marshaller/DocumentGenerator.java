/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modus.edeliveryclient.jaxb.marshaller;

import com.modus.edeliveryclient.jaxb.egif_core_component.AttachmentType;
import com.modus.edeliveryclient.jaxb.egif_core_component.BinaryObjectType;
import com.modus.edeliveryclient.jaxb.egif_core_component.DocumentType;
import com.modus.edeliveryclient.jaxb.egif_core_component.EgifCoreComponentsFactory;
import com.modus.edeliveryclient.jaxb.egif_core_component.EventType;
import com.modus.edeliveryclient.jaxb.egif_core_component.ExaminationResultType;
import com.modus.edeliveryclient.jaxb.egif_core_component.HierarchicalStructureType;
import com.modus.edeliveryclient.jaxb.egif_core_component.OrganizationType;
import com.modus.edeliveryclient.jaxb.egif_core_component.PartyType;
import com.modus.edeliveryclient.jaxb.egif_core_component.PaymentTermsType;
import com.modus.edeliveryclient.jaxb.egif_core_component.PaymentType;
import com.modus.edeliveryclient.jaxb.egif_core_component.PeriodType;
import com.modus.edeliveryclient.jaxb.egif_core_component.PersonType;
import com.modus.edeliveryclient.jaxb.egif_core_component.ServiceChargeType;
import com.modus.edeliveryclient.jaxb.egif_core_component.StatusType;
import com.modus.edeliveryclient.jaxb.egif_core_component.TextType;
import com.modus.edeliveryclient.jaxb.egif_core_component.UsageConditionType;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.GregorianCalendar;
import java.util.List;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author Pantelispanka
 */
public class DocumentGenerator {

    EgifCoreComponentsFactory gifFactory = new EgifCoreComponentsFactory();
    DocumentType docType = new EgifCoreComponentsFactory().createDocumentType();

    public DocumentType generateDocTypeFromClasses(PeriodType period, OrganizationType organization, PartyType party,
             PersonType person, ServiceChargeType chargeType, TextType text,
             HierarchicalStructureType stracture, Boolean copyIndicator, BigDecimal copyNumeric,
             PeriodType effectivePeriod, BigDecimal identifiedNumeric,
             XMLGregorianCalendar issueDateTime, OrganizationType issuerOrganization, PartyType issuerParty,
             Boolean multipleReferencesIndicator,TextType purpose,XMLGregorianCalendar receiptDateTime,
             XMLGregorianCalendar referenceDateTime,ExaminationResultType examinationResult,
             PaymentType payment,PaymentTermsType paymentTerms,StatusType referenceStatus,
             EventType relatedEvent,UsageConditionType usageConditionType,XMLGregorianCalendar submissionDateTime) throws DatatypeConfigurationException {

        
        
        docType.setAcceptablePeriod(period);
        docType.setAddresseeOrganization(organization);
        docType.setAddresseeParty(party);
        docType.setAddresseePerson(person);
        docType.setApplicableServiceCharge(chargeType);
        docType.setApplicantParty(party);
        docType.setApplicantPerson(person);
        docType.setContent(text);
        docType.setContentHierarchicalStructure(stracture);
        docType.setCopyIndicator(copyIndicator);
        docType.setCopyNumeric(copyNumeric);
        LocalDate localDate = LocalDate.now();
        GregorianCalendar gcal = GregorianCalendar.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        XMLGregorianCalendar xdate = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
        docType.setCreationDateTime(xdate);
        docType.setEffectivePeriod(effectivePeriod);
        docType.setIdentifiedNumeric(identifiedNumeric);
        docType.setIssueDateTime(issueDateTime);
        docType.setIssuerOrganization(issuerOrganization);
        docType.setIssuerParty(issuerParty);
        docType.setMultipleReferencesIndicator(multipleReferencesIndicator);
        docType.setPurpose(purpose);
        docType.setReceiptDateTime(receiptDateTime);
        docType.setReferenceDateTime(referenceDateTime);
        docType.setReferenceExaminationResult(examinationResult);
        docType.setReferencePayment(payment);
        docType.setReferencePaymentTerms(paymentTerms);
        docType.setReferenceStatus(referenceStatus);
        docType.setRelatedEvent(relatedEvent);
        docType.setRelatedUsageCondition(usageConditionType);
        docType.setSubmissionDateTime(submissionDateTime);

        return docType;
    }

}
