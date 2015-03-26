package com.lynxspa.coac.notificationmanagers.nodes;

import com.lynxit.fpm.admin.config.NodeBeautifier;
import com.lynxit.fpm.nodes.OutputConnectable;
import com.lynxit.fpm.nodes.support.InternalNodeSupport;
import com.lynxspa.coac.notificationmanagers.beans.NotificationEventBean;
/**
 * @author joseluis.llorente
 */
@NodeBeautifier(description="Manager Exists Filter", category="CorporateActionsCore", smallIcon = "../../../../lynxit/fpm/nodes/icons/switch_16.gif", largeIcon = "../../../../lynxit/fpm/nodes/icons/switch_32.gif")
public class ManagerExistsFilter<T extends NotificationEventBean> extends InternalNodeSupport<T> {
	private OutputConnectable< ? super T> nodeConnectedToOut_;

	public ManagerExistsFilter() {
		super();
	}

	
    @Override
    protected void processMessage(T message) throws Exception{
    	if (message.getEmailAddresses()!=null && message.getEmailAddresses().length>0){
    		getNodeConnectedToOut().process(message);
    	}else{
    		throw new Exception("Manager Email is not created");
    	}
    }

    public void connectNodeToOut(OutputConnectable< ? super T> node){
        nodeConnectedToOut_ = node;
    }
    public OutputConnectable< ? super T> getNodeConnectedToOut(){
        return nodeConnectedToOut_;
    }
    
}
