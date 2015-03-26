package com.lynxspa.coac.importers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lynxspa.sdm.entities.events.messages.CAEventMessage;
import com.lynxspa.sdm.entities.events.messages.CAEventMessageField;

public class EventMessageImportBean{

	private String messageType=null;
	private CAEventMessage message=null;
	private List<CAEventMessageField> fields=null;
	private Map<String,CAEventMessageField> mappedFields=null;
	private boolean discartedByDuplication=false;
	private boolean filtered=false;
	private boolean grouped=false;
	
	public EventMessageImportBean(){
	
		this.fields=new ArrayList<CAEventMessageField>();
		this.mappedFields=new HashMap<String, CAEventMessageField>();
	}
	
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	public CAEventMessage getMessage() {
		return message;
	}
	public boolean isDiscartedByDuplication() {
		return discartedByDuplication;
	}
	public void setDiscartedByDuplication(boolean discartedByDuplication) {
		this.discartedByDuplication = discartedByDuplication;
	}
	public boolean isFiltered() {
		return filtered;
	}
	public void setFiltered(boolean filtered) {
		this.filtered = filtered;
	}
	public boolean isGrouped() {
		return grouped;
	}
	public void setGrouped(boolean grouped) {
		this.grouped = grouped;
	}
	public void setMessage(CAEventMessage message) {
		this.message = message;
	}
	public List<CAEventMessageField> getFields() {
		return fields;
	}
	public void setFields(List<CAEventMessageField> fields) {
		this.fields = fields;
	}
	public Map<String, CAEventMessageField> getMappedFields() {
		return mappedFields;
	}
	public void setMappedFields(Map<String, CAEventMessageField> mappedFields) {
		this.mappedFields = mappedFields;
	}
	public void addField(String _path, String _value) {
		CAEventMessageField field=new CAEventMessageField(this.getMessage().getAuditor().getCreationUser(),this.getMessage(),_path,_value);
		this.mappedFields.put(field.getPath(),field);
		this.fields.add(field);
	}
	public void replaceField(String _path, String _value) {
		CAEventMessageField field=new CAEventMessageField(this.getMessage().getAuditor().getCreationUser(),this.getMessage(),_path,_value);
		this.fields.remove(this.mappedFields.get(_path));
		this.fields.add(field);
		this.mappedFields.put(field.getPath(),field);
	}
	public String getField(String _path) {
		
		String reply=null;
		CAEventMessageField field=null;
		
		field=this.mappedFields.get(_path);
		if(field!=null)
			reply=field.getValue();
		
		return reply;
	}

	public String toString(){

		String reply="EventMessageInportBean:\n";
		
		reply+="message="+String.valueOf(this.message)+"\n";
		reply+="type="+String.valueOf(this.messageType)+"\n";
		reply+="fields["+this.fields.size()+"]={\n";
			for(CAEventMessageField field:this.fields){
				reply+="["+field.getPath()+"]--["+field.getValue()+"]\n";
			}
		reply+="}";
		
		return reply;
	}
}
