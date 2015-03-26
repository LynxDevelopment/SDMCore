package com.lynxspa.coac.importers.ofival.nodes;

import java.util.HashMap;
import java.util.Map;

import com.lynxit.fpm.admin.config.NodeBeautifier;
import com.lynxit.fpm.admin.config.annotations.NodeConnection;
import com.lynxit.fpm.nodes.ExceptionOutputConnectable;
import com.lynxit.fpm.nodes.InternalNode;
import com.lynxit.fpm.nodes.OutputConnectable;
import com.lynxit.fpm.nodes.support.NodeSupport;
import com.lynxspa.coac.importers.EventMessageImportBean;


@NodeBeautifier(description = "Ofival Switch Node", category = "CorporateActionsCore", smallIcon = "../../../../../lynxit/fpm/nodes/icons/switch_16.gif", largeIcon = "../../../../../lynxit/fpm/nodes/icons/switch_32.gif")
public class OfivalSwitchNode<T extends EventMessageImportBean> extends NodeSupport implements InternalNode<T> {

	private ExceptionOutputConnectable<? super T> exceptionNode;
	private Map<String,OutputConnectable<? super T>> connectionNodes;
	OutputConnectable<? super T> connectionNodeDefault;
	
	public OfivalSwitchNode(){
		connectionNodes=new HashMap<String, OutputConnectable<? super T>>();
	}
	
	
	@NodeConnection(order = 1)
	public OutputConnectable<? super T> getNodeConnectedToAmp() {
		return connectionNodes.get("AMP");
	}
	public void connectNodeToAmp(OutputConnectable<? super T> node) {
		connectionNodes.put("AMP",node);
	}

	@NodeConnection(order = 2)
	public OutputConnectable<? super T> getNodeConnectedToDac() {
		return connectionNodes.get("DAC");
	}
	public void connectNodeToDac(OutputConnectable<? super T> node) {
		connectionNodes.put("DAC",node);
	}

	@NodeConnection(order = 3)
	public OutputConnectable<? super T> getNodeConnectedToDev() {
		return connectionNodes.get("DEV");
	}
	public void connectNodeToDev(OutputConnectable<? super T> node) {
		connectionNodes.put("DEV",node);
	}
	
	@NodeConnection(order = 4)
	public OutputConnectable<? super T> getNodeConnectedToSpl() {
		return connectionNodes.get("SPL");
	}
	public void connectNodeToSpl(OutputConnectable<? super T> node) {
		connectionNodes.put("SPL",node);
	}
	
	@NodeConnection(order = 5)
	public OutputConnectable<? super T> getNodeConnectedToOpa() {
		return connectionNodes.get("OPA");
	}
	public void connectNodeToOpa(OutputConnectable<? super T> node) {
		connectionNodes.put("OPA",node);
	}
	
	@NodeConnection(order = 6)
	public OutputConnectable<? super T> getNodeConnectedToOpv() {
		return connectionNodes.get("OPV");
	}
	public void connectNodeToOpv(OutputConnectable<? super T> node) {
		connectionNodes.put("OPV",node);
	}
	
	@NodeConnection(order = 7)
	public OutputConnectable<? super T> getNodeConnectedToCvc() {
		return connectionNodes.get("CVC");
	}
	public void connectNodeToCvc(OutputConnectable<? super T> node) {
		connectionNodes.put("CVC",node);
	}
	
	@NodeConnection(order = 8)
	public OutputConnectable<? super T> getNodeConnectedToThr() {
		return connectionNodes.get("THR");
	}
	public void connectNodeToThr(OutputConnectable<? super T> node) {
		connectionNodes.put("THR",node);
	}
	
	@NodeConnection(order = 9)
	public OutputConnectable<? super T> getNodeConnectedToFus() {
		return connectionNodes.get("FUS");
	}
	public void connectNodeToFus(OutputConnectable<? super T> node) {
		connectionNodes.put("FUS",node);
	}
	
	@NodeConnection(order = 10)
	public OutputConnectable<? super T> getNodeConnectedToFvl() {
		return connectionNodes.get("FVL");
	}
	public void connectNodeToFvl(OutputConnectable<? super T> node) {
		connectionNodes.put("FVL",node);
	}
	
	@NodeConnection(order = 11)
	public OutputConnectable<? super T> getNodeConnectedToRfj() {
		return connectionNodes.get("RFJ");
	}
	public void connectNodeToRfj(OutputConnectable<? super T> node) {
		connectionNodes.put("RFJ",node);
	}
	
	@NodeConnection(order = 12)
	public OutputConnectable<? super T> getNodeConnectedToAms() {
		return connectionNodes.get("AMS");
	}
	public void connectNodeToAms(OutputConnectable<? super T> node) {
		connectionNodes.put("AMS",node);
	}
	
	@NodeConnection(order = 13)
	public OutputConnectable<? super T> getNodeConnectedToCup() {
		return connectionNodes.get("CUP");
	}
	public void connectNodeToCup(OutputConnectable<? super T> node) {
		connectionNodes.put("CUP",node);
	}

	@NodeConnection(order = 14)
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
