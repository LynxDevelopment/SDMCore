package com.lynxspa.coac.historics.logs.businessprocess;

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
@GeneratedType(definitionFile = "src/com/lynxspa/coac/historics/logs/businessprocess/deletingLogs.fpmprocess")
public class DeletingLogsProcess extends BusinessProcess {
	/*
	 * Parameters declarations
	 */
	private String locale_;

	@ConfigParam(description = "param1", required = false, dynamic = false, name = "locale")
	public String getLocale() {
		return locale_;
	}

	public void setLocale(String value) {
		this.locale_ = value;
	}

	private Integer logsCommitSize_;

	@ConfigParam(description = "param1", required = false, dynamic = false, name = "logsCommitSize")
	public Integer getLogsCommitSize() {
		return logsCommitSize_;
	}

	public void setLogsCommitSize(Integer value) {
		this.logsCommitSize_ = value;
	}

	private com.lynxit.fpm.resources.Resource sessionFactory_;

	@ConfigParam(description = "param1", required = false, dynamic = false, name = "sessionFactory")
	public com.lynxit.fpm.resources.Resource getSessionFactory() {
		return sessionFactory_;
	}

	public void setSessionFactory(com.lynxit.fpm.resources.Resource value) {
		this.sessionFactory_ = value;
	}

	private com.lynxit.fpm.resources.Resource statelessSessionFactory_;

	@ConfigParam(description = "param1", required = false, dynamic = false, name = "statelessSessionFactory")
	public com.lynxit.fpm.resources.Resource getStatelessSessionFactory() {
		return statelessSessionFactory_;
	}

	public void setStatelessSessionFactory(
			com.lynxit.fpm.resources.Resource value) {
		this.statelessSessionFactory_ = value;
	}

	private String user_;

	@ConfigParam(description = "param1", required = false, dynamic = false, name = "user")
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

	public DeletingLogsProcess(String id) {
		super(id);
	}

	public void init() throws Exception {
		Properties initParams = ConfigParams.getInstance().getProperties();

		// instantiate and configure inputs
		// instantiate and configure 'input' input
		input = addInput("input");

		// instantiate and configure outputs

		// instantiate and configure nodes
		// $-- /business process/HistorificationLogsNode
		// instantiate and configure 'HistorificationLogsNode' node
		com.lynxspa.coac.historics.logs.nodes.HistorificationLogsNode<com.lynxit.fpm.events.scheduler.ScheduledEvent> historificationLogsNodeNode = Interceptors
				.createNode(com.lynxspa.coac.historics.logs.nodes.HistorificationLogsNode.class);
		historificationLogsNodeNode.setId("HistorificationLogsNode");
		addNode(historificationLogsNodeNode);
		// $-- /business process/HistorificationLogsNode/
		historificationLogsNodeNode
				.setResourceStateless(getStatelessSessionFactory());
		// $-- /business process/HistorificationLogsNode/
		historificationLogsNodeNode.setLocale(getLocale());
		// $-- /business process/HistorificationLogsNode/
		historificationLogsNodeNode.setResource(getSessionFactory());
		// $-- /business process/HistorificationLogsNode/
		historificationLogsNodeNode
				.setHistorificationCommitSize(getLogsCommitSize());
		// $-- /business process/HistorificationLogsNode/
		// $-- /business process/HistorificationLogsNode/
		historificationLogsNodeNode.setUser(getUser());

		// instantiate and configure subprocesses

		// link nodes
		historificationLogsNodeNode
				.connectNodeToException(getDefaultExceptionHandlerNode());

		// link subprocesses

		// link inputs
		// $-- /business process/input/business process/HistorificationLogsNode/in
		input.connectNodeToOut(historificationLogsNodeNode);

		historificationLogsNodeNode.init();
	}

}