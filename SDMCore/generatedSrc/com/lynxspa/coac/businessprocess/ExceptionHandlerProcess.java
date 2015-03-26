package com.lynxspa.coac.businessprocess;

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
@GeneratedType(definitionFile = "src/com/lynxspa/coac/businessprocess/ExceptionHandler.fpmprocess")
public class ExceptionHandlerProcess extends BusinessProcess {
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

	private String user_;

	@ConfigParam(description = "param1", required = true, dynamic = true, name = "user")
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
	private com.lynxit.fpm.BusinessProcessInput<com.lynxit.fpm.events.processevents.BusinessProcessExceptionEvent> input;

	public com.lynxit.fpm.BusinessProcessInput<com.lynxit.fpm.events.processevents.BusinessProcessExceptionEvent> asInputInput() {
		return input;
	}

	/*
	 * outputs declarations
	 */

	public ExceptionHandlerProcess(String id) {
		super(id);
	}

	public void init() throws Exception {
		Properties initParams = ConfigParams.getInstance().getProperties();

		// instantiate and configure inputs
		// instantiate and configure 'input' input
		input = addInput("input");

		// instantiate and configure outputs

		// instantiate and configure nodes
		// $-- /business process/LogCoacErrorHandler
		// instantiate and configure 'LogCoacErrorHandler' node
		com.lynxspa.coac.nodes.logs.LogCoacErrorHandler logCoacErrorHandlerNode = Interceptors
				.createNode(com.lynxspa.coac.nodes.logs.LogCoacErrorHandler.class);
		logCoacErrorHandlerNode.setId("LogCoacErrorHandler");
		addNode(logCoacErrorHandlerNode);
		// $-- /business process/LogCoacErrorHandler/
		logCoacErrorHandlerNode.setDataSource(getSessionFactory());
		// $-- /business process/LogCoacErrorHandler/
		logCoacErrorHandlerNode.setLocale(getLocale());
		// $-- /business process/LogCoacErrorHandler/
		// $-- /business process/LogCoacErrorHandler/
		logCoacErrorHandlerNode.setUser(getUser());

		// instantiate and configure subprocesses

		// link nodes
		logCoacErrorHandlerNode
				.connectNodeToException(getDefaultExceptionHandlerNode());

		// link subprocesses

		// link inputs
		// $-- /business process/input/business process/LogCoacErrorHandler/in
		input.connectNodeToOut(logCoacErrorHandlerNode);

		logCoacErrorHandlerNode.init();
	}

}