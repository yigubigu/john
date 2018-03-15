package com.zmap.zmap.framework.base.model;



/**
 * 
 * 类描述：科室数据载体实体
 * 创建者：fanmm
 * 项目名称：model
 * 创建时间: 2016年12月22日 上午10:48:51
 * 版本号: v1.0
 */
public class ZmapRDeptModel {
	
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
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
	public String getZmapDeptCode() {
		return zmapDeptCode;
	}
	public void setZmapDeptCode(String zmapDeptCode) {
		this.zmapDeptCode = zmapDeptCode;
	}
	public String getZmapDeptName() {
		return zmapDeptName;
	}
	public void setZmapDeptName(String zmapDeptName) {
		this.zmapDeptName = zmapDeptName;
	}
	public String getDeptTypeCode() {
		return deptTypeCode;
	}
	public void setDeptTypeCode(String deptTypeCode) {
		this.deptTypeCode = deptTypeCode;
	}
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	public String getSubHospitalCode() {
		return subHospitalCode;
	}
	public void setSubHospitalCode(String subHospitalCode) {
		this.subHospitalCode = subHospitalCode;
	}
	private String deptId;//科室UUID
	private String deptCode;//科室编码
	private String deptName;//科室名称
	private String zmapDeptCode;//科室标准编码
	private String zmapDeptName;//科室标准名称
	private String deptTypeCode;//科室类型
	private String hospitalCode;//医院编码
	private String subHospitalCode;//分院编码
	private String parentCode;//parentCode
	private String isLeaf;//isLeaf
	private String levelCode;//
	public String getParentCode() {
		return parentCode;
	}
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	public String getIsLeaf() {
		return isLeaf;
	}
	public void setIsLeaf(String isLeaf) {
		this.isLeaf = isLeaf;
	}
	public String getLevelCode() {
		return levelCode;
	}
	public void setLevelCode(String levelCode) {
		this.levelCode = levelCode;
	}

	
}
