package com.lynxspa.sdm.normalizer.businessprocess;

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
@GeneratedType(definitionFile = "src/com/lynxspa/sdm/normalizer/businessprocess/SDMNormalizerProcess.fpmprocess")
public class SDMNormalizerProcessProcess extends BusinessProcess {
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

	private com.lynxit.fpm.resources.Resource session_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "session")
	public com.lynxit.fpm.resources.Resource getSession() {
		return session_;
	}

	public void setSession(com.lynxit.fpm.resources.Resource value) {
		this.session_ = value;
	}

	private com.lynxit.fpm.resources.Resource sessionStateless_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "sessionStateless")
	public com.lynxit.fpm.resources.Resource getSessionStateless() {
		return sessionStateless_;
	}

	public void setSessionStateless(com.lynxit.fpm.resources.Resource value) {
		this.sessionStateless_ = value;
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

	// input 'NormalizerEvent'
	// $-- /business process/NormalizerEvent
	private com.lynxit.fpm.BusinessProcessInput<com.lynxit.fpm.events.scheduler.ScheduledEvent> normalizerEvent;

	public com.lynxit.fpm.BusinessProcessInput<com.lynxit.fpm.events.scheduler.ScheduledEvent> asNormalizerEventInput() {
		return normalizerEvent;
	}

	/*
	 * outputs declarations
	 */

	public SDMNormalizerProcessProcess(String id) {
		super(id);
	}

	public void init() throws Exception {
		Properties initParams = ConfigParams.getInstance().getProperties();

		// instantiate and configure inputs
		// instantiate and configure 'NormalizerEvent' input
		normalizerEvent = addInput("NormalizerEvent");

		// instantiate and configure outputs

		// instantiate and configure nodes
		// $-- /business process/SDMNormalizer
		// instantiate and configure 'SDMNormalizer' node
		com.lynxspa.sdm.normalizer.nodes.SDMNormalizer<java.lang.Object> sDMNormalizerNode = Interceptors
				.createNode(com.lynxspa.sdm.normalizer.nodes.SDMNormalizer.class);
		sDMNormalizerNode.setId("SDMNormalizer");
		addNode(sDMNormalizerNode);
		// $-- /business process/SDMNormalizer/
		sDMNormalizerNode.setStatelessResource(getSessionStateless());
		// $-- /business process/SDMNormalizer/
		sDMNormalizerNode.setLocale(getLocale());
		// $-- /business process/SDMNormalizer/
		sDMNormalizerNode.setResource(getSession());
		// $-- /business process/SDMNormalizer/
		// $-- /business process/SDMNormalizer/
		sDMNormalizerNode.setUser(getUser());

		// instantiate and configure subprocesses

		// link nodes
		sDMNormalizerNode
				.connectNodeToException(getDefaultExceptionHandlerNode());

		// link subprocesses

		// link inputs
		// $-- /business process/NormalizerEvent/business process/SDMNormalizer/in
		normalizerEvent.connectNodeToOut(sDMNormalizerNode);

		sDMNormalizerNode.init();
	}

}