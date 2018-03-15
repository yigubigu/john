package com.zmap.his.clinic.model;

import java.io.Serializable;
import java.util.UUID;

public class HisClinicCM1SyncModel implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String regCode;
	private String regTime;
	private String patientCode;
	private String doctorCode;
	private String deptCode;
	private String zmapDeptCode;
	private String orgCode;
	private String iccardCode;
	private String clinicType;
	private String hospitalCode;
	private String subHospitalCode;
	private String isTrans;
	private String transRegType;
	private String transDeptCode;
	private String transDoctorCode;
	private String transStatus;
	
	
	public String getIsTrans() {
		return isTrans;
	}

	public void setIsTrans(String isTrans) {
		this.isTrans = isTrans;
	}

	public String getTransRegType() {
		return transRegType;
	}

	public void setTransRegType(String transRegType) {
		this.transRegType = transRegType;
	}

	public String getTransDeptCode() {
		return transDeptCode;
	}

	public void setTransDeptCode(String transDeptCode) {
		this.transDeptCode = transDeptCode;
	}

	public String getTransDoctorCode() {
		return transDoctorCode;
	}

	public void setTransDoctorCode(String transDoctorCode) {
		this.transDoctorCode = transDoctorCode;
	}

	public String getTransStatus() {
		return transStatus;
	}

	public void setTransStatus(String transStatus) {
		this.transStatus = transStatus;
	}

	public String getRegCode() {
		return regCode;
	}

	public void setRegCode(String regCode) {
		this.regCode = regCode;
	}

	public String getRegTime() {
		return regTime;
	}

	public void setRegTime(String regTime) {
		this.regTime = regTime;
	}

	public String getPatientCode() {
		return patientCode;
	}

	public void setPatientCode(String patientCode) {
		this.patientCode = patientCode;
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

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getIccardCode() {
		return iccardCode;
	}

	public void setIccardCode(String iccardCode) {
		this.iccardCode = iccardCode;
	}

	public String getClinicType() {
		return clinicType;
	}

	public void setClinicType(String clinicType) {
		this.clinicType = clinicType;
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

	@Override
	public String toString() {
		return "HisClinicCM1SyncModel [reg_code=" + regCode + ", reg_time=" + regTime 
				+ ", patient_code=" + patientCode + ", doctor_code=" + doctorCode 
				+ ", dept_code=" + deptCode + ", zmap_dept_code=" + zmapDeptCode 
				+ ", org_code=" + orgCode + ", iccard_code=" + iccardCode 
				+ ", clinic_type=" + clinicType + ", hospital_code=" + hospitalCode 
				+ ", sub_hospital_code=" + subHospitalCode + "]"; 
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		
		result = prime * result
				+ ((regCode == null) ? 0 : regCode.hashCode());
		result = prime * result
				+ ((regTime == null) ? 0 : regTime.hashCode());
		result = prime * result
				+ ((patientCode == null) ? 0 : patientCode.hashCode());
		result = prime * result
				+ ((doctorCode == null) ? 0 : doctorCode.hashCode());
		result = prime * result
				+ ((deptCode == null) ? 0 : deptCode.hashCode());
		result = prime * result
				+ ((zmapDeptCode == null) ? 0 : zmapDeptCode.hashCode());
		result = prime * result
				+ ((orgCode == null) ? 0 : orgCode.hashCode());
		result = prime * result
				+ ((iccardCode == null) ? 0 : iccardCode.hashCode());
		result = prime * result
				+ ((clinicType == null) ? 0 : clinicType.hashCode());
		result = prime * result
				+ ((hospitalCode == null) ? 0 : hospitalCode.hashCode());
		result = prime * result
				+ ((subHospitalCode == null) ? 0 : subHospitalCode.hashCode());
		
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
		HisClinicCM1SyncModel other = (HisClinicCM1SyncModel) obj;
		if (regCode == null) {
			if (other.regCode != null)
				return false;
		} else if (!regCode.equals(other.regCode))
			return false;
		if (regTime == null) {
			if (other.regTime != null)
				return false;
		} else if (!regTime.equals(other.regTime))
			return false;
		if (patientCode == null) {
			if (other.patientCode != null)
				return false;
		} else if (!patientCode.equals(other.patientCode))
			return false;
		if (patientCode == null) {
			if (other.patientCode != null)
				return false;
		} else if (!patientCode.equals(other.patientCode))
			return false;
		if (doctorCode == null) {
			if (other.doctorCode != null)
				return false;
		} else if (!doctorCode.equals(other.doctorCode))
			return false;
		if (deptCode == null) {
			if (other.deptCode != null)
				return false;
		} else if (!deptCode.equals(other.deptCode))
			return false;
		if (zmapDeptCode == null) {
			if (other.zmapDeptCode != null)
				return false;
		} else if (!zmapDeptCode.equals(other.zmapDeptCode))
			return false;
		if (orgCode == null) {
			if (other.orgCode != null)
				return false;
		} else if (!orgCode.equals(other.orgCode))
			return false;
		if (iccardCode == null) {
			if (other.iccardCode != null)
				return false;
		} else if (!iccardCode.equals(other.iccardCode))
			return false;
		if (clinicType == null) {
			if (other.clinicType != null)
				return false;
		} else if (!clinicType.equals(other.clinicType))
			return false;
		if (hospitalCode == null) {
			if (other.hospitalCode != null)
				return false;
		} else if (!hospitalCode.equals(other.hospitalCode))
			return false;
		if (subHospitalCode == null) {
			if (other.subHospitalCode != null)
				return false;
		} else if (!subHospitalCode.equals(other.subHospitalCode))
			return false;
		
		return true;
	}

	/**
	 * 对应model层VO
	 * @return
	 */
	public ZmapClinicCM1SyncVO toVo(){
		ZmapClinicCM1SyncVO destObj = new ZmapClinicCM1SyncVO();
		destObj.setClinicId(UUID.randomUUID().toString());
		destObj.setDeptCode(this.getDeptCode());
		destObj.setDoctorCode(this.getDoctorCode());
		destObj.setIccardCode(this.getIccardCode());
		destObj.setOrgCode(this.getOrgCode());
		destObj.setPatientCode(this.getPatientCode());
		destObj.setRegCode(this.getRegCode());
		destObj.setRegTime(this.getRegTime());
		destObj.setZmapDeptCode(this.getZmapDeptCode());
		destObj.setClinicType(this.getClinicType());
		destObj.setHospitalCode(this.getHospitalCode());
		destObj.setSubHospitalCode(this.getSubHospitalCode());
		destObj.setIsTrans(this.getIsTrans());
		destObj.setTransDeptCode(this.getTransDeptCode());
		destObj.setTransDoctorCode(this.getTransDoctorCode());
		destObj.setTransRegType(this.getTransRegType());
		destObj.setTransStatus(this.getTransStatus());
		
		return destObj;
	}
	
}
