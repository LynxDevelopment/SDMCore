package com.lynxspa.coac.prenormalizer.businessprocess;

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
@GeneratedType(definitionFile = "src/com/lynxspa/coac/prenormalizer/businessprocess/PreNormalizer.fpmprocess")
public class PreNormalizerProcess extends BusinessProcess {
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

	private Integer maxResultsToLoad_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "maxResultsToLoad")
	public Integer getMaxResultsToLoad() {
		return maxResultsToLoad_;
	}

	public void setMaxResultsToLoad(Integer value) {
		this.maxResultsToLoad_ = value;
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

	public PreNormalizerProcess(String id) {
		super(id);
	}

	public void init() throws Exception {
		Properties initParams = ConfigParams.getInstance().getProperties();

		// instantiate and configure inputs
		// instantiate and configure 'input' input
		input = addInput("input");

		// instantiate and configure outputs

		// instantiate and configure nodes
		// $-- /business process/Event Type
		// instantiate and configure 'Event Type' node
		com.lynxspa.coac.prenormalizer.nodes.EventTypeNormalizer eventTypeNode = Interceptors
				.createNode(com.lynxspa.coac.prenormalizer.nodes.EventTypeNormalizer.class);
		eventTypeNode.setId("Event Type");
		addNode(eventTypeNode);
		// $-- /business process/Event Type/
		eventTypeNode.setLocale(getLocale());
		// $-- /business process/Event Type/
		eventTypeNode.setResource(getStatefullSession());
		// $-- /business process/Event Type/
		// $-- /business process/Event Type/
		eventTypeNode.setUser(getUser());

		// $-- /business process/HibernateStandardCommit
		// instantiate and configure 'HibernateStandardCommit' node
		com.lynxspa.fpm.nodes.hibernate.HibernateStandardCommit hibernateStandardCommitNode = Interceptors
				.createNode(com.lynxspa.fpm.nodes.hibernate.HibernateStandardCommit.class);
		hibernateStandardCommitNode.setId("HibernateStandardCommit");
		addNode(hibernateStandardCommitNode);
		// $-- /business process/HibernateStandardCommit/
		hibernateStandardCommitNode.setDataSource(getStatefullSession());
		// $-- /business process/HibernateStandardCommit/
		hibernateStandardCommitNode.setCommitEvery(1);
		// $-- /business process/HibernateStandardCommit/

		// $-- /business process/LiveCycleValidatorNode
		// instantiate and configure 'LiveCycleValidatorNode' node
		com.lynxspa.coac.nodes.LiveCycleValidatorNode<java.lang.Object> liveCycleValidatorNodeNode = Interceptors
				.createNode(com.lynxspa.coac.nodes.LiveCycleValidatorNode.class);
		liveCycleValidatorNodeNode.setId("LiveCycleValidatorNode");
		addNode(liveCycleValidatorNodeNode);
		// $-- /business process/LiveCycleValidatorNode/
		liveCycleValidatorNodeNode
				.setRequiredLiveCycle(com.lynxspa.sdm.dictionaries.CALiveCycle.PRENORMALIZE_IMPORT);
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
				.setLog(com.lynxspa.sdm.dictionaries.logs.LogErrorDict.PRENORMALIZATION_FAIL);

		// $-- /business process/Operation
		// instantiate and configure 'Operation' node
		com.lynxspa.coac.prenormalizer.nodes.OperationNormalizer operationNode = Interceptors
				.createNode(com.lynxspa.coac.prenormalizer.nodes.OperationNormalizer.class);
		operationNode.setId("Operation");
		addNode(operationNode);
		// $-- /business process/Operation/
		operationNode.setLocale(getLocale());
		// $-- /business process/Operation/
		operationNode.setResource(getStatefullSession());
		// $-- /business process/Operation/
		// $-- /business process/Operation/
		operationNode.setUser(getUser());

		// $-- /business process/PatchedIteratorNode
		// instantiate and configure 'PatchedIteratorNode' node
		com.lynxspa.fpm.nodes.patch.PatchedIteratorNode<com.lynxspa.sdm.entities.events.messages.CAEventMessage> patchedIteratorNodeNode = Interceptors
				.createNode(com.lynxspa.fpm.nodes.patch.PatchedIteratorNode.class);
		patchedIteratorNodeNode.setId("PatchedIteratorNode");
		addNode(patchedIteratorNodeNode);
		// $-- /business process/PatchedIteratorNode/

		// $-- /business process/Provider
		// instantiate and configure 'Provider' node
		com.lynxspa.coac.prenormalizer.nodes.ProviderNormalizer providerNode = Interceptors
				.createNode(com.lynxspa.coac.prenormalizer.nodes.ProviderNormalizer.class);
		providerNode.setId("Provider");
		addNode(providerNode);
		// $-- /business process/Provider/
		providerNode.setLocale(getLocale());
		// $-- /business process/Provider/
		providerNode.setResource(getStatefullSession());
		// $-- /business process/Provider/
		// $-- /business process/Provider/
		providerNode.setUser(getUser());

		// $-- /business process/Read
		// instantiate and configure 'Read' node
		com.lynxspa.fpm.nodes.hibernate.HibernateStandardRead<java.lang.Object, com.lynxspa.sdm.entities.events.messages.CAEventMessage> readNode = Interceptors
				.createNode(com.lynxspa.fpm.nodes.hibernate.HibernateStandardRead.class);
		readNode.setId("Read");
		addNode(readNode);
		// $-- /business process/Read/
		readNode.setReadOnly(false);
		// $-- /business process/Read/
		readNode.setQuery("from CAEventMessage where auditor.deleted = :par0 and operationStatus.state.id.code = :par1 order by senderTimestamp,id asc");
		// $-- /business process/Read/
		readNode.setEntityIdField("id");
		// $-- /business process/Read/
		readNode.setResource(getStatefullSession());
		// $-- /business process/Read/
		readNode.setEntityClass(com.lynxspa.sdm.entities.events.messages.CAEventMessage.class);
		// $-- /business process/Read/
		readNode.setNumResults(getMaxResultsToLoad());
		// $-- /business process/Read/
		readNode.setParameters(com.lynxit.utils.beans.BeanUtils.createList(
				java.lang.Object.class, false, "PRSD"));
		// $-- /business process/Read/
		// $-- /business process/Read/
		readNode.setReadMode(com.lynxspa.fpm.nodes.hibernate.HibernateStandardRead.HibernateReadModes.CURSOR_IDENTIFIER);

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

		// $-- /business process/Security
		// instantiate and configure 'Security' node
		com.lynxspa.coac.prenormalizer.nodes.SecurityNormalizer securityNode = Interceptors
				.createNode(com.lynxspa.coac.prenormalizer.nodes.SecurityNormalizer.class);
		securityNode.setId("Security");
		addNode(securityNode);
		// $-- /business process/Security/
		securityNode.setLocale(getLocale());
		// $-- /business process/Security/
		securityNode.setResource(getStatefullSession());
		// $-- /business process/Security/
		// $-- /business process/Security/
		securityNode.setUser(getUser());

		// $-- /business process/ToPNFA
		// instantiate and configure 'ToPNFA' node
		com.lynxspa.coac.nodes.workflow.EventMessageChangeStateExceptionNode toPNFANode = Interceptors
				.createNode(com.lynxspa.coac.nodes.workflow.EventMessageChangeStateExceptionNode.class);
		toPNFANode.setId("ToPNFA");
		addNode(toPNFANode);
		// $-- /business process/ToPNFA/
		toPNFANode.setCreateLog(true);
		// $-- /business process/ToPNFA/
		// $-- /business process/ToPNFA/
		toPNFANode.setStopExceptionHandling(true);
		// $-- /business process/ToPNFA/
		toPNFANode.setCommit(true);
		// $-- /business process/ToPNFA/
		toPNFANode.setLocale(getLocale());
		// $-- /business process/ToPNFA/
		toPNFANode.setResource(getStatefullSession());
		// $-- /business process/ToPNFA/
		toPNFANode
				.setState(com.lynxspa.sdm.dictionaries.flows.states.CAStatesEVENTMESSAGEFlow.PNFA);
		// $-- /business process/ToPNFA/
		// $-- /business process/ToPNFA/
		// $-- /business process/ToPNFA/
		toPNFANode.setUser(getUser());

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

		// $-- /business process/toNRMP
		// instantiate and configure 'toNRMP' node
		com.lynxspa.coac.nodes.workflow.EventMessageChangeStateNode toNRMPNode = Interceptors
				.createNode(com.lynxspa.coac.nodes.workflow.EventMessageChangeStateNode.class);
		toNRMPNode.setId("toNRMP");
		addNode(toNRMPNode);
		// $-- /business process/toNRMP/
		toNRMPNode.setActiveMessageTransition(false);
		// $-- /business process/toNRMP/
		toNRMPNode.setLocale(getLocale());
		// $-- /business process/toNRMP/
		toNRMPNode
				.setState(com.lynxspa.sdm.dictionaries.flows.states.CAStatesEVENTMESSAGEFlow.PNRM);
		// $-- /business process/toNRMP/
		toNRMPNode.setResource(getStatefullSession());
		// $-- /business process/toNRMP/
		// $-- /business process/toNRMP/
		// $-- /business process/toNRMP/
		toNRMPNode.setCreateLog(false);
		// $-- /business process/toNRMP/
		// $-- /business process/toNRMP/
		// $-- /business process/toNRMP/
		// $-- /business process/toNRMP/
		toNRMPNode.setUser(getUser());

		// instantiate and configure subprocesses

		// link nodes
		// $-- /business process/Event Type/out/business process/Security/in
		eventTypeNode.connectNodeToOut(securityNode);
		eventTypeNode.connectNodeToException(getDefaultExceptionHandlerNode());
		hibernateStandardCommitNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/LiveCycleValidatorNode/out/business process/Read/in
		liveCycleValidatorNodeNode.connectNodeToOut(readNode);
		// $-- /business process/LiveCycleValidatorNode/exception/business process/LogCoacException/exc
		liveCycleValidatorNodeNode.connectNodeToException(logCoacExceptionNode);
		// $-- /business process/Operation/out/business process/Provider/in
		operationNode.connectNodeToOut(providerNode);
		// $-- /business process/Operation/exception/business process/Rollback/exc
		operationNode.connectNodeToException(rollbackNode);
		// $-- /business process/PatchedIteratorNode/out/business process/Operation/in
		patchedIteratorNodeNode.connectNodeToOut(operationNode);
		// $-- /business process/PatchedIteratorNode/exception/business process/ToPNFA/exc
		patchedIteratorNodeNode.connectNodeToException(toPNFANode);
		// $-- /business process/Provider/out/business process/Event Type/in
		providerNode.connectNodeToOut(eventTypeNode);
		providerNode.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/Read/out/business process/PatchedIteratorNode/in
		readNode.connectNodeToOut(patchedIteratorNodeNode);
		readNode.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/Security/out/business process/toNRMP/in
		securityNode.connectNodeToOut(toNRMPNode);
		securityNode.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/Update/out/business process/HibernateStandardCommit/in
		updateNode.connectNodeToOut(hibernateStandardCommitNode);
		updateNode.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/toNRMP/out/business process/Update/in
		toNRMPNode.connectNodeToOut(updateNode);
		toNRMPNode.connectNodeToException(getDefaultExceptionHandlerNode());

		// link subprocesses

		// link inputs
		// $-- /business process/input/business process/LiveCycleValidatorNode/in
		input.connectNodeToOut(liveCycleValidatorNodeNode);

		eventTypeNode.init();
		hibernateStandardCommitNode.init();
		liveCycleValidatorNodeNode.init();
		logCoacExceptionNode.init();
		operationNode.init();
		patchedIteratorNodeNode.init();
		providerNode.init();
		readNode.init();
		rollbackNode.init();
		securityNode.init();
		toPNFANode.init();
		updateNode.init();
		toNRMPNode.init();
	}

}