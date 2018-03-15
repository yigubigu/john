package com.zmap.zmap.clinic.model;

public class ClinicsDrugRatioModel {
	private String hospitalCode;
	private String deptCode;
	private String deptName;
	private String doctorCode;
	private String doctorName;
	private float  clinicsDrugIncome;
	private float  deptDrugIncome;
	private float  doctorDrugIncome;

	
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getDoctorCode() {
		return doctorCode;
	}
	public void setDoctorCode(String doctorCode) {
		this.doctorCode = doctorCode;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public float getClinicsDrugIncome() {
		return clinicsDrugIncome;
	}
	public void setClinicsDrugIncome(float clinicsDrugIncome) {
		this.clinicsDrugIncome = clinicsDrugIncome;
	}
	public float getDeptDrugIncome() {
		return deptDrugIncome;
	}
	public void setDeptDrugIncome(float deptDrugIncome) {
		this.deptDrugIncome = deptDrugIncome;
	}
	public float getDoctorDrugIncome() {
		return doctorDrugIncome;
	}
	public void setDoctorDrugIncome(float doctorDrugIncome) {
		this.doctorDrugIncome = doctorDrugIncome;
	}


}
