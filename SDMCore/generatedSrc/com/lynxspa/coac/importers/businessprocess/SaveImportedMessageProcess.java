package com.lynxspa.coac.importers.businessprocess;

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
@GeneratedType(definitionFile = "src/com/lynxspa/coac/importers/businessprocess/SaveImportedMessage.fpmprocess")
public class SaveImportedMessageProcess extends BusinessProcess {
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

	// input 'ImportedMessage'
	// $-- /business process/ImportedMessage
	private com.lynxit.fpm.BusinessProcessInput<com.lynxspa.coac.importers.EventMessageImportBean> importedMessage;

	public com.lynxit.fpm.BusinessProcessInput<com.lynxspa.coac.importers.EventMessageImportBean> asImportedMessageInput() {
		return importedMessage;
	}

	/*
	 * outputs declarations
	 */

	// output 'importedMessage'
	// $-- /business process/importedMessage
	private com.lynxit.fpm.BusinessProcessOutput<com.lynxspa.coac.importers.EventMessageImportBean> importedMessage1;

	public com.lynxit.fpm.BusinessProcessOutput<com.lynxspa.coac.importers.EventMessageImportBean> asImportedMessageOutput() {
		return importedMessage1;
	}

	public SaveImportedMessageProcess(String id) {
		super(id);
	}

	public void init() throws Exception {
		Properties initParams = ConfigParams.getInstance().getProperties();

		// instantiate and configure inputs
		// instantiate and configure 'ImportedMessage' input
		importedMessage = addInput("ImportedMessage");

		// instantiate and configure outputs
		// instantiate and configure 'importedMessage' output
		importedMessage1 = addOutput("importedMessage");

		// instantiate and configure nodes
		// $-- /business process/ForeachField
		// instantiate and configure 'ForeachField' node
		com.lynxit.fpm.nodes.internal.CollectionIteratorNode<java.lang.Object> foreachFieldNode = Interceptors
				.createNode(com.lynxit.fpm.nodes.internal.CollectionIteratorNode.class);
		foreachFieldNode.setId("ForeachField");
		addNode(foreachFieldNode);
		// $-- /business process/ForeachField/

		// $-- /business process/ForkNode
		// instantiate and configure 'ForkNode' node
		com.lynxit.fpm.nodes.internal.ForkNode<com.lynxspa.coac.importers.EventMessageImportBean> forkNodeNode = Interceptors
				.createNode(com.lynxit.fpm.nodes.internal.ForkNode.class);
		forkNodeNode.setId("ForkNode");
		addNode(forkNodeNode);
		// $-- /business process/ForkNode/
		// $-- /business process/ForkNode/
		forkNodeNode.setCloneMessage(false);

		// $-- /business process/ForkNode1
		// instantiate and configure 'ForkNode1' node
		com.lynxit.fpm.nodes.internal.ForkNode<com.lynxspa.coac.importers.EventMessageImportBean> forkNode1Node = Interceptors
				.createNode(com.lynxit.fpm.nodes.internal.ForkNode.class);
		forkNode1Node.setId("ForkNode1");
		addNode(forkNode1Node);
		// $-- /business process/ForkNode1/
		// $-- /business process/ForkNode1/
		forkNode1Node.setCloneMessage(false);

		// $-- /business process/GetMessage
		// instantiate and configure 'GetMessage' node
		com.lynxspa.coac.importers.mappings.GetEventMessageFromImportMap getMessageNode = Interceptors
				.createNode(com.lynxspa.coac.importers.mappings.GetEventMessageFromImportMap.class);
		getMessageNode.setId("GetMessage");
		addNode(getMessageNode);
		// $-- /business process/GetMessage/

		// $-- /business process/GetMessageFields
		// instantiate and configure 'GetMessageFields' node
		com.lynxspa.coac.importers.mappings.GetEventMessageFieldsFromImportMap getMessageFieldsNode = Interceptors
				.createNode(com.lynxspa.coac.importers.mappings.GetEventMessageFieldsFromImportMap.class);
		getMessageFieldsNode.setId("GetMessageFields");
		addNode(getMessageFieldsNode);
		// $-- /business process/GetMessageFields/

		// $-- /business process/HibernateStandardSave
		// instantiate and configure 'HibernateStandardSave' node
		com.lynxspa.fpm.nodes.hibernate.HibernateStandardSave hibernateStandardSaveNode = Interceptors
				.createNode(com.lynxspa.fpm.nodes.hibernate.HibernateStandardSave.class);
		hibernateStandardSaveNode.setId("HibernateStandardSave");
		addNode(hibernateStandardSaveNode);
		// $-- /business process/HibernateStandardSave/
		hibernateStandardSaveNode.setDataSource(getSessionFactory());
		// $-- /business process/HibernateStandardSave/
		hibernateStandardSaveNode.setFlush(false);
		// $-- /business process/HibernateStandardSave/
		// $-- /business process/HibernateStandardSave/
		hibernateStandardSaveNode.setUser(getUser());

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
		((DynaParamsNode) logCoacExceptionNode)
				.setDynamicParamValues(
						"arguments",
						com.lynxit.utils.scripting.Language.BEAN_SHELL
								.createExpression(
										"message.getMessage().getMessageType().getId().getFormat().getId()",
										java.lang.Object.class),
						com.lynxit.utils.scripting.Language.BEAN_SHELL
								.createExpression(
										"message.getMessage().getOriginName()",
										java.lang.Object.class),
						com.lynxit.utils.scripting.Language.BEAN_SHELL
								.createExpression(
										"message.getMessage().getOriginPosition()",
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
				.setLog(com.lynxspa.sdm.dictionaries.logs.LogErrorDict.IMPORT_SAVE_FAIL);

		// $-- /business process/PatchedCollectionIteratorNode
		// instantiate and configure 'PatchedCollectionIteratorNode' node
		com.lynxspa.fpm.nodes.patch.PatchedCollectionIteratorNode<java.lang.Object> patchedCollectionIteratorNodeNode = Interceptors
				.createNode(com.lynxspa.fpm.nodes.patch.PatchedCollectionIteratorNode.class);
		patchedCollectionIteratorNodeNode
				.setId("PatchedCollectionIteratorNode");
		addNode(patchedCollectionIteratorNodeNode);
		// $-- /business process/PatchedCollectionIteratorNode/

		// instantiate and configure subprocesses

		// link nodes
		foreachFieldNode.connectNodeToOut(getDefaultStopperNode());
		foreachFieldNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/ForkNode/two/business process/GetMessageFields/in
		forkNodeNode.connectNodeToTwo(getMessageFieldsNode);
		// $-- /business process/ForkNode/one/business process/GetMessage/in
		forkNodeNode.connectNodeToOne(getMessageNode);
		forkNodeNode.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/ForkNode1/two/business process/importedMessage
		forkNode1Node.connectNodeToTwo(importedMessage1);
		// $-- /business process/ForkNode1/one/business process/ForkNode/in
		forkNode1Node.connectNodeToOne(forkNodeNode);
		// $-- /business process/ForkNode1/exception/business process/LogCoacException/exc
		forkNode1Node.connectNodeToException(logCoacExceptionNode);
		// $-- /business process/GetMessage/out/business process/HibernateStandardSave/in
		getMessageNode.connectNodeToOut(hibernateStandardSaveNode);
		getMessageNode.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/GetMessageFields/out/business process/PatchedCollectionIteratorNode/in
		getMessageFieldsNode
				.connectNodeToOut(patchedCollectionIteratorNodeNode);
		getMessageFieldsNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		hibernateStandardSaveNode.connectNodeToOut(getDefaultStopperNode());
		hibernateStandardSaveNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/PatchedCollectionIteratorNode/out/business process/HibernateStandardSave/in
		patchedCollectionIteratorNodeNode
				.connectNodeToOut(hibernateStandardSaveNode);
		patchedCollectionIteratorNodeNode
				.connectNodeToException(getDefaultExceptionHandlerNode());

		// link subprocesses

		// link inputs
		// $-- /business process/ImportedMessage/business process/ForkNode1/in
		importedMessage.connectNodeToOut(forkNode1Node);

		foreachFieldNode.init();
		forkNodeNode.init();
		forkNode1Node.init();
		getMessageNode.init();
		getMessageFieldsNode.init();
		hibernateStandardSaveNode.init();
		logCoacExceptionNode.init();
		patchedCollectionIteratorNodeNode.init();
	}

}