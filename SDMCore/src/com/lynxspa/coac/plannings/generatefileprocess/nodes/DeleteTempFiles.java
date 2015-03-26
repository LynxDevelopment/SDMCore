package com.lynxspa.coac.plannings.generatefileprocess.nodes;

import java.io.File;

import org.apache.log4j.Logger;

import com.lynxit.fpm.admin.config.NodeBeautifier;
import com.lynxit.fpm.admin.config.annotations.ConfigParam;
import com.lynxit.fpm.nodes.OutputConnectable;
import com.lynxit.fpm.nodes.support.InternalNodeSupport;
import com.lynxspa.coac.plannings.beans.PlanningBean;

/**
 * 
 * @author joseluis.llorente
 *
 */
@NodeBeautifier(description="Delete Temporal Files", category="Corporateactionscore", smallIcon = "../../../../../lynxit/fpm/nodes/icons/certificate_16.gif", largeIcon = "../../../../../lynxit/fpm/nodes/icons/certificate_32.gif")
public class DeleteTempFiles <T extends PlanningBean> extends InternalNodeSupport <T> {

	private final Logger logger = Logger.getLogger(DeleteTempFiles.class);
	private OutputConnectable< ? super T> nodeConnectedToOut_;
	private String fileToDelete = null;
	private String locale=null;
	private String user=null;

	
	@Override
	protected void processMessage(T message) throws Exception{
		logger.debug("Deleting temporal Files: "+this.fileToDelete);
		File filedeleted = null;
		filedeleted = new File(this.fileToDelete);
		if (filedeleted.exists()){
			if(filedeleted.delete()){
				logger.debug("File "+this.fileToDelete +" deleted correctly.");
			}else{
				logger.warn("File "+this.fileToDelete +" could not be deleted");
			}
		}else{
			logger.warn("File "+this.fileToDelete +" does not exist.");
		}

		getNodeConnectedToOut().process(message);
	}

	public void connectNodeToOut(OutputConnectable< ? super T> node){
        nodeConnectedToOut_ = node;
    }

	public OutputConnectable< ? super T> getNodeConnectedToOut(){
        return nodeConnectedToOut_;
	}

	@ConfigParam(required=true,description="FileToDelete",dynamic=true)
	public String getFileToDelete() {
		return fileToDelete;
	}

	public void setFileToDelete(String fileToDelete) {
		this.fileToDelete = fileToDelete;
	}

	@ConfigParam(required=true)
	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	@ConfigParam(required=true)
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}



}
