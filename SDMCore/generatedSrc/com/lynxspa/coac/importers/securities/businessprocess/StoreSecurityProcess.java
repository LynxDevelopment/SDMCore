package com.lynxspa.coac.importers.securities.businessprocess;

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
@GeneratedType(definitionFile = "src/com/lynxspa/coac/importers/securities/businessprocess/StoreSecurity.fpmprocess")
public class StoreSecurityProcess extends BusinessProcess {
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

	private com.lynxspa.coac.importers.securities.SecuritySearchMode searchMode_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "searchMode")
	public com.lynxspa.coac.importers.securities.SecuritySearchMode getSearchMode() {
		return searchMode_;
	}

	public void setSearchMode(
			com.lynxspa.coac.importers.securities.SecuritySearchMode value) {
		this.searchMode_ = value;
	}

	private java.lang.Boolean securitiesUploadForceMarketInsert_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "securitiesUploadForceMarketInsert")
	public java.lang.Boolean getSecuritiesUploadForceMarketInsert() {
		return securitiesUploadForceMarketInsert_;
	}

	public void setSecuritiesUploadForceMarketInsert(java.lang.Boolean value) {
		this.securitiesUploadForceMarketInsert_ = value;
	}

	private com.lynxit.fpm.resources.Resource sessionFactory_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "sessionFactory")
	public com.lynxit.fpm.resources.Resource getSessionFactory() {
		return sessionFactory_;
	}

	public void setSessionFactory(com.lynxit.fpm.resources.Resource value) {
		this.sessionFactory_ = value;
	}

	private com.lynxit.fpm.resources.Resource stateFullSession_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "stateFullSession")
	public com.lynxit.fpm.resources.Resource getStateFullSession() {
		return stateFullSession_;
	}

	public void setStateFullSession(com.lynxit.fpm.resources.Resource value) {
		this.stateFullSession_ = value;
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

	// input 'importedBean'
	// $-- /business process/importedBean
	private com.lynxit.fpm.BusinessProcessInput<com.lynxspa.sdm.importers.securities.beans.SecurityBean> importedBean;

	public com.lynxit.fpm.BusinessProcessInput<com.lynxspa.sdm.importers.securities.beans.SecurityBean> asImportedBeanInput() {
		return importedBean;
	}

	/*
	 * outputs declarations
	 */

	public StoreSecurityProcess(String id) {
		super(id);
	}

	public void init() throws Exception {
		Properties initParams = ConfigParams.getInstance().getProperties();

		// instantiate and configure inputs
		// instantiate and configure 'importedBean' input
		importedBean = addInput("importedBean");

		// instantiate and configure outputs

		// instantiate and configure nodes
		// $-- /business process/LogCoacException
		// instantiate and configure 'LogCoacException' node
		com.lynxspa.coac.nodes.logs.LogCoacException logCoacExceptionNode = Interceptors
				.createNode(com.lynxspa.coac.nodes.logs.LogCoacException.class);
		logCoacExceptionNode.setId("LogCoacException");
		addNode(logCoacExceptionNode);
		// $-- /business process/LogCoacException/
		logCoacExceptionNode.setDataSource(getSessionFactory());
		// $-- /business process/LogCoacException/
		logCoacExceptionNode.setStopExceptionHandling(false);
		// $-- /business process/LogCoacException/
		((DynaParamsNode) logCoacExceptionNode).setDynamicParamValues(
				"arguments", com.lynxit.utils.scripting.Language.BEAN_SHELL
						.createExpression("message.getFormat().getCode()",
								java.lang.Object.class),
				com.lynxit.utils.scripting.Language.BEAN_SHELL
						.createExpression("message.getOriginName()",
								java.lang.Object.class),
				com.lynxit.utils.scripting.Language.BEAN_SHELL
						.createExpression("message.getOriginPosition()",
								java.lang.Object.class));
		// $-- /business process/LogCoacException/
		logCoacExceptionNode.setCommitInNewTransaction(true);
		// $-- /business process/LogCoacException/
		logCoacExceptionNode.setLocale(getLocale());
		// $-- /business process/LogCoacException/
		// $-- /business process/LogCoacException/
		logCoacExceptionNode.setUser(getUser());
		// $-- /business process/LogCoacException/
		logCoacExceptionNode
				.setLog(com.lynxspa.sdm.dictionaries.logs.LogErrorDict.SECURITY_FUNDS_IMPORT_SAVE_FAIL);

		// $-- /business process/action decision
		// instantiate and configure 'action decision' node
		com.lynxspa.coac.importers.securities.nodes.OperationDecisionSwitch actionDecisionNode = Interceptors
				.createNode(com.lynxspa.coac.importers.securities.nodes.OperationDecisionSwitch.class);
		actionDecisionNode.setId("action decision");
		addNode(actionDecisionNode);
		// $-- /business process/action decision/

		// $-- /business process/delete
		// instantiate and configure 'delete' node
		com.lynxspa.coac.importers.securities.nodes.SecuritiesDeleteNode deleteNode = Interceptors
				.createNode(com.lynxspa.coac.importers.securities.nodes.SecuritiesDeleteNode.class);
		deleteNode.setId("delete");
		addNode(deleteNode);
		// $-- /business process/delete/
		deleteNode.setStateFullResource(getStateFullSession());
		// $-- /business process/delete/
		deleteNode.setLocale(getLocale());
		// $-- /business process/delete/
		deleteNode.setResource(getSessionFactory());
		// $-- /business process/delete/
		// $-- /business process/delete/
		deleteNode.setUser(getUser());
		// $-- /business process/delete/
		deleteNode.setSearchMode(getSearchMode());

		// $-- /business process/save
		// instantiate and configure 'save' node
		com.lynxspa.coac.importers.securities.nodes.SecuritiesSaveNode saveNode = Interceptors
				.createNode(com.lynxspa.coac.importers.securities.nodes.SecuritiesSaveNode.class);
		saveNode.setId("save");
		addNode(saveNode);
		// $-- /business process/save/
		saveNode.setStateFullResource(getStateFullSession());
		// $-- /business process/save/
		saveNode.setLocale(getLocale());
		// $-- /business process/save/
		saveNode.setResource(getSessionFactory());
		// $-- /business process/save/
		saveNode.setAddableMarket(getSecuritiesUploadForceMarketInsert());
		// $-- /business process/save/
		// $-- /business process/save/
		saveNode.setUser(getUser());
		// $-- /business process/save/
		saveNode.setSearchMode(getSearchMode());

		// instantiate and configure subprocesses

		// link nodes
		// $-- /business process/action decision/insertOrUpdate/business process/save/in
		actionDecisionNode.connectNodeToInsertOrUpdate(saveNode);
		// $-- /business process/action decision/delete/business process/delete/in
		actionDecisionNode.connectNodeToDelete(deleteNode);
		// $-- /business process/action decision/exception/business process/LogCoacException/exc
		actionDecisionNode.connectNodeToException(logCoacExceptionNode);
		deleteNode.connectNodeToException(getDefaultExceptionHandlerNode());
		saveNode.connectNodeToException(getDefaultExceptionHandlerNode());

		// link subprocesses

		// link inputs
		// $-- /business process/importedBean/business process/action decision/in
		importedBean.connectNodeToOut(actionDecisionNode);

		logCoacExceptionNode.init();
		actionDecisionNode.init();
		deleteNode.init();
		saveNode.init();
	}

}