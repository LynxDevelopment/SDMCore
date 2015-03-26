package com.lynxspa.coac.normalizer.operationupdate.beans;

import com.lynxspa.sdm.entities.events.details.CAEventDetailExtended;

public class EventDetailExtensionUpdateBean {

	private CAEventDetailExtended newVersion=null;
	private CAEventDetailExtended existentVersion=null;

	
	public EventDetailExtensionUpdateBean(CAEventDetailExtended _newVersion,CAEventDetailExtended _existentVersion){
		this.newVersion=_newVersion;
		this.existentVersion=_existentVersion;
	}
	
	
	public CAEventDetailExtended getNewVersion() {
		return newVersion;
	}
	public void setNewVersion(CAEventDetailExtended newVersion) {
		this.newVersion = newVersion;
	}
	public CAEventDetailExtended getExistentVersion() {
		return existentVersion;
	}
	public void setExistentVersion(CAEventDetailExtended existentVersion) {
		this.existentVersion = existentVersion;
	}
}
