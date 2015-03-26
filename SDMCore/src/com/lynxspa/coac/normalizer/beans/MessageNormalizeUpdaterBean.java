package com.lynxspa.coac.normalizer.beans;

import com.lynxspa.sdm.entities.events.CAEventCollected;
import com.lynxspa.coac.normalizer.adapters.NormalizableUpdateAdapter;


public class MessageNormalizeUpdaterBean extends MessageNormalizeBean implements NormalizableUpdateAdapter{

	private CAEventCollected previousEvent=null;
	
	
	public CAEventCollected getPreviousEventCollected() {
		return this.previousEvent;
	}
	public void setPreviousEventCollected(CAEventCollected _event) {
		this.previousEvent=_event;
	}
}
