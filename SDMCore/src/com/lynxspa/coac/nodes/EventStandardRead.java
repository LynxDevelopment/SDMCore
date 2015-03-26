package com.lynxspa.coac.nodes;


import java.util.Date;

import org.hibernate.Query;

import com.lynxit.fpm.admin.config.NodeBeautifier;
import com.lynxit.fpm.admin.config.annotations.ConfigParam;
import com.lynxspa.sdm.dictionaries.config.CAConfiguration;
import com.lynxspa.sdm.entities.events.CAEvent;
import com.lynxspa.sdm.managers.SDMConfigManager;
import com.lynxspa.fpm.nodes.hibernate.HibernateStandardRead;
import com.lynxspa.utils.UtilDate;


@NodeBeautifier(description="Event Standard Read", category="CorporateActionsCore", smallIcon = "../../fpm/icons/database_check_out_16_h_g.gif", largeIcon = "../../fpm/icons/database_check_out_32_h_g.gif")
public class EventStandardRead<I,T extends CAEvent> extends HibernateStandardRead<I,T> {

	protected CAConfiguration operationalDateTimeMarginParam=null;
	
	
	@SuppressWarnings("unchecked")
	protected Query getHibernateQuery(String _queryString) throws Exception{
		
		Query reply=null;
		long margin=0l;
		Date operationalDate=null;
		
		reply=super.getHibernateQuery(_queryString);
		// Calculate operationalDate
		margin=(Long)SDMConfigManager.getInstance().getConfiguration(getResource().getCurrentInstance(),operationalDateTimeMarginParam);
		operationalDate=UtilDate.getDateTodayRelativeDate((int)margin,0,0);
		reply.setParameter("operationalDate",operationalDate);
		
		return reply;
	}

	@ConfigParam(group="Query",required=true , description="Selects the time margin in operational date configuration param for this query can be used as: operationalDate")
	public CAConfiguration getOperationalDateTimeMarginParam() {
		return operationalDateTimeMarginParam;
	}
	public void setOperationalDateTimeMarginParam(CAConfiguration _param) {
		this.operationalDateTimeMarginParam = _param;
	}
}
