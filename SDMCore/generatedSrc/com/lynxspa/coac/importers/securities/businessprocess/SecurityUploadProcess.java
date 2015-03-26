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
@GeneratedType(definitionFile = "src/com/lynxspa/coac/importers/securities/businessprocess/SecurityUpload.fpmprocess")
public class SecurityUploadProcess extends BusinessProcess {
	/*
	 * Parameters declarations
	 */
	private Integer bloombergCommitSize_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "bloombergCommitSize")
	public Integer getBloombergCommitSize() {
		return bloombergCommitSize_;
	}

	public void setBloombergCommitSize(Integer value) {
		this.bloombergCommitSize_ = value;
	}

	private java.io.File bloombergDebtPath_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "bloombergDebtPath")
	public java.io.File getBloombergDebtPath() {
		return bloombergDebtPath_;
	}

	public void setBloombergDebtPath(java.io.File value) {
		this.bloombergDebtPath_ = value;
	}

	private java.io.File bloombergFundsPath_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "bloombergFundsPath")
	public java.io.File getBloombergFundsPath() {
		return bloombergFundsPath_;
	}

	public void setBloombergFundsPath(java.io.File value) {
		this.bloombergFundsPath_ = value;
	}

	private java.io.File bloombergPath_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "bloombergPath")
	public java.io.File getBloombergPath() {
		return bloombergPath_;
	}

	public void setBloombergPath(java.io.File value) {
		this.bloombergPath_ = value;
	}

	private java.io.File bloombergRVNoEuroPath_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "bloombergRVNoEuroPath")
	public java.io.File getBloombergRVNoEuroPath() {
		return bloombergRVNoEuroPath_;
	}

	public void setBloombergRVNoEuroPath(java.io.File value) {
		this.bloombergRVNoEuroPath_ = value;
	}

	private Integer customerCommitSize_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "customerCommitSize")
	public Integer getCustomerCommitSize() {
		return customerCommitSize_;
	}

	public void setCustomerCommitSize(Integer value) {
		this.customerCommitSize_ = value;
	}

	private java.io.File customerSecurityUploadPath_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "customerSecurityUploadPath")
	public java.io.File getCustomerSecurityUploadPath() {
		return customerSecurityUploadPath_;
	}

	public void setCustomerSecurityUploadPath(java.io.File value) {
		this.customerSecurityUploadPath_ = value;
	}

	private Integer fundsCommitSize_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "fundsCommitSize")
	public Integer getFundsCommitSize() {
		return fundsCommitSize_;
	}

	public void setFundsCommitSize(Integer value) {
		this.fundsCommitSize_ = value;
	}

	private java.io.File fundsPath_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "fundsPath")
	public java.io.File getFundsPath() {
		return fundsPath_;
	}

	public void setFundsPath(java.io.File value) {
		this.fundsPath_ = value;
	}

	private Integer inputTimeout_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "inputTimeout")
	public Integer getInputTimeout() {
		return inputTimeout_;
	}

	public void setInputTimeout(Integer value) {
		this.inputTimeout_ = value;
	}

	private String locale_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "locale")
	public String getLocale() {
		return locale_;
	}

	public void setLocale(String value) {
		this.locale_ = value;
	}

	private Integer ofivalCommitSize_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "ofivalCommitSize")
	public Integer getOfivalCommitSize() {
		return ofivalCommitSize_;
	}

	public void setOfivalCommitSize(Integer value) {
		this.ofivalCommitSize_ = value;
	}

	private java.io.File ofivalPath_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "ofivalPath")
	public java.io.File getOfivalPath() {
		return ofivalPath_;
	}

	public void setOfivalPath(java.io.File value) {
		this.ofivalPath_ = value;
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

	// input 'FileCreated'
	// $-- /business process/FileCreated
	private com.lynxit.fpm.BusinessProcessInput<com.lynxit.fpm.events.fileevents.FileEvent> fileCreated;

	public com.lynxit.fpm.BusinessProcessInput<com.lynxit.fpm.events.fileevents.FileEvent> asFileCreatedInput() {
		return fileCreated;
	}

	/*
	 * outputs declarations
	 */

	public SecurityUploadProcess(String id) {
		super(id);
	}

	public void init() throws Exception {
		Properties initParams = ConfigParams.getInstance().getProperties();

		// instantiate and configure inputs
		// instantiate and configure 'FileCreated' input
		fileCreated = addInput("FileCreated");

		// instantiate and configure outputs

		// instantiate and configure nodes
		// $-- /business process/ForkNode
		// instantiate and configure 'ForkNode' node
		com.lynxit.fpm.nodes.internal.ForkNode<com.lynxit.fpm.events.fileevents.FileEvent> forkNodeNode = Interceptors
				.createNode(com.lynxit.fpm.nodes.internal.ForkNode.class);
		forkNodeNode.setId("ForkNode");
		addNode(forkNodeNode);
		// $-- /business process/ForkNode/
		// $-- /business process/ForkNode/
		forkNodeNode.setCloneMessage(false);

		// $-- /business process/LiveCycle Validation
		// instantiate and configure 'LiveCycle Validation' node
		com.lynxspa.coac.nodes.LiveCycleValidatorNode<com.lynxit.fpm.events.fileevents.FileEvent> liveCycleValidationNode = Interceptors
				.createNode(com.lynxspa.coac.nodes.LiveCycleValidatorNode.class);
		liveCycleValidationNode.setId("LiveCycle Validation");
		addNode(liveCycleValidationNode);
		// $-- /business process/LiveCycle Validation/
		liveCycleValidationNode
				.setRequiredLiveCycle(com.lynxspa.sdm.dictionaries.CALiveCycle.PRENORMALIZE_IMPORT);
		// $-- /business process/LiveCycle Validation/
		liveCycleValidationNode.setResource(getSessionFactory());
		// $-- /business process/LiveCycle Validation/

		// $-- /business process/SecurityFormatValidatorNode
		// instantiate and configure 'SecurityFormatValidatorNode' node
		com.lynxspa.coac.importers.securities.nodes.SecurityFormatValidatorNode securityFormatValidatorNodeNode = Interceptors
				.createNode(com.lynxspa.coac.importers.securities.nodes.SecurityFormatValidatorNode.class);
		securityFormatValidatorNodeNode.setId("SecurityFormatValidatorNode");
		addNode(securityFormatValidatorNodeNode);
		// $-- /business process/SecurityFormatValidatorNode/
		securityFormatValidatorNodeNode.setOfivalFilePath(getOfivalPath());
		// $-- /business process/SecurityFormatValidatorNode/
		securityFormatValidatorNodeNode.setFundsFilePath(getFundsPath());
		// $-- /business process/SecurityFormatValidatorNode/
		securityFormatValidatorNodeNode
				.setBloombergFundsFilePath(getBloombergFundsPath());
		// $-- /business process/SecurityFormatValidatorNode/
		securityFormatValidatorNodeNode
				.setBloombergRentaFijaFilePath(getBloombergDebtPath());
		// $-- /business process/SecurityFormatValidatorNode/
		securityFormatValidatorNodeNode.setResource(getSessionFactory());
		// $-- /business process/SecurityFormatValidatorNode/
		securityFormatValidatorNodeNode
				.setBloombergFilePath(getBloombergPath());
		// $-- /business process/SecurityFormatValidatorNode/
		securityFormatValidatorNodeNode
				.setCustomerFilePath(getCustomerSecurityUploadPath());
		// $-- /business process/SecurityFormatValidatorNode/
		// $-- /business process/SecurityFormatValidatorNode/
		securityFormatValidatorNodeNode
				.setBloombergRVNoEuroFilePath(getBloombergRVNoEuroPath());

		// $-- /business process/VirtualSecurityReplacementNode
		// instantiate and configure 'VirtualSecurityReplacementNode' node
		com.lynxspa.coac.importers.securities.nodes.VirtualSecurityReplacementNode virtualSecurityReplacementNodeNode = Interceptors
				.createNode(com.lynxspa.coac.importers.securities.nodes.VirtualSecurityReplacementNode.class);
		virtualSecurityReplacementNodeNode
				.setId("VirtualSecurityReplacementNode");
		addNode(virtualSecurityReplacementNodeNode);
		// $-- /business process/VirtualSecurityReplacementNode/
		virtualSecurityReplacementNodeNode
				.setStateFullResource(getSessionFactory());
		// $-- /business process/VirtualSecurityReplacementNode/
		virtualSecurityReplacementNodeNode.setLocale(getLocale());
		// $-- /business process/VirtualSecurityReplacementNode/
		virtualSecurityReplacementNodeNode
				.setResource(getStatelessSessionFactory());
		// $-- /business process/VirtualSecurityReplacementNode/
		// $-- /business process/VirtualSecurityReplacementNode/
		virtualSecurityReplacementNodeNode.setUser(getUser());

		// instantiate and configure subprocesses
		// $-- /business process/BloombergSecuritiesUploadProcess
		// instantiate and configure 'BloombergSecuritiesUploadProcess' subprocess
		com.lynxspa.coac.importers.securities.bloomberg.businessprocess.BloombergSecuritiesUploadProcess bloombergSecuritiesUploadProcess = Interceptors
				.createSubProcess(
						com.lynxspa.coac.importers.securities.bloomberg.businessprocess.BloombergSecuritiesUploadProcess.class,
						"BloombergSecuritiesUploadProcess");
		addSubProcess(bloombergSecuritiesUploadProcess);
		// $-- /business process/BloombergSecuritiesUploadProcess/
		bloombergSecuritiesUploadProcess
				.setCommitSize(getBloombergCommitSize());
		// $-- /business process/BloombergSecuritiesUploadProcess/
		bloombergSecuritiesUploadProcess.setSessionFactory(getSessionFactory());
		// $-- /business process/BloombergSecuritiesUploadProcess/
		bloombergSecuritiesUploadProcess
				.setStatelessSessionFactory(getStatelessSessionFactory());
		// $-- /business process/BloombergSecuritiesUploadProcess/
		bloombergSecuritiesUploadProcess
				.setSecuritiesUploadForceMarketInsert(getSecuritiesUploadForceMarketInsert());
		// $-- /business process/BloombergSecuritiesUploadProcess/
		bloombergSecuritiesUploadProcess.setLocale(getLocale());
		// $-- /business process/BloombergSecuritiesUploadProcess/
		bloombergSecuritiesUploadProcess.setInputTimeout(getInputTimeout());
		// $-- /business process/BloombergSecuritiesUploadProcess/
		// $-- /business process/BloombergSecuritiesUploadProcess/
		bloombergSecuritiesUploadProcess.setUser("IMPORTBLOOMBERGSECURITIES");

		bloombergSecuritiesUploadProcess.initialize();
		// $-- /business process/CustomerSecuritiesUploadProcess
		// instantiate and configure 'CustomerSecuritiesUploadProcess' subprocess
		com.lynxspa.coac.importers.securities.customer.businessprocess.CustomerSecuritiesUploadProcess customerSecuritiesUploadProcess = Interceptors
				.createSubProcess(
						com.lynxspa.coac.importers.securities.customer.businessprocess.CustomerSecuritiesUploadProcess.class,
						"CustomerSecuritiesUploadProcess");
		addSubProcess(customerSecuritiesUploadProcess);
		// $-- /business process/CustomerSecuritiesUploadProcess/
		customerSecuritiesUploadProcess.setCommitSize(getCustomerCommitSize());
		// $-- /business process/CustomerSecuritiesUploadProcess/
		customerSecuritiesUploadProcess.setSessionFactory(getSessionFactory());
		// $-- /business process/CustomerSecuritiesUploadProcess/
		customerSecuritiesUploadProcess
				.setStatelessSessionFactory(getStatelessSessionFactory());
		// $-- /business process/CustomerSecuritiesUploadProcess/
		customerSecuritiesUploadProcess
				.setSecuritiesUploadForceMarketInsert(getSecuritiesUploadForceMarketInsert());
		// $-- /business process/CustomerSecuritiesUploadProcess/
		customerSecuritiesUploadProcess.setLocale(getLocale());
		// $-- /business process/CustomerSecuritiesUploadProcess/
		customerSecuritiesUploadProcess.setInputTimeout(getInputTimeout());
		// $-- /business process/CustomerSecuritiesUploadProcess/
		// $-- /business process/CustomerSecuritiesUploadProcess/
		customerSecuritiesUploadProcess.setUser("IMPORTCUSTOMERSECURITIES");

		customerSecuritiesUploadProcess.initialize();
		// $-- /business process/OfivalSecuritiesUploadProcess
		// instantiate and configure 'OfivalSecuritiesUploadProcess' subprocess
		com.lynxspa.coac.importers.securities.ofival.businessprocess.OfivalSecuritiesUploadProcess ofivalSecuritiesUploadProcess = Interceptors
				.createSubProcess(
						com.lynxspa.coac.importers.securities.ofival.businessprocess.OfivalSecuritiesUploadProcess.class,
						"OfivalSecuritiesUploadProcess");
		addSubProcess(ofivalSecuritiesUploadProcess);
		// $-- /business process/OfivalSecuritiesUploadProcess/
		ofivalSecuritiesUploadProcess.setCommitSize(getOfivalCommitSize());
		// $-- /business process/OfivalSecuritiesUploadProcess/
		ofivalSecuritiesUploadProcess.setSessionFactory(getSessionFactory());
		// $-- /business process/OfivalSecuritiesUploadProcess/
		ofivalSecuritiesUploadProcess
				.setStatelessSessionFactory(getStatelessSessionFactory());
		// $-- /business process/OfivalSecuritiesUploadProcess/
		ofivalSecuritiesUploadProcess
				.setSecuritiesUploadForceMarketInsert(getSecuritiesUploadForceMarketInsert());
		// $-- /business process/OfivalSecuritiesUploadProcess/
		ofivalSecuritiesUploadProcess.setLocale(getLocale());
		// $-- /business process/OfivalSecuritiesUploadProcess/
		ofivalSecuritiesUploadProcess.setInputTimeout(getInputTimeout());
		// $-- /business process/OfivalSecuritiesUploadProcess/
		// $-- /business process/OfivalSecuritiesUploadProcess/
		ofivalSecuritiesUploadProcess.setUser("IMPORTOFIVALSECURITIES");

		ofivalSecuritiesUploadProcess.initialize();

		// link nodes
		// $-- /business process/ForkNode/two/business process/VirtualSecurityReplacementNode/in
		forkNodeNode.connectNodeToTwo(virtualSecurityReplacementNodeNode);
		// $-- /business process/ForkNode/one/business process/SecurityFormatValidatorNode/in
		forkNodeNode.connectNodeToOne(securityFormatValidatorNodeNode);
		forkNodeNode.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/LiveCycle Validation/out/business process/ForkNode/in
		liveCycleValidationNode.connectNodeToOut(forkNodeNode);
		liveCycleValidationNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/SecurityFormatValidatorNode/bloomberg/business process/BloombergSecuritiesUploadProcess/securitiesBloombergFileCreated
		securityFormatValidatorNodeNode
				.connectNodeToBloomberg(bloombergSecuritiesUploadProcess
						.asSecuritiesBloombergFileCreatedInput());
		// $-- /business process/SecurityFormatValidatorNode/ofival/business process/OfivalSecuritiesUploadProcess/securitiesOfivalFileCreated
		securityFormatValidatorNodeNode
				.connectNodeToOfival(ofivalSecuritiesUploadProcess
						.asSecuritiesOfivalFileCreatedInput());
		// $-- /business process/SecurityFormatValidatorNode/customer/business process/CustomerSecuritiesUploadProcess/securitiesCustomerFileCreated
		securityFormatValidatorNodeNode
				.connectNodeToCustomer(customerSecuritiesUploadProcess
						.asSecuritiesCustomerFileCreatedInput());
		securityFormatValidatorNodeNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		virtualSecurityReplacementNodeNode
				.connectNodeToException(getDefaultExceptionHandlerNode());

		// link subprocesses
		bloombergSecuritiesUploadProcess.asExcOutput().connect(
				getDefaultStopperNode());
		customerSecuritiesUploadProcess.asExcOutput().connect(
				getDefaultStopperNode());
		ofivalSecuritiesUploadProcess.asExcOutput().connect(
				getDefaultStopperNode());

		// link inputs
		// $-- /business process/FileCreated/business process/LiveCycle Validation/in
		fileCreated.connectNodeToOut(liveCycleValidationNode);

		forkNodeNode.init();
		liveCycleValidationNode.init();
		securityFormatValidatorNodeNode.init();
		virtualSecurityReplacementNodeNode.init();
	}

}