package com.zmap.his.clinic.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 中间VO层，对应zmap库中model
 * @author Admin
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ZmapClinicCM2ChargeSyncVO {

   private String chargeId;
   private String clinicCode;
   private String chargeCode;
   private String regCode;
   private String patientCode;
   private String chargeTime;
   private String deptCode;
   private String doctorCode;
   private String feeClassCode;
   private String feeTypeCode;
   private String feeItemCode;
   private String feeSourceCode;
   private String settleTypeCode;
   private Double amount;
   private Double num;
   private Double unitPrice;
   private String size;
   private Double discountAmount;
   private String discountRate;
   private String iccardCode;
   private String zmapDeptCode;
   private String zmapFeeItemCode;
   private String subHospitalCode;
   private String hospitalCode;
   private String isInsuresettle;
   private String acctItemCode;
   private String settleAcctCode;
   private String recStatus;
   private String recType;
   private String morderCode;;

   

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
public String getSettleAcctCode() {
	return settleAcctCode;
}
public void setSettleAcctCode(String settleAcctCode) {
	this.settleAcctCode = settleAcctCode;
}
public String getAcctItemCode() {
	return acctItemCode;
}
public void setAcctItemCode(String acctItemCode) {
	this.acctItemCode = acctItemCode;
}
public String getIsInsuresettle() {
	return isInsuresettle;
}
public void setIsInsuresettle(String isInsuresettle) {
	this.isInsuresettle = isInsuresettle;
}
public String getFeeTypeCode() {
	return feeTypeCode;
}
public void setFeeTypeCode(String feeTypeCode) {
	this.feeTypeCode = feeTypeCode;
}
public String getZmapDeptCode() {
	return zmapDeptCode;
}
public void setZmapDeptCode(String zmapDeptCode) {
	this.zmapDeptCode = zmapDeptCode;
}
public String getZmapFeeItemCode() {
	return zmapFeeItemCode;
}
public void setZmapFeeItemCode(String zmapFeeItemCode) {
	this.zmapFeeItemCode = zmapFeeItemCode;
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
public String getChargeId() {
	return chargeId;
}
public void setChargeId(String chargeId) {
	this.chargeId = chargeId;
}
public String getClinicCode() {
	return clinicCode;
}
public void setClinicCode(String clinicCode) {
	this.clinicCode = clinicCode;
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
public String getFeeItemCode() {
	return feeItemCode;
}
public void setFeeItemCode(String feeItemCode) {
	this.feeItemCode = feeItemCode;
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
   
}
