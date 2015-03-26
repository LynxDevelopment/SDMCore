package com.lynxspa.coac.importers.bloomberg;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.lynxspa.sdm.entities.events.messages.CAEventMessage;
import com.lynxspa.sdm.entities.events.messages.CAEventMessageField;
import com.lynxspa.coac.importers.EventMessageImportBean;

public class BloombergTester {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		EventMessageImportBean message=null;
		File file=null;
		FileReader reader=null;
		BufferedReader bufferedReader=null;
		String readedString=null;
		BloombergParser parser=null;
		StringBuffer headerBuffer=null;
		StringBuffer footerBuffer=null;
		String originalmessage=null;
		String dateFormat="";
		
		try{
			file=new File("input/test.cax");
			System.out.println("Opening bloomberg file ["+file.getAbsolutePath()+"]");
			if(!file.exists())
				System.out.println("File don't exist!");
			if(!file.canRead())
				System.out.println("File can't be read!");
			reader=new FileReader(file);
			bufferedReader=new BufferedReader(reader);
			System.out.println("Start reading message");
			parser=new BloombergParser();
			message=new EventMessageImportBean();
			message.setMessage(new CAEventMessage("TESTER"));
			headerBuffer=new StringBuffer();
			footerBuffer=new StringBuffer();
			System.out.println("--------------IMPORTING MESSAGE--------------");
			try{
				readedString=bufferedReader.readLine();
				headerBuffer.append(readedString+"\n");
				while(!"START-OF-DATA".equals(readedString.trim())){
					if(readedString.startsWith("DATEFORMAT")){
						dateFormat=readedString.substring(11).replaceAll("mm","MM");
					}
					readedString=bufferedReader.readLine();
					headerBuffer.append(readedString+"\n");
				}
				readedString=bufferedReader.readLine();
				if("# PRODUCT=Corp/Pfd".equals(readedString.trim())){
					headerBuffer.append(readedString);
					readedString=bufferedReader.readLine();
				}
				System.out.println("readedString: \n"+readedString);
				originalmessage=readedString;
				parser.parse(message, readedString,0);
				for(CAEventMessageField field:message.getFields()){
					System.out.println("field ["+field.getPath()+"]:["+field.getValue()+"]");
					//System.out.println("fieldformatPath ["+field.getPath().replaceAll("(\\(\\d*\\))|(\\([^0-9]\\))","").replaceAll("//", "/")+"]");
				}
				readedString=bufferedReader.readLine();
				while(readedString!=null){
					if(readedString.startsWith("DATARECORDS"))
						readedString="DATARECORDS=1";
					footerBuffer.append(readedString+"\n");
					readedString=bufferedReader.readLine();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			System.out.println("-------MESSAGE IMPORTED --------");
			System.out.println("date format: "+dateFormat);
			System.out.println("--------------ORIGINAL MESSAGE--------------");
			System.out.println(headerBuffer);
			System.out.println(originalmessage);
			System.out.println(footerBuffer);
			System.out.println("--------------MESSAGE ORIGINAL--------------");
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
