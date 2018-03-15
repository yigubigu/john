package com.zmap.his.hospitalize.model;

import java.io.Serializable;
import java.util.UUID;

public class HisHospitalizeHM2ExamineSyncModel implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String inHosCode;
	private String examFlowCode;
	private String examTime;
	private String sickmanCode;
	private String doctorCode;
	private String deptCode;
	private String zmapExamCode;
	private String examTypeCode;
	private String examCode;
	private String examResult;
	private String subHospitalCode;
	private String zmapDeptCode;
	private String hospitalCode;
	
	
	

	public String getInHosCode() {
		return inHosCode;
	}




	public void setInHosCode(String inHosCode) {
		this.inHosCode = inHosCode;
	}




	public String getExamFlowCode() {
		return examFlowCode;
	}




	public void setExamFlowCode(String examFlowCode) {
		this.examFlowCode = examFlowCode;
	}




	public String getExamTime() {
		return examTime;
	}




	public void setExamTime(String examTime) {
		this.examTime = examTime;
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




	public String getZmapExamCode() {
		return zmapExamCode;
	}




	public void setZmapExamCode(String zmapExamCode) {
		this.zmapExamCode = zmapExamCode;
	}




	public String getExamTypeCode() {
		return examTypeCode;
	}




	public void setExamTypeCode(String examTypeCode) {
		this.examTypeCode = examTypeCode;
	}




	public String getExamCode() {
		return examCode;
	}




	public void setExamCode(String examCode) {
		this.examCode = examCode;
	}




	public String getExamResult() {
		return examResult;
	}




	public void setExamResult(String examResult) {
		this.examResult = examResult;
	}




	public String getSubHospitalCode() {
		return subHospitalCode;
	}




	public void setSubHospitalCode(String subHospitalCode) {
		this.subHospitalCode = subHospitalCode;
	}




	public String getZmapDeptCode() {
		return zmapDeptCode;
	}




	public void setZmapDeptCode(String zmapDeptCode) {
		this.zmapDeptCode = zmapDeptCode;
	}




	public String getHospitalCode() {
		return hospitalCode;
	}




	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}




	public static long getSerialversionuid() {
		return serialVersionUID;
	}




	@Override
	public String toString() {
		return "HisHospitalizeHM2ExamineSyncModel [inHosCode=" + inHosCode
				+ ", examFlowCode=" + examFlowCode + ", examTime=" + examTime
				+ ", sickmanCode=" + sickmanCode + ", doctorCode=" + doctorCode
				+ ", deptCode=" + deptCode + ", zmapExamCode=" + zmapExamCode
				+ ", examTypeCode=" + examTypeCode + ", examCode=" + examCode
				+ ", examResult=" + examResult + ", subHospitalCode="
				+ subHospitalCode + ", zmapDeptCode=" + zmapDeptCode
				+ ", hospitalCode=" + hospitalCode + "]";
	}




	public ZmapHospitalizeHM2ExamineSyncVO toVo(){
		ZmapHospitalizeHM2ExamineSyncVO destObj=new ZmapHospitalizeHM2ExamineSyncVO();
		destObj.setDeptCode(this.getDeptCode());
		destObj.setDoctorCode(this.getDoctorCode());
		destObj.setExamCode(this.getExamCode());
		destObj.setExamFlowCode(this.getExamFlowCode());
		destObj.setExamId(UUID.randomUUID().toString());
		destObj.setExamResult(this.getExamResult());
		destObj.setExamTime(this.getExamTime());
		destObj.setExamTypeCode(this.getExamTypeCode());
		destObj.setHospitalCode("1");
		destObj.setInHosCode("");
		destObj.setSickmanCode(this.getSickmanCode());
		destObj.setSubHospitalCode(this.getSubHospitalCode());
		destObj.setZmapDeptCode("");
		destObj.setZmapExamCode("");

	
		return destObj;
	}
}
