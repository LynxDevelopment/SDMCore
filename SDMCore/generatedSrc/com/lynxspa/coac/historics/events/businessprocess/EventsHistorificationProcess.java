package com.lynxspa.coac.historics.events.businessprocess;

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
@GeneratedType(definitionFile = "src/com/lynxspa/coac/historics/events/businessprocess/eventsHistorification.fpmprocess")
public class EventsHistorificationProcess extends BusinessProcess {
	/*
	 * Parameters declarations
	 */
	private Integer eventsHistorificationCommitSize_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "eventsHistorificationCommitSize")
	public Integer getEventsHistorificationCommitSize() {
		return eventsHistorificationCommitSize_;
	}

	public void setEventsHistorificationCommitSize(Integer value) {
		this.eventsHistorificationCommitSize_ = value;
	}

	private String eventsHistorificationCron_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "eventsHistorificationCron")
	public String getEventsHistorificationCron() {
		return eventsHistorificationCron_;
	}

	public void setEventsHistorificationCron(String value) {
		this.eventsHistorificationCron_ = value;
	}

	private String locale_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "locale")
	public String getLocale() {
		return locale_;
	}

	public void setLocale(String value) {
		this.locale_ = value;
	}

	private com.lynxit.fpm.resources.Resource sessionFactory_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "sessionFactory")
	public com.lynxit.fpm.resources.Resource getSessionFactory() {
		return sessionFactory_;
	}

	public void setSessionFactory(com.lynxit.fpm.resources.Resource value) {
		this.sessionFactory_ = value;
	}

	private com.lynxit.fpm.resources.Resource statelessSessionFactory_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "statelessSessionFactory")
	public com.lynxit.fpm.resources.Resource getStatelessSessionFactory() {
		return statelessSessionFactory_;
	}

	public void setStatelessSessionFactory(
			com.lynxit.fpm.resources.Resource value) {
		this.statelessSessionFactory_ = value;
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
	private com.lynxit.fpm.BusinessProcessInput<com.lynxit.fpm.events.scheduler.ScheduledEvent> input;

	public com.lynxit.fpm.BusinessProcessInput<com.lynxit.fpm.events.scheduler.ScheduledEvent> asInputInput() {
		return input;
	}

	/*
	 * outputs declarations
	 */

	public EventsHistorificationProcess(String id) {
		super(id);
	}

	public void init() throws Exception {
		Properties initParams = ConfigParams.getInstance().getProperties();

		// instantiate and configure inputs
		// instantiate and configure 'input' input
		input = addInput("input");

		// instantiate and configure outputs

		// instantiate and configure nodes
		// $-- /business process/EventsHistorificationNode1
		// instantiate and configure 'EventsHistorificationNode1' node
		com.lynxspa.coac.historics.events.nodes.EventsHistorificationNode<com.lynxit.fpm.events.scheduler.ScheduledEvent> eventsHistorificationNode1Node = Interceptors
				.createNode(com.lynxspa.coac.historics.events.nodes.EventsHistorificationNode.class);
		eventsHistorificationNode1Node.setId("EventsHistorificationNode1");
		addNode(eventsHistorificationNode1Node);
		// $-- /business process/EventsHistorificationNode1/
		eventsHistorificationNode1Node
				.setResourceStateless(getStatelessSessionFactory());
		// $-- /business process/EventsHistorificationNode1/
		eventsHistorificationNode1Node.setLocale(getLocale());
		// $-- /business process/EventsHistorificationNode1/
		eventsHistorificationNode1Node.setResource(getSessionFactory());
		// $-- /business process/EventsHistorificationNode1/
		eventsHistorificationNode1Node
				.setHistorificationCommitSize(getEventsHistorificationCommitSize());
		// $-- /business process/EventsHistorificationNode1/
		// $-- /business process/EventsHistorificationNode1/
		eventsHistorificationNode1Node.setUser(getUser());

		// instantiate and configure subprocesses

		// link nodes
		eventsHistorificationNode1Node
				.connectNodeToException(getDefaultExceptionHandlerNode());

		// link subprocesses

		// link inputs
		// $-- /business process/input/business process/EventsHistorificationNode1/in
		input.connectNodeToOut(eventsHistorificationNode1Node);

		eventsHistorificationNode1Node.init();
	}

}