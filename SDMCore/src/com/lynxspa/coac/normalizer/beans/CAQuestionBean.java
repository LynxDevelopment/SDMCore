package com.lynxspa.coac.normalizer.beans;

import com.lynxspa.sdm.entities.events.CAEventCollected;

public class CAQuestionBean {

	private CAEventCollected event;
	private String originalMessage;
	private boolean mandatory;
	
	public CAEventCollected getEvent() {
		return event;
	}
	public void setEvent(CAEventCollected event) {
		this.event = event;
	}
	public String getOriginalMessage() {
		return originalMessage;
	}
	public void setOriginalMessage(String originalMessage) {
		this.originalMessage = originalMessage;
	}
	public boolean isMandatory() {
		return mandatory;
	}
	public void setMandatory(boolean mandatory) {
		this.mandatory = mandatory;
	}
	
	
}
