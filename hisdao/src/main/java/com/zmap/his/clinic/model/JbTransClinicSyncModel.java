package com.zmap.his.clinic.model;

import java.io.Serializable;
import java.util.UUID;

public class JbTransClinicSyncModel implements Serializable{
	private static final long serialVersionUID = 1L;
	private String regSeq;
	private String applyDeptCode;
	private String applyDoctorCode;
	private String recDeptCode;
	private String recDoctorCode;
	private String recTime;
	private String regCode;
	public String getRegSeq() {
		return regSeq;
	}
	public void setRegSeq(String regSeq) {
		this.regSeq = regSeq;
	}
	public String getApplyDeptCode() {
		return applyDeptCode;
	}
	public void setApplyDeptCode(String applyDeptCode) {
		this.applyDeptCode = applyDeptCode;
	}
	public String getApplyDoctorCode() {
		return applyDoctorCode;
	}
	public void setApplyDoctorCode(String applyDoctorCode) {
		this.applyDoctorCode = applyDoctorCode;
	}
	public String getRecDeptCode() {
		return recDeptCode;
	}
	public void setRecDeptCode(String recDeptCode) {
		this.recDeptCode = recDeptCode;
	}
	public String getRecDoctorCode() {
		return recDoctorCode;
	}
	public void setRecDoctorCode(String recDoctorCode) {
		this.recDoctorCode = recDoctorCode;
	}
	public String getRecTime() {
		return recTime;
	}
	public void setRecTime(String recTime) {
		this.recTime = recTime;
	}
	public String getRegCode() {
		return regCode;
	}
	public void setRegCode(String regCode) {
		this.regCode = regCode;
	}
	
	/**
	 * 对应model层VO
	 * @return
	 */
	public JbTransClinicSyncVO toVo(){
		JbTransClinicSyncVO destObj = new JbTransClinicSyncVO();
		destObj.setApplyDeptCode(this.getApplyDeptCode());
		destObj.setApplyDoctorCode(this.getApplyDoctorCode());
		destObj.setRecDeptCode(this.getRecDeptCode());
		destObj.setRecDoctorCode(this.getRecDoctorCode());
		destObj.setRecTime(this.getRecTime());
		destObj.setRegCode(this.getRegCode());
		destObj.setRegSeq(this.getRegSeq());
		
		return destObj;
	}
}
