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

@GeneratedType(definitionFile = "src/com/lynxspa/coac/CorporateActionsPlanning.fpmapplication")
public class CorporateActionsPlanningApplication extends ApplicationDomain {

	public CorporateActionsPlanningApplication(String id) throws Exception {
		super(id);
	}

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

	private String planningCronnExpression_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "planningCronnExpression")
	public String getPlanningCronnExpression() {
		return planningCronnExpression_;
	}

	public void setPlanningCronnExpression(String value) {
		this.planningCronnExpression_ = value;
	}

	private java.io.File tempPathFile_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "tempPathFile")
	public java.io.File getTempPathFile() {
		return tempPathFile_;
	}

	public void setTempPathFile(java.io.File value) {
		this.tempPathFile_ = value;
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
		standardEventProducerEventProducer
				.setCronExpression(getPlanningCronnExpression());

		// link event producer to resources

		standardEventProducerEventProducer.init();

		/*
		 * business processes section
		 */

		// $-- /application/ControlExecutionPlanningProcess
		// instantiate and configure 'ControlExecutionPlanningProcess' business process
		com.lynxspa.coac.plannings.businessprocess.ControlExecutionPlanningsProcess controlExecutionPlanningProcessBusinessProcess = Interceptors
				.createBusinessProcess(
						com.lynxspa.coac.plannings.businessprocess.ControlExecutionPlanningsProcess.class,
						"ControlExecutionPlanningProcess", 1);

		// add business process to the processes
		this.addBusinessProcess(controlExecutionPlanningProcessBusinessProcess);

		// $-- /application/ControlExecutionPlanningProcess/
		controlExecutionPlanningProcessBusinessProcess.setLocale(getLocale());
		// $-- /application/ControlExecutionPlanningProcess/
		controlExecutionPlanningProcessBusinessProcess
				.setTempPathFile(getTempPathFile());
		// $-- /application/ControlExecutionPlanningProcess/
		// $-- /application/ControlExecutionPlanningProcess/
		controlExecutionPlanningProcessBusinessProcess
				.setUser("CONTROLPLANNINGS");

		// link process to resources
		// $-- /application/HibernateResource/resource/application/ControlExecutionPlanningProcess/statefullSession
		controlExecutionPlanningProcessBusinessProcess
				.setStatefullSession(hibernateResource_);
		// $-- /application/HibernateStatelessResource/resource/application/ControlExecutionPlanningProcess/statelessSession
		controlExecutionPlanningProcessBusinessProcess
				.setStatelessSession(hibernateStatelessResource_);

		controlExecutionPlanningProcessBusinessProcess.initialize();

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
		errorHandlerBusinessProcess.setUser("PLANNINGERRORHANDLER");

		// link process to resources
		// $-- /application/HibernateStatelessResource/resource/application/ErrorHandler/sessionFactory
		errorHandlerBusinessProcess
				.setSessionFactory(hibernateStatelessResource_);

		errorHandlerBusinessProcess.initialize();

		/*
		 * Link event producers to processes, sub-applications or outputs
		 */
		// $-- /application/StandardEventProducer/events/application/ControlExecutionPlanningProcess/input
		BusinessProcess.connect(standardEventProducerEventProducer,
				controlExecutionPlanningProcessBusinessProcess.asInputInput());

		/*
		 * Link inputs to processes, sub-applications or outputs
		 */

		/*
		 * Link processes to other processes, sub-applications or outputs
		 */
		// $-- /application/ControlExecutionPlanningProcess/exc/application/ErrorHandler/input
		BusinessProcess.connect(controlExecutionPlanningProcessBusinessProcess
				.asExcEventProducer(), errorHandlerBusinessProcess
				.asInputInput());

		/*
		 * Link sub applications to other processes, sub-applications or outputs
		 */
	}

	protected void setInitParams() throws ParseException {
		Properties initParams = ConfigParams.getInstance().getProperties();

		setLocale(ConfigurationHelper.parseString(
				initParams.getProperty("locale"), String.class));
		setNotes(ConfigurationHelper.parseString(
				initParams.getProperty("Notes"), java.lang.String.class));
		setPlanningCronnExpression(ConfigurationHelper
				.parseString(initParams.getProperty("planningCronnExpression"),
						String.class));
		setTempPathFile(ConfigurationHelper.parseString(
				initParams.getProperty("tempPathFile"), java.io.File.class));
	}

	public static void main(String[] args) {
		try {
			ApplicationDomain application = Interceptors
					.createApplicationDomain(
							CorporateActionsPlanningApplication.class,
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