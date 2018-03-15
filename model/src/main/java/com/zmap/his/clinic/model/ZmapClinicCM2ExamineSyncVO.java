package com.zmap.his.clinic.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ZmapClinicCM2ExamineSyncVO {

	private String examId;
	private String clinicId;
	private String clinicCode;
	private String examFlowCode;
	private String examTime;
	private String regCode;
	private String patientCode;
	private String doctorCode;
	private String deptCode;
	private String examTypeCode;
	private String examCode;
	private String examResult;
	private String iccardCode;
	private String zmapDeptCode;
	private String hospitalCode;
	private String subHospitalCode;
	
	public String getClinicCode() {
		return clinicCode;
	}
	public void setClinicCode(String clinicCode) {
		this.clinicCode = clinicCode;
	}
	public String getZmapDeptCode() {
		return zmapDeptCode;
	}
	public void setZmapDeptCode(String zmapDeptCode) {
		this.zmapDeptCode = zmapDeptCode;
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
	public String getExamId() {
		return examId;
	}
	public void setExamId(String examId) {
		this.examId = examId;
	}
	public String getClinicId() {
		return clinicId;
	}
	public void setClinicId(String clinicId) {
		this.clinicId = clinicId;
	}
	public String getExamFlowCode() {
		return examFlowCode;
	}
	public void setExamFlowCode(String examFlowCode) {
		this.examFlowCode = examFlowCode;
	}
	public String getExamTime() {
		return examTime;
	}
	public void setExamTime(String examTime) {
		this.examTime = examTime;
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
	public String getExamTypeCode() {
		return examTypeCode;
	}
	public void setExamTypeCode(String examTypeCode) {
		this.examTypeCode = examTypeCode;
	}
	public String getExamCode() {
		return examCode;
	}
	public void setExamCode(String examCode) {
		this.examCode = examCode;
	}
	public String getExamResult() {
		return examResult;
	}
	public void setExamResult(String examResult) {
		this.examResult = examResult;
	}
	public String getIccardCode() {
		return iccardCode;
	}
	public void setIccardCode(String iccardCode) {
		this.iccardCode = iccardCode;
	}
   
}
