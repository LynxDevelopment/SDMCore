package com.lynxspa.coac.plannings.ftpprocess.nodes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.lynxit.fpm.ApplicationDomain;
import com.lynxit.fpm.admin.config.NodeBeautifier;
import com.lynxit.fpm.admin.config.annotations.ConfigParam;
import com.lynxit.fpm.nodes.support.OutputNodeSupport;
import com.lynxit.fpm.resources.Resource;
import com.lynxit.fpm.resources.ResourceId;
import com.lynxspa.sdm.managers.SDMConfigManager;
import com.lynxspa.coac.plannings.dictionaries.FTPActionsDict;
import com.lynxspa.coac.plannings.logs.LogPlanningError;
import com.lynxspa.coac.plannings.logs.LogPlanningInfo;
import com.lynxspa.entities.application.logs.utils.LogUtils;
import com.lynxspa.exception.FPMException;
import com.lynxspa.exception.dict.BasicErrorDict;

/**
 * 
 * @author joseluis.llorente
 *
 */
@NodeBeautifier(description="FTP Process", category="Corporateactionscore", smallIcon = "../../../../../lynxit/fpm/nodes/icons/certificate_16.gif", largeIcon = "../../../../../lynxit/fpm/nodes/icons/certificate_32.gif")
public class FTPResourceOutputNode extends OutputNodeSupport<Object> implements Resource<FTPClient>{

	private static final Logger logger_ = Logger.getLogger(FTPResourceOutputNode.class);
	private static final String BLANK = "";
	//Resource Variables
	private String serverName_;
	private String userId_;
	private String password_;
	private int timeout_;
	private boolean passiveMode_;
	private ResourceId<FTPClient> id_;
	ThreadLocal<FTPClient> currentInstance_ = new ThreadLocal<FTPClient>();
	private String ftpFile;
	private String ftpProxy;
	private String ftpPort;
	private String ftpProxyUser;
	private String ftpProxyPassword;
	private FTPActionsDict ftpAction;
	//File Variables
	private String fileName_;
    private String directory_;
    private boolean overwrite_;
    private boolean binary_;
    private String pathFileToTransfer;
    private boolean deleteFile;
	//Config variables
    protected String locale=null;
	protected String user=null;
	protected Resource<Session> resource=null;
	

	public FTPResourceOutputNode() {
		
	}

	public FTPResourceOutputNode(String id) {
		id_ = new ResourceId<FTPClient>(id);
	}

	public void process(Object message) throws Exception {
		FileOutputStream fos = null;
		FileInputStream fis = null;
		File fileToFTP = null; 
		SDMConfigManager manager=null;
		Session session=null;
		StablishProxy proxy = null;
		boolean proxySettings=false;
		try{
			fileToFTP = new File(pathFileToTransfer);
			session=this.resource.getCurrentInstance();
			manager = SDMConfigManager.getInstance();
			proxy = new StablishProxy();

			proxySettings = !this.ftpProxy.equals(BLANK);
			stablishProxySettings(proxy,proxySettings);
			
			FTPClient ftpClient = getCurrentInstance(session,manager);
	    	if (ftpClient == null || !ftpClient.isConnected()) {
	    		
	    		ftpClient = getNewInstance(session,manager);
			}
	    	
	    	
	    	if (ftpClient!=null){
	    		if (ftpAction.getAction().equals(FTPActionsDict.GET.getAction())){//GET
		    		fos= new FileOutputStream(pathFileToTransfer,!overwrite_);
					LogUtils.createLog(session,this.user,manager.getBundleName(),new Locale(this.locale), 
							manager.getApplication(session), LogPlanningInfo.PROCESS_FTP_GET, 
							new Object[]{(directory_+this.ftpFile), serverName_,userId_, pathFileToTransfer},null);
					logger_.debug("Transferring file " + directory_+this.ftpFile + " from server "+serverName_+ 
							" with user" + userId_ +" to local path "+ pathFileToTransfer);
				}else{//PUT
					fis = new FileInputStream(fileToFTP);
					LogUtils.createLog(session,this.user,manager.getBundleName(),new Locale(this.locale), 
							manager.getApplication(session), LogPlanningInfo.PROCESS_FTP_PUT, 
							new Object[]{pathFileToTransfer, serverName_,userId_, directory_.concat(ftpFile)},null);
					logger_.debug("Transferring local file" + pathFileToTransfer +" to path "+ directory_.concat(ftpFile) + " of the server "+serverName_+ 
							" with user" + userId_ );
				}
	    		
	    		
	    		logger_.debug("Binary mode: " +binary_);
	    		if(binary_)
	        		ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
	        	else 
	        		ftpClient.setFileType(FTP.ASCII_FILE_TYPE);
	        	
	    		if (directory_ != null && directory_.trim().length() > 0){
	    			if (!ftpClient.changeWorkingDirectory(directory_.trim())){
	    				logger_.error("Unable to change directory "+ directory_.trim());
	    				throw new IOException("Unable to change directory "+ directory_.trim());
	    			}
	    		}
	    		
	    		
	    		if (ftpAction.getAction().equals(FTPActionsDict.PUT.getAction())){
	    			try {
			    		logger_.debug("Transferring " + pathFileToTransfer);
			    		if (!ftpClient.appendFile(ftpFile, fis)) {
			    			if (!ftpClient.storeFile(ftpFile, fis)){
			    				logger_.error("Unable to send file " + fileName_+". Review permissions in " +directory_);
			    				LogUtils.createLog(session,this.user,manager.getBundleName(),new Locale(this.locale), 
										manager.getApplication(session), LogPlanningError.FTP_PUT_PERMISSION_FAIL, 
										new Object[]{pathFileToTransfer,directory_,serverName_},null);
			    				throw new IOException("Unable to send file " + fileName_+". Review permissions in " +directory_);
			    			}
			    		}
			    		LogUtils.createLog(session,this.user,manager.getBundleName(),new Locale(this.locale), 
			    				manager.getApplication(session), LogPlanningInfo.PROCESS_FTP_PUT_CORRECT, 
			    				new Object[]{fileName_},null);
			    		logger_.info("Transferred " + pathFileToTransfer + " to " + directory_.concat(ftpFile));
	    			}catch (Exception e) {
	    				LogUtils.createLog(session,this.user,manager.getBundleName(),new Locale(this.locale), 
								manager.getApplication(session), LogPlanningError.FTP_PUT_FAIL, 
								new Object[]{pathFileToTransfer,directory_,serverName_},e);
					}
	    		}else{
	    			try {
	    				logger_.debug("Getting File "+directory_+ftpFile+" to Directory "+fileToFTP.getPath());
	    				boolean received = ftpClient.retrieveFile(ftpFile, fos);
	    				logger_.info("File "+fileToFTP.getPath()+" received correctly="+received);
	    				LogUtils.createLog(session,this.user,manager.getBundleName(),new Locale(this.locale), manager.getApplication(session), 
	    						LogPlanningInfo.PROCESS_FTP_GET_CORRECT, new Object[]{directory_,fileName_},null);
	    				
					} catch (Exception e) {
						LogUtils.createLog(session,this.user,manager.getBundleName(),new Locale(this.locale), 
								manager.getApplication(session), LogPlanningError.FTP_GET_FAIL, 
								new Object[]{(directory_+fileName_),serverName_},e);
						logger_.error("Error getting file "+directory_+fileName_+" from server: "+serverName_ +": "+e);
						throw new FPMException(BasicErrorDict.UNEXPECTED_ERROR,e);
					}
	    		}
	    		
	    	}else{
	    		throw new Exception("Imposible to obtain FTP Instance");
	    	}
		}catch (Exception e){
    		throw new FPMException(BasicErrorDict.UNEXPECTED_ERROR,e);
    	}finally{
    		try{
	    		releaseCurrentInstance();
	    		if (proxySettings){
	    			releaseProxySettings(proxy);
	    		}
				if (ftpAction.getAction().equals(FTPActionsDict.GET.getAction())){
					if (fos!=null){
						fos.close();
					}
				}
				else{
					if (fis!=null){
						fis.close();
					}
				}
    		}catch (Exception e) {
    			logger_.error("Error closing Stream file. FTP Action: "+ftpAction.getAction()+". Error:"+e);
    			throw new FPMException(BasicErrorDict.UNEXPECTED_ERROR,e);
			}
    	}
    	
    	
	}

	public FTPClient getCurrentInstance() throws Exception {

        return null;
	}

	public FTPClient getCurrentInstance(Session _session, SDMConfigManager _manager) throws Exception {
		
		FTPClient instance = currentInstance_.get();
        if (instance == null)
        {
            instance = getNewInstance(_session, _manager);
            currentInstance_.set(instance);
        }
        return instance;
	}

	public FTPClient getNewInstance(Session _session, SDMConfigManager _manager) throws Exception {
		logger_.info("Creating new connection to "+serverName_+", user="+userId_);
		
		FTPClient ftpClient = new FTPClient();
		ftpClient.setDataTimeout(timeout_);

		if (passiveMode_)
			ftpClient.enterLocalPassiveMode();
		else
			ftpClient.enterLocalActiveMode();

		//connect to ftp server
		try {
			ftpClient.connect(serverName_);
		} catch (java.net.SocketException e) {
			LogUtils.createLog(_session,this.user,_manager.getBundleName(),new Locale(this.locale), 
					_manager.getApplication(_session), LogPlanningError.FTP_CONNECT_SERVER_FAIL, 
					new Object[]{serverName_},e);
			logger_.error("Couldn�t connect to server: "+serverName_);
			return null;
		}
		
		try {
			ftpClient.login(userId_, password_);
		} catch (Exception e) {
			logger_.error("Couldn�t login to server: "+serverName_);
			LogUtils.createLog(_session,this.user,_manager.getBundleName(),new Locale(this.locale), 
					_manager.getApplication(_session), LogPlanningError.FTP_CONNECT_LOGIN_FAIL, 
					new Object[]{serverName_,userId_},e);
			return null;
		}

		return ftpClient;
	}
	
	public FTPClient getNewInstance() throws Exception {
		return null;
	}
	
	

	@Override
    public void init() throws Exception
    {
        super.init();
    }

	public ResourceId<FTPClient> getResourceId() {
		return id_;
	}



	public void releaseCurrentInstance() throws Exception {
		FTPClient instance = currentInstance_.get();
        if (instance != null)
        {
            closeInstance(instance);
            currentInstance_.remove();
        }
        
	}
	
	protected void releaseProxySettings(StablishProxy _proxy) throws Exception {

		_proxy.setSocksProxyHost(BLANK);
		_proxy.stablish();
		
	}

	protected void closeInstance(FTPClient instance) throws Exception {

		try {
			instance.logout();
			instance.disconnect();
		} catch (IOException e) {
			// do nothing
		}

	}

	public void terminate() {
		
	}

	public void test() throws Exception {
		
	}

	
	private void stablishProxySettings(StablishProxy _proxy, boolean _proxySettings) throws Exception{
		try{
			if (_proxySettings){
				_proxy.setSocksProxyHost(this.ftpProxy);
				_proxy.setSocksProxyPort(Integer.parseInt(this.ftpPort));
				_proxy.setProxyUser(!this.ftpProxyUser.equals("")?this.ftpProxyUser:null);
				_proxy.setProxyPassword(!this.ftpProxyPassword.equals("")?this.ftpProxyPassword:null);
				_proxy.stablish();
			}
		}catch (Exception e) {
			logger_.error("Error setting proxy properties. "+e);
			throw new Exception("Error setting proxy properties. "+e);
		}
	}
	
	@ConfigParam(required = false, dynamic= true, defaultValue = "true", group="FTP Config")
	public boolean isPassiveMode() {
		return passiveMode_;
	}

	public void setPassiveMode(boolean passiveMode) {
		passiveMode_ = passiveMode;
	}

	@ConfigParam(required = true, dynamic = true, group="FTP Config")
	public String getPassword() {
		return password_;
	}

	public void setPassword(String password) {
		password_ = password;
	}

	@ConfigParam(required = true, dynamic = true, group="FTP Config")
	public String getServerName() {
		return serverName_;
	}

	public void setServerName(String serverName) {
		serverName_ = serverName;
	}

	@ConfigParam(required = false, dynamic = true, group="FTP Config")
	public int getTimeout() {
		return timeout_;
	}

	public void setTimeout(int timeout) {
		timeout_ = timeout;
	}

	@ConfigParam(required = true, dynamic = true, group="FTP Config")
	public String getUserId() {
		return userId_;
	}

	public void setUserId(String userId) {
		userId_ = userId;
	}
	
	@ConfigParam(required = true,  group="FTP Config", description="PUT/GET Action")
	public FTPActionsDict getFtpAction() {
		return ftpAction;
	}

	public void setFtpAction(FTPActionsDict ftpAction) {
		this.ftpAction = ftpAction;
	}

	@ConfigParam(required = true, dynamic = true,  group="FTP Config", description="FTP File")
	public String getFtpFile() {
		return ftpFile;
	}

	public void setFtpFile(String ftpFile) {
		this.ftpFile = ftpFile;
	}
	
	@ConfigParam(required = false, dynamic = true, group="FTP Config", description="FTP PROXY")
	public String getFtpProxy() {
		return ftpProxy;
	}

	public void setFtpProxy(String ftpProxy) {
		this.ftpProxy = ftpProxy;
	}

	@ConfigParam(required = false, dynamic = true, group="FTP Config", description="FTP PORT")
	public String getFtpPort() {
		return ftpPort;
	}

	public void setFtpPort(String ftpPort) {
		this.ftpPort = ftpPort;
	}

	@ConfigParam(required = false, dynamic = true, group="FTP Config", description="Proxy User")
	public String getFtpProxyUser() {
		return ftpProxyUser;
	}

	public void setFtpProxyUser(String ftpProxyUser) {
		this.ftpProxyUser = ftpProxyUser;
	}

	@ConfigParam(required = false, dynamic = true, group="FTP Config", description="Proxy Password")
	public String getFtpProxyPassword() {
		return ftpProxyPassword;
	}

	public void setFtpProxyPassword(String ftpProxyPassword) {
		this.ftpProxyPassword = ftpProxyPassword;
	}
	
	@ConfigParam(required = true,dynamic=true, group="File")
    public String getFileName()
    {
        return fileName_;
    }

    public void setFileName(String fileName)
    {
        fileName_ = fileName;
    }
	
	@ConfigParam(required = false,dynamic=true, group="File")
	public String getDirectory() {
		return directory_;
	}

	public void setDirectory(String directory) {
		directory_ = directory;
	}
	@ConfigParam(required = false, dynamic=true, defaultValue = "false", group="File")
	public boolean isOverwrite() {
		return overwrite_;
	}

	public void setOverwrite(boolean overwrite) {
		overwrite_ = overwrite;
	}

	@ConfigParam(required = false,defaultValue="true",dynamic=true, group="File")
	public boolean isBinary()
	{
		return binary_;
	}

	public void setBinary(boolean binary)
	{
		binary_ = binary;
	}

	@ConfigParam(required = true,dynamic=true, group="File")
	public String getPathFileToTransfer() {
		return pathFileToTransfer;
	}

	public void setPathFileToTransfer(String pathFileToTransfer) {
		this.pathFileToTransfer = pathFileToTransfer;
	}

	@ConfigParam(required = false, defaultValue = "true", group="File", description="Delete file when FTP finished correctly")
	public boolean isDeleteFile() {
		return deleteFile;
	}

	public void setDeleteFile(boolean deleteFile) {
		this.deleteFile = deleteFile;
	}

	@ConfigParam(required=true,description="Locale", group="config")
	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	@ConfigParam(required=true,description="User", group="config")
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	@ConfigParam(required=true,description="Resource", group="config")
	public Resource<Session> getResource() {
		return resource;
	}
	
	public void setResource(Resource<Session> resource) {
		this.resource = resource;
	}

	public ApplicationDomain getApplicationDomain() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setApplicationDomain(ApplicationDomain applicationDomain) {
		// TODO Auto-generated method stub
		
	}

	public FTPClient getNamedInstance(String instanceName) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public void releaseNamedInstance(String instanceName) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void releaseAllInstances() {
		// TODO Auto-generated method stub
		
	}
	
}
