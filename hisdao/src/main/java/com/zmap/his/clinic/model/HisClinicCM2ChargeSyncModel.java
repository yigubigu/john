package com.zmap.his.clinic.model;

import java.io.Serializable;
import java.util.UUID;


public class HisClinicCM2ChargeSyncModel  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String chargeCode;
	private String regCode;
	private String patientCode;
	private String chargeTime;
	private String deptCode;
	private String zmapDeptCode;
	private String doctorCode;
	private String feeClassCode;
	private String feeTypeCode;
	private String feeItemCode;
	private String zmapFeeItemCode;
	private String feeSourceCode;
	private String settleTypeCode;
	private Double amount;
	private Double num;
	private Double unitPrice;
	private String size;
	private Double discountAmount;
	private String discountRate;
	private String iccardCode;
	private String subHospitalCode;
	private String hospitalCode;
	private String settleAcctCode;
	private String isInsureSettle;
	private String acctItemCode;
	private String recStatus;
	private String recType;
	private String morderCode;
	
	
	
	public String getMorderCode() {
		return morderCode;
	}


	public void setMorderCode(String morderCode) {
		this.morderCode = morderCode;
	}


	public String getRecStatus() {
		return recStatus;
	}


	public void setRecStatus(String recStatus) {
		this.recStatus = recStatus;
	}


	public String getRecType() {
		return recType;
	}


	public void setRecType(String recType) {
		this.recType = recType;
	}


	public String getChargeCode() {
		return chargeCode;
	}


	public void setChargeCode(String chargeCode) {
		this.chargeCode = chargeCode;
	}


	public String getRegCode() {
		return regCode;
	}


	public void setRegCode(String regCode) {
		this.regCode = regCode;
	}


	public String getPatientCode() {
		return patientCode;
	}


	public void setPatientCode(String patientCode) {
		this.patientCode = patientCode;
	}


	public String getChargeTime() {
		return chargeTime;
	}


	public void setChargeTime(String chargeTime) {
		this.chargeTime = chargeTime;
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


	public String getDoctorCode() {
		return doctorCode;
	}


	public void setDoctorCode(String doctorCode) {
		this.doctorCode = doctorCode;
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


	public String getFeeSourceCode() {
		return feeSourceCode;
	}


	public void setFeeSourceCode(String feeSourceCode) {
		this.feeSourceCode = feeSourceCode;
	}


	public String getSettleTypeCode() {
		return settleTypeCode;
	}


	public void setSettleTypeCode(String settleTypeCode) {
		this.settleTypeCode = settleTypeCode;
	}


	public Double getAmount() {
		return amount;
	}


	public void setAmount(Double amount) {
		this.amount = amount;
	}


	public Double getNum() {
		return num;
	}


	public void setNum(Double num) {
		this.num = num;
	}


	public Double getUnitPrice() {
		return unitPrice;
	}


	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}


	public String getSize() {
		return size;
	}


	public void setSize(String size) {
		this.size = size;
	}


	public Double getDiscountAmount() {
		return discountAmount;
	}


	public void setDiscountAmount(Double discountAmount) {
		this.discountAmount = discountAmount;
	}


	public String getDiscountRate() {
		return discountRate;
	}


	public void setDiscountRate(String discountRate) {
		this.discountRate = discountRate;
	}


	public String getIccardCode() {
		return iccardCode;
	}


	public void setIccardCode(String iccardCode) {
		this.iccardCode = iccardCode;
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

	
	public ZmapClinicCM2ChargeSyncVO toVo(){
		ZmapClinicCM2ChargeSyncVO destObj = new ZmapClinicCM2ChargeSyncVO();
		destObj.setChargeId(UUID.randomUUID().toString());
		destObj.setAmount(this.getAmount());
		destObj.setChargeCode(this.getChargeCode());
		destObj.setChargeTime(this.getChargeTime());
		destObj.setDeptCode(this.getDeptCode());
		destObj.setDiscountAmount(this.getDiscountAmount());
		destObj.setDiscountRate(this.getDiscountRate());
		destObj.setDoctorCode(this.getDoctorCode());
		destObj.setFeeClassCode(this.getFeeClassCode());
		destObj.setFeeTypeCode(this.getFeeTypeCode());
		destObj.setFeeItemCode(this.getFeeItemCode());
		destObj.setFeeSourceCode(this.getFeeSourceCode());
		destObj.setIccardCode(this.getIccardCode());
		destObj.setNum(this.getNum());
		destObj.setPatientCode(this.getPatientCode());
		destObj.setUnitPrice(this.getUnitPrice());
		destObj.setRegCode(this.getRegCode());
		destObj.setSettleTypeCode(this.getSettleTypeCode());
		destObj.setSize(this.getSize());
		destObj.setZmapDeptCode(this.getZmapDeptCode());
		destObj.setZmapFeeItemCode(this.getZmapFeeItemCode());
		destObj.setSubHospitalCode(this.getSubHospitalCode());
		destObj.setHospitalCode(this.getHospitalCode());
		destObj.setIsInsuresettle(this.getIsInsureSettle());
		destObj.setAcctItemCode(this.getAcctItemCode());
		destObj.setSettleAcctCode(this.getSettleAcctCode());
		destObj.setRecStatus(this.getRecStatus());
		destObj.setRecType(this.getRecType());
		destObj.setMorderCode(this.getMorderCode());
		
		return destObj;
	}
}
