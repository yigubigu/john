package com.zmap.his.clinic.model;

import java.util.UUID;

public class HisClinicCM2DrugUseSyncModel {
	
	private String drug_use_code;
	private String drug_use_time;
	private String reg_code;
	private String patient_code;
	private String doctor_code;
	private String dept_code;
	private String drug_type_code;
	private String drug_code;
	private String drug_dose;
	private String use_freq;
	private String iccard_code;
	private String sub_hospital_code;
	public String getSub_hospital_code() {
		return sub_hospital_code;
	}
	public void setSub_hospital_code(String sub_hospital_code) {
		this.sub_hospital_code = sub_hospital_code;
	}
	public String getDrug_use_code() {
		return drug_use_code;
	}
	public void setDrug_use_code(String drug_use_code) {
		this.drug_use_code = drug_use_code;
	}
	public String getDrug_use_time() {
		return drug_use_time;
	}
	public void setDrug_use_time(String drug_use_time) {
		this.drug_use_time = drug_use_time;
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
	public String getDrug_type_code() {
		return drug_type_code;
	}
	public void setDrug_type_code(String drug_type_code) {
		this.drug_type_code = drug_type_code;
	}
	public String getDrug_code() {
		return drug_code;
	}
	public void setDrug_code(String drug_code) {
		this.drug_code = drug_code;
	}
	public String getDrug_dose() {
		return drug_dose;
	}
	public void setDrug_dose(String drug_dose) {
		this.drug_dose = drug_dose;
	}
	public String getUse_freq() {
		return use_freq;
	}
	public void setUse_freq(String use_freq) {
		this.use_freq = use_freq;
	}
	public String getIccard_code() {
		return iccard_code;
	}
	public void setIccard_code(String iccard_code) {
		this.iccard_code = iccard_code;
	}
	
	
	@Override
	public String toString() {
		return "HisClinicCM2DrugUseSyncModel [drug_use_code=" + drug_use_code
				+ ", drug_use_time=" + drug_use_time + ", reg_code=" + reg_code
				+ ", patient_code=" + patient_code + ", doctor_code="
				+ doctor_code + ", dept_code=" + dept_code
				+ ", drug_type_code=" + drug_type_code + ", drug_code="
				+ drug_code + ", drug_dose=" + drug_dose + ", use_freq="
				+ use_freq + ", iccard_code=" + iccard_code
				+ ", sub_hospital_code=" + sub_hospital_code + "]";
	}
	public ZmapClinicCM2DrugUseSyncVO toVo(){
		ZmapClinicCM2DrugUseSyncVO destObj = new ZmapClinicCM2DrugUseSyncVO();
		destObj.setClinicId(" ");
		destObj.setDeptCode(this.getDept_code());
		destObj.setDoctorCode(this.getDoctor_code());
		destObj.setDrugCode(this.getDrug_code());
		destObj.setDrugDose(this.getDrug_dose());
		destObj.setDrugTypeCode(this.getDrug_type_code());
		destObj.setDrugUseCode(this.getDrug_use_code());
		destObj.setDrugUseId(UUID.randomUUID().toString());
		destObj.setDrugUseTime(this.getDrug_use_time());
		destObj.setIccardCode(this.getIccard_code());
		destObj.setPatientCode(this.getPatient_code());
		destObj.setRegCode(this.getReg_code());
		destObj.setUseFreq(this.getUse_freq());
		destObj.setSubHospitalCode(this.getSub_hospital_code());
		destObj.setZmapDeptCode("");
		destObj.setZmapDrugCode("");
		destObj.setHospitalCode("1");
		
		return destObj;
	}
}
