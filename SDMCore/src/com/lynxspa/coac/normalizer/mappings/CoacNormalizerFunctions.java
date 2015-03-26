package com.lynxspa.coac.normalizer.mappings;

import org.hibernate.Session;

import com.lynxit.fpm.functionlibraries.FunctionLibrarySupport;
import com.lynxit.fpm.nodes.internal.MappingNode;
import com.lynxit.fpm.resources.Resource;
import com.lynxit.fpm.resources.ResourceId;
import com.lynxit.fpm.resources.ResourcesManager;
import com.lynxspa.sdm.entities.events.CAEventCollected;
import com.lynxspa.sdm.entities.events.details.CAEventDetail;
import com.lynxspa.entities.securities.SPSecurity;
import com.lynxspa.fpm.functions.FPMFunctions;

public class CoacNormalizerFunctions extends FunctionLibrarySupport{
	
	private FPMFunctions fpmFunctions=null;

	public CoacNormalizerFunctions(){
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
	public CAEventDetail getEventDetailFromContext(String _contextName) throws Exception{
		
		CAEventDetail reply=null;
		
		reply=(CAEventDetail)this.fpmFunctions.getContextParam(_contextName);
		
		return reply;
	}

	@SuppressWarnings("unchecked")
	public CAEventCollected getEventCollectedFromContext(String _contextName) throws Exception{
		
		CAEventCollected reply=null;
		
		reply=(CAEventCollected)this.fpmFunctions.getContextParam(_contextName);
		
		return reply;
	}
	
	public SPSecurity getSPSecurity(String _resourceName,long _securityId) throws Exception{
		
		SPSecurity reply=null;
		Resource<Session> resource=null;
		Session session=null;
		
		resource=ResourcesManager.getInstance().getResource(new ResourceId<Session>(_resourceName));
		session=resource.getCurrentInstance();
		reply=(SPSecurity)session.load(SPSecurity.class, _securityId);
		
		return reply;
	}
}
