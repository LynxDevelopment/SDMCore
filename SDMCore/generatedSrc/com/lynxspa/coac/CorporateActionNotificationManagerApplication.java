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

@GeneratedType(definitionFile = "src/com/lynxspa/coac/CorporateActionNotificationManager.fpmapplication")
public class CorporateActionNotificationManagerApplication extends
		ApplicationDomain {

	public CorporateActionNotificationManagerApplication(String id)
			throws Exception {
		super(id);
	}

	/*
	 * Parameters declarations
	 */
	private Integer evenDuration_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "evenDuration")
	public Integer getEvenDuration() {
		return evenDuration_;
	}

	public void setEvenDuration(Integer value) {
		this.evenDuration_ = value;
	}

	private Integer hourToBeginEven_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "hourToBeginEven")
	public Integer getHourToBeginEven() {
		return hourToBeginEven_;
	}

	public void setHourToBeginEven(Integer value) {
		this.hourToBeginEven_ = value;
	}

	private String locale_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "locale")
	public String getLocale() {
		return locale_;
	}

	public void setLocale(String value) {
		this.locale_ = value;
	}

	private Integer maxResultsToLoad_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "maxResultsToLoad")
	public Integer getMaxResultsToLoad() {
		return maxResultsToLoad_;
	}

	public void setMaxResultsToLoad(Integer value) {
		this.maxResultsToLoad_ = value;
	}

	private String notificationManagerEventCron_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "notificationManagerEventCron")
	public String getNotificationManagerEventCron() {
		return notificationManagerEventCron_;
	}

	public void setNotificationManagerEventCron(String value) {
		this.notificationManagerEventCron_ = value;
	}

	private Integer previosHoursForAlarm_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "previosHoursForAlarm")
	public Integer getPreviosHoursForAlarm() {
		return previosHoursForAlarm_;
	}

	public void setPreviosHoursForAlarm(Integer value) {
		this.previosHoursForAlarm_ = value;
	}

	private String smtpFrom_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "smtpFrom")
	public String getSmtpFrom() {
		return smtpFrom_;
	}

	public void setSmtpFrom(String value) {
		this.smtpFrom_ = value;
	}

	private String smtpHost_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "smtpHost")
	public String getSmtpHost() {
		return smtpHost_;
	}

	public void setSmtpHost(String value) {
		this.smtpHost_ = value;
	}

	private String smtpPassword_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "smtpPassword")
	public String getSmtpPassword() {
		return smtpPassword_;
	}

	public void setSmtpPassword(String value) {
		this.smtpPassword_ = value;
	}

	private String smtpPort_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "smtpPort")
	public String getSmtpPort() {
		return smtpPort_;
	}

	public void setSmtpPort(String value) {
		this.smtpPort_ = value;
	}

	private String smtpUser_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "smtpUser")
	public String getSmtpUser() {
		return smtpUser_;
	}

	public void setSmtpUser(String value) {
		this.smtpUser_ = value;
	}

	private String timeZone_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "timeZone")
	public String getTimeZone() {
		return timeZone_;
	}

	public void setTimeZone(String value) {
		this.timeZone_ = value;
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
				.setCronExpression(getNotificationManagerEventCron());

		// link event producer to resources

		standardEventProducerEventProducer.init();

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
		errorHandlerBusinessProcess.setUser("SENDTOBACKOFFICEERRORHANDLER");

		// link process to resources
		// $-- /application/HibernateResource/resource/application/ErrorHandler/sessionFactory
		errorHandlerBusinessProcess.setSessionFactory(hibernateResource_);

		errorHandlerBusinessProcess.initialize();

		// $-- /application/SendNotificationManagersProcess
		// instantiate and configure 'SendNotificationManagersProcess' business process
		com.lynxspa.coac.notificationmanagers.businessprocess.SendNotificationManagersProcess sendNotificationManagersProcessBusinessProcess = Interceptors
				.createBusinessProcess(
						com.lynxspa.coac.notificationmanagers.businessprocess.SendNotificationManagersProcess.class,
						"SendNotificationManagersProcess", 1);

		// add business process to the processes
		this.addBusinessProcess(sendNotificationManagersProcessBusinessProcess);

		// $-- /application/SendNotificationManagersProcess/
		sendNotificationManagersProcessBusinessProcess
				.setSmtpPort(getSmtpPort());
		// $-- /application/SendNotificationManagersProcess/
		sendNotificationManagersProcessBusinessProcess
				.setHourToBeginEven(getHourToBeginEven());
		// $-- /application/SendNotificationManagersProcess/
		sendNotificationManagersProcessBusinessProcess
				.setMaxResultsToLoad(getMaxResultsToLoad());
		// $-- /application/SendNotificationManagersProcess/
		sendNotificationManagersProcessBusinessProcess
				.setSmtpHost(getSmtpHost());
		// $-- /application/SendNotificationManagersProcess/
		sendNotificationManagersProcessBusinessProcess.setLocale(getLocale());
		// $-- /application/SendNotificationManagersProcess/
		sendNotificationManagersProcessBusinessProcess
				.setTimeZone(getTimeZone());
		// $-- /application/SendNotificationManagersProcess/
		sendNotificationManagersProcessBusinessProcess
				.setSmtpFrom(getSmtpFrom());
		// $-- /application/SendNotificationManagersProcess/
		sendNotificationManagersProcessBusinessProcess
				.setSmtpPassword(getSmtpPassword());
		// $-- /application/SendNotificationManagersProcess/
		sendNotificationManagersProcessBusinessProcess
				.setPreviosHoursForAlarm(getPreviosHoursForAlarm());
		// $-- /application/SendNotificationManagersProcess/
		sendNotificationManagersProcessBusinessProcess
				.setSmtpUser(getSmtpUser());
		// $-- /application/SendNotificationManagersProcess/
		// $-- /application/SendNotificationManagersProcess/
		sendNotificationManagersProcessBusinessProcess
				.setUser("NOTIFICATIONMANAGER");
		// $-- /application/SendNotificationManagersProcess/
		sendNotificationManagersProcessBusinessProcess
				.setEvenDuration(getEvenDuration());

		// link process to resources
		// $-- /application/HibernateResource/resource/application/SendNotificationManagersProcess/statefullSession
		sendNotificationManagersProcessBusinessProcess
				.setStatefullSession(hibernateResource_);
		// $-- /application/HibernateStatelessResource/resource/application/SendNotificationManagersProcess/statelessSession
		sendNotificationManagersProcessBusinessProcess
				.setStatelessSession(hibernateStatelessResource_);

		sendNotificationManagersProcessBusinessProcess.initialize();

		/*
		 * Link event producers to processes, sub-applications or outputs
		 */
		// $-- /application/StandardEventProducer/events/application/SendNotificationManagersProcess/input
		BusinessProcess.connect(standardEventProducerEventProducer,
				sendNotificationManagersProcessBusinessProcess.asInputInput());

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

		setEvenDuration(ConfigurationHelper.parseString(
				initParams.getProperty("evenDuration"), Integer.class));
		setHourToBeginEven(ConfigurationHelper.parseString(
				initParams.getProperty("hourToBeginEven"), Integer.class));
		setLocale(ConfigurationHelper.parseString(
				initParams.getProperty("locale"), String.class));
		setMaxResultsToLoad(ConfigurationHelper.parseString(
				initParams.getProperty("maxResultsToLoad"), Integer.class));
		setNotes(ConfigurationHelper.parseString(
				initParams.getProperty("Notes"), java.lang.String.class));
		setNotificationManagerEventCron(ConfigurationHelper.parseString(
				initParams.getProperty("notificationManagerEventCron"),
				String.class));
		setPreviosHoursForAlarm(ConfigurationHelper.parseString(
				initParams.getProperty("previosHoursForAlarm"), Integer.class));
		setSmtpFrom(ConfigurationHelper.parseString(
				initParams.getProperty("smtpFrom"), String.class));
		setSmtpHost(ConfigurationHelper.parseString(
				initParams.getProperty("smtpHost"), String.class));
		setSmtpPassword(ConfigurationHelper.parseString(
				initParams.getProperty("smtpPassword"), String.class));
		setSmtpPort(ConfigurationHelper.parseString(
				initParams.getProperty("smtpPort"), String.class));
		setSmtpUser(ConfigurationHelper.parseString(
				initParams.getProperty("smtpUser"), String.class));
		setTimeZone(ConfigurationHelper.parseString(
				initParams.getProperty("timeZone"), String.class));
	}

	public static void main(String[] args) {
		try {
			ApplicationDomain application = Interceptors
					.createApplicationDomain(
							CorporateActionNotificationManagerApplication.class,
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