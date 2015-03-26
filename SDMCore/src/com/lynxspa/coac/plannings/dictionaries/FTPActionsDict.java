package com.lynxspa.coac.plannings.dictionaries;

public enum FTPActionsDict {
	PUT("PUT","FTP PUT Action"),
	GET("GET","FTP GET Actions");
	
	String action=null;
	String description=null;
	private FTPActionsDict(String _action, String _description){
		this.action = _action;
		this.description = _description;
	}
	public String getAction() {
		return action;
	}
	public String getDescription() {
		return description;
	}
}
