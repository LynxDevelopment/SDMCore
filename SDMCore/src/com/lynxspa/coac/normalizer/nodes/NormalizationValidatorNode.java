package com.lynxspa.coac.normalizer.nodes;

import org.hibernate.Session;

import com.lynxit.fpm.admin.config.NodeBeautifier;
import com.lynxit.fpm.admin.config.annotations.ConfigParam;
import com.lynxit.fpm.nodes.OutputConnectable;
import com.lynxit.fpm.nodes.support.InternalNodeSupport;
import com.lynxit.fpm.resources.Resource;
import com.lynxspa.sdm.dictionaries.config.CAConfiguration;
import com.lynxspa.sdm.dictionaries.logs.LogErrorDict;
import com.lynxspa.sdm.dictionaries.logs.LogInfoDict;
import com.lynxspa.sdm.entities.events.messages.CAEventMessage;
import com.lynxspa.sdm.managers.SDMConfigManager;
import com.lynxspa.entities.securities.SPSecurity;
import com.lynxspa.entities.securities.SPVirtualSecurity;
import com.lynxspa.exception.FPMException;
import com.lynxspa.utils.ReflectionUtils;

/**
 * Checks if there are any normalization installed to normalize this message
 * @author albert.farre
 */
@NodeBeautifier(description="Normalization validator", category="CorporateActionsCore", smallIcon = "../../../../lynxit/fpm/nodes/icons/decision_16.gif", largeIcon = "../../../../lynxit/fpm/nodes/icons/decision_32.gif")
public class NormalizationValidatorNode extends InternalNodeSupport<CAEventMessage> {

	private OutputConnectable<? super CAEventMessage> nodeConnectedToNormalizable;
	private OutputConnectable<? super CAEventMessage> nodeConnectedToNotNormalizable;
	private Resource<Session> resource=null;

	
	@Override
	protected void processMessage(CAEventMessage _message) throws Exception {
		
		try{
			final SPVirtualSecurity security=ReflectionUtils.deproxy(_message.getNormalizedSecurity(), SPVirtualSecurity.class);
			_message.setNormalizedSecurity(security);
			if(security instanceof SPSecurity){
				final Session session=this.resource.getCurrentInstance();
				final SDMConfigManager manager=SDMConfigManager.getInstance();
				final boolean normalizeOnlyMainMarket=(Boolean)manager.getConfiguration(session, CAConfiguration.NORMALIZEONLYMAINMARKET);
				final boolean normalizeOnlyInCustomerPortfolio=(Boolean)manager.getConfiguration(session, CAConfiguration.NORMALIZEONLYINCUSTOMERPORTFOLIO);
				if(((!normalizeOnlyInCustomerPortfolio)||(_message.isInCustomerPortfolio()))&&((!normalizeOnlyMainMarket)||(_message.isMainMarket()))){
					if((manager.getNormalizationScripts(session,_message.getNormalizedEventType(),_message.getMessageType(),_message.getEventProvider()))==null){
						this.getBusinessProcess().setContextAttribute("not.normalizable.reason",LogInfoDict.NORMALIZE_SCRIPT_NOT_FOUND);
						this.getNodeConnectedToNotNormalizable().process(_message);
					}else{
						this.getNodeConnectedToNormalizable().process(_message);
					}
				}else{
					if (normalizeOnlyInCustomerPortfolio && (!_message.isInCustomerPortfolio())){
						this.getBusinessProcess().setContextAttribute("not.normalizable.reason",LogInfoDict.SECURITY_NOT_INPORTFOLIO);
					}else if ((normalizeOnlyMainMarket)&&(!_message.isMainMarket())){
						this.getBusinessProcess().setContextAttribute("not.normalizable.reason",LogInfoDict.SECURITY_MESSAGE_NOT_IN_PRINCIPAL_MARKET);
					}
					
					this.getNodeConnectedToNotNormalizable().process(_message);
				}
			}else{
				this.getBusinessProcess().setContextAttribute("not.normalizable.reason",LogInfoDict.VIRTUALSECURITY_MESSAGE_NOT_NORMALIZABLE);
				this.getNodeConnectedToNotNormalizable().process(_message);
			}
		}catch(FPMException e){
			throw e;
		}catch(Exception e){
			throw new FPMException(LogErrorDict.NORMALIZATION_FAIL,e);
		}
	}

	@ConfigParam(required = true, description = "Session",group="config")
	public Resource<Session> getResource() {
		return resource;
	}
	public void setResource(Resource<Session> resource) {
		this.resource = resource;
	}
	
	
	public void connectNodeToNormalizable(OutputConnectable<? super CAEventMessage> node){
        this.nodeConnectedToNormalizable = node;
    }
    public OutputConnectable<? super CAEventMessage> getNodeConnectedToNormalizable(){
        return this.nodeConnectedToNormalizable;
    }

	public void connectNodeToNotNormalizable(OutputConnectable<? super CAEventMessage> node){
        this.nodeConnectedToNotNormalizable = node;
    }
    public OutputConnectable<? super CAEventMessage> getNodeConnectedToNotNormalizable(){
        return this.nodeConnectedToNotNormalizable;
    }
}
