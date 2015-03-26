package com.lynxspa.coac.plannings.generatefileprocess.businessprocess;

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
@GeneratedType(definitionFile = "src/com/lynxspa/coac/plannings/generatefileprocess/businessprocess/generateFile.fpmprocess")
public class GenerateFileProcess extends BusinessProcess {
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

	private java.io.File tempPathFiles_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "tempPathFiles")
	public java.io.File getTempPathFiles() {
		return tempPathFiles_;
	}

	public void setTempPathFiles(java.io.File value) {
		this.tempPathFiles_ = value;
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

	public GenerateFileProcess(String id) {
		super(id);
	}

	public void init() throws Exception {
		Properties initParams = ConfigParams.getInstance().getProperties();

		// instantiate and configure inputs
		// instantiate and configure 'input' input
		input = addInput("input");

		// instantiate and configure outputs

		// instantiate and configure nodes
		// $-- /business process/DeleteTempFiles1
		// instantiate and configure 'DeleteTempFiles1' node
		com.lynxspa.coac.plannings.generatefileprocess.nodes.DeleteTempFiles<com.lynxspa.coac.plannings.beans.PlanningBean> deleteTempFiles1Node = Interceptors
				.createNode(com.lynxspa.coac.plannings.generatefileprocess.nodes.DeleteTempFiles.class);
		deleteTempFiles1Node.setId("DeleteTempFiles1");
		addNode(deleteTempFiles1Node);
		// $-- /business process/DeleteTempFiles1/
		deleteTempFiles1Node.setLocale(getLocale());
		// $-- /business process/DeleteTempFiles1/
		// $-- /business process/DeleteTempFiles1/
		((DynaParamsNode) deleteTempFiles1Node).setDynamicParam("fileToDelete",
				com.lynxit.utils.scripting.Language.BEAN_SHELL
						.createExpression("message.getPathTemplate()",
								java.lang.String.class));
		// $-- /business process/DeleteTempFiles1/
		deleteTempFiles1Node.setUser(getUser());

		// $-- /business process/FindSecurityEventNode1
		// instantiate and configure 'FindSecurityEventNode1' node
		com.lynxspa.coac.plannings.generatefileprocess.nodes.FindSecurityEventNode<com.lynxspa.coac.plannings.beans.PlanningBean> findSecurityEventNode1Node = Interceptors
				.createNode(com.lynxspa.coac.plannings.generatefileprocess.nodes.FindSecurityEventNode.class);
		findSecurityEventNode1Node.setId("FindSecurityEventNode1");
		addNode(findSecurityEventNode1Node);
		// $-- /business process/FindSecurityEventNode1/
		findSecurityEventNode1Node.setLocale(getLocale());
		// $-- /business process/FindSecurityEventNode1/
		findSecurityEventNode1Node.setResource(getStatelessSession());
		// $-- /business process/FindSecurityEventNode1/
		// $-- /business process/FindSecurityEventNode1/
		findSecurityEventNode1Node.setResourceFull(getStatefullSession());
		// $-- /business process/FindSecurityEventNode1/
		findSecurityEventNode1Node.setUser(getUser());

		// $-- /business process/GenerateFileFromTemplate1
		// instantiate and configure 'GenerateFileFromTemplate1' node
		com.lynxspa.coac.plannings.generatefileprocess.nodes.GenerateFileFromTemplate<com.lynxspa.coac.plannings.beans.PlanningBean> generateFileFromTemplate1Node = Interceptors
				.createNode(com.lynxspa.coac.plannings.generatefileprocess.nodes.GenerateFileFromTemplate.class);
		generateFileFromTemplate1Node.setId("GenerateFileFromTemplate1");
		addNode(generateFileFromTemplate1Node);
		// $-- /business process/GenerateFileFromTemplate1/
		((DynaParamsNode) generateFileFromTemplate1Node).setDynamicParam(
				"template", com.lynxit.utils.scripting.Language.BEAN_SHELL
						.createExpression("message.getPathTemplate()",
								java.lang.String.class));
		// $-- /business process/GenerateFileFromTemplate1/
		((DynaParamsNode) generateFileFromTemplate1Node).setDynamicParam(
				"outputPathFile",
				com.lynxit.utils.scripting.Language.BEAN_SHELL
						.createExpression("message.getOutputFile()",
								java.lang.String.class));
		// $-- /business process/GenerateFileFromTemplate1/
		// $-- /business process/GenerateFileFromTemplate1/
		// $-- /business process/GenerateFileFromTemplate1/
		((DynaParamsNode) generateFileFromTemplate1Node).setDynamicParam(
				"overwrite", com.lynxit.utils.scripting.Language.BEAN_SHELL
						.createExpression("message.isOverWrite()",
								boolean.class));
		// $-- /business process/GenerateFileFromTemplate1/
		generateFileFromTemplate1Node.setLocale(getLocale());
		// $-- /business process/GenerateFileFromTemplate1/
		generateFileFromTemplate1Node.setResource(getStatefullSession());
		// $-- /business process/GenerateFileFromTemplate1/
		// $-- /business process/GenerateFileFromTemplate1/
		generateFileFromTemplate1Node.setUser(getUser());

		// $-- /business process/GenerateTemplateFile1
		// instantiate and configure 'GenerateTemplateFile1' node
		com.lynxspa.coac.plannings.generatefileprocess.nodes.GenerateTemplateFile<com.lynxspa.coac.plannings.beans.PlanningBean> generateTemplateFile1Node = Interceptors
				.createNode(com.lynxspa.coac.plannings.generatefileprocess.nodes.GenerateTemplateFile.class);
		generateTemplateFile1Node.setId("GenerateTemplateFile1");
		addNode(generateTemplateFile1Node);
		// $-- /business process/GenerateTemplateFile1/
		generateTemplateFile1Node.setTemplatePathFile(getTempPathFiles());
		// $-- /business process/GenerateTemplateFile1/
		generateTemplateFile1Node.setTemplateExtension("ftl");
		// $-- /business process/GenerateTemplateFile1/
		generateTemplateFile1Node.setLocale(getLocale());
		// $-- /business process/GenerateTemplateFile1/
		generateTemplateFile1Node.setResource(getStatefullSession());
		// $-- /business process/GenerateTemplateFile1/
		// $-- /business process/GenerateTemplateFile1/
		generateTemplateFile1Node.setUser(getUser());

		// $-- /business process/HibernateStandardRead
		// instantiate and configure 'HibernateStandardRead' node
		com.lynxspa.fpm.nodes.hibernate.HibernateStandardRead<java.lang.Object, com.lynxspa.entities.plannings.SPPlanningProcess> hibernateStandardReadNode = Interceptors
				.createNode(com.lynxspa.fpm.nodes.hibernate.HibernateStandardRead.class);
		hibernateStandardReadNode.setId("HibernateStandardRead");
		addNode(hibernateStandardReadNode);
		// $-- /business process/HibernateStandardRead/
		hibernateStandardReadNode.setReadOnly(false);
		// $-- /business process/HibernateStandardRead/
		hibernateStandardReadNode
				.setQuery("from SPPlanningProcess where id=:par0 and auditor.deleted=:par1");
		// $-- /business process/HibernateStandardRead/
		// $-- /business process/HibernateStandardRead/
		hibernateStandardReadNode.setResource(getStatefullSession());
		// $-- /business process/HibernateStandardRead/
		hibernateStandardReadNode
				.setEntityClass(com.lynxspa.entities.plannings.SPPlanningProcess.class);
		// $-- /business process/HibernateStandardRead/
		hibernateStandardReadNode.setNumResults(10);
		// $-- /business process/HibernateStandardRead/
		((DynaParamsNode) hibernateStandardReadNode).setDynamicParamValues(
				"parameters", com.lynxit.utils.scripting.Language.BEAN_SHELL
						.createExpression("message.getId()",
								java.lang.Object.class),
				com.lynxit.utils.scripting.ELHelper
						.buildConstantExpression(false));
		// $-- /business process/HibernateStandardRead/
		// $-- /business process/HibernateStandardRead/
		hibernateStandardReadNode
				.setReadMode(com.lynxspa.fpm.nodes.hibernate.HibernateStandardRead.HibernateReadModes.CURSOR);

		// $-- /business process/IteratorNode
		// instantiate and configure 'IteratorNode' node
		com.lynxit.fpm.nodes.internal.IteratorNode<com.lynxspa.entities.plannings.SPPlanningProcess> iteratorNodeNode = Interceptors
				.createNode(com.lynxit.fpm.nodes.internal.IteratorNode.class);
		iteratorNodeNode.setId("IteratorNode");
		addNode(iteratorNodeNode);
		// $-- /business process/IteratorNode/

		// $-- /business process/MapPersecuritiesMap
		// instantiate and configure 'MapPersecuritiesMap' node
		com.lynxspa.coac.plannings.maps.MapPlanningsMap mapPersecuritiesMapNode = Interceptors
				.createNode(com.lynxspa.coac.plannings.maps.MapPlanningsMap.class);
		mapPersecuritiesMapNode.setId("MapPersecuritiesMap");
		addNode(mapPersecuritiesMapNode);
		// $-- /business process/MapPersecuritiesMap/

		// $-- /business process/PatchedIteratorNode
		// instantiate and configure 'PatchedIteratorNode' node
		com.lynxspa.fpm.nodes.patch.PatchedIteratorNode<com.lynxspa.entities.plannings.SPPlanningProcess> patchedIteratorNodeNode = Interceptors
				.createNode(com.lynxspa.fpm.nodes.patch.PatchedIteratorNode.class);
		patchedIteratorNodeNode.setId("PatchedIteratorNode");
		addNode(patchedIteratorNodeNode);
		// $-- /business process/PatchedIteratorNode/

		// instantiate and configure subprocesses

		// link nodes
		deleteTempFiles1Node.connectNodeToOut(getDefaultStopperNode());
		deleteTempFiles1Node
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/FindSecurityEventNode1/out/business process/GenerateTemplateFile1/in
		findSecurityEventNode1Node.connectNodeToOut(generateTemplateFile1Node);
		findSecurityEventNode1Node
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/GenerateFileFromTemplate1/out/business process/DeleteTempFiles1/in
		generateFileFromTemplate1Node.connectNodeToOut(deleteTempFiles1Node);
		generateFileFromTemplate1Node
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/GenerateTemplateFile1/out/business process/GenerateFileFromTemplate1/in
		generateTemplateFile1Node
				.connectNodeToOut(generateFileFromTemplate1Node);
		generateTemplateFile1Node
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/HibernateStandardRead/out/business process/PatchedIteratorNode/in
		hibernateStandardReadNode.connectNodeToOut(patchedIteratorNodeNode);
		hibernateStandardReadNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		iteratorNodeNode.connectNodeToOut(getDefaultStopperNode());
		iteratorNodeNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/MapPersecuritiesMap/out/business process/FindSecurityEventNode1/in
		mapPersecuritiesMapNode.connectNodeToOut(findSecurityEventNode1Node);
		mapPersecuritiesMapNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/PatchedIteratorNode/out/business process/MapPersecuritiesMap/in
		patchedIteratorNodeNode.connectNodeToOut(mapPersecuritiesMapNode);
		patchedIteratorNodeNode
				.connectNodeToException(getDefaultExceptionHandlerNode());

		// link subprocesses

		// link inputs
		// $-- /business process/input/business process/HibernateStandardRead/in
		input.connectNodeToOut(hibernateStandardReadNode);

		deleteTempFiles1Node.init();
		findSecurityEventNode1Node.init();
		generateFileFromTemplate1Node.init();
		generateTemplateFile1Node.init();
		hibernateStandardReadNode.init();
		iteratorNodeNode.init();
		mapPersecuritiesMapNode.init();
		patchedIteratorNodeNode.init();
	}

}