package com.zmap.his.clinic.model;


public class ValueObjectBuilder {

	public static PatienRegistrationVO buildPaientRegistration(PatientRegistration source) {
		PatienRegistrationVO destObj = new PatienRegistrationVO();
		destObj.setOriginalId(source.getId());
		destObj.setCreateTime(source.getCreateTime());
		destObj.setDoctorId(source.getDoctorId());
		destObj.setPatientCardNumber(source.getPatientCardNumber());
		destObj.setRegistrationNumber(source.getRegistrationNumber());
		destObj.setRegistrationTime(source.getRegistrationTime());
		destObj.setServicedepartmentId(source.getServiceDepartmentId());
		destObj.setUpdateTime(source.getUpdateTime());
		
		return destObj;
	}
}
