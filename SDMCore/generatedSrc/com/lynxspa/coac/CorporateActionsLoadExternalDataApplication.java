package com.lynxspa.coac;

import com.lynxit.fpm.ApplicationDomain;
import com.lynxit.fpm.BusinessProcess;
import com.lynxit.fpm.FPM;
import com.lynxit.fpm.admin.config.annotations.ConfigParam;
import com.lynxit.fpm.admin.config.annotations.GeneratedType;
import com.lynxit.fpm.events.EventListener;
import com.lynxit.fpm.events.EventProducer;
import com.lynxit.fpm.resources.ResourcesManager;
import com.lynxit.fpm.instrumentation.interceptors.Interceptors;
import com.lynxit.fpm.resources.Resource;

import com.lynxit.utils.beans.BeanWithRuntimeConfigurableParams;
import com.lynxit.utils.scripting.*;
import com.lynxit.utils.ConfigParams;
import com.lynxit.utils.ConfigurationHelper;

import java.text.ParseException;
import java.util.Properties;

@GeneratedType(definitionFile = "src/com/lynxspa/coac/CorporateActionsLoadExternalData.fpmapplication")
public class CorporateActionsLoadExternalDataApplication extends
		ApplicationDomain {

	public CorporateActionsLoadExternalDataApplication(String id)
			throws Exception {
		super(id);
	}

	/*
	 * Parameters declarations
	 */
	private String customersLoadCron_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "customersLoadCron")
	public String getCustomersLoadCron() {
		return customersLoadCron_;
	}

	public void setCustomersLoadCron(String value) {
		this.customersLoadCron_ = value;
	}

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

	/*
	 * Resources accessors
	 */
	private Resource<org.hibernate.Session> hibernateResource_;

	@ConfigParam(required = false)
	public Resource<org.hibernate.Session> getHibernateResource() {
		return hibernateResource_;
	}

	public void setHibernateResource(Resource<org.hibernate.Session> res) {
		this.hibernateResource_ = res;
	}

	private Resource<com.lynxspa.hbt.extensions.StandardStatelessSession> hibernateStatelessResource_;

	@ConfigParam(required = false)
	public Resource<com.lynxspa.hbt.extensions.StandardStatelessSession> getHibernateStatelessResource() {
		return hibernateStatelessResource_;
	}

	public void setHibernateStatelessResource(
			Resource<com.lynxspa.hbt.extensions.StandardStatelessSession> res) {
		this.hibernateStatelessResource_ = res;
	}

	/*
	 * inputs declarations
	 */

	/*
	 * outputs declarations
	 */

	protected void init() throws Exception {
		/*
		 * resources section
		 */
		ResourcesManager resourcesManager = ResourcesManager.getInstance();

		if (hibernateResource_ == null) {
			// $-- /application/HibernateResource
			// instantiate and configure 'HibernateResource' resource
			com.lynxit.fpm.resources.hibernate.HibernateResource hibernateResource = new com.lynxit.fpm.resources.hibernate.HibernateResource(
					"HibernateResource");
			hibernateResource.setApplicationDomain(this);
			resourcesManager.addResource(hibernateResource);
			// $-- /application/HibernateResource/
			hibernateResource.setUseAnnotation(true);
			// $-- /application/HibernateResource/
			hibernateResource.setConfigFilePath("hibernate.cfg.xml");
			// $-- /application/HibernateResource/

			// link resources to resources

			// add the newly created resource to resources manager
			hibernateResource.init();

			hibernateResource_ = hibernateResource;
		}
		if (hibernateStatelessResource_ == null) {
			// $-- /application/HibernateStatelessResource
			// instantiate and configure 'HibernateStatelessResource' resource
			com.lynxspa.fpm.resources.HibernateStatelessResource hibernateStatelessResource = new com.lynxspa.fpm.resources.HibernateStatelessResource(
					"HibernateStatelessResource");
			hibernateStatelessResource.setApplicationDomain(this);
			resourcesManager.addResource(hibernateStatelessResource);
			// $-- /application/HibernateStatelessResource/

			// link resources to resources
			// 1
			// $-- /application/HibernateResource/resource/application/HibernateStatelessResource/resource
			hibernateStatelessResource.setResource(hibernateResource_);

			// add the newly created resource to resources manager
			hibernateStatelessResource.init();

			hibernateStatelessResource_ = hibernateStatelessResource;
		}

		/*
		 * sub-applications section
		 */

		/*
		 * events section
		 */
		// $-- /application/Customers Load
		// instantiate and configure 'Customers Load' event producer
		com.lynxspa.fpm.events.StandardEventProducer customersLoadEventProducer = Interceptors
				.createEventProducer(
						com.lynxspa.fpm.events.StandardEventProducer.class,
						"Customers Load");

		// add the newly created resource to domain
		this.addEventProducer(customersLoadEventProducer);

		// $-- /application/Customers Load/
		customersLoadEventProducer.setAllowMultiThreading(false);
		// $-- /application/Customers Load/
		// $-- /application/Customers Load/
		customersLoadEventProducer.setCronExpression(getCustomersLoadCron());

		// link event producer to resources

		customersLoadEventProducer.init();

		/*
		 * business processes section
		 */

		// $-- /application/LoadCustomersProcess
		// instantiate and configure 'LoadCustomersProcess' business process
		com.lynxspa.coac.loadauxtables.customers.businessprocess.LoadCustomersProcess loadCustomersProcessBusinessProcess = Interceptors
				.createBusinessProcess(
						com.lynxspa.coac.loadauxtables.customers.businessprocess.LoadCustomersProcess.class,
						"LoadCustomersProcess", 1);

		// add business process to the processes
		this.addBusinessProcess(loadCustomersProcessBusinessProcess);

		// $-- /application/LoadCustomersProcess/
		loadCustomersProcessBusinessProcess.setLocale(getLocale());
		// $-- /application/LoadCustomersProcess/
		loadCustomersProcessBusinessProcess.setPageSize(getPageSize());
		// $-- /application/LoadCustomersProcess/
		// $-- /application/LoadCustomersProcess/
		loadCustomersProcessBusinessProcess.setUser("CUSTOMERSLOADUSER");

		// link process to resources
		// $-- /application/HibernateResource/resource/application/LoadCustomersProcess/sessionFactory
		loadCustomersProcessBusinessProcess
				.setSessionFactory(hibernateResource_);
		// $-- /application/HibernateStatelessResource/resource/application/LoadCustomersProcess/statelessSessionFactory
		loadCustomersProcessBusinessProcess
				.setStatelessSessionFactory(hibernateStatelessResource_);

		loadCustomersProcessBusinessProcess.initialize();

		/*
		 * Link event producers to processes, sub-applications or outputs
		 */
		// $-- /application/Customers Load/events/application/LoadCustomersProcess/input
		BusinessProcess.connect(customersLoadEventProducer,
				loadCustomersProcessBusinessProcess.asInputInput());

		/*
		 * Link inputs to processes, sub-applications or outputs
		 */

		/*
		 * Link processes to other processes, sub-applications or outputs
		 */

		/*
		 * Link sub applications to other processes, sub-applications or outputs
		 */
	}

	protected void setInitParams() throws ParseException {
		Properties initParams = ConfigParams.getInstance().getProperties();

		setCustomersLoadCron(ConfigurationHelper.parseString(
				initParams.getProperty("customersLoadCron"), String.class));
		setLocale(ConfigurationHelper.parseString(
				initParams.getProperty("locale"), String.class));
		setNotes(ConfigurationHelper.parseString(
				initParams.getProperty("Notes"), java.lang.String.class));
		setPageSize(ConfigurationHelper.parseString(
				initParams.getProperty("pageSize"), Integer.class));
	}

	public static void main(String[] args) {
		try {
			ApplicationDomain application = Interceptors
					.createApplicationDomain(
							CorporateActionsLoadExternalDataApplication.class,
							"application");

			FPM executionContext = Interceptors
					.createExecutionContext(FPM.class);
			executionContext.setApplicationDomain(application);
			executionContext.initLogger();
			executionContext.initialize();
			executionContext.start();
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}
}