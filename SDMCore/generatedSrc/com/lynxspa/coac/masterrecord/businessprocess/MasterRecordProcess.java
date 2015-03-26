package com.lynxspa.coac.masterrecord.businessprocess;

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
@GeneratedType(definitionFile = "src/com/lynxspa/coac/masterrecord/businessprocess/MasterRecord.fpmprocess")
public class MasterRecordProcess extends BusinessProcess {
	/*
	 * Parameters declarations
	 */
	private java.lang.Integer entityDeadLineDays_;

	@ConfigParam(description = "param1", required = false, dynamic = false, name = "entityDeadLineDays")
	public java.lang.Integer getEntityDeadLineDays() {
		return entityDeadLineDays_;
	}

	public void setEntityDeadLineDays(java.lang.Integer value) {
		this.entityDeadLineDays_ = value;
	}

	private String locale_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "locale")
	public String getLocale() {
		return locale_;
	}

	public void setLocale(String value) {
		this.locale_ = value;
	}

	private java.lang.Integer selectSize_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "selectSize")
	public java.lang.Integer getSelectSize() {
		return selectSize_;
	}

	public void setSelectSize(java.lang.Integer value) {
		this.selectSize_ = value;
	}

	private com.lynxit.fpm.resources.Resource statefullSession_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "statefullSession")
	public com.lynxit.fpm.resources.Resource getStatefullSession() {
		return statefullSession_;
	}

	public void setStatefullSession(com.lynxit.fpm.resources.Resource value) {
		this.statefullSession_ = value;
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
	private com.lynxit.fpm.BusinessProcessInput<com.lynxit.fpm.events.Event> input;

	public com.lynxit.fpm.BusinessProcessInput<com.lynxit.fpm.events.Event> asInputInput() {
		return input;
	}

	/*
	 * outputs declarations
	 */

	public MasterRecordProcess(String id) {
		super(id);
	}

	public void init() throws Exception {
		Properties initParams = ConfigParams.getInstance().getProperties();

		// instantiate and configure inputs
		// instantiate and configure 'input' input
		input = addInput("input");

		// instantiate and configure outputs

		// instantiate and configure nodes
		// $-- /business process/Commit
		// instantiate and configure 'Commit' node
		com.lynxspa.fpm.nodes.hibernate.HibernateStandardCommit commitNode = Interceptors
				.createNode(com.lynxspa.fpm.nodes.hibernate.HibernateStandardCommit.class);
		commitNode.setId("Commit");
		addNode(commitNode);
		// $-- /business process/Commit/
		commitNode.setDataSource(getStatefullSession());
		// $-- /business process/Commit/
		commitNode.setCommitEvery(1);
		// $-- /business process/Commit/

		// $-- /business process/LiveCycleValidatorNode
		// instantiate and configure 'LiveCycleValidatorNode' node
		com.lynxspa.coac.nodes.LiveCycleValidatorNode<java.lang.Object> liveCycleValidatorNodeNode = Interceptors
				.createNode(com.lynxspa.coac.nodes.LiveCycleValidatorNode.class);
		liveCycleValidatorNodeNode.setId("LiveCycleValidatorNode");
		addNode(liveCycleValidatorNodeNode);
		// $-- /business process/LiveCycleValidatorNode/
		liveCycleValidatorNodeNode
				.setRequiredLiveCycle(com.lynxspa.sdm.dictionaries.CALiveCycle.MASTER_RECORD);
		// $-- /business process/LiveCycleValidatorNode/
		liveCycleValidatorNodeNode.setResource(getStatefullSession());
		// $-- /business process/LiveCycleValidatorNode/

		// $-- /business process/LogCoacException
		// instantiate and configure 'LogCoacException' node
		com.lynxspa.coac.nodes.logs.LogCoacException logCoacExceptionNode = Interceptors
				.createNode(com.lynxspa.coac.nodes.logs.LogCoacException.class);
		logCoacExceptionNode.setId("LogCoacException");
		addNode(logCoacExceptionNode);
		// $-- /business process/LogCoacException/
		logCoacExceptionNode.setDataSource(getStatefullSession());
		// $-- /business process/LogCoacException/
		logCoacExceptionNode.setStopExceptionHandling(true);
		// $-- /business process/LogCoacException/
		// $-- /business process/LogCoacException/
		logCoacExceptionNode.setCommitInNewTransaction(true);
		// $-- /business process/LogCoacException/
		logCoacExceptionNode.setLocale(getLocale());
		// $-- /business process/LogCoacException/
		// $-- /business process/LogCoacException/
		logCoacExceptionNode.setUser(getUser());
		// $-- /business process/LogCoacException/
		logCoacExceptionNode
				.setLog(com.lynxspa.sdm.dictionaries.logs.LogErrorDict.MASTERRECORD_FAIL);

		// $-- /business process/MasterRecordChangeStateNode
		// instantiate and configure 'MasterRecordChangeStateNode' node
		com.lynxspa.coac.masterrecord.nodes.MasterRecordChangeStateNode masterRecordChangeStateNodeNode = Interceptors
				.createNode(com.lynxspa.coac.masterrecord.nodes.MasterRecordChangeStateNode.class);
		masterRecordChangeStateNodeNode.setId("MasterRecordChangeStateNode");
		addNode(masterRecordChangeStateNodeNode);
		// $-- /business process/MasterRecordChangeStateNode/
		masterRecordChangeStateNodeNode.setCreateLog(false);
		// $-- /business process/MasterRecordChangeStateNode/
		// $-- /business process/MasterRecordChangeStateNode/
		masterRecordChangeStateNodeNode.setLocale(getLocale());
		// $-- /business process/MasterRecordChangeStateNode/
		masterRecordChangeStateNodeNode.setActiveMessageTransition(false);
		// $-- /business process/MasterRecordChangeStateNode/
		masterRecordChangeStateNodeNode.setResource(getStatefullSession());
		// $-- /business process/MasterRecordChangeStateNode/
		masterRecordChangeStateNodeNode
				.setState(com.lynxspa.sdm.dictionaries.flows.states.CAStatesEVENTGROUPFlow.NAUT);
		// $-- /business process/MasterRecordChangeStateNode/
		// $-- /business process/MasterRecordChangeStateNode/
		// $-- /business process/MasterRecordChangeStateNode/
		// $-- /business process/MasterRecordChangeStateNode/
		masterRecordChangeStateNodeNode.setUser(getUser());

		// $-- /business process/PatchedIteratorNode
		// instantiate and configure 'PatchedIteratorNode' node
		com.lynxspa.fpm.nodes.patch.PatchedIteratorNode<com.lynxspa.sdm.entities.events.CAEventGroup> patchedIteratorNodeNode = Interceptors
				.createNode(com.lynxspa.fpm.nodes.patch.PatchedIteratorNode.class);
		patchedIteratorNodeNode.setId("PatchedIteratorNode");
		addNode(patchedIteratorNodeNode);
		// $-- /business process/PatchedIteratorNode/

		// $-- /business process/Read
		// instantiate and configure 'Read' node
		com.lynxspa.coac.nodes.EventStandardRead<java.lang.Object, com.lynxspa.sdm.entities.events.CAEventGroup> readNode = Interceptors
				.createNode(com.lynxspa.coac.nodes.EventStandardRead.class);
		readNode.setId("Read");
		addNode(readNode);
		// $-- /business process/Read/
		readNode.setQuery("from CAEventGroup where auditor.deleted=:par0 and auditor.refId=:par1 and operationStatus.state.id.code=:par2 and operationalDate<:operationalDate order by executionDate,id asc");
		// $-- /business process/Read/
		readNode.setNumResults(getSelectSize());
		// $-- /business process/Read/
		readNode.setResource(getStatefullSession());
		// $-- /business process/Read/
		readNode.setOperationalDateTimeMarginParam(com.lynxspa.sdm.dictionaries.config.CAConfiguration.MASTERRECORDOPERATIONALDATE);
		// $-- /business process/Read/
		readNode.setReadMode(com.lynxspa.fpm.nodes.hibernate.HibernateStandardRead.HibernateReadModes.CURSOR_IDENTIFIER);
		// $-- /business process/Read/
		readNode.setReadOnly(false);
		// $-- /business process/Read/
		readNode.setEntityClass(com.lynxspa.sdm.entities.events.CAEventGroup.class);
		// $-- /business process/Read/
		readNode.setEntityIdField("id");
		// $-- /business process/Read/
		readNode.setParameters(com.lynxit.utils.beans.BeanUtils.createList(
				java.lang.Object.class, false, 0l, "PMRS"));
		// $-- /business process/Read/

		// $-- /business process/Rollback
		// instantiate and configure 'Rollback' node
		com.lynxspa.fpm.nodes.hibernate.ExceptionRollbackNode rollbackNode = Interceptors
				.createNode(com.lynxspa.fpm.nodes.hibernate.ExceptionRollbackNode.class);
		rollbackNode.setId("Rollback");
		addNode(rollbackNode);
		// $-- /business process/Rollback/
		rollbackNode.setDataSource(getStatefullSession());
		// $-- /business process/Rollback/
		rollbackNode.setStopExceptionHandling(false);
		// $-- /business process/Rollback/

		// $-- /business process/SelectMR
		// instantiate and configure 'SelectMR' node
		com.lynxspa.coac.masterrecord.nodes.CallMasterRecordProcessor selectMRNode = Interceptors
				.createNode(com.lynxspa.coac.masterrecord.nodes.CallMasterRecordProcessor.class);
		selectMRNode.setId("SelectMR");
		addNode(selectMRNode);
		// $-- /business process/SelectMR/
		selectMRNode.setLocale(getLocale());
		// $-- /business process/SelectMR/
		selectMRNode.setResource(getStatefullSession());
		// $-- /business process/SelectMR/
		// $-- /business process/SelectMR/
		selectMRNode.setUser(getUser());
		// $-- /business process/SelectMR/
		selectMRNode.setEntityDeadLineDays(getEntityDeadLineDays());

		// $-- /business process/ToManualMasterRecord
		// instantiate and configure 'ToManualMasterRecord' node
		com.lynxspa.coac.nodes.workflow.EventGroupChangeStateExceptionNode toManualMasterRecordNode = Interceptors
				.createNode(com.lynxspa.coac.nodes.workflow.EventGroupChangeStateExceptionNode.class);
		toManualMasterRecordNode.setId("ToManualMasterRecord");
		addNode(toManualMasterRecordNode);
		// $-- /business process/ToManualMasterRecord/
		toManualMasterRecordNode.setCreateLog(false);
		// $-- /business process/ToManualMasterRecord/
		// $-- /business process/ToManualMasterRecord/
		toManualMasterRecordNode.setStopExceptionHandling(true);
		// $-- /business process/ToManualMasterRecord/
		toManualMasterRecordNode.setCommit(true);
		// $-- /business process/ToManualMasterRecord/
		toManualMasterRecordNode.setLocale(getLocale());
		// $-- /business process/ToManualMasterRecord/
		toManualMasterRecordNode.setResource(getStatefullSession());
		// $-- /business process/ToManualMasterRecord/
		toManualMasterRecordNode
				.setState(com.lynxspa.sdm.dictionaries.flows.states.CAStatesEVENTGROUPFlow.MMRS);
		// $-- /business process/ToManualMasterRecord/
		// $-- /business process/ToManualMasterRecord/
		// $-- /business process/ToManualMasterRecord/
		toManualMasterRecordNode.setUser(getUser());

		// $-- /business process/Update
		// instantiate and configure 'Update' node
		com.lynxspa.fpm.nodes.hibernate.HibernateStandardUpdate updateNode = Interceptors
				.createNode(com.lynxspa.fpm.nodes.hibernate.HibernateStandardUpdate.class);
		updateNode.setId("Update");
		addNode(updateNode);
		// $-- /business process/Update/
		updateNode.setDataSource(getStatefullSession());
		// $-- /business process/Update/
		updateNode.setFlush(true);
		// $-- /business process/Update/
		// $-- /business process/Update/
		updateNode.setUser(getUser());

		// instantiate and configure subprocesses

		// link nodes
		commitNode.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/LiveCycleValidatorNode/out/business process/Read/in
		liveCycleValidatorNodeNode.connectNodeToOut(readNode);
		// $-- /business process/LiveCycleValidatorNode/exception/business process/LogCoacException/exc
		liveCycleValidatorNodeNode.connectNodeToException(logCoacExceptionNode);
		// $-- /business process/MasterRecordChangeStateNode/out/business process/Update/in
		masterRecordChangeStateNodeNode.connectNodeToOut(updateNode);
		masterRecordChangeStateNodeNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/PatchedIteratorNode/out/business process/SelectMR/in
		patchedIteratorNodeNode.connectNodeToOut(selectMRNode);
		// $-- /business process/PatchedIteratorNode/exception/business process/ToManualMasterRecord/exc
		patchedIteratorNodeNode
				.connectNodeToException(toManualMasterRecordNode);
		// $-- /business process/Read/out/business process/PatchedIteratorNode/in
		readNode.connectNodeToOut(patchedIteratorNodeNode);
		readNode.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/SelectMR/out/business process/MasterRecordChangeStateNode/in
		selectMRNode.connectNodeToOut(masterRecordChangeStateNodeNode);
		// $-- /business process/SelectMR/exception/business process/Rollback/exc
		selectMRNode.connectNodeToException(rollbackNode);
		// $-- /business process/Update/out/business process/Commit/in
		updateNode.connectNodeToOut(commitNode);
		updateNode.connectNodeToException(getDefaultExceptionHandlerNode());

		// link subprocesses

		// link inputs
		// $-- /business process/input/business process/LiveCycleValidatorNode/in
		input.connectNodeToOut(liveCycleValidatorNodeNode);

		commitNode.init();
		liveCycleValidatorNodeNode.init();
		logCoacExceptionNode.init();
		masterRecordChangeStateNodeNode.init();
		patchedIteratorNodeNode.init();
		readNode.init();
		rollbackNode.init();
		selectMRNode.init();
		toManualMasterRecordNode.init();
		updateNode.init();
	}

}