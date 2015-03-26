package com.lynxspa.coac.importers.swift.nodes;

import java.util.HashMap;
import java.util.Map;

import com.lynxit.fpm.admin.config.NodeBeautifier;
import com.lynxit.fpm.admin.config.annotations.NodeConnection;
import com.lynxit.fpm.nodes.ExceptionOutputConnectable;
import com.lynxit.fpm.nodes.InternalNode;
import com.lynxit.fpm.nodes.OutputConnectable;
import com.lynxit.fpm.nodes.support.NodeSupport;
import com.lynxspa.coac.importers.EventMessageImportBean;


@NodeBeautifier(description = "Swift Switch Node", category = "CorporateActionsCore", smallIcon = "../../../../../lynxit/fpm/nodes/icons/switch_16.gif", largeIcon = "../../../../../lynxit/fpm/nodes/icons/switch_32.gif")
public class SwiftSwitchNode<T extends EventMessageImportBean> extends NodeSupport implements InternalNode<T> {

	private ExceptionOutputConnectable<? super T> exceptionNode;
	private Map<String,OutputConnectable<? super T>> connectionNodes;
	OutputConnectable<? super T> connectionNodeDefault;
	
	public SwiftSwitchNode(){
		connectionNodes=new HashMap<String, OutputConnectable<? super T>>();
	}
	
	
	@NodeConnection(order = 1)
	public OutputConnectable<? super T> getNodeConnectedToMT564() {
		return connectionNodes.get("564");
	}
	public void connectNodeToMT564(OutputConnectable<? super T> node) {
		connectionNodes.put("564",node);
	}

	@NodeConnection(order = 2)
	public OutputConnectable<? super T> getNodeConnectedToMT565() {
		return connectionNodes.get("565");
	}
	public void connectNodeToMT565(OutputConnectable<? super T> node) {
		connectionNodes.put("565",node);
	}

	@NodeConnection(order = 3)
	public OutputConnectable<? super T> getNodeConnectedToMT566() {
		return connectionNodes.get("566");
	}
	public void connectNodeToMT566(OutputConnectable<? super T> node) {
		connectionNodes.put("566",node);
	}
	
	@NodeConnection(order = 4)
	public OutputConnectable<? super T> getNodeConnectedToMT567() {
		return connectionNodes.get("567");
	}
	public void connectNodeToMT567(OutputConnectable<? super T> node) {
		connectionNodes.put("567",node);
	}
	
	@NodeConnection(order = 5)
	public OutputConnectable<? super T> getNodeConnectedToMT568() {
		return connectionNodes.get("568");
	}
	public void connectNodeToMT568(OutputConnectable<? super T> node) {
		connectionNodes.put("568",node);
	}

	@NodeConnection(order = 6)
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
			selectedExit=connectionNodes.get(message.getMessageType());
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
