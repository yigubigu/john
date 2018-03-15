package com.zmap.his.basedata.model;

import java.io.Serializable;
import java.util.UUID;

public class RDoctorModel implements Serializable{
	private static final long serialVersionUID = 1L;
	private String doctorId;
	private String doctorCode;
	private String deptCode;
	private String zmapDeptCode;
	private String status;
	private String doctorName;
	private String sex;
	private String idCard;
	private String birthday;
	private String jobTitleCode;
	private String jobTitle;
	private String subHospitalCode;
	private String hospitalCode;
	private String zmapDoctorCode;
	private String zmapDoctorName;
	private String password;
	public String getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
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
	public String getZmapDeptCode() {
		return zmapDeptCode;
	}
	public void setZmapDeptCode(String zmapDeptCode) {
		this.zmapDeptCode = zmapDeptCode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getJobTitleCode() {
		return jobTitleCode;
	}
	public void setJobTitleCode(String jobTitleCode) {
		this.jobTitleCode = jobTitleCode;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public String getSubHospitalCode() {
		return subHospitalCode;
	}
	public void setSubHospitalCode(String subHospitalCode) {
		this.subHospitalCode = subHospitalCode;
	}
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	public String getZmapDoctorCode() {
		return zmapDoctorCode;
	}
	public void setZmapDoctorCode(String zmapDoctorCode) {
		this.zmapDoctorCode = zmapDoctorCode;
	}
	public String getZmapDoctorName() {
		return zmapDoctorName;
	}
	public void setZmapDoctorName(String zmapDoctorName) {
		this.zmapDoctorName = zmapDoctorName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public ZmapRDoctorVO toVo() {
		ZmapRDoctorVO destObj = new ZmapRDoctorVO();
		destObj.setDoctorId(UUID.randomUUID().toString());
		destObj.setBirthday(this.getBirthday());
		destObj.setDeptCode(this.getDeptCode());
		destObj.setDoctorCode(this.getDoctorCode());
		destObj.setDoctorName(this.getDoctorName());
		destObj.setHospitalCode(this.getHospitalCode());
		destObj.setIdCard(this.getIdCard());
		destObj.setJobTitle(this.getJobTitle());
		destObj.setJobTitleCode(this.getJobTitleCode());
		destObj.setPassword(this.getPassword());
		destObj.setSex(this.getSex());
		destObj.setStatus(this.getStatus());
		destObj.setSubHospitalCode(this.getSubHospitalCode());
		destObj.setZmapDeptCode(this.getZmapDeptCode());
		destObj.setZmapDoctorCode(this.getZmapDoctorCode());
		destObj.setZmapDoctorName(this.getZmapDoctorName());
		
		return destObj;
	}
}
