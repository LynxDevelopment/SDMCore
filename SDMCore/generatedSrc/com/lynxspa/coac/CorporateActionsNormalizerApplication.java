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

@GeneratedType(definitionFile = "src/com/lynxspa/coac/CorporateActionsNormalizer.fpmapplication")
public class CorporateActionsNormalizerApplication extends ApplicationDomain {

	public CorporateActionsNormalizerApplication(String id) throws Exception {
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

	private String normalizeCron_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "normalizeCron")
	public String getNormalizeCron() {
		return normalizeCron_;
	}

	public void setNormalizeCron(String value) {
		this.normalizeCron_ = value;
	}

	private Integer normalizeSelectSize_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "normalizeSelectSize")
	public Integer getNormalizeSelectSize() {
		return normalizeSelectSize_;
	}

	public void setNormalizeSelectSize(Integer value) {
		this.normalizeSelectSize_ = value;
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
				.setCronExpression(getNormalizeCron());

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
		errorHandlerBusinessProcess.setUser("NORMALIZERERRORHANDLER");

		// link process to resources
		// $-- /application/HibernateResource/resource/application/ErrorHandler/sessionFactory
		errorHandlerBusinessProcess.setSessionFactory(hibernateResource_);

		errorHandlerBusinessProcess.initialize();

		// $-- /application/Normalizer
		// instantiate and configure 'Normalizer' business process
		com.lynxspa.coac.normalizer.businessprocess.NormalizerProcess normalizerBusinessProcess = Interceptors
				.createBusinessProcess(
						com.lynxspa.coac.normalizer.businessprocess.NormalizerProcess.class,
						"Normalizer", 1);

		// add business process to the processes
		this.addBusinessProcess(normalizerBusinessProcess);

		// $-- /application/Normalizer/
		normalizerBusinessProcess.setMaxResultsToLoad(getNormalizeSelectSize());
		// $-- /application/Normalizer/
		normalizerBusinessProcess.setLocale(getLocale());
		// $-- /application/Normalizer/
		// $-- /application/Normalizer/
		normalizerBusinessProcess.setUser("COACNORMALIZER");

		// link process to resources
		// $-- /application/HibernateResource/resource/application/Normalizer/statefullSession
		normalizerBusinessProcess.setStatefullSession(hibernateResource_);

		normalizerBusinessProcess.initialize();

		/*
		 * Link event producers to processes, sub-applications or outputs
		 */
		// $-- /application/StandardEventProducer/events/application/Normalizer/input
		BusinessProcess.connect(standardEventProducerEventProducer,
				normalizerBusinessProcess.asInputInput());

		/*
		 * Link inputs to processes, sub-applications or outputs
		 */

		/*
		 * Link processes to other processes, sub-applications or outputs
		 */
		// $-- /application/Normalizer/exc/application/ErrorHandler/input
		BusinessProcess.connect(normalizerBusinessProcess.asExcEventProducer(),
				errorHandlerBusinessProcess.asInputInput());

		/*
		 * Link sub applications to other processes, sub-applications or outputs
		 */
	}

	protected void setInitParams() throws ParseException {
		Properties initParams = ConfigParams.getInstance().getProperties();

		setLocale(ConfigurationHelper.parseString(
				initParams.getProperty("locale"), String.class));
		setNormalizeCron(ConfigurationHelper.parseString(
				initParams.getProperty("normalizeCron"), String.class));
		setNormalizeSelectSize(ConfigurationHelper.parseString(
				initParams.getProperty("normalizeSelectSize"), Integer.class));
		setNotes(ConfigurationHelper.parseString(
				initParams.getProperty("Notes"), java.lang.String.class));
	}

	public static void main(String[] args) {
		try {
			ApplicationDomain application = Interceptors
					.createApplicationDomain(
							CorporateActionsNormalizerApplication.class,
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