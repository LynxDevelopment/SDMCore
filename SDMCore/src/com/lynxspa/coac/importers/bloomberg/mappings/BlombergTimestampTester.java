package com.lynxspa.coac.importers.bloomberg.mappings;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class BlombergTimestampTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		 
		String inputFormat="EEE MMM dd HH:mm:ss zzz yyyy";
		String outputFormat="EEE yyyy/MM/dd HH:mm:ss  zzz";
		String inputString="Fri May 23 17:56:14 BST 2008";
		String outputString="";
		SimpleDateFormat parser=null;
		Date parsedDate=null;
		
		System.out.println("Parsing date: "+inputString);
		System.out.println("Generating parser with format: "+inputFormat);
		parser=new SimpleDateFormat(inputFormat,new Locale("en"));
		System.out.println("Parsing data");
		parsedDate=parser.parse(inputString);
		System.out.println("Parsed data: "+parsedDate);
		System.out.println("Generating formatter with format: "+outputFormat);
		parser=new SimpleDateFormat(outputFormat);
		System.out.println("Formatting data");
		outputString=parser.format(parsedDate);
		System.out.println("Formatted data: "+outputString);
	}
}
