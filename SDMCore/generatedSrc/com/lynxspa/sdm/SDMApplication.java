package com.lynxspa.sdm;

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

@GeneratedType(definitionFile = "src/com/lynxspa/sdm/SDM.fpmapplication")
public class SDMApplication extends ApplicationDomain {

	public SDMApplication(String id) throws Exception {
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

	private java.io.File inputBloombergFieldsDirectory_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "inputBloombergFieldsDirectory")
	public java.io.File getInputBloombergFieldsDirectory() {
		return inputBloombergFieldsDirectory_;
	}

	public void setInputBloombergFieldsDirectory(java.io.File value) {
		this.inputBloombergFieldsDirectory_ = value;
	}

	private String inputBloombergFieldsPattern_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "inputBloombergFieldsPattern")
	public String getInputBloombergFieldsPattern() {
		return inputBloombergFieldsPattern_;
	}

	public void setInputBloombergFieldsPattern(String value) {
		this.inputBloombergFieldsPattern_ = value;
	}

	private String inputBloombergSecuritiesCronn_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "inputBloombergSecuritiesCronn")
	public String getInputBloombergSecuritiesCronn() {
		return inputBloombergSecuritiesCronn_;
	}

	public void setInputBloombergSecuritiesCronn(String value) {
		this.inputBloombergSecuritiesCronn_ = value;
	}

	private java.io.File inputBloombergSecuritiesDirectory_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "inputBloombergSecuritiesDirectory")
	public java.io.File getInputBloombergSecuritiesDirectory() {
		return inputBloombergSecuritiesDirectory_;
	}

	public void setInputBloombergSecuritiesDirectory(java.io.File value) {
		this.inputBloombergSecuritiesDirectory_ = value;
	}

	private String inputBloombergSecuritiesPattern_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "inputBloombergSecuritiesPattern")
	public String getInputBloombergSecuritiesPattern() {
		return inputBloombergSecuritiesPattern_;
	}

	public void setInputBloombergSecuritiesPattern(String value) {
		this.inputBloombergSecuritiesPattern_ = value;
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
		// $-- /application/ImportBloombergFieldsApplication
		// instantiate and configure 'ImportBloombergFieldsApplication' sub application
		com.lynxspa.sdm.importers.ImportBloombergFieldsApplication importBloombergFieldsApplicationApplication = Interceptors
				.createApplicationDomain(
						com.lynxspa.sdm.importers.ImportBloombergFieldsApplication.class,
						"ImportBloombergFieldsApplication");

		// add the newly created application to domain
		this.addSubApplication(importBloombergFieldsApplicationApplication);

		// $-- /application/ImportBloombergFieldsApplication/
		importBloombergFieldsApplicationApplication
				.setInputBloombergFieldsPattern(getInputBloombergFieldsPattern());
		// $-- /application/ImportBloombergFieldsApplication/
		importBloombergFieldsApplicationApplication
				.setInputBloombergFieldsDirectory(getInputBloombergFieldsDirectory());
		// $-- /application/ImportBloombergFieldsApplication/
		importBloombergFieldsApplicationApplication.setLocale(getLocale());
		// $-- /application/ImportBloombergFieldsApplication/
		importBloombergFieldsApplicationApplication
				.setInputTimeout(getInputTimeout());
		// $-- /application/ImportBloombergFieldsApplication/
		// $-- /application/ImportBloombergFieldsApplication/
		importBloombergFieldsApplicationApplication
				.setUser("IMPORTBLOOMBERGFIELDS");
		// $-- /application/ImportBloombergFieldsApplication/
		importBloombergFieldsApplicationApplication
				.setInputBloombergFieldsCronn(getInputBloombergFieldsCronn());

		// link sub application to resources
		// $-- /application/HibernateResource/resource/application/ImportBloombergFieldsApplication/hibernateResource
		importBloombergFieldsApplicationApplication
				.setHibernateResource(hibernateResource_);
		// $-- /application/HibernateStatelessResource/resource/application/ImportBloombergFieldsApplication/hibernateStatelessResource
		importBloombergFieldsApplicationApplication
				.setHibernateStatelessResource(hibernateStatelessResource_);

		importBloombergFieldsApplicationApplication.initialize();
		// $-- /application/ImportBloombergSecuritiesApplication
		// instantiate and configure 'ImportBloombergSecuritiesApplication' sub application
		com.lynxspa.sdm.importers.ImportBloombergSecuritiesApplication importBloombergSecuritiesApplicationApplication = Interceptors
				.createApplicationDomain(
						com.lynxspa.sdm.importers.ImportBloombergSecuritiesApplication.class,
						"ImportBloombergSecuritiesApplication");

		// add the newly created application to domain
		this.addSubApplication(importBloombergSecuritiesApplicationApplication);

		// $-- /application/ImportBloombergSecuritiesApplication/
		importBloombergSecuritiesApplicationApplication.setLocale(getLocale());
		// $-- /application/ImportBloombergSecuritiesApplication/
		importBloombergSecuritiesApplicationApplication
				.setInputBloombergSecuritiesCronn(getInputBloombergSecuritiesCronn());
		// $-- /application/ImportBloombergSecuritiesApplication/
		importBloombergSecuritiesApplicationApplication
				.setInputTimeout(getInputTimeout());
		// $-- /application/ImportBloombergSecuritiesApplication/
		importBloombergSecuritiesApplicationApplication
				.setInputBloombergSecuritiesDirectory(getInputBloombergSecuritiesDirectory());
		// $-- /application/ImportBloombergSecuritiesApplication/
		// $-- /application/ImportBloombergSecuritiesApplication/
		importBloombergSecuritiesApplicationApplication
				.setUser("IMPORTBLOOMBERGSECURITIES");
		// $-- /application/ImportBloombergSecuritiesApplication/
		importBloombergSecuritiesApplicationApplication
				.setInputBloombergSecuritiesPattern(getInputBloombergSecuritiesPattern());

		// link sub application to resources
		// $-- /application/HibernateResource/resource/application/ImportBloombergSecuritiesApplication/hibernateResource
		importBloombergSecuritiesApplicationApplication
				.setHibernateResource(hibernateResource_);
		// $-- /application/HibernateStatelessResource/resource/application/ImportBloombergSecuritiesApplication/hibernateStatelessResource
		importBloombergSecuritiesApplicationApplication
				.setHibernateStatelessResource(hibernateStatelessResource_);

		importBloombergSecuritiesApplicationApplication.initialize();
		// $-- /application/SDMNormalizerApplication
		// instantiate and configure 'SDMNormalizerApplication' sub application
		com.lynxspa.sdm.normalizer.SDMNormalizerApplication sDMNormalizerApplicationApplication = Interceptors
				.createApplicationDomain(
						com.lynxspa.sdm.normalizer.SDMNormalizerApplication.class,
						"SDMNormalizerApplication");

		// add the newly created application to domain
		this.addSubApplication(sDMNormalizerApplicationApplication);

		// $-- /application/SDMNormalizerApplication/
		sDMNormalizerApplicationApplication.setLocale(getLocale());
		// $-- /application/SDMNormalizerApplication/
		sDMNormalizerApplicationApplication.setInputTimeout(getInputTimeout());
		// $-- /application/SDMNormalizerApplication/
		// $-- /application/SDMNormalizerApplication/
		sDMNormalizerApplicationApplication.setUser("SDMNORMALIZER");
		// $-- /application/SDMNormalizerApplication/
		sDMNormalizerApplicationApplication
				.setInputBloombergFieldsCronn(getInputBloombergFieldsCronn());

		// link sub application to resources
		// $-- /application/HibernateResource/resource/application/SDMNormalizerApplication/hibernateResource
		sDMNormalizerApplicationApplication
				.setHibernateResource(hibernateResource_);
		// $-- /application/HibernateStatelessResource/resource/application/SDMNormalizerApplication/hibernateStatelessResource
		sDMNormalizerApplicationApplication
				.setHibernateStatelessResource(hibernateStatelessResource_);

		sDMNormalizerApplicationApplication.initialize();

		/*
		 * events section
		 */

		/*
		 * business processes section
		 */

		/*
		 * Link event producers to processes, sub-applications or outputs
		 */

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
		setInputBloombergFieldsDirectory(ConfigurationHelper.parseString(
				initParams.getProperty("inputBloombergFieldsDirectory"),
				java.io.File.class));
		setInputBloombergFieldsPattern(ConfigurationHelper.parseString(
				initParams.getProperty("inputBloombergFieldsPattern"),
				String.class));
		setInputBloombergSecuritiesCronn(ConfigurationHelper.parseString(
				initParams.getProperty("inputBloombergSecuritiesCronn"),
				String.class));
		setInputBloombergSecuritiesDirectory(ConfigurationHelper.parseString(
				initParams.getProperty("inputBloombergSecuritiesDirectory"),
				java.io.File.class));
		setInputBloombergSecuritiesPattern(ConfigurationHelper.parseString(
				initParams.getProperty("inputBloombergSecuritiesPattern"),
				String.class));
		setInputTimeout(ConfigurationHelper.parseString(
				initParams.getProperty("inputTimeout"), Integer.class));
		setLocale(ConfigurationHelper.parseString(
				initParams.getProperty("locale"), String.class));
		setNotes(ConfigurationHelper.parseString(
				initParams.getProperty("Notes"), java.lang.String.class));
	}

	public static void main(String[] args) {
		try {
			ApplicationDomain application = Interceptors
					.createApplicationDomain(SDMApplication.class,
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