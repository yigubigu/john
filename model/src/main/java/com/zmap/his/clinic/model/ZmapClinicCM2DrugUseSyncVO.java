package com.zmap.his.clinic.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ZmapClinicCM2DrugUseSyncVO {

	private String drugUseId;
	private String clinicId;
	private String drugUseCode;
	private String drugUseTime;
	private String regCode;
	private String patientCode;
	private String doctorCode;
	private String deptCode;
	private String drugTypeCode;
	private String drugCode;
	private String drugDose;
	private String useFreq;
	private String iccardCode;
	private String zmapDeptCode;
	private String zmapDrugCode;
	private String hospitalCode;
	private String subHospitalCode;
	
	
	
	
	public String getZmapDeptCode() {
		return zmapDeptCode;
	}
	public void setZmapDeptCode(String zmapDeptCode) {
		this.zmapDeptCode = zmapDeptCode;
	}
	public String getZmapDrugCode() {
		return zmapDrugCode;
	}
	public void setZmapDrugCode(String zmapDrugCode) {
		this.zmapDrugCode = zmapDrugCode;
	}
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	public String getSubHospitalCode() {
		return subHospitalCode;
	}
	public void setSubHospitalCode(String subHospitalCode) {
		this.subHospitalCode = subHospitalCode;
	}
	public String getDrugUseId() {
		return drugUseId;
	}
	public void setDrugUseId(String drugUseId) {
		this.drugUseId = drugUseId;
	}
	public String getClinicId() {
		return clinicId;
	}
	public void setClinicId(String clinicId) {
		this.clinicId = clinicId;
	}
	public String getDrugUseCode() {
		return drugUseCode;
	}
	public void setDrugUseCode(String drugUseCode) {
		this.drugUseCode = drugUseCode;
	}
	public String getDrugUseTime() {
		return drugUseTime;
	}
	public void setDrugUseTime(String drugUseTime) {
		this.drugUseTime = drugUseTime;
	}
	public String getRegCode() {
		return regCode;
	}
	public void setRegCode(String regCode) {
		this.regCode = regCode;
	}
	public String getPatientCode() {
		return patientCode;
	}
	public void setPatientCode(String patientCode) {
		this.patientCode = patientCode;
	}
	public String getDoctorCode() {
		return doctorCode;
	}
	public void setDoctorCode(String doctorCode) {
		this.doctorCode = doctorCode;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getDrugTypeCode() {
		return drugTypeCode;
	}
	public void setDrugTypeCode(String drugTypeCode) {
		this.drugTypeCode = drugTypeCode;
	}
	public String getDrugCode() {
		return drugCode;
	}
	public void setDrugCode(String drugCode) {
		this.drugCode = drugCode;
	}
	public String getDrugDose() {
		return drugDose;
	}
	public void setDrugDose(String drugDose) {
		this.drugDose = drugDose;
	}
	public String getUseFreq() {
		return useFreq;
	}
	public void setUseFreq(String useFreq) {
		this.useFreq = useFreq;
	}
	public String getIccardCode() {
		return iccardCode;
	}
	public void setIccardCode(String iccardCode) {
		this.iccardCode = iccardCode;
	}
   
}
