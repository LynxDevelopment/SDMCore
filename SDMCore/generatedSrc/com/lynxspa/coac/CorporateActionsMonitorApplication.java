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

@GeneratedType(definitionFile = "src/com/lynxspa/coac/CorporateActionsMonitor.fpmapplication")
public class CorporateActionsMonitorApplication extends ApplicationDomain {

	public CorporateActionsMonitorApplication(String id) throws Exception {
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

	private String monitorCron_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "monitorCron")
	public String getMonitorCron() {
		return monitorCron_;
	}

	public void setMonitorCron(String value) {
		this.monitorCron_ = value;
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
		// $-- /application/ScheduledEventProducer
		// instantiate and configure 'ScheduledEventProducer' event producer
		com.lynxit.fpm.events.scheduler.ScheduledEventProducer scheduledEventProducerEventProducer = Interceptors
				.createEventProducer(
						com.lynxit.fpm.events.scheduler.ScheduledEventProducer.class,
						"ScheduledEventProducer");

		// add the newly created resource to domain
		this.addEventProducer(scheduledEventProducerEventProducer);

		// $-- /application/ScheduledEventProducer/
		// $-- /application/ScheduledEventProducer/
		scheduledEventProducerEventProducer.setCronExpression(getMonitorCron());

		// link event producer to resources

		scheduledEventProducerEventProducer.init();

		/*
		 * business processes section
		 */

		// $-- /application/CacheCleanerProcess
		// instantiate and configure 'CacheCleanerProcess' business process
		com.lynxspa.coac.monitor.businessprocess.CacheCleanerProcess cacheCleanerProcessBusinessProcess = Interceptors
				.createBusinessProcess(
						com.lynxspa.coac.monitor.businessprocess.CacheCleanerProcess.class,
						"CacheCleanerProcess", 1);

		// add business process to the processes
		this.addBusinessProcess(cacheCleanerProcessBusinessProcess);

		// $-- /application/CacheCleanerProcess/
		cacheCleanerProcessBusinessProcess.setLocale(getLocale());
		// $-- /application/CacheCleanerProcess/
		// $-- /application/CacheCleanerProcess/
		cacheCleanerProcessBusinessProcess.setUser("MONITORERRORHANDLER");

		// link process to resources
		// $-- /application/HibernateResource/resource/application/CacheCleanerProcess/statefullSession
		cacheCleanerProcessBusinessProcess
				.setStatefullSession(hibernateResource_);

		cacheCleanerProcessBusinessProcess.initialize();

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
		errorHandlerBusinessProcess.setUser("MONITORERRORHANDLER");

		// link process to resources
		// $-- /application/HibernateResource/resource/application/ErrorHandler/sessionFactory
		errorHandlerBusinessProcess.setSessionFactory(hibernateResource_);

		errorHandlerBusinessProcess.initialize();

		/*
		 * Link event producers to processes, sub-applications or outputs
		 */
		// $-- /application/ScheduledEventProducer/events/application/CacheCleanerProcess/input
		BusinessProcess.connect(scheduledEventProducerEventProducer,
				cacheCleanerProcessBusinessProcess.asInputInput());

		/*
		 * Link inputs to processes, sub-applications or outputs
		 */

		/*
		 * Link processes to other processes, sub-applications or outputs
		 */
		// $-- /application/CacheCleanerProcess/exc/application/ErrorHandler/input
		BusinessProcess.connect(
				cacheCleanerProcessBusinessProcess.asExcEventProducer(),
				errorHandlerBusinessProcess.asInputInput());

		/*
		 * Link sub applications to other processes, sub-applications or outputs
		 */
	}

	protected void setInitParams() throws ParseException {
		Properties initParams = ConfigParams.getInstance().getProperties();

		setLocale(ConfigurationHelper.parseString(
				initParams.getProperty("locale"), String.class));
		setMonitorCron(ConfigurationHelper.parseString(
				initParams.getProperty("monitorCron"), String.class));
		setNotes(ConfigurationHelper.parseString(
				initParams.getProperty("Notes"), java.lang.String.class));
	}

	public static void main(String[] args) {
		try {
			ApplicationDomain application = Interceptors
					.createApplicationDomain(
							CorporateActionsMonitorApplication.class,
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