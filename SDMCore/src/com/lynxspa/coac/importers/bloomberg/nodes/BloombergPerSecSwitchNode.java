package com.lynxspa.coac.importers.bloomberg.nodes;

import java.util.HashMap;
import java.util.Map;

import com.lynxit.fpm.admin.config.NodeBeautifier;
import com.lynxit.fpm.admin.config.annotations.NodeConnection;
import com.lynxit.fpm.nodes.ExceptionOutputConnectable;
import com.lynxit.fpm.nodes.InternalNode;
import com.lynxit.fpm.nodes.OutputConnectable;
import com.lynxit.fpm.nodes.support.NodeSupport;
import com.lynxspa.coac.importers.EventMessageImportBean;
import com.lynxspa.coac.importers.bloomberg.BloombergPerSecParser;


@NodeBeautifier(description = "Bloomberg PerSecurity Switch Node", category = "CorporateActionsCore", smallIcon = "../../../../../lynxit/fpm/nodes/icons/switch_16.gif", largeIcon = "../../../../../lynxit/fpm/nodes/icons/switch_32.gif")
public class BloombergPerSecSwitchNode<T extends EventMessageImportBean> extends NodeSupport implements InternalNode<T> {

	private ExceptionOutputConnectable<? super T> exceptionNode;
	private Map<String,OutputConnectable<? super T>> connectionNodes;
	OutputConnectable<? super T> connectionNodeDefault;
	
	public BloombergPerSecSwitchNode(){
		connectionNodes=new HashMap<String, OutputConnectable<? super T>>();
	}
	
	
	@NodeConnection(order = 1)
	public OutputConnectable<? super T> getNodeConnectedToCPN() {
		return connectionNodes.get(BloombergPerSecParser.EVENT_TYPE_CUPON);
	}
	public void connectNodeToCPN(OutputConnectable<? super T> node) {
		connectionNodes.put(BloombergPerSecParser.EVENT_TYPE_CUPON,node);
	}

	@NodeConnection(order = 2)
	public OutputConnectable<? super T> getNodeConnectedToDefault() {
		return connectionNodeDefault;
	}
	public void connectNodeToDefault(OutputConnectable<? super T> node) {
		connectionNodeDefault=node;
	}

	public ExceptionOutputConnectable<? super T> getNodeConnectedToException() {
		return exceptionNode;
	}
	public void connectNodeToException(ExceptionOutputConnectable<? super T> node) {
		exceptionNode = node;
	}

	@SuppressWarnings("unchecked")
	public void process(T message) throws Exception {
		
		OutputConnectable<? super T> selectedExit=null;
		
		try{
			selectedExit=connectionNodes.get(message.getMessage().getEventType());
			if(selectedExit!=null){
				selectedExit.process(message);
			}else{
				connectionNodeDefault.process(message);
			}
		}catch(Exception e) {
			getNodeConnectedToException().processException(e, message);
		}
	}
}
