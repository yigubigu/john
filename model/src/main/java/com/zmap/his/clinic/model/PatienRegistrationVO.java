package com.zmap.his.clinic.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PatienRegistrationVO {

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column patientregistration.id
	 *
	 * @mbg.generated
	 */
	private Integer id;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column patientregistration.original_id
	 *
	 * @mbg.generated
	 */
	private String originalId;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column patientregistration.registration_number
	 *
	 * @mbg.generated
	 */
	private Integer registrationNumber;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column patientregistration.registration_time
	 *
	 * @mbg.generated
	 */
	private Date registrationTime;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column patientregistration.doctor_id
	 *
	 * @mbg.generated
	 */
	private String doctorId;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column patientregistration.serviceDepartment_id
	 *
	 * @mbg.generated
	 */
	private String servicedepartmentId;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column patientregistration.patient_card_number
	 *
	 * @mbg.generated
	 */
	private String patientCardNumber;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column patientregistration.create_time
	 *
	 * @mbg.generated
	 */
	private Date createTime;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column patientregistration.update_time
	 *
	 * @mbg.generated
	 */
	private Date updateTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOriginalId() {
		return originalId;
	}

	public void setOriginalId(String originalId) {
		this.originalId = originalId;
	}

	public Integer getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(Integer registrationNumber) {
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

	public String getServicedepartmentId() {
		return servicedepartmentId;
	}

	public void setServicedepartmentId(String servicedepartmentId) {
		this.servicedepartmentId = servicedepartmentId;
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

}
