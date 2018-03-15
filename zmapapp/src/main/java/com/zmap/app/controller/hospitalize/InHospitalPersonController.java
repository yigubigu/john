package com.zmap.app.controller.hospitalize;

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
import com.zmap.zmap.hospitalize.mapper.InHospitalPersonMapper;
import com.zmap.zmap.hospitalize.model.InHospitalPersonModel;




@RestController
@RequestMapping("/api/service/inHospital")
public class InHospitalPersonController {
	
	@Autowired
	private InHospitalPersonMapper inHospitalPersonMapper;
	private double sum;
	@RequestMapping("/countInHospitalPersonByDateSum")
	@ResponseBody
	public JSONPObject countInHospitalPersonByDate(HttpServletRequest request , HttpServletResponse response) {
		String callbackFunName = request.getParameter("callbackparams");
		String sdate = request.getParameter("sdate");
		String hospital = request.getParameter("hospitalCode");
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("curDate", sdate);
		params.put("hospitalCode", hospital);
		List<InHospitalPersonModel> people = inHospitalPersonMapper.selectInHospitalPersonByDateSum(params); 
		
		for (InHospitalPersonModel model : people) {
			double count = Long.valueOf((long) model.getInPeopleCount());
			sum = count;
		}
		
		JSONObject json = new JSONObject();
		json.put("sum", sum);
		System.out.println(json.toJSONString());
		return new JSONPObject(callbackFunName, json);
		
	}
	
	
	
	@RequestMapping("/countInHospitalPersonByDept")
	@ResponseBody
	public JSONPObject countInHospitalPersonByDept(HttpServletRequest request , HttpServletResponse response) {
		String callbackFunName = request.getParameter("callbackparamd");
		String sdate = request.getParameter("sdate");
		String hospital = request.getParameter("hospitalCode");
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("curDate", sdate);
		params.put("hospitalCode", hospital);
		List<InHospitalPersonModel> deptList = inHospitalPersonMapper.selectInHospitalPersonByDept(params); 
		List<String> deptCode = new ArrayList<String>();
		List<String> nameData = new ArrayList<String>();
		List<Double> valueData = new ArrayList<Double>();
		for (InHospitalPersonModel model : deptList) {
			float count = Long.valueOf((long) model.getInPeopleCount());
			valueData.add((double) count);
			String code = model.getDeptCode();
			deptCode.add(code);
			String name = model.getDeptName();
			nameData.add(name);
		}	
		JSONObject json = new JSONObject();
		json.put("deptcode", deptCode);
		json.put("name", nameData);
		json.put("value", valueData);
		System.out.println(json.toJSONString());
		return new JSONPObject(callbackFunName, json);
		
	}
	
	
	@RequestMapping("/countInHospitalPersonByDoctor")
	@ResponseBody
	public JSONPObject countInHospitalPersonByDoctor(HttpServletRequest request , HttpServletResponse response) {
		String callbackFunName = request.getParameter("callbackparamdd");
		String sdate = request.getParameter("sdate");
		String hospital = request.getParameter("hospitalCode");
		String deptcode = request.getParameter("deptcode");
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("curDate", sdate);
		params.put("hospitalCode", hospital);
		params.put("deptCode", deptcode);
		List<InHospitalPersonModel> deptList = inHospitalPersonMapper.selectInHospitalPersonByDoctor(params); 
		List<String> doctorCode = new ArrayList<String>();
		List<String> nameData = new ArrayList<String>();
		List<Double> valueData = new ArrayList<Double>();
		for (InHospitalPersonModel model : deptList) {
			float count = Long.valueOf((long) model.getInPeopleDoctorCount());
			valueData.add((double) count);
			String ddcode = model.getDoctorCode();
			doctorCode.add(ddcode);
			String name = model.getDoctorName();
			nameData.add(name);
		}	
		JSONObject json = new JSONObject();
		json.put("doctorCode", doctorCode);
		json.put("name", nameData);
		json.put("value", valueData);
		System.out.println(json.toJSONString());
		return new JSONPObject(callbackFunName, json);
		
	}
	
}
