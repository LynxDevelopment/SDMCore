/**
 * 
 */
package com.lynxspa.coac.normalizer.nodes;

import java.util.Date;
import java.util.HashMap;

import com.lynxit.fpm.admin.config.NodeBeautifier;
import com.lynxit.fpm.nodes.support.SingleExitInternalNodeSupport;
import com.lynxspa.sdm.entities.events.details.CAEventDetail;
import com.lynxspa.coac.normalizer.adapters.NormalizableUpdateAdapter;


@NodeBeautifier(description = "Manual Map Normalizable Update Adapter To CA Event Detail", category = "CorporateActionsCore")
public class ManualMapNormalizableUpdateAdapterToCAEventDetail extends SingleExitInternalNodeSupport<NormalizableUpdateAdapter, CAEventDetail> {

	public ManualMapNormalizableUpdateAdapterToCAEventDetail() {
		
	}

	@Override
	protected CAEventDetail perform(NormalizableUpdateAdapter message) throws Exception {
		CAEventDetail caEventDetail = null;
		
		try{
			caEventDetail = (CAEventDetail)message.getEventDetail();
			HashMap<String, Object> eventDetailDynamicTable = caEventDetail.getDynamicTable();
			HashMap<String, Object> previousEventDetailDynamicTable = message.getPreviousEventCollected().getEventDetail().getDynamicTable();
			caEventDetail.setDynamicTable(getSecureDynamicTable(eventDetailDynamicTable,previousEventDetailDynamicTable));
			caEventDetail.setExecutionDate(getSecureDate(message.getPreviousEventCollected().getEventDetail().getExecutionDate(), caEventDetail.getExecutionDate()));
			caEventDetail.setExpirationDate(getSecureDate(message.getPreviousEventCollected().getEventDetail().getExpirationDate(),caEventDetail.getExpirationDate()));
			caEventDetail.setOperationalDate(getSecureDate(message.getPreviousEventCollected().getEventDetail().getOperationalDate(),caEventDetail.getOperationalDate()));
			caEventDetail.setSubscriptionDate(getSecureDate(message.getPreviousEventCollected().getEventDetail().getSubscriptionDate(),caEventDetail.getSubscriptionDate()));
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		return caEventDetail;
	}
	
	public HashMap<String, Object> getSecureDynamicTable(HashMap<String, Object> _newDynamicTable,HashMap<String, Object> _default){
		
		HashMap<String, Object> reply=null;
		Object value=null;
		Object castedValue=null;
		if(_default!=null){
			reply=new HashMap<String, Object>(_default);
			for(String key:_newDynamicTable.keySet()){
				value=_newDynamicTable.get(key);
				if(value instanceof Boolean){
					if((castedValue=getSecureBoolean((Boolean)value,null))!=null)
						reply.put(key,castedValue);
				}else if(value instanceof Integer){
					if((castedValue=getSecureInteger((Integer)value,null))!=null)
						reply.put(key,castedValue);
				}else if(value instanceof Long){
					if((castedValue=getSecureLong((Long)value,null))!=null)
						reply.put(key,castedValue);
				}else if(value instanceof Float){
					if((castedValue=getSecureFloat((Float)value,null))!=null)
						reply.put(key,castedValue);
				}else if(value instanceof Double){
					if((castedValue=getSecureDouble((Double)value,null))!=null)
						reply.put(key,castedValue);
				}else if(value instanceof Date){
					if((castedValue=getSecureDate((Date)value,null))!=null)
						reply.put(key,castedValue);
				}else if(value!=null){
					reply.put(key,value);
				}
			}
		}else{
			reply=_newDynamicTable;
		}
		
		return reply;
	}
	
	public Boolean getSecureBoolean(Boolean _newBoolean,Boolean _default){
		return ((_newBoolean!=null)? _newBoolean : _default);
	}
	public Integer getSecureInteger(Integer _newInt,Integer _default){
		return ((_newInt!=null)? _newInt : _default);
	}
	public Long getSecureLong(Long _newLong,Long _default){
		return ((_newLong!=null)? _newLong : _default);
	}
	public Float getSecureFloat(Float _newFloat,Float _default){
		return ((_newFloat!=null)? _newFloat : _default);
	}
	public Double getSecureDouble(Double _newDouble,Double _default){
		return ((_newDouble!=null)? _newDouble : _default);
	}
	public Date getSecureDate(Date _newDate,Date _default){
		return ((_newDate!=null)? _newDate : _default);
	}
	public String getSecureString(String _newString,String _default){
		return (((_newString!=null)&&(_newString.trim().length()>0))? _newString : _default);
	}
	

}
