package com.lynxspa.coac.historics.livetime.businessprocess;

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
@GeneratedType(definitionFile = "src/com/lynxspa/coac/historics/livetime/businessprocess/liveTimeEventsHistorified.fpmprocess")
public class LiveTimeEventsHistorifiedProcess extends BusinessProcess {
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

	public LiveTimeEventsHistorifiedProcess(String id) {
		super(id);
	}

	public void init() throws Exception {
		Properties initParams = ConfigParams.getInstance().getProperties();

		// instantiate and configure inputs
		// instantiate and configure 'input' input
		input = addInput("input");

		// instantiate and configure outputs

		// instantiate and configure nodes
		// $-- /business process/LivetimeEventsHistorifiedNode
		// instantiate and configure 'LivetimeEventsHistorifiedNode' node
		com.lynxspa.coac.historics.livetime.nodes.LivetimeEventsHistorifiedNode<com.lynxit.fpm.events.scheduler.ScheduledEvent> livetimeEventsHistorifiedNodeNode = Interceptors
				.createNode(com.lynxspa.coac.historics.livetime.nodes.LivetimeEventsHistorifiedNode.class);
		livetimeEventsHistorifiedNodeNode
				.setId("LivetimeEventsHistorifiedNode");
		addNode(livetimeEventsHistorifiedNodeNode);
		// $-- /business process/LivetimeEventsHistorifiedNode/
		livetimeEventsHistorifiedNodeNode
				.setResourceStateless(getStatelessSessionFactory());
		// $-- /business process/LivetimeEventsHistorifiedNode/
		livetimeEventsHistorifiedNodeNode.setLocale(getLocale());
		// $-- /business process/LivetimeEventsHistorifiedNode/
		livetimeEventsHistorifiedNodeNode.setResource(getSessionFactory());
		// $-- /business process/LivetimeEventsHistorifiedNode/
		// $-- /business process/LivetimeEventsHistorifiedNode/
		livetimeEventsHistorifiedNodeNode.setUser(getUser());

		// instantiate and configure subprocesses

		// link nodes
		livetimeEventsHistorifiedNodeNode
				.connectNodeToException(getDefaultExceptionHandlerNode());

		// link subprocesses

		// link inputs
		// $-- /business process/input/business process/LivetimeEventsHistorifiedNode/in
		input.connectNodeToOut(livetimeEventsHistorifiedNodeNode);

		livetimeEventsHistorifiedNodeNode.init();
	}

}