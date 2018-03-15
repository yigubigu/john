package com.zmap.his.clinic.model;

import java.io.Serializable;
import java.util.UUID;

public class HisClinicCM2MOrderSyncModel implements Serializable{
	private static final long serialVersionUID = 1L;
	private String morderId;
	private String morderCode;
	private String relatedCode;
	private String patientCode;
	private String patientName;
	private String mhomePageCode;
	private String regSeq;
	private String deptCode;
	private String morderStatus;
	private String isValid;
	private String treatTypeCode;
	private String treatCode;
	private String treatBodyPart;
	private String feeItemCode;
	private String days;
	private String singleUseVolumn;
	private String totalUseVolumn;
	private String morderContent;
	private String morderSpecial;
	private String skinTestResult;
	private String execDeptCode;
	private String execFreq;
	private String freqTimes;
	private String freqInterval;
	private String intervalTimeUnit;
	private String execSchedule;
	private String execTypeCode;
	private String execMark;
	private String execBeginTime;
	private String execEndTime;
	private String lastExecTime;
	private String morderDeptCode;
	private String morderDoctorCode;
	private String morderTime;
	private String proofreadNurse;
	private String proofreadTime;
	private String stopDoctorCode;
	private String stopTime;
	private String confirmedStopTime;
	private String confirmedStopNurse;
	private String checkMark;
	private String checkStatus;
	private String firstUseVolumn;
	private String overUseVolumn;
	private String combinedItemCode;
	private String fomulaCode;
	private String pharCheckMark;
	private String pharCheckTime;
	private String chargeCode;
	public String getMorderId() {
		return morderId;
	}
	public void setMorderId(String morderId) {
		this.morderId = morderId;
	}
	public String getMorderCode() {
		return morderCode;
	}
	public void setMorderCode(String morderCode) {
		this.morderCode = morderCode;
	}
	
	public String getRelatedCode() {
		return relatedCode;
	}
	public void setRelatedCode(String relatedCode) {
		this.relatedCode = relatedCode;
	}
	public String getPatientCode() {
		return patientCode;
	}
	public void setPatientCode(String patientCode) {
		this.patientCode = patientCode;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public String getMhomePageCode() {
		return mhomePageCode;
	}
	public void setMhomePageCode(String mhomePageCode) {
		this.mhomePageCode = mhomePageCode;
	}
	public String getRegSeq() {
		return regSeq;
	}
	public void setRegSeq(String regSeq) {
		this.regSeq = regSeq;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getMorderStatus() {
		return morderStatus;
	}
	public void setMorderStatus(String morderStatus) {
		this.morderStatus = morderStatus;
	}
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	public String getTreatTypeCode() {
		return treatTypeCode;
	}
	public void setTreatTypeCode(String treatTypeCode) {
		this.treatTypeCode = treatTypeCode;
	}
	public String getTreatCode() {
		return treatCode;
	}
	public void setTreatCode(String treatCode) {
		this.treatCode = treatCode;
	}
	public String getTreatBodyPart() {
		return treatBodyPart;
	}
	public void setTreatBodyPart(String treatBodyPart) {
		this.treatBodyPart = treatBodyPart;
	}
	public String getFeeItemCode() {
		return feeItemCode;
	}
	public void setFeeItemCode(String feeItemCode) {
		this.feeItemCode = feeItemCode;
	}
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
	}
	public String getSingleUseVolumn() {
		return singleUseVolumn;
	}
	public void setSingleUseVolumn(String singleUseVolumn) {
		this.singleUseVolumn = singleUseVolumn;
	}
	public String getTotalUseVolumn() {
		return totalUseVolumn;
	}
	public void setTotalUseVolumn(String totalUseVolumn) {
		this.totalUseVolumn = totalUseVolumn;
	}
	public String getMorderContent() {
		return morderContent;
	}
	public void setMorderContent(String morderContent) {
		this.morderContent = morderContent;
	}
	public String getMorderSpecial() {
		return morderSpecial;
	}
	public void setMorderSpecial(String morderSpecial) {
		this.morderSpecial = morderSpecial;
	}
	public String getSkinTestResult() {
		return skinTestResult;
	}
	public void setSkinTestResult(String skinTestResult) {
		this.skinTestResult = skinTestResult;
	}
	public String getExecDeptCode() {
		return execDeptCode;
	}
	public void setExecDeptCode(String execDeptCode) {
		this.execDeptCode = execDeptCode;
	}
	public String getExecFreq() {
		return execFreq;
	}
	public void setExecFreq(String execFreq) {
		this.execFreq = execFreq;
	}
	public String getFreqTimes() {
		return freqTimes;
	}
	public void setFreqTimes(String freqTimes) {
		this.freqTimes = freqTimes;
	}
	public String getFreqInterval() {
		return freqInterval;
	}
	public void setFreqInterval(String freqInterval) {
		this.freqInterval = freqInterval;
	}
	public String getIntervalTimeUnit() {
		return intervalTimeUnit;
	}
	public void setIntervalTimeUnit(String intervalTimeUnit) {
		this.intervalTimeUnit = intervalTimeUnit;
	}
	public String getExecSchedule() {
		return execSchedule;
	}
	public void setExecSchedule(String execSchedule) {
		this.execSchedule = execSchedule;
	}
	public String getExecTypeCode() {
		return execTypeCode;
	}
	public void setExecTypeCode(String execTypeCode) {
		this.execTypeCode = execTypeCode;
	}
	public String getExecMark() {
		return execMark;
	}
	public void setExecMark(String execMark) {
		this.execMark = execMark;
	}
	public String getExecBeginTime() {
		return execBeginTime;
	}
	public void setExecBeginTime(String execBeginTime) {
		this.execBeginTime = execBeginTime;
	}
	public String getExecEndTime() {
		return execEndTime;
	}
	public void setExecEndTime(String execEndTime) {
		this.execEndTime = execEndTime;
	}
	public String getLastExecTime() {
		return lastExecTime;
	}
	public void setLastExecTime(String lastExecTime) {
		this.lastExecTime = lastExecTime;
	}
	public String getMorderDeptCode() {
		return morderDeptCode;
	}
	public void setMorderDeptCode(String morderDeptCode) {
		this.morderDeptCode = morderDeptCode;
	}
	public String getMorderDoctorCode() {
		return morderDoctorCode;
	}
	public void setMorderDoctorCode(String morderDoctorCode) {
		this.morderDoctorCode = morderDoctorCode;
	}
	public String getMorderTime() {
		return morderTime;
	}
	public void setMorderTime(String morderTime) {
		this.morderTime = morderTime;
	}
	public String getProofreadNurse() {
		return proofreadNurse;
	}
	public void setProofreadNurse(String proofreadNurse) {
		this.proofreadNurse = proofreadNurse;
	}
	public String getProofreadTime() {
		return proofreadTime;
	}
	public void setProofreadTime(String proofreadTime) {
		this.proofreadTime = proofreadTime;
	}
	public String getStopDoctorCode() {
		return stopDoctorCode;
	}
	public void setStopDoctorCode(String stopDoctorCode) {
		this.stopDoctorCode = stopDoctorCode;
	}
	public String getStopTime() {
		return stopTime;
	}
	public void setStopTime(String stopTime) {
		this.stopTime = stopTime;
	}
	public String getConfirmedStopTime() {
		return confirmedStopTime;
	}
	public void setConfirmedStopTime(String confirmedStopTime) {
		this.confirmedStopTime = confirmedStopTime;
	}
	public String getConfirmedStopNurse() {
		return confirmedStopNurse;
	}
	public void setConfirmedStopNurse(String confirmedStopNurse) {
		this.confirmedStopNurse = confirmedStopNurse;
	}
	public String getCheckMark() {
		return checkMark;
	}
	public void setCheckMark(String checkMark) {
		this.checkMark = checkMark;
	}
	public String getCheckStatus() {
		return checkStatus;
	}
	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}
	public String getFirstUseVolumn() {
		return firstUseVolumn;
	}
	public void setFirstUseVolumn(String firstUseVolumn) {
		this.firstUseVolumn = firstUseVolumn;
	}
	public String getOverUseVolumn() {
		return overUseVolumn;
	}
	public void setOverUseVolumn(String overUseVolumn) {
		this.overUseVolumn = overUseVolumn;
	}
	public String getCombinedItemCode() {
		return combinedItemCode;
	}
	public void setCombinedItemCode(String combinedItemCode) {
		this.combinedItemCode = combinedItemCode;
	}
	public String getFomulaCode() {
		return fomulaCode;
	}
	public void setFomulaCode(String fomulaCode) {
		this.fomulaCode = fomulaCode;
	}
	public String getPharCheckMark() {
		return pharCheckMark;
	}
	public void setPharCheckMark(String pharCheckMark) {
		this.pharCheckMark = pharCheckMark;
	}
	public String getPharCheckTime() {
		return pharCheckTime;
	}
	public void setPharCheckTime(String pharCheckTime) {
		this.pharCheckTime = pharCheckTime;
	}
	public String getChargeCode() {
		return chargeCode;
	}
	public void setChargeCode(String chargeCode) {
		this.chargeCode = chargeCode;
	}

	public ZmapClinicCM2MOrderSyncVO toVo() {
		ZmapClinicCM2MOrderSyncVO destObj = new ZmapClinicCM2MOrderSyncVO();
		destObj.setMorderId(UUID.randomUUID().toString());
		destObj.setChargeCode(this.getChargeCode());
		destObj.setCheckMark(this.getCheckMark());
		destObj.setCheckStatus(this.getCheckStatus());
		destObj.setCombinedItemCode(this.getCombinedItemCode());
		destObj.setConfirmedStopNurse(this.getConfirmedStopNurse());
		destObj.setConfirmedStopTime(this.getConfirmedStopTime());
		destObj.setDays(this.getDays());
		destObj.setDeptCode(this.getDeptCode());
		destObj.setExecBeginTime(this.getExecBeginTime());
		destObj.setExecDeptCode(this.getExecDeptCode());
		destObj.setExecEndTime(this.getExecEndTime());
		destObj.setExecFreq(this.getExecFreq());
		destObj.setExecMark(this.getExecMark());
		destObj.setExecSchedule(this.getExecSchedule());
		destObj.setExecTypeCode(this.getExecTypeCode());
		destObj.setFeeItemCode(this.getFeeItemCode());
		destObj.setFirstUseVolumn(this.getFirstUseVolumn());
		destObj.setFomulaCode(this.getFomulaCode());
		destObj.setFreqInterval(this.getFreqInterval());
		destObj.setFreqTimes(this.getFreqTimes());
		destObj.setIntervalTimeUnit(this.getIntervalTimeUnit());
		destObj.setIsValid(this.getIsValid());
		destObj.setLastExecTime(this.getLastExecTime());
		destObj.setMhomePageCode(this.getMhomePageCode());
		destObj.setMorderCode(this.getMorderCode());
		destObj.setMorderContent(this.getMorderContent());
		destObj.setMorderDeptCode(this.getMorderDeptCode());
		destObj.setMorderDoctorCode(this.getMorderDoctorCode());
		destObj.setMorderSpecial(this.getMorderSpecial());
		destObj.setMorderStatus(this.getMorderStatus());
		destObj.setMorderTime(this.getMorderTime());
		destObj.setOverUseVolumn(this.getOverUseVolumn());
		destObj.setPatientCode(this.getPatientCode());
		destObj.setPatientName(this.getPatientName());
		destObj.setPharCheckMark(this.getPharCheckMark());
		destObj.setPharCheckTime(this.getPharCheckTime());
		destObj.setProofreadNurse(this.getProofreadNurse());
		destObj.setProofreadTime(this.getProofreadTime());
		destObj.setRegSeq(this.getRegSeq());
		destObj.setRelatedCode(this.getRelatedCode());
		destObj.setSingleUseVolumn(this.getSingleUseVolumn());
		destObj.setSkinTestResult(this.getSkinTestResult());
		destObj.setStopDoctorCode(this.getStopDoctorCode());
		destObj.setStopTime(this.getStopTime());
		destObj.setTotalUseVolumn(this.getTotalUseVolumn());
		destObj.setTreatBodyPart(this.getTreatBodyPart());
		destObj.setTreatCode(this.getTreatCode());
		destObj.setTreatTypeCode(this.getTreatTypeCode());
		
		return destObj;
	}
}
