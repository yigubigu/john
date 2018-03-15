package com.zmap.zmap.framework.income.model;

public class CostPrivateModel {
	private String deptCode;
	private double feeCount;
	private String doctorCode;
	
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
	public double getFeeCount() {
		return feeCount;
	}
	public void setFeeCount(double feeCount) {
		this.feeCount = feeCount;
	}
	

}
