package com.lynxspa.coac.importers.nodes;

import org.hibernate.Query;
import org.hibernate.StatelessSession;

import com.lynxit.fpm.admin.config.NodeBeautifier;
import com.lynxit.fpm.admin.config.annotations.ConfigParam;
import com.lynxit.fpm.nodes.OutputConnectable;
import com.lynxit.fpm.nodes.support.InternalNodeSupport;
import com.lynxit.fpm.resources.Resource;
import com.lynxspa.coac.importers.EventMessageImportBean;


/**
 * @author albert.farre
 */
@NodeBeautifier(description="Duplicate Import Filter", category="CorporateActionsCore", smallIcon = "../../../../lynxit/fpm/nodes/icons/switch_16.gif", largeIcon = "../../../../lynxit/fpm/nodes/icons/switch_32.gif")
public class DuplicateImportFilter<T extends EventMessageImportBean> extends InternalNodeSupport<T> {

	private OutputConnectable< ? super T> nodeConnectedToOut_;

	private Resource<StatelessSession> resource=null;
	private String user=null;
	private String locale=null;
	

	public DuplicateImportFilter() {
		super();
	}

	
    @Override
    protected void processMessage(T message) throws Exception{
    	
    	final StatelessSession session=resource.getCurrentInstance();
    	final Query query=session.createQuery("select count(message.id) from CAEventMessage message where auditor.deleted=:deleted and messageId=:messageId");
    	query.setParameter("deleted", false);
    	query.setString("messageId", message.getMessage().getMessageId());
    	query.setReadOnly(true);
    	query.setFetchSize(1);
    	final Number elements=(Number)query.uniqueResult();
    	if(elements.longValue()==0){
    		getNodeConnectedToOut().process(message);
    	}else{
    		message.setDiscartedByDuplication(true);
    	}
    }

	
    @ConfigParam(required=true,description="locale",group="config")
	public Resource<StatelessSession> getResource() {
		return resource;
	}
	public void setResource(Resource<StatelessSession> resource) {
		this.resource = resource;
	}

	@ConfigParam(required=true,description="locale",group="config")
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}

	@ConfigParam(required=true,description="locale",group="config")
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}


	public void connectNodeToOut(OutputConnectable< ? super T> node){
        nodeConnectedToOut_ = node;
    }
    public OutputConnectable< ? super T> getNodeConnectedToOut(){
        return nodeConnectedToOut_;
    }
}
