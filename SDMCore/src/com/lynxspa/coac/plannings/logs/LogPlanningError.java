package com.lynxspa.coac.plannings.logs;

import com.lynxspa.entities.application.logs.utils.LogDictAdapter;
import com.lynxspa.entities.application.logs.utils.LogLevel;

public enum LogPlanningError implements LogDictAdapter {

	FTP_CONNECT_SERVER_FAIL("error.planning.ftp.connect.fail","Couldn´t connect to server: {0}."),
	FTP_CONNECT_LOGIN_FAIL("error.planning.ftp.login.fail","Couldn´t login to server {0} with user {1}."),
	FTP_GET_FAIL("error.planning.ftp.get.fail","Error Transferring file {0} from server {1}."),
	FTP_PUT_FAIL("error.planning.ftp.put.fail","Error Transferring file {0} to path {1} of server {2}."),
	FTP_PUT_PERMISSION_FAIL("error.planning.ftp.put.permission.fail","Unable to send file {0}. Review permissions in path {1} of server {2}."),
	PLANNINGS_FILE_FROM_TEMPLATE_ERROR("error.planning.generating.file","Error creating file {0} from template {1}."),
	PLANNINGS_TEMPLATE_ERROR("error.planning.generating.template","Error generating template file {0}.");
	
	private String messageKey=null;
	private String defaultMessage=null;
	
	LogPlanningError(String _key, String _defaultMessage){
		this.messageKey=_key;
		this.defaultMessage=_defaultMessage;
	}
	
	public String getMessageKey() {
		return this.messageKey;
	}
	public String getDefaultMessage() {
		return this.defaultMessage;
	}
	public LogLevel getLevel() {
		return LogLevel.ERROR;
	}
}
