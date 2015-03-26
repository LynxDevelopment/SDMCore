package com.lynxspa.coac.plannings.businessprocess;

import com.lynxit.fpm.BusinessProcess;
import com.lynxit.fpm.BusinessProcessInput;
import com.lynxit.fpm.BusinessProcessOutput;
import com.lynxit.fpm.admin.config.annotations.ConfigParam;
import com.lynxit.fpm.admin.config.annotations.GeneratedType;
import com.lynxit.fpm.events.EventListener;
import com.lynxit.fpm.nodes.DynaParamsNode;
import com.lynxit.fpm.resources.ResourceId;
import com.lynxit.fpm.instrumentation.interceptors.Interceptors;
import com.lynxit.utils.ConfigParams;
import com.lynxit.utils.beans.BeanWithRuntimeConfigurableParams;

import java.util.Properties;

/*
 * process 'business process'
 */
@GeneratedType(definitionFile = "src/com/lynxspa/coac/plannings/businessprocess/controlExecutionPlannings.fpmprocess")
public class ControlExecutionPlanningsProcess extends BusinessProcess {
	/*
	 * Parameters declarations
	 */
	private String locale_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "locale")
	public String getLocale() {
		return locale_;
	}

	public void setLocale(String value) {
		this.locale_ = value;
	}

	private com.lynxit.fpm.resources.Resource statefullSession_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "statefullSession")
	public com.lynxit.fpm.resources.Resource getStatefullSession() {
		return statefullSession_;
	}

	public void setStatefullSession(com.lynxit.fpm.resources.Resource value) {
		this.statefullSession_ = value;
	}

	private com.lynxit.fpm.resources.Resource statelessSession_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "statelessSession")
	public com.lynxit.fpm.resources.Resource getStatelessSession() {
		return statelessSession_;
	}

	public void setStatelessSession(com.lynxit.fpm.resources.Resource value) {
		this.statelessSession_ = value;
	}

	private java.io.File tempPathFile_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "tempPathFile")
	public java.io.File getTempPathFile() {
		return tempPathFile_;
	}

	public void setTempPathFile(java.io.File value) {
		this.tempPathFile_ = value;
	}

	private String user_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "user")
	public String getUser() {
		return user_;
	}

	public void setUser(String value) {
		this.user_ = value;
	}

	/*
	 * inputs declarations
	 */

	// input 'input'
	// $-- /business process/input
	private com.lynxit.fpm.BusinessProcessInput<java.lang.Object> input;

	public com.lynxit.fpm.BusinessProcessInput<java.lang.Object> asInputInput() {
		return input;
	}

	/*
	 * outputs declarations
	 */

	public ControlExecutionPlanningsProcess(String id) {
		super(id);
	}

	public void init() throws Exception {
		Properties initParams = ConfigParams.getInstance().getProperties();

		// instantiate and configure inputs
		// instantiate and configure 'input' input
		input = addInput("input");

		// instantiate and configure outputs

		// instantiate and configure nodes
		// $-- /business process/ControlExecutionPlanning
		// instantiate and configure 'ControlExecutionPlanning' node
		com.lynxspa.coac.plannings.nodes.ControlExecutionPlanning<com.lynxspa.coac.plannings.beans.ControlPlanningsBean> controlExecutionPlanningNode = Interceptors
				.createNode(com.lynxspa.coac.plannings.nodes.ControlExecutionPlanning.class);
		controlExecutionPlanningNode.setId("ControlExecutionPlanning");
		addNode(controlExecutionPlanningNode);
		// $-- /business process/ControlExecutionPlanning/
		controlExecutionPlanningNode.setLocale(getLocale());
		// $-- /business process/ControlExecutionPlanning/
		controlExecutionPlanningNode.setResource(getStatefullSession());
		// $-- /business process/ControlExecutionPlanning/
		// $-- /business process/ControlExecutionPlanning/
		controlExecutionPlanningNode.setUser(getUser());

		// $-- /business process/ForkNode
		// instantiate and configure 'ForkNode' node
		com.lynxit.fpm.nodes.internal.ForkNode<com.lynxspa.coac.plannings.beans.ControlPlanningsBean> forkNodeNode = Interceptors
				.createNode(com.lynxit.fpm.nodes.internal.ForkNode.class);
		forkNodeNode.setId("ForkNode");
		addNode(forkNodeNode);
		// $-- /business process/ForkNode/
		// $-- /business process/ForkNode/
		forkNodeNode.setCloneMessage(false);

		// $-- /business process/HibernateStandardRead
		// instantiate and configure 'HibernateStandardRead' node
		com.lynxspa.fpm.nodes.hibernate.HibernateStandardRead<java.lang.Object, com.lynxspa.entities.plannings.SPPlanningProcess> hibernateStandardReadNode = Interceptors
				.createNode(com.lynxspa.fpm.nodes.hibernate.HibernateStandardRead.class);
		hibernateStandardReadNode.setId("HibernateStandardRead");
		addNode(hibernateStandardReadNode);
		// $-- /business process/HibernateStandardRead/
		hibernateStandardReadNode.setReadOnly(true);
		// $-- /business process/HibernateStandardRead/
		hibernateStandardReadNode
				.setQuery("from SPPlanningProcess where auditor.deleted=:par0");
		// $-- /business process/HibernateStandardRead/
		// $-- /business process/HibernateStandardRead/
		hibernateStandardReadNode.setResource(getStatefullSession());
		// $-- /business process/HibernateStandardRead/
		hibernateStandardReadNode
				.setEntityClass(com.lynxspa.entities.plannings.SPPlanningProcess.class);
		// $-- /business process/HibernateStandardRead/
		hibernateStandardReadNode.setNumResults(50);
		// $-- /business process/HibernateStandardRead/
		hibernateStandardReadNode
				.setParameters(com.lynxit.utils.beans.BeanUtils.createList(
						java.lang.Object.class, false));
		// $-- /business process/HibernateStandardRead/
		// $-- /business process/HibernateStandardRead/
		hibernateStandardReadNode
				.setReadMode(com.lynxspa.fpm.nodes.hibernate.HibernateStandardRead.HibernateReadModes.CURSOR);

		// $-- /business process/IteratorNode
		// instantiate and configure 'IteratorNode' node
		com.lynxit.fpm.nodes.internal.IteratorNode<com.lynxspa.entities.plannings.SPPlanningProcess> iteratorNodeNode = Interceptors
				.createNode(com.lynxit.fpm.nodes.internal.IteratorNode.class);
		iteratorNodeNode.setId("IteratorNode");
		addNode(iteratorNodeNode);
		// $-- /business process/IteratorNode/

		// $-- /business process/LogPlanningException
		// instantiate and configure 'LogPlanningException' node
		com.lynxspa.coac.plannings.logs.LogPlanningException logPlanningExceptionNode = Interceptors
				.createNode(com.lynxspa.coac.plannings.logs.LogPlanningException.class);
		logPlanningExceptionNode.setId("LogPlanningException");
		addNode(logPlanningExceptionNode);
		// $-- /business process/LogPlanningException/
		logPlanningExceptionNode.setDataSource(getStatelessSession());
		// $-- /business process/LogPlanningException/
		logPlanningExceptionNode.setStopExceptionHandling(true);
		// $-- /business process/LogPlanningException/
		// $-- /business process/LogPlanningException/
		logPlanningExceptionNode.setCommitInNewTransaction(true);
		// $-- /business process/LogPlanningException/
		logPlanningExceptionNode.setLocale(getLocale());
		// $-- /business process/LogPlanningException/
		// $-- /business process/LogPlanningException/
		logPlanningExceptionNode.setUser(getUser());
		// $-- /business process/LogPlanningException/
		logPlanningExceptionNode
				.setLog(com.lynxspa.coac.plannings.dictionaries.LogErrorDict.PLANNING_EXCEPTION);

		// $-- /business process/MappingNode
		// instantiate and configure 'MappingNode' node
		com.lynxspa.coac.plannings.maps.MapControlPlanningsMap mappingNodeNode = Interceptors
				.createNode(com.lynxspa.coac.plannings.maps.MapControlPlanningsMap.class);
		mappingNodeNode.setId("MappingNode");
		addNode(mappingNodeNode);
		// $-- /business process/MappingNode/

		// $-- /business process/PatchedIteratorNode
		// instantiate and configure 'PatchedIteratorNode' node
		com.lynxspa.fpm.nodes.patch.PatchedIteratorNode<com.lynxspa.entities.plannings.SPPlanningProcess> patchedIteratorNodeNode = Interceptors
				.createNode(com.lynxspa.fpm.nodes.patch.PatchedIteratorNode.class);
		patchedIteratorNodeNode.setId("PatchedIteratorNode");
		addNode(patchedIteratorNodeNode);
		// $-- /business process/PatchedIteratorNode/

		// $-- /business process/SwitchNode
		// instantiate and configure 'SwitchNode' node
		com.lynxspa.coac.plannings.nodes.PlanningProcessSwitch switchNodeNode = Interceptors
				.createNode(com.lynxspa.coac.plannings.nodes.PlanningProcessSwitch.class);
		switchNodeNode.setId("SwitchNode");
		addNode(switchNodeNode);
		// $-- /business process/SwitchNode/

		// instantiate and configure subprocesses
		// $-- /business process/ExecuteShellProcess
		// instantiate and configure 'ExecuteShellProcess' subprocess
		com.lynxspa.coac.plannings.executeshellprocess.businesprocess.ExecuteShellProcess executeShellProcess = Interceptors
				.createSubProcess(
						com.lynxspa.coac.plannings.executeshellprocess.businesprocess.ExecuteShellProcess.class,
						"ExecuteShellProcess");
		addSubProcess(executeShellProcess);
		// $-- /business process/ExecuteShellProcess/
		executeShellProcess.setStatefullSession(getStatefullSession());
		// $-- /business process/ExecuteShellProcess/
		executeShellProcess.setLocale(getLocale());
		// $-- /business process/ExecuteShellProcess/
		// $-- /business process/ExecuteShellProcess/
		executeShellProcess.setUser(getUser());

		executeShellProcess.initialize();
		// $-- /business process/FtpGetProcess
		// instantiate and configure 'FtpGetProcess' subprocess
		com.lynxspa.coac.plannings.ftpprocess.businessprocess.FtpGetProcess ftpGetProcess = Interceptors
				.createSubProcess(
						com.lynxspa.coac.plannings.ftpprocess.businessprocess.FtpGetProcess.class,
						"FtpGetProcess");
		addSubProcess(ftpGetProcess);
		// $-- /business process/FtpGetProcess/
		ftpGetProcess.setStatefullSession(getStatefullSession());
		// $-- /business process/FtpGetProcess/
		ftpGetProcess.setLocale(getLocale());
		// $-- /business process/FtpGetProcess/
		ftpGetProcess.setStatelessSession(getStatelessSession());
		// $-- /business process/FtpGetProcess/
		// $-- /business process/FtpGetProcess/
		ftpGetProcess.setUser(getUser());

		ftpGetProcess.initialize();
		// $-- /business process/FtpPutProcess
		// instantiate and configure 'FtpPutProcess' subprocess
		com.lynxspa.coac.plannings.ftpprocess.businessprocess.FtpPutProcess ftpPutProcess = Interceptors
				.createSubProcess(
						com.lynxspa.coac.plannings.ftpprocess.businessprocess.FtpPutProcess.class,
						"FtpPutProcess");
		addSubProcess(ftpPutProcess);
		// $-- /business process/FtpPutProcess/
		ftpPutProcess.setStatefullSession(getStatefullSession());
		// $-- /business process/FtpPutProcess/
		ftpPutProcess.setLocale(getLocale());
		// $-- /business process/FtpPutProcess/
		ftpPutProcess.setStatelessSession(getStatelessSession());
		// $-- /business process/FtpPutProcess/
		// $-- /business process/FtpPutProcess/
		ftpPutProcess.setUser(getUser());

		ftpPutProcess.initialize();
		// $-- /business process/GenerateFileProcess
		// instantiate and configure 'GenerateFileProcess' subprocess
		com.lynxspa.coac.plannings.generatefileprocess.businessprocess.GenerateFileProcess generateFileProcess = Interceptors
				.createSubProcess(
						com.lynxspa.coac.plannings.generatefileprocess.businessprocess.GenerateFileProcess.class,
						"GenerateFileProcess");
		addSubProcess(generateFileProcess);
		// $-- /business process/GenerateFileProcess/
		generateFileProcess.setStatefullSession(getStatefullSession());
		// $-- /business process/GenerateFileProcess/
		generateFileProcess.setLocale(getLocale());
		// $-- /business process/GenerateFileProcess/
		generateFileProcess.setStatelessSession(getStatelessSession());
		// $-- /business process/GenerateFileProcess/
		// $-- /business process/GenerateFileProcess/
		generateFileProcess.setUser(getUser());
		// $-- /business process/GenerateFileProcess/
		generateFileProcess.setTempPathFiles(getTempPathFile());

		generateFileProcess.initialize();
		// $-- /business process/Update Plannings
		// instantiate and configure 'Update Plannings' subprocess
		com.lynxspa.coac.plannings.businessprocess.UpdatePlanningsProcess updatePlannings = Interceptors
				.createSubProcess(
						com.lynxspa.coac.plannings.businessprocess.UpdatePlanningsProcess.class,
						"Update Plannings");
		addSubProcess(updatePlannings);
		// $-- /business process/Update Plannings/
		updatePlannings.setStatefullSession(getStatefullSession());
		// $-- /business process/Update Plannings/
		updatePlannings.setLocale(getLocale());
		// $-- /business process/Update Plannings/
		updatePlannings.setStatelessSession(getStatelessSession());
		// $-- /business process/Update Plannings/
		// $-- /business process/Update Plannings/
		updatePlannings.setUser(getUser());

		updatePlannings.initialize();

		// link nodes
		// $-- /business process/ControlExecutionPlanning/out/business process/ForkNode/in
		controlExecutionPlanningNode.connectNodeToOut(forkNodeNode);
		controlExecutionPlanningNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/ForkNode/two/business process/Update Plannings/input
		forkNodeNode.connectNodeToTwo(updatePlannings.asInputInput());
		// $-- /business process/ForkNode/one/business process/SwitchNode/in
		forkNodeNode.connectNodeToOne(switchNodeNode);
		forkNodeNode.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/HibernateStandardRead/out/business process/PatchedIteratorNode/in
		hibernateStandardReadNode.connectNodeToOut(patchedIteratorNodeNode);
		hibernateStandardReadNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		iteratorNodeNode.connectNodeToOut(getDefaultStopperNode());
		iteratorNodeNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/MappingNode/out/business process/ControlExecutionPlanning/in
		mappingNodeNode.connectNodeToOut(controlExecutionPlanningNode);
		mappingNodeNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/PatchedIteratorNode/out/business process/MappingNode/in
		patchedIteratorNodeNode.connectNodeToOut(mappingNodeNode);
		patchedIteratorNodeNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/SwitchNode/default/business process/GenerateFileProcess/input
		switchNodeNode.connectNodeToDefault(generateFileProcess.asInputInput());
		// $-- /business process/SwitchNode/ftpPut/business process/FtpPutProcess/input
		switchNodeNode.connectNodeToFtpPut(ftpPutProcess.asInputInput());
		// $-- /business process/SwitchNode/ftpGet/business process/FtpGetProcess/input
		switchNodeNode.connectNodeToFtpGet(ftpGetProcess.asInputInput());
		// $-- /business process/SwitchNode/executeShell/business process/ExecuteShellProcess/input
		switchNodeNode.connectNodeToExecuteShell(executeShellProcess
				.asInputInput());
		// $-- /business process/SwitchNode/exception/business process/LogPlanningException/exc
		switchNodeNode.connectNodeToException(logPlanningExceptionNode);

		// link subprocesses
		executeShellProcess.asExcOutput().connect(getDefaultStopperNode());
		ftpGetProcess.asExcOutput().connect(getDefaultStopperNode());
		ftpPutProcess.asExcOutput().connect(getDefaultStopperNode());
		generateFileProcess.asExcOutput().connect(getDefaultStopperNode());
		updatePlannings.asExcOutput().connect(getDefaultStopperNode());

		// link inputs
		// $-- /business process/input/business process/HibernateStandardRead/in
		input.connectNodeToOut(hibernateStandardReadNode);

		controlExecutionPlanningNode.init();
		forkNodeNode.init();
		hibernateStandardReadNode.init();
		iteratorNodeNode.init();
		logPlanningExceptionNode.init();
		mappingNodeNode.init();
		patchedIteratorNodeNode.init();
		switchNodeNode.init();
	}

}