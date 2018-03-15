package com.zmap.his.clinic.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 中间VO层，对应zmap库中model
 * @author Admin
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ZmapClinicCM1SyncVO {
	private String clinicId;
	private String regCode;
	private String regTime;
	private String patientCode;
	private String doctorCode;
	private String deptCode;
	private String orgCode;
	private String iccardCode;
    private String zmapDeptCode;
    private String clinicType;
    private String hospitalCode;
    private String subHospitalCode;
    private String isTrans;
	private String transRegType;
	private String transDeptCode;
	private String transDoctorCode;
	private String transStatus;
	
	
	public String getIsTrans() {
		return isTrans;
	}
	public void setIsTrans(String isTrans) {
		this.isTrans = isTrans;
	}
	public String getTransRegType() {
		return transRegType;
	}
	public void setTransRegType(String transRegType) {
		this.transRegType = transRegType;
	}
	public String getTransDeptCode() {
		return transDeptCode;
	}
	public void setTransDeptCode(String transDeptCode) {
		this.transDeptCode = transDeptCode;
	}
	public String getTransDoctorCode() {
		return transDoctorCode;
	}
	public void setTransDoctorCode(String transDoctorCode) {
		this.transDoctorCode = transDoctorCode;
	}
	public String getTransStatus() {
		return transStatus;
	}
	public void setTransStatus(String transStatus) {
		this.transStatus = transStatus;
	}
	public String getZmapDeptCode() {
			return zmapDeptCode;
	}
	public void setZmapDeptCode(String zmapDeptCode) {
		this.zmapDeptCode = zmapDeptCode;
	}
	public String getClinicType() {
		return clinicType;
	}
	public void setClinicType(String clinicType) {
		this.clinicType = clinicType;
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
	public String getClinicId() {
		return clinicId;
	}
	public void setClinicId(String clinicId) {
		this.clinicId = clinicId;
	}
	public String getRegCode() {
		return regCode;
	}
	public void setRegCode(String regCode) {
		this.regCode = regCode;
	}
	public String getRegTime() {
		return regTime;
	}
	public void setRegTime(String regTime) {
		this.regTime = regTime;
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
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getIccardCode() {
		return iccardCode;
	}
	public void setIccardCode(String iccardCode) {
		this.iccardCode = iccardCode;
	}
	
	
}
