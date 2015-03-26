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

@GeneratedType(definitionFile = "src/com/lynxspa/coac/CorporateActionEventsHistorification.fpmapplication")
public class CorporateActionEventsHistorificationApplication extends
		ApplicationDomain {

	public CorporateActionEventsHistorificationApplication(String id)
			throws Exception {
		super(id);
	}

	/*
	 * Parameters declarations
	 */
	private Integer eventsHistorificationCommitSize_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "eventsHistorificationCommitSize")
	public Integer getEventsHistorificationCommitSize() {
		return eventsHistorificationCommitSize_;
	}

	public void setEventsHistorificationCommitSize(Integer value) {
		this.eventsHistorificationCommitSize_ = value;
	}

	private String eventsHistorificationCron_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "eventsHistorificationCron")
	public String getEventsHistorificationCron() {
		return eventsHistorificationCron_;
	}

	public void setEventsHistorificationCron(String value) {
		this.eventsHistorificationCron_ = value;
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
		// $-- /application/event
		// instantiate and configure 'event' event producer
		com.lynxspa.fpm.events.StandardEventProducer eventEventProducer = Interceptors
				.createEventProducer(
						com.lynxspa.fpm.events.StandardEventProducer.class,
						"event");

		// add the newly created resource to domain
		this.addEventProducer(eventEventProducer);

		// $-- /application/event/
		eventEventProducer.setAllowMultiThreading(false);
		// $-- /application/event/
		// $-- /application/event/
		eventEventProducer.setCronExpression(getEventsHistorificationCron());

		// link event producer to resources

		eventEventProducer.init();

		/*
		 * business processes section
		 */

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
		errorHandlerBusinessProcess.setUser("EVENTHISTORIFERRORHANDLER");

		// link process to resources
		// $-- /application/HibernateResource/resource/application/ErrorHandler/sessionFactory
		errorHandlerBusinessProcess.setSessionFactory(hibernateResource_);

		errorHandlerBusinessProcess.initialize();

		// $-- /application/EventsHistorificationProcess
		// instantiate and configure 'EventsHistorificationProcess' business process
		com.lynxspa.coac.historics.events.businessprocess.EventsHistorificationProcess eventsHistorificationProcessBusinessProcess = Interceptors
				.createBusinessProcess(
						com.lynxspa.coac.historics.events.businessprocess.EventsHistorificationProcess.class,
						"EventsHistorificationProcess", 1);

		// add business process to the processes
		this.addBusinessProcess(eventsHistorificationProcessBusinessProcess);

		// $-- /application/EventsHistorificationProcess/
		eventsHistorificationProcessBusinessProcess.setLocale(getLocale());
		// $-- /application/EventsHistorificationProcess/
		eventsHistorificationProcessBusinessProcess
				.setEventsHistorificationCommitSize(getEventsHistorificationCommitSize());
		// $-- /application/EventsHistorificationProcess/
		eventsHistorificationProcessBusinessProcess
				.setEventsHistorificationCron(getEventsHistorificationCron());
		// $-- /application/EventsHistorificationProcess/
		// $-- /application/EventsHistorificationProcess/
		eventsHistorificationProcessBusinessProcess
				.setUser("EVENTSHISTORIFIER");

		// link process to resources
		// $-- /application/HibernateResource/resource/application/EventsHistorificationProcess/sessionFactory
		eventsHistorificationProcessBusinessProcess
				.setSessionFactory(hibernateResource_);
		// $-- /application/HibernateStatelessResource/resource/application/EventsHistorificationProcess/statelessSessionFactory
		eventsHistorificationProcessBusinessProcess
				.setStatelessSessionFactory(hibernateStatelessResource_);

		eventsHistorificationProcessBusinessProcess.initialize();

		/*
		 * Link event producers to processes, sub-applications or outputs
		 */
		// $-- /application/event/events/application/EventsHistorificationProcess/input
		BusinessProcess.connect(eventEventProducer,
				eventsHistorificationProcessBusinessProcess.asInputInput());

		/*
		 * Link inputs to processes, sub-applications or outputs
		 */

		/*
		 * Link processes to other processes, sub-applications or outputs
		 */
		// $-- /application/EventsHistorificationProcess/exc/application/ErrorHandler/input
		BusinessProcess.connect(eventsHistorificationProcessBusinessProcess
				.asExcEventProducer(), errorHandlerBusinessProcess
				.asInputInput());

		/*
		 * Link sub applications to other processes, sub-applications or outputs
		 */
	}

	protected void setInitParams() throws ParseException {
		Properties initParams = ConfigParams.getInstance().getProperties();

		setEventsHistorificationCommitSize(ConfigurationHelper.parseString(
				initParams.getProperty("eventsHistorificationCommitSize"),
				Integer.class));
		setEventsHistorificationCron(ConfigurationHelper.parseString(
				initParams.getProperty("eventsHistorificationCron"),
				String.class));
		setLocale(ConfigurationHelper.parseString(
				initParams.getProperty("locale"), String.class));
		setNotes(ConfigurationHelper.parseString(
				initParams.getProperty("Notes"), java.lang.String.class));
	}

	public static void main(String[] args) {
		try {
			ApplicationDomain application = Interceptors
					.createApplicationDomain(
							CorporateActionEventsHistorificationApplication.class,
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