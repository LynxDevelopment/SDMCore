package com.lynxspa.coac.authorization.businessprocess;

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
@GeneratedType(definitionFile = "src/com/lynxspa/coac/authorization/businessprocess/Authorize.fpmprocess")
public class AuthorizeProcess extends BusinessProcess {
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

	private java.lang.Integer selectSize_;

	@ConfigParam(description = "param2", required = true, dynamic = false, name = "selectSize")
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

	@ConfigParam(description = "param3", required = true, dynamic = false, name = "user")
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

	public AuthorizeProcess(String id) {
		super(id);
	}

	public void init() throws Exception {
		Properties initParams = ConfigParams.getInstance().getProperties();

		// instantiate and configure inputs
		// instantiate and configure 'input' input
		input = addInput("input");

		// instantiate and configure outputs

		// instantiate and configure nodes
		// $-- /business process/CallAuthorizationProcessor
		// instantiate and configure 'CallAuthorizationProcessor' node
		com.lynxspa.coac.authorization.nodes.CallAuthorizationProcessor callAuthorizationProcessorNode = Interceptors
				.createNode(com.lynxspa.coac.authorization.nodes.CallAuthorizationProcessor.class);
		callAuthorizationProcessorNode.setId("CallAuthorizationProcessor");
		addNode(callAuthorizationProcessorNode);
		// $-- /business process/CallAuthorizationProcessor/
		callAuthorizationProcessorNode.setLocale("locale");
		// $-- /business process/CallAuthorizationProcessor/
		callAuthorizationProcessorNode.setResource(getStatefullSession());
		// $-- /business process/CallAuthorizationProcessor/
		// $-- /business process/CallAuthorizationProcessor/
		callAuthorizationProcessorNode.setUser("user");

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
				.setRequiredLiveCycle(com.lynxspa.sdm.dictionaries.CALiveCycle.VALIDATE_MASTER_RECORD);
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
				.setLog(com.lynxspa.sdm.dictionaries.logs.LogErrorDict.AUTHORIZATION_FAIL);

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
		readNode.setOperationalDateTimeMarginParam(com.lynxspa.sdm.dictionaries.config.CAConfiguration.AUTHORIZATIONOPERATIONALDATE);
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
				java.lang.Object.class, false, 0l, "NAUT"));
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

		// $-- /business process/ToAuthorized
		// instantiate and configure 'ToAuthorized' node
		com.lynxspa.coac.authorization.nodes.AuthorizationChangeStateNode toAuthorizedNode = Interceptors
				.createNode(com.lynxspa.coac.authorization.nodes.AuthorizationChangeStateNode.class);
		toAuthorizedNode.setId("ToAuthorized");
		addNode(toAuthorizedNode);
		// $-- /business process/ToAuthorized/
		toAuthorizedNode.setCreateLog(false);
		// $-- /business process/ToAuthorized/
		// $-- /business process/ToAuthorized/
		toAuthorizedNode.setLocale(getLocale());
		// $-- /business process/ToAuthorized/
		toAuthorizedNode.setActiveMessageTransition(false);
		// $-- /business process/ToAuthorized/
		toAuthorizedNode.setResource(getStatefullSession());
		// $-- /business process/ToAuthorized/
		toAuthorizedNode
				.setState(com.lynxspa.sdm.dictionaries.flows.states.CAStatesEVENTGROUPFlow.AUTH);
		// $-- /business process/ToAuthorized/
		// $-- /business process/ToAuthorized/
		// $-- /business process/ToAuthorized/
		// $-- /business process/ToAuthorized/
		toAuthorizedNode.setUser(getUser());

		// $-- /business process/ToManualAuthorization
		// instantiate and configure 'ToManualAuthorization' node
		com.lynxspa.coac.nodes.workflow.EventGroupChangeStateNode toManualAuthorizationNode = Interceptors
				.createNode(com.lynxspa.coac.nodes.workflow.EventGroupChangeStateNode.class);
		toManualAuthorizationNode.setId("ToManualAuthorization");
		addNode(toManualAuthorizationNode);
		// $-- /business process/ToManualAuthorization/
		toManualAuthorizationNode.setActiveMessageTransition(false);
		// $-- /business process/ToManualAuthorization/
		toManualAuthorizationNode.setLocale(getLocale());
		// $-- /business process/ToManualAuthorization/
		toManualAuthorizationNode
				.setState(com.lynxspa.sdm.dictionaries.flows.states.CAStatesEVENTGROUPFlow.MAUT);
		// $-- /business process/ToManualAuthorization/
		toManualAuthorizationNode.setResource(getStatefullSession());
		// $-- /business process/ToManualAuthorization/
		// $-- /business process/ToManualAuthorization/
		// $-- /business process/ToManualAuthorization/
		toManualAuthorizationNode.setCreateLog(false);
		// $-- /business process/ToManualAuthorization/
		// $-- /business process/ToManualAuthorization/
		// $-- /business process/ToManualAuthorization/
		// $-- /business process/ToManualAuthorization/
		toManualAuthorizationNode.setUser(getUser());

		// $-- /business process/ToManualAuthorizationException
		// instantiate and configure 'ToManualAuthorizationException' node
		com.lynxspa.coac.nodes.workflow.EventGroupChangeStateExceptionNode toManualAuthorizationExceptionNode = Interceptors
				.createNode(com.lynxspa.coac.nodes.workflow.EventGroupChangeStateExceptionNode.class);
		toManualAuthorizationExceptionNode
				.setId("ToManualAuthorizationException");
		addNode(toManualAuthorizationExceptionNode);
		// $-- /business process/ToManualAuthorizationException/
		toManualAuthorizationExceptionNode.setCreateLog(false);
		// $-- /business process/ToManualAuthorizationException/
		// $-- /business process/ToManualAuthorizationException/
		toManualAuthorizationExceptionNode.setStopExceptionHandling(true);
		// $-- /business process/ToManualAuthorizationException/
		toManualAuthorizationExceptionNode.setCommit(true);
		// $-- /business process/ToManualAuthorizationException/
		toManualAuthorizationExceptionNode.setLocale(getLocale());
		// $-- /business process/ToManualAuthorizationException/
		toManualAuthorizationExceptionNode.setResource(getStatefullSession());
		// $-- /business process/ToManualAuthorizationException/
		toManualAuthorizationExceptionNode
				.setState(com.lynxspa.sdm.dictionaries.flows.states.CAStatesEVENTGROUPFlow.MAUT);
		// $-- /business process/ToManualAuthorizationException/
		// $-- /business process/ToManualAuthorizationException/
		// $-- /business process/ToManualAuthorizationException/
		toManualAuthorizationExceptionNode.setUser(getUser());

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
		// $-- /business process/CallAuthorizationProcessor/true/business process/ToAuthorized/in
		callAuthorizationProcessorNode.connectNodeToTrue(toAuthorizedNode);
		// $-- /business process/CallAuthorizationProcessor/false/business process/ToManualAuthorization/in
		callAuthorizationProcessorNode
				.connectNodeToFalse(toManualAuthorizationNode);
		// $-- /business process/CallAuthorizationProcessor/exception/business process/Rollback/exc
		callAuthorizationProcessorNode.connectNodeToException(rollbackNode);
		commitNode.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/LiveCycleValidatorNode/out/business process/Read/in
		liveCycleValidatorNodeNode.connectNodeToOut(readNode);
		// $-- /business process/LiveCycleValidatorNode/exception/business process/LogCoacException/exc
		liveCycleValidatorNodeNode.connectNodeToException(logCoacExceptionNode);
		// $-- /business process/PatchedIteratorNode/out/business process/CallAuthorizationProcessor/in
		patchedIteratorNodeNode
				.connectNodeToOut(callAuthorizationProcessorNode);
		// $-- /business process/PatchedIteratorNode/exception/business process/ToManualAuthorizationException/exc
		patchedIteratorNodeNode
				.connectNodeToException(toManualAuthorizationExceptionNode);
		// $-- /business process/Read/out/business process/PatchedIteratorNode/in
		readNode.connectNodeToOut(patchedIteratorNodeNode);
		readNode.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/ToAuthorized/out/business process/Update/in
		toAuthorizedNode.connectNodeToOut(updateNode);
		toAuthorizedNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/ToManualAuthorization/out/business process/Update/in
		toManualAuthorizationNode.connectNodeToOut(updateNode);
		toManualAuthorizationNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/Update/out/business process/Commit/in
		updateNode.connectNodeToOut(commitNode);
		updateNode.connectNodeToException(getDefaultExceptionHandlerNode());

		// link subprocesses

		// link inputs
		// $-- /business process/input/business process/LiveCycleValidatorNode/in
		input.connectNodeToOut(liveCycleValidatorNodeNode);

		callAuthorizationProcessorNode.init();
		commitNode.init();
		liveCycleValidatorNodeNode.init();
		logCoacExceptionNode.init();
		patchedIteratorNodeNode.init();
		readNode.init();
		rollbackNode.init();
		toAuthorizedNode.init();
		toManualAuthorizationNode.init();
		toManualAuthorizationExceptionNode.init();
		updateNode.init();
	}

}