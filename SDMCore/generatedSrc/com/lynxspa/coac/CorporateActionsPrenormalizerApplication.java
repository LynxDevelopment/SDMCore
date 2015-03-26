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

@GeneratedType(definitionFile = "src/com/lynxspa/coac/CorporateActionsPrenormalizer.fpmapplication")
public class CorporateActionsPrenormalizerApplication extends ApplicationDomain {

	public CorporateActionsPrenormalizerApplication(String id) throws Exception {
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

	private String prenormalizeCron_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "prenormalizeCron")
	public String getPrenormalizeCron() {
		return prenormalizeCron_;
	}

	public void setPrenormalizeCron(String value) {
		this.prenormalizeCron_ = value;
	}

	private java.lang.Integer prenormalizeSelectSize_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "prenormalizeSelectSize")
	public java.lang.Integer getPrenormalizeSelectSize() {
		return prenormalizeSelectSize_;
	}

	public void setPrenormalizeSelectSize(java.lang.Integer value) {
		this.prenormalizeSelectSize_ = value;
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
				.setCronExpression(getPrenormalizeCron());

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
		errorHandlerBusinessProcess.setUser("PRENORMALIZERERRORHANDLER");

		// link process to resources
		// $-- /application/HibernateResource/resource/application/ErrorHandler/sessionFactory
		errorHandlerBusinessProcess.setSessionFactory(hibernateResource_);

		errorHandlerBusinessProcess.initialize();

		// $-- /application/Prenormalize
		// instantiate and configure 'Prenormalize' business process
		com.lynxspa.coac.prenormalizer.businessprocess.PreNormalizerProcess prenormalizeBusinessProcess = Interceptors
				.createBusinessProcess(
						com.lynxspa.coac.prenormalizer.businessprocess.PreNormalizerProcess.class,
						"Prenormalize", 1);

		// add business process to the processes
		this.addBusinessProcess(prenormalizeBusinessProcess);

		// $-- /application/Prenormalize/
		prenormalizeBusinessProcess
				.setMaxResultsToLoad(getPrenormalizeSelectSize());
		// $-- /application/Prenormalize/
		prenormalizeBusinessProcess.setLocale(getLocale());
		// $-- /application/Prenormalize/
		// $-- /application/Prenormalize/
		prenormalizeBusinessProcess.setUser("COACPRENORMALIZER");

		// link process to resources
		// $-- /application/HibernateResource/resource/application/Prenormalize/statefullSession
		prenormalizeBusinessProcess.setStatefullSession(hibernateResource_);

		prenormalizeBusinessProcess.initialize();

		/*
		 * Link event producers to processes, sub-applications or outputs
		 */
		// $-- /application/StandardEventProducer/events/application/Prenormalize/input
		BusinessProcess.connect(standardEventProducerEventProducer,
				prenormalizeBusinessProcess.asInputInput());

		/*
		 * Link inputs to processes, sub-applications or outputs
		 */

		/*
		 * Link processes to other processes, sub-applications or outputs
		 */
		// $-- /application/Prenormalize/exc/application/ErrorHandler/input
		BusinessProcess.connect(
				prenormalizeBusinessProcess.asExcEventProducer(),
				errorHandlerBusinessProcess.asInputInput());

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
		setPrenormalizeCron(ConfigurationHelper.parseString(
				initParams.getProperty("prenormalizeCron"), String.class));
		setPrenormalizeSelectSize(ConfigurationHelper.parseString(
				initParams.getProperty("prenormalizeSelectSize"),
				java.lang.Integer.class));
	}

	public static void main(String[] args) {
		try {
			ApplicationDomain application = Interceptors
					.createApplicationDomain(
							CorporateActionsPrenormalizerApplication.class,
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