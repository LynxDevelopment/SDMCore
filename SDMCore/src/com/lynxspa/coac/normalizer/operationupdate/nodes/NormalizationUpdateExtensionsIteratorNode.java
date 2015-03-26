package com.lynxspa.coac.normalizer.operationupdate.nodes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;

import com.lynxit.fpm.admin.config.NodeBeautifier;
import com.lynxit.fpm.admin.config.annotations.ConfigParam;
import com.lynxit.fpm.admin.config.annotations.ValueMode;
import com.lynxit.fpm.nodes.OutputConnectable;
import com.lynxit.fpm.nodes.support.InternalNodeSupport;
import com.lynxit.fpm.resources.Resource;
import com.lynxspa.sdm.entities.events.details.CAEventDetailExtended;
import com.lynxspa.coac.normalizer.adapters.NormalizableUpdateAdapter;
import com.lynxspa.coac.normalizer.operationupdate.beans.EventDetailExtensionUpdateBean;


/**
 * Iterate over extensions and generates a com.lynxspa.coac.normalizer.operationupdate.beans.EventDetailExtensionUpdateBean
 * @author albert.farre
 */
@NodeBeautifier(description="Normalization Update Extension Iterator", category="CorporateActionsCore", smallIcon = "../../../../../lynxit/fpm/nodes/icons/iteration_16.gif", largeIcon = "../../../../../lynxit/fpm/nodes/icons/iteration_32.gif")
public class NormalizationUpdateExtensionsIteratorNode extends InternalNodeSupport<NormalizableUpdateAdapter> {

    private OutputConnectable<? super CAEventDetailExtended> nodeConnectedToNewExtension;
    private OutputConnectable<? super EventDetailExtensionUpdateBean> nodeConnectedToExistentExtension;
	private Resource<Session> resource=null;

	
	@Override
	@SuppressWarnings("unchecked")
	protected void processMessage(NormalizableUpdateAdapter _message) throws Exception {
		
		Map<String,CAEventDetailExtended> existentExtensions=null;
		
		//Recover all previous extensions
		final Session session=this.resource.getCurrentInstance();
		final Query query=session.createQuery("" +
				"select new Map(detailExtended.extensionIdCode,detailExtended) " +
				"from CAEventDetailExtended as detailExtended " +
				"where " +
				"	mainDetail.id=:mainDetailId and " +
				"	auditor.refId=0");
		query.setLong("mainDetailId", _message.getPreviousEventCollected().getId());
		existentExtensions=(Map<String,CAEventDetailExtended>)query.uniqueResult();//_message.getPreviousEventCollected().getEventDetail().getExtensions();
		if(existentExtensions==null)
			existentExtensions=new HashMap<String,CAEventDetailExtended>();
		//process previous extensions
		final List<CAEventDetailExtended> updatedExtensions=_message.getEventDetailExtended();
		for(CAEventDetailExtended updatedExtension:updatedExtensions){
			final CAEventDetailExtended existentExtension=existentExtensions.get(updatedExtension.getExtensionId().toString());
			if(existentExtension!=null){
				this.getNodeConnectedToExistentExtension().process(new EventDetailExtensionUpdateBean(updatedExtension,existentExtension));
			}else{
				updatedExtension.setMainDetail(_message.getPreviousEventCollected().getEventDetail());
				this.getNodeConnectedToNewExtension().process(updatedExtension);
			}
		}
	}
	
	
	@ConfigParam(required=true, group="config",defaultValueMode=ValueMode.REFERENCE)
	public Resource<Session> getResource(){
		return resource;
	}
	public void setResource(Resource<Session> _resource){
		this.resource = _resource;
	}
	
	public void connectNodeToNewExtension(OutputConnectable<? super CAEventDetailExtended> node){
        this.nodeConnectedToNewExtension = node;
    }
    public OutputConnectable<? super CAEventDetailExtended> getNodeConnectedToNewExtension(){
        return this.nodeConnectedToNewExtension;
    }
    
	public void connectNodeToExistentExtension(OutputConnectable<? super EventDetailExtensionUpdateBean> node){
        this.nodeConnectedToExistentExtension = node;
    }
    public OutputConnectable<? super EventDetailExtensionUpdateBean> getNodeConnectedToExistentExtension(){
        return this.nodeConnectedToExistentExtension;
    }
}
