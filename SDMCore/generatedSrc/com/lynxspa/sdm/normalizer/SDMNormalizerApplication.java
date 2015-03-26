package com.lynxspa.sdm.normalizer;

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

@GeneratedType(definitionFile = "src/com/lynxspa/sdm/normalizer/SDMNormalizer.fpmapplication")
public class SDMNormalizerApplication extends ApplicationDomain {

	public SDMNormalizerApplication(String id) throws Exception {
		super(id);
	}

	/*
	 * Parameters declarations
	 */
	private String inputBloombergFieldsCronn_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "inputBloombergFieldsCronn")
	public String getInputBloombergFieldsCronn() {
		return inputBloombergFieldsCronn_;
	}

	public void setInputBloombergFieldsCronn(String value) {
		this.inputBloombergFieldsCronn_ = value;
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
		// $-- /application/StandardEventProducer
		// instantiate and configure 'StandardEventProducer' event producer
		com.lynxspa.fpm.events.StandardEventProducer standardEventProducerEventProducer = Interceptors
				.createEventProducer(
						com.lynxspa.fpm.events.StandardEventProducer.class,
						"StandardEventProducer");

		// add the newly created resource to domain
		this.addEventProducer(standardEventProducerEventProducer);

		// $-- /application/StandardEventProducer/
		standardEventProducerEventProducer.setAllowMultiThreading(false);
		// $-- /application/StandardEventProducer/
		// $-- /application/StandardEventProducer/
		standardEventProducerEventProducer.setCronExpression("0/30 * * * * ?");

		// link event producer to resources

		standardEventProducerEventProducer.init();

		/*
		 * business processes section
		 */

		// $-- /application/SDMNormalizerProcessProcess
		// instantiate and configure 'SDMNormalizerProcessProcess' business process
		com.lynxspa.sdm.normalizer.businessprocess.SDMNormalizerProcessProcess sDMNormalizerProcessProcessBusinessProcess = Interceptors
				.createBusinessProcess(
						com.lynxspa.sdm.normalizer.businessprocess.SDMNormalizerProcessProcess.class,
						"SDMNormalizerProcessProcess", 1);

		// add business process to the processes
		this.addBusinessProcess(sDMNormalizerProcessProcessBusinessProcess);

		// $-- /application/SDMNormalizerProcessProcess/
		sDMNormalizerProcessProcessBusinessProcess.setLocale(getLocale());
		// $-- /application/SDMNormalizerProcessProcess/
		// $-- /application/SDMNormalizerProcessProcess/
		sDMNormalizerProcessProcessBusinessProcess.setUser(getUser());

		// link process to resources
		// $-- /application/HibernateStatelessResource/resource/application/SDMNormalizerProcessProcess/sessionStateless
		sDMNormalizerProcessProcessBusinessProcess
				.setSessionStateless(hibernateStatelessResource_);
		// $-- /application/HibernateResource/resource/application/SDMNormalizerProcessProcess/session
		sDMNormalizerProcessProcessBusinessProcess
				.setSession(hibernateResource_);

		sDMNormalizerProcessProcessBusinessProcess.initialize();

		/*
		 * Link event producers to processes, sub-applications or outputs
		 */
		// $-- /application/StandardEventProducer/events/application/SDMNormalizerProcessProcess/normalizerEvent
		BusinessProcess.connect(standardEventProducerEventProducer,
				sDMNormalizerProcessProcessBusinessProcess
						.asNormalizerEventInput());

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

		setInputBloombergFieldsCronn(ConfigurationHelper.parseString(
				initParams.getProperty("inputBloombergFieldsCronn"),
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
					.createApplicationDomain(SDMNormalizerApplication.class,
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