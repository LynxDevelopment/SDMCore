package com.lynxspa.sdm.importers.bloomberg.securities.businessprocess;

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
@GeneratedType(definitionFile = "src/com/lynxspa/sdm/importers/bloomberg/securities/businessprocess/ImportBloombergSecurities.fpmprocess")
public class ImportBloombergSecuritiesProcess extends BusinessProcess {
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

	// input 'BloombergFileCreated'
	// $-- /business process/BloombergFileCreated
	private com.lynxit.fpm.BusinessProcessInput<com.lynxit.fpm.events.fileevents.FileEvent> bloombergFileCreated;

	public com.lynxit.fpm.BusinessProcessInput<com.lynxit.fpm.events.fileevents.FileEvent> asBloombergFileCreatedInput() {
		return bloombergFileCreated;
	}

	/*
	 * outputs declarations
	 */

	public ImportBloombergSecuritiesProcess(String id) {
		super(id);
	}

	public void init() throws Exception {
		Properties initParams = ConfigParams.getInstance().getProperties();

		// instantiate and configure inputs
		// instantiate and configure 'BloombergFileCreated' input
		bloombergFileCreated = addInput("BloombergFileCreated");

		// instantiate and configure outputs

		// instantiate and configure nodes
		// $-- /business process/SDMSecuritiesBloombergImporter
		// instantiate and configure 'SDMSecuritiesBloombergImporter' node
		com.lynxspa.sdm.importers.bloomberg.securities.nodes.SDMSecuritiesBloombergImporter<java.lang.Object> sDMSecuritiesBloombergImporterNode = Interceptors
				.createNode(com.lynxspa.sdm.importers.bloomberg.securities.nodes.SDMSecuritiesBloombergImporter.class);
		sDMSecuritiesBloombergImporterNode
				.setId("SDMSecuritiesBloombergImporter");
		addNode(sDMSecuritiesBloombergImporterNode);
		// $-- /business process/SDMSecuritiesBloombergImporter/
		sDMSecuritiesBloombergImporterNode
				.setStatelessResource(getSessionStateless());
		// $-- /business process/SDMSecuritiesBloombergImporter/
		sDMSecuritiesBloombergImporterNode.setLocale(getLocale());
		// $-- /business process/SDMSecuritiesBloombergImporter/
		sDMSecuritiesBloombergImporterNode.setResource(getSession());
		// $-- /business process/SDMSecuritiesBloombergImporter/
		// $-- /business process/SDMSecuritiesBloombergImporter/
		sDMSecuritiesBloombergImporterNode.setUser(getUser());

		// instantiate and configure subprocesses

		// link nodes
		sDMSecuritiesBloombergImporterNode
				.connectNodeToException(getDefaultExceptionHandlerNode());

		// link subprocesses

		// link inputs
		// $-- /business process/BloombergFileCreated/business process/SDMSecuritiesBloombergImporter/in
		bloombergFileCreated
				.connectNodeToOut(sDMSecuritiesBloombergImporterNode);

		sDMSecuritiesBloombergImporterNode.init();
	}

}