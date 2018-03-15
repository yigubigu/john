package com.zmap.app.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.zmap.zmap.framework.base.mapper.Patient360Mapper;
import com.zmap.zmap.framework.base.model.Patient360Model;
import com.zmap.zmap.framework.income.mapper.OtherIncomeMapper;
import com.zmap.zmap.framework.income.model.OtherIncomeModel;

@RestController
@RequestMapping("/api/patient360")
public class Patient360Controller {
	
	@Autowired
	private Patient360Mapper patient360Mapper;
	
	@Autowired 
	private OtherIncomeMapper otherIncomeMapper;
	
	/**
	 * 搜索患者
	 */
	@RequestMapping("/searchForPatient")
	@ResponseBody
	public JSONPObject searchForPatient(HttpServletRequest request, HttpServletResponse response){
		String callbackFullName=request.getParameter("callbackparams");
		String name=request.getParameter("name");
		String birthday=request.getParameter("birthday");
		String sex=request.getParameter("sex");
		String marriage=request.getParameter("marriage");
		String age=request.getParameter("age");
		String citizenship=request.getParameter("citizenship");//国籍
		String nation=request.getParameter("nation");//民族
		String addr=request.getParameter("addr");
		String primary=request.getParameter("primary");
		String hospitalCode=request.getParameter("hospitalCode");
		HashMap<String, String> params=new HashMap<String, String>();
		params.put("name", name);
		params.put("birthday", birthday);
		params.put("sex", sex);
		params.put("marriage", marriage);
		params.put("age", age);
		params.put("citizenship", citizenship);
		params.put("nation", nation);
		params.put("addr", addr);
		params.put("primary", primary);
		params.put("hospitalCode", hospitalCode);
		List<Patient360Model> patient360Model=patient360Mapper.searchForPatients(params);
		List<String> nameData=new ArrayList<String>();
		List<String> primarytData=new ArrayList<String>();
		List<String> sexData=new ArrayList<String>();
		List<String> birthdayData=new ArrayList<String>();
		List<String> timesData=new ArrayList<String>();
		List<String> regtimeData=new ArrayList<String>();
		List<String> typeData=new ArrayList<String>();
		List<String> deptData=new ArrayList<String>();
		for (Patient360Model model : patient360Model) {
			String primaryIndex=model.getPatientCode();
			String patientName=model.getPatientName();
			String patientSex=model.getSex();
			String patientBirthday=model.getBirthday();
			String count=model.getCount();
			primarytData.add(primaryIndex);
			nameData.add(patientName);
			sexData.add(patientSex);
			birthdayData.add(patientBirthday);
			timesData.add(count);
     		params.put("patientCode", primaryIndex);
			List<Patient360Model> patient360Model3=patient360Mapper.viewDetails(params);
			for (Patient360Model model3 : patient360Model3) {
				String regTime=model3.getRegTime();
				String clinicType=model3.getClinicType();
				String deptCode=model3.getDeptCode();
				params.put("deptCode", deptCode);
				List<OtherIncomeModel> deptName=otherIncomeMapper.selectDeptName(params);
				for (OtherIncomeModel otherIncomeModel : deptName) {
					String deptname=otherIncomeModel.getDeptName();
					deptData.add(deptname);
				}
				if(clinicType.equals("1")){
					clinicType="门诊";
				}else if(clinicType.equals("2")){
					clinicType="急诊";
				}
				regtimeData.add(regTime);
				typeData.add(clinicType);
				
			}
		}
		JSONObject json=new JSONObject();
		json.put("name", nameData);
		json.put("primary",primarytData);
		json.put("sex", sexData);
		json.put("birthday", birthdayData);
		json.put("times", timesData);
		json.put("regtime", regtimeData);
		json.put("type", typeData);
		json.put("dept", deptData);
		System.out.println(json.toJSONString());
		return new JSONPObject(callbackFullName,json);
	}
		
}
