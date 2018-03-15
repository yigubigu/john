package com.zmap.his.hospitalize.model;

import java.math.BigDecimal;
import java.util.UUID;

public class HisHospitalizeHM2ChargeSyncModel {
	private String inHosCode;
	private String sickFeeFlowCode;
	private String inHosFlowCode;
	private String sickmanCode;
	private String doctorCode;
	private String deptCode;
	private String zmapDeptCode;
	private String feeDate;
	private String feeSourceCode;
	private String feeClassCode;
	private String feeTypeCode;
	private String feeItemCode;
	private String zmapFeeItemCode;
	private String size;
	private String unit;
	private BigDecimal num;
	private BigDecimal unitPrice;
	private BigDecimal disPrice;
	private String disRatio;
	private BigDecimal amount;
	private String pharCode;
	private String subHospitalCode;
	private String hospitalCode;
	private String settleAcctCode;
	private String settleTypeCode;
	private String isInsureSettle;
	private String acctItemCode;
	private String settleChargeTime;
	private String insureSettleTime;
	private BigDecimal recAmount;
	private String morderCode;

	
	
	
	public String getInHosFlowCode() {
		return inHosFlowCode;
	}


	public void setInHosFlowCode(String inHosFlowCode) {
		this.inHosFlowCode = inHosFlowCode;
	}


	public String getMorderCode() {
		return morderCode;
	}


	public void setMorderCode(String morderCode) {
		this.morderCode = morderCode;
	}


	public String getSettleChargeTime() {
		return settleChargeTime;
	}


	public void setSettleChargeTime(String settleChargeTime) {
		this.settleChargeTime = settleChargeTime;
	}


	public String getInsureSettleTime() {
		return insureSettleTime;
	}


	public void setInsureSettleTime(String insureSettleTime) {
		this.insureSettleTime = insureSettleTime;
	}


	public BigDecimal getRecAmount() {
		return recAmount;
	}


	public void setRecAmount(BigDecimal recAmount) {
		this.recAmount = recAmount;
	}


	public String getInHosCode() {
		return inHosCode;
	}


	public void setInHosCode(String inHosCode) {
		this.inHosCode = inHosCode;
	}


	public String getSickFeeFlowCode() {
		return sickFeeFlowCode;
	}


	public void setSickFeeFlowCode(String sickFeeFlowCode) {
		this.sickFeeFlowCode = sickFeeFlowCode;
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


	public String getZmapDeptCode() {
		return zmapDeptCode;
	}


	public void setZmapDeptCode(String zmapDeptCode) {
		this.zmapDeptCode = zmapDeptCode;
	}


	public String getFeeDate() {
		return feeDate;
	}


	public void setFeeDate(String feeDate) {
		this.feeDate = feeDate;
	}


	public String getFeeSourceCode() {
		return feeSourceCode;
	}


	public void setFeeSourceCode(String feeSourceCode) {
		this.feeSourceCode = feeSourceCode;
	}


	public String getFeeClassCode() {
		return feeClassCode;
	}


	public void setFeeClassCode(String feeClassCode) {
		this.feeClassCode = feeClassCode;
	}


	public String getFeeTypeCode() {
		return feeTypeCode;
	}


	public void setFeeTypeCode(String feeTypeCode) {
		this.feeTypeCode = feeTypeCode;
	}


	public String getFeeItemCode() {
		return feeItemCode;
	}


	public void setFeeItemCode(String feeItemCode) {
		this.feeItemCode = feeItemCode;
	}


	public String getZmapFeeItemCode() {
		return zmapFeeItemCode;
	}


	public void setZmapFeeItemCode(String zmapFeeItemCode) {
		this.zmapFeeItemCode = zmapFeeItemCode;
	}


	public String getSize() {
		return size;
	}


	public void setSize(String size) {
		this.size = size;
	}


	public String getUnit() {
		return unit;
	}


	public void setUnit(String unit) {
		this.unit = unit;
	}


	public BigDecimal getNum() {
		return num;
	}


	public void setNum(BigDecimal num) {
		this.num = num;
	}


	public BigDecimal getUnitPrice() {
		return unitPrice;
	}


	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}


	public BigDecimal getDisPrice() {
		return disPrice;
	}


	public void setDisPrice(BigDecimal disPrice) {
		this.disPrice = disPrice;
	}


	public String getDisRatio() {
		return disRatio;
	}


	public void setDisRatio(String disRatio) {
		this.disRatio = disRatio;
	}


	public BigDecimal getAmount() {
		return amount;
	}


	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}


	public String getPharCode() {
		return pharCode;
	}


	public void setPharCode(String pharCode) {
		this.pharCode = pharCode;
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



	public String getSettleAcctCode() {
		return settleAcctCode;
	}


	public void setSettleAcctCode(String settleAcctCode) {
		this.settleAcctCode = settleAcctCode;
	}


	public String getSettleTypeCode() {
		return settleTypeCode;
	}


	public void setSettleTypeCode(String settleTypeCode) {
		this.settleTypeCode = settleTypeCode;
	}


	public String getIsInsureSettle() {
		return isInsureSettle;
	}


	public void setIsInsureSettle(String isInsureSettle) {
		this.isInsureSettle = isInsureSettle;
	}


	public String getAcctItemCode() {
		return acctItemCode;
	}


	public void setAcctItemCode(String acctItemCode) {
		this.acctItemCode = acctItemCode;
	}


	public ZmapHospitalizeHM2ChargeSyncVO toVo() {
		ZmapHospitalizeHM2ChargeSyncVO destObj = new ZmapHospitalizeHM2ChargeSyncVO();
		destObj.setAmount(this.getAmount());
		destObj.setDeptCode(this.getDeptCode());
		destObj.setDisRatio(this.getDisRatio());
		destObj.setDoctorCode(this.getDoctorCode());
		destObj.setDisPrice(this.getDisPrice());
		destObj.setFeeClassCode(this.getFeeClassCode());
		destObj.setFeeDate(this.getFeeDate());
		destObj.setFeeItemCode(this.getFeeItemCode());
		destObj.setFeeSourceCode(this.getFeeSourceCode());
		destObj.setFeeTypeCode(this.getFeeTypeCode());
		destObj.setHospitalCode(this.getHospitalCode());
		destObj.setInHosCode(this.getInHosCode());
		destObj.setNum(this.getNum());
		destObj.setPharCode(this.getPharCode());
		destObj.setSickFeeFlowCode(this.getSickFeeFlowCode());
		destObj.setSickFeeFlowId(UUID.randomUUID().toString());
		destObj.setSickmanCode(this.getSickmanCode());
		destObj.setSize(this.getSize());
		destObj.setSubHospitalCode(this.getSubHospitalCode());
		destObj.setUnit(this.getUnit());
		destObj.setUnitPrice(this.getUnitPrice());
		destObj.setZmapDeptCode(this.getZmapDeptCode());
		destObj.setZmapFeeItemCode(this.getZmapFeeItemCode());
		destObj.setSettleAcctCode(this.getSettleAcctCode());
		destObj.setSettleTypeCode(this.getSettleTypeCode());
		destObj.setIsInsureSettle(this.getIsInsureSettle());
		destObj.setAcctItemCode(this.getAcctItemCode());
		destObj.setSettleChargeTime(this.getSettleChargeTime());
		destObj.setInsureSettleTime(this.getInsureSettleTime());
		destObj.setRecAmount(this.getRecAmount());
		destObj.setMorderCode(this.getMorderCode());
		destObj.setInHosFlowCode(this.getInHosFlowCode());
		
		return destObj;
	}
}
