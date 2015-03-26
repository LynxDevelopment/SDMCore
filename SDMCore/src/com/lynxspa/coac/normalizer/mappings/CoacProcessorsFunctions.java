package com.lynxspa.coac.normalizer.mappings;

import java.util.Map;

import com.lynxit.fpm.functionlibraries.FunctionLibrarySupport;
import com.lynxspa.sdm.entities.events.providers.CAExternalEventProvider;
import com.lynxspa.fpm.functions.FPMFunctions;
import com.lynxit.fpm.nodes.internal.MappingNode;

public class CoacProcessorsFunctions extends FunctionLibrarySupport{
	
	private FPMFunctions fpmFunctions=null;

	public CoacProcessorsFunctions(){
		fpmFunctions=new FPMFunctions();
	}

	@Override
	@SuppressWarnings("unchecked")
	public MappingNode getNode() {
		return super.getNode();
	}

	@Override
	@SuppressWarnings("unchecked")
	public void setNode(MappingNode node) {
		super.setNode(node);
		this.fpmFunctions.setNode(node);
	}
	
	@SuppressWarnings("unchecked")
	public CAExternalEventProvider getExternalProviderFromContext(String _contextName,String _providerId) throws Exception{
		
		CAExternalEventProvider reply=null;
		Map<String,CAExternalEventProvider> providersMap=null;
		
		providersMap=(Map<String,CAExternalEventProvider>)this.fpmFunctions.getContextParam(_contextName);
		reply=providersMap.get(_providerId);
		
		return reply;
	}
}
