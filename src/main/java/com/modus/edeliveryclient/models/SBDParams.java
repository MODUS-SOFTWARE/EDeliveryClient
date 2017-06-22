package com.modus.edeliveryclient.models;

public class SBDParams {

	
	//TODO remove default values .
	private float headerVersion = 1.0f;

   
	
	private   String participantIdentifierSenderScheme = "iso6523-actorid-upis";
    private   String participantIdentifierSenderValue = "9933:test1";
    private   String participantIdentifierReceiverScheme = "iso6523-actorid-upis";
    private   String participantIdentifierReceiverValue = "9933:test1";

    private   String documentIdStandard = "urn:oasis:names:specification:ubl:schema:xsd:Invoice-2";
    private   int docTypeVersion = 1;
    private   String documentInstanceIdentifier = "627a2e9a-a146-461f-b62f-f22f5799fd55";
    private   String documentType = "Invoice";

    private   String scopeType = "DOCUMENTID";
    private   String scopeInstance="http://uri.etsi.org/02640/soapbinding/v2#::REMDispatch:2";
    private   String scopeIdentifier = "urn:oasis:names:specification:ubl:schema:xsd:Invoice-2:: Invoice##urn:www.cenbii.eu:transaction:biitrns010:ver2.0:extended:urn: www.peppol.eu:bis:peppol4a:ver2.0::2.1";
    private   String scopeType2 = "PROCESSID";
    private   String scopeInstance2="urn:cef-eDelivery.europa.eu::generalERDS:ver1.0";
    
	private   String scopeIdentifier2 = "urn:www.cenbii.eu:profile:bii04:ver2.0";

    private   String manifestDescr = "manifestDescr";
    private   String manifestLanguage = " manifestLanguage";
    private   String maniTypeQualCode = "maniTypeQualCode";
    private   String uniformResourceIdentifier = "uniformResourceIdentifier";
    public String getScopeInstance() {
		return scopeInstance;
	}
	public void setScopeInstance(String scopeInstance) {
		this.scopeInstance = scopeInstance;
	}
	public String getScopeInstance2() {
		return scopeInstance2;
	}
	public void setScopeInstance2(String scopeInstance2) {
		this.scopeInstance2 = scopeInstance2;
	}
    public float getHeaderVersion() {
		return headerVersion;
	}
	public void setHeaderVersion(float headerVersion) {
		this.headerVersion = headerVersion;
	}
    
	public String getParticipantIdentifierSenderScheme() {
		return participantIdentifierSenderScheme;
	}
	public void setParticipantIdentifierSenderScheme(String participantIdentifierSenderScheme) {
		this.participantIdentifierSenderScheme = participantIdentifierSenderScheme;
	}
	public String getParticipantIdentifierSenderValue() {
		return participantIdentifierSenderValue;
	}
	public void setParticipantIdentifierSenderValue(String participantIdentifierSenderValue) {
		this.participantIdentifierSenderValue = participantIdentifierSenderValue;
	}
	public String getParticipantIdentifierReceiverScheme() {
		return participantIdentifierReceiverScheme;
	}
	public void setParticipantIdentifierReceiverScheme(String participantIdentifierReceiverScheme) {
		this.participantIdentifierReceiverScheme = participantIdentifierReceiverScheme;
	}
	public String getParticipantIdentifierReceiverValue() {
		return participantIdentifierReceiverValue;
	}
	public void setParticipantIdentifierReceiverValue(String participantIdentifierReceiverValue) {
		this.participantIdentifierReceiverValue = participantIdentifierReceiverValue;
	}
	public String getDocumentIdStandard() {
		return documentIdStandard;
	}
	public void setDocumentIdStandard(String documentIdStandard) {
		this.documentIdStandard = documentIdStandard;
	}
	public int getDocTypeVersion() {
		return docTypeVersion;
	}
	public void setDocTypeVersion(int docTypeVersion) {
		this.docTypeVersion = docTypeVersion;
	}
	public String getDocumentInstanceIdentifier() {
		return documentInstanceIdentifier;
	}
	public void setDocumentInstanceIdentifier(String documentInstanceIdentifier) {
		this.documentInstanceIdentifier = documentInstanceIdentifier;
	}
	public String getDocumentType() {
		return documentType;
	}
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}
	public String getScopeType() {
		return scopeType;
	}
	public void setScopeType(String scopeType) {
		this.scopeType = scopeType;
	}
	public String getScopeIdentifier() {
		return scopeIdentifier;
	}
	public void setScopeIdentifier(String scopeIdentifier) {
		this.scopeIdentifier = scopeIdentifier;
	}
	public String getScopeType2() {
		return scopeType2;
	}
	public void setScopeType2(String scopeType2) {
		this.scopeType2 = scopeType2;
	}
	public String getScopeIdentifier2() {
		return scopeIdentifier2;
	}
	public void setScopeIdentifier2(String scopeIdentifier2) {
		this.scopeIdentifier2 = scopeIdentifier2;
	}
	public String getManifestDescr() {
		return manifestDescr;
	}
	public void setManifestDescr(String manifestDescr) {
		this.manifestDescr = manifestDescr;
	}
	public String getManifestLanguage() {
		return manifestLanguage;
	}
	public void setManifestLanguage(String manifestLanguage) {
		this.manifestLanguage = manifestLanguage;
	}
	public String getManiTypeQualCode() {
		return maniTypeQualCode;
	}
	public void setManiTypeQualCode(String maniTypeQualCode) {
		this.maniTypeQualCode = maniTypeQualCode;
	}
	public String getUniformResourceIdentifier() {
		return uniformResourceIdentifier;
	}
	public void setUniformResourceIdentifier(String uniformResourceIdentifier) {
		this.uniformResourceIdentifier = uniformResourceIdentifier;
	}
    
    
}
