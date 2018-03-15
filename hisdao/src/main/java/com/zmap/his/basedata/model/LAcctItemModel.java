package com.zmap.his.basedata.model;

import java.util.UUID;

public class LAcctItemModel {
	private String acctItemId;
	private String acctItemCode;
	private String parentCode;
	private String acctItemName;
	private String isLeaf;
	private String recItemName;
	private String mRecordItemName;
	private String createTime;
	private String dropTime;
	private String remark;
	public String getAcctItemId() {
		return acctItemId;
	}
	public void setAcctItemId(String acctItemId) {
		this.acctItemId = acctItemId;
	}
	public String getAcctItemCode() {
		return acctItemCode;
	}
	public void setAcctItemCode(String acctItemCode) {
		this.acctItemCode = acctItemCode;
	}
	public String getParentCode() {
		return parentCode;
	}
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	public String getAcctItemName() {
		return acctItemName;
	}
	public void setAcctItemName(String acctItemName) {
		this.acctItemName = acctItemName;
	}
	public String getIsLeaf() {
		return isLeaf;
	}
	public void setIsLeaf(String isLeaf) {
		this.isLeaf = isLeaf;
	}
	public String getRecItemName() {
		return recItemName;
	}
	public void setRecItemName(String recItemName) {
		this.recItemName = recItemName;
	}
	public String getmRecordItemName() {
		return mRecordItemName;
	}
	public void setmRecordItemName(String mRecordItemName) {
		this.mRecordItemName = mRecordItemName;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getDropTime() {
		return dropTime;
	}
	public void setDropTime(String dropTime) {
		this.dropTime = dropTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public ZmapLAcctItemVO toVo() {
		ZmapLAcctItemVO destObj = new ZmapLAcctItemVO();
		destObj.setAcctItemId(UUID.randomUUID().toString());
		destObj.setAcctItemCode(this.getAcctItemCode());
		destObj.setAcctItemName(this.getAcctItemName());
		destObj.setCreateTime(this.getCreateTime());
		destObj.setDropTime(this.getDropTime());
		destObj.setIsLeaf(this.getIsLeaf());
		destObj.setmRecordItemName(this.getmRecordItemName());
		destObj.setParentCode(this.getParentCode());
		destObj.setRecItemName(this.getRecItemName());
		destObj.setRemark(this.getRemark());
		
		return destObj;
	}
}
