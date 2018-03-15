package com.zmap.zmap.hospitalize.model;

public class HospitalizeHighOwePersonModel {
	private String sickmanCode;
	private String deptCode;
	private float oweFeeCount;
	private float prepayFeeCount;
	private float feeCount;
	private double personTime;
	public String getSickmanCode() {
		return sickmanCode;
	}
	public void setSickmanCode(String sickmanCode) {
		this.sickmanCode = sickmanCode;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public double getPersonTime() {
		return personTime;
	}
	public void setPersonTime(double personTime) {
		this.personTime = personTime;
	}
	public float getOweFeeCount() {
		return oweFeeCount;
	}
	public void setOweFeeCount(float oweFeeCount) {
		this.oweFeeCount = oweFeeCount;
	}
	public float getPrepayFeeCount() {
		return prepayFeeCount;
	}
	public void setPrepayFeeCount(float prepayFeeCount) {
		this.prepayFeeCount = prepayFeeCount;
	}
	public float getFeeCount() {
		return feeCount;
	}
	public void setFeeCount(float feeCount) {
		this.feeCount = feeCount;
	}
	

}
