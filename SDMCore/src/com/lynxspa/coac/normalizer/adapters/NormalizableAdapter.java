package com.lynxspa.coac.normalizer.adapters;

import java.util.List;

import com.lynxspa.sdm.entities.events.CAEventCollected;
import com.lynxspa.sdm.entities.events.details.CAEventDetail;
import com.lynxspa.sdm.entities.events.details.CAEventDetailExtended;
import com.lynxspa.sdm.entities.events.messages.CAEventMessage;

public interface NormalizableAdapter{

	public CAEventCollected getEventCollected();
	public void setEventCollected(CAEventCollected _previousEvent);
	public CAEventDetail getEventDetail();
	public void setEventDetail(CAEventDetail _detail);
	public List<CAEventDetailExtended> getEventDetailExtended();
	public void setEventDetailExtended(List<CAEventDetailExtended> _extendedDetails);
	public CAEventMessage getMessage();
	public void setMessage(CAEventMessage _message);
}
