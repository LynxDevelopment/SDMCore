package com.lynxspa.coac.importers.ofival;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.lynxspa.sdm.entities.events.messages.CAEventMessage;
import com.lynxspa.sdm.entities.events.messages.CAEventMessageField;
import com.lynxspa.coac.importers.EventMessageImportBean;

public class OfivalTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		EventMessageImportBean message=null;
		File file=null;
		FileReader reader=null;
		BufferedReader bufferedReader=null;
		String readedString=null;
		OfivalParser parser=null;
		
		try{
			file=new File("input/test.ofv");
			System.out.println("Opening ofival file ["+file.getAbsolutePath()+"]");
			if(!file.exists())
				System.out.println("File don't exist!");
			if(!file.canRead())
				System.out.println("File can't be read!");
			reader=new FileReader(file);
			bufferedReader=new BufferedReader(reader);
			System.out.println("Start reading message");
			parser=new OfivalParser();
			message=new EventMessageImportBean();
			message.setMessage(new CAEventMessage("TESTER"));
			System.out.println("--------------IMPORTING MESSAGE--------------");
			try{
				readedString=bufferedReader.readLine();
				System.out.println("readedString: "+readedString);
				parser.parse(message, readedString,0);
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
		}finally{
			if(bufferedReader!=null){
				try{
					bufferedReader.close();
				}catch(IOException e){e.printStackTrace();}
			}
			if(reader!=null){
				try{
					reader.close();
				}catch(IOException e){e.printStackTrace();}
			}
		}
	}
}
