package com.lynxspa.coac.importers.swift;

import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.lynxspa.sdm.entities.events.messages.CAEventMessage;
import com.lynxspa.sdm.entities.events.messages.CAEventMessageField;
import com.lynxspa.coac.importers.EventMessageImportBean;

public class SwiftTester {

	public static void main(String[] args) {
		
		EventMessageImportBean message=null;
		File file=null;
		Scanner scanner=null;
		FileReader reader=null;
		SwiftParser swiftParser=null;
		String readedString=null;
		
		try{
			file=new File("input/test.swf");
			System.out.println("Opening swift file ["+file.getAbsolutePath()+"]");
			if(!file.exists())
				System.out.println("File don't exist!");
			if(!file.canRead())
				System.out.println("File can't be read!");
			reader=new FileReader(file);
			System.out.println("Start reading message");
			swiftParser=new SwiftParser();
			scanner=new Scanner(reader);
			scanner.useDelimiter(Pattern.compile("\\{1:",Pattern.MULTILINE));
			message=new EventMessageImportBean();
			message.setMessage(new CAEventMessage("TESTER"));
			System.out.println("--------------IMPORTING MESSAGE--------------");
			try{
				readedString="{1:"+scanner.next();
				System.out.println("readedString: "+readedString);
				swiftParser.parse(message, readedString,0);
				for(CAEventMessageField field:message.getFields()){
					System.out.println("field ["+field.getPath()+"]:["+field.getValue()+"]");
					System.out.println("fieldformatPath ["+field.getPath().replaceAll("(\\(\\d*\\))|(\\([^0-9]\\))","").replaceAll("//", "/")+"]");
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			System.out.println("-------MESSAGE IMPORTED --------");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
