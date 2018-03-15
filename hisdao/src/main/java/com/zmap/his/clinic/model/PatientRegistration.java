package com.zmap.his.clinic.model;

import java.io.Serializable;
import java.util.Date;

public class PatientRegistration implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;
	private int registrationNumber;
	private Date registrationTime;
	private String doctorId;
	private String serviceDepartmentId;
	private String patientCardNumber;
	private Date createTime;
	private Date updateTime;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getRegistrationNumber() {
		return registrationNumber;
	}
	public void setRegistrationNumber(int registrationNumber) {
		this.registrationNumber = registrationNumber;
	}
	public Date getRegistrationTime() {
		return registrationTime;
	}
	public void setRegistrationTime(Date registrationTime) {
		this.registrationTime = registrationTime;
	}
	public String getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}
	public String getServiceDepartmentId() {
		return serviceDepartmentId;
	}
	public void setServiceDepartmentId(String serviceDepartmentId) {
		this.serviceDepartmentId = serviceDepartmentId;
	}
	public String getPatientCardNumber() {
		return patientCardNumber;
	}
	public void setPatientCardNumber(String patientCardNumber) {
		this.patientCardNumber = patientCardNumber;
	}
	
	
	
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	
	
	@Override
	public String toString() {
		return "PatientRegistration [id=" + id + ", registrationNumber="
				+ registrationNumber + ", registrationTime=" + registrationTime
				+ ", doctorId=" + doctorId + ", serviceDepartmentId="
				+ serviceDepartmentId + ", patientCardNumber="
				+ patientCardNumber + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((doctorId == null) ? 0 : doctorId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime
				* result
				+ ((patientCardNumber == null) ? 0 : patientCardNumber
						.hashCode());
		result = prime * result + registrationNumber;
		result = prime
				* result
				+ ((registrationTime == null) ? 0 : registrationTime.hashCode());
		result = prime
				* result
				+ ((serviceDepartmentId == null) ? 0 : serviceDepartmentId
						.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PatientRegistration other = (PatientRegistration) obj;
		if (doctorId == null) {
			if (other.doctorId != null)
				return false;
		} else if (!doctorId.equals(other.doctorId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (patientCardNumber == null) {
			if (other.patientCardNumber != null)
				return false;
		} else if (!patientCardNumber.equals(other.patientCardNumber))
			return false;
		if (registrationNumber != other.registrationNumber)
			return false;
		if (registrationTime == null) {
			if (other.registrationTime != null)
				return false;
		} else if (!registrationTime.equals(other.registrationTime))
			return false;
		if (serviceDepartmentId == null) {
			if (other.serviceDepartmentId != null)
				return false;
		} else if (!serviceDepartmentId.equals(other.serviceDepartmentId))
			return false;
		return true;
	}
	
	
	
	public PatienRegistrationVO toVo(){
		PatienRegistrationVO destObj = new PatienRegistrationVO();
		destObj.setOriginalId(this.getId());
		destObj.setCreateTime(this.getCreateTime());
		destObj.setDoctorId(this.getDoctorId());
		destObj.setPatientCardNumber(this.getPatientCardNumber());
		destObj.setRegistrationNumber(this.getRegistrationNumber());
		destObj.setRegistrationTime(this.getRegistrationTime());
		destObj.setServicedepartmentId(this.getServiceDepartmentId());
		destObj.setUpdateTime(this.getUpdateTime());
		
		return destObj;
	}
	
	
	
}
