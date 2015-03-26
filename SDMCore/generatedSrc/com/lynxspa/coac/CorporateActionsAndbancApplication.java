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

@GeneratedType(definitionFile = "src/com/lynxspa/coac/CorporateActions_Andbanc.fpmapplication")
public class CorporateActionsAndbancApplication extends ApplicationDomain {

	public CorporateActionsAndbancApplication(String id) throws Exception {
		super(id);
	}

	/*
	 * Parameters declarations
	 */
	private String authorizationCron_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "authorizationCron")
	public String getAuthorizationCron() {
		return authorizationCron_;
	}

	public void setAuthorizationCron(String value) {
		this.authorizationCron_ = value;
	}

	private java.lang.Integer authorizationSelectSize_;

	@ConfigParam(description = "param2", required = true, dynamic = false, name = "authorizationSelectSize")
	public java.lang.Integer getAuthorizationSelectSize() {
		return authorizationSelectSize_;
	}

	public void setAuthorizationSelectSize(java.lang.Integer value) {
		this.authorizationSelectSize_ = value;
	}

	private String customersLoadCron_;

	@ConfigParam(description = "param1", required = false, dynamic = false, name = "customersLoadCron")
	public String getCustomersLoadCron() {
		return customersLoadCron_;
	}

	public void setCustomersLoadCron(String value) {
		this.customersLoadCron_ = value;
	}

	private java.lang.Integer deleteLogsCommitSize_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "deleteLogsCommitSize")
	public java.lang.Integer getDeleteLogsCommitSize() {
		return deleteLogsCommitSize_;
	}

	public void setDeleteLogsCommitSize(java.lang.Integer value) {
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

	private java.lang.Integer entityDeadLineDays_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "entityDeadLineDays")
	public java.lang.Integer getEntityDeadLineDays() {
		return entityDeadLineDays_;
	}

	public void setEntityDeadLineDays(java.lang.Integer value) {
		this.entityDeadLineDays_ = value;
	}

	private Integer evenDuration_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "evenDuration")
	public Integer getEvenDuration() {
		return evenDuration_;
	}

	public void setEvenDuration(Integer value) {
		this.evenDuration_ = value;
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

	private Integer hourToBeginEven_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "hourToBeginEven")
	public Integer getHourToBeginEven() {
		return hourToBeginEven_;
	}

	public void setHourToBeginEven(Integer value) {
		this.hourToBeginEven_ = value;
	}

	private Integer inputBloombergCommitSize_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "inputBloombergCommitSize")
	public Integer getInputBloombergCommitSize() {
		return inputBloombergCommitSize_;
	}

	public void setInputBloombergCommitSize(Integer value) {
		this.inputBloombergCommitSize_ = value;
	}

	private String inputBloombergCron_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "inputBloombergCron")
	public String getInputBloombergCron() {
		return inputBloombergCron_;
	}

	public void setInputBloombergCron(String value) {
		this.inputBloombergCron_ = value;
	}

	private java.io.File inputBloombergDebtDirectory_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "inputBloombergDebtDirectory")
	public java.io.File getInputBloombergDebtDirectory() {
		return inputBloombergDebtDirectory_;
	}

	public void setInputBloombergDebtDirectory(java.io.File value) {
		this.inputBloombergDebtDirectory_ = value;
	}

	private java.io.File inputBloombergDirectory_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "inputBloombergDirectory")
	public java.io.File getInputBloombergDirectory() {
		return inputBloombergDirectory_;
	}

	public void setInputBloombergDirectory(java.io.File value) {
		this.inputBloombergDirectory_ = value;
	}

	private java.io.File inputBloombergErrorDirectory_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "inputBloombergErrorDirectory")
	public java.io.File getInputBloombergErrorDirectory() {
		return inputBloombergErrorDirectory_;
	}

	public void setInputBloombergErrorDirectory(java.io.File value) {
		this.inputBloombergErrorDirectory_ = value;
	}

	private java.io.File inputBloombergFundsDirectory_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "inputBloombergFundsDirectory")
	public java.io.File getInputBloombergFundsDirectory() {
		return inputBloombergFundsDirectory_;
	}

	public void setInputBloombergFundsDirectory(java.io.File value) {
		this.inputBloombergFundsDirectory_ = value;
	}

	private String inputBloombergPattern_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "inputBloombergPattern")
	public String getInputBloombergPattern() {
		return inputBloombergPattern_;
	}

	public void setInputBloombergPattern(String value) {
		this.inputBloombergPattern_ = value;
	}

	private Integer inputBloombergPerSecCommitSize_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "inputBloombergPerSecCommitSize")
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

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "inputBloombergPerSecCron")
	public String getInputBloombergPerSecCron() {
		return inputBloombergPerSecCron_;
	}

	public void setInputBloombergPerSecCron(String value) {
		this.inputBloombergPerSecCron_ = value;
	}

	private java.io.File inputBloombergPerSecDirectory_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "inputBloombergPerSecDirectory")
	public java.io.File getInputBloombergPerSecDirectory() {
		return inputBloombergPerSecDirectory_;
	}

	public void setInputBloombergPerSecDirectory(java.io.File value) {
		this.inputBloombergPerSecDirectory_ = value;
	}

	private java.io.File inputBloombergPerSecErrorDirectory_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "inputBloombergPerSecErrorDirectory")
	public java.io.File getInputBloombergPerSecErrorDirectory() {
		return inputBloombergPerSecErrorDirectory_;
	}

	public void setInputBloombergPerSecErrorDirectory(java.io.File value) {
		this.inputBloombergPerSecErrorDirectory_ = value;
	}

	private String inputBloombergPerSecPattern_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "inputBloombergPerSecPattern")
	public String getInputBloombergPerSecPattern() {
		return inputBloombergPerSecPattern_;
	}

	public void setInputBloombergPerSecPattern(String value) {
		this.inputBloombergPerSecPattern_ = value;
	}

	private java.io.File inputBloombergRVNoEDirectory_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "inputBloombergRVNoEDirectory")
	public java.io.File getInputBloombergRVNoEDirectory() {
		return inputBloombergRVNoEDirectory_;
	}

	public void setInputBloombergRVNoEDirectory(java.io.File value) {
		this.inputBloombergRVNoEDirectory_ = value;
	}

	private Integer inputIberclearCommitSize_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "inputIberclearCommitSize")
	public Integer getInputIberclearCommitSize() {
		return inputIberclearCommitSize_;
	}

	public void setInputIberclearCommitSize(Integer value) {
		this.inputIberclearCommitSize_ = value;
	}

	private String inputIberclearCron_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "inputIberclearCron")
	public String getInputIberclearCron() {
		return inputIberclearCron_;
	}

	public void setInputIberclearCron(String value) {
		this.inputIberclearCron_ = value;
	}

	private java.io.File inputIberclearDirectory_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "inputIberclearDirectory")
	public java.io.File getInputIberclearDirectory() {
		return inputIberclearDirectory_;
	}

	public void setInputIberclearDirectory(java.io.File value) {
		this.inputIberclearDirectory_ = value;
	}

	private java.io.File inputIberclearErrorDirectory_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "inputIberclearErrorDirectory")
	public java.io.File getInputIberclearErrorDirectory() {
		return inputIberclearErrorDirectory_;
	}

	public void setInputIberclearErrorDirectory(java.io.File value) {
		this.inputIberclearErrorDirectory_ = value;
	}

	private String inputIberclearPattern_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "inputIberclearPattern")
	public String getInputIberclearPattern() {
		return inputIberclearPattern_;
	}

	public void setInputIberclearPattern(String value) {
		this.inputIberclearPattern_ = value;
	}

	private Integer inputInversisCommitSize_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "inputInversisCommitSize")
	public Integer getInputInversisCommitSize() {
		return inputInversisCommitSize_;
	}

	public void setInputInversisCommitSize(Integer value) {
		this.inputInversisCommitSize_ = value;
	}

	private String inputInversisCron_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "inputInversisCron")
	public String getInputInversisCron() {
		return inputInversisCron_;
	}

	public void setInputInversisCron(String value) {
		this.inputInversisCron_ = value;
	}

	private java.io.File inputInversisDirectory_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "inputInversisDirectory")
	public java.io.File getInputInversisDirectory() {
		return inputInversisDirectory_;
	}

	public void setInputInversisDirectory(java.io.File value) {
		this.inputInversisDirectory_ = value;
	}

	private java.io.File inputInversisErrorDirectory_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "inputInversisErrorDirectory")
	public java.io.File getInputInversisErrorDirectory() {
		return inputInversisErrorDirectory_;
	}

	public void setInputInversisErrorDirectory(java.io.File value) {
		this.inputInversisErrorDirectory_ = value;
	}

	private String inputInversisPattern_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "inputInversisPattern")
	public String getInputInversisPattern() {
		return inputInversisPattern_;
	}

	public void setInputInversisPattern(String value) {
		this.inputInversisPattern_ = value;
	}

	private Integer inputOfivalCommitSize_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "inputOfivalCommitSize")
	public Integer getInputOfivalCommitSize() {
		return inputOfivalCommitSize_;
	}

	public void setInputOfivalCommitSize(Integer value) {
		this.inputOfivalCommitSize_ = value;
	}

	private String inputOfivalCron_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "inputOfivalCron")
	public String getInputOfivalCron() {
		return inputOfivalCron_;
	}

	public void setInputOfivalCron(String value) {
		this.inputOfivalCron_ = value;
	}

	private java.io.File inputOfivalDirectory_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "inputOfivalDirectory")
	public java.io.File getInputOfivalDirectory() {
		return inputOfivalDirectory_;
	}

	public void setInputOfivalDirectory(java.io.File value) {
		this.inputOfivalDirectory_ = value;
	}

	private java.io.File inputOfivalErrorDirectory_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "inputOfivalErrorDirectory")
	public java.io.File getInputOfivalErrorDirectory() {
		return inputOfivalErrorDirectory_;
	}

	public void setInputOfivalErrorDirectory(java.io.File value) {
		this.inputOfivalErrorDirectory_ = value;
	}

	private String inputOfivalPattern_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "inputOfivalPattern")
	public String getInputOfivalPattern() {
		return inputOfivalPattern_;
	}

	public void setInputOfivalPattern(String value) {
		this.inputOfivalPattern_ = value;
	}

	private Integer inputSwiftCommitSize_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "inputSwiftCommitSize")
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

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "inputSwiftErrorDirectory")
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

	private String livetimeHistorifiedCron_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "livetimeHistorifiedCron")
	public String getLivetimeHistorifiedCron() {
		return livetimeHistorifiedCron_;
	}

	public void setLivetimeHistorifiedCron(String value) {
		this.livetimeHistorifiedCron_ = value;
	}

	private String locale_;

	@ConfigParam(description = "locale", required = true, dynamic = false, name = "locale")
	public String getLocale() {
		return locale_;
	}

	public void setLocale(String value) {
		this.locale_ = value;
	}

	private String masterRecordCron_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "masterRecordCron")
	public String getMasterRecordCron() {
		return masterRecordCron_;
	}

	public void setMasterRecordCron(String value) {
		this.masterRecordCron_ = value;
	}

	private java.lang.Integer masterRecordSelectSize_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "masterRecordSelectSize")
	public java.lang.Integer getMasterRecordSelectSize() {
		return masterRecordSelectSize_;
	}

	public void setMasterRecordSelectSize(java.lang.Integer value) {
		this.masterRecordSelectSize_ = value;
	}

	private String matchingCron_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "matchingCron")
	public String getMatchingCron() {
		return matchingCron_;
	}

	public void setMatchingCron(String value) {
		this.matchingCron_ = value;
	}

	private java.lang.Integer matchingSelectSize_;

	@ConfigParam(description = "param2", required = true, dynamic = false, name = "matchingSelectSize")
	public java.lang.Integer getMatchingSelectSize() {
		return matchingSelectSize_;
	}

	public void setMatchingSelectSize(java.lang.Integer value) {
		this.matchingSelectSize_ = value;
	}

	private String monitorCron_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "monitorCron")
	public String getMonitorCron() {
		return monitorCron_;
	}

	public void setMonitorCron(String value) {
		this.monitorCron_ = value;
	}

	private String normalizeCron_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "normalizeCron")
	public String getNormalizeCron() {
		return normalizeCron_;
	}

	public void setNormalizeCron(String value) {
		this.normalizeCron_ = value;
	}

	private java.lang.Integer normalizeSelectSize_;

	@ConfigParam(description = "param2", required = true, dynamic = false, name = "normalizeSelectSize")
	public java.lang.Integer getNormalizeSelectSize() {
		return normalizeSelectSize_;
	}

	public void setNormalizeSelectSize(java.lang.Integer value) {
		this.normalizeSelectSize_ = value;
	}

	private String notificationManagerEventCron_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "notificationManagerEventCron")
	public String getNotificationManagerEventCron() {
		return notificationManagerEventCron_;
	}

	public void setNotificationManagerEventCron(String value) {
		this.notificationManagerEventCron_ = value;
	}

	private java.lang.Integer notificationManagerMaxResults_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "notificationManagerMaxResults")
	public java.lang.Integer getNotificationManagerMaxResults() {
		return notificationManagerMaxResults_;
	}

	public void setNotificationManagerMaxResults(java.lang.Integer value) {
		this.notificationManagerMaxResults_ = value;
	}

	private Integer pageSize_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "pageSize")
	public Integer getPageSize() {
		return pageSize_;
	}

	public void setPageSize(Integer value) {
		this.pageSize_ = value;
	}

	private String planningCronnExpression_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "planningCronnExpression")
	public String getPlanningCronnExpression() {
		return planningCronnExpression_;
	}

	public void setPlanningCronnExpression(String value) {
		this.planningCronnExpression_ = value;
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

	private Integer previosHoursForAlarm_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "previosHoursForAlarm")
	public Integer getPreviosHoursForAlarm() {
		return previosHoursForAlarm_;
	}

	public void setPreviosHoursForAlarm(Integer value) {
		this.previosHoursForAlarm_ = value;
	}

	private String processMessageCron_;

	@ConfigParam(description = "processMessageCron", required = true, dynamic = false, name = "processMessageCron")
	public String getProcessMessageCron() {
		return processMessageCron_;
	}

	public void setProcessMessageCron(String value) {
		this.processMessageCron_ = value;
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

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "securitiesUploadBloombergCron")
	public String getSecuritiesUploadBloombergCron() {
		return securitiesUploadBloombergCron_;
	}

	public void setSecuritiesUploadBloombergCron(String value) {
		this.securitiesUploadBloombergCron_ = value;
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

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "securitiesUploadBloombergPattern")
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

	private Integer securitiesUploadCustomerCommitSize_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "securitiesUploadCustomerCommitSize")
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

	@ConfigParam(description = "param3", required = true, dynamic = false, name = "securitiesUploadCustomerInputDirectory")
	public java.io.File getSecuritiesUploadCustomerInputDirectory() {
		return securitiesUploadCustomerInputDirectory_;
	}

	public void setSecuritiesUploadCustomerInputDirectory(java.io.File value) {
		this.securitiesUploadCustomerInputDirectory_ = value;
	}

	private String securitiesUploadCustomerPattern_;

	@ConfigParam(description = "param2", required = true, dynamic = false, name = "securitiesUploadCustomerPattern")
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

	private Integer securityPortfolioHistorificationCommitSize_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "securityPortfolioHistorificationCommitSize")
	public Integer getSecurityPortfolioHistorificationCommitSize() {
		return securityPortfolioHistorificationCommitSize_;
	}

	public void setSecurityPortfolioHistorificationCommitSize(Integer value) {
		this.securityPortfolioHistorificationCommitSize_ = value;
	}

	private String securityPortfolioHistorificationCron_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "securityPortfolioHistorificationCron")
	public String getSecurityPortfolioHistorificationCron() {
		return securityPortfolioHistorificationCron_;
	}

	public void setSecurityPortfolioHistorificationCron(String value) {
		this.securityPortfolioHistorificationCron_ = value;
	}

	private String sendBackOfficeCron_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "sendBackOfficeCron")
	public String getSendBackOfficeCron() {
		return sendBackOfficeCron_;
	}

	public void setSendBackOfficeCron(String value) {
		this.sendBackOfficeCron_ = value;
	}

	private String sendBackOfficeDestinationFolder_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "sendBackOfficeDestinationFolder")
	public String getSendBackOfficeDestinationFolder() {
		return sendBackOfficeDestinationFolder_;
	}

	public void setSendBackOfficeDestinationFolder(String value) {
		this.sendBackOfficeDestinationFolder_ = value;
	}

	private Integer sendBackOfficeSelectSize_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "sendBackOfficeSelectSize")
	public Integer getSendBackOfficeSelectSize() {
		return sendBackOfficeSelectSize_;
	}

	public void setSendBackOfficeSelectSize(Integer value) {
		this.sendBackOfficeSelectSize_ = value;
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

	private java.io.File tempPathFiles_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "tempPathFiles")
	public java.io.File getTempPathFiles() {
		return tempPathFiles_;
	}

	public void setTempPathFiles(java.io.File value) {
		this.tempPathFiles_ = value;
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
		// $-- /application/CorporateActionNotificationManagerApplication
		// instantiate and configure 'CorporateActionNotificationManagerApplication' sub application
		com.lynxspa.coac.CorporateActionNotificationManagerApplication corporateActionNotificationManagerApplicationApplication = Interceptors
				.createApplicationDomain(
						com.lynxspa.coac.CorporateActionNotificationManagerApplication.class,
						"CorporateActionNotificationManagerApplication");

		// add the newly created application to domain
		this.addSubApplication(corporateActionNotificationManagerApplicationApplication);

		// $-- /application/CorporateActionNotificationManagerApplication/
		corporateActionNotificationManagerApplicationApplication
				.setSmtpPort(getSmtpPort());
		// $-- /application/CorporateActionNotificationManagerApplication/
		corporateActionNotificationManagerApplicationApplication
				.setHourToBeginEven(getHourToBeginEven());
		// $-- /application/CorporateActionNotificationManagerApplication/
		corporateActionNotificationManagerApplicationApplication
				.setNotificationManagerEventCron(getNotificationManagerEventCron());
		// $-- /application/CorporateActionNotificationManagerApplication/
		corporateActionNotificationManagerApplicationApplication
				.setMaxResultsToLoad(getNotificationManagerMaxResults());
		// $-- /application/CorporateActionNotificationManagerApplication/
		corporateActionNotificationManagerApplicationApplication
				.setSmtpHost(getSmtpHost());
		// $-- /application/CorporateActionNotificationManagerApplication/
		corporateActionNotificationManagerApplicationApplication
				.setSmtpPassword(getSmtpPassword());
		// $-- /application/CorporateActionNotificationManagerApplication/
		corporateActionNotificationManagerApplicationApplication
				.setPreviosHoursForAlarm(getPreviosHoursForAlarm());
		// $-- /application/CorporateActionNotificationManagerApplication/
		corporateActionNotificationManagerApplicationApplication
				.setLocale(getLocale());
		// $-- /application/CorporateActionNotificationManagerApplication/
		corporateActionNotificationManagerApplicationApplication
				.setSmtpUser(getSmtpUser());
		// $-- /application/CorporateActionNotificationManagerApplication/
		corporateActionNotificationManagerApplicationApplication
				.setTimeZone(getTimeZone());
		// $-- /application/CorporateActionNotificationManagerApplication/
		// $-- /application/CorporateActionNotificationManagerApplication/
		corporateActionNotificationManagerApplicationApplication
				.setSmtpFrom(getSmtpFrom());
		// $-- /application/CorporateActionNotificationManagerApplication/
		corporateActionNotificationManagerApplicationApplication
				.setEvenDuration(getEvenDuration());

		// link sub application to resources
		// $-- /application/HibernateResource/resource/application/CorporateActionNotificationManagerApplication/hibernateResource
		corporateActionNotificationManagerApplicationApplication
				.setHibernateResource(hibernateResource_);
		// $-- /application/HibernateStatelessResource/resource/application/CorporateActionNotificationManagerApplication/hibernateStatelessResource
		corporateActionNotificationManagerApplicationApplication
				.setHibernateStatelessResource(hibernateStatelessResource_);

		corporateActionNotificationManagerApplicationApplication.initialize();
		// $-- /application/CorporateActionsAuthorizationApplication
		// instantiate and configure 'CorporateActionsAuthorizationApplication' sub application
		com.lynxspa.coac.CorporateActionsAuthorizationApplication corporateActionsAuthorizationApplicationApplication = Interceptors
				.createApplicationDomain(
						com.lynxspa.coac.CorporateActionsAuthorizationApplication.class,
						"CorporateActionsAuthorizationApplication");

		// add the newly created application to domain
		this.addSubApplication(corporateActionsAuthorizationApplicationApplication);

		// $-- /application/CorporateActionsAuthorizationApplication/
		corporateActionsAuthorizationApplicationApplication
				.setAuthorizationSelectSize(getAuthorizationSelectSize());
		// $-- /application/CorporateActionsAuthorizationApplication/
		corporateActionsAuthorizationApplicationApplication
				.setLocale(getLocale());
		// $-- /application/CorporateActionsAuthorizationApplication/
		corporateActionsAuthorizationApplicationApplication
				.setAuthorizationCron(getAuthorizationCron());
		// $-- /application/CorporateActionsAuthorizationApplication/

		// link sub application to resources
		// $-- /application/HibernateResource/resource/application/CorporateActionsAuthorizationApplication/hibernateResource
		corporateActionsAuthorizationApplicationApplication
				.setHibernateResource(hibernateResource_);

		corporateActionsAuthorizationApplicationApplication.initialize();
		// $-- /application/CorporateActionsExportEventsApplication
		// instantiate and configure 'CorporateActionsExportEventsApplication' sub application
		com.lynxspa.coac.CorporateActionsExportEventsApplication corporateActionsExportEventsApplicationApplication = Interceptors
				.createApplicationDomain(
						com.lynxspa.coac.CorporateActionsExportEventsApplication.class,
						"CorporateActionsExportEventsApplication");

		// add the newly created application to domain
		this.addSubApplication(corporateActionsExportEventsApplicationApplication);

		// $-- /application/CorporateActionsExportEventsApplication/
		corporateActionsExportEventsApplicationApplication
				.setMaxResultsToLoad(getSendBackOfficeSelectSize());
		// $-- /application/CorporateActionsExportEventsApplication/
		corporateActionsExportEventsApplicationApplication
				.setExportEventCron(getSendBackOfficeCron());
		// $-- /application/CorporateActionsExportEventsApplication/
		corporateActionsExportEventsApplicationApplication
				.setLocale(getLocale());
		// $-- /application/CorporateActionsExportEventsApplication/

		// link sub application to resources
		// $-- /application/HibernateResource/resource/application/CorporateActionsExportEventsApplication/hibernateResource
		corporateActionsExportEventsApplicationApplication
				.setHibernateResource(hibernateResource_);

		corporateActionsExportEventsApplicationApplication.initialize();
		// $-- /application/CorporateActionsHistorificationApplication
		// instantiate and configure 'CorporateActionsHistorificationApplication' sub application
		com.lynxspa.coac.CorporateActionsHistorificationApplication corporateActionsHistorificationApplicationApplication = Interceptors
				.createApplicationDomain(
						com.lynxspa.coac.CorporateActionsHistorificationApplication.class,
						"CorporateActionsHistorificationApplication");

		// add the newly created application to domain
		this.addSubApplication(corporateActionsHistorificationApplicationApplication);

		// $-- /application/CorporateActionsHistorificationApplication/
		corporateActionsHistorificationApplicationApplication
				.setSecurityPortfolioHistorificationCommitSize(getSecurityPortfolioHistorificationCommitSize());
		// $-- /application/CorporateActionsHistorificationApplication/
		corporateActionsHistorificationApplicationApplication
				.setDeleteLogsCommitSize(getDeleteLogsCommitSize());
		// $-- /application/CorporateActionsHistorificationApplication/
		corporateActionsHistorificationApplicationApplication
				.setLocale(getLocale());
		// $-- /application/CorporateActionsHistorificationApplication/
		corporateActionsHistorificationApplicationApplication
				.setHistorificationCron(getHistorificationCron());
		// $-- /application/CorporateActionsHistorificationApplication/
		corporateActionsHistorificationApplicationApplication
				.setEventsHistorificationCommitSize(getEventsHistorificationCommitSize());
		// $-- /application/CorporateActionsHistorificationApplication/
		corporateActionsHistorificationApplicationApplication
				.setSecurityPortfolioHistorificationCronn(getSecurityPortfolioHistorificationCron());
		// $-- /application/CorporateActionsHistorificationApplication/
		corporateActionsHistorificationApplicationApplication
				.setHistorificationCommitSize(getHistorificationCommitSize());
		// $-- /application/CorporateActionsHistorificationApplication/
		corporateActionsHistorificationApplicationApplication
				.setEventsHistorificationCron(getEventsHistorificationCron());
		// $-- /application/CorporateActionsHistorificationApplication/
		// $-- /application/CorporateActionsHistorificationApplication/
		corporateActionsHistorificationApplicationApplication
				.setDeleteLogsCronn(getDeleteLogsCronn());

		// link sub application to resources
		// $-- /application/HibernateResource/resource/application/CorporateActionsHistorificationApplication/hibernateResource
		corporateActionsHistorificationApplicationApplication
				.setHibernateResource(hibernateResource_);
		// $-- /application/HibernateStatelessResource/resource/application/CorporateActionsHistorificationApplication/hibernateStatelessResource
		corporateActionsHistorificationApplicationApplication
				.setHibernateStatelessResource(hibernateStatelessResource_);

		corporateActionsHistorificationApplicationApplication.initialize();
		// $-- /application/CorporateActionsImporter
		// instantiate and configure 'CorporateActionsImporter' sub application
		com.lynxspa.coac.CorporateActionsImporterApplication corporateActionsImporterApplication = Interceptors
				.createApplicationDomain(
						com.lynxspa.coac.CorporateActionsImporterApplication.class,
						"CorporateActionsImporter");

		// add the newly created application to domain
		this.addSubApplication(corporateActionsImporterApplication);

		// $-- /application/CorporateActionsImporter/
		corporateActionsImporterApplication
				.setInputIberclearPattern(getInputIberclearPattern());
		// $-- /application/CorporateActionsImporter/
		corporateActionsImporterApplication
				.setInputSwiftDirectory(getInputSwiftDirectory());
		// $-- /application/CorporateActionsImporter/
		corporateActionsImporterApplication
				.setInputOfivalCron(getInputOfivalCron());
		// $-- /application/CorporateActionsImporter/
		corporateActionsImporterApplication
				.setInputInversisCommitSize(getInputInversisCommitSize());
		// $-- /application/CorporateActionsImporter/
		corporateActionsImporterApplication.setLocale(getLocale());
		// $-- /application/CorporateActionsImporter/
		corporateActionsImporterApplication
				.setInputBloombergCron(getInputBloombergCron());
		// $-- /application/CorporateActionsImporter/
		corporateActionsImporterApplication
				.setInputBloombergErrorDirectory(getInputBloombergErrorDirectory());
		// $-- /application/CorporateActionsImporter/
		corporateActionsImporterApplication
				.setInputIberclearErrorDirectory(getInputIberclearErrorDirectory());
		// $-- /application/CorporateActionsImporter/
		corporateActionsImporterApplication
				.setInputSwiftErrorDirectory(getInputSwiftErrorDirectory());
		// $-- /application/CorporateActionsImporter/
		corporateActionsImporterApplication
				.setInputInversisErrorDirectory(getInputInversisErrorDirectory());
		// $-- /application/CorporateActionsImporter/
		corporateActionsImporterApplication.setInputTimeout(getInputTimeout());
		// $-- /application/CorporateActionsImporter/
		corporateActionsImporterApplication
				.setInputBloombergPerSecCommitSize(getInputBloombergPerSecCommitSize());
		// $-- /application/CorporateActionsImporter/
		corporateActionsImporterApplication
				.setInputSwiftPattern(getInputSwiftPattern());
		// $-- /application/CorporateActionsImporter/
		corporateActionsImporterApplication
				.setInputBloombergPattern(getInputBloombergPattern());
		// $-- /application/CorporateActionsImporter/
		corporateActionsImporterApplication
				.setInputOfivalDirectory(getInputOfivalDirectory());
		// $-- /application/CorporateActionsImporter/
		corporateActionsImporterApplication
				.setInputInversisCron(getInputInversisCron());
		// $-- /application/CorporateActionsImporter/
		corporateActionsImporterApplication
				.setInputBloombergPerSecCorporatesErrorDirectory(getInputBloombergPerSecCorporatesErrorDirectory());
		// $-- /application/CorporateActionsImporter/
		corporateActionsImporterApplication
				.setInputIberclearCron(getInputIberclearCron());
		// $-- /application/CorporateActionsImporter/
		corporateActionsImporterApplication
				.setInputBloombergPerSecCorporatesCron(getInputBloombergPerSecCorporatesCron());
		// $-- /application/CorporateActionsImporter/
		corporateActionsImporterApplication
				.setInputIberclearDirectory(getInputIberclearDirectory());
		// $-- /application/CorporateActionsImporter/
		corporateActionsImporterApplication
				.setInputBloombergCommitSize(getInputBloombergCommitSize());
		// $-- /application/CorporateActionsImporter/
		corporateActionsImporterApplication
				.setInputBloombergDirectory(getInputBloombergDirectory());
		// $-- /application/CorporateActionsImporter/
		corporateActionsImporterApplication
				.setInputBloombergPerSecCorporatesDirectory(getInputBloombergPerSecCorporatesDirectory());
		// $-- /application/CorporateActionsImporter/
		corporateActionsImporterApplication
				.setInputOfivalCommitSize(getInputOfivalCommitSize());
		// $-- /application/CorporateActionsImporter/
		corporateActionsImporterApplication
				.setInputOfivalErrorDirectory(getInputOfivalErrorDirectory());
		// $-- /application/CorporateActionsImporter/
		corporateActionsImporterApplication
				.setInputBloombergPerSecDirectory(getInputBloombergPerSecDirectory());
		// $-- /application/CorporateActionsImporter/
		corporateActionsImporterApplication
				.setInputBloombergPerSecErrorDirectory(getInputBloombergPerSecErrorDirectory());
		// $-- /application/CorporateActionsImporter/
		corporateActionsImporterApplication
				.setInputOfivalPattern(getInputOfivalPattern());
		// $-- /application/CorporateActionsImporter/
		corporateActionsImporterApplication
				.setInputIberclearCommitSize(getInputIberclearCommitSize());
		// $-- /application/CorporateActionsImporter/
		corporateActionsImporterApplication
				.setInputInversisPattern(getInputInversisPattern());
		// $-- /application/CorporateActionsImporter/
		corporateActionsImporterApplication
				.setInputBloombergPerSecCorporatesPattern(getInputBloombergPerSecCorporatesPattern());
		// $-- /application/CorporateActionsImporter/
		corporateActionsImporterApplication
				.setInputBloombergPerSecPattern(getInputBloombergPerSecPattern());
		// $-- /application/CorporateActionsImporter/
		corporateActionsImporterApplication
				.setInputSwiftCron(getInputSwiftCron());
		// $-- /application/CorporateActionsImporter/
		corporateActionsImporterApplication
				.setInputSwiftCommitSize(getInputSwiftCommitSize());
		// $-- /application/CorporateActionsImporter/
		corporateActionsImporterApplication
				.setInputBloombergPerSecCron(getInputBloombergPerSecCron());
		// $-- /application/CorporateActionsImporter/
		// $-- /application/CorporateActionsImporter/
		corporateActionsImporterApplication
				.setInputInversisDirectory(getInputInversisDirectory());
		// $-- /application/CorporateActionsImporter/
		corporateActionsImporterApplication
				.setInputBloombergPerSecCorporatesCommitSize(getInputBloombergPerSecCorporatesCommitSize());

		// link sub application to resources
		// $-- /application/HibernateResource/resource/application/CorporateActionsImporter/hibernateResource
		corporateActionsImporterApplication
				.setHibernateResource(hibernateResource_);
		// $-- /application/HibernateStatelessResource/resource/application/CorporateActionsImporter/hibernateStatelessResource
		corporateActionsImporterApplication
				.setHibernateStatelessResource(hibernateStatelessResource_);

		corporateActionsImporterApplication.initialize();
		// $-- /application/CorporateActionsLifeTimeHistorifiedApplication
		// instantiate and configure 'CorporateActionsLifeTimeHistorifiedApplication' sub application
		com.lynxspa.coac.CorporateActionsLifeTimeHistorifiedApplication corporateActionsLifeTimeHistorifiedApplicationApplication = Interceptors
				.createApplicationDomain(
						com.lynxspa.coac.CorporateActionsLifeTimeHistorifiedApplication.class,
						"CorporateActionsLifeTimeHistorifiedApplication");

		// add the newly created application to domain
		this.addSubApplication(corporateActionsLifeTimeHistorifiedApplicationApplication);

		// $-- /application/CorporateActionsLifeTimeHistorifiedApplication/
		corporateActionsLifeTimeHistorifiedApplicationApplication
				.setLocale(getLocale());
		// $-- /application/CorporateActionsLifeTimeHistorifiedApplication/
		// $-- /application/CorporateActionsLifeTimeHistorifiedApplication/
		corporateActionsLifeTimeHistorifiedApplicationApplication
				.setLivetimeHistorifiedCron(getLivetimeHistorifiedCron());

		// link sub application to resources
		// $-- /application/HibernateResource/resource/application/CorporateActionsLifeTimeHistorifiedApplication/hibernateResource
		corporateActionsLifeTimeHistorifiedApplicationApplication
				.setHibernateResource(hibernateResource_);
		// $-- /application/HibernateStatelessResource/resource/application/CorporateActionsLifeTimeHistorifiedApplication/hibernateStatelessResource
		corporateActionsLifeTimeHistorifiedApplicationApplication
				.setHibernateStatelessResource(hibernateStatelessResource_);

		corporateActionsLifeTimeHistorifiedApplicationApplication.initialize();
		// $-- /application/CorporateActionsLoadExternalDataApplication
		// instantiate and configure 'CorporateActionsLoadExternalDataApplication' sub application
		com.lynxspa.coac.CorporateActionsLoadExternalDataApplication corporateActionsLoadExternalDataApplicationApplication = Interceptors
				.createApplicationDomain(
						com.lynxspa.coac.CorporateActionsLoadExternalDataApplication.class,
						"CorporateActionsLoadExternalDataApplication");

		// add the newly created application to domain
		this.addSubApplication(corporateActionsLoadExternalDataApplicationApplication);

		// $-- /application/CorporateActionsLoadExternalDataApplication/
		corporateActionsLoadExternalDataApplicationApplication
				.setCustomersLoadCron(getCustomersLoadCron());
		// $-- /application/CorporateActionsLoadExternalDataApplication/
		corporateActionsLoadExternalDataApplicationApplication
				.setLocale(getLocale());
		// $-- /application/CorporateActionsLoadExternalDataApplication/
		corporateActionsLoadExternalDataApplicationApplication
				.setPageSize(getPageSize());
		// $-- /application/CorporateActionsLoadExternalDataApplication/

		// link sub application to resources
		// $-- /application/HibernateResource/resource/application/CorporateActionsLoadExternalDataApplication/hibernateResource
		corporateActionsLoadExternalDataApplicationApplication
				.setHibernateResource(hibernateResource_);
		// $-- /application/HibernateStatelessResource/resource/application/CorporateActionsLoadExternalDataApplication/hibernateStatelessResource
		corporateActionsLoadExternalDataApplicationApplication
				.setHibernateStatelessResource(hibernateStatelessResource_);

		corporateActionsLoadExternalDataApplicationApplication.initialize();
		// $-- /application/CorporateActionsMasterRecordApplication
		// instantiate and configure 'CorporateActionsMasterRecordApplication' sub application
		com.lynxspa.coac.CorporateActionsMasterRecordApplication corporateActionsMasterRecordApplicationApplication = Interceptors
				.createApplicationDomain(
						com.lynxspa.coac.CorporateActionsMasterRecordApplication.class,
						"CorporateActionsMasterRecordApplication");

		// add the newly created application to domain
		this.addSubApplication(corporateActionsMasterRecordApplicationApplication);

		// $-- /application/CorporateActionsMasterRecordApplication/
		corporateActionsMasterRecordApplicationApplication
				.setMasterRecordCron(getMasterRecordCron());
		// $-- /application/CorporateActionsMasterRecordApplication/
		corporateActionsMasterRecordApplicationApplication
				.setLocale(getLocale());
		// $-- /application/CorporateActionsMasterRecordApplication/
		corporateActionsMasterRecordApplicationApplication
				.setMasterRecordSelectSize(getMasterRecordSelectSize());
		// $-- /application/CorporateActionsMasterRecordApplication/
		// $-- /application/CorporateActionsMasterRecordApplication/
		corporateActionsMasterRecordApplicationApplication
				.setEntityDeadLineDays(getEntityDeadLineDays());

		// link sub application to resources
		// $-- /application/HibernateResource/resource/application/CorporateActionsMasterRecordApplication/hibernateResource
		corporateActionsMasterRecordApplicationApplication
				.setHibernateResource(hibernateResource_);

		corporateActionsMasterRecordApplicationApplication.initialize();
		// $-- /application/CorporateActionsMatcherApplication
		// instantiate and configure 'CorporateActionsMatcherApplication' sub application
		com.lynxspa.coac.CorporateActionsMatcherApplication corporateActionsMatcherApplicationApplication = Interceptors
				.createApplicationDomain(
						com.lynxspa.coac.CorporateActionsMatcherApplication.class,
						"CorporateActionsMatcherApplication");

		// add the newly created application to domain
		this.addSubApplication(corporateActionsMatcherApplicationApplication);

		// $-- /application/CorporateActionsMatcherApplication/
		corporateActionsMatcherApplicationApplication.setLocale(getLocale());
		// $-- /application/CorporateActionsMatcherApplication/
		// $-- /application/CorporateActionsMatcherApplication/
		corporateActionsMatcherApplicationApplication
				.setMatchingSelectSize(getMatchingSelectSize());
		// $-- /application/CorporateActionsMatcherApplication/
		corporateActionsMatcherApplicationApplication
				.setMatchingCron(getMatchingCron());

		// link sub application to resources
		// $-- /application/HibernateResource/resource/application/CorporateActionsMatcherApplication/hibernateResource
		corporateActionsMatcherApplicationApplication
				.setHibernateResource(hibernateResource_);

		corporateActionsMatcherApplicationApplication.initialize();
		// $-- /application/CorporateActionsMonitorApplication
		// instantiate and configure 'CorporateActionsMonitorApplication' sub application
		com.lynxspa.coac.CorporateActionsMonitorApplication corporateActionsMonitorApplicationApplication = Interceptors
				.createApplicationDomain(
						com.lynxspa.coac.CorporateActionsMonitorApplication.class,
						"CorporateActionsMonitorApplication");

		// add the newly created application to domain
		this.addSubApplication(corporateActionsMonitorApplicationApplication);

		// $-- /application/CorporateActionsMonitorApplication/
		corporateActionsMonitorApplicationApplication
				.setMonitorCron(getMonitorCron());
		// $-- /application/CorporateActionsMonitorApplication/
		corporateActionsMonitorApplicationApplication.setLocale(getLocale());
		// $-- /application/CorporateActionsMonitorApplication/

		// link sub application to resources
		// $-- /application/HibernateResource/resource/application/CorporateActionsMonitorApplication/hibernateResource
		corporateActionsMonitorApplicationApplication
				.setHibernateResource(hibernateResource_);

		corporateActionsMonitorApplicationApplication.initialize();
		// $-- /application/CorporateActionsNormalizerApplication
		// instantiate and configure 'CorporateActionsNormalizerApplication' sub application
		com.lynxspa.coac.CorporateActionsNormalizerApplication corporateActionsNormalizerApplicationApplication = Interceptors
				.createApplicationDomain(
						com.lynxspa.coac.CorporateActionsNormalizerApplication.class,
						"CorporateActionsNormalizerApplication");

		// add the newly created application to domain
		this.addSubApplication(corporateActionsNormalizerApplicationApplication);

		// $-- /application/CorporateActionsNormalizerApplication/
		corporateActionsNormalizerApplicationApplication.setLocale(getLocale());
		// $-- /application/CorporateActionsNormalizerApplication/
		corporateActionsNormalizerApplicationApplication
				.setNormalizeCron(getNormalizeCron());
		// $-- /application/CorporateActionsNormalizerApplication/
		corporateActionsNormalizerApplicationApplication
				.setNormalizeSelectSize(getNormalizeSelectSize());
		// $-- /application/CorporateActionsNormalizerApplication/

		// link sub application to resources
		// $-- /application/HibernateResource/resource/application/CorporateActionsNormalizerApplication/hibernateResource
		corporateActionsNormalizerApplicationApplication
				.setHibernateResource(hibernateResource_);

		corporateActionsNormalizerApplicationApplication.initialize();
		// $-- /application/CorporateActionsPlanningApplication
		// instantiate and configure 'CorporateActionsPlanningApplication' sub application
		com.lynxspa.coac.CorporateActionsPlanningApplication corporateActionsPlanningApplicationApplication = Interceptors
				.createApplicationDomain(
						com.lynxspa.coac.CorporateActionsPlanningApplication.class,
						"CorporateActionsPlanningApplication");

		// add the newly created application to domain
		this.addSubApplication(corporateActionsPlanningApplicationApplication);

		// $-- /application/CorporateActionsPlanningApplication/
		corporateActionsPlanningApplicationApplication
				.setPlanningCronnExpression(getPlanningCronnExpression());
		// $-- /application/CorporateActionsPlanningApplication/
		corporateActionsPlanningApplicationApplication.setLocale(getLocale());
		// $-- /application/CorporateActionsPlanningApplication/
		corporateActionsPlanningApplicationApplication
				.setTempPathFile(getTempPathFiles());
		// $-- /application/CorporateActionsPlanningApplication/

		// link sub application to resources
		// $-- /application/HibernateResource/resource/application/CorporateActionsPlanningApplication/hibernateResource
		corporateActionsPlanningApplicationApplication
				.setHibernateResource(hibernateResource_);
		// $-- /application/HibernateStatelessResource/resource/application/CorporateActionsPlanningApplication/hibernateStatelessResource
		corporateActionsPlanningApplicationApplication
				.setHibernateStatelessResource(hibernateStatelessResource_);

		corporateActionsPlanningApplicationApplication.initialize();
		// $-- /application/CorporateActionsPrenormalizerApplication
		// instantiate and configure 'CorporateActionsPrenormalizerApplication' sub application
		com.lynxspa.coac.CorporateActionsPrenormalizerApplication corporateActionsPrenormalizerApplicationApplication = Interceptors
				.createApplicationDomain(
						com.lynxspa.coac.CorporateActionsPrenormalizerApplication.class,
						"CorporateActionsPrenormalizerApplication");

		// add the newly created application to domain
		this.addSubApplication(corporateActionsPrenormalizerApplicationApplication);

		// $-- /application/CorporateActionsPrenormalizerApplication/
		corporateActionsPrenormalizerApplicationApplication
				.setLocale(getLocale());
		// $-- /application/CorporateActionsPrenormalizerApplication/
		corporateActionsPrenormalizerApplicationApplication
				.setPrenormalizeCron(getPrenormalizeCron());
		// $-- /application/CorporateActionsPrenormalizerApplication/
		corporateActionsPrenormalizerApplicationApplication
				.setPrenormalizeSelectSize(getPrenormalizeSelectSize());
		// $-- /application/CorporateActionsPrenormalizerApplication/

		// link sub application to resources
		// $-- /application/HibernateResource/resource/application/CorporateActionsPrenormalizerApplication/hibernateResource
		corporateActionsPrenormalizerApplicationApplication
				.setHibernateResource(hibernateResource_);

		corporateActionsPrenormalizerApplicationApplication.initialize();
		// $-- /application/CorporateActionsSecuritiesUploadApplication
		// instantiate and configure 'CorporateActionsSecuritiesUploadApplication' sub application
		com.lynxspa.coac.CorporateActionsSecuritiesUploadApplication corporateActionsSecuritiesUploadApplicationApplication = Interceptors
				.createApplicationDomain(
						com.lynxspa.coac.CorporateActionsSecuritiesUploadApplication.class,
						"CorporateActionsSecuritiesUploadApplication");

		// add the newly created application to domain
		this.addSubApplication(corporateActionsSecuritiesUploadApplicationApplication);

		// $-- /application/CorporateActionsSecuritiesUploadApplication/
		corporateActionsSecuritiesUploadApplicationApplication
				.setSecuritiesUploadCustomerPattern(getSecuritiesUploadCustomerPattern());
		// $-- /application/CorporateActionsSecuritiesUploadApplication/
		corporateActionsSecuritiesUploadApplicationApplication
				.setSecuritiesUploadForceMarketInsert(getSecuritiesUploadForceMarketInsert());
		// $-- /application/CorporateActionsSecuritiesUploadApplication/
		corporateActionsSecuritiesUploadApplicationApplication
				.setSecuritiesUploadFundsCron(getSecuritiesUploadFundsCron());
		// $-- /application/CorporateActionsSecuritiesUploadApplication/
		corporateActionsSecuritiesUploadApplicationApplication
				.setLocale(getLocale());
		// $-- /application/CorporateActionsSecuritiesUploadApplication/
		corporateActionsSecuritiesUploadApplicationApplication
				.setSecuritiesUploadBloombergDebtInputDirectory(getInputBloombergDebtDirectory());
		// $-- /application/CorporateActionsSecuritiesUploadApplication/
		corporateActionsSecuritiesUploadApplicationApplication
				.setSecuritiesUploadOfivalCron(getSecuritiesUploadOfivalCron());
		// $-- /application/CorporateActionsSecuritiesUploadApplication/
		corporateActionsSecuritiesUploadApplicationApplication
				.setSecuritiesUploadBloombergPattern(getSecuritiesUploadBloombergPattern());
		// $-- /application/CorporateActionsSecuritiesUploadApplication/
		corporateActionsSecuritiesUploadApplicationApplication
				.setSecuritiesUploadBloombergInputDirectory(getSecuritiesUploadBloombergInputDirectory());
		// $-- /application/CorporateActionsSecuritiesUploadApplication/
		corporateActionsSecuritiesUploadApplicationApplication
				.setInputTimeout(getInputTimeout());
		// $-- /application/CorporateActionsSecuritiesUploadApplication/
		corporateActionsSecuritiesUploadApplicationApplication
				.setSecuritiesUploadFundsPatternDif(getSecuritiesUploadFundsPatternDif());
		// $-- /application/CorporateActionsSecuritiesUploadApplication/
		corporateActionsSecuritiesUploadApplicationApplication
				.setSecuritiesUploadFundsCommitSize(getSecuritiesUploadFundsCommitSize());
		// $-- /application/CorporateActionsSecuritiesUploadApplication/
		corporateActionsSecuritiesUploadApplicationApplication
				.setSecuritiesUploadBloombergCron(getSecuritiesUploadBloombergCron());
		// $-- /application/CorporateActionsSecuritiesUploadApplication/
		corporateActionsSecuritiesUploadApplicationApplication
				.setSecuritiesUploadFundsInputDirectory(getSecuritiesUploadFundsInputDirectory());
		// $-- /application/CorporateActionsSecuritiesUploadApplication/
		corporateActionsSecuritiesUploadApplicationApplication
				.setSecuritiesUploadOfivalInputDirectory(getSecuritiesUploadOfivalInputDirectory());
		// $-- /application/CorporateActionsSecuritiesUploadApplication/
		corporateActionsSecuritiesUploadApplicationApplication
				.setSecuritiesUploadCustomerCron(getSecuritiesUploadCustomerCron());
		// $-- /application/CorporateActionsSecuritiesUploadApplication/
		corporateActionsSecuritiesUploadApplicationApplication
				.setSecuritiesUploadFundsPattern(getSecuritiesUploadFundsPattern());
		// $-- /application/CorporateActionsSecuritiesUploadApplication/
		corporateActionsSecuritiesUploadApplicationApplication
				.setSecuritiesUploadCustomerCommitSize(getSecuritiesUploadCustomerCommitSize());
		// $-- /application/CorporateActionsSecuritiesUploadApplication/
		corporateActionsSecuritiesUploadApplicationApplication
				.setSecuritiesUploadBloombergFundsInputDirectory(getInputBloombergFundsDirectory());
		// $-- /application/CorporateActionsSecuritiesUploadApplication/
		corporateActionsSecuritiesUploadApplicationApplication
				.setSecuritiesUploadCustomerInputDirectory(getSecuritiesUploadCustomerInputDirectory());
		// $-- /application/CorporateActionsSecuritiesUploadApplication/
		corporateActionsSecuritiesUploadApplicationApplication
				.setSecuritiesUploadBloombergRVNoEuropeInputDirectory(getInputBloombergRVNoEDirectory());
		// $-- /application/CorporateActionsSecuritiesUploadApplication/
		corporateActionsSecuritiesUploadApplicationApplication
				.setSecuritiesUploadOfivalPattern(getSecuritiesUploadOfivalPattern());
		// $-- /application/CorporateActionsSecuritiesUploadApplication/
		corporateActionsSecuritiesUploadApplicationApplication
				.setSecuritiesUploadBloombergPatternDif(getSecuritiesUploadBloombergPatternDif());
		// $-- /application/CorporateActionsSecuritiesUploadApplication/
		corporateActionsSecuritiesUploadApplicationApplication
				.setSecuritiesUploadOfivalCommitSize(getSecuritiesUploadOfivalCommitSize());
		// $-- /application/CorporateActionsSecuritiesUploadApplication/
		corporateActionsSecuritiesUploadApplicationApplication
				.setSecuritiesUploadBloombergCommitSize(getInputBloombergCommitSize());
		// $-- /application/CorporateActionsSecuritiesUploadApplication/

		// link sub application to resources
		// $-- /application/HibernateResource/resource/application/CorporateActionsSecuritiesUploadApplication/hibernateResource
		corporateActionsSecuritiesUploadApplicationApplication
				.setHibernateResource(hibernateResource_);
		// $-- /application/HibernateStatelessResource/resource/application/CorporateActionsSecuritiesUploadApplication/hibernateStatelessResource
		corporateActionsSecuritiesUploadApplicationApplication
				.setHibernateStatelessResource(hibernateStatelessResource_);

		corporateActionsSecuritiesUploadApplicationApplication.initialize();

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

		setAuthorizationCron(ConfigurationHelper.parseString(
				initParams.getProperty("authorizationCron"), String.class));
		setAuthorizationSelectSize(ConfigurationHelper.parseString(
				initParams.getProperty("authorizationSelectSize"),
				java.lang.Integer.class));
		setCustomersLoadCron(ConfigurationHelper.parseString(
				initParams.getProperty("customersLoadCron"), String.class));
		setDeleteLogsCommitSize(ConfigurationHelper.parseString(
				initParams.getProperty("deleteLogsCommitSize"),
				java.lang.Integer.class));
		setDeleteLogsCronn(ConfigurationHelper.parseString(
				initParams.getProperty("deleteLogsCronn"), String.class));
		setEntityDeadLineDays(ConfigurationHelper.parseString(
				initParams.getProperty("entityDeadLineDays"),
				java.lang.Integer.class));
		setEvenDuration(ConfigurationHelper.parseString(
				initParams.getProperty("evenDuration"), Integer.class));
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
		setHourToBeginEven(ConfigurationHelper.parseString(
				initParams.getProperty("hourToBeginEven"), Integer.class));
		setInputBloombergCommitSize(ConfigurationHelper.parseString(
				initParams.getProperty("inputBloombergCommitSize"),
				Integer.class));
		setInputBloombergCron(ConfigurationHelper.parseString(
				initParams.getProperty("inputBloombergCron"), String.class));
		setInputBloombergDebtDirectory(ConfigurationHelper.parseString(
				initParams.getProperty("inputBloombergDebtDirectory"),
				java.io.File.class));
		setInputBloombergDirectory(ConfigurationHelper.parseString(
				initParams.getProperty("inputBloombergDirectory"),
				java.io.File.class));
		setInputBloombergErrorDirectory(ConfigurationHelper.parseString(
				initParams.getProperty("inputBloombergErrorDirectory"),
				java.io.File.class));
		setInputBloombergFundsDirectory(ConfigurationHelper.parseString(
				initParams.getProperty("inputBloombergFundsDirectory"),
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
		setInputBloombergRVNoEDirectory(ConfigurationHelper.parseString(
				initParams.getProperty("inputBloombergRVNoEDirectory"),
				java.io.File.class));
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
		setLivetimeHistorifiedCron(ConfigurationHelper
				.parseString(initParams.getProperty("livetimeHistorifiedCron"),
						String.class));
		setLocale(ConfigurationHelper.parseString(
				initParams.getProperty("locale"), String.class));
		setMasterRecordCron(ConfigurationHelper.parseString(
				initParams.getProperty("masterRecordCron"), String.class));
		setMasterRecordSelectSize(ConfigurationHelper.parseString(
				initParams.getProperty("masterRecordSelectSize"),
				java.lang.Integer.class));
		setMatchingCron(ConfigurationHelper.parseString(
				initParams.getProperty("matchingCron"), String.class));
		setMatchingSelectSize(ConfigurationHelper.parseString(
				initParams.getProperty("matchingSelectSize"),
				java.lang.Integer.class));
		setMonitorCron(ConfigurationHelper.parseString(
				initParams.getProperty("monitorCron"), String.class));
		setNormalizeCron(ConfigurationHelper.parseString(
				initParams.getProperty("normalizeCron"), String.class));
		setNormalizeSelectSize(ConfigurationHelper.parseString(
				initParams.getProperty("normalizeSelectSize"),
				java.lang.Integer.class));
		setNotes(ConfigurationHelper.parseString(
				initParams.getProperty("Notes"), java.lang.String.class));
		setNotificationManagerEventCron(ConfigurationHelper.parseString(
				initParams.getProperty("notificationManagerEventCron"),
				String.class));
		setNotificationManagerMaxResults(ConfigurationHelper.parseString(
				initParams.getProperty("notificationManagerMaxResults"),
				java.lang.Integer.class));
		setPageSize(ConfigurationHelper.parseString(
				initParams.getProperty("pageSize"), Integer.class));
		setPlanningCronnExpression(ConfigurationHelper
				.parseString(initParams.getProperty("planningCronnExpression"),
						String.class));
		setPrenormalizeCron(ConfigurationHelper.parseString(
				initParams.getProperty("prenormalizeCron"), String.class));
		setPrenormalizeSelectSize(ConfigurationHelper.parseString(
				initParams.getProperty("prenormalizeSelectSize"),
				java.lang.Integer.class));
		setPreviosHoursForAlarm(ConfigurationHelper.parseString(
				initParams.getProperty("previosHoursForAlarm"), Integer.class));
		setProcessMessageCron(ConfigurationHelper.parseString(
				initParams.getProperty("processMessageCron"), String.class));
		setSecuritiesUploadBloombergCommitSize(ConfigurationHelper.parseString(
				initParams.getProperty("securitiesUploadBloombergCommitSize"),
				Integer.class));
		setSecuritiesUploadBloombergCron(ConfigurationHelper.parseString(
				initParams.getProperty("securitiesUploadBloombergCron"),
				String.class));
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
		setSecurityPortfolioHistorificationCommitSize(ConfigurationHelper
				.parseString(
						initParams
								.getProperty("securityPortfolioHistorificationCommitSize"),
						Integer.class));
		setSecurityPortfolioHistorificationCron(ConfigurationHelper
				.parseString(initParams
						.getProperty("securityPortfolioHistorificationCron"),
						String.class));
		setSendBackOfficeCron(ConfigurationHelper.parseString(
				initParams.getProperty("sendBackOfficeCron"), String.class));
		setSendBackOfficeDestinationFolder(ConfigurationHelper.parseString(
				initParams.getProperty("sendBackOfficeDestinationFolder"),
				String.class));
		setSendBackOfficeSelectSize(ConfigurationHelper.parseString(
				initParams.getProperty("sendBackOfficeSelectSize"),
				Integer.class));
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
		setTempPathFiles(ConfigurationHelper.parseString(
				initParams.getProperty("tempPathFiles"), java.io.File.class));
		setTimeZone(ConfigurationHelper.parseString(
				initParams.getProperty("timeZone"), String.class));
	}

	public static void main(String[] args) {
		try {
			ApplicationDomain application = Interceptors
					.createApplicationDomain(
							CorporateActionsAndbancApplication.class,
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