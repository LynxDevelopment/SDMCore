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

@GeneratedType(definitionFile = "src/com/lynxspa/coac/CorporateActionsImporter.fpmapplication")
public class CorporateActionsImporterApplication extends ApplicationDomain {

	public CorporateActionsImporterApplication(String id) throws Exception {
		super(id);
	}

	/*
	 * Parameters declarations
	 */
	private Integer inputBloombergCommitSize_;

	@ConfigParam(description = "inputBloombergCommitSize", required = true, dynamic = false, name = "inputBloombergCommitSize")
	public Integer getInputBloombergCommitSize() {
		return inputBloombergCommitSize_;
	}

	public void setInputBloombergCommitSize(Integer value) {
		this.inputBloombergCommitSize_ = value;
	}

	private String inputBloombergCron_;

	@ConfigParam(description = "inputBloombergCron", required = true, dynamic = false, name = "inputBloombergCron")
	public String getInputBloombergCron() {
		return inputBloombergCron_;
	}

	public void setInputBloombergCron(String value) {
		this.inputBloombergCron_ = value;
	}

	private java.io.File inputBloombergDirectory_;

	@ConfigParam(description = "inputBloombergDirectory", required = true, dynamic = false, name = "inputBloombergDirectory")
	public java.io.File getInputBloombergDirectory() {
		return inputBloombergDirectory_;
	}

	public void setInputBloombergDirectory(java.io.File value) {
		this.inputBloombergDirectory_ = value;
	}

	private java.io.File inputBloombergErrorDirectory_;

	@ConfigParam(description = "inputBloombergErrorDirectory", required = true, dynamic = false, name = "inputBloombergErrorDirectory")
	public java.io.File getInputBloombergErrorDirectory() {
		return inputBloombergErrorDirectory_;
	}

	public void setInputBloombergErrorDirectory(java.io.File value) {
		this.inputBloombergErrorDirectory_ = value;
	}

	private String inputBloombergPattern_;

	@ConfigParam(description = "inputBloombergPattern", required = true, dynamic = false, name = "inputBloombergPattern")
	public String getInputBloombergPattern() {
		return inputBloombergPattern_;
	}

	public void setInputBloombergPattern(String value) {
		this.inputBloombergPattern_ = value;
	}

	private Integer inputBloombergPerSecCommitSize_;

	@ConfigParam(description = "inputBloombergPerSecCommitSize", required = true, dynamic = false, name = "inputBloombergPerSecCommitSize")
	public Integer getInputBloombergPerSecCommitSize() {
		return inputBloombergPerSecCommitSize_;
	}

	public void setInputBloombergPerSecCommitSize(Integer value) {
		this.inputBloombergPerSecCommitSize_ = value;
	}

	private Integer inputBloombergPerSecCorporatesCommitSize_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "inputBloombergPerSecCorporatesCommitSize")
	public Integer getInputBloombergPerSecCorporatesCommitSize() {
		return inputBloombergPerSecCorporatesCommitSize_;
	}

	public void setInputBloombergPerSecCorporatesCommitSize(Integer value) {
		this.inputBloombergPerSecCorporatesCommitSize_ = value;
	}

	private String inputBloombergPerSecCorporatesCron_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "inputBloombergPerSecCorporatesCron")
	public String getInputBloombergPerSecCorporatesCron() {
		return inputBloombergPerSecCorporatesCron_;
	}

	public void setInputBloombergPerSecCorporatesCron(String value) {
		this.inputBloombergPerSecCorporatesCron_ = value;
	}

	private java.io.File inputBloombergPerSecCorporatesDirectory_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "inputBloombergPerSecCorporatesDirectory")
	public java.io.File getInputBloombergPerSecCorporatesDirectory() {
		return inputBloombergPerSecCorporatesDirectory_;
	}

	public void setInputBloombergPerSecCorporatesDirectory(java.io.File value) {
		this.inputBloombergPerSecCorporatesDirectory_ = value;
	}

	private java.io.File inputBloombergPerSecCorporatesErrorDirectory_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "inputBloombergPerSecCorporatesErrorDirectory")
	public java.io.File getInputBloombergPerSecCorporatesErrorDirectory() {
		return inputBloombergPerSecCorporatesErrorDirectory_;
	}

	public void setInputBloombergPerSecCorporatesErrorDirectory(
			java.io.File value) {
		this.inputBloombergPerSecCorporatesErrorDirectory_ = value;
	}

	private String inputBloombergPerSecCorporatesPattern_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "inputBloombergPerSecCorporatesPattern")
	public String getInputBloombergPerSecCorporatesPattern() {
		return inputBloombergPerSecCorporatesPattern_;
	}

	public void setInputBloombergPerSecCorporatesPattern(String value) {
		this.inputBloombergPerSecCorporatesPattern_ = value;
	}

	private String inputBloombergPerSecCron_;

	@ConfigParam(description = "inputBloombergPerSecCron", required = true, dynamic = false, name = "inputBloombergPerSecCron")
	public String getInputBloombergPerSecCron() {
		return inputBloombergPerSecCron_;
	}

	public void setInputBloombergPerSecCron(String value) {
		this.inputBloombergPerSecCron_ = value;
	}

	private java.io.File inputBloombergPerSecDirectory_;

	@ConfigParam(description = "inputBloombergPerSecDirectory", required = true, dynamic = false, name = "inputBloombergPerSecDirectory")
	public java.io.File getInputBloombergPerSecDirectory() {
		return inputBloombergPerSecDirectory_;
	}

	public void setInputBloombergPerSecDirectory(java.io.File value) {
		this.inputBloombergPerSecDirectory_ = value;
	}

	private java.io.File inputBloombergPerSecErrorDirectory_;

	@ConfigParam(description = "inputBloombergPerSecErrorDirectory", required = true, dynamic = false, name = "inputBloombergPerSecErrorDirectory")
	public java.io.File getInputBloombergPerSecErrorDirectory() {
		return inputBloombergPerSecErrorDirectory_;
	}

	public void setInputBloombergPerSecErrorDirectory(java.io.File value) {
		this.inputBloombergPerSecErrorDirectory_ = value;
	}

	private String inputBloombergPerSecPattern_;

	@ConfigParam(description = "inputBloombergPerSecPattern", required = true, dynamic = false, name = "inputBloombergPerSecPattern")
	public String getInputBloombergPerSecPattern() {
		return inputBloombergPerSecPattern_;
	}

	public void setInputBloombergPerSecPattern(String value) {
		this.inputBloombergPerSecPattern_ = value;
	}

	private Integer inputIberclearCommitSize_;

	@ConfigParam(description = "inputIberclearCommitSize", required = true, dynamic = false, name = "inputIberclearCommitSize")
	public Integer getInputIberclearCommitSize() {
		return inputIberclearCommitSize_;
	}

	public void setInputIberclearCommitSize(Integer value) {
		this.inputIberclearCommitSize_ = value;
	}

	private String inputIberclearCron_;

	@ConfigParam(description = "inputIberclearCron", required = true, dynamic = false, name = "inputIberclearCron")
	public String getInputIberclearCron() {
		return inputIberclearCron_;
	}

	public void setInputIberclearCron(String value) {
		this.inputIberclearCron_ = value;
	}

	private java.io.File inputIberclearDirectory_;

	@ConfigParam(description = "inputIberclearDirectory", required = true, dynamic = false, name = "inputIberclearDirectory")
	public java.io.File getInputIberclearDirectory() {
		return inputIberclearDirectory_;
	}

	public void setInputIberclearDirectory(java.io.File value) {
		this.inputIberclearDirectory_ = value;
	}

	private java.io.File inputIberclearErrorDirectory_;

	@ConfigParam(description = "inputIberclearErrorDirectory", required = true, dynamic = false, name = "inputIberclearErrorDirectory")
	public java.io.File getInputIberclearErrorDirectory() {
		return inputIberclearErrorDirectory_;
	}

	public void setInputIberclearErrorDirectory(java.io.File value) {
		this.inputIberclearErrorDirectory_ = value;
	}

	private String inputIberclearPattern_;

	@ConfigParam(description = "inputIberclearPattern", required = true, dynamic = false, name = "inputIberclearPattern")
	public String getInputIberclearPattern() {
		return inputIberclearPattern_;
	}

	public void setInputIberclearPattern(String value) {
		this.inputIberclearPattern_ = value;
	}

	private Integer inputInversisCommitSize_;

	@ConfigParam(description = "inputInversisCommitSize", required = true, dynamic = false, name = "inputInversisCommitSize")
	public Integer getInputInversisCommitSize() {
		return inputInversisCommitSize_;
	}

	public void setInputInversisCommitSize(Integer value) {
		this.inputInversisCommitSize_ = value;
	}

	private String inputInversisCron_;

	@ConfigParam(description = "inputInversisCron", required = true, dynamic = false, name = "inputInversisCron")
	public String getInputInversisCron() {
		return inputInversisCron_;
	}

	public void setInputInversisCron(String value) {
		this.inputInversisCron_ = value;
	}

	private java.io.File inputInversisDirectory_;

	@ConfigParam(description = "inputInversisDirectory", required = true, dynamic = false, name = "inputInversisDirectory")
	public java.io.File getInputInversisDirectory() {
		return inputInversisDirectory_;
	}

	public void setInputInversisDirectory(java.io.File value) {
		this.inputInversisDirectory_ = value;
	}

	private java.io.File inputInversisErrorDirectory_;

	@ConfigParam(description = "inputInversisErrorDirectory", required = true, dynamic = false, name = "inputInversisErrorDirectory")
	public java.io.File getInputInversisErrorDirectory() {
		return inputInversisErrorDirectory_;
	}

	public void setInputInversisErrorDirectory(java.io.File value) {
		this.inputInversisErrorDirectory_ = value;
	}

	private String inputInversisPattern_;

	@ConfigParam(description = "inputInversisPattern", required = true, dynamic = false, name = "inputInversisPattern")
	public String getInputInversisPattern() {
		return inputInversisPattern_;
	}

	public void setInputInversisPattern(String value) {
		this.inputInversisPattern_ = value;
	}

	private Integer inputOfivalCommitSize_;

	@ConfigParam(description = "inputOfivalCommitSize", required = true, dynamic = false, name = "inputOfivalCommitSize")
	public Integer getInputOfivalCommitSize() {
		return inputOfivalCommitSize_;
	}

	public void setInputOfivalCommitSize(Integer value) {
		this.inputOfivalCommitSize_ = value;
	}

	private String inputOfivalCron_;

	@ConfigParam(description = "inputOfivalCron", required = true, dynamic = false, name = "inputOfivalCron")
	public String getInputOfivalCron() {
		return inputOfivalCron_;
	}

	public void setInputOfivalCron(String value) {
		this.inputOfivalCron_ = value;
	}

	private java.io.File inputOfivalDirectory_;

	@ConfigParam(description = "inputOfivalDirectory", required = true, dynamic = false, name = "inputOfivalDirectory")
	public java.io.File getInputOfivalDirectory() {
		return inputOfivalDirectory_;
	}

	public void setInputOfivalDirectory(java.io.File value) {
		this.inputOfivalDirectory_ = value;
	}

	private java.io.File inputOfivalErrorDirectory_;

	@ConfigParam(description = "inputOfivalErrorDirectory", required = true, dynamic = false, name = "inputOfivalErrorDirectory")
	public java.io.File getInputOfivalErrorDirectory() {
		return inputOfivalErrorDirectory_;
	}

	public void setInputOfivalErrorDirectory(java.io.File value) {
		this.inputOfivalErrorDirectory_ = value;
	}

	private String inputOfivalPattern_;

	@ConfigParam(description = "inputOfivalPattern", required = true, dynamic = false, name = "inputOfivalPattern")
	public String getInputOfivalPattern() {
		return inputOfivalPattern_;
	}

	public void setInputOfivalPattern(String value) {
		this.inputOfivalPattern_ = value;
	}

	private Integer inputSwiftCommitSize_;

	@ConfigParam(description = "inputSwiftCommitSize", required = true, dynamic = false, name = "inputSwiftCommitSize")
	public Integer getInputSwiftCommitSize() {
		return inputSwiftCommitSize_;
	}

	public void setInputSwiftCommitSize(Integer value) {
		this.inputSwiftCommitSize_ = value;
	}

	private String inputSwiftCron_;

	@ConfigParam(description = "inputSwiftCron", required = true, dynamic = false, name = "inputSwiftCron")
	public String getInputSwiftCron() {
		return inputSwiftCron_;
	}

	public void setInputSwiftCron(String value) {
		this.inputSwiftCron_ = value;
	}

	private java.io.File inputSwiftDirectory_;

	@ConfigParam(description = "inputSwiftDirectory", required = true, dynamic = false, name = "inputSwiftDirectory")
	public java.io.File getInputSwiftDirectory() {
		return inputSwiftDirectory_;
	}

	public void setInputSwiftDirectory(java.io.File value) {
		this.inputSwiftDirectory_ = value;
	}

	private java.io.File inputSwiftErrorDirectory_;

	@ConfigParam(description = "inputSwiftErrorDirectory", required = true, dynamic = false, name = "inputSwiftErrorDirectory")
	public java.io.File getInputSwiftErrorDirectory() {
		return inputSwiftErrorDirectory_;
	}

	public void setInputSwiftErrorDirectory(java.io.File value) {
		this.inputSwiftErrorDirectory_ = value;
	}

	private String inputSwiftPattern_;

	@ConfigParam(description = "inputSwiftPattern", required = true, dynamic = false, name = "inputSwiftPattern")
	public String getInputSwiftPattern() {
		return inputSwiftPattern_;
	}

	public void setInputSwiftPattern(String value) {
		this.inputSwiftPattern_ = value;
	}

	private Integer inputTimeout_;

	@ConfigParam(description = "inputTimeout", required = true, dynamic = false, name = "inputTimeout")
	public Integer getInputTimeout() {
		return inputTimeout_;
	}

	public void setInputTimeout(Integer value) {
		this.inputTimeout_ = value;
	}

	private String locale_;

	@ConfigParam(description = "locale", required = true, dynamic = false, name = "locale")
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
		// $-- /application/BloombergFileCreated
		// instantiate and configure 'BloombergFileCreated' event producer
		com.lynxspa.fpm.events.StandardFileCreatedEventproducer bloombergFileCreatedEventProducer = Interceptors
				.createEventProducer(
						com.lynxspa.fpm.events.StandardFileCreatedEventproducer.class,
						"BloombergFileCreated");

		// add the newly created resource to domain
		this.addEventProducer(bloombergFileCreatedEventProducer);

		// $-- /application/BloombergFileCreated/
		bloombergFileCreatedEventProducer
				.setInputDirectory(getInputBloombergDirectory());
		// $-- /application/BloombergFileCreated/
		bloombergFileCreatedEventProducer.setWaitTimeout(getInputTimeout());
		// $-- /application/BloombergFileCreated/
		bloombergFileCreatedEventProducer
				.setPattern(getInputBloombergPattern());
		// $-- /application/BloombergFileCreated/
		bloombergFileCreatedEventProducer.setIncludeSubdirectories(false);
		// $-- /application/BloombergFileCreated/
		bloombergFileCreatedEventProducer.setCaseSensitivePattern(true);
		// $-- /application/BloombergFileCreated/
		bloombergFileCreatedEventProducer.setDetectAlreadyExistentFiles(true);
		// $-- /application/BloombergFileCreated/
		bloombergFileCreatedEventProducer
				.setCronExpression(getInputBloombergCron());
		// $-- /application/BloombergFileCreated/
		// $-- /application/BloombergFileCreated/
		bloombergFileCreatedEventProducer.setAllowMultiThreading(false);

		// link event producer to resources

		bloombergFileCreatedEventProducer.init();
		// $-- /application/BloombergPerSecurityCorporateFileCreated
		// instantiate and configure 'BloombergPerSecurityCorporateFileCreated' event producer
		com.lynxspa.fpm.events.StandardFileCreatedEventproducer bloombergPerSecurityCorporateFileCreatedEventProducer = Interceptors
				.createEventProducer(
						com.lynxspa.fpm.events.StandardFileCreatedEventproducer.class,
						"BloombergPerSecurityCorporateFileCreated");

		// add the newly created resource to domain
		this.addEventProducer(bloombergPerSecurityCorporateFileCreatedEventProducer);

		// $-- /application/BloombergPerSecurityCorporateFileCreated/
		bloombergPerSecurityCorporateFileCreatedEventProducer
				.setInputDirectory(getInputBloombergPerSecCorporatesDirectory());
		// $-- /application/BloombergPerSecurityCorporateFileCreated/
		bloombergPerSecurityCorporateFileCreatedEventProducer
				.setWaitTimeout(getInputTimeout());
		// $-- /application/BloombergPerSecurityCorporateFileCreated/
		bloombergPerSecurityCorporateFileCreatedEventProducer
				.setPattern(getInputBloombergPerSecCorporatesPattern());
		// $-- /application/BloombergPerSecurityCorporateFileCreated/
		bloombergPerSecurityCorporateFileCreatedEventProducer
				.setIncludeSubdirectories(false);
		// $-- /application/BloombergPerSecurityCorporateFileCreated/
		bloombergPerSecurityCorporateFileCreatedEventProducer
				.setCaseSensitivePattern(true);
		// $-- /application/BloombergPerSecurityCorporateFileCreated/
		bloombergPerSecurityCorporateFileCreatedEventProducer
				.setDetectAlreadyExistentFiles(true);
		// $-- /application/BloombergPerSecurityCorporateFileCreated/
		bloombergPerSecurityCorporateFileCreatedEventProducer
				.setCronExpression(getInputBloombergPerSecCorporatesCron());
		// $-- /application/BloombergPerSecurityCorporateFileCreated/
		// $-- /application/BloombergPerSecurityCorporateFileCreated/
		bloombergPerSecurityCorporateFileCreatedEventProducer
				.setAllowMultiThreading(false);

		// link event producer to resources

		bloombergPerSecurityCorporateFileCreatedEventProducer.init();
		// $-- /application/BloombergPerSecurityFileCreated
		// instantiate and configure 'BloombergPerSecurityFileCreated' event producer
		com.lynxspa.fpm.events.StandardFileCreatedEventproducer bloombergPerSecurityFileCreatedEventProducer = Interceptors
				.createEventProducer(
						com.lynxspa.fpm.events.StandardFileCreatedEventproducer.class,
						"BloombergPerSecurityFileCreated");

		// add the newly created resource to domain
		this.addEventProducer(bloombergPerSecurityFileCreatedEventProducer);

		// $-- /application/BloombergPerSecurityFileCreated/
		bloombergPerSecurityFileCreatedEventProducer
				.setInputDirectory(getInputBloombergPerSecDirectory());
		// $-- /application/BloombergPerSecurityFileCreated/
		bloombergPerSecurityFileCreatedEventProducer
				.setWaitTimeout(getInputTimeout());
		// $-- /application/BloombergPerSecurityFileCreated/
		bloombergPerSecurityFileCreatedEventProducer
				.setPattern(getInputBloombergPerSecPattern());
		// $-- /application/BloombergPerSecurityFileCreated/
		bloombergPerSecurityFileCreatedEventProducer
				.setIncludeSubdirectories(false);
		// $-- /application/BloombergPerSecurityFileCreated/
		bloombergPerSecurityFileCreatedEventProducer
				.setCaseSensitivePattern(true);
		// $-- /application/BloombergPerSecurityFileCreated/
		bloombergPerSecurityFileCreatedEventProducer
				.setDetectAlreadyExistentFiles(true);
		// $-- /application/BloombergPerSecurityFileCreated/
		bloombergPerSecurityFileCreatedEventProducer
				.setCronExpression(getInputBloombergPerSecCron());
		// $-- /application/BloombergPerSecurityFileCreated/
		// $-- /application/BloombergPerSecurityFileCreated/
		bloombergPerSecurityFileCreatedEventProducer
				.setAllowMultiThreading(false);

		// link event producer to resources

		bloombergPerSecurityFileCreatedEventProducer.init();
		// $-- /application/IberclearFileCreated
		// instantiate and configure 'IberclearFileCreated' event producer
		com.lynxspa.fpm.events.StandardFileCreatedEventproducer iberclearFileCreatedEventProducer = Interceptors
				.createEventProducer(
						com.lynxspa.fpm.events.StandardFileCreatedEventproducer.class,
						"IberclearFileCreated");

		// add the newly created resource to domain
		this.addEventProducer(iberclearFileCreatedEventProducer);

		// $-- /application/IberclearFileCreated/
		iberclearFileCreatedEventProducer
				.setInputDirectory(getInputIberclearDirectory());
		// $-- /application/IberclearFileCreated/
		iberclearFileCreatedEventProducer.setWaitTimeout(getInputTimeout());
		// $-- /application/IberclearFileCreated/
		iberclearFileCreatedEventProducer
				.setPattern(getInputIberclearPattern());
		// $-- /application/IberclearFileCreated/
		iberclearFileCreatedEventProducer.setIncludeSubdirectories(false);
		// $-- /application/IberclearFileCreated/
		iberclearFileCreatedEventProducer.setCaseSensitivePattern(true);
		// $-- /application/IberclearFileCreated/
		iberclearFileCreatedEventProducer.setDetectAlreadyExistentFiles(true);
		// $-- /application/IberclearFileCreated/
		iberclearFileCreatedEventProducer
				.setCronExpression(getInputIberclearCron());
		// $-- /application/IberclearFileCreated/
		// $-- /application/IberclearFileCreated/
		iberclearFileCreatedEventProducer.setAllowMultiThreading(false);

		// link event producer to resources

		iberclearFileCreatedEventProducer.init();
		// $-- /application/InversisFileCreated
		// instantiate and configure 'InversisFileCreated' event producer
		com.lynxspa.fpm.events.StandardFileCreatedEventproducer inversisFileCreatedEventProducer = Interceptors
				.createEventProducer(
						com.lynxspa.fpm.events.StandardFileCreatedEventproducer.class,
						"InversisFileCreated");

		// add the newly created resource to domain
		this.addEventProducer(inversisFileCreatedEventProducer);

		// $-- /application/InversisFileCreated/
		inversisFileCreatedEventProducer
				.setInputDirectory(getInputInversisDirectory());
		// $-- /application/InversisFileCreated/
		inversisFileCreatedEventProducer.setWaitTimeout(getInputTimeout());
		// $-- /application/InversisFileCreated/
		inversisFileCreatedEventProducer.setPattern(getInputInversisPattern());
		// $-- /application/InversisFileCreated/
		inversisFileCreatedEventProducer.setIncludeSubdirectories(false);
		// $-- /application/InversisFileCreated/
		inversisFileCreatedEventProducer.setCaseSensitivePattern(true);
		// $-- /application/InversisFileCreated/
		inversisFileCreatedEventProducer.setDetectAlreadyExistentFiles(true);
		// $-- /application/InversisFileCreated/
		inversisFileCreatedEventProducer
				.setCronExpression(getInputInversisCron());
		// $-- /application/InversisFileCreated/
		// $-- /application/InversisFileCreated/
		inversisFileCreatedEventProducer.setAllowMultiThreading(false);

		// link event producer to resources

		inversisFileCreatedEventProducer.init();
		// $-- /application/OfivalFileCreated
		// instantiate and configure 'OfivalFileCreated' event producer
		com.lynxspa.fpm.events.StandardFileCreatedEventproducer ofivalFileCreatedEventProducer = Interceptors
				.createEventProducer(
						com.lynxspa.fpm.events.StandardFileCreatedEventproducer.class,
						"OfivalFileCreated");

		// add the newly created resource to domain
		this.addEventProducer(ofivalFileCreatedEventProducer);

		// $-- /application/OfivalFileCreated/
		ofivalFileCreatedEventProducer
				.setInputDirectory(getInputOfivalDirectory());
		// $-- /application/OfivalFileCreated/
		ofivalFileCreatedEventProducer.setWaitTimeout(getInputTimeout());
		// $-- /application/OfivalFileCreated/
		ofivalFileCreatedEventProducer.setPattern(getInputOfivalPattern());
		// $-- /application/OfivalFileCreated/
		ofivalFileCreatedEventProducer.setIncludeSubdirectories(false);
		// $-- /application/OfivalFileCreated/
		ofivalFileCreatedEventProducer.setCaseSensitivePattern(true);
		// $-- /application/OfivalFileCreated/
		ofivalFileCreatedEventProducer.setDetectAlreadyExistentFiles(true);
		// $-- /application/OfivalFileCreated/
		ofivalFileCreatedEventProducer.setCronExpression(getInputOfivalCron());
		// $-- /application/OfivalFileCreated/
		// $-- /application/OfivalFileCreated/
		ofivalFileCreatedEventProducer.setAllowMultiThreading(false);

		// link event producer to resources

		ofivalFileCreatedEventProducer.init();
		// $-- /application/SwiftFileCreated
		// instantiate and configure 'SwiftFileCreated' event producer
		com.lynxspa.fpm.events.StandardFileCreatedEventproducer swiftFileCreatedEventProducer = Interceptors
				.createEventProducer(
						com.lynxspa.fpm.events.StandardFileCreatedEventproducer.class,
						"SwiftFileCreated");

		// add the newly created resource to domain
		this.addEventProducer(swiftFileCreatedEventProducer);

		// $-- /application/SwiftFileCreated/
		swiftFileCreatedEventProducer
				.setInputDirectory(getInputSwiftDirectory());
		// $-- /application/SwiftFileCreated/
		swiftFileCreatedEventProducer.setWaitTimeout(getInputTimeout());
		// $-- /application/SwiftFileCreated/
		swiftFileCreatedEventProducer.setPattern(getInputSwiftPattern());
		// $-- /application/SwiftFileCreated/
		swiftFileCreatedEventProducer.setIncludeSubdirectories(false);
		// $-- /application/SwiftFileCreated/
		swiftFileCreatedEventProducer.setCaseSensitivePattern(true);
		// $-- /application/SwiftFileCreated/
		swiftFileCreatedEventProducer.setDetectAlreadyExistentFiles(true);
		// $-- /application/SwiftFileCreated/
		swiftFileCreatedEventProducer.setCronExpression(getInputSwiftCron());
		// $-- /application/SwiftFileCreated/
		// $-- /application/SwiftFileCreated/
		swiftFileCreatedEventProducer.setAllowMultiThreading(false);

		// link event producer to resources

		swiftFileCreatedEventProducer.init();

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
		errorHandlerBusinessProcess.setUser("IMPORTERERRORHANDLER");

		// link process to resources
		// $-- /application/HibernateResource/resource/application/ErrorHandler/sessionFactory
		errorHandlerBusinessProcess.setSessionFactory(hibernateResource_);

		errorHandlerBusinessProcess.initialize();

		// $-- /application/Import Bloomberg
		// instantiate and configure 'Import Bloomberg' business process
		com.lynxspa.coac.importers.bloomberg.businessprocess.ImportBloombergProcess importBloombergBusinessProcess = Interceptors
				.createBusinessProcess(
						com.lynxspa.coac.importers.bloomberg.businessprocess.ImportBloombergProcess.class,
						"Import Bloomberg", 1);

		// add business process to the processes
		this.addBusinessProcess(importBloombergBusinessProcess);

		// $-- /application/Import Bloomberg/
		importBloombergBusinessProcess.setLocale(getLocale());
		// $-- /application/Import Bloomberg/
		importBloombergBusinessProcess
				.setInputBloombergCommitSize(getInputBloombergCommitSize());
		// $-- /application/Import Bloomberg/
		importBloombergBusinessProcess.setInputTimeout(getInputTimeout());
		// $-- /application/Import Bloomberg/
		importBloombergBusinessProcess
				.setInputBloombergErrorDirectory(getInputBloombergErrorDirectory());
		// $-- /application/Import Bloomberg/
		// $-- /application/Import Bloomberg/
		importBloombergBusinessProcess.setUser("COACBLOOMBERGIMPORTER");

		// link process to resources
		// $-- /application/HibernateResource/resource/application/Import Bloomberg/sessionFactory
		importBloombergBusinessProcess.setSessionFactory(hibernateResource_);
		// $-- /application/HibernateStatelessResource/resource/application/Import Bloomberg/statelessSessionFactory
		importBloombergBusinessProcess
				.setStatelessSessionFactory(hibernateStatelessResource_);

		importBloombergBusinessProcess.initialize();

		// $-- /application/Import Bloomberg PerSecurity
		// instantiate and configure 'Import Bloomberg PerSecurity' business process
		com.lynxspa.coac.importers.bloomberg.businessprocess.ImportBloombergPerSecurityProcess importBloombergPerSecurityBusinessProcess = Interceptors
				.createBusinessProcess(
						com.lynxspa.coac.importers.bloomberg.businessprocess.ImportBloombergPerSecurityProcess.class,
						"Import Bloomberg PerSecurity", 1);

		// add business process to the processes
		this.addBusinessProcess(importBloombergPerSecurityBusinessProcess);

		// $-- /application/Import Bloomberg PerSecurity/
		importBloombergPerSecurityBusinessProcess.setLocale(getLocale());
		// $-- /application/Import Bloomberg PerSecurity/
		importBloombergPerSecurityBusinessProcess
				.setInputBloombergPerSecCommitSize(getInputBloombergPerSecCommitSize());
		// $-- /application/Import Bloomberg PerSecurity/
		importBloombergPerSecurityBusinessProcess
				.setInputTimeout(getInputTimeout());
		// $-- /application/Import Bloomberg PerSecurity/
		// $-- /application/Import Bloomberg PerSecurity/
		importBloombergPerSecurityBusinessProcess
				.setInputBloombergPerSecErrorDirectory(getInputBloombergPerSecErrorDirectory());
		// $-- /application/Import Bloomberg PerSecurity/
		importBloombergPerSecurityBusinessProcess
				.setUser("COACBLOOMBERGPERSECIMP");

		// link process to resources
		// $-- /application/HibernateResource/resource/application/Import Bloomberg PerSecurity/sessionFactory
		importBloombergPerSecurityBusinessProcess
				.setSessionFactory(hibernateResource_);
		// $-- /application/HibernateStatelessResource/resource/application/Import Bloomberg PerSecurity/statelessSessionFactory
		importBloombergPerSecurityBusinessProcess
				.setStatelessSessionFactory(hibernateStatelessResource_);

		importBloombergPerSecurityBusinessProcess.initialize();

		// $-- /application/Import Ofival
		// instantiate and configure 'Import Ofival' business process
		com.lynxspa.coac.importers.ofival.businessprocess.ImportOfivalProcess importOfivalBusinessProcess = Interceptors
				.createBusinessProcess(
						com.lynxspa.coac.importers.ofival.businessprocess.ImportOfivalProcess.class,
						"Import Ofival", 1);

		// add business process to the processes
		this.addBusinessProcess(importOfivalBusinessProcess);

		// $-- /application/Import Ofival/
		importOfivalBusinessProcess.setLocale(getLocale());
		// $-- /application/Import Ofival/
		importOfivalBusinessProcess
				.setInputOfivalCommitSize(getInputOfivalCommitSize());
		// $-- /application/Import Ofival/
		importOfivalBusinessProcess
				.setInputOfivalErrorDirectory(getInputOfivalErrorDirectory());
		// $-- /application/Import Ofival/
		importOfivalBusinessProcess.setInputTimeout(getInputTimeout());
		// $-- /application/Import Ofival/
		// $-- /application/Import Ofival/
		importOfivalBusinessProcess.setUser("COACOFIVALIMPORTER");

		// link process to resources
		// $-- /application/HibernateResource/resource/application/Import Ofival/sessionFactory
		importOfivalBusinessProcess.setSessionFactory(hibernateResource_);
		// $-- /application/HibernateStatelessResource/resource/application/Import Ofival/statelessSessionFactory
		importOfivalBusinessProcess
				.setStatelessSessionFactory(hibernateStatelessResource_);

		importOfivalBusinessProcess.initialize();

		// $-- /application/Import Swift
		// instantiate and configure 'Import Swift' business process
		com.lynxspa.coac.importers.swift.businessprocess.ImportSwiftProcess importSwiftBusinessProcess = Interceptors
				.createBusinessProcess(
						com.lynxspa.coac.importers.swift.businessprocess.ImportSwiftProcess.class,
						"Import Swift", 1);

		// add business process to the processes
		this.addBusinessProcess(importSwiftBusinessProcess);

		// $-- /application/Import Swift/
		importSwiftBusinessProcess
				.setInputSwiftErrorDirectory(getInputSwiftErrorDirectory());
		// $-- /application/Import Swift/
		importSwiftBusinessProcess.setLocale(getLocale());
		// $-- /application/Import Swift/
		importSwiftBusinessProcess
				.setInputSwiftCommitSize(getInputSwiftCommitSize());
		// $-- /application/Import Swift/
		importSwiftBusinessProcess.setInputTimeout(getInputTimeout());
		// $-- /application/Import Swift/
		// $-- /application/Import Swift/
		importSwiftBusinessProcess.setUser("COACSWIFTIMPORTER");

		// link process to resources
		// $-- /application/HibernateResource/resource/application/Import Swift/sessionFactory
		importSwiftBusinessProcess.setSessionFactory(hibernateResource_);
		// $-- /application/HibernateStatelessResource/resource/application/Import Swift/statelessSessionFactory
		importSwiftBusinessProcess
				.setStatelessSessionFactory(hibernateStatelessResource_);

		importSwiftBusinessProcess.initialize();

		// $-- /application/ImportBloombergPersecCorporatesProcess
		// instantiate and configure 'ImportBloombergPersecCorporatesProcess' business process
		com.lynxspa.coac.importers.bloomberg.businessprocess.ImportBloombergPersecCorporatesProcess importBloombergPersecCorporatesProcessBusinessProcess = Interceptors
				.createBusinessProcess(
						com.lynxspa.coac.importers.bloomberg.businessprocess.ImportBloombergPersecCorporatesProcess.class,
						"ImportBloombergPersecCorporatesProcess", 1);

		// add business process to the processes
		this.addBusinessProcess(importBloombergPersecCorporatesProcessBusinessProcess);

		// $-- /application/ImportBloombergPersecCorporatesProcess/
		importBloombergPersecCorporatesProcessBusinessProcess
				.setInputBloombergPerSecCorporatesErrorDirectory(getInputBloombergPerSecCorporatesErrorDirectory());
		// $-- /application/ImportBloombergPersecCorporatesProcess/
		importBloombergPersecCorporatesProcessBusinessProcess
				.setLocale(getLocale());
		// $-- /application/ImportBloombergPersecCorporatesProcess/
		importBloombergPersecCorporatesProcessBusinessProcess
				.setInputTimeout(getInputTimeout());
		// $-- /application/ImportBloombergPersecCorporatesProcess/
		// $-- /application/ImportBloombergPersecCorporatesProcess/
		importBloombergPersecCorporatesProcessBusinessProcess
				.setInputBloombergPerSecCorporatesCommitSize(getInputBloombergPerSecCorporatesCommitSize());
		// $-- /application/ImportBloombergPersecCorporatesProcess/
		importBloombergPersecCorporatesProcessBusinessProcess
				.setUser("COACBLOOMBERGPERSECCORPIMP");

		// link process to resources
		// $-- /application/HibernateResource/resource/application/ImportBloombergPersecCorporatesProcess/sessionFactory
		importBloombergPersecCorporatesProcessBusinessProcess
				.setSessionFactory(hibernateResource_);
		// $-- /application/HibernateStatelessResource/resource/application/ImportBloombergPersecCorporatesProcess/statelessSessionFactory
		importBloombergPersecCorporatesProcessBusinessProcess
				.setStatelessSessionFactory(hibernateStatelessResource_);

		importBloombergPersecCorporatesProcessBusinessProcess.initialize();

		// $-- /application/ImportIberclear
		// instantiate and configure 'ImportIberclear' business process
		com.lynxspa.coac.importers.iberclear.businessprocess.ImportIberclearProcess importIberclearBusinessProcess = Interceptors
				.createBusinessProcess(
						com.lynxspa.coac.importers.iberclear.businessprocess.ImportIberclearProcess.class,
						"ImportIberclear", 1);

		// add business process to the processes
		this.addBusinessProcess(importIberclearBusinessProcess);

		// $-- /application/ImportIberclear/
		importIberclearBusinessProcess
				.setInputIberclearCommitSize(getInputIberclearCommitSize());
		// $-- /application/ImportIberclear/
		importIberclearBusinessProcess.setInputTimeOut(getInputTimeout());
		// $-- /application/ImportIberclear/
		importIberclearBusinessProcess.setLocale(getLocale());
		// $-- /application/ImportIberclear/
		// $-- /application/ImportIberclear/
		importIberclearBusinessProcess.setUser("COACIBERCLEARIMPORTER");
		// $-- /application/ImportIberclear/
		importIberclearBusinessProcess
				.setInputIberclearErrorDirectory(getInputIberclearErrorDirectory());

		// link process to resources
		// $-- /application/HibernateResource/resource/application/ImportIberclear/sessionFactory
		importIberclearBusinessProcess.setSessionFactory(hibernateResource_);
		// $-- /application/HibernateStatelessResource/resource/application/ImportIberclear/statelessSessionFactory
		importIberclearBusinessProcess
				.setStatelessSessionFactory(hibernateStatelessResource_);

		importIberclearBusinessProcess.initialize();

		// $-- /application/ImportInversisProcess
		// instantiate and configure 'ImportInversisProcess' business process
		com.lynxspa.coac.importers.inversis.businesprocess.ImportInversisProcess importInversisProcessBusinessProcess = Interceptors
				.createBusinessProcess(
						com.lynxspa.coac.importers.inversis.businesprocess.ImportInversisProcess.class,
						"ImportInversisProcess", 1);

		// add business process to the processes
		this.addBusinessProcess(importInversisProcessBusinessProcess);

		// $-- /application/ImportInversisProcess/
		importInversisProcessBusinessProcess
				.setInputInversisErrorDirectory(getInputInversisErrorDirectory());
		// $-- /application/ImportInversisProcess/
		importInversisProcessBusinessProcess.setInputTimeOut(getInputTimeout());
		// $-- /application/ImportInversisProcess/
		importInversisProcessBusinessProcess
				.setInputInversisCommitSize(getInputInversisCommitSize());
		// $-- /application/ImportInversisProcess/
		importInversisProcessBusinessProcess.setLocale(getLocale());
		// $-- /application/ImportInversisProcess/
		// $-- /application/ImportInversisProcess/
		importInversisProcessBusinessProcess.setUser("COACINVERSISIMPORTED");

		// link process to resources
		// $-- /application/HibernateResource/resource/application/ImportInversisProcess/sessionFactory
		importInversisProcessBusinessProcess
				.setSessionFactory(hibernateResource_);
		// $-- /application/HibernateStatelessResource/resource/application/ImportInversisProcess/statelessSessionFactory
		importInversisProcessBusinessProcess
				.setStatelessSessionFactory(hibernateStatelessResource_);

		importInversisProcessBusinessProcess.initialize();

		/*
		 * Link event producers to processes, sub-applications or outputs
		 */
		// $-- /application/BloombergFileCreated/events/application/Import Bloomberg/bloombergFileCreated
		BusinessProcess.connect(bloombergFileCreatedEventProducer,
				importBloombergBusinessProcess.asBloombergFileCreatedInput());
		// $-- /application/BloombergPerSecurityCorporateFileCreated/events/application/ImportBloombergPersecCorporatesProcess/bloombergFileCreated
		BusinessProcess.connect(
				bloombergPerSecurityCorporateFileCreatedEventProducer,
				importBloombergPersecCorporatesProcessBusinessProcess
						.asBloombergFileCreatedInput());
		// $-- /application/BloombergPerSecurityFileCreated/events/application/Import Bloomberg PerSecurity/bloombergFileCreated
		BusinessProcess.connect(bloombergPerSecurityFileCreatedEventProducer,
				importBloombergPerSecurityBusinessProcess
						.asBloombergFileCreatedInput());
		// $-- /application/IberclearFileCreated/events/application/ImportIberclear/iberclearFileCreated
		BusinessProcess.connect(iberclearFileCreatedEventProducer,
				importIberclearBusinessProcess.asIberclearFileCreatedInput());
		// $-- /application/InversisFileCreated/events/application/ImportInversisProcess/inversisFileCreated
		BusinessProcess.connect(inversisFileCreatedEventProducer,
				importInversisProcessBusinessProcess
						.asInversisFileCreatedInput());
		// $-- /application/OfivalFileCreated/events/application/Import Ofival/ofivalFileCreated
		BusinessProcess.connect(ofivalFileCreatedEventProducer,
				importOfivalBusinessProcess.asOfivalFileCreatedInput());
		// $-- /application/SwiftFileCreated/events/application/Import Swift/swiftFileCreated
		BusinessProcess.connect(swiftFileCreatedEventProducer,
				importSwiftBusinessProcess.asSwiftFileCreatedInput());

		/*
		 * Link inputs to processes, sub-applications or outputs
		 */

		/*
		 * Link processes to other processes, sub-applications or outputs
		 */
		// $-- /application/Import Bloomberg/exc/application/ErrorHandler/input
		BusinessProcess.connect(
				importBloombergBusinessProcess.asExcEventProducer(),
				errorHandlerBusinessProcess.asInputInput());
		// $-- /application/Import Bloomberg PerSecurity/exc/application/ErrorHandler/input
		BusinessProcess.connect(
				importBloombergPerSecurityBusinessProcess.asExcEventProducer(),
				errorHandlerBusinessProcess.asInputInput());
		// $-- /application/Import Ofival/exc/application/ErrorHandler/input
		BusinessProcess.connect(
				importOfivalBusinessProcess.asExcEventProducer(),
				errorHandlerBusinessProcess.asInputInput());
		// $-- /application/Import Swift/exc/application/ErrorHandler/input
		BusinessProcess.connect(
				importSwiftBusinessProcess.asExcEventProducer(),
				errorHandlerBusinessProcess.asInputInput());
		// $-- /application/ImportBloombergPersecCorporatesProcess/exc/application/ErrorHandler/input
		BusinessProcess.connect(
				importBloombergPersecCorporatesProcessBusinessProcess
						.asExcEventProducer(), errorHandlerBusinessProcess
						.asInputInput());
		// $-- /application/ImportIberclear/exc/application/ErrorHandler/input
		BusinessProcess.connect(
				importIberclearBusinessProcess.asExcEventProducer(),
				errorHandlerBusinessProcess.asInputInput());
		// $-- /application/ImportInversisProcess/exc/application/ErrorHandler/input
		BusinessProcess.connect(
				importInversisProcessBusinessProcess.asExcEventProducer(),
				errorHandlerBusinessProcess.asInputInput());

		/*
		 * Link sub applications to other processes, sub-applications or outputs
		 */
	}

	protected void setInitParams() throws ParseException {
		Properties initParams = ConfigParams.getInstance().getProperties();

		setInputBloombergCommitSize(ConfigurationHelper.parseString(
				initParams.getProperty("inputBloombergCommitSize"),
				Integer.class));
		setInputBloombergCron(ConfigurationHelper.parseString(
				initParams.getProperty("inputBloombergCron"), String.class));
		setInputBloombergDirectory(ConfigurationHelper.parseString(
				initParams.getProperty("inputBloombergDirectory"),
				java.io.File.class));
		setInputBloombergErrorDirectory(ConfigurationHelper.parseString(
				initParams.getProperty("inputBloombergErrorDirectory"),
				java.io.File.class));
		setInputBloombergPattern(ConfigurationHelper.parseString(
				initParams.getProperty("inputBloombergPattern"), String.class));
		setInputBloombergPerSecCommitSize(ConfigurationHelper.parseString(
				initParams.getProperty("inputBloombergPerSecCommitSize"),
				Integer.class));
		setInputBloombergPerSecCorporatesCommitSize(ConfigurationHelper
				.parseString(
						initParams
								.getProperty("inputBloombergPerSecCorporatesCommitSize"),
						Integer.class));
		setInputBloombergPerSecCorporatesCron(ConfigurationHelper.parseString(
				initParams.getProperty("inputBloombergPerSecCorporatesCron"),
				String.class));
		setInputBloombergPerSecCorporatesDirectory(ConfigurationHelper
				.parseString(
						initParams
								.getProperty("inputBloombergPerSecCorporatesDirectory"),
						java.io.File.class));
		setInputBloombergPerSecCorporatesErrorDirectory(ConfigurationHelper
				.parseString(
						initParams
								.getProperty("inputBloombergPerSecCorporatesErrorDirectory"),
						java.io.File.class));
		setInputBloombergPerSecCorporatesPattern(ConfigurationHelper
				.parseString(initParams
						.getProperty("inputBloombergPerSecCorporatesPattern"),
						String.class));
		setInputBloombergPerSecCron(ConfigurationHelper.parseString(
				initParams.getProperty("inputBloombergPerSecCron"),
				String.class));
		setInputBloombergPerSecDirectory(ConfigurationHelper.parseString(
				initParams.getProperty("inputBloombergPerSecDirectory"),
				java.io.File.class));
		setInputBloombergPerSecErrorDirectory(ConfigurationHelper.parseString(
				initParams.getProperty("inputBloombergPerSecErrorDirectory"),
				java.io.File.class));
		setInputBloombergPerSecPattern(ConfigurationHelper.parseString(
				initParams.getProperty("inputBloombergPerSecPattern"),
				String.class));
		setInputIberclearCommitSize(ConfigurationHelper.parseString(
				initParams.getProperty("inputIberclearCommitSize"),
				Integer.class));
		setInputIberclearCron(ConfigurationHelper.parseString(
				initParams.getProperty("inputIberclearCron"), String.class));
		setInputIberclearDirectory(ConfigurationHelper.parseString(
				initParams.getProperty("inputIberclearDirectory"),
				java.io.File.class));
		setInputIberclearErrorDirectory(ConfigurationHelper.parseString(
				initParams.getProperty("inputIberclearErrorDirectory"),
				java.io.File.class));
		setInputIberclearPattern(ConfigurationHelper.parseString(
				initParams.getProperty("inputIberclearPattern"), String.class));
		setInputInversisCommitSize(ConfigurationHelper.parseString(
				initParams.getProperty("inputInversisCommitSize"),
				Integer.class));
		setInputInversisCron(ConfigurationHelper.parseString(
				initParams.getProperty("inputInversisCron"), String.class));
		setInputInversisDirectory(ConfigurationHelper.parseString(
				initParams.getProperty("inputInversisDirectory"),
				java.io.File.class));
		setInputInversisErrorDirectory(ConfigurationHelper.parseString(
				initParams.getProperty("inputInversisErrorDirectory"),
				java.io.File.class));
		setInputInversisPattern(ConfigurationHelper.parseString(
				initParams.getProperty("inputInversisPattern"), String.class));
		setInputOfivalCommitSize(ConfigurationHelper.parseString(
				initParams.getProperty("inputOfivalCommitSize"), Integer.class));
		setInputOfivalCron(ConfigurationHelper.parseString(
				initParams.getProperty("inputOfivalCron"), String.class));
		setInputOfivalDirectory(ConfigurationHelper.parseString(
				initParams.getProperty("inputOfivalDirectory"),
				java.io.File.class));
		setInputOfivalErrorDirectory(ConfigurationHelper.parseString(
				initParams.getProperty("inputOfivalErrorDirectory"),
				java.io.File.class));
		setInputOfivalPattern(ConfigurationHelper.parseString(
				initParams.getProperty("inputOfivalPattern"), String.class));
		setInputSwiftCommitSize(ConfigurationHelper.parseString(
				initParams.getProperty("inputSwiftCommitSize"), Integer.class));
		setInputSwiftCron(ConfigurationHelper.parseString(
				initParams.getProperty("inputSwiftCron"), String.class));
		setInputSwiftDirectory(ConfigurationHelper.parseString(
				initParams.getProperty("inputSwiftDirectory"),
				java.io.File.class));
		setInputSwiftErrorDirectory(ConfigurationHelper.parseString(
				initParams.getProperty("inputSwiftErrorDirectory"),
				java.io.File.class));
		setInputSwiftPattern(ConfigurationHelper.parseString(
				initParams.getProperty("inputSwiftPattern"), String.class));
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
					.createApplicationDomain(
							CorporateActionsImporterApplication.class,
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