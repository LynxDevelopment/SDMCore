package com.lynxspa.sdm.importers;

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

@GeneratedType(definitionFile = "src/com/lynxspa/sdm/importers/ImportBloombergSecurities.fpmapplication")
public class ImportBloombergSecuritiesApplication extends ApplicationDomain {

	public ImportBloombergSecuritiesApplication(String id) throws Exception {
		super(id);
	}

	/*
	 * Parameters declarations
	 */
	private String inputBloombergSecuritiesCronn_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "inputBloombergSecuritiesCronn")
	public String getInputBloombergSecuritiesCronn() {
		return inputBloombergSecuritiesCronn_;
	}

	public void setInputBloombergSecuritiesCronn(String value) {
		this.inputBloombergSecuritiesCronn_ = value;
	}

	private java.io.File inputBloombergSecuritiesDirectory_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "inputBloombergSecuritiesDirectory")
	public java.io.File getInputBloombergSecuritiesDirectory() {
		return inputBloombergSecuritiesDirectory_;
	}

	public void setInputBloombergSecuritiesDirectory(java.io.File value) {
		this.inputBloombergSecuritiesDirectory_ = value;
	}

	private String inputBloombergSecuritiesPattern_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "inputBloombergSecuritiesPattern")
	public String getInputBloombergSecuritiesPattern() {
		return inputBloombergSecuritiesPattern_;
	}

	public void setInputBloombergSecuritiesPattern(String value) {
		this.inputBloombergSecuritiesPattern_ = value;
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

	private String user_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "user")
	public String getUser() {
		return user_;
	}

	public void setUser(String value) {
		this.user_ = value;
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
		// $-- /application/BloombergFileCreated
		// instantiate and configure 'BloombergFileCreated' event producer
		com.lynxspa.fpm.events.StandardFileCreatedEventproducer bloombergFileCreatedEventProducer = Interceptors
				.createEventProducer(
						com.lynxspa.fpm.events.StandardFileCreatedEventproducer.class,
						"BloombergFileCreated");

		// add the newly created resource to domain
		this.addEventProducer(bloombergFileCreatedEventProducer);

		// $-- /application/BloombergFileCreated/
		bloombergFileCreatedEventProducer
				.setInputDirectory(getInputBloombergSecuritiesDirectory());
		// $-- /application/BloombergFileCreated/
		bloombergFileCreatedEventProducer.setWaitTimeout(getInputTimeout());
		// $-- /application/BloombergFileCreated/
		bloombergFileCreatedEventProducer
				.setPattern(getInputBloombergSecuritiesPattern());
		// $-- /application/BloombergFileCreated/
		bloombergFileCreatedEventProducer.setIncludeSubdirectories(false);
		// $-- /application/BloombergFileCreated/
		bloombergFileCreatedEventProducer.setCaseSensitivePattern(true);
		// $-- /application/BloombergFileCreated/
		bloombergFileCreatedEventProducer.setDetectAlreadyExistentFiles(true);
		// $-- /application/BloombergFileCreated/
		bloombergFileCreatedEventProducer
				.setCronExpression(getInputBloombergSecuritiesCronn());
		// $-- /application/BloombergFileCreated/
		// $-- /application/BloombergFileCreated/
		bloombergFileCreatedEventProducer.setAllowMultiThreading(false);

		// link event producer to resources

		bloombergFileCreatedEventProducer.init();

		/*
		 * business processes section
		 */

		// $-- /application/ImportBloombergSecuritiesProcess1
		// instantiate and configure 'ImportBloombergSecuritiesProcess1' business process
		com.lynxspa.sdm.importers.bloomberg.securities.businessprocess.ImportBloombergSecuritiesProcess importBloombergSecuritiesProcess1BusinessProcess = Interceptors
				.createBusinessProcess(
						com.lynxspa.sdm.importers.bloomberg.securities.businessprocess.ImportBloombergSecuritiesProcess.class,
						"ImportBloombergSecuritiesProcess1", 1);

		// add business process to the processes
		this.addBusinessProcess(importBloombergSecuritiesProcess1BusinessProcess);

		// $-- /application/ImportBloombergSecuritiesProcess1/
		importBloombergSecuritiesProcess1BusinessProcess.setLocale(getLocale());
		// $-- /application/ImportBloombergSecuritiesProcess1/
		// $-- /application/ImportBloombergSecuritiesProcess1/
		importBloombergSecuritiesProcess1BusinessProcess.setUser(getUser());

		// link process to resources
		// $-- /application/HibernateStatelessResource/resource/application/ImportBloombergSecuritiesProcess1/sessionStateless
		importBloombergSecuritiesProcess1BusinessProcess
				.setSessionStateless(hibernateStatelessResource_);
		// $-- /application/HibernateResource/resource/application/ImportBloombergSecuritiesProcess1/session
		importBloombergSecuritiesProcess1BusinessProcess
				.setSession(hibernateResource_);

		importBloombergSecuritiesProcess1BusinessProcess.initialize();

		/*
		 * Link event producers to processes, sub-applications or outputs
		 */
		// $-- /application/BloombergFileCreated/events/application/ImportBloombergSecuritiesProcess1/bloombergFileCreated
		BusinessProcess.connect(bloombergFileCreatedEventProducer,
				importBloombergSecuritiesProcess1BusinessProcess
						.asBloombergFileCreatedInput());

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

		setInputBloombergSecuritiesCronn(ConfigurationHelper.parseString(
				initParams.getProperty("inputBloombergSecuritiesCronn"),
				String.class));
		setInputBloombergSecuritiesDirectory(ConfigurationHelper.parseString(
				initParams.getProperty("inputBloombergSecuritiesDirectory"),
				java.io.File.class));
		setInputBloombergSecuritiesPattern(ConfigurationHelper.parseString(
				initParams.getProperty("inputBloombergSecuritiesPattern"),
				String.class));
		setInputTimeout(ConfigurationHelper.parseString(
				initParams.getProperty("inputTimeout"), Integer.class));
		setLocale(ConfigurationHelper.parseString(
				initParams.getProperty("locale"), String.class));
		setNotes(ConfigurationHelper.parseString(
				initParams.getProperty("Notes"), java.lang.String.class));
		setUser(ConfigurationHelper.parseString(initParams.getProperty("user"),
				String.class));
	}

	public static void main(String[] args) {
		try {
			ApplicationDomain application = Interceptors
					.createApplicationDomain(
							ImportBloombergSecuritiesApplication.class,
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