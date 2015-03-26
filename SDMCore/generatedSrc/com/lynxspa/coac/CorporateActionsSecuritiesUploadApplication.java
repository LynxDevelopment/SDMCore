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

@GeneratedType(definitionFile = "src/com/lynxspa/coac/CorporateActionsSecuritiesUpload.fpmapplication")
public class CorporateActionsSecuritiesUploadApplication extends
		ApplicationDomain {

	public CorporateActionsSecuritiesUploadApplication(String id)
			throws Exception {
		super(id);
	}

	/*
	 * Parameters declarations
	 */
	private Integer inputTimeout_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "inputTimeout")
	public Integer getInputTimeout() {
		return inputTimeout_;
	}

	public void setInputTimeout(Integer value) {
		this.inputTimeout_ = value;
	}

	private String locale_;

	@ConfigParam(description = "param2", required = true, dynamic = false, name = "locale")
	public String getLocale() {
		return locale_;
	}

	public void setLocale(String value) {
		this.locale_ = value;
	}

	private Integer securitiesUploadBloombergCommitSize_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "securitiesUploadBloombergCommitSize")
	public Integer getSecuritiesUploadBloombergCommitSize() {
		return securitiesUploadBloombergCommitSize_;
	}

	public void setSecuritiesUploadBloombergCommitSize(Integer value) {
		this.securitiesUploadBloombergCommitSize_ = value;
	}

	private String securitiesUploadBloombergCron_;

	@ConfigParam(description = "param4", required = true, dynamic = false, name = "securitiesUploadBloombergCron")
	public String getSecuritiesUploadBloombergCron() {
		return securitiesUploadBloombergCron_;
	}

	public void setSecuritiesUploadBloombergCron(String value) {
		this.securitiesUploadBloombergCron_ = value;
	}

	private java.io.File securitiesUploadBloombergDebtInputDirectory_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "securitiesUploadBloombergDebtInputDirectory")
	public java.io.File getSecuritiesUploadBloombergDebtInputDirectory() {
		return securitiesUploadBloombergDebtInputDirectory_;
	}

	public void setSecuritiesUploadBloombergDebtInputDirectory(
			java.io.File value) {
		this.securitiesUploadBloombergDebtInputDirectory_ = value;
	}

	private java.io.File securitiesUploadBloombergFundsInputDirectory_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "securitiesUploadBloombergFundsInputDirectory")
	public java.io.File getSecuritiesUploadBloombergFundsInputDirectory() {
		return securitiesUploadBloombergFundsInputDirectory_;
	}

	public void setSecuritiesUploadBloombergFundsInputDirectory(
			java.io.File value) {
		this.securitiesUploadBloombergFundsInputDirectory_ = value;
	}

	private java.io.File securitiesUploadBloombergInputDirectory_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "securitiesUploadBloombergInputDirectory")
	public java.io.File getSecuritiesUploadBloombergInputDirectory() {
		return securitiesUploadBloombergInputDirectory_;
	}

	public void setSecuritiesUploadBloombergInputDirectory(java.io.File value) {
		this.securitiesUploadBloombergInputDirectory_ = value;
	}

	private String securitiesUploadBloombergPattern_;

	@ConfigParam(description = "param7", required = true, dynamic = false, name = "securitiesUploadBloombergPattern")
	public String getSecuritiesUploadBloombergPattern() {
		return securitiesUploadBloombergPattern_;
	}

	public void setSecuritiesUploadBloombergPattern(String value) {
		this.securitiesUploadBloombergPattern_ = value;
	}

	private String securitiesUploadBloombergPatternDif_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "securitiesUploadBloombergPatternDif")
	public String getSecuritiesUploadBloombergPatternDif() {
		return securitiesUploadBloombergPatternDif_;
	}

	public void setSecuritiesUploadBloombergPatternDif(String value) {
		this.securitiesUploadBloombergPatternDif_ = value;
	}

	private java.io.File securitiesUploadBloombergRVNoEuropeInputDirectory_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "securitiesUploadBloombergRVNoEuropeInputDirectory")
	public java.io.File getSecuritiesUploadBloombergRVNoEuropeInputDirectory() {
		return securitiesUploadBloombergRVNoEuropeInputDirectory_;
	}

	public void setSecuritiesUploadBloombergRVNoEuropeInputDirectory(
			java.io.File value) {
		this.securitiesUploadBloombergRVNoEuropeInputDirectory_ = value;
	}

	private Integer securitiesUploadCustomerCommitSize_;

	@ConfigParam(description = "param1", required = false, dynamic = false, name = "securitiesUploadCustomerCommitSize")
	public Integer getSecuritiesUploadCustomerCommitSize() {
		return securitiesUploadCustomerCommitSize_;
	}

	public void setSecuritiesUploadCustomerCommitSize(Integer value) {
		this.securitiesUploadCustomerCommitSize_ = value;
	}

	private String securitiesUploadCustomerCron_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "securitiesUploadCustomerCron")
	public String getSecuritiesUploadCustomerCron() {
		return securitiesUploadCustomerCron_;
	}

	public void setSecuritiesUploadCustomerCron(String value) {
		this.securitiesUploadCustomerCron_ = value;
	}

	private java.io.File securitiesUploadCustomerInputDirectory_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "securitiesUploadCustomerInputDirectory")
	public java.io.File getSecuritiesUploadCustomerInputDirectory() {
		return securitiesUploadCustomerInputDirectory_;
	}

	public void setSecuritiesUploadCustomerInputDirectory(java.io.File value) {
		this.securitiesUploadCustomerInputDirectory_ = value;
	}

	private String securitiesUploadCustomerPattern_;

	@ConfigParam(description = "param3", required = true, dynamic = false, name = "securitiesUploadCustomerPattern")
	public String getSecuritiesUploadCustomerPattern() {
		return securitiesUploadCustomerPattern_;
	}

	public void setSecuritiesUploadCustomerPattern(String value) {
		this.securitiesUploadCustomerPattern_ = value;
	}

	private Boolean securitiesUploadForceMarketInsert_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "securitiesUploadForceMarketInsert")
	public Boolean getSecuritiesUploadForceMarketInsert() {
		return securitiesUploadForceMarketInsert_;
	}

	public void setSecuritiesUploadForceMarketInsert(Boolean value) {
		this.securitiesUploadForceMarketInsert_ = value;
	}

	private Integer securitiesUploadFundsCommitSize_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "securitiesUploadFundsCommitSize")
	public Integer getSecuritiesUploadFundsCommitSize() {
		return securitiesUploadFundsCommitSize_;
	}

	public void setSecuritiesUploadFundsCommitSize(Integer value) {
		this.securitiesUploadFundsCommitSize_ = value;
	}

	private String securitiesUploadFundsCron_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "securitiesUploadFundsCron")
	public String getSecuritiesUploadFundsCron() {
		return securitiesUploadFundsCron_;
	}

	public void setSecuritiesUploadFundsCron(String value) {
		this.securitiesUploadFundsCron_ = value;
	}

	private java.io.File securitiesUploadFundsInputDirectory_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "securitiesUploadFundsInputDirectory")
	public java.io.File getSecuritiesUploadFundsInputDirectory() {
		return securitiesUploadFundsInputDirectory_;
	}

	public void setSecuritiesUploadFundsInputDirectory(java.io.File value) {
		this.securitiesUploadFundsInputDirectory_ = value;
	}

	private String securitiesUploadFundsPattern_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "securitiesUploadFundsPattern")
	public String getSecuritiesUploadFundsPattern() {
		return securitiesUploadFundsPattern_;
	}

	public void setSecuritiesUploadFundsPattern(String value) {
		this.securitiesUploadFundsPattern_ = value;
	}

	private String securitiesUploadFundsPatternDif_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "securitiesUploadFundsPatternDif")
	public String getSecuritiesUploadFundsPatternDif() {
		return securitiesUploadFundsPatternDif_;
	}

	public void setSecuritiesUploadFundsPatternDif(String value) {
		this.securitiesUploadFundsPatternDif_ = value;
	}

	private Integer securitiesUploadOfivalCommitSize_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "securitiesUploadOfivalCommitSize")
	public Integer getSecuritiesUploadOfivalCommitSize() {
		return securitiesUploadOfivalCommitSize_;
	}

	public void setSecuritiesUploadOfivalCommitSize(Integer value) {
		this.securitiesUploadOfivalCommitSize_ = value;
	}

	private String securitiesUploadOfivalCron_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "securitiesUploadOfivalCron")
	public String getSecuritiesUploadOfivalCron() {
		return securitiesUploadOfivalCron_;
	}

	public void setSecuritiesUploadOfivalCron(String value) {
		this.securitiesUploadOfivalCron_ = value;
	}

	private java.io.File securitiesUploadOfivalInputDirectory_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "securitiesUploadOfivalInputDirectory")
	public java.io.File getSecuritiesUploadOfivalInputDirectory() {
		return securitiesUploadOfivalInputDirectory_;
	}

	public void setSecuritiesUploadOfivalInputDirectory(java.io.File value) {
		this.securitiesUploadOfivalInputDirectory_ = value;
	}

	private String securitiesUploadOfivalPattern_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "securitiesUploadOfivalPattern")
	public String getSecuritiesUploadOfivalPattern() {
		return securitiesUploadOfivalPattern_;
	}

	public void setSecuritiesUploadOfivalPattern(String value) {
		this.securitiesUploadOfivalPattern_ = value;
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
		// $-- /application/Bloomberg Base
		// instantiate and configure 'Bloomberg Base' event producer
		com.lynxspa.fpm.events.StandardFileCreatedEventproducer bloombergBaseEventProducer = Interceptors
				.createEventProducer(
						com.lynxspa.fpm.events.StandardFileCreatedEventproducer.class,
						"Bloomberg Base");

		// add the newly created resource to domain
		this.addEventProducer(bloombergBaseEventProducer);

		// $-- /application/Bloomberg Base/
		bloombergBaseEventProducer
				.setInputDirectory(getSecuritiesUploadBloombergInputDirectory());
		// $-- /application/Bloomberg Base/
		bloombergBaseEventProducer.setWaitTimeout(getInputTimeout());
		// $-- /application/Bloomberg Base/
		bloombergBaseEventProducer
				.setPattern(getSecuritiesUploadBloombergPattern());
		// $-- /application/Bloomberg Base/
		bloombergBaseEventProducer.setIncludeSubdirectories(false);
		// $-- /application/Bloomberg Base/
		bloombergBaseEventProducer.setCaseSensitivePattern(true);
		// $-- /application/Bloomberg Base/
		bloombergBaseEventProducer.setDetectAlreadyExistentFiles(true);
		// $-- /application/Bloomberg Base/
		bloombergBaseEventProducer
				.setCronExpression(getSecuritiesUploadBloombergCron());
		// $-- /application/Bloomberg Base/
		// $-- /application/Bloomberg Base/
		bloombergBaseEventProducer.setAllowMultiThreading(false);

		// link event producer to resources

		bloombergBaseEventProducer.init();
		// $-- /application/Bloomberg Differences
		// instantiate and configure 'Bloomberg Differences' event producer
		com.lynxspa.fpm.events.StandardFileCreatedEventproducer bloombergDifferencesEventProducer = Interceptors
				.createEventProducer(
						com.lynxspa.fpm.events.StandardFileCreatedEventproducer.class,
						"Bloomberg Differences");

		// add the newly created resource to domain
		this.addEventProducer(bloombergDifferencesEventProducer);

		// $-- /application/Bloomberg Differences/
		bloombergDifferencesEventProducer
				.setInputDirectory(getSecuritiesUploadBloombergInputDirectory());
		// $-- /application/Bloomberg Differences/
		bloombergDifferencesEventProducer.setWaitTimeout(getInputTimeout());
		// $-- /application/Bloomberg Differences/
		bloombergDifferencesEventProducer
				.setPattern(getSecuritiesUploadBloombergPatternDif());
		// $-- /application/Bloomberg Differences/
		bloombergDifferencesEventProducer.setIncludeSubdirectories(false);
		// $-- /application/Bloomberg Differences/
		bloombergDifferencesEventProducer.setCaseSensitivePattern(true);
		// $-- /application/Bloomberg Differences/
		bloombergDifferencesEventProducer.setDetectAlreadyExistentFiles(true);
		// $-- /application/Bloomberg Differences/
		bloombergDifferencesEventProducer
				.setCronExpression(getSecuritiesUploadBloombergCron());
		// $-- /application/Bloomberg Differences/
		// $-- /application/Bloomberg Differences/
		bloombergDifferencesEventProducer.setAllowMultiThreading(false);

		// link event producer to resources

		bloombergDifferencesEventProducer.init();
		// $-- /application/Bloomberg Fondos
		// instantiate and configure 'Bloomberg Fondos' event producer
		com.lynxspa.fpm.events.StandardFileCreatedEventproducer bloombergFondosEventProducer = Interceptors
				.createEventProducer(
						com.lynxspa.fpm.events.StandardFileCreatedEventproducer.class,
						"Bloomberg Fondos");

		// add the newly created resource to domain
		this.addEventProducer(bloombergFondosEventProducer);

		// $-- /application/Bloomberg Fondos/
		bloombergFondosEventProducer
				.setInputDirectory(getSecuritiesUploadBloombergFundsInputDirectory());
		// $-- /application/Bloomberg Fondos/
		bloombergFondosEventProducer.setWaitTimeout(getInputTimeout());
		// $-- /application/Bloomberg Fondos/
		bloombergFondosEventProducer
				.setPattern(getSecuritiesUploadBloombergPattern());
		// $-- /application/Bloomberg Fondos/
		bloombergFondosEventProducer.setIncludeSubdirectories(false);
		// $-- /application/Bloomberg Fondos/
		bloombergFondosEventProducer.setCaseSensitivePattern(true);
		// $-- /application/Bloomberg Fondos/
		bloombergFondosEventProducer.setDetectAlreadyExistentFiles(true);
		// $-- /application/Bloomberg Fondos/
		bloombergFondosEventProducer
				.setCronExpression(getSecuritiesUploadBloombergCron());
		// $-- /application/Bloomberg Fondos/
		// $-- /application/Bloomberg Fondos/
		bloombergFondosEventProducer.setAllowMultiThreading(false);

		// link event producer to resources

		bloombergFondosEventProducer.init();
		// $-- /application/Bloomberg Fondos Diff
		// instantiate and configure 'Bloomberg Fondos Diff' event producer
		com.lynxspa.fpm.events.StandardFileCreatedEventproducer bloombergFondosDiffEventProducer = Interceptors
				.createEventProducer(
						com.lynxspa.fpm.events.StandardFileCreatedEventproducer.class,
						"Bloomberg Fondos Diff");

		// add the newly created resource to domain
		this.addEventProducer(bloombergFondosDiffEventProducer);

		// $-- /application/Bloomberg Fondos Diff/
		bloombergFondosDiffEventProducer
				.setInputDirectory(getSecuritiesUploadBloombergFundsInputDirectory());
		// $-- /application/Bloomberg Fondos Diff/
		bloombergFondosDiffEventProducer.setWaitTimeout(getInputTimeout());
		// $-- /application/Bloomberg Fondos Diff/
		bloombergFondosDiffEventProducer
				.setPattern(getSecuritiesUploadBloombergPatternDif());
		// $-- /application/Bloomberg Fondos Diff/
		bloombergFondosDiffEventProducer.setIncludeSubdirectories(false);
		// $-- /application/Bloomberg Fondos Diff/
		bloombergFondosDiffEventProducer.setCaseSensitivePattern(true);
		// $-- /application/Bloomberg Fondos Diff/
		bloombergFondosDiffEventProducer.setDetectAlreadyExistentFiles(true);
		// $-- /application/Bloomberg Fondos Diff/
		bloombergFondosDiffEventProducer
				.setCronExpression(getSecuritiesUploadBloombergCron());
		// $-- /application/Bloomberg Fondos Diff/
		// $-- /application/Bloomberg Fondos Diff/
		bloombergFondosDiffEventProducer.setAllowMultiThreading(false);

		// link event producer to resources

		bloombergFondosDiffEventProducer.init();
		// $-- /application/Bloomberg RF Diff
		// instantiate and configure 'Bloomberg RF Diff' event producer
		com.lynxspa.fpm.events.StandardFileCreatedEventproducer bloombergRFDiffEventProducer = Interceptors
				.createEventProducer(
						com.lynxspa.fpm.events.StandardFileCreatedEventproducer.class,
						"Bloomberg RF Diff");

		// add the newly created resource to domain
		this.addEventProducer(bloombergRFDiffEventProducer);

		// $-- /application/Bloomberg RF Diff/
		bloombergRFDiffEventProducer
				.setInputDirectory(getSecuritiesUploadBloombergDebtInputDirectory());
		// $-- /application/Bloomberg RF Diff/
		bloombergRFDiffEventProducer.setWaitTimeout(getInputTimeout());
		// $-- /application/Bloomberg RF Diff/
		bloombergRFDiffEventProducer
				.setPattern(getSecuritiesUploadBloombergPatternDif());
		// $-- /application/Bloomberg RF Diff/
		bloombergRFDiffEventProducer.setIncludeSubdirectories(false);
		// $-- /application/Bloomberg RF Diff/
		bloombergRFDiffEventProducer.setCaseSensitivePattern(true);
		// $-- /application/Bloomberg RF Diff/
		bloombergRFDiffEventProducer.setDetectAlreadyExistentFiles(true);
		// $-- /application/Bloomberg RF Diff/
		bloombergRFDiffEventProducer
				.setCronExpression(getSecuritiesUploadBloombergCron());
		// $-- /application/Bloomberg RF Diff/
		// $-- /application/Bloomberg RF Diff/
		bloombergRFDiffEventProducer.setAllowMultiThreading(false);

		// link event producer to resources

		bloombergRFDiffEventProducer.init();
		// $-- /application/Bloomberg RV No Europea
		// instantiate and configure 'Bloomberg RV No Europea' event producer
		com.lynxspa.fpm.events.StandardFileCreatedEventproducer bloombergRVNoEuropeaEventProducer = Interceptors
				.createEventProducer(
						com.lynxspa.fpm.events.StandardFileCreatedEventproducer.class,
						"Bloomberg RV No Europea");

		// add the newly created resource to domain
		this.addEventProducer(bloombergRVNoEuropeaEventProducer);

		// $-- /application/Bloomberg RV No Europea/
		bloombergRVNoEuropeaEventProducer
				.setInputDirectory(getSecuritiesUploadBloombergRVNoEuropeInputDirectory());
		// $-- /application/Bloomberg RV No Europea/
		bloombergRVNoEuropeaEventProducer.setWaitTimeout(getInputTimeout());
		// $-- /application/Bloomberg RV No Europea/
		bloombergRVNoEuropeaEventProducer
				.setPattern(getSecuritiesUploadBloombergPattern());
		// $-- /application/Bloomberg RV No Europea/
		bloombergRVNoEuropeaEventProducer.setIncludeSubdirectories(false);
		// $-- /application/Bloomberg RV No Europea/
		bloombergRVNoEuropeaEventProducer.setCaseSensitivePattern(true);
		// $-- /application/Bloomberg RV No Europea/
		bloombergRVNoEuropeaEventProducer.setDetectAlreadyExistentFiles(true);
		// $-- /application/Bloomberg RV No Europea/
		bloombergRVNoEuropeaEventProducer
				.setCronExpression(getSecuritiesUploadBloombergCron());
		// $-- /application/Bloomberg RV No Europea/
		// $-- /application/Bloomberg RV No Europea/
		bloombergRVNoEuropeaEventProducer.setAllowMultiThreading(false);

		// link event producer to resources

		bloombergRVNoEuropeaEventProducer.init();
		// $-- /application/Bloomberg RVNE Diff
		// instantiate and configure 'Bloomberg RVNE Diff' event producer
		com.lynxspa.fpm.events.StandardFileCreatedEventproducer bloombergRVNEDiffEventProducer = Interceptors
				.createEventProducer(
						com.lynxspa.fpm.events.StandardFileCreatedEventproducer.class,
						"Bloomberg RVNE Diff");

		// add the newly created resource to domain
		this.addEventProducer(bloombergRVNEDiffEventProducer);

		// $-- /application/Bloomberg RVNE Diff/
		bloombergRVNEDiffEventProducer
				.setInputDirectory(getSecuritiesUploadBloombergRVNoEuropeInputDirectory());
		// $-- /application/Bloomberg RVNE Diff/
		bloombergRVNEDiffEventProducer.setWaitTimeout(getInputTimeout());
		// $-- /application/Bloomberg RVNE Diff/
		bloombergRVNEDiffEventProducer
				.setPattern(getSecuritiesUploadBloombergPatternDif());
		// $-- /application/Bloomberg RVNE Diff/
		bloombergRVNEDiffEventProducer.setIncludeSubdirectories(false);
		// $-- /application/Bloomberg RVNE Diff/
		bloombergRVNEDiffEventProducer.setCaseSensitivePattern(true);
		// $-- /application/Bloomberg RVNE Diff/
		bloombergRVNEDiffEventProducer.setDetectAlreadyExistentFiles(true);
		// $-- /application/Bloomberg RVNE Diff/
		bloombergRVNEDiffEventProducer
				.setCronExpression(getSecuritiesUploadBloombergCron());
		// $-- /application/Bloomberg RVNE Diff/
		// $-- /application/Bloomberg RVNE Diff/
		bloombergRVNEDiffEventProducer.setAllowMultiThreading(false);

		// link event producer to resources

		bloombergRVNEDiffEventProducer.init();
		// $-- /application/Bloomberg Renta Fija
		// instantiate and configure 'Bloomberg Renta Fija' event producer
		com.lynxspa.fpm.events.StandardFileCreatedEventproducer bloombergRentaFijaEventProducer = Interceptors
				.createEventProducer(
						com.lynxspa.fpm.events.StandardFileCreatedEventproducer.class,
						"Bloomberg Renta Fija");

		// add the newly created resource to domain
		this.addEventProducer(bloombergRentaFijaEventProducer);

		// $-- /application/Bloomberg Renta Fija/
		bloombergRentaFijaEventProducer
				.setInputDirectory(getSecuritiesUploadBloombergDebtInputDirectory());
		// $-- /application/Bloomberg Renta Fija/
		bloombergRentaFijaEventProducer.setWaitTimeout(getInputTimeout());
		// $-- /application/Bloomberg Renta Fija/
		bloombergRentaFijaEventProducer
				.setPattern(getSecuritiesUploadBloombergPattern());
		// $-- /application/Bloomberg Renta Fija/
		bloombergRentaFijaEventProducer.setIncludeSubdirectories(false);
		// $-- /application/Bloomberg Renta Fija/
		bloombergRentaFijaEventProducer.setCaseSensitivePattern(true);
		// $-- /application/Bloomberg Renta Fija/
		bloombergRentaFijaEventProducer.setDetectAlreadyExistentFiles(true);
		// $-- /application/Bloomberg Renta Fija/
		bloombergRentaFijaEventProducer
				.setCronExpression(getSecuritiesUploadBloombergCron());
		// $-- /application/Bloomberg Renta Fija/
		// $-- /application/Bloomberg Renta Fija/
		bloombergRentaFijaEventProducer.setAllowMultiThreading(false);

		// link event producer to resources

		bloombergRentaFijaEventProducer.init();
		// $-- /application/Customer
		// instantiate and configure 'Customer' event producer
		com.lynxspa.fpm.events.StandardFileCreatedEventproducer customerEventProducer = Interceptors
				.createEventProducer(
						com.lynxspa.fpm.events.StandardFileCreatedEventproducer.class,
						"Customer");

		// add the newly created resource to domain
		this.addEventProducer(customerEventProducer);

		// $-- /application/Customer/
		customerEventProducer
				.setInputDirectory(getSecuritiesUploadCustomerInputDirectory());
		// $-- /application/Customer/
		customerEventProducer.setWaitTimeout(getInputTimeout());
		// $-- /application/Customer/
		customerEventProducer.setPattern(getSecuritiesUploadCustomerPattern());
		// $-- /application/Customer/
		customerEventProducer.setIncludeSubdirectories(false);
		// $-- /application/Customer/
		customerEventProducer.setCaseSensitivePattern(true);
		// $-- /application/Customer/
		customerEventProducer.setDetectAlreadyExistentFiles(true);
		// $-- /application/Customer/
		customerEventProducer
				.setCronExpression(getSecuritiesUploadCustomerCron());
		// $-- /application/Customer/
		// $-- /application/Customer/
		customerEventProducer.setAllowMultiThreading(false);

		// link event producer to resources

		customerEventProducer.init();
		// $-- /application/Funds Base
		// instantiate and configure 'Funds Base' event producer
		com.lynxspa.fpm.events.StandardFileCreatedEventproducer fundsBaseEventProducer = Interceptors
				.createEventProducer(
						com.lynxspa.fpm.events.StandardFileCreatedEventproducer.class,
						"Funds Base");

		// add the newly created resource to domain
		this.addEventProducer(fundsBaseEventProducer);

		// $-- /application/Funds Base/
		fundsBaseEventProducer
				.setInputDirectory(getSecuritiesUploadFundsInputDirectory());
		// $-- /application/Funds Base/
		fundsBaseEventProducer.setWaitTimeout(getInputTimeout());
		// $-- /application/Funds Base/
		fundsBaseEventProducer.setPattern(getSecuritiesUploadFundsPattern());
		// $-- /application/Funds Base/
		fundsBaseEventProducer.setIncludeSubdirectories(false);
		// $-- /application/Funds Base/
		fundsBaseEventProducer.setCaseSensitivePattern(true);
		// $-- /application/Funds Base/
		fundsBaseEventProducer.setDetectAlreadyExistentFiles(true);
		// $-- /application/Funds Base/
		fundsBaseEventProducer
				.setCronExpression(getSecuritiesUploadFundsCron());
		// $-- /application/Funds Base/
		// $-- /application/Funds Base/
		fundsBaseEventProducer.setAllowMultiThreading(false);

		// link event producer to resources

		fundsBaseEventProducer.init();
		// $-- /application/Funds Differences
		// instantiate and configure 'Funds Differences' event producer
		com.lynxspa.fpm.events.StandardFileCreatedEventproducer fundsDifferencesEventProducer = Interceptors
				.createEventProducer(
						com.lynxspa.fpm.events.StandardFileCreatedEventproducer.class,
						"Funds Differences");

		// add the newly created resource to domain
		this.addEventProducer(fundsDifferencesEventProducer);

		// $-- /application/Funds Differences/
		fundsDifferencesEventProducer
				.setInputDirectory(getSecuritiesUploadFundsInputDirectory());
		// $-- /application/Funds Differences/
		fundsDifferencesEventProducer.setWaitTimeout(getInputTimeout());
		// $-- /application/Funds Differences/
		fundsDifferencesEventProducer
				.setPattern(getSecuritiesUploadFundsPatternDif());
		// $-- /application/Funds Differences/
		fundsDifferencesEventProducer.setIncludeSubdirectories(false);
		// $-- /application/Funds Differences/
		fundsDifferencesEventProducer.setCaseSensitivePattern(true);
		// $-- /application/Funds Differences/
		fundsDifferencesEventProducer.setDetectAlreadyExistentFiles(true);
		// $-- /application/Funds Differences/
		fundsDifferencesEventProducer
				.setCronExpression(getSecuritiesUploadFundsCron());
		// $-- /application/Funds Differences/
		// $-- /application/Funds Differences/
		fundsDifferencesEventProducer.setAllowMultiThreading(false);

		// link event producer to resources

		fundsDifferencesEventProducer.init();
		// $-- /application/Ofival Base
		// instantiate and configure 'Ofival Base' event producer
		com.lynxspa.fpm.events.StandardFileCreatedEventproducer ofivalBaseEventProducer = Interceptors
				.createEventProducer(
						com.lynxspa.fpm.events.StandardFileCreatedEventproducer.class,
						"Ofival Base");

		// add the newly created resource to domain
		this.addEventProducer(ofivalBaseEventProducer);

		// $-- /application/Ofival Base/
		ofivalBaseEventProducer
				.setInputDirectory(getSecuritiesUploadOfivalInputDirectory());
		// $-- /application/Ofival Base/
		ofivalBaseEventProducer.setWaitTimeout(getInputTimeout());
		// $-- /application/Ofival Base/
		ofivalBaseEventProducer.setPattern(getSecuritiesUploadOfivalPattern());
		// $-- /application/Ofival Base/
		ofivalBaseEventProducer.setIncludeSubdirectories(false);
		// $-- /application/Ofival Base/
		ofivalBaseEventProducer.setCaseSensitivePattern(true);
		// $-- /application/Ofival Base/
		ofivalBaseEventProducer.setDetectAlreadyExistentFiles(true);
		// $-- /application/Ofival Base/
		ofivalBaseEventProducer
				.setCronExpression(getSecuritiesUploadOfivalCron());
		// $-- /application/Ofival Base/
		// $-- /application/Ofival Base/
		ofivalBaseEventProducer.setAllowMultiThreading(false);

		// link event producer to resources

		ofivalBaseEventProducer.init();

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
		errorHandlerBusinessProcess.setUser("SECURITIESUPLOADERRORHANDLER");

		// link process to resources
		// $-- /application/HibernateResource/resource/application/ErrorHandler/sessionFactory
		errorHandlerBusinessProcess.setSessionFactory(hibernateResource_);

		errorHandlerBusinessProcess.initialize();

		// $-- /application/SecurityUploadProcess
		// instantiate and configure 'SecurityUploadProcess' business process
		com.lynxspa.coac.importers.securities.businessprocess.SecurityUploadProcess securityUploadProcessBusinessProcess = Interceptors
				.createBusinessProcess(
						com.lynxspa.coac.importers.securities.businessprocess.SecurityUploadProcess.class,
						"SecurityUploadProcess", 1);

		// add business process to the processes
		this.addBusinessProcess(securityUploadProcessBusinessProcess);

		// $-- /application/SecurityUploadProcess/
		securityUploadProcessBusinessProcess
				.setFundsCommitSize(getSecuritiesUploadFundsCommitSize());
		// $-- /application/SecurityUploadProcess/
		securityUploadProcessBusinessProcess
				.setSecuritiesUploadForceMarketInsert(getSecuritiesUploadForceMarketInsert());
		// $-- /application/SecurityUploadProcess/
		securityUploadProcessBusinessProcess.setLocale(getLocale());
		// $-- /application/SecurityUploadProcess/
		securityUploadProcessBusinessProcess
				.setOfivalPath(getSecuritiesUploadOfivalInputDirectory());
		// $-- /application/SecurityUploadProcess/
		securityUploadProcessBusinessProcess
				.setCustomerCommitSize(getSecuritiesUploadCustomerCommitSize());
		// $-- /application/SecurityUploadProcess/
		securityUploadProcessBusinessProcess
				.setOfivalCommitSize(getSecuritiesUploadOfivalCommitSize());
		// $-- /application/SecurityUploadProcess/
		securityUploadProcessBusinessProcess
				.setFundsPath(getSecuritiesUploadFundsInputDirectory());
		// $-- /application/SecurityUploadProcess/
		securityUploadProcessBusinessProcess
				.setCustomerSecurityUploadPath(getSecuritiesUploadCustomerInputDirectory());
		// $-- /application/SecurityUploadProcess/
		securityUploadProcessBusinessProcess
				.setBloombergCommitSize(getSecuritiesUploadBloombergCommitSize());
		// $-- /application/SecurityUploadProcess/
		securityUploadProcessBusinessProcess
				.setBloombergPath(getSecuritiesUploadBloombergInputDirectory());
		// $-- /application/SecurityUploadProcess/
		securityUploadProcessBusinessProcess
				.setBloombergDebtPath(getSecuritiesUploadBloombergDebtInputDirectory());
		// $-- /application/SecurityUploadProcess/
		securityUploadProcessBusinessProcess.setInputTimeout(getInputTimeout());
		// $-- /application/SecurityUploadProcess/
		// $-- /application/SecurityUploadProcess/
		securityUploadProcessBusinessProcess
				.setBloombergRVNoEuroPath(getSecuritiesUploadBloombergRVNoEuropeInputDirectory());
		// $-- /application/SecurityUploadProcess/
		securityUploadProcessBusinessProcess.setUser("IMPORTUPLOAD");
		// $-- /application/SecurityUploadProcess/
		securityUploadProcessBusinessProcess
				.setBloombergFundsPath(getSecuritiesUploadBloombergFundsInputDirectory());

		// link process to resources
		// $-- /application/HibernateResource/resource/application/SecurityUploadProcess/sessionFactory
		securityUploadProcessBusinessProcess
				.setSessionFactory(hibernateResource_);
		// $-- /application/HibernateStatelessResource/resource/application/SecurityUploadProcess/statelessSessionFactory
		securityUploadProcessBusinessProcess
				.setStatelessSessionFactory(hibernateStatelessResource_);

		securityUploadProcessBusinessProcess.initialize();

		/*
		 * Link event producers to processes, sub-applications or outputs
		 */
		// $-- /application/Bloomberg Base/events/application/SecurityUploadProcess/fileCreated
		BusinessProcess.connect(bloombergBaseEventProducer,
				securityUploadProcessBusinessProcess.asFileCreatedInput());
		// $-- /application/Bloomberg Differences/events/application/SecurityUploadProcess/fileCreated
		BusinessProcess.connect(bloombergDifferencesEventProducer,
				securityUploadProcessBusinessProcess.asFileCreatedInput());
		// $-- /application/Bloomberg Fondos/events/application/SecurityUploadProcess/fileCreated
		BusinessProcess.connect(bloombergFondosEventProducer,
				securityUploadProcessBusinessProcess.asFileCreatedInput());
		// $-- /application/Bloomberg Fondos Diff/events/application/SecurityUploadProcess/fileCreated
		BusinessProcess.connect(bloombergFondosDiffEventProducer,
				securityUploadProcessBusinessProcess.asFileCreatedInput());
		// $-- /application/Bloomberg RF Diff/events/application/SecurityUploadProcess/fileCreated
		BusinessProcess.connect(bloombergRFDiffEventProducer,
				securityUploadProcessBusinessProcess.asFileCreatedInput());
		// $-- /application/Bloomberg RV No Europea/events/application/SecurityUploadProcess/fileCreated
		BusinessProcess.connect(bloombergRVNoEuropeaEventProducer,
				securityUploadProcessBusinessProcess.asFileCreatedInput());
		// $-- /application/Bloomberg RVNE Diff/events/application/SecurityUploadProcess/fileCreated
		BusinessProcess.connect(bloombergRVNEDiffEventProducer,
				securityUploadProcessBusinessProcess.asFileCreatedInput());
		// $-- /application/Bloomberg Renta Fija/events/application/SecurityUploadProcess/fileCreated
		BusinessProcess.connect(bloombergRentaFijaEventProducer,
				securityUploadProcessBusinessProcess.asFileCreatedInput());
		// $-- /application/Customer/events/application/SecurityUploadProcess/fileCreated
		BusinessProcess.connect(customerEventProducer,
				securityUploadProcessBusinessProcess.asFileCreatedInput());
		// $-- /application/Funds Base/events/application/SecurityUploadProcess/fileCreated
		BusinessProcess.connect(fundsBaseEventProducer,
				securityUploadProcessBusinessProcess.asFileCreatedInput());
		// $-- /application/Funds Differences/events/application/SecurityUploadProcess/fileCreated
		BusinessProcess.connect(fundsDifferencesEventProducer,
				securityUploadProcessBusinessProcess.asFileCreatedInput());
		// $-- /application/Ofival Base/events/application/SecurityUploadProcess/fileCreated
		BusinessProcess.connect(ofivalBaseEventProducer,
				securityUploadProcessBusinessProcess.asFileCreatedInput());

		/*
		 * Link inputs to processes, sub-applications or outputs
		 */

		/*
		 * Link processes to other processes, sub-applications or outputs
		 */
		// $-- /application/SecurityUploadProcess/exc/application/ErrorHandler/input
		BusinessProcess.connect(
				securityUploadProcessBusinessProcess.asExcEventProducer(),
				errorHandlerBusinessProcess.asInputInput());

		/*
		 * Link sub applications to other processes, sub-applications or outputs
		 */
	}

	protected void setInitParams() throws ParseException {
		Properties initParams = ConfigParams.getInstance().getProperties();

		setInputTimeout(ConfigurationHelper.parseString(
				initParams.getProperty("inputTimeout"), Integer.class));
		setLocale(ConfigurationHelper.parseString(
				initParams.getProperty("locale"), String.class));
		setNotes(ConfigurationHelper.parseString(
				initParams.getProperty("Notes"), java.lang.String.class));
		setSecuritiesUploadBloombergCommitSize(ConfigurationHelper.parseString(
				initParams.getProperty("securitiesUploadBloombergCommitSize"),
				Integer.class));
		setSecuritiesUploadBloombergCron(ConfigurationHelper.parseString(
				initParams.getProperty("securitiesUploadBloombergCron"),
				String.class));
		setSecuritiesUploadBloombergDebtInputDirectory(ConfigurationHelper
				.parseString(
						initParams
								.getProperty("securitiesUploadBloombergDebtInputDirectory"),
						java.io.File.class));
		setSecuritiesUploadBloombergFundsInputDirectory(ConfigurationHelper
				.parseString(
						initParams
								.getProperty("securitiesUploadBloombergFundsInputDirectory"),
						java.io.File.class));
		setSecuritiesUploadBloombergInputDirectory(ConfigurationHelper
				.parseString(
						initParams
								.getProperty("securitiesUploadBloombergInputDirectory"),
						java.io.File.class));
		setSecuritiesUploadBloombergPattern(ConfigurationHelper.parseString(
				initParams.getProperty("securitiesUploadBloombergPattern"),
				String.class));
		setSecuritiesUploadBloombergPatternDif(ConfigurationHelper.parseString(
				initParams.getProperty("securitiesUploadBloombergPatternDif"),
				String.class));
		setSecuritiesUploadBloombergRVNoEuropeInputDirectory(ConfigurationHelper
				.parseString(
						initParams
								.getProperty("securitiesUploadBloombergRVNoEuropeInputDirectory"),
						java.io.File.class));
		setSecuritiesUploadCustomerCommitSize(ConfigurationHelper.parseString(
				initParams.getProperty("securitiesUploadCustomerCommitSize"),
				Integer.class));
		setSecuritiesUploadCustomerCron(ConfigurationHelper.parseString(
				initParams.getProperty("securitiesUploadCustomerCron"),
				String.class));
		setSecuritiesUploadCustomerInputDirectory(ConfigurationHelper
				.parseString(initParams
						.getProperty("securitiesUploadCustomerInputDirectory"),
						java.io.File.class));
		setSecuritiesUploadCustomerPattern(ConfigurationHelper.parseString(
				initParams.getProperty("securitiesUploadCustomerPattern"),
				String.class));
		setSecuritiesUploadForceMarketInsert(ConfigurationHelper.parseString(
				initParams.getProperty("securitiesUploadForceMarketInsert"),
				Boolean.class));
		setSecuritiesUploadFundsCommitSize(ConfigurationHelper.parseString(
				initParams.getProperty("securitiesUploadFundsCommitSize"),
				Integer.class));
		setSecuritiesUploadFundsCron(ConfigurationHelper.parseString(
				initParams.getProperty("securitiesUploadFundsCron"),
				String.class));
		setSecuritiesUploadFundsInputDirectory(ConfigurationHelper.parseString(
				initParams.getProperty("securitiesUploadFundsInputDirectory"),
				java.io.File.class));
		setSecuritiesUploadFundsPattern(ConfigurationHelper.parseString(
				initParams.getProperty("securitiesUploadFundsPattern"),
				String.class));
		setSecuritiesUploadFundsPatternDif(ConfigurationHelper.parseString(
				initParams.getProperty("securitiesUploadFundsPatternDif"),
				String.class));
		setSecuritiesUploadOfivalCommitSize(ConfigurationHelper.parseString(
				initParams.getProperty("securitiesUploadOfivalCommitSize"),
				Integer.class));
		setSecuritiesUploadOfivalCron(ConfigurationHelper.parseString(
				initParams.getProperty("securitiesUploadOfivalCron"),
				String.class));
		setSecuritiesUploadOfivalInputDirectory(ConfigurationHelper
				.parseString(initParams
						.getProperty("securitiesUploadOfivalInputDirectory"),
						java.io.File.class));
		setSecuritiesUploadOfivalPattern(ConfigurationHelper.parseString(
				initParams.getProperty("securitiesUploadOfivalPattern"),
				String.class));
	}

	public static void main(String[] args) {
		try {
			ApplicationDomain application = Interceptors
					.createApplicationDomain(
							CorporateActionsSecuritiesUploadApplication.class,
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