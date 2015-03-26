package com.lynxspa.coac.normalizer.beans;

import java.util.List;

import com.lynxspa.sdm.entities.events.CAEventCollected;
import com.lynxspa.sdm.entities.events.details.CAEventDetail;
import com.lynxspa.sdm.entities.events.details.CAEventDetailExtended;
import com.lynxspa.sdm.entities.events.messages.CAEventMessage;
import com.lynxspa.coac.normalizer.adapters.NormalizableAdapter;


public class MessageNormalizeBean implements NormalizableAdapter{

	private CAEventCollected event=null;
	private CAEventMessage message=null;
	private CAEventDetail eventDetail=null;
	private List<CAEventDetailExtended> eventDetailExtended=null;
	
	
	public CAEventMessage getMessage() {
		return message;
	}
	public void setMessage(CAEventMessage message) {
		this.message = message;
	}
	public CAEventDetail getEventDetail() {
		return this.eventDetail;
	}
	public void setEventDetail(CAEventDetail _detail) {
		this.eventDetail=_detail;
	}
	public List<CAEventDetailExtended> getEventDetailExtended() {
		return this.eventDetailExtended;
	}
	public void setEventDetailExtended(List<CAEventDetailExtended> _extendedDetails) {
		this.eventDetailExtended=_extendedDetails;
	}
	public CAEventCollected getEventCollected() {
		return this.event;
	}
	public void setEventCollected(CAEventCollected _event) {
		this.event=_event;
	}
}
