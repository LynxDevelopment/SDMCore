package com.lynxspa.coac.notificationmanagers.businessprocess;

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
@GeneratedType(definitionFile = "src/com/lynxspa/coac/notificationmanagers/businessprocess/SendNotificationManagers.fpmprocess")
public class SendNotificationManagersProcess extends BusinessProcess {
	/*
	 * Parameters declarations
	 */
	private Integer evenDuration_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "evenDuration")
	public Integer getEvenDuration() {
		return evenDuration_;
	}

	public void setEvenDuration(Integer value) {
		this.evenDuration_ = value;
	}

	private Integer hourToBeginEven_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "hourToBeginEven")
	public Integer getHourToBeginEven() {
		return hourToBeginEven_;
	}

	public void setHourToBeginEven(Integer value) {
		this.hourToBeginEven_ = value;
	}

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

	private Integer previosHoursForAlarm_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "previosHoursForAlarm")
	public Integer getPreviosHoursForAlarm() {
		return previosHoursForAlarm_;
	}

	public void setPreviosHoursForAlarm(Integer value) {
		this.previosHoursForAlarm_ = value;
	}

	private String smtpFrom_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "smtpFrom")
	public String getSmtpFrom() {
		return smtpFrom_;
	}

	public void setSmtpFrom(String value) {
		this.smtpFrom_ = value;
	}

	private String smtpHost_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "smtpHost")
	public String getSmtpHost() {
		return smtpHost_;
	}

	public void setSmtpHost(String value) {
		this.smtpHost_ = value;
	}

	private String smtpPassword_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "smtpPassword")
	public String getSmtpPassword() {
		return smtpPassword_;
	}

	public void setSmtpPassword(String value) {
		this.smtpPassword_ = value;
	}

	private String smtpPort_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "smtpPort")
	public String getSmtpPort() {
		return smtpPort_;
	}

	public void setSmtpPort(String value) {
		this.smtpPort_ = value;
	}

	private String smtpUser_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "smtpUser")
	public String getSmtpUser() {
		return smtpUser_;
	}

	public void setSmtpUser(String value) {
		this.smtpUser_ = value;
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

	private String timeZone_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "timeZone")
	public String getTimeZone() {
		return timeZone_;
	}

	public void setTimeZone(String value) {
		this.timeZone_ = value;
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

	public SendNotificationManagersProcess(String id) {
		super(id);
	}

	public void init() throws Exception {
		Properties initParams = ConfigParams.getInstance().getProperties();

		// instantiate and configure inputs
		// instantiate and configure 'input' input
		input = addInput("input");

		// instantiate and configure outputs

		// instantiate and configure nodes
		// $-- /business process/EventGroupChangeStateExceptionNode
		// instantiate and configure 'EventGroupChangeStateExceptionNode' node
		com.lynxspa.coac.nodes.workflow.EventGroupChangeStateExceptionNode eventGroupChangeStateExceptionNodeNode = Interceptors
				.createNode(com.lynxspa.coac.nodes.workflow.EventGroupChangeStateExceptionNode.class);
		eventGroupChangeStateExceptionNodeNode
				.setId("EventGroupChangeStateExceptionNode");
		addNode(eventGroupChangeStateExceptionNodeNode);
		// $-- /business process/EventGroupChangeStateExceptionNode/
		eventGroupChangeStateExceptionNodeNode.setCreateLog(true);
		// $-- /business process/EventGroupChangeStateExceptionNode/
		// $-- /business process/EventGroupChangeStateExceptionNode/
		eventGroupChangeStateExceptionNodeNode.setStopExceptionHandling(false);
		// $-- /business process/EventGroupChangeStateExceptionNode/
		eventGroupChangeStateExceptionNodeNode.setCommit(false);
		// $-- /business process/EventGroupChangeStateExceptionNode/
		eventGroupChangeStateExceptionNodeNode.setLocale(getLocale());
		// $-- /business process/EventGroupChangeStateExceptionNode/
		eventGroupChangeStateExceptionNodeNode
				.setResource(getStatefullSession());
		// $-- /business process/EventGroupChangeStateExceptionNode/
		eventGroupChangeStateExceptionNodeNode
				.setState(com.lynxspa.sdm.dictionaries.flows.states.CAStatesEVENTGROUPFlow.SUSC);
		// $-- /business process/EventGroupChangeStateExceptionNode/
		// $-- /business process/EventGroupChangeStateExceptionNode/
		// $-- /business process/EventGroupChangeStateExceptionNode/
		eventGroupChangeStateExceptionNodeNode.setUser(getUser());

		// $-- /business process/EventGroupChangeStateNode
		// instantiate and configure 'EventGroupChangeStateNode' node
		com.lynxspa.coac.nodes.workflow.EventGroupChangeStateNode eventGroupChangeStateNodeNode = Interceptors
				.createNode(com.lynxspa.coac.nodes.workflow.EventGroupChangeStateNode.class);
		eventGroupChangeStateNodeNode.setId("EventGroupChangeStateNode");
		addNode(eventGroupChangeStateNodeNode);
		// $-- /business process/EventGroupChangeStateNode/
		eventGroupChangeStateNodeNode.setActiveMessageTransition(false);
		// $-- /business process/EventGroupChangeStateNode/
		eventGroupChangeStateNodeNode.setLocale(getLocale());
		// $-- /business process/EventGroupChangeStateNode/
		eventGroupChangeStateNodeNode
				.setState(com.lynxspa.sdm.dictionaries.flows.states.CAStatesEVENTGROUPFlow.SUSC);
		// $-- /business process/EventGroupChangeStateNode/
		eventGroupChangeStateNodeNode.setResource(getStatefullSession());
		// $-- /business process/EventGroupChangeStateNode/
		// $-- /business process/EventGroupChangeStateNode/
		// $-- /business process/EventGroupChangeStateNode/
		eventGroupChangeStateNodeNode.setCreateLog(false);
		// $-- /business process/EventGroupChangeStateNode/
		// $-- /business process/EventGroupChangeStateNode/
		// $-- /business process/EventGroupChangeStateNode/
		// $-- /business process/EventGroupChangeStateNode/
		eventGroupChangeStateNodeNode.setUser(getUser());

		// $-- /business process/GenerateMessageBody
		// instantiate and configure 'GenerateMessageBody' node
		com.lynxspa.coac.notificationmanagers.nodes.GenerateMessageBody generateMessageBodyNode = Interceptors
				.createNode(com.lynxspa.coac.notificationmanagers.nodes.GenerateMessageBody.class);
		generateMessageBodyNode.setId("GenerateMessageBody");
		addNode(generateMessageBodyNode);
		// $-- /business process/GenerateMessageBody/

		// $-- /business process/GetManagersEvent
		// instantiate and configure 'GetManagersEvent' node
		com.lynxspa.coac.notificationmanagers.nodes.GetManagersEvent getManagersEventNode = Interceptors
				.createNode(com.lynxspa.coac.notificationmanagers.nodes.GetManagersEvent.class);
		getManagersEventNode.setId("GetManagersEvent");
		addNode(getManagersEventNode);
		// $-- /business process/GetManagersEvent/
		getManagersEventNode.setResourceStateless(getStatelessSession());
		// $-- /business process/GetManagersEvent/
		getManagersEventNode.setLocale(getLocale());
		// $-- /business process/GetManagersEvent/
		// $-- /business process/GetManagersEvent/
		getManagersEventNode.setUser(getUser());

		// $-- /business process/HibernateStandardRead
		// instantiate and configure 'HibernateStandardRead' node
		com.lynxspa.fpm.nodes.hibernate.HibernateStandardRead<java.lang.Object, com.lynxspa.sdm.entities.events.CAEventGroup> hibernateStandardReadNode = Interceptors
				.createNode(com.lynxspa.fpm.nodes.hibernate.HibernateStandardRead.class);
		hibernateStandardReadNode.setId("HibernateStandardRead");
		addNode(hibernateStandardReadNode);
		// $-- /business process/HibernateStandardRead/
		hibernateStandardReadNode.setReadOnly(false);
		// $-- /business process/HibernateStandardRead/
		hibernateStandardReadNode
				.setQuery("from CAEventGroup where auditor.deleted=:par0 and operationStatus.state.id.code=:par1 and auditor.refId=:par2");
		// $-- /business process/HibernateStandardRead/
		hibernateStandardReadNode.setEntityIdField("id");
		// $-- /business process/HibernateStandardRead/
		hibernateStandardReadNode.setResource(getStatefullSession());
		// $-- /business process/HibernateStandardRead/
		hibernateStandardReadNode
				.setEntityClass(com.lynxspa.sdm.entities.events.CAEventGroup.class);
		// $-- /business process/HibernateStandardRead/
		hibernateStandardReadNode.setNumResults(getMaxResultsToLoad());
		// $-- /business process/HibernateStandardRead/
		hibernateStandardReadNode
				.setParameters(com.lynxit.utils.beans.BeanUtils.createList(
						java.lang.Object.class, false, "AUTH", 0l));
		// $-- /business process/HibernateStandardRead/
		// $-- /business process/HibernateStandardRead/
		hibernateStandardReadNode
				.setReadMode(com.lynxspa.fpm.nodes.hibernate.HibernateStandardRead.HibernateReadModes.CURSOR_IDENTIFIER);

		// $-- /business process/LogCoacDebug
		// instantiate and configure 'LogCoacDebug' node
		com.lynxspa.coac.nodes.logs.LogCoacDebug<com.lynxspa.coac.notificationmanagers.beans.NotificationEventBean> logCoacDebugNode = Interceptors
				.createNode(com.lynxspa.coac.nodes.logs.LogCoacDebug.class);
		logCoacDebugNode.setId("LogCoacDebug");
		addNode(logCoacDebugNode);
		// $-- /business process/LogCoacDebug/
		logCoacDebugNode.setDataSource(getStatelessSession());
		// $-- /business process/LogCoacDebug/
		((DynaParamsNode) logCoacDebugNode)
				.setDynamicParamValues(
						"arguments",
						com.lynxit.utils.scripting.Language.BEAN_SHELL
								.createExpression(
										"message.getEventGroup().getMasterEvent().getId()",
										java.lang.Object.class));
		// $-- /business process/LogCoacDebug/
		logCoacDebugNode.setCommitInNewTransaction(true);
		// $-- /business process/LogCoacDebug/
		logCoacDebugNode.setLocale(getLocale());
		// $-- /business process/LogCoacDebug/
		// $-- /business process/LogCoacDebug/
		logCoacDebugNode.setUser(getUser());
		// $-- /business process/LogCoacDebug/
		logCoacDebugNode
				.setLog(com.lynxspa.sdm.dictionaries.logs.LogDebugDict.NOTIFICATION_NOT_NECESARY);

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
				.setLog(com.lynxspa.sdm.dictionaries.logs.LogErrorDict.NOTIFICATION_MANAGER_ERROR);

		// $-- /business process/MappingNode
		// instantiate and configure 'MappingNode' node
		com.lynxspa.coac.notificationmanagers.mappings.NotificationEventMap mappingNodeNode = Interceptors
				.createNode(com.lynxspa.coac.notificationmanagers.mappings.NotificationEventMap.class);
		mappingNodeNode.setId("MappingNode");
		addNode(mappingNodeNode);
		// $-- /business process/MappingNode/

		// $-- /business process/PatchedIteratorNode
		// instantiate and configure 'PatchedIteratorNode' node
		com.lynxspa.fpm.nodes.patch.PatchedIteratorNode<com.lynxspa.sdm.entities.events.CAEventGroup> patchedIteratorNodeNode = Interceptors
				.createNode(com.lynxspa.fpm.nodes.patch.PatchedIteratorNode.class);
		patchedIteratorNodeNode.setId("PatchedIteratorNode");
		addNode(patchedIteratorNodeNode);
		// $-- /business process/PatchedIteratorNode/

		// $-- /business process/SendNotificationEvent
		// instantiate and configure 'SendNotificationEvent' node
		com.lynxspa.coac.notificationmanagers.nodes.SendNotificationEvent sendNotificationEventNode = Interceptors
				.createNode(com.lynxspa.coac.notificationmanagers.nodes.SendNotificationEvent.class);
		sendNotificationEventNode.setId("SendNotificationEvent");
		addNode(sendNotificationEventNode);
		// $-- /business process/SendNotificationEvent/
		sendNotificationEventNode.setSmtp_host(getSmtpHost());
		// $-- /business process/SendNotificationEvent/
		sendNotificationEventNode.setSmtp_password(getSmtpPassword());
		// $-- /business process/SendNotificationEvent/
		sendNotificationEventNode.setSmtp_user(getSmtpUser());
		// $-- /business process/SendNotificationEvent/
		// $-- /business process/SendNotificationEvent/
		sendNotificationEventNode.setMessage_from_ad(getSmtpFrom());
		// $-- /business process/SendNotificationEvent/
		sendNotificationEventNode.setSmtp_port(getSmtpPort());

		// $-- /business process/SwitchNode
		// instantiate and configure 'SwitchNode' node
		com.lynxspa.coac.notificationmanagers.nodes.IsNotificableSwitch switchNodeNode = Interceptors
				.createNode(com.lynxspa.coac.notificationmanagers.nodes.IsNotificableSwitch.class);
		switchNodeNode.setId("SwitchNode");
		addNode(switchNodeNode);
		// $-- /business process/SwitchNode/

		// $-- /business process/ToEvent
		// instantiate and configure 'ToEvent' node
		com.lynxspa.coac.notificationmanagers.mappings.ToEventMap toEventNode = Interceptors
				.createNode(com.lynxspa.coac.notificationmanagers.mappings.ToEventMap.class);
		toEventNode.setId("ToEvent");
		addNode(toEventNode);
		// $-- /business process/ToEvent/

		// $-- /business process/setCalendarEvent
		// instantiate and configure 'setCalendarEvent' node
		com.lynxspa.coac.notificationmanagers.nodes.setCalendarEvent setCalendarEventNode = Interceptors
				.createNode(com.lynxspa.coac.notificationmanagers.nodes.setCalendarEvent.class);
		setCalendarEventNode.setId("setCalendarEvent");
		addNode(setCalendarEventNode);
		// $-- /business process/setCalendarEvent/
		setCalendarEventNode.setHourToBeginEven(getHourToBeginEven());
		// $-- /business process/setCalendarEvent/
		setCalendarEventNode.setPreviosHoursForAlarm(getPreviosHoursForAlarm());
		// $-- /business process/setCalendarEvent/
		setCalendarEventNode.setTimeZone(getTimeZone());
		// $-- /business process/setCalendarEvent/
		// $-- /business process/setCalendarEvent/
		setCalendarEventNode.setEvenDuration(getEvenDuration());

		// instantiate and configure subprocesses

		// link nodes
		eventGroupChangeStateNodeNode.connectNodeToOut(getDefaultStopperNode());
		eventGroupChangeStateNodeNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/GenerateMessageBody/out/business process/SendNotificationEvent/in
		generateMessageBodyNode.connectNodeToOut(sendNotificationEventNode);
		generateMessageBodyNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/GetManagersEvent/out/business process/SwitchNode/in
		getManagersEventNode.connectNodeToOut(switchNodeNode);
		getManagersEventNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/HibernateStandardRead/out/business process/PatchedIteratorNode/in
		hibernateStandardReadNode.connectNodeToOut(patchedIteratorNodeNode);
		// $-- /business process/HibernateStandardRead/exception/business process/LogCoacException/exc
		hibernateStandardReadNode.connectNodeToException(logCoacExceptionNode);
		// $-- /business process/LogCoacDebug/out/business process/ToEvent/in
		logCoacDebugNode.connectNodeToOut(toEventNode);
		logCoacDebugNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/MappingNode/out/business process/GetManagersEvent/in
		mappingNodeNode.connectNodeToOut(getManagersEventNode);
		mappingNodeNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/PatchedIteratorNode/out/business process/MappingNode/in
		patchedIteratorNodeNode.connectNodeToOut(mappingNodeNode);
		// $-- /business process/PatchedIteratorNode/exception/business process/EventGroupChangeStateExceptionNode/exc
		patchedIteratorNodeNode
				.connectNodeToException(eventGroupChangeStateExceptionNodeNode);
		// $-- /business process/SendNotificationEvent/out/business process/EventGroupChangeStateNode/in
		sendNotificationEventNode
				.connectNodeToOut(eventGroupChangeStateNodeNode);
		sendNotificationEventNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/SwitchNode/default/business process/LogCoacDebug/in
		switchNodeNode.connectNodeToDefault(logCoacDebugNode);
		// $-- /business process/SwitchNode/true/business process/setCalendarEvent/in
		switchNodeNode.connectNodeToTrue(setCalendarEventNode);
		switchNodeNode.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/ToEvent/out/business process/EventGroupChangeStateNode/in
		toEventNode.connectNodeToOut(eventGroupChangeStateNodeNode);
		toEventNode.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/setCalendarEvent/out/business process/GenerateMessageBody/in
		setCalendarEventNode.connectNodeToOut(generateMessageBodyNode);
		setCalendarEventNode
				.connectNodeToException(getDefaultExceptionHandlerNode());

		// link subprocesses

		// link inputs
		// $-- /business process/input/business process/HibernateStandardRead/in
		input.connectNodeToOut(hibernateStandardReadNode);

		eventGroupChangeStateExceptionNodeNode.init();
		eventGroupChangeStateNodeNode.init();
		generateMessageBodyNode.init();
		getManagersEventNode.init();
		hibernateStandardReadNode.init();
		logCoacDebugNode.init();
		logCoacExceptionNode.init();
		mappingNodeNode.init();
		patchedIteratorNodeNode.init();
		sendNotificationEventNode.init();
		switchNodeNode.init();
		toEventNode.init();
		setCalendarEventNode.init();
	}

}