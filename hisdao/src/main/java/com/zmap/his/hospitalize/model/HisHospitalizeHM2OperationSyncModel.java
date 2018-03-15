package com.zmap.his.hospitalize.model;

import java.io.Serializable;
import java.util.UUID;

public class HisHospitalizeHM2OperationSyncModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String operFlowId;
	private String inHosCode;
	private String operFlowCode;
	private String sickmanCode;
	private String operTime;
	private String operCode;
	private String mainDoctorCode;
	private String anesDoctorCode;
	private String anesDoctor2Code;
	private String itinerNurseCode;
	private String instrNurseCode;
	private String assist1Code;
	private String assist2Code;
	private String anesType;
	private String subHospitalCode;
	private String hospitalCode;
	public String getOperFlowId() {
		return operFlowId;
	}
	public void setOperFlowId(String operFlowId) {
		this.operFlowId = operFlowId;
	}
	public String getInHosCode() {
		return inHosCode;
	}
	public void setInHosCode(String inHosCode) {
		this.inHosCode = inHosCode;
	}
	public String getOperFlowCode() {
		return operFlowCode;
	}
	public void setOperFlowCode(String operFlowCode) {
		this.operFlowCode = operFlowCode;
	}
	public String getSickmanCode() {
		return sickmanCode;
	}
	public void setSickmanCode(String sickmanCode) {
		this.sickmanCode = sickmanCode;
	}
	public String getOperTime() {
		return operTime;
	}
	public void setOperTime(String operTime) {
		this.operTime = operTime;
	}
	public String getOperCode() {
		return operCode;
	}
	public void setOperCode(String operCode) {
		this.operCode = operCode;
	}
	public String getMainDoctorCode() {
		return mainDoctorCode;
	}
	public void setMainDoctorCode(String mainDoctorCode) {
		this.mainDoctorCode = mainDoctorCode;
	}
	public String getAnesDoctorCode() {
		return anesDoctorCode;
	}
	public void setAnesDoctorCode(String anesDoctorCode) {
		this.anesDoctorCode = anesDoctorCode;
	}
	public String getAnesDoctor2Code() {
		return anesDoctor2Code;
	}
	public void setAnesDoctor2Code(String anesDoctor2Code) {
		this.anesDoctor2Code = anesDoctor2Code;
	}
	public String getItinerNurseCode() {
		return itinerNurseCode;
	}
	public void setItinerNurseCode(String itinerNurseCode) {
		this.itinerNurseCode = itinerNurseCode;
	}
	public String getInstrNurseCode() {
		return instrNurseCode;
	}
	public void setInstrNurseCode(String instrNurseCode) {
		this.instrNurseCode = instrNurseCode;
	}
	public String getAssist1Code() {
		return assist1Code;
	}
	public void setAssist1Code(String assist1Code) {
		this.assist1Code = assist1Code;
	}
	public String getAssist2Code() {
		return assist2Code;
	}
	public void setAssist2Code(String assist2Code) {
		this.assist2Code = assist2Code;
	}
	public String getAnesType() {
		return anesType;
	}
	public void setAnesType(String anesType) {
		this.anesType = anesType;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "HisHospitalizeHM2OperationSyncModel [operFlowId=" + operFlowId
				+ ", inHosCode=" + inHosCode + ", operFlowCode=" + operFlowCode
				+ ", sickmanCode=" + sickmanCode + ", operTime=" + operTime
				+ ", operCode=" + operCode + ", mainDoctorCode="
				+ mainDoctorCode + ", anesDoctorCode=" + anesDoctorCode
				+ ", anesDoctor2Code=" + anesDoctor2Code + ", itinerNurseCode="
				+ itinerNurseCode + ", instrNurseCode=" + instrNurseCode
				+ ", assist1Code=" + assist1Code + ", assist2Code="
				+ assist2Code + ", anesType=" + anesType + ", subHospitalCode="
				+ subHospitalCode + ", hospitalCode=" + hospitalCode + "]";
	}
	
	public ZmapHospitalizeHM2OperationSyncVO toVo(){
		ZmapHospitalizeHM2OperationSyncVO destObj=new ZmapHospitalizeHM2OperationSyncVO();
		destObj.setAnesDoctor2Code(this.getAnesDoctor2Code());
		destObj.setAnesDoctorCode(this.getAnesDoctorCode());
		destObj.setAnesType(this.getAnesType());
		destObj.setAssist1Code(this.getAssist1Code());
		destObj.setAssist2Code(this.getAssist2Code());
		destObj.setHospitalCode("");
		destObj.setInHosCode(this.getInHosCode());
		destObj.setInstrNurseCode(this.getInstrNurseCode());
		destObj.setItinerNurseCode(this.getItinerNurseCode());
		destObj.setMainDoctorCode(this.getMainDoctorCode());
		destObj.setOperCode(this.getOperCode());
		destObj.setOperFlowCode(this.getOperFlowCode());
		destObj.setOperFlowId(UUID.randomUUID().toString());
		destObj.setOperTime(this.getOperTime());
		destObj.setSickmanCode(this.getSickmanCode());
		destObj.setSubHospitalCode(this.getSubHospitalCode());
		
		
		return destObj;
		
	}

}
