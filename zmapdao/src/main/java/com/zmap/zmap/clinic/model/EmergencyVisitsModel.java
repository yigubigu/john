package com.zmap.zmap.clinic.model;

public class EmergencyVisitsModel {
	private int visitsCount;
	private String regTime;
	private String clinicType;
	private String deptCode;
	private String hospitalCode;
	
	public String getRegTime() {
		return regTime;
	}
	public void setRegTime(String regTime) {
		this.regTime = regTime;
	}
	public String getClinicType() {
		return clinicType;
	}
	public void setClinicType(String clinicType) {
		this.clinicType = clinicType;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	public int getVisitsCount() {
		return visitsCount;
	}
	public void setVisitsCount(int visitsCount) {
		this.visitsCount = visitsCount;
	}

	
}
