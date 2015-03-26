/**
 *
 * This is a node class for the parsing of Bloomberg Securities files (usually .*.out).
 *
 * 
 * @author marco.bonin
 * 
 */

package com.lynxspa.coac.importers.securities.ofival.nodes;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;

import org.hibernate.Session;

import com.lynxit.fpm.admin.config.NodeBeautifier;
import com.lynxit.fpm.admin.config.annotations.ConfigParam;
import com.lynxit.fpm.nodes.OutputConnectable;
import com.lynxit.fpm.nodes.support.InternalNodeSupport;
import com.lynxit.fpm.resources.Resource;
import com.lynxspa.sdm.dictionaries.formats.CAFormat;
import com.lynxspa.sdm.dictionaries.logs.LogInfoDict;
import com.lynxspa.coac.importers.ImportParserAdapter;
import com.lynxspa.coac.importers.securities.ofival.beans.OfivalSecurityBean;
import com.lynxspa.sdm.managers.SDMConfigManager;
import com.lynxspa.entities.application.logs.utils.LogUtils;


@NodeBeautifier(description="Securities Ofival Parser", category="CorporateActionsCore", smallIcon = "../../../../../../lynxit/fpm/nodes/icons/parser_16.gif", largeIcon = "../../../../../../lynxit/fpm/nodes/icons/parser_32.gif")
public class SecuritiesOfivalParserNode extends InternalNodeSupport<InputStream>  {
	
	public static final String DEFAULTDATEFORMAT="yyyyMMdd";
	private int messagesProcessed=0;
	
	@SuppressWarnings("unused")
	private String dateFormat=null;
	protected CAFormat format=null;
	protected ImportParserAdapter parser=null;
	protected Resource<Session> resource=null;	
	protected int bufferSize=8192;
	protected String user=null;	
	protected String locale=null;
	protected OutputConnectable<? super OfivalSecurityBean> nodeConnectedToOut_;
	
	
	public SecuritiesOfivalParserNode(){
		super();
		this.format=CAFormat.OFIVAL;
	}
	
	@Override
	protected final void processMessage(InputStream _message) throws Exception{
		
		BufferedReader reader=null;
		Session session=null;
		SDMConfigManager manager=null;
		long processingTime=0l;
		
		try{
			session=this.resource.getCurrentInstance();
			manager=SDMConfigManager.getInstance();
			this.messagesProcessed=0;
			reader=new BufferedReader(new InputStreamReader(_message),this.bufferSize);
			processingTime=System.currentTimeMillis();
			LogUtils.createLog(session,this.user,manager.getBundleName(),new Locale(this.locale), manager.getApplication(resource.getCurrentInstance()), LogInfoDict.START_SECURITY_PARSE, new Object[]{this.format.getCode(),this.getBusinessProcess().getContextAttribute("fileName")},null);
			processFile(session,manager,reader);
			reader.close();
			processingTime=System.currentTimeMillis()-processingTime;
			LogUtils.createLog(session,this.user,manager.getBundleName(),new Locale(this.locale), manager.getApplication(resource.getCurrentInstance()), LogInfoDict.END_SECURITY_PARSE, new Object[]{this.format.getCode(),this.getBusinessProcess().getContextAttribute("fileName"),processingTime,this.messagesProcessed},null);
		}catch(Exception e){
			throw e;
		}
	}
	
	protected void processFile (Session _session,SDMConfigManager _manager,BufferedReader _reader) throws Exception{
		
		String readedString=null;
		int processingLine=0;
		String[] values=null;
		OfivalSecurityBean security=null;
		
		readedString=_reader.readLine();
		processingLine++;
		while(readedString!=null){
			values=readedString.split(";");
			security=new OfivalSecurityBean();
			security.setFile((String)this.getBusinessProcess().getContextAttribute("fileName"));
			security.setPosition(processingLine);
			if(values.length>0)
				security.setName(values[0]);
			if(values.length>1)
				security.setCodigoEmisora(values[1]);
			if(values.length>2)
				security.setIsin(values[2]);
			if(security.getIsin()!=null){
				getNodeConnectedToOut().process(security);
				this.messagesProcessed++;
			}
			readedString=_reader.readLine();
			processingLine++;
		}
	}
	
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

	public void connectNodeToOut(OutputConnectable< ? super OfivalSecurityBean> node){
        nodeConnectedToOut_ = node;
    }
    public OutputConnectable< ? super OfivalSecurityBean> getNodeConnectedToOut(){
        return nodeConnectedToOut_;
    }
}

