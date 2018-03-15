package com.zmap.his.basedata.model;

import java.util.UUID;

public class LOperationModel {
	private String operId;
	private String operCode;
	private String operName;
	private String zmapOperCode;
	private String zmapOperName;
	private String icdCode;
	private String icdName;
	private String pinyinCode;
	private String operType1Code;
	private String operType2Code;
	private String isSerious;
	private String subHospitalCode;
	private String hospitalCode;
	public String getOperId() {
		return operId;
	}
	public void setOperId(String operId) {
		this.operId = operId;
	}
	public String getOperCode() {
		return operCode;
	}
	public void setOperCode(String operCode) {
		this.operCode = operCode;
	}
	public String getOperName() {
		return operName;
	}
	public void setOperName(String operName) {
		this.operName = operName;
	}
	public String getZmapOperCode() {
		return zmapOperCode;
	}
	public void setZmapOperCode(String zmapOperCode) {
		this.zmapOperCode = zmapOperCode;
	}
	public String getZmapOperName() {
		return zmapOperName;
	}
	public void setZmapOperName(String zmapOperName) {
		this.zmapOperName = zmapOperName;
	}
	public String getIcdCode() {
		return icdCode;
	}
	public void setIcdCode(String icdCode) {
		this.icdCode = icdCode;
	}
	public String getIcdName() {
		return icdName;
	}
	public void setIcdName(String icdName) {
		this.icdName = icdName;
	}
	public String getPinyinCode() {
		return pinyinCode;
	}
	public void setPinyinCode(String pinyinCode) {
		this.pinyinCode = pinyinCode;
	}
	public String getOperType1Code() {
		return operType1Code;
	}
	public void setOperType1Code(String operType1Code) {
		this.operType1Code = operType1Code;
	}
	public String getOperType2Code() {
		return operType2Code;
	}
	public void setOperType2Code(String operType2Code) {
		this.operType2Code = operType2Code;
	}
	public String getIsSerious() {
		return isSerious;
	}
	public void setIsSerious(String isSerious) {
		this.isSerious = isSerious;
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

	public ZmapLOperationVO toVo() {
		ZmapLOperationVO destObj = new ZmapLOperationVO();
		destObj.setOperId(UUID.randomUUID().toString());
		destObj.setHospitalCode(this.getHospitalCode());
		destObj.setIcdCode(this.getIcdCode());
		destObj.setIcdName(this.getIcdName());
		destObj.setIsSerious(this.getIsSerious());
		destObj.setOperCode(this.getOperCode());
		destObj.setOperName(this.getOperName());
		destObj.setOperType1Code(this.getOperType1Code());
		destObj.setOperType2Code(this.getOperType2Code());
		destObj.setPinyinCode(this.getPinyinCode());
		destObj.setSubHospitalCode(this.getSubHospitalCode());
		destObj.setZmapOperCode(this.getZmapOperCode());
		destObj.setZmapOperName(this.getZmapOperName());
		
		return destObj;
	}
}
