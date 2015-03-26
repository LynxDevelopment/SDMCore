package com.lynxspa.coac.plannings.ftpprocess.businessprocess;

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
@GeneratedType(definitionFile = "src/com/lynxspa/coac/plannings/ftpprocess/businessprocess/ftpGet.fpmprocess")
public class FtpGetProcess extends BusinessProcess {
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

	public FtpGetProcess(String id) {
		super(id);
	}

	public void init() throws Exception {
		Properties initParams = ConfigParams.getInstance().getProperties();

		// instantiate and configure inputs
		// instantiate and configure 'input' input
		input = addInput("input");

		// instantiate and configure outputs

		// instantiate and configure nodes
		// $-- /business process/FTP GET
		// instantiate and configure 'FTP GET' node
		com.lynxspa.coac.plannings.ftpprocess.nodes.FTPResourceOutputNode fTPGETNode = Interceptors
				.createNode(com.lynxspa.coac.plannings.ftpprocess.nodes.FTPResourceOutputNode.class);
		fTPGETNode.setId("FTP GET");
		addNode(fTPGETNode);
		// $-- /business process/FTP GET/
		((DynaParamsNode) fTPGETNode).setDynamicParam("pathFileToTransfer",
				com.lynxit.utils.scripting.Language.BEAN_SHELL
						.createExpression("message.getOutputFile()",
								java.lang.String.class));
		// $-- /business process/FTP GET/
		((DynaParamsNode) fTPGETNode).setDynamicParam("ftpFile",
				com.lynxit.utils.scripting.Language.BEAN_SHELL
						.createExpression("message.getFtpFile()",
								java.lang.String.class));
		// $-- /business process/FTP GET/
		((DynaParamsNode) fTPGETNode).setDynamicParam("ftpProxyPassword",
				com.lynxit.utils.scripting.Language.BEAN_SHELL
						.createExpression("message.getFtpProxyPassword()",
								java.lang.String.class));
		// $-- /business process/FTP GET/
		((DynaParamsNode) fTPGETNode).setDynamicParam("ftpProxy",
				com.lynxit.utils.scripting.Language.BEAN_SHELL
						.createExpression("message.getFtpProxy()",
								java.lang.String.class));
		// $-- /business process/FTP GET/
		((DynaParamsNode) fTPGETNode).setDynamicParam("binary",
				com.lynxit.utils.scripting.Language.BEAN_SHELL
						.createExpression("message.isFTPBinary()",
								boolean.class));
		// $-- /business process/FTP GET/
		fTPGETNode.setLocale(getLocale());
		// $-- /business process/FTP GET/
		fTPGETNode.setResource(getStatefullSession());
		// $-- /business process/FTP GET/
		((DynaParamsNode) fTPGETNode).setDynamicParam("serverName",
				com.lynxit.utils.scripting.Language.BEAN_SHELL
						.createExpression("message.getFtpServer()",
								java.lang.String.class));
		// $-- /business process/FTP GET/
		((DynaParamsNode) fTPGETNode).setDynamicParam("password",
				com.lynxit.utils.scripting.Language.BEAN_SHELL
						.createExpression("message.getFtpPassword()",
								java.lang.String.class));
		// $-- /business process/FTP GET/
		((DynaParamsNode) fTPGETNode).setDynamicParam("ftpProxyUser",
				com.lynxit.utils.scripting.Language.BEAN_SHELL
						.createExpression("message.getFtpProxyUser()",
								java.lang.String.class));
		// $-- /business process/FTP GET/
		fTPGETNode
				.setFtpAction(com.lynxspa.coac.plannings.dictionaries.FTPActionsDict.GET);
		// $-- /business process/FTP GET/
		((DynaParamsNode) fTPGETNode).setDynamicParam("passiveMode",
				com.lynxit.utils.scripting.Language.BEAN_SHELL
						.createExpression("message.isFTPPassive()",
								boolean.class));
		// $-- /business process/FTP GET/
		((DynaParamsNode) fTPGETNode).setDynamicParam("directory",
				com.lynxit.utils.scripting.Language.BEAN_SHELL
						.createExpression("message.getFtpPath()",
								java.lang.String.class));
		// $-- /business process/FTP GET/
		((DynaParamsNode) fTPGETNode).setDynamicParam("userId",
				com.lynxit.utils.scripting.Language.BEAN_SHELL
						.createExpression("message.getFtpUser()",
								java.lang.String.class));
		// $-- /business process/FTP GET/
		((DynaParamsNode) fTPGETNode).setDynamicParam("overwrite",
				com.lynxit.utils.scripting.Language.BEAN_SHELL
						.createExpression("message.isOverWrite()",
								boolean.class));
		// $-- /business process/FTP GET/
		((DynaParamsNode) fTPGETNode).setDynamicParam("fileName",
				com.lynxit.utils.scripting.Language.BEAN_SHELL
						.createExpression("message.getOutputFileName()",
								java.lang.String.class));
		// $-- /business process/FTP GET/
		((DynaParamsNode) fTPGETNode).setDynamicParam("ftpPort",
				com.lynxit.utils.scripting.Language.BEAN_SHELL
						.createExpression("message.getFtpPort()",
								java.lang.String.class));
		// $-- /business process/FTP GET/
		// $-- /business process/FTP GET/
		fTPGETNode.setUser(getUser());
		// $-- /business process/FTP GET/
		fTPGETNode.setTimeout(60000);
		// $-- /business process/FTP GET/
		fTPGETNode.setDeleteFile(false);

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
		fTPGETNode.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/HibernateStandardRead/out/business process/PatchedIteratorNode/in
		hibernateStandardReadNode.connectNodeToOut(patchedIteratorNodeNode);
		hibernateStandardReadNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		iteratorNodeNode.connectNodeToOut(getDefaultStopperNode());
		iteratorNodeNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/MapPersecuritiesMap/out/business process/FTP GET/in
		mapPersecuritiesMapNode.connectNodeToOut(fTPGETNode);
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

		fTPGETNode.init();
		hibernateStandardReadNode.init();
		iteratorNodeNode.init();
		mapPersecuritiesMapNode.init();
		patchedIteratorNodeNode.init();
	}

}