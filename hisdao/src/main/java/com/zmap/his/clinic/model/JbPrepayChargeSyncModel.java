package com.zmap.his.clinic.model;

import java.io.Serializable;
import java.util.UUID;

public class JbPrepayChargeSyncModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String prepayId;
	private String prepayCode;
	private String recTypeCode;
	private String regSeq;
	private String recStatus;
	private String sickmanCode;
	private String mhomePageCode;
	private String deptCode;
	private String payCompany;
	private String companyBank;
	private String companyAcctNo;
	private String remark;
	private String amount;
	private String settleTypeCode;
	private String collectTime;
	private String operCode;
	private String operName;
	private String agaistAmount;
	private String settleAcctCode;
	private String recAmount;
	private String changeAmount;
	private String prepayTypeCode;
	private String settleSeq;
	public String getPrepayId() {
		return prepayId;
	}
	public void setPrepayId(String prepayId) {
		this.prepayId = prepayId;
	}
	public String getPrepayCode() {
		return prepayCode;
	}
	public void setPrepayCode(String prepayCode) {
		this.prepayCode = prepayCode;
	}
	public String getRecTypeCode() {
		return recTypeCode;
	}
	public void setRecTypeCode(String recTypeCode) {
		this.recTypeCode = recTypeCode;
	}
	public String getRegSeq() {
		return regSeq;
	}
	public void setRegSeq(String regSeq) {
		this.regSeq = regSeq;
	}
	public String getRecStatus() {
		return recStatus;
	}
	public void setRecStatus(String recStatus) {
		this.recStatus = recStatus;
	}
	public String getSickmanCode() {
		return sickmanCode;
	}
	public void setSickmanCode(String sickmanCode) {
		this.sickmanCode = sickmanCode;
	}
	public String getMhomePageCode() {
		return mhomePageCode;
	}
	public void setMhomePageCode(String mhomePageCode) {
		this.mhomePageCode = mhomePageCode;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getPayCompany() {
		return payCompany;
	}
	public void setPayCompany(String payCompany) {
		this.payCompany = payCompany;
	}
	public String getCompanyBank() {
		return companyBank;
	}
	public void setCompanyBank(String companyBank) {
		this.companyBank = companyBank;
	}
	public String getCompanyAcctNo() {
		return companyAcctNo;
	}
	public void setCompanyAcctNo(String companyAcctNo) {
		this.companyAcctNo = companyAcctNo;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getSettleTypeCode() {
		return settleTypeCode;
	}
	public void setSettleTypeCode(String settleTypeCode) {
		this.settleTypeCode = settleTypeCode;
	}
	public String getCollectTime() {
		return collectTime;
	}
	public void setCollectTime(String collectTime) {
		this.collectTime = collectTime;
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
	public String getAgaistAmount() {
		return agaistAmount;
	}
	public void setAgaistAmount(String agaistAmount) {
		this.agaistAmount = agaistAmount;
	}
	public String getSettleAcctCode() {
		return settleAcctCode;
	}
	public void setSettleAcctCode(String settleAcctCode) {
		this.settleAcctCode = settleAcctCode;
	}
	public String getRecAmount() {
		return recAmount;
	}
	public void setRecAmount(String recAmount) {
		this.recAmount = recAmount;
	}
	public String getChangeAmount() {
		return changeAmount;
	}
	public void setChangeAmount(String changeAmount) {
		this.changeAmount = changeAmount;
	}
	public String getPrepayTypeCode() {
		return prepayTypeCode;
	}
	public void setPrepayTypeCode(String prepayTypeCode) {
		this.prepayTypeCode = prepayTypeCode;
	}
	public String getSettleSeq() {
		return settleSeq;
	}
	public void setSettleSeq(String settleSeq) {
		this.settleSeq = settleSeq;
	}

	public JbPrepayChargeSyncVO toVo() {
		JbPrepayChargeSyncVO destObj = new JbPrepayChargeSyncVO();
		destObj.setPrepayId(UUID.randomUUID().toString());
		destObj.setAgaistAmount(this.getAgaistAmount());
		destObj.setAmount(this.getAmount());
		destObj.setChangeAmount(this.getChangeAmount());
		destObj.setCollectTime(this.getCollectTime());
		destObj.setCompanyAcctNo(this.getCompanyAcctNo());
		destObj.setCompanyBank(this.getCompanyBank());
		destObj.setDeptCode(this.getDeptCode());
		destObj.setMhomePageCode(this.getMhomePageCode());
		destObj.setOperCode(this.getOperCode());
		destObj.setOperName(this.getOperName());
		destObj.setPayCompany(this.getPayCompany());
		destObj.setPrepayCode(this.getPrepayCode());
		destObj.setPrepayTypeCode(this.getPrepayTypeCode());
		destObj.setRecAmount(this.getRecAmount());
		destObj.setRecStatus(this.getRecStatus());
		destObj.setRecTypeCode(this.getRecTypeCode());
		destObj.setRegSeq(this.getRegSeq());
		destObj.setRemark(this.getRemark());
		destObj.setSettleAcctCode(this.getSettleAcctCode());
		destObj.setSettleSeq(this.getSettleSeq());
		destObj.setSettleTypeCode(this.getSettleTypeCode());
		destObj.setSickmanCode(this.getSickmanCode());
		
		return destObj;
	}
	
}
