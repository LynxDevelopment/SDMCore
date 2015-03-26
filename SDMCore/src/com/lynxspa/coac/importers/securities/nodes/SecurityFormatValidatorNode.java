package com.lynxspa.coac.importers.securities.nodes;

import java.io.File;

import org.hibernate.Session;

import com.lynxit.fpm.admin.config.NodeBeautifier;
import com.lynxit.fpm.admin.config.annotations.ConfigParam;
import com.lynxit.fpm.events.fileevents.FileEvent;
import com.lynxit.fpm.nodes.OutputConnectable;
import com.lynxit.fpm.nodes.support.InternalNodeSupport;
import com.lynxit.fpm.resources.Resource;
import com.lynxspa.sdm.dictionaries.formats.CAFormat;
import com.lynxspa.sdm.dictionaries.securities.securityfinancialassets.CASecurityFinancialAssets;
import com.lynxspa.sdm.managers.SDMConfigManager;
import com.lynxspa.exception.FPMException;
import com.lynxspa.exception.dict.BasicErrorDict;


/**
 * @author albert.farre
 */
@NodeBeautifier(description="Security Format validator", category="CorporateActionsCore", smallIcon = "../../../../../lynxit/fpm/nodes/icons/switch_16.gif", largeIcon = "../../../../../lynxit/fpm/nodes/icons/switch_32.gif")
public class SecurityFormatValidatorNode extends InternalNodeSupport<FileEvent> {

	private OutputConnectable<? super FileEvent> nodeConnectedToBloomberg;
	private OutputConnectable<? super FileEvent> nodeConnectedToOfival;
	private OutputConnectable<? super FileEvent> nodeConnectedToCustomer;


	private File customerFilePath=null;
	private File bloombergFilePath=null;
	private File ofivalFilePath=null;
	private File fundsFilePath=null;
	private File bloombergFundsFilePath=null;
	private File bloombergRentaFijaFilePath=null;
	private File bloombergRVNoEuroFilePath=null;
	private Resource<Session> resource=null;


	public SecurityFormatValidatorNode() {
		super();
	}

	
    @Override
    protected void processMessage(FileEvent message) throws Exception{

    	OutputConnectable<? super FileEvent> connectionToUse=null;
    	CASecurityFinancialAssets financialAsset=null;
    	File fProcessed = null; 
    		
    	if (message.getFile().getParentFile().equals(this.ofivalFilePath)){
    		try{
        		SDMConfigManager.getInstance().getMessageFormat(this.resource.getCurrentInstance(),CAFormat.OFIVAL.getCode());
        		connectionToUse=getNodeConnectedToOfival();
        	}catch(Exception e){}
    	}else if(message.getFile().getParentFile().equals(this.customerFilePath)){
    		try{
        		connectionToUse=getNodeConnectedToCustomer();
        	}catch(Exception e){}
    	}else{ 
    		try{
    			fProcessed = new File (message.getFile().getParentFile().toString());
    			if (fProcessed.equals(this.bloombergRVNoEuroFilePath)){
    				financialAsset = CASecurityFinancialAssets.NOEUROPEANEQUITY;
    			}else if(fProcessed.equals(this.bloombergFundsFilePath)){
    				financialAsset = CASecurityFinancialAssets.FUNDS;
    			}else if(fProcessed.equals(this.bloombergRentaFijaFilePath)){
    				financialAsset = CASecurityFinancialAssets.DEBT;
    			}else if(fProcessed.equals(this.bloombergFilePath)){
    				financialAsset = CASecurityFinancialAssets.EQUITY;
    			}
    			this.getBusinessProcess().setContextAttribute("financialAsset", financialAsset);
    			
        		SDMConfigManager.getInstance().getMessageFormat(this.resource.getCurrentInstance(),CAFormat.BLOOMBERG.getCode());
        		connectionToUse=getNodeConnectedToBloomberg();
        	}catch(Exception e){
        		throw new FPMException(BasicErrorDict.UNEXPECTED_ERROR,e);
        	}
    		
    	}
    	
    	if(connectionToUse!=null)
    		processSincronMessage(connectionToUse,message);
	}
    
    protected static synchronized void processSincronMessage (OutputConnectable<? super FileEvent> _connected,FileEvent _message) throws Exception{
   
    	_connected.process(_message);
    }
    
	
    @ConfigParam(required = true, description = "bloomberg equity path",group="config")
	public File getBloombergFilePath() {
		return bloombergFilePath;
	}
	public void setBloombergFilePath(File bloombergFilePath) {
		this.bloombergFilePath = bloombergFilePath;
	}

    @ConfigParam(required = true, description = "ofival path",group="config")
	public File getOfivalFilePath() {
		return ofivalFilePath;
	}
	public void setOfivalFilePath(File ofivalFilePath) {
		this.ofivalFilePath = ofivalFilePath;
	}

    @ConfigParam(required = true, description = "customer path",group="config")
	public File getCustomerFilePath() {
		return customerFilePath;
	}
	public void setCustomerFilePath(File customerFilePath) {
		this.customerFilePath = customerFilePath;
	}

	@ConfigParam(required = true, description = "Session",group="config")
	public Resource<Session> getResource() {
		return resource;
	}
	public void setResource(Resource<Session> resource) {
		this.resource = resource;
	}

    @ConfigParam(required = true, description = "funds path",group="config")
	public File getFundsFilePath() {
		return fundsFilePath;
	}
	public void setFundsFilePath(File fundsFilePath) {
		this.fundsFilePath = fundsFilePath;
	}

    @ConfigParam(required = true, description = "funds path",group="config")
	public File getBloombergFundsFilePath() {
		return bloombergFundsFilePath;
	}
	public void setBloombergFundsFilePath(File bloombergFundsFilePath) {
		this.bloombergFundsFilePath = bloombergFundsFilePath;
	}

	@ConfigParam(required = true, description = "fixed rent path",group="config")
	public File getBloombergRentaFijaFilePath() {
		return bloombergRentaFijaFilePath;
	}
	public void setBloombergRentaFijaFilePath(File bloombergRentaFijaFilePath) {
		this.bloombergRentaFijaFilePath = bloombergRentaFijaFilePath;
	}

	@ConfigParam(required = true, description = "no european equity path",group="config")
	public File getBloombergRVNoEuroFilePath() {
		return bloombergRVNoEuroFilePath;
	}
	public void setBloombergRVNoEuroFilePath(File bloombergRVNoEuroFilePath) {
		this.bloombergRVNoEuroFilePath = bloombergRVNoEuroFilePath;
	}

	
	public void connectNodeToOfival(OutputConnectable<? super FileEvent> node){
        nodeConnectedToOfival = node;
    }
    public OutputConnectable<? super FileEvent> getNodeConnectedToOfival(){
        return nodeConnectedToOfival;
    }
	
    public void connectNodeToBloomberg(OutputConnectable<? super FileEvent> node){
        nodeConnectedToBloomberg = node;
    }
    public OutputConnectable<? super FileEvent> getNodeConnectedToBloomberg(){
        return nodeConnectedToBloomberg;
    }
    
    public void connectNodeToCustomer(OutputConnectable<? super FileEvent> node){
        nodeConnectedToCustomer = node;
    }
    public OutputConnectable<? super FileEvent> getNodeConnectedToCustomer(){
        return nodeConnectedToCustomer;
    }
}

