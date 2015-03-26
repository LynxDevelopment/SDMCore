package com.lynxspa.coac.importers.nodes;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.lynxit.fpm.admin.config.NodeBeautifier;
import com.lynxit.fpm.admin.config.annotations.ConfigParam;
import com.lynxit.fpm.nodes.OutputConnectable;
import com.lynxit.fpm.nodes.support.InternalNodeSupport;
import com.lynxit.fpm.resources.Resource;
import com.lynxspa.sdm.dictionaries.formats.CAFormat;
import com.lynxspa.sdm.dictionaries.logs.LogErrorDict;
import com.lynxspa.sdm.dictionaries.logs.LogInfoDict;
import com.lynxspa.sdm.entities.events.messages.CAEventMessage;
import com.lynxspa.coac.importers.EventMessageImportBean;
import com.lynxspa.coac.importers.ImportParserAdapter;
import com.lynxspa.sdm.managers.SDMConfigManager;
import com.lynxspa.entities.application.logs.utils.LogUtils;


/**
 * @author albert.farre
 */
@NodeBeautifier(description="Message Parser", category="CorporateActionsCore", smallIcon = "../../../../lynxit/fpm/nodes/icons/parser_16.gif", largeIcon = "../../../../lynxit/fpm/nodes/icons/parser_32.gif")
public abstract class ParserNode extends InternalNodeSupport<InputStream> {
	
	public static final Logger logger = Logger.getLogger(ParserNode.class);
	
	public static final String MESSAGEPOSITION="ParserNode.position";
	
	protected OutputConnectable<? super EventMessageImportBean> nodeConnectedToOut_;
	protected String user=null;
	protected Resource<Session> resource=null;
	protected String locale=null;
	protected File errorPath=null;
	protected String errorExtension=null;
	private FileWriter errorWritter=null;
	protected CAFormat format=null;
	protected int bufferSize=8192;
	protected ImportParserAdapter parser=null;
	private int messagesFound=0;
	private int messagesGrouped=0;
	private int messagesIgnored=0;
	private int messagesFiltered=0;
	private int messagesDuplicated=0;
	private int messagesWithErrors=0;
	
	public ParserNode() {
		super();
	}

	@Override
	protected final void processMessage(InputStream _message) throws Exception{
		
		BufferedReader reader=null;
		long processingTime=0l;

		final SDMConfigManager manager=SDMConfigManager.getInstance();
		final Session session=this.resource.getCurrentInstance();
		try{
			this.errorWritter=null;
			reader=new BufferedReader(new InputStreamReader(_message),this.bufferSize);
			processingTime=System.currentTimeMillis();
			LogUtils.createLog(session,this.user,manager.getBundleName(),new Locale(this.locale), manager.getApplication(resource.getCurrentInstance()), LogInfoDict.START_PARSE, new Object[]{this.format.getCode(),this.getBusinessProcess().getContextAttribute("fileName")},null);
			this.messagesGrouped=0;
			this.messagesIgnored=0;
			this.messagesFiltered=0;
			this.messagesDuplicated=0;
			this.messagesWithErrors=0;
			this.messagesFound=processFile(session,manager,reader);
			reader.close();
			processingTime=System.currentTimeMillis()-processingTime;
			LogUtils.createLog(session,this.user,manager.getBundleName(),new Locale(this.locale), manager.getApplication(resource.getCurrentInstance()), LogInfoDict.END_PARSE, new Object[]{this.format.getCode(),this.getBusinessProcess().getContextAttribute("fileName"),processingTime,this.messagesFound,this.messagesIgnored,this.messagesGrouped,this.messagesFiltered,this.messagesDuplicated,this.messagesWithErrors,this.messagesFound-(this.messagesIgnored+this.messagesGrouped+this.messagesFiltered+this.messagesDuplicated+this.messagesWithErrors)},null);
		}catch(Exception e){
			LogUtils.createLog(session,this.user,manager.getBundleName(),new Locale(this.locale), manager.getApplication(resource.getCurrentInstance()), LogErrorDict.PARSING_EXCEPTION, new Object[]{this.format.getCode(),this.getBusinessProcess().getContextAttribute("fileName"),e.getStackTrace(),0},e);
			throw e;
		}finally{
			if(reader!=null){
				reader.close();
				reader=null;
			}
			if(this.errorWritter!=null){
				this.errorWritter.close();
				this.errorWritter=null;
			}
		}
	}

	protected final void processMessage (Session _session,SDMConfigManager _manager,int _position,String _message) throws Exception{
		
		File errorFile=null;
		
		try{
			getBusinessProcess().setContextAttribute(ParserNode.MESSAGEPOSITION, _position);
			EventMessageImportBean importedMessage=new EventMessageImportBean();
			importedMessage.setMessage(new CAEventMessage(this.user));
			if(this.parser.parse(importedMessage,_message,_position)){
				if(processInternalMessage(_session,_manager,_position,_message,importedMessage)){
					continueProcessingMessage(importedMessage);
					if(importedMessage.isDiscartedByDuplication()) this.messagesDuplicated++;
					if(importedMessage.isFiltered()) this.messagesFiltered++;
					if(importedMessage.isGrouped()) this.messagesGrouped++;
				}else{
					this.messagesIgnored++;
				}
			}else{
				this.messagesIgnored++;
			}
		}catch(Throwable e){
			this.messagesWithErrors++;
			final Object messageOffset=(e instanceof ParseException)? ((ParseException)e).getErrorOffset() : "unkown position";
			LogUtils.createLog(_session,this.user,_manager.getBundleName(),new Locale(this.locale),_manager.getApplication(resource.getCurrentInstance()), LogErrorDict.PARSING_EXCEPTION, new Object[]{this.format.getCode(),this.getBusinessProcess().getContextAttribute("fileName"),messageOffset,_position},e);
			if(this.errorWritter==null){
				String st1=String.valueOf(this.getBusinessProcess().getContextAttribute("fileName"));
				st1=(((this.errorExtension!=null)&&(!"".equals(this.errorExtension)))? st1+'.'+errorExtension : st1);
				errorFile=new File(this.errorPath.getPath()+'/'+st1);
				errorFile.createNewFile();
				this.errorWritter=new FileWriter(errorFile);
			}
			this.errorWritter.write("-----------> "+e.getMessage()+": \n"+ _message);
		}
	}

	protected void continueProcessingMessage (EventMessageImportBean _importedMessage) throws Exception{
		getNodeConnectedToOut().process(_importedMessage);
	}

	protected abstract int processFile (Session _session,SDMConfigManager _manager,BufferedReader _reader) throws Exception;
	protected abstract boolean processInternalMessage (Session _session,SDMConfigManager _manager,int _position,String _message,EventMessageImportBean _importedMessage) throws Exception;
	
	
	@ConfigParam(required = true, description = "DefaultUser")
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

	@ConfigParam(required=true,description="locale",group="config")
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}

	@ConfigParam(required=true,description="error path",group="config")
	public File getErrorPath() {
		return errorPath;
	}
	public void setErrorPath(File errorPath) {
		this.errorPath = errorPath;
	}

	@ConfigParam(required=false,description="error extension",group="config")
	public String getErrorExtension() {
		return errorExtension;
	}
	public void setErrorExtension(String errorExtension) {
		this.errorExtension = errorExtension;
	}

	public void connectNodeToOut(OutputConnectable< ? super EventMessageImportBean> node){
        nodeConnectedToOut_ = node;
    }
    public OutputConnectable< ? super EventMessageImportBean> getNodeConnectedToOut(){
        return nodeConnectedToOut_;
    }
}
