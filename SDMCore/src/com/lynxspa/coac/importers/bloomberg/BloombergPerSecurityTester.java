package com.lynxspa.coac.importers.bloomberg;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lynxspa.sdm.entities.events.messages.CAEventMessage;
import com.lynxspa.sdm.entities.events.messages.CAEventMessageField;
import com.lynxspa.coac.importers.EventMessageImportBean;
import com.lynxspa.coac.importers.bloomberg.nodes.BloombergPerSecParserNode;

public class BloombergPerSecurityTester {
	
	private enum ProcessBlock{

		FILE("START-OF-FILE","END-OF-FILE"),
		DICTIONARY("START-OF-FIELDS","END-OF-FIELDS"),
		DATA("START-OF-DATA","END-OF-DATA");
		
		private String startBlock=null;
		private String endBlock=null;
	
		ProcessBlock(String _startBlock,String _endBlock){
			this.startBlock=_startBlock;
			this.endBlock=_endBlock;
		}
		public String getStartBlock(){
			return startBlock;
		}
		public String getEndBlock(){
			return endBlock;
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		EventMessageImportBean message=null;
		File file=null;
		FileReader reader=null;
		BufferedReader bufferedReader=null;
		String readedString=null;
		List<String> messageFieldNames=null;
		BloombergPerSecParser parser=null;
		StringBuffer originalBuffer=null;
		String dateFormat="";
		int processingLine=0;
		ProcessBlock block=ProcessBlock.FILE;
		Map<String,String> messageFields=null;
		
		try{
			file=new File("input/coacs/coupons.out");
			System.out.println("Opening bloomberg file ["+file.getAbsolutePath()+"]");
			if(!file.exists())
				System.out.println("File don't exist!");
			if(!file.canRead())
				System.out.println("File can't be read!");
			reader=new FileReader(file);
			bufferedReader=new BufferedReader(reader);
			System.out.println("Preparing message reader");
			messageFieldNames=new ArrayList<String>();
			messageFieldNames.add("0");
			messageFieldNames.add("1");
			messageFieldNames.add("2");
			parser=new BloombergPerSecParser(messageFieldNames);
			messageFields=new HashMap<String,String>();
			messageFields.put("DATEFORMAT", BloombergPerSecParserNode.DEFAULTDATEFORMAT);
			message=new EventMessageImportBean();
			message.setMessage(new CAEventMessage("TESTER"));
			System.out.println("Start reading message");
			originalBuffer=new StringBuffer();
			System.out.println("--------------IMPORTING MESSAGE--------------");
			try{
				readedString=bufferedReader.readLine();
				readedString=(readedString!=null)? readedString.trim() : readedString;
				originalBuffer.append(readedString+"\n");
				processingLine++;
				System.out.println("Starting File block");
				while((readedString!=null)&&(!ProcessBlock.FILE.getEndBlock().equals(readedString))){
					System.out.println("readedString: "+readedString);
					if((readedString.length()>0)&&(!readedString.startsWith("#"))){
						if(ProcessBlock.DICTIONARY==block){
							if(!ProcessBlock.DICTIONARY.getEndBlock().equals(readedString)){
								messageFieldNames.add(readedString);
							}else{
								block=ProcessBlock.FILE;
								System.out.println("Starting File block");
							}
						}else if(ProcessBlock.DATA==block){
							if(!ProcessBlock.DATA.getEndBlock().equals(readedString)){
								parser.parse(message,readedString,processingLine);
								//Post row processing
								if("0".equals(message.getField("HDR:1").trim())){
									for(Entry<String,String> entry:messageFields.entrySet()){
										message.addField("MSG:"+entry.getKey(),entry.getValue());
									}
									message.getMessage().setOriginalMessage(readedString);
									message.getMessage().setOriginPosition(processingLine);
									message.setMessageType("Corp/Psc");
								}else if((messageFieldNames.size()==3)&&("ERROR CODE".equals(message.getField("HDR:1").trim()))){
									for(int ic1=3;ic1<message.getFields().size();ic1++){
										messageFieldNames.add(message.getFields().get(ic1).getValue());
									}
								}
							}else{
								block=ProcessBlock.FILE;
								System.out.println("Starting File block");
							}
						}else{
							if(ProcessBlock.DICTIONARY.getStartBlock().equals(readedString)){
								System.out.println("Starting dictionary block");
								block=ProcessBlock.DICTIONARY;
							}else if(ProcessBlock.DATA.getStartBlock().equals(readedString)){
								System.out.println("Starting Data block");
								block=ProcessBlock.DATA;
							}else if(!ProcessBlock.FILE.getStartBlock().equals(readedString)){
								final String[] splittedField=readedString.split("=");
								if(splittedField[0].equals("DATEFORMAT")){
									messageFields.put(splittedField[0],splittedField[1].replaceAll("mm","MM"));
								}else{
									messageFields.put(splittedField[0],splittedField[1]);
								}
							}
						}
					}
					readedString=bufferedReader.readLine();
					readedString=(readedString!=null)? readedString.trim() : readedString;
					originalBuffer.append(readedString+"\n");
					processingLine++;
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			System.out.println("-------MESSAGE IMPORTED --------");
			System.out.println("date format: "+dateFormat);
			for(CAEventMessageField field:message.getFields()){
				System.out.println("field ["+field.getPath()+"]:["+field.getValue()+"]");
			}
			System.out.println("--------------ORIGINAL MESSAGE--------------");
			System.out.println(originalBuffer);
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
