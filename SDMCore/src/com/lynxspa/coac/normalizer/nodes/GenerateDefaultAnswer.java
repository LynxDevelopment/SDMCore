package com.lynxspa.coac.normalizer.nodes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.lynxit.fpm.admin.config.NodeBeautifier;
import com.lynxit.fpm.admin.config.annotations.ConfigParam;
import com.lynxit.fpm.nodes.support.SingleExitInternalNodeSupport;
import com.lynxit.fpm.resources.Resource;
import com.lynxspa.sdm.dictionaries.logs.LogErrorDict;
import com.lynxspa.sdm.entities.events.answers.CAEventHoldingAnswer;
import com.lynxspa.sdm.entities.events.answers.CAQuestions;
import com.lynxspa.entities.securities.SPSecurityPortfolio;
import com.lynxspa.exception.FPMException;

@NodeBeautifier(description="Generate Default Answers Processor", category="CorporateActionsCore", smallIcon = "../../../../lynxit/fpm/nodes/icons/certificate_16.gif", largeIcon = "../../../../lynxit/fpm/nodes/icons/certificate_32.gif")
public class GenerateDefaultAnswer extends SingleExitInternalNodeSupport<Iterator<CAQuestions>,Iterator<CAEventHoldingAnswer>> {
	
	private String user=null;
	private Resource<Session> resource=null;
	
	@Override
	protected Iterator<CAEventHoldingAnswer> perform(Iterator<CAQuestions> questions) throws Exception {
		Iterator<CAEventHoldingAnswer> reply = null;
		List<CAEventHoldingAnswer> answers=null;
		CAQuestions question = null;
		List<CAQuestions> questions_ = null;
		
		answers=new ArrayList<CAEventHoldingAnswer>();
		if (this.getBusinessProcess().getContextAttribute("questions")!=null){
			questions_ = (List<CAQuestions>)this.getBusinessProcess().getContextAttribute("questions");
			Iterator<CAQuestions> ite = questions_.iterator();
			while(ite.hasNext()){
				question = ite.next();
				if (question.getDefaultValue()!= null && !question.getDefaultValue().equals("")){
					answers = generateAnswer(question);
				}
			}
		}
		
		
		reply= answers.iterator();
		return reply;
	}
	
	private List<CAEventHoldingAnswer> generateAnswer(CAQuestions _question) throws Exception{
		CAEventHoldingAnswer answer= null;
		Query query=null;
		Session session=null;
		HashMap<String,Object> defaultAnswer=null;
		List<CAEventHoldingAnswer> reply=null;
		String key=null;
		try{
			key = _question.getAnswerType().getId()+ _question.getDefaultValue();
			defaultAnswer = new HashMap<String,Object>(1);
			defaultAnswer.put(key, _question.getDefaultValue());
			
			session=this.resource.getCurrentInstance();
			query= session.createQuery("from SPSecurityPortfolio where auditor.deleted=:isDeleted and security.id =:securityId");
			query.setBoolean("isDeleted", false);
			query.setLong("securityId",_question.getEvent().getSecurity().getId());
			
			List<SPSecurityPortfolio> holdings = query.list();
			reply= new ArrayList<CAEventHoldingAnswer>();
			for (SPSecurityPortfolio  holding:holdings){
				answer=new CAEventHoldingAnswer(this.getUser(), _question, holding);
				answer.setDynamicTable(defaultAnswer);
				reply.add(answer);
			}
			
			
		}catch(FPMException e){
			throw e;
		}catch(Exception e){
			throw new FPMException(LogErrorDict.NORMALIZATION_FAIL,e);
		}
		return reply;
	}
	
	@ConfigParam(required=true, group="config", dynamic=true, description="User ho store the entity")
    public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	
	@ConfigParam(required = true, description = "Session",group="config")
	public Resource<Session> getResource() {
		return resource;
	}
	public void setResource(Resource<Session> resource) {
		this.resource = resource;
	}
}
