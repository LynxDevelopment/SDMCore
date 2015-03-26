/**
 * 
 */
package com.lynxspa.coac.importers.nodes;

import com.lynxit.fpm.admin.config.NodeBeautifier;
import com.lynxit.fpm.admin.config.annotations.ConfigParam;
import com.lynxit.fpm.nodes.OutputConnectable;
import com.lynxit.fpm.nodes.support.InternalNodeSupport;
import com.lynxspa.coac.importers.EventMessageImportBean;


/**
 * 
 * 
 * @author albert.farre
 *
 */
@NodeBeautifier(description="Import Filter", category="CorporateActionsCore", smallIcon = "../../../../lynxit/fpm/nodes/icons/switch_16.gif", largeIcon = "../../../../lynxit/fpm/nodes/icons/switch_32.gif")
public class ImportFilter<T extends EventMessageImportBean> extends InternalNodeSupport<T> {

	private OutputConnectable< ? super T> nodeConnectedToOut_;
	private String[] acceptedMessages;


	public ImportFilter() {
		super();
	}

	
    @Override
    protected void processMessage(T message) throws Exception{

    	if(acceptable(message.getMessageType())){
    		getNodeConnectedToOut().process(message);
    	}else{
    		message.setFiltered(true);
    	}
    }

    private boolean acceptable(String _messageType){
    	
    	boolean reply=false;
    	
    	for(int ic1=0;(ic1<this.acceptedMessages.length)&&(!reply);ic1++){
    		reply=(this.acceptedMessages[ic1].equals(_messageType))? true : false;
    	}
    	
    	return reply;
    }
    
    @ConfigParam(required=true,description="accepted message types")
    public String[] getAcceptedMessages() {
		return acceptedMessages;
	}
	public void setAcceptedMessages(String[] acceptedMessages) {
		this.acceptedMessages = acceptedMessages;
	}
	
	public void connectNodeToOut(OutputConnectable< ? super T> node){
        nodeConnectedToOut_ = node;
    }
    public OutputConnectable< ? super T> getNodeConnectedToOut(){
        return nodeConnectedToOut_;
    }
}
