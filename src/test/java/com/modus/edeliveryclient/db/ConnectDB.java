package com.modus.edeliveryclient.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectDB {
	String userName ="papyrosm3";
	String password = "papyrosm3";
	//jdbc:sqlserver://[serverName[\instanceName][:portNumber]][;property=value[;property=value]]
	//jdbc:sqlserver://TESTSRV\SQLEXPRESS;DatabaseName=edeliveryserver
	String url="jdbc:sqlserver://TESTSRV\\SQLEXPRESS;DatabaseName=edeliveryserver";
	public ConnectDB(){
		
	}
	
	public void  getConnection() throws SQLException {

	    Connection conn = null;
	    Properties connectionProps = new Properties();
	    connectionProps.put("user", this.userName);
	    connectionProps.put("password", this.password);
	    try{
		    conn = DriverManager.getConnection(this.url,connectionProps);
		    
		    System.out.println("Connected to database");
	    }
	    catch(Exception ex){
	    	ex.printStackTrace();
	    }
	    finally{
	    	if(conn!=null){
	    		conn.close();
	    	}
	    }
	    //return conn;
	}
	
	public static void main(String args[]){
		ConnectDB conB = new ConnectDB();conB.userName = "papyrosm3";conB.userName = "papyrosm3";
		try {
			conB.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
