package com.zmap.his.clinic.model;

import java.util.UUID;

public class HisClinicCM2ExamineSyncModel {
	private String exam_flow_code;
	private String exam_time;
	private String reg_code;
	private String patient_code;
	private String doctor_code;
	private String dept_code;
	private String exam_type_code;
	private String exam_code;
	private String exam_result;
	private String iccard_code;
	private String sub_hospital_code;
	private String clinic_code;
	
	
	
	public String getClinic_code() {
		return clinic_code;
	}
	public void setClinic_code(String clinic_code) {
		this.clinic_code = clinic_code;
	}
	public String getSub_hospital_code() {
		return sub_hospital_code;
	}
	public void setSub_hospital_code(String sub_hospital_code) {
		this.sub_hospital_code = sub_hospital_code;
	}
	public String getExam_flow_code() {
		return exam_flow_code;
	}
	public void setExam_flow_code(String exam_flow_code) {
		this.exam_flow_code = exam_flow_code;
	}
	public String getExam_time() {
		return exam_time;
	}
	public void setExam_time(String exam_time) {
		this.exam_time = exam_time;
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
	public String getExam_type_code() {
		return exam_type_code;
	}
	public void setExam_type_code(String exam_type_code) {
		this.exam_type_code = exam_type_code;
	}
	public String getExam_code() {
		return exam_code;
	}
	public void setExam_code(String exam_code) {
		this.exam_code = exam_code;
	}
	public String getExam_result() {
		return exam_result;
	}
	public void setExam_result(String exam_result) {
		this.exam_result = exam_result;
	}
	public String getIccard_code() {
		return iccard_code;
	}
	public void setIccard_code(String iccard_code) {
		this.iccard_code = iccard_code;
	}
	
	
	

	@Override
	public String toString() {
		return "HisClinicCM2ExamineSyncModel [exam_flow_code=" + exam_flow_code
				+ ", exam_time=" + exam_time + ", reg_code=" + reg_code
				+ ", patient_code=" + patient_code + ", doctor_code="
				+ doctor_code + ", dept_code=" + dept_code
				+ ", exam_type_code=" + exam_type_code + ", exam_code="
				+ exam_code + ", exam_result=" + exam_result + ", iccard_code="
				+ iccard_code + ", sub_hospital_code=" + sub_hospital_code
				+ ", clinic_code=" + clinic_code + "]";
	}
	public ZmapClinicCM2ExamineSyncVO toVo(){
		ZmapClinicCM2ExamineSyncVO destObj = new ZmapClinicCM2ExamineSyncVO();
		destObj.setClinicId(" ");
		destObj.setClinicCode(this.getClinic_code());
		destObj.setDeptCode(this.getDept_code());
		destObj.setDoctorCode(this.getDoctor_code());
		destObj.setExamCode(this.getExam_code());
		destObj.setExamFlowCode(this.getExam_flow_code());
		destObj.setExamId(UUID.randomUUID().toString());
		destObj.setExamResult(this.getExam_result());
		destObj.setExamTime(this.getExam_time());
		destObj.setExamTypeCode(this.getExam_type_code());
		destObj.setIccardCode(this.getIccard_code());
		destObj.setPatientCode(this.getPatient_code());
		destObj.setRegCode(this.getReg_code());
	    destObj.setZmapDeptCode("");
	    destObj.setHospitalCode("1");
	    destObj.setSubHospitalCode(this.getSub_hospital_code());
		return destObj;
	}
}
