package com.lynxspa.coac.exportevents.businesprocess;

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
@GeneratedType(definitionFile = "src/com/lynxspa/coac/exportevents/businesprocess/ExportEvents.fpmprocess")
public class ExportEventsProcess extends BusinessProcess {
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

	public ExportEventsProcess(String id) {
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
				.setRequiredLiveCycle(com.lynxspa.sdm.dictionaries.CALiveCycle.EXPORT_EVENTS);
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
				.setLog(com.lynxspa.sdm.dictionaries.logs.LogErrorDict.SEND_BACK_OFFICE_ERROR);

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
		readNode.setQuery("from CAEventGroup where auditor.deleted = :par0 and auditor.refId=:par1 and (operationStatus.state.id.code = :par2 or operationStatus.state.id.code = :par3) and entityDeadLine<:operationalDate order by executionDate,id asc");
		// $-- /business process/Read/
		readNode.setNumResults(getMaxResultsToLoad());
		// $-- /business process/Read/
		readNode.setResource(getStatefullSession());
		// $-- /business process/Read/
		readNode.setOperationalDateTimeMarginParam(com.lynxspa.sdm.dictionaries.config.CAConfiguration.BACKOFFICEOPERATIONALDATE);
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
				java.lang.Object.class, false, 0l, "NSTA", "NTAF"));
		// $-- /business process/Read/

		// $-- /business process/SENT_BO_KO
		// instantiate and configure 'SENT_BO_KO' node
		com.lynxspa.coac.nodes.workflow.EventGroupChangeStateExceptionNode sENTBOKONode = Interceptors
				.createNode(com.lynxspa.coac.nodes.workflow.EventGroupChangeStateExceptionNode.class);
		sENTBOKONode.setId("SENT_BO_KO");
		addNode(sENTBOKONode);
		// $-- /business process/SENT_BO_KO/
		sENTBOKONode.setCreateLog(true);
		// $-- /business process/SENT_BO_KO/
		// $-- /business process/SENT_BO_KO/
		sENTBOKONode.setStopExceptionHandling(true);
		// $-- /business process/SENT_BO_KO/
		sENTBOKONode.setCommit(true);
		// $-- /business process/SENT_BO_KO/
		sENTBOKONode.setLocale(getLocale());
		// $-- /business process/SENT_BO_KO/
		sENTBOKONode.setResource(getStatefullSession());
		// $-- /business process/SENT_BO_KO/
		sENTBOKONode
				.setState(com.lynxspa.sdm.dictionaries.flows.states.CAStatesEVENTGROUPFlow.SBKO);
		// $-- /business process/SENT_BO_KO/
		// $-- /business process/SENT_BO_KO/
		// $-- /business process/SENT_BO_KO/
		sENTBOKONode.setUser(getUser());

		// $-- /business process/SENT_BO_OK
		// instantiate and configure 'SENT_BO_OK' node
		com.lynxspa.coac.nodes.workflow.EventGroupChangeStateNode sENTBOOKNode = Interceptors
				.createNode(com.lynxspa.coac.nodes.workflow.EventGroupChangeStateNode.class);
		sENTBOOKNode.setId("SENT_BO_OK");
		addNode(sENTBOOKNode);
		// $-- /business process/SENT_BO_OK/
		sENTBOOKNode.setActiveMessageTransition(false);
		// $-- /business process/SENT_BO_OK/
		sENTBOOKNode.setLocale(getLocale());
		// $-- /business process/SENT_BO_OK/
		sENTBOOKNode
				.setState(com.lynxspa.sdm.dictionaries.flows.states.CAStatesEVENTGROUPFlow.SBOK);
		// $-- /business process/SENT_BO_OK/
		sENTBOOKNode.setResource(getStatefullSession());
		// $-- /business process/SENT_BO_OK/
		// $-- /business process/SENT_BO_OK/
		sENTBOOKNode
				.setMessage(com.lynxspa.sdm.dictionaries.logs.LogInfoDict.START_HISTORIFY);
		// $-- /business process/SENT_BO_OK/
		sENTBOOKNode.setCreateLog(false);
		// $-- /business process/SENT_BO_OK/
		// $-- /business process/SENT_BO_OK/
		// $-- /business process/SENT_BO_OK/
		// $-- /business process/SENT_BO_OK/
		sENTBOOKNode.setUser(getUser());

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
		// $-- /business process/PatchedIteratorNode/out/business process/SENT_BO_OK/in
		patchedIteratorNodeNode.connectNodeToOut(sENTBOOKNode);
		// $-- /business process/PatchedIteratorNode/exception/business process/SENT_BO_KO/exc
		patchedIteratorNodeNode.connectNodeToException(sENTBOKONode);
		// $-- /business process/Read/out/business process/PatchedIteratorNode/in
		readNode.connectNodeToOut(patchedIteratorNodeNode);
		readNode.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/SENT_BO_OK/out/business process/Update/in
		sENTBOOKNode.connectNodeToOut(updateNode);
		sENTBOOKNode.connectNodeToException(getDefaultExceptionHandlerNode());
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
		patchedIteratorNodeNode.init();
		readNode.init();
		sENTBOKONode.init();
		sENTBOOKNode.init();
		updateNode.init();
	}

}