package com.modus.edeliveryclient;

import com.google.gson.Gson;
import com.modus.edeliveryclient.model.Messages;
import com.modus.edeliveryclient.model.TestJson;

public class ConvertJson {

	
	public static void main (String args[]){
		Messages msg = new Messages();
		String[] array =new String[2];
		array[0]="9933_test1-20170516083110645@local_delivery";
		array[1]="9933_test1-20170516083110645@local_delivery2";
		msg.setMessageId(array);
		TestJson t = new TestJson();
		t.setMessages(msg);
		
		System.out.println(new Gson().toJson(t));
		
		String s = "{\"Messages\": {\"MessageId\": [\"9933_test1-20170413121102099@local_delivery\",\"9933_test1-20170516083110645@local_delivery\"]},\"NumberOfMessages\": 11}";
		String s2 = "{\r\n" + 
				"  \"Messages\": {\r\n" + 
				"    \"MessageId\": [\r\n" + 
				"      \"9933_test1-20170413121102099@local_delivery\",\r\n" + 
				"      \"9933_test1-20170516083110645@local_delivery\"\r\n" + 
				"    \r\n" + 
				"    ]\r\n" + 
				"  },\r\n" + 
				"  \"NumberOfMessages\": 11\r\n" + 
				"}";
				
		TestJson t2 = new Gson().fromJson(s2, TestJson.class);
		
		System.out.println(new Gson().toJson(t2));
		
	}
}
