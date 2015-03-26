package com.lynxspa.coac.normalizer.operationupdate.nodes;

import org.hibernate.LockMode;
import org.hibernate.Session;

import com.lynxit.fpm.admin.config.NodeBeautifier;
import com.lynxspa.sdm.dictionaries.config.CAConfiguration;
import com.lynxspa.sdm.dictionaries.logs.LogErrorDict;
import com.lynxspa.sdm.entities.events.CAEventCollected;
import com.lynxspa.sdm.entities.events.messages.CAEventMessage;
import com.lynxspa.sdm.managers.SDMConfigManager;
import com.lynxspa.coac.normalizer.adapters.NormalizableAdapter;
import com.lynxspa.coac.normalizer.nodes.FindPreviousEventNode;
import com.lynxspa.exception.FPMException;

/**
 * Find previous event
 * @author albert.farre
 */
@NodeBeautifier(description="Update Find Previous Event", category="CorporateActionsCore", smallIcon = "../../../../lynxit/fpm/nodes/icons/db_input_16.gif", largeIcon = "../../../../lynxit/fpm/nodes/icons/db_input_32.gif")
public class UpdateFindPreviousEventNode extends FindPreviousEventNode {

	@Override
	protected CAEventCollected getPreviousEvent(final Session _session,final NormalizableAdapter _message,final CAEventMessage _previousMessage) throws FPMException{
		
		CAEventCollected reply=null;
		
		if((isOnNotFoundFail())&&(_previousMessage==null)){
			final boolean normalizeNotFoundAsNew=(Boolean)SDMConfigManager.getInstance().getConfiguration(_session,CAConfiguration.NORMALIZEPREVIOUSNOTFOUNDASNEW);
			if(!normalizeNotFoundAsNew)
				throw new FPMException(LogErrorDict.PREVIOUS_NEW_MESSAGE_NOT_FOUND,new Object[]{_message.getMessage().getId()});
		}
		if(_previousMessage!=null){
			reply=(CAEventCollected)_session.get(CAEventCollected.class,_previousMessage.getNormalizedEvent(),LockMode.READ);
			if((isOnNotFoundFail())&&(reply==null)){
				final boolean normalizeNotFoundAsNew=(Boolean)SDMConfigManager.getInstance().getConfiguration(_session,CAConfiguration.NORMALIZEPREVIOUSNOTFOUNDASNEW);
				if(!normalizeNotFoundAsNew)
					throw new FPMException(LogErrorDict.PREVIOUS_NEW_MESSAGE_NOT_NORMALIZED,new Object[]{_message.getMessage().getId(),_previousMessage.getId()});
			}
		}
		
		return reply;
	}
}
