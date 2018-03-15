package com.zmap.his.clinic.model;

import java.util.UUID;

public class HisClinicCM2DiagnoseSyncModel {
	private static final long serialVersionUID = 1L;
	
	private String diag_flow_code;
	private String diag_time;
	private String reg_code;
	private String patient_code;
	private String doctor_code;
	private String dept_code;
	private String diag_code;
	private String diag_result;
	private String iccard_code;
	private String sub_hospital_code;
	
	public String getSub_hospital_code() {
		return sub_hospital_code;
	}
	public void setSub_hospital_code(String sub_hospital_code) {
		this.sub_hospital_code = sub_hospital_code;
	}
	public String getDiag_flow_code() {
		return diag_flow_code;
	}
	public void setDiag_flow_code(String diag_flow_code) {
		this.diag_flow_code = diag_flow_code;
	}
	public String getDiag_time() {
		return diag_time;
	}
	public void setDiag_time(String diag_time) {
		this.diag_time = diag_time;
	}
	public String getReg_code() {
		return reg_code;
	}
	public void setReg_code(String reg_code) {
		this.reg_code = reg_code;
	}
	public String getPatient_code() {
		return patient_code;
	}
	public void setPatient_code(String patient_code) {
		this.patient_code = patient_code;
	}
	public String getDoctor_code() {
		return doctor_code;
	}
	public void setDoctor_code(String doctor_code) {
		this.doctor_code = doctor_code;
	}
	public String getDept_code() {
		return dept_code;
	}
	public void setDept_code(String dept_code) {
		this.dept_code = dept_code;
	}
	public String getDiag_code() {
		return diag_code;
	}
	public void setDiag_code(String diag_code) {
		this.diag_code = diag_code;
	}
	public String getDiag_result() {
		return diag_result;
	}
	public void setDiag_result(String diag_result) {
		this.diag_result = diag_result;
	}
	public String getIccard_code() {
		return iccard_code;
	}
	public void setIccard_code(String iccard_code) {
		this.iccard_code = iccard_code;
	}
	
	
	
	@Override
	public String toString() {
		return "HisClinicCM2DiagnoseSyncModel [diag_flow_code="
				+ diag_flow_code + ", diag_time=" + diag_time + ", reg_code="
				+ reg_code + ", patient_code=" + patient_code
				+ ", doctor_code=" + doctor_code + ", dept_code=" + dept_code
				+ ", diag_code=" + diag_code + ", diag_result=" + diag_result
				+ ", iccard_code=" + iccard_code + ", sub_hospital_code="
				+ sub_hospital_code + "]";
	}
	public ZmapClinicCM2DiagnoseSyncVO toVo(){
		ZmapClinicCM2DiagnoseSyncVO destObj = new ZmapClinicCM2DiagnoseSyncVO();
		destObj.setDeptCode(this.getDept_code());
		destObj.setDiagCode(this.getDiag_code());
		destObj.setDiagFlowCode(this.getDiag_flow_code());
		destObj.setDiagId(UUID.randomUUID().toString());
		destObj.setDiagResult(this.getDiag_result());
		destObj.setDiagTime(this.getDiag_time());
		destObj.setDoctorCode(this.getDoctor_code());
		destObj.setIccardCode(this.getIccard_code());
		destObj.setPatientCode(this.getPatient_code());
		destObj.setRegCode(this.getReg_code());
		destObj.setZmapDeptCode("");
		destObj.setZmapDiagCode("");
		destObj.setHospitalCode("1");
		destObj.setSubHospitalCode(this.getSub_hospital_code());
		return destObj;
	}
	
}
