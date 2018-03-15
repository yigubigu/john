package com.zmap.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zmap.zmap.clinic.mapper.PatientRegistrationMapper;
import com.zmap.zmap.clinic.model.PatientRegistration;

@RestController
@RequestMapping("/api/patient/registrations")
public class PatientRegistrationController {

	@Autowired
	private PatientRegistrationMapper patientRegistrationMapper;
	
	

	@RequestMapping(method=RequestMethod.POST)
	ResponseEntity<?> add(@RequestBody PatientRegistration input) {
	
		/*
		 * curl -H "Content-Type: application/json" -X POST  -d '{"originalId":"111", "registrationNumber":111, "doctorId":"docker li", "servicedepartmentId":"xin zang ke", "patientCardNumber":"103432"}' http://localhost:8080/api/patient/registrations
		 */
		int id = this.patientRegistrationMapper.insert(input);
		return null;
		
	}
}
