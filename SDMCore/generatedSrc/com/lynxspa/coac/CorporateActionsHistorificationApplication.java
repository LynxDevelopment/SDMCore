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

@GeneratedType(definitionFile = "src/com/lynxspa/coac/CorporateActionsHistorification.fpmapplication")
public class CorporateActionsHistorificationApplication extends
		ApplicationDomain {

	public CorporateActionsHistorificationApplication(String id)
			throws Exception {
		super(id);
	}

	/*
	 * Parameters declarations
	 */
	private Integer deleteLogsCommitSize_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "deleteLogsCommitSize")
	public Integer getDeleteLogsCommitSize() {
		return deleteLogsCommitSize_;
	}

	public void setDeleteLogsCommitSize(Integer value) {
		this.deleteLogsCommitSize_ = value;
	}

	private String deleteLogsCronn_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "deleteLogsCronn")
	public String getDeleteLogsCronn() {
		return deleteLogsCronn_;
	}

	public void setDeleteLogsCronn(String value) {
		this.deleteLogsCronn_ = value;
	}

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

	private Integer historificationCommitSize_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "historificationCommitSize")
	public Integer getHistorificationCommitSize() {
		return historificationCommitSize_;
	}

	public void setHistorificationCommitSize(Integer value) {
		this.historificationCommitSize_ = value;
	}

	private String historificationCron_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "historificationCron")
	public String getHistorificationCron() {
		return historificationCron_;
	}

	public void setHistorificationCron(String value) {
		this.historificationCron_ = value;
	}

	private String locale_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "locale")
	public String getLocale() {
		return locale_;
	}

	public void setLocale(String value) {
		this.locale_ = value;
	}

	private Integer securityPortfolioHistorificationCommitSize_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "securityPortfolioHistorificationCommitSize")
	public Integer getSecurityPortfolioHistorificationCommitSize() {
		return securityPortfolioHistorificationCommitSize_;
	}

	public void setSecurityPortfolioHistorificationCommitSize(Integer value) {
		this.securityPortfolioHistorificationCommitSize_ = value;
	}

	private String securityPortfolioHistorificationCronn_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "securityPortfolioHistorificationCronn")
	public String getSecurityPortfolioHistorificationCronn() {
		return securityPortfolioHistorificationCronn_;
	}

	public void setSecurityPortfolioHistorificationCronn(String value) {
		this.securityPortfolioHistorificationCronn_ = value;
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
		// $-- /application/Delete Logs Event
		// instantiate and configure 'Delete Logs Event' event producer
		com.lynxspa.fpm.events.StandardEventProducer deleteLogsEventEventProducer = Interceptors
				.createEventProducer(
						com.lynxspa.fpm.events.StandardEventProducer.class,
						"Delete Logs Event");

		// add the newly created resource to domain
		this.addEventProducer(deleteLogsEventEventProducer);

		// $-- /application/Delete Logs Event/
		deleteLogsEventEventProducer.setAllowMultiThreading(false);
		// $-- /application/Delete Logs Event/
		// $-- /application/Delete Logs Event/
		deleteLogsEventEventProducer.setCronExpression(getDeleteLogsCronn());

		// link event producer to resources

		deleteLogsEventEventProducer.init();
		// $-- /application/Events Historification Event
		// instantiate and configure 'Events Historification Event' event producer
		com.lynxspa.fpm.events.StandardEventProducer eventsHistorificationEventEventProducer = Interceptors
				.createEventProducer(
						com.lynxspa.fpm.events.StandardEventProducer.class,
						"Events Historification Event");

		// add the newly created resource to domain
		this.addEventProducer(eventsHistorificationEventEventProducer);

		// $-- /application/Events Historification Event/
		eventsHistorificationEventEventProducer.setAllowMultiThreading(false);
		// $-- /application/Events Historification Event/
		// $-- /application/Events Historification Event/
		eventsHistorificationEventEventProducer
				.setCronExpression(getEventsHistorificationCron());

		// link event producer to resources

		eventsHistorificationEventEventProducer.init();
		// $-- /application/MessageHistorification event
		// instantiate and configure 'MessageHistorification event' event producer
		com.lynxspa.fpm.events.StandardEventProducer messageHistorificationEventEventProducer = Interceptors
				.createEventProducer(
						com.lynxspa.fpm.events.StandardEventProducer.class,
						"MessageHistorification event");

		// add the newly created resource to domain
		this.addEventProducer(messageHistorificationEventEventProducer);

		// $-- /application/MessageHistorification event/
		messageHistorificationEventEventProducer.setAllowMultiThreading(false);
		// $-- /application/MessageHistorification event/
		// $-- /application/MessageHistorification event/
		messageHistorificationEventEventProducer
				.setCronExpression(getHistorificationCron());

		// link event producer to resources

		messageHistorificationEventEventProducer.init();
		// $-- /application/SecurityPortfolio Historification Event
		// instantiate and configure 'SecurityPortfolio Historification Event' event producer
		com.lynxspa.fpm.events.StandardEventProducer securityPortfolioHistorificationEventEventProducer = Interceptors
				.createEventProducer(
						com.lynxspa.fpm.events.StandardEventProducer.class,
						"SecurityPortfolio Historification Event");

		// add the newly created resource to domain
		this.addEventProducer(securityPortfolioHistorificationEventEventProducer);

		// $-- /application/SecurityPortfolio Historification Event/
		securityPortfolioHistorificationEventEventProducer
				.setAllowMultiThreading(false);
		// $-- /application/SecurityPortfolio Historification Event/
		// $-- /application/SecurityPortfolio Historification Event/
		securityPortfolioHistorificationEventEventProducer
				.setCronExpression(getSecurityPortfolioHistorificationCronn());

		// link event producer to resources

		securityPortfolioHistorificationEventEventProducer.init();

		/*
		 * business processes section
		 */

		// $-- /application/DeletingLogsProcess
		// instantiate and configure 'DeletingLogsProcess' business process
		com.lynxspa.coac.historics.logs.businessprocess.DeletingLogsProcess deletingLogsProcessBusinessProcess = Interceptors
				.createBusinessProcess(
						com.lynxspa.coac.historics.logs.businessprocess.DeletingLogsProcess.class,
						"DeletingLogsProcess", 1);

		// add business process to the processes
		this.addBusinessProcess(deletingLogsProcessBusinessProcess);

		// $-- /application/DeletingLogsProcess/
		deletingLogsProcessBusinessProcess.setLocale(getLocale());
		// $-- /application/DeletingLogsProcess/
		// $-- /application/DeletingLogsProcess/
		deletingLogsProcessBusinessProcess
				.setLogsCommitSize(getDeleteLogsCommitSize());
		// $-- /application/DeletingLogsProcess/
		deletingLogsProcessBusinessProcess.setUser("DELETELOGS");

		// link process to resources
		// $-- /application/HibernateResource/resource/application/DeletingLogsProcess/sessionFactory
		deletingLogsProcessBusinessProcess
				.setSessionFactory(hibernateResource_);
		// $-- /application/HibernateStatelessResource/resource/application/DeletingLogsProcess/statelessSessionFactory
		deletingLogsProcessBusinessProcess
				.setStatelessSessionFactory(hibernateStatelessResource_);

		deletingLogsProcessBusinessProcess.initialize();

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
		errorHandlerBusinessProcess.setUser("HISTORIFICATIONHANDLER");

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
				.setEventsHistorificationCommitSize(getHistorificationCommitSize());
		// $-- /application/EventsHistorificationProcess/
		eventsHistorificationProcessBusinessProcess
				.setEventsHistorificationCron(getHistorificationCron());
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

		// $-- /application/Message Historication Process
		// instantiate and configure 'Message Historication Process' business process
		com.lynxspa.coac.historics.messages.businessprocess.HistorificationProcess messageHistoricationProcessBusinessProcess = Interceptors
				.createBusinessProcess(
						com.lynxspa.coac.historics.messages.businessprocess.HistorificationProcess.class,
						"Message Historication Process", 1);

		// add business process to the processes
		this.addBusinessProcess(messageHistoricationProcessBusinessProcess);

		// $-- /application/Message Historication Process/
		messageHistoricationProcessBusinessProcess.setLocale(getLocale());
		// $-- /application/Message Historication Process/
		messageHistoricationProcessBusinessProcess
				.setHistorificationCron(getHistorificationCron());
		// $-- /application/Message Historication Process/
		messageHistoricationProcessBusinessProcess
				.setHistorificationCommitSize(getHistorificationCommitSize());
		// $-- /application/Message Historication Process/
		// $-- /application/Message Historication Process/
		messageHistoricationProcessBusinessProcess
				.setUser("MESSAGESHISTORIFIER");

		// link process to resources
		// $-- /application/HibernateResource/resource/application/Message Historication Process/sessionFactory
		messageHistoricationProcessBusinessProcess
				.setSessionFactory(hibernateResource_);
		// $-- /application/HibernateStatelessResource/resource/application/Message Historication Process/statelessSessionFactory
		messageHistoricationProcessBusinessProcess
				.setStatelessSessionFactory(hibernateStatelessResource_);

		messageHistoricationProcessBusinessProcess.initialize();

		// $-- /application/SecurityPortfolio Historification Process
		// instantiate and configure 'SecurityPortfolio Historification Process' business process
		com.lynxspa.coac.historics.securityportfolio.businessprocess.SecurituPortfolioHistorificationProcess securityPortfolioHistorificationProcessBusinessProcess = Interceptors
				.createBusinessProcess(
						com.lynxspa.coac.historics.securityportfolio.businessprocess.SecurituPortfolioHistorificationProcess.class,
						"SecurityPortfolio Historification Process", 1);

		// add business process to the processes
		this.addBusinessProcess(securityPortfolioHistorificationProcessBusinessProcess);

		// $-- /application/SecurityPortfolio Historification Process/
		securityPortfolioHistorificationProcessBusinessProcess
				.setLocale(getLocale());
		// $-- /application/SecurityPortfolio Historification Process/
		securityPortfolioHistorificationProcessBusinessProcess
				.setHistorificationCommitSize(getSecurityPortfolioHistorificationCommitSize());
		// $-- /application/SecurityPortfolio Historification Process/
		// $-- /application/SecurityPortfolio Historification Process/
		securityPortfolioHistorificationProcessBusinessProcess
				.setUser("SECURITYPORTFOLIOHISTORIFIER");

		// link process to resources
		// $-- /application/HibernateResource/resource/application/SecurityPortfolio Historification Process/sessionFactory
		securityPortfolioHistorificationProcessBusinessProcess
				.setSessionFactory(hibernateResource_);
		// $-- /application/HibernateStatelessResource/resource/application/SecurityPortfolio Historification Process/statelessSessionFactory
		securityPortfolioHistorificationProcessBusinessProcess
				.setStatelessSessionFactory(hibernateStatelessResource_);

		securityPortfolioHistorificationProcessBusinessProcess.initialize();

		/*
		 * Link event producers to processes, sub-applications or outputs
		 */
		// $-- /application/Delete Logs Event/events/application/DeletingLogsProcess/input
		BusinessProcess.connect(deleteLogsEventEventProducer,
				deletingLogsProcessBusinessProcess.asInputInput());
		// $-- /application/Events Historification Event/events/application/EventsHistorificationProcess/input
		BusinessProcess.connect(eventsHistorificationEventEventProducer,
				eventsHistorificationProcessBusinessProcess.asInputInput());
		// $-- /application/MessageHistorification event/events/application/Message Historication Process/input
		BusinessProcess.connect(messageHistorificationEventEventProducer,
				messageHistoricationProcessBusinessProcess.asInputInput());
		// $-- /application/SecurityPortfolio Historification Event/events/application/SecurityPortfolio Historification Process/input
		BusinessProcess.connect(
				securityPortfolioHistorificationEventEventProducer,
				securityPortfolioHistorificationProcessBusinessProcess
						.asInputInput());

		/*
		 * Link inputs to processes, sub-applications or outputs
		 */

		/*
		 * Link processes to other processes, sub-applications or outputs
		 */
		// $-- /application/DeletingLogsProcess/exc/application/ErrorHandler/input
		BusinessProcess.connect(
				deletingLogsProcessBusinessProcess.asExcEventProducer(),
				errorHandlerBusinessProcess.asInputInput());
		// $-- /application/EventsHistorificationProcess/exc/application/ErrorHandler/input
		BusinessProcess.connect(eventsHistorificationProcessBusinessProcess
				.asExcEventProducer(), errorHandlerBusinessProcess
				.asInputInput());
		// $-- /application/Message Historication Process/exc/application/ErrorHandler/input
		BusinessProcess
				.connect(messageHistoricationProcessBusinessProcess
						.asExcEventProducer(), errorHandlerBusinessProcess
						.asInputInput());
		// $-- /application/SecurityPortfolio Historification Process/exc/application/ErrorHandler/input
		BusinessProcess.connect(
				securityPortfolioHistorificationProcessBusinessProcess
						.asExcEventProducer(), errorHandlerBusinessProcess
						.asInputInput());

		/*
		 * Link sub applications to other processes, sub-applications or outputs
		 */
	}

	protected void setInitParams() throws ParseException {
		Properties initParams = ConfigParams.getInstance().getProperties();

		setDeleteLogsCommitSize(ConfigurationHelper.parseString(
				initParams.getProperty("deleteLogsCommitSize"), Integer.class));
		setDeleteLogsCronn(ConfigurationHelper.parseString(
				initParams.getProperty("deleteLogsCronn"), String.class));
		setEventsHistorificationCommitSize(ConfigurationHelper.parseString(
				initParams.getProperty("eventsHistorificationCommitSize"),
				Integer.class));
		setEventsHistorificationCron(ConfigurationHelper.parseString(
				initParams.getProperty("eventsHistorificationCron"),
				String.class));
		setHistorificationCommitSize(ConfigurationHelper.parseString(
				initParams.getProperty("historificationCommitSize"),
				Integer.class));
		setHistorificationCron(ConfigurationHelper.parseString(
				initParams.getProperty("historificationCron"), String.class));
		setLocale(ConfigurationHelper.parseString(
				initParams.getProperty("locale"), String.class));
		setNotes(ConfigurationHelper.parseString(
				initParams.getProperty("Notes"), java.lang.String.class));
		setSecurityPortfolioHistorificationCommitSize(ConfigurationHelper
				.parseString(
						initParams
								.getProperty("securityPortfolioHistorificationCommitSize"),
						Integer.class));
		setSecurityPortfolioHistorificationCronn(ConfigurationHelper
				.parseString(initParams
						.getProperty("securityPortfolioHistorificationCronn"),
						String.class));
	}

	public static void main(String[] args) {
		try {
			ApplicationDomain application = Interceptors
					.createApplicationDomain(
							CorporateActionsHistorificationApplication.class,
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