package com.zmap.app.controller.clinics;

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
import com.zmap.common.DateUtil;
import com.zmap.zmap.clinic.mapper.ClinicsInfusionPersonMapper;
import com.zmap.zmap.clinic.model.ClinicsInfusionPersonModel;
import com.zmap.zmap.framework.base.mapper.ZmapRDeptMapper;
import com.zmap.zmap.framework.base.mapper.ZmapRDoctorMapper;
import com.zmap.zmap.framework.base.model.ZmapRDeptModel;
import com.zmap.zmap.framework.base.model.ZmapRDoctorModel;
import com.zmap.zmap.framework.model.NameValueEntity;

@RestController
@RequestMapping("/api/service/clinicsInfusion")
public class ClinicsInfusionPersonController {

	@Autowired
	private ClinicsInfusionPersonMapper clinicsInfusionPersonMapper;
	@Autowired
	private ZmapRDeptMapper zmapRDeptMapper;
	@Autowired
	private ZmapRDoctorMapper zmapRDoctorSyncMapper;
	
	@RequestMapping("/clinicsInfusionPersonBySum")
	@ResponseBody
	public JSONPObject clinicsInfusionPersonBySum(HttpServletRequest request,HttpServletResponse response) {
		String callbackFunName = request.getParameter("callbackparams");
		String sdate = request.getParameter("sdate");
		String startTime = sdate + " " + "00:00:00";
		String endTime = sdate + " " +"23:59:59";
		HashMap params = new HashMap ();
		params.put("startTime", startTime);
		params.put("endTime", endTime);
		List<ClinicsInfusionPersonModel> result = clinicsInfusionPersonMapper.selectPersonTimeByDateSum(params);
		double sum = 0;
		for (ClinicsInfusionPersonModel model : result) {
			sum = Double.valueOf(model.getPersonCount());
		}
		JSONObject json = new JSONObject();
		json.put("sum",sum);
		System.out.println(json.toJSONString());
		return new JSONPObject(callbackFunName, json);
	}
	
	@RequestMapping("/clinicsInfusionPersonByHour")
	@ResponseBody
	public JSONPObject clinicsInfusionPersonByHour(HttpServletRequest request,HttpServletResponse response) {
		String callbackFunName = request.getParameter("callbackparamch");
		String sdate = request.getParameter("sdate");
		HashMap paramh = new HashMap();
		int step = 2;
		List hours = DateUtil.getHoursByRule(2, 0, 24);
		int len = hours.size();

		List<Double> valueData = new ArrayList<Double>();
		for (int i = 0; i < len; i++) {
			int startHour = (Integer) hours.get(i);
			int endHour = startHour + step - 1;
			String startTime = sdate + " " + DateUtil.parseHourToString(startHour)+":00:00";
			String endTime = sdate + " " + DateUtil.parseHourToString(endHour)+":59:59";
			paramh.put("startTime", startTime);
			paramh.put("endTime", endTime);
			List<ClinicsInfusionPersonModel> result = clinicsInfusionPersonMapper.selectPersonTimeByDatefromClinics(paramh);
			for (ClinicsInfusionPersonModel model : result) {
				double count = Double.valueOf(model.getPersonCount());
				valueData.add(count);
			}
		}
		JSONObject json = new JSONObject();
		json.put("value", valueData);
		System.out.println(json.toJSONString());
		return new JSONPObject(callbackFunName, json);
	}
	
	
	@RequestMapping("/emergencyInfusionPersonByHour")
	@ResponseBody
	public JSONPObject emergencyInfusionPersonByHour(HttpServletRequest request,HttpServletResponse response) {
		String callbackFunName = request.getParameter("callbackparameh");
		String sdate = request.getParameter("sdate");
		HashMap paramh = new HashMap();
		int step = 2;
		List hours = DateUtil.getHoursByRule(2, 0, 24);
		int len = hours.size();
		List<Double> valueData = new ArrayList<Double>();
		for (int i = 0; i < len; i++) {
			int startHour = (Integer) hours.get(i);
			int endHour = startHour + step - 1;
			String startTime = sdate + " " + DateUtil.parseHourToString(startHour)+":00:00";
			String endTime = sdate + " " + DateUtil.parseHourToString(endHour)+":59:59";
			paramh.put("startTime", startTime);
			paramh.put("endTime", endTime);
			List<ClinicsInfusionPersonModel> result = clinicsInfusionPersonMapper.selectPersonTimeByDateFromEmergency(paramh);
			for (ClinicsInfusionPersonModel model : result) {
				double count = Double.valueOf(model.getPersonCount());
				valueData.add(count);
			}
		}
		JSONObject json = new JSONObject();
		json.put("value", valueData);
		System.out.println(json.toJSONString());
		return new JSONPObject(callbackFunName, json);
	}
	
	
	@RequestMapping("/InfusionPersonByDept")
	@ResponseBody
	public JSONPObject InfusionPersonByDept(HttpServletRequest request , HttpServletResponse response) {
		String callbackFunName = request.getParameter("callbackparamd");
		String sdate = request.getParameter("sdate");
		String hospitalCode = request.getParameter("hospitalCode");
		String startTime = sdate + " 00:00:00";
		String endTime = sdate + " 23:59:59";
		HashMap paramd = new HashMap();
		paramd.put("startTime", startTime);
		paramd.put("endTime", endTime);
		paramd.put("hospitalCode", hospitalCode);
		List<ClinicsInfusionPersonModel> result = clinicsInfusionPersonMapper.selectPersonTimeByDateFromDept(paramd);
		HashMap<String, Double> kvs = new HashMap<String, Double>();
		List<String> codes = new ArrayList<String>();
		for (ClinicsInfusionPersonModel model : result) {
			String deptCode = model.getDeptCode();
			codes.add(deptCode);
			double count = Long.valueOf((long) model.getPersonCount());
			kvs.put(deptCode, count);
		}
		paramd.put("codes", codes);
		List<ZmapRDeptModel> depts = zmapRDeptMapper.selectDetpsByDeptCodes(paramd);
		List<String> nameData = new ArrayList<String>();
		List<String> codeData = new ArrayList<String>();
		List<NameValueEntity> valueData = new ArrayList<NameValueEntity>();
		for (ZmapRDeptModel model : depts) {
			String deptCode = model.getDeptCode();
			String deptName = model.getDeptName();
			NameValueEntity entity = new NameValueEntity();
			double count = kvs.get(deptCode);
			nameData.add(deptName);
			codeData.add(deptCode);
			entity.setName(deptName);
			entity.setValue((long)count);
			valueData.add(entity);	
		}
		JSONObject json = new JSONObject();
		json.put("deptCodes", codeData);
		json.put("name", nameData);
		json.put("value", valueData);
		System.out.println(json.toJSONString());
		return new JSONPObject(callbackFunName, json);
	}
	
	
	@RequestMapping("/InfusionPersonByDoctor")
	@ResponseBody
	public JSONPObject InfusionPersonByDoctor(HttpServletRequest request , HttpServletResponse response) {
		String callbackFunName = request.getParameter("callbackparamdd");
		String sdate = request.getParameter("sdate");
		String deptCode = request.getParameter("deptcode");
		String hospitalCode = request.getParameter("hospitalCode");
		String startTime = sdate + " 00:00:00";
		String endTime = sdate + " 23:59:59";
		HashMap paramdd = new HashMap();
		paramdd.put("startTime", startTime);
		paramdd.put("endTime", endTime);
		paramdd.put("deptCode", deptCode);
		paramdd.put("hospitalCode", hospitalCode);
		List<ClinicsInfusionPersonModel> result = clinicsInfusionPersonMapper.selectPersonTimeByDateFromDoctor(paramdd);
		HashMap<String, Double> kvs = new HashMap<String, Double>();
		List<String> codes = new ArrayList<String>();
		for (ClinicsInfusionPersonModel model : result) {
			String doctorCode = model.getDoctorCode();
			codes.add(doctorCode);
			double count = Double.valueOf(model.getPersonCount());
			kvs.put(doctorCode, count);
		}
		paramdd.put("codes", codes);
		List<ZmapRDoctorModel> doctors = zmapRDoctorSyncMapper.selectDoctorsByDoctorCodes(paramdd);
		List<String> nameData = new ArrayList<String>();
		List<Double> valueData = new ArrayList<Double>();
		for (ZmapRDoctorModel deptModel : doctors) {
			String doctorCode = deptModel.getDoctorCode();
			String doctorName = deptModel.getDoctorName();
			nameData.add(doctorName);
			Double count = kvs.get(doctorCode);
			System.out.println(count);
			valueData.add(count);
		}
		JSONObject json = new JSONObject();
		json.put("namedata", nameData);
		json.put("valuedata", valueData);
		System.out.println(json.toJSONString());
		return new JSONPObject(callbackFunName, json);
	}
	

}
