package com.modus.edelivery.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import org.apache.commons.io.IOUtils;

public class StreamUtils {

	
	
	public static String stream2String(InputStream inputStream, String encoding) throws IOException{
		StringWriter writer = new StringWriter();
		IOUtils.copy(inputStream, writer, encoding);
		String theString = writer.toString();
		return theString;
	}
	
	
	public static String stream2String(String filename, String encoding) throws IOException{
		StringWriter writer = new StringWriter();
		IOUtils.copy(new FileInputStream(new File(filename)), writer, encoding);
		String theString = writer.toString();
		return theString;
	}
}
