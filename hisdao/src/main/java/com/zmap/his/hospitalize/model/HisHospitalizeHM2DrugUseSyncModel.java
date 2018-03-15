package com.zmap.his.hospitalize.model;

import java.io.Serializable;
import java.util.UUID;

public class HisHospitalizeHM2DrugUseSyncModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String inHosCode;
	private String drugUseCode;
	private String drugUseTime;
	private String sickmanCode;
	private String doctorCode;
	private String deptCode;
	private String drugTypeCode;
	private String drugCode;
	private String zmapDrugCode;
	private String drugDose;
	private String useFreq;
	private String subHospitalCode;
	
	
	public String getInHosCode() {
		return inHosCode;
	}


	public void setInHosCode(String inHosCode) {
		this.inHosCode = inHosCode;
	}


	public String getDrugUseCode() {
		return drugUseCode;
	}


	public void setDrugUseCode(String drugUseCode) {
		this.drugUseCode = drugUseCode;
	}


	public String getDrugUseTime() {
		return drugUseTime;
	}


	public void setDrugUseTime(String drugUseTime) {
		this.drugUseTime = drugUseTime;
	}


	public String getSickmanCode() {
		return sickmanCode;
	}


	public void setSickmanCode(String sickmanCode) {
		this.sickmanCode = sickmanCode;
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


	public String getDrugTypeCode() {
		return drugTypeCode;
	}


	public void setDrugTypeCode(String drugTypeCode) {
		this.drugTypeCode = drugTypeCode;
	}


	public String getDrugCode() {
		return drugCode;
	}


	public void setDrugCode(String drugCode) {
		this.drugCode = drugCode;
	}


	public String getZmapDrugCode() {
		return zmapDrugCode;
	}


	public void setZmapDrugCode(String zmapDrugCode) {
		this.zmapDrugCode = zmapDrugCode;
	}


	public String getDrugDose() {
		return drugDose;
	}


	public void setDrugDose(String drugDose) {
		this.drugDose = drugDose;
	}


	public String getUseFreq() {
		return useFreq;
	}


	public void setUseFreq(String useFreq) {
		this.useFreq = useFreq;
	}


	public String getSubHospitalCode() {
		return subHospitalCode;
	}


	public void setSubHospitalCode(String subHospitalCode) {
		this.subHospitalCode = subHospitalCode;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public String toString() {
		return "HisHospitalizeHM2DrugUseSyncModel [inHosCode=" + inHosCode
				+ ", drugUseCode=" + drugUseCode + ", drugUseTime="
				+ drugUseTime + ", sickmanCode=" + sickmanCode
				+ ", doctorCode=" + doctorCode + ", deptCode=" + deptCode
				+ ", drugTypeCode=" + drugTypeCode + ", drugCode=" + drugCode
				+ ", zmapDrugCode=" + zmapDrugCode + ", drugDose=" + drugDose
				+ ", useFreq=" + useFreq + ", subHospitalCode="
				+ subHospitalCode + "]";
	}


	public ZmapHospitalizeHM2DrugUseSyncVO toVo(){
		ZmapHospitalizeHM2DrugUseSyncVO destObj=new ZmapHospitalizeHM2DrugUseSyncVO();
		destObj.setDeptCode(this.getDeptCode());
		destObj.setDoctorCode(this.getDoctorCode());
		destObj.setDrugCode(this.getDrugCode());
		destObj.setDrugDose(this.getDrugDose());
		destObj.setDrugTypeCode(this.getDrugTypeCode());
		destObj.setDrugUseCode(this.getDrugUseCode());
		destObj.setDrugUseId(UUID.randomUUID().toString());
		destObj.setDrugUseTime(this.getDrugUseTime());
		destObj.setHospitalCode("1");
		destObj.setInHosCode("");
		destObj.setSickmanCode(this.getSickmanCode());
		destObj.setSubHospitalCode(this.getSubHospitalCode());
		destObj.setUseFreq(this.getUseFreq());
		destObj.setZmapDeptCode("");
		destObj.setZmapDrugCode("");
		
		return destObj;
	}

}
