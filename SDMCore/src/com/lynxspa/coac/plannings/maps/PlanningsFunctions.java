package com.lynxspa.coac.plannings.maps;

import com.lynxit.fpm.functionlibraries.FunctionLibrarySupport;
import com.lynxit.fpm.nodes.internal.MappingNode;
import com.lynxspa.sdm.entities.plannings.SPFTPGetProcess;
import com.lynxspa.sdm.entities.plannings.SPFTPPutProcess;
import com.lynxspa.sdm.entities.plannings.SPFileProcess;
import com.lynxspa.sdm.entities.plannings.SPShellProcess;
import com.lynxspa.entities.plannings.SPPlanningProcess;

public class PlanningsFunctions extends FunctionLibrarySupport {

	@Override
	@SuppressWarnings("unchecked")
	public MappingNode getNode() {
		return super.getNode();
	}

	@Override
	@SuppressWarnings("unchecked")
	public void setNode(MappingNode node) {
		super.setNode(node);
	}
	
	public Boolean isExecuteShellProcess(SPPlanningProcess _planningProcess){
		boolean reply=false;
		reply = _planningProcess.getProcess() instanceof SPShellProcess;
		return reply;
	}
	
	public Boolean isFTPExportProcess(SPPlanningProcess _planningProcess){
		boolean reply=false;
		reply = _planningProcess.getProcess() instanceof SPFTPPutProcess;
		return reply;
	}
	
	public Boolean isFTPImportProcess(SPPlanningProcess _planningProcess){
		boolean reply=false;
		reply = _planningProcess.getProcess() instanceof SPFTPGetProcess;
		return reply;
	}
	
	public Boolean isFTPProcess(SPPlanningProcess _planningProcess){
		boolean reply=false;
		reply = (_planningProcess.getProcess() instanceof SPFTPPutProcess) || 
			(_planningProcess.getProcess() instanceof SPFTPGetProcess);
		return reply;
	}
	
	public String getFTPUser (SPPlanningProcess _planningProcess){
		String reply = null;
		if (_planningProcess.getProcess() instanceof SPFTPPutProcess)
			reply = ((SPFTPPutProcess)_planningProcess.getProcess()).getFtpUser();
		else if (_planningProcess.getProcess() instanceof SPFTPGetProcess)
			reply = ((SPFTPGetProcess)_planningProcess.getProcess()).getFtpUser();
		return reply;
	}
	
	public String getFTPPassword (SPPlanningProcess _planningProcess){
		String reply = null;
		if (_planningProcess.getProcess() instanceof SPFTPPutProcess)
			reply = ((SPFTPPutProcess)_planningProcess.getProcess()).getFtpPassword();
		else if (_planningProcess.getProcess() instanceof SPFTPGetProcess)
			reply = ((SPFTPGetProcess)_planningProcess.getProcess()).getFtpPassword();
		return reply;
	}
	
	public String getFTPServer (SPPlanningProcess _planningProcess){
		String reply = null;
		if (_planningProcess.getProcess() instanceof SPFTPPutProcess)
			reply = ((SPFTPPutProcess)_planningProcess.getProcess()).getFtpServer();
		else if (_planningProcess.getProcess() instanceof SPFTPGetProcess)
			reply = ((SPFTPGetProcess)_planningProcess.getProcess()).getFtpServer();
		return reply;
	}
	
	public String getFTPPath (SPPlanningProcess _planningProcess){
		String reply = null;
		if (_planningProcess.getProcess() instanceof SPFTPPutProcess)
			reply = ((SPFTPPutProcess)_planningProcess.getProcess()).getFtpPath();
		else if (_planningProcess.getProcess() instanceof SPFTPGetProcess)
			reply = ((SPFTPGetProcess)_planningProcess.getProcess()).getFtpPath();
		return reply;
	}
	
	public String getFTPProxy (SPPlanningProcess _planningProcess){
		String reply = null;
		if (_planningProcess.getProcess() instanceof SPFTPPutProcess)
			reply = ((SPFTPPutProcess)_planningProcess.getProcess()).getFtpProxy();
		else if (_planningProcess.getProcess() instanceof SPFTPGetProcess)
			reply = ((SPFTPGetProcess)_planningProcess.getProcess()).getFtpProxy();
		return reply==null?"":reply;
	}
	
	public String getFTPPort (SPPlanningProcess _planningProcess){
		String reply = null;
		if (_planningProcess.getProcess() instanceof SPFTPPutProcess)
			reply = ((SPFTPPutProcess)_planningProcess.getProcess()).getFtpPort();
		else if (_planningProcess.getProcess() instanceof SPFTPGetProcess)
			reply = ((SPFTPGetProcess)_planningProcess.getProcess()).getFtpPort();
		return reply==null?"":reply;
	}
	
	public String getFTPFile (SPPlanningProcess _planningProcess){
		String reply = null;
		if (_planningProcess.getProcess() instanceof SPFTPPutProcess)
			reply = ((SPFTPPutProcess)_planningProcess.getProcess()).getFtpFile();
		else if (_planningProcess.getProcess() instanceof SPFTPGetProcess)
			reply = ((SPFTPGetProcess)_planningProcess.getProcess()).getFtpFile();
		return reply;
	}
	
	public boolean isFTPBinary (SPPlanningProcess _planningProcess){
		boolean reply = false;
		if (_planningProcess.getProcess() instanceof SPFTPPutProcess)
			reply = ((SPFTPPutProcess)_planningProcess.getProcess()).isFtpBinary();
		else if (_planningProcess.getProcess() instanceof SPFTPGetProcess)
			reply = ((SPFTPGetProcess)_planningProcess.getProcess()).isFtpBinary();
		return reply;
	}
	
	public boolean isFTPPassive (SPPlanningProcess _planningProcess){
		boolean reply = false;
		if (_planningProcess.getProcess() instanceof SPFTPPutProcess)
			reply = ((SPFTPPutProcess)_planningProcess.getProcess()).isFtpBinary();
		else if (_planningProcess.getProcess() instanceof SPFTPGetProcess)
			reply = ((SPFTPGetProcess)_planningProcess.getProcess()).isFtpBinary();
		return reply;
	}
	
	public String getFTPProxyUser (SPPlanningProcess _planningProcess){
		String reply = null;
		if (_planningProcess.getProcess() instanceof SPFTPPutProcess)
			reply = ((SPFTPPutProcess)_planningProcess.getProcess()).getFtpProxyUser();
		else if (_planningProcess.getProcess() instanceof SPFTPGetProcess)
			reply = ((SPFTPGetProcess)_planningProcess.getProcess()).getFtpProxyUser();
		return reply==null?"":reply;
	}
	
	public String getFTPProxyPassword (SPPlanningProcess _planningProcess){
		String reply = null;
		if (_planningProcess.getProcess() instanceof SPFTPPutProcess)
			reply = ((SPFTPPutProcess)_planningProcess.getProcess()).getFtpProxyPassword();
		else if (_planningProcess.getProcess() instanceof SPFTPGetProcess)
			reply = ((SPFTPGetProcess)_planningProcess.getProcess()).getFtpProxyPassword();
		return reply==null?"":reply;
	}
	
	public String getPath (SPPlanningProcess _planningProcess){
		String reply = null;
		if (_planningProcess.getProcess() instanceof SPFileProcess){
			reply = ((SPFileProcess)_planningProcess.getProcess()).getPath();
		}
		return reply;
	}
	
	public String getExtension (SPPlanningProcess _planningProcess){
		String reply = null;
		if (_planningProcess.getProcess() instanceof SPFileProcess){
			reply = ((SPFileProcess)_planningProcess.getProcess()).getExtension();
		}
		return reply;
	}
	
	public String getFileName (SPPlanningProcess _planningProcess){
		String reply = null;
		if (_planningProcess.getProcess() instanceof SPFileProcess){
			reply = ((SPFileProcess)_planningProcess.getProcess()).getFileName();
		}
		return reply;
	}
	
	public boolean isOverwrite (SPPlanningProcess _planningProcess){
		boolean reply = false;
		if (_planningProcess.getProcess() instanceof SPFileProcess){
			reply = ((SPFileProcess)_planningProcess.getProcess()).isOverwrite();
		}
		return reply;
	}
}
