package com.modus.edeliveryclient.models;

public class SBDParams {

	//TODO remove default values .
	private static int headerVersion = 1;

    private final static String participantIdentifierSenderScheme = "iso6523-actorid-upis";
    private final static String participantIdentifierSenderValue = "9933:test1";
    private final static String participantIdentifierReceiverScheme = "iso6523-actorid-upis";
    private final static String participantIdentifierReceiverValue = "9933:test1";

    private final static String documentIdStandard = "urn:oasis:names:specification:ubl:schema:xsd:Invoice-2";
    private final static int docTypeVersion = 1;
    private final static String documentInstanceIdentifier = "627a2e9a-a146-461f-b62f-f22f5799fd55";
    private final static String documentType = "Invoice";

    private final static String scopeType = "DOCUMENTID";
    private final static String scopeIdentifier = "urn:oasis:names:specification:ubl:schema:xsd:Invoice-2:: Invoice##urn:www.cenbii.eu:transaction:biitrns010:ver2.0:extended:urn: www.peppol.eu:bis:peppol4a:ver2.0::2.1";
    private final static String scopeType2 = "PROCESSID";
    private final static String scopeIdentifier2 = "urn:www.cenbii.eu:profile:bii04:ver2.0";

    private final static String manifestDescr = "manifestDescr";
    private final static String manifestLanguage = " manifestLanguage";
    private final static String maniTypeQualCode = "maniTypeQualCode";
    private final static String uniformResourceIdentifier = "uniformResourceIdentifier";
    
    
	public static int getHeaderVersion() {
		return headerVersion;
	}
	public static void setHeaderVersion(int headerVersion) {
		SBDParams.headerVersion = headerVersion;
	}
	public static String getParticipantidentifiersenderscheme() {
		return participantIdentifierSenderScheme;
	}
	public static String getParticipantidentifiersendervalue() {
		return participantIdentifierSenderValue;
	}
	public static String getParticipantidentifierreceiverscheme() {
		return participantIdentifierReceiverScheme;
	}
	public static String getParticipantidentifierreceivervalue() {
		return participantIdentifierReceiverValue;
	}
	public static String getDocumentidstandard() {
		return documentIdStandard;
	}
	public static int getDoctypeversion() {
		return docTypeVersion;
	}
	public static String getDocumentinstanceidentifier() {
		return documentInstanceIdentifier;
	}
	public static String getDocumenttype() {
		return documentType;
	}
	public static String getScopetype() {
		return scopeType;
	}
	public static String getScopeidentifier() {
		return scopeIdentifier;
	}
	public static String getScopetype2() {
		return scopeType2;
	}
	public static String getScopeidentifier2() {
		return scopeIdentifier2;
	}
	public static String getManifestdescr() {
		return manifestDescr;
	}
	public static String getManifestlanguage() {
		return manifestLanguage;
	}
	public static String getManitypequalcode() {
		return maniTypeQualCode;
	}
	public static String getUniformresourceidentifier() {
		return uniformResourceIdentifier;
	}
    
    
    
    
}
