package com.lynxspa.coac.authorization;

import com.lynxspa.entities.application.logs.utils.LogDictAdapter;
import com.lynxspa.entities.application.logs.utils.LogLevel;

public class VirtualLogDictBean implements LogDictAdapter {

	
	private String messageKey=null;
	
	
	public VirtualLogDictBean(String _messageKey){
		this.messageKey=_messageKey;
	}
	
	
	public String getDefaultMessage() {
		return this.messageKey;
	}
	public String getMessageKey() {
		return this.messageKey;
	}
	public LogLevel getLevel() {
		return LogLevel.INFO;
	}
}
