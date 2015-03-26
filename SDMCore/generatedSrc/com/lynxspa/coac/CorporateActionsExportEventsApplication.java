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

@GeneratedType(definitionFile = "src/com/lynxspa/coac/CorporateActionsExportEvents.fpmapplication")
public class CorporateActionsExportEventsApplication extends ApplicationDomain {

	public CorporateActionsExportEventsApplication(String id) throws Exception {
		super(id);
	}

	/*
	 * Parameters declarations
	 */
	private String exportEventCron_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "exportEventCron")
	public String getExportEventCron() {
		return exportEventCron_;
	}

	public void setExportEventCron(String value) {
		this.exportEventCron_ = value;
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
				.setCronExpression(getExportEventCron());

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

		// $-- /application/ExportEventsProcess
		// instantiate and configure 'ExportEventsProcess' business process
		com.lynxspa.coac.exportevents.businesprocess.ExportEventsProcess exportEventsProcessBusinessProcess = Interceptors
				.createBusinessProcess(
						com.lynxspa.coac.exportevents.businesprocess.ExportEventsProcess.class,
						"ExportEventsProcess", 1);

		// add business process to the processes
		this.addBusinessProcess(exportEventsProcessBusinessProcess);

		// $-- /application/ExportEventsProcess/
		exportEventsProcessBusinessProcess
				.setMaxResultsToLoad(getMaxResultsToLoad());
		// $-- /application/ExportEventsProcess/
		exportEventsProcessBusinessProcess.setLocale(getLocale());
		// $-- /application/ExportEventsProcess/
		// $-- /application/ExportEventsProcess/
		exportEventsProcessBusinessProcess.setUser("EXPORTEVENTS");

		// link process to resources
		// $-- /application/HibernateResource/resource/application/ExportEventsProcess/statefullSession
		exportEventsProcessBusinessProcess
				.setStatefullSession(hibernateResource_);

		exportEventsProcessBusinessProcess.initialize();

		/*
		 * Link event producers to processes, sub-applications or outputs
		 */
		// $-- /application/StandardEventProducer/events/application/ExportEventsProcess/input
		BusinessProcess.connect(standardEventProducerEventProducer,
				exportEventsProcessBusinessProcess.asInputInput());

		/*
		 * Link inputs to processes, sub-applications or outputs
		 */

		/*
		 * Link processes to other processes, sub-applications or outputs
		 */
		// $-- /application/ExportEventsProcess/exc/application/ErrorHandler/input
		BusinessProcess.connect(
				exportEventsProcessBusinessProcess.asExcEventProducer(),
				errorHandlerBusinessProcess.asInputInput());

		/*
		 * Link sub applications to other processes, sub-applications or outputs
		 */
	}

	protected void setInitParams() throws ParseException {
		Properties initParams = ConfigParams.getInstance().getProperties();

		setExportEventCron(ConfigurationHelper.parseString(
				initParams.getProperty("exportEventCron"), String.class));
		setLocale(ConfigurationHelper.parseString(
				initParams.getProperty("locale"), String.class));
		setMaxResultsToLoad(ConfigurationHelper.parseString(
				initParams.getProperty("maxResultsToLoad"), Integer.class));
		setNotes(ConfigurationHelper.parseString(
				initParams.getProperty("Notes"), java.lang.String.class));
	}

	public static void main(String[] args) {
		try {
			ApplicationDomain application = Interceptors
					.createApplicationDomain(
							CorporateActionsExportEventsApplication.class,
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