package com.lynxspa.coac.plannings.businessprocess;

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
@GeneratedType(definitionFile = "src/com/lynxspa/coac/plannings/businessprocess/updatePlannings.fpmprocess")
public class UpdatePlanningsProcess extends BusinessProcess {
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
	private com.lynxit.fpm.BusinessProcessInput<com.lynxspa.coac.plannings.beans.ControlPlanningsBean> input;

	public com.lynxit.fpm.BusinessProcessInput<com.lynxspa.coac.plannings.beans.ControlPlanningsBean> asInputInput() {
		return input;
	}

	/*
	 * outputs declarations
	 */

	public UpdatePlanningsProcess(String id) {
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
		commitNode.setDataSource(getStatelessSession());
		// $-- /business process/Commit/
		commitNode.setCommitEvery(1);
		// $-- /business process/Commit/

		// $-- /business process/UpdateExecutionDate1
		// instantiate and configure 'UpdateExecutionDate1' node
		com.lynxspa.coac.plannings.nodes.UpdateExecutionDate updateExecutionDate1Node = Interceptors
				.createNode(com.lynxspa.coac.plannings.nodes.UpdateExecutionDate.class);
		updateExecutionDate1Node.setId("UpdateExecutionDate1");
		addNode(updateExecutionDate1Node);
		// $-- /business process/UpdateExecutionDate1/
		updateExecutionDate1Node.setLocale(getLocale());
		// $-- /business process/UpdateExecutionDate1/
		updateExecutionDate1Node.setResource(getStatelessSession());
		// $-- /business process/UpdateExecutionDate1/
		// $-- /business process/UpdateExecutionDate1/
		updateExecutionDate1Node.setResourceFull(getStatefullSession());
		// $-- /business process/UpdateExecutionDate1/
		updateExecutionDate1Node.setUser(getUser());

		// instantiate and configure subprocesses

		// link nodes
		commitNode.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/UpdateExecutionDate1/out/business process/Commit/in
		updateExecutionDate1Node.connectNodeToOut(commitNode);
		updateExecutionDate1Node
				.connectNodeToException(getDefaultExceptionHandlerNode());

		// link subprocesses

		// link inputs
		// $-- /business process/input/business process/UpdateExecutionDate1/in
		input.connectNodeToOut(updateExecutionDate1Node);

		commitNode.init();
		updateExecutionDate1Node.init();
	}

}