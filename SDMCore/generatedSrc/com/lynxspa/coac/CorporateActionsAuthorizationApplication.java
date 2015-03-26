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

@GeneratedType(definitionFile = "src/com/lynxspa/coac/CorporateActionsAuthorization.fpmapplication")
public class CorporateActionsAuthorizationApplication extends ApplicationDomain {

	public CorporateActionsAuthorizationApplication(String id) throws Exception {
		super(id);
	}

	/*
	 * Parameters declarations
	 */
	private String authorizationCron_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "authorizationCron")
	public String getAuthorizationCron() {
		return authorizationCron_;
	}

	public void setAuthorizationCron(String value) {
		this.authorizationCron_ = value;
	}

	private java.lang.Integer authorizationSelectSize_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "authorizationSelectSize")
	public java.lang.Integer getAuthorizationSelectSize() {
		return authorizationSelectSize_;
	}

	public void setAuthorizationSelectSize(java.lang.Integer value) {
		this.authorizationSelectSize_ = value;
	}

	private String locale_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "locale")
	public String getLocale() {
		return locale_;
	}

	public void setLocale(String value) {
		this.locale_ = value;
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
		standardEventProducerEventProducer
				.setCronExpression(getAuthorizationCron());

		// link event producer to resources

		standardEventProducerEventProducer.init();

		/*
		 * business processes section
		 */

		// $-- /application/AuthorizeProcess
		// instantiate and configure 'AuthorizeProcess' business process
		com.lynxspa.coac.authorization.businessprocess.AuthorizeProcess authorizeProcessBusinessProcess = Interceptors
				.createBusinessProcess(
						com.lynxspa.coac.authorization.businessprocess.AuthorizeProcess.class,
						"AuthorizeProcess", 1);

		// add business process to the processes
		this.addBusinessProcess(authorizeProcessBusinessProcess);

		// $-- /application/AuthorizeProcess/
		authorizeProcessBusinessProcess.setLocale(getLocale());
		// $-- /application/AuthorizeProcess/
		authorizeProcessBusinessProcess
				.setSelectSize(getAuthorizationSelectSize());
		// $-- /application/AuthorizeProcess/
		// $-- /application/AuthorizeProcess/
		authorizeProcessBusinessProcess.setUser("AUTHORIZATIONPROCESS");

		// link process to resources
		// $-- /application/HibernateResource/resource/application/AuthorizeProcess/statefullSession
		authorizeProcessBusinessProcess.setStatefullSession(hibernateResource_);

		authorizeProcessBusinessProcess.initialize();

		// $-- /application/ErrorHandler
		// instantiate and configure 'ErrorHandler' business process
		com.lynxspa.coac.businessprocess.ExceptionHandlerProcess errorHandlerBusinessProcess = Interceptors
				.createBusinessProcess(
						com.lynxspa.coac.businessprocess.ExceptionHandlerProcess.class,
						"ErrorHandler", 1);

		// add business process to the processes
		this.addBusinessProcess(errorHandlerBusinessProcess);

		// $-- /application/ErrorHandler/
		errorHandlerBusinessProcess.setLocale(getLocale());
		// $-- /application/ErrorHandler/
		// $-- /application/ErrorHandler/
		errorHandlerBusinessProcess.setUser("AUTHORIZATIONERRORHANDLER");

		// link process to resources
		// $-- /application/HibernateResource/resource/application/ErrorHandler/sessionFactory
		errorHandlerBusinessProcess.setSessionFactory(hibernateResource_);

		errorHandlerBusinessProcess.initialize();

		/*
		 * Link event producers to processes, sub-applications or outputs
		 */
		// $-- /application/StandardEventProducer/events/application/AuthorizeProcess/input
		BusinessProcess.connect(standardEventProducerEventProducer,
				authorizeProcessBusinessProcess.asInputInput());

		/*
		 * Link inputs to processes, sub-applications or outputs
		 */

		/*
		 * Link processes to other processes, sub-applications or outputs
		 */
		// $-- /application/AuthorizeProcess/exc/application/ErrorHandler/input
		BusinessProcess.connect(
				authorizeProcessBusinessProcess.asExcEventProducer(),
				errorHandlerBusinessProcess.asInputInput());

		/*
		 * Link sub applications to other processes, sub-applications or outputs
		 */
	}

	protected void setInitParams() throws ParseException {
		Properties initParams = ConfigParams.getInstance().getProperties();

		setAuthorizationCron(ConfigurationHelper.parseString(
				initParams.getProperty("authorizationCron"), String.class));
		setAuthorizationSelectSize(ConfigurationHelper.parseString(
				initParams.getProperty("authorizationSelectSize"),
				java.lang.Integer.class));
		setLocale(ConfigurationHelper.parseString(
				initParams.getProperty("locale"), String.class));
		setNotes(ConfigurationHelper.parseString(
				initParams.getProperty("Notes"), java.lang.String.class));
	}

	public static void main(String[] args) {
		try {
			ApplicationDomain application = Interceptors
					.createApplicationDomain(
							CorporateActionsAuthorizationApplication.class,
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