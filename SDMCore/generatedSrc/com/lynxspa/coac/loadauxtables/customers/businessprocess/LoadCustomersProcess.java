package com.lynxspa.coac.loadauxtables.customers.businessprocess;

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
@GeneratedType(definitionFile = "src/com/lynxspa/coac/loadauxtables/customers/businessprocess/LoadCustomers.fpmprocess")
public class LoadCustomersProcess extends BusinessProcess {
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

	private Integer pageSize_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "pageSize")
	public Integer getPageSize() {
		return pageSize_;
	}

	public void setPageSize(Integer value) {
		this.pageSize_ = value;
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

	// input 'input'
	// $-- /business process/input
	private com.lynxit.fpm.BusinessProcessInput<com.lynxit.fpm.events.scheduler.ScheduledEvent> input;

	public com.lynxit.fpm.BusinessProcessInput<com.lynxit.fpm.events.scheduler.ScheduledEvent> asInputInput() {
		return input;
	}

	/*
	 * outputs declarations
	 */

	public LoadCustomersProcess(String id) {
		super(id);
	}

	public void init() throws Exception {
		Properties initParams = ConfigParams.getInstance().getProperties();

		// instantiate and configure inputs
		// instantiate and configure 'input' input
		input = addInput("input");

		// instantiate and configure outputs

		// instantiate and configure nodes
		// $-- /business process/ForkNode
		// instantiate and configure 'ForkNode' node
		com.lynxit.fpm.nodes.internal.ForkNode<com.lynxit.fpm.events.scheduler.ScheduledEvent> forkNodeNode = Interceptors
				.createNode(com.lynxit.fpm.nodes.internal.ForkNode.class);
		forkNodeNode.setId("ForkNode");
		addNode(forkNodeNode);
		// $-- /business process/ForkNode/
		// $-- /business process/ForkNode/
		forkNodeNode.setCloneMessage(false);

		// $-- /business process/ForkNode1
		// instantiate and configure 'ForkNode1' node
		com.lynxit.fpm.nodes.internal.ForkNode<com.lynxit.fpm.events.scheduler.ScheduledEvent> forkNode1Node = Interceptors
				.createNode(com.lynxit.fpm.nodes.internal.ForkNode.class);
		forkNode1Node.setId("ForkNode1");
		addNode(forkNode1Node);
		// $-- /business process/ForkNode1/
		// $-- /business process/ForkNode1/
		forkNode1Node.setCloneMessage(false);

		// $-- /business process/ForkNode11
		// instantiate and configure 'ForkNode11' node
		com.lynxit.fpm.nodes.internal.ForkNode<com.lynxit.fpm.events.scheduler.ScheduledEvent> forkNode11Node = Interceptors
				.createNode(com.lynxit.fpm.nodes.internal.ForkNode.class);
		forkNode11Node.setId("ForkNode11");
		addNode(forkNode11Node);
		// $-- /business process/ForkNode11/
		// $-- /business process/ForkNode11/
		forkNode11Node.setCloneMessage(false);

		// $-- /business process/ForkNode111
		// instantiate and configure 'ForkNode111' node
		com.lynxit.fpm.nodes.internal.ForkNode<com.lynxit.fpm.events.scheduler.ScheduledEvent> forkNode111Node = Interceptors
				.createNode(com.lynxit.fpm.nodes.internal.ForkNode.class);
		forkNode111Node.setId("ForkNode111");
		addNode(forkNode111Node);
		// $-- /business process/ForkNode111/
		// $-- /business process/ForkNode111/
		forkNode111Node.setCloneMessage(false);

		// $-- /business process/ForkNode112
		// instantiate and configure 'ForkNode112' node
		com.lynxit.fpm.nodes.internal.ForkNode<com.lynxit.fpm.events.scheduler.ScheduledEvent> forkNode112Node = Interceptors
				.createNode(com.lynxit.fpm.nodes.internal.ForkNode.class);
		forkNode112Node.setId("ForkNode112");
		addNode(forkNode112Node);
		// $-- /business process/ForkNode112/
		// $-- /business process/ForkNode112/
		forkNode112Node.setCloneMessage(false);

		// $-- /business process/LoadClientHoldings
		// instantiate and configure 'LoadClientHoldings' node
		com.lynxspa.coac.loadauxtables.customers.nodes.LoadClientHoldings<com.lynxit.fpm.events.scheduler.ScheduledEvent> loadClientHoldingsNode = Interceptors
				.createNode(com.lynxspa.coac.loadauxtables.customers.nodes.LoadClientHoldings.class);
		loadClientHoldingsNode.setId("LoadClientHoldings");
		addNode(loadClientHoldingsNode);
		// $-- /business process/LoadClientHoldings/
		loadClientHoldingsNode
				.setResourceStateless(getStatelessSessionFactory());
		// $-- /business process/LoadClientHoldings/
		loadClientHoldingsNode.setLocale(getLocale());
		// $-- /business process/LoadClientHoldings/
		loadClientHoldingsNode.setPageSize(getPageSize());
		// $-- /business process/LoadClientHoldings/
		// $-- /business process/LoadClientHoldings/
		loadClientHoldingsNode.setUser(getUser());

		// $-- /business process/LoadCustomers
		// instantiate and configure 'LoadCustomers' node
		com.lynxspa.coac.loadauxtables.customers.nodes.LoadCustomers<com.lynxit.fpm.events.scheduler.ScheduledEvent> loadCustomersNode = Interceptors
				.createNode(com.lynxspa.coac.loadauxtables.customers.nodes.LoadCustomers.class);
		loadCustomersNode.setId("LoadCustomers");
		addNode(loadCustomersNode);
		// $-- /business process/LoadCustomers/
		loadCustomersNode.setResourceStateless(getStatelessSessionFactory());
		// $-- /business process/LoadCustomers/
		loadCustomersNode.setLocale(getLocale());
		// $-- /business process/LoadCustomers/
		loadCustomersNode.setResource(getSessionFactory());
		// $-- /business process/LoadCustomers/
		loadCustomersNode.setPageSize(getPageSize());
		// $-- /business process/LoadCustomers/
		// $-- /business process/LoadCustomers/
		loadCustomersNode.setUser(getUser());

		// $-- /business process/LoadManagers
		// instantiate and configure 'LoadManagers' node
		com.lynxspa.coac.loadauxtables.customers.nodes.LoadManagers<com.lynxit.fpm.events.scheduler.ScheduledEvent> loadManagersNode = Interceptors
				.createNode(com.lynxspa.coac.loadauxtables.customers.nodes.LoadManagers.class);
		loadManagersNode.setId("LoadManagers");
		addNode(loadManagersNode);
		// $-- /business process/LoadManagers/
		loadManagersNode.setResourceStateless(getStatelessSessionFactory());
		// $-- /business process/LoadManagers/
		loadManagersNode.setLocale(getLocale());
		// $-- /business process/LoadManagers/
		loadManagersNode.setPageSize(getPageSize());
		// $-- /business process/LoadManagers/
		// $-- /business process/LoadManagers/
		loadManagersNode.setUser(getUser());

		// $-- /business process/LoadMarkets
		// instantiate and configure 'LoadMarkets' node
		com.lynxspa.coac.loadauxtables.customers.nodes.LoadMarkets<com.lynxit.fpm.events.scheduler.ScheduledEvent> loadMarketsNode = Interceptors
				.createNode(com.lynxspa.coac.loadauxtables.customers.nodes.LoadMarkets.class);
		loadMarketsNode.setId("LoadMarkets");
		addNode(loadMarketsNode);
		// $-- /business process/LoadMarkets/
		loadMarketsNode.setResourceStateless(getStatelessSessionFactory());
		// $-- /business process/LoadMarkets/
		loadMarketsNode.setLocale(getLocale());
		// $-- /business process/LoadMarkets/
		loadMarketsNode.setPageSize(getPageSize());
		// $-- /business process/LoadMarkets/
		// $-- /business process/LoadMarkets/
		loadMarketsNode.setUser(getUser());

		// $-- /business process/LoadProviderAccounts
		// instantiate and configure 'LoadProviderAccounts' node
		com.lynxspa.coac.loadauxtables.customers.nodes.LoadProviderAccounts<com.lynxit.fpm.events.scheduler.ScheduledEvent> loadProviderAccountsNode = Interceptors
				.createNode(com.lynxspa.coac.loadauxtables.customers.nodes.LoadProviderAccounts.class);
		loadProviderAccountsNode.setId("LoadProviderAccounts");
		addNode(loadProviderAccountsNode);
		// $-- /business process/LoadProviderAccounts/
		loadProviderAccountsNode
				.setResourceStateless(getStatelessSessionFactory());
		// $-- /business process/LoadProviderAccounts/
		loadProviderAccountsNode.setLocale(getLocale());
		// $-- /business process/LoadProviderAccounts/
		loadProviderAccountsNode.setPageSize(getPageSize());
		// $-- /business process/LoadProviderAccounts/
		// $-- /business process/LoadProviderAccounts/
		loadProviderAccountsNode.setUser(getUser());

		// $-- /business process/LoadSecurities
		// instantiate and configure 'LoadSecurities' node
		com.lynxspa.coac.loadauxtables.customers.nodes.LoadSecurities<com.lynxit.fpm.events.scheduler.ScheduledEvent> loadSecuritiesNode = Interceptors
				.createNode(com.lynxspa.coac.loadauxtables.customers.nodes.LoadSecurities.class);
		loadSecuritiesNode.setId("LoadSecurities");
		addNode(loadSecuritiesNode);
		// $-- /business process/LoadSecurities/
		loadSecuritiesNode.setResourceStateless(getStatelessSessionFactory());
		// $-- /business process/LoadSecurities/
		loadSecuritiesNode.setLocale(getLocale());
		// $-- /business process/LoadSecurities/
		loadSecuritiesNode.setPageSize(getPageSize());
		// $-- /business process/LoadSecurities/
		// $-- /business process/LoadSecurities/
		loadSecuritiesNode.setUser(getUser());

		// instantiate and configure subprocesses

		// link nodes
		// $-- /business process/ForkNode/two/business process/LoadCustomers/in
		forkNodeNode.connectNodeToTwo(loadCustomersNode);
		// $-- /business process/ForkNode/one/business process/LoadManagers/in
		forkNodeNode.connectNodeToOne(loadManagersNode);
		forkNodeNode.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/ForkNode1/two/business process/ForkNode11/in
		forkNode1Node.connectNodeToTwo(forkNode11Node);
		// $-- /business process/ForkNode1/one/business process/ForkNode/in
		forkNode1Node.connectNodeToOne(forkNodeNode);
		forkNode1Node.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/ForkNode11/two/business process/ForkNode112/in
		forkNode11Node.connectNodeToTwo(forkNode112Node);
		// $-- /business process/ForkNode11/one/business process/ForkNode111/in
		forkNode11Node.connectNodeToOne(forkNode111Node);
		forkNode11Node.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/ForkNode111/two/business process/LoadSecurities/in
		forkNode111Node.connectNodeToTwo(loadSecuritiesNode);
		// $-- /business process/ForkNode111/one/business process/LoadMarkets/in
		forkNode111Node.connectNodeToOne(loadMarketsNode);
		forkNode111Node
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/ForkNode112/two/business process/LoadClientHoldings/in
		forkNode112Node.connectNodeToTwo(loadClientHoldingsNode);
		// $-- /business process/ForkNode112/one/business process/LoadProviderAccounts/in
		forkNode112Node.connectNodeToOne(loadProviderAccountsNode);
		forkNode112Node
				.connectNodeToException(getDefaultExceptionHandlerNode());
		loadClientHoldingsNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		loadCustomersNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		loadManagersNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		loadMarketsNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		loadProviderAccountsNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		loadSecuritiesNode
				.connectNodeToException(getDefaultExceptionHandlerNode());

		// link subprocesses

		// link inputs
		// $-- /business process/input/business process/ForkNode1/in
		input.connectNodeToOut(forkNode1Node);

		forkNodeNode.init();
		forkNode1Node.init();
		forkNode11Node.init();
		forkNode111Node.init();
		forkNode112Node.init();
		loadClientHoldingsNode.init();
		loadCustomersNode.init();
		loadManagersNode.init();
		loadMarketsNode.init();
		loadProviderAccountsNode.init();
		loadSecuritiesNode.init();
	}

}