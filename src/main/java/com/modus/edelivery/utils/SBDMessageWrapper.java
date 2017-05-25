package com.modus.edelivery.utils;

public class SBDMessageWrapper {
	String SBDMessageStr ;
	
	public SBDMessageWrapper(){
		
	}
	
	
	public SBDMessageWrapper(String msg){
		SBDMessageStr = msg;
	}

	public String getSBDMessageStr() {
		return SBDMessageStr;
	}


	public void setSBDMessageStr(String sBDMessageStr) {
		SBDMessageStr = sBDMessageStr;
	}


	/**
	 * H μέθοδος αυτή λαμβάνει το payload και το κάνει append σαν έγγραφο στο SBD. To SBD 
	 * πρέπει να είναι String.  
	 * @param payload
	 */
	public void appendPayload(String payload){
		String part1 = "";
		String part2 = "";
		if(this.SBDMessageStr.indexOf("</StandardBusinessDocumentHeader>")>-1){
			part1 = this.SBDMessageStr.substring(0, this.SBDMessageStr.indexOf("</StandardBusinessDocumentHeader>")+"</StandardBusinessDocumentHeader>".length());
			part2= this.SBDMessageStr.substring(this.SBDMessageStr.indexOf("</StandardBusinessDocumentHeader>")+"</StandardBusinessDocumentHeader>".length(),this.SBDMessageStr.length());
			this.SBDMessageStr = part1 + payload+ part2;
		}
	}
	
	public String getPayload(boolean remove){
		String part1 = "";
		String part2 = "";
		String part3="";
		
		int index1=this.SBDMessageStr.indexOf("</StandardBusinessDocumentHeader>");
		if(index1>-1){
			//part1 = this.SBDMessageStr.substring(0, this.SBDMessageStr.indexOf("</StandardBusinessDocumentHeader>")+"</StandardBusinessDocumentHeader>".length());
			int index2 =this.SBDMessageStr.indexOf("</StandardBusinessDocument>"); 
			if(index2>-1){
				part2=	this.SBDMessageStr.substring(index1+"</StandardBusinessDocumentHeader>".length(),index2-1);// TODO ίσως καλύτερα trim.
				if(remove){
					StringBuffer sb = new  StringBuffer(this.SBDMessageStr);
					sb.replace(index1, index2-1, "");
					this.SBDMessageStr = sb.toString();
				}
			}
		}
		return part2;
	}
	

	
}
