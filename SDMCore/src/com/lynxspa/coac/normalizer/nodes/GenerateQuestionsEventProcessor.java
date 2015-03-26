package com.lynxspa.coac.normalizer.nodes;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

import org.hibernate.Session;

import com.lynxit.fpm.admin.config.NodeBeautifier;
import com.lynxit.fpm.admin.config.annotations.ConfigParam;
import com.lynxit.fpm.nodes.support.SingleExitInternalNodeSupport;
import com.lynxit.fpm.resources.Resource;
import com.lynxspa.sdm.dictionaries.answers.CAAnswerTypes;
import com.lynxspa.sdm.entities.events.CAEventCollected;
import com.lynxspa.sdm.entities.events.answers.CAAnswerType;
import com.lynxspa.sdm.entities.events.answers.CAQuestions;
import com.lynxspa.coac.normalizer.beans.CAQuestionBean;
import com.lynxspa.coac.normalizer.beans.OptionBean;
import com.lynxspa.utils.StringUtils;

/**
 * Generate Questions Event Processor <br>
 * if normalization fails log an error and launch an exception<br>
 * 
 * @author jose luis
 *
 */
@NodeBeautifier(description="Generate Questions Event Processor", category="CorporateActionsCore", smallIcon = "../../../../lynxit/fpm/nodes/icons/certificate_16.gif", largeIcon = "../../../../lynxit/fpm/nodes/icons/certificate_32.gif")
public class GenerateQuestionsEventProcessor extends SingleExitInternalNodeSupport<CAQuestionBean, Iterator<CAQuestions>> {

	private static final String BLOCK_START 	= "16R:CAOPTN";
	private static final String BLOCK_END 		= "16S:CAOPTN";
	private static final String CASHMOVE_OPTION	= "CASHMOVE";
	private static final String SECMOVE_OPTION	= "SECMOVE";
	private static final String FIA_OPTION	= "FIA";
	
	private String user=null;
	private Resource<Session> resource=null;
	@Override
	protected Iterator<CAQuestions> perform(CAQuestionBean message) throws Exception {
		Iterator<CAQuestions> reply = null;		
		if (!message.isMandatory() && message.getOriginalMessage()!=null){
			reply = generateEventQuestions(message);
			//this.getBusinessProcess().setContextAttribute("questions", reply);
		}else{
			reply=new ArrayList<CAQuestions>().iterator();
			this.getBusinessProcess().setContextAttribute("questions", null);
		}

		return reply;
	}

	
	private Iterator<CAQuestions> generateEventQuestions (CAQuestionBean _question) throws Exception{
		Scanner scanner=null;
		String message= null;
		String block = null;
		List<Map<String,OptionBean>> options = null;
		CAQuestions question = null;
		CAQuestions header = null;
		List<CAQuestions> questions=null;
		Iterator<CAQuestions> reply = null;
		final Session session=this.resource.getCurrentInstance();
    	
		message = _question.getOriginalMessage();
		scanner=new Scanner(message);
		scanner.useDelimiter(Pattern.compile("\\{(?=\\w:)|\\}[^{]*\\{(?=\\w:)|\\-\\}[^{]*\\{(?=\\w:)|(?<=\\})}",Pattern.MULTILINE));
		while(scanner.hasNext()){
			block=scanner.next();
			if(Character.isLetterOrDigit(block.charAt(0))){
				if (block.charAt(0)=='4'){
					options = parseBlock4Fin(block);
				}
			}
		}
		scanner.close();
		questions = new ArrayList<CAQuestions>();
		for(Map<String,OptionBean> option:options){
			question = generateQuestions(option, _question.getEvent(),session, header);
			questions.add(question);
		}
		this.getBusinessProcess().setContextAttribute("questions", questions);
		reply = questions.iterator(); 
		
		return reply;
	}

	
	protected List<Map<String,OptionBean>> parseBlock4Fin(String _block) throws ParseException{

		Scanner fieldScanner=null;
		String fullField=null;
		String field="";
		String value="";
		boolean isQuestion=false;
		boolean isSubBlock=false;
		Map<String,OptionBean> replyMap=null;
		List<Map<String,OptionBean>> reply=null;
		
		
		fieldScanner=new Scanner(_block);
		
		fieldScanner.useDelimiter(Pattern.compile("(\\r\\n|\\n)\\:(?=\\d{2}\\D{1}?\\:)",Pattern.MULTILINE));
		reply = new ArrayList<Map<String,OptionBean>>();
		while(fieldScanner.hasNext()){
			fullField=StringUtils.checkNotExist(fieldScanner.next(),"").trim();
			if(fullField.length()>0 && fullField.indexOf('/')!=-1){
				int position=fullField.indexOf('/');
				field=fullField.substring(0,position);
				value=fullField.substring(position+2,fullField.length());
				if(BLOCK_START.equals(field)){
					isQuestion=true;
					replyMap=new HashMap<String,OptionBean>();
				}
				/*else if((field!=null) && (BLOCK_END.equals(field)) && isQuestion){
					isQuestion=false;
					reply.add(replyMap);
				}*/
				else if(field!=null && isQuestion){
					
					isSubBlock =isSubBLockField(field,value,isSubBlock);
					if (!isSubBlock && !BLOCK_END.equals(field)){
						if (replyMap.get(field)==null)
							replyMap.put(field, parseField(field, value));
					}else{
						
					}
				}
			}else if(BLOCK_START.equals(fullField)){
				isQuestion=true;
				replyMap=new HashMap<String,OptionBean>();
			}else if(BLOCK_END.equals(fullField)){
				isQuestion=false;
				reply.add(replyMap);
			}
		}
		fieldScanner.close();
		
		return reply;
	}
	
	private CAQuestions generateQuestions (Map<String,OptionBean> _optionsFields, CAEventCollected _event, Session _session, CAQuestions _header) throws ParseException {
		CAQuestions reply=null;
		OptionBean option=null;
		String numberOption=null;
		CAAnswerType answerType = null;
		String questionText=null;
		
		reply = new CAQuestions();
		
		option = _optionsFields.get("13A::CAON");
		numberOption = option.getValue();
		
		option = _optionsFields.get("22F::CAOP");
		questionText = option.getValue();
		if (questionText.equals("OTHR")){
			if (_optionsFields.get("70E")!=null){
				questionText = _optionsFields.get("70E").getValue();
			}
		}
		reply.setText("question."+option.getValue());
		
		option = _optionsFields.get("17B::DFLT");
		if (option.getValue().equals("Y")){
			reply.setDefaultValue(String.valueOf(Integer.parseInt(numberOption)));
		}
		
		answerType =(CAAnswerType)_session.get(CAAnswerType.class,CAAnswerTypes.CHAR.getCode());
		reply.setAnswerType(answerType);
		reply.setPosition(1);
		reply.setHeader(false);
		reply.setHeaderGroup(_header!=null?_header.getHeaderGroup():0);
		reply.setHeaderPosition(Integer.parseInt(numberOption));
		reply.setHidden(false);
		reply.setEvent(_event);
		reply.setOptional(true);
		
		return reply;
	}
	
	private CAQuestions generateHeader (CAEventCollected _event) throws ParseException {
		CAQuestions reply = null;
		reply = new CAQuestions();
		
		reply.setText("Opciones");
		reply.setHeader(true);
		reply.setHeaderPosition(0);
		reply.setHidden(false);
		reply.setEvent(_event);
		reply.setOptional(false);
		
		return reply;
	}
	
	private OptionBean parseField(String _field, String _value) throws ParseException{
		OptionBean reply=null;
		
		try{
			reply = new OptionBean(_field, _value );
			
		}catch (Exception e){
			System.err.println("Error para field " +_value);
		}
		return reply;
	}
	
	private boolean isSubBLockField(String _field, String _value, boolean _isSubBlock) throws ParseException{
		boolean reply=_isSubBlock;
		if (BLOCK_START.equals(_field) && (_value.equals(CASHMOVE_OPTION) || _value.equals(FIA_OPTION)) || _value.equals(SECMOVE_OPTION) ){
			reply = true;
		}
		else if (reply && BLOCK_END.equals(_field) && (_value.equals(CASHMOVE_OPTION) || _value.equals(FIA_OPTION)) || _value.equals(SECMOVE_OPTION) ){
			reply = false;
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
