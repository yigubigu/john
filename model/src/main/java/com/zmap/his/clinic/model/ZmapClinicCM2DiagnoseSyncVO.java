package com.zmap.his.clinic.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ZmapClinicCM2DiagnoseSyncVO {
	private String diagId;
	private String clinicCode;
	private String diagFlowCode;
	private String diagTime;
	private String regCode;
	private String patientCode;
	private String doctorCode;
	private String deptCode;
	private String diagCode;
	private String diagResult;
	private String iccardCode;
	 private String zmapDeptCode;
	    private String zmapDiagCode;
	    private String subHospitalCode;
	    private String hospitalCode;
	public String getZmapDeptCode() {
			return zmapDeptCode;
		}
		public void setZmapDeptCode(String zmapDeptCode) {
			this.zmapDeptCode = zmapDeptCode;
		}
		public String getZmapDiagCode() {
			return zmapDiagCode;
		}
		public void setZmapDiagCode(String zmapDiagCode) {
			this.zmapDiagCode = zmapDiagCode;
		}
		public String getSubHospitalCode() {
			return subHospitalCode;
		}
		public void setSubHospitalCode(String subHospitalCode) {
			this.subHospitalCode = subHospitalCode;
		}
		public String getHospitalCode() {
			return hospitalCode;
		}
		public void setHospitalCode(String hospitalCode) {
			this.hospitalCode = hospitalCode;
		}
	public String getDiagId() {
		return diagId;
	}
	public void setDiagId(String diagId) {
		this.diagId = diagId;
	}
	public String getClinicCode() {
		return clinicCode;
	}
	public void setClinicCode(String clinicCode) {
		this.clinicCode = clinicCode;
	}
	public String getDiagFlowCode() {
		return diagFlowCode;
	}
	public void setDiagFlowCode(String diagFlowCode) {
		this.diagFlowCode = diagFlowCode;
	}
	public String getDiagTime() {
		return diagTime;
	}
	public void setDiagTime(String diagTime) {
		this.diagTime = diagTime;
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
	public String getDiagCode() {
		return diagCode;
	}
	public void setDiagCode(String diagCode) {
		this.diagCode = diagCode;
	}
	public String getDiagResult() {
		return diagResult;
	}
	public void setDiagResult(String diagResult) {
		this.diagResult = diagResult;
	}
	public String getIccardCode() {
		return iccardCode;
	}
	public void setIccardCode(String iccardCode) {
		this.iccardCode = iccardCode;
	}
   
}
