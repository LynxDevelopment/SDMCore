package com.lynxspa.coac.matcher.businessprocess;

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
@GeneratedType(definitionFile = "src/com/lynxspa/coac/matcher/businessprocess/Matcher.fpmprocess")
public class MatcherProcess extends BusinessProcess {
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

	public MatcherProcess(String id) {
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

		// $-- /business process/HibernateStandardRead1
		// instantiate and configure 'HibernateStandardRead1' node
		com.lynxspa.fpm.nodes.hibernate.HibernateStandardRead<java.lang.Object, com.lynxspa.sdm.entities.events.CAEventCollected> hibernateStandardRead1Node = Interceptors
				.createNode(com.lynxspa.fpm.nodes.hibernate.HibernateStandardRead.class);
		hibernateStandardRead1Node.setId("HibernateStandardRead1");
		addNode(hibernateStandardRead1Node);
		// $-- /business process/HibernateStandardRead1/
		hibernateStandardRead1Node.setReadOnly(false);
		// $-- /business process/HibernateStandardRead1/
		hibernateStandardRead1Node
				.setQuery("from CAEventCollected where auditor.deleted=:par0 and auditor.refId=:par1 and operationStatus.state.id.code=:par2");
		// $-- /business process/HibernateStandardRead1/
		hibernateStandardRead1Node.setEntityIdField("id");
		// $-- /business process/HibernateStandardRead1/
		hibernateStandardRead1Node.setResource(getStatefullSession());
		// $-- /business process/HibernateStandardRead1/
		hibernateStandardRead1Node
				.setEntityClass(com.lynxspa.sdm.entities.events.CAEventCollected.class);
		// $-- /business process/HibernateStandardRead1/
		hibernateStandardRead1Node.setNumResults(getMaxResultsToLoad());
		// $-- /business process/HibernateStandardRead1/
		hibernateStandardRead1Node
				.setParameters(com.lynxit.utils.beans.BeanUtils.createList(
						java.lang.Object.class, false, 0l, "UNMC"));
		// $-- /business process/HibernateStandardRead1/
		// $-- /business process/HibernateStandardRead1/
		hibernateStandardRead1Node
				.setReadMode(com.lynxspa.fpm.nodes.hibernate.HibernateStandardRead.HibernateReadModes.CURSOR_IDENTIFIER);

		// $-- /business process/LiveCycleValidate
		// instantiate and configure 'LiveCycleValidate' node
		com.lynxspa.coac.nodes.LiveCycleValidatorNode<java.lang.Object> liveCycleValidateNode = Interceptors
				.createNode(com.lynxspa.coac.nodes.LiveCycleValidatorNode.class);
		liveCycleValidateNode.setId("LiveCycleValidate");
		addNode(liveCycleValidateNode);
		// $-- /business process/LiveCycleValidate/
		liveCycleValidateNode
				.setRequiredLiveCycle(com.lynxspa.sdm.dictionaries.CALiveCycle.EVENT_MATCH);
		// $-- /business process/LiveCycleValidate/
		liveCycleValidateNode.setResource(getStatefullSession());
		// $-- /business process/LiveCycleValidate/

		// $-- /business process/LogException
		// instantiate and configure 'LogException' node
		com.lynxspa.coac.nodes.logs.LogCoacException logExceptionNode = Interceptors
				.createNode(com.lynxspa.coac.nodes.logs.LogCoacException.class);
		logExceptionNode.setId("LogException");
		addNode(logExceptionNode);
		// $-- /business process/LogException/
		logExceptionNode.setDataSource(getStatefullSession());
		// $-- /business process/LogException/
		logExceptionNode.setStopExceptionHandling(true);
		// $-- /business process/LogException/
		// $-- /business process/LogException/
		logExceptionNode.setCommitInNewTransaction(true);
		// $-- /business process/LogException/
		logExceptionNode.setLocale(getLocale());
		// $-- /business process/LogException/
		// $-- /business process/LogException/
		logExceptionNode.setUser(getUser());
		// $-- /business process/LogException/
		logExceptionNode
				.setLog(com.lynxspa.sdm.dictionaries.logs.LogErrorDict.MATCHING_FAIL);

		// $-- /business process/Match
		// instantiate and configure 'Match' node
		com.lynxspa.coac.matcher.nodes.CallMatcherProcessor matchNode = Interceptors
				.createNode(com.lynxspa.coac.matcher.nodes.CallMatcherProcessor.class);
		matchNode.setId("Match");
		addNode(matchNode);
		// $-- /business process/Match/
		matchNode.setLocale(getLocale());
		// $-- /business process/Match/
		matchNode.setResource(getStatefullSession());
		// $-- /business process/Match/
		// $-- /business process/Match/
		matchNode.setUser(getUser());

		// $-- /business process/PatchedIteratorNode
		// instantiate and configure 'PatchedIteratorNode' node
		com.lynxspa.fpm.nodes.patch.PatchedIteratorNode<com.lynxspa.sdm.entities.events.CAEventCollected> patchedIteratorNodeNode = Interceptors
				.createNode(com.lynxspa.fpm.nodes.patch.PatchedIteratorNode.class);
		patchedIteratorNodeNode.setId("PatchedIteratorNode");
		addNode(patchedIteratorNodeNode);
		// $-- /business process/PatchedIteratorNode/

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

		// $-- /business process/ToManualMatch
		// instantiate and configure 'ToManualMatch' node
		com.lynxspa.coac.nodes.workflow.EventCollectedChangeStateExceptionNode toManualMatchNode = Interceptors
				.createNode(com.lynxspa.coac.nodes.workflow.EventCollectedChangeStateExceptionNode.class);
		toManualMatchNode.setId("ToManualMatch");
		addNode(toManualMatchNode);
		// $-- /business process/ToManualMatch/
		toManualMatchNode.setCreateLog(false);
		// $-- /business process/ToManualMatch/
		// $-- /business process/ToManualMatch/
		toManualMatchNode.setStopExceptionHandling(true);
		// $-- /business process/ToManualMatch/
		toManualMatchNode.setCommit(true);
		// $-- /business process/ToManualMatch/
		toManualMatchNode.setLocale(getLocale());
		// $-- /business process/ToManualMatch/
		toManualMatchNode.setResource(getStatefullSession());
		// $-- /business process/ToManualMatch/
		toManualMatchNode
				.setState(com.lynxspa.sdm.dictionaries.flows.states.CAStatesEVENTCOLLECTEDFlow.MNMC);
		// $-- /business process/ToManualMatch/
		// $-- /business process/ToManualMatch/
		// $-- /business process/ToManualMatch/
		toManualMatchNode.setUser(getUser());

		// $-- /business process/ToMatched
		// instantiate and configure 'ToMatched' node
		com.lynxspa.coac.nodes.workflow.EventCollectedChangeStateNode toMatchedNode = Interceptors
				.createNode(com.lynxspa.coac.nodes.workflow.EventCollectedChangeStateNode.class);
		toMatchedNode.setId("ToMatched");
		addNode(toMatchedNode);
		// $-- /business process/ToMatched/
		toMatchedNode.setActiveMessageTransition(false);
		// $-- /business process/ToMatched/
		toMatchedNode.setLocale(getLocale());
		// $-- /business process/ToMatched/
		toMatchedNode
				.setState(com.lynxspa.sdm.dictionaries.flows.states.CAStatesEVENTCOLLECTEDFlow.MTCH);
		// $-- /business process/ToMatched/
		toMatchedNode.setResource(getStatefullSession());
		// $-- /business process/ToMatched/
		// $-- /business process/ToMatched/
		// $-- /business process/ToMatched/
		toMatchedNode.setCreateLog(false);
		// $-- /business process/ToMatched/
		// $-- /business process/ToMatched/
		// $-- /business process/ToMatched/
		// $-- /business process/ToMatched/
		toMatchedNode.setUser(getUser());

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
		// $-- /business process/HibernateStandardRead1/out/business process/PatchedIteratorNode/in
		hibernateStandardRead1Node.connectNodeToOut(patchedIteratorNodeNode);
		hibernateStandardRead1Node
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/LiveCycleValidate/out/business process/HibernateStandardRead1/in
		liveCycleValidateNode.connectNodeToOut(hibernateStandardRead1Node);
		// $-- /business process/LiveCycleValidate/exception/business process/LogException/exc
		liveCycleValidateNode.connectNodeToException(logExceptionNode);
		// $-- /business process/Match/out/business process/ToMatched/in
		matchNode.connectNodeToOut(toMatchedNode);
		// $-- /business process/Match/exception/business process/Rollback/exc
		matchNode.connectNodeToException(rollbackNode);
		// $-- /business process/PatchedIteratorNode/out/business process/Match/in
		patchedIteratorNodeNode.connectNodeToOut(matchNode);
		// $-- /business process/PatchedIteratorNode/exception/business process/ToManualMatch/exc
		patchedIteratorNodeNode.connectNodeToException(toManualMatchNode);
		// $-- /business process/ToMatched/out/business process/Update/in
		toMatchedNode.connectNodeToOut(updateNode);
		toMatchedNode.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/Update/out/business process/Commit/in
		updateNode.connectNodeToOut(commitNode);
		updateNode.connectNodeToException(getDefaultExceptionHandlerNode());

		// link subprocesses

		// link inputs
		// $-- /business process/input/business process/LiveCycleValidate/in
		input.connectNodeToOut(liveCycleValidateNode);

		commitNode.init();
		hibernateStandardRead1Node.init();
		liveCycleValidateNode.init();
		logExceptionNode.init();
		matchNode.init();
		patchedIteratorNodeNode.init();
		rollbackNode.init();
		toManualMatchNode.init();
		toMatchedNode.init();
		updateNode.init();
	}

}