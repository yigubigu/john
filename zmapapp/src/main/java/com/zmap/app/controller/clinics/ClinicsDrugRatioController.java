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
import com.zmap.zmap.framework.base.mapper.ZmapRDeptMapper;
import com.zmap.zmap.framework.base.model.ZmapRDeptModel;
import com.zmap.zmap.framework.income.mapper.ClinicsIncomeMapper;
import com.zmap.zmap.framework.income.model.ClinicsIncomeModel;
import com.zmap.zmap.clinic.mapper.ClinicsDrugRatioMapper;
import com.zmap.zmap.clinic.model.ClinicsDrugRatioModel;
import com.zmap.zmap.framework.base.mapper.ZmapRDoctorMapper;
import com.zmap.zmap.framework.base.model.ZmapRDoctorModel;

@RestController
@RequestMapping("/api/service/clinicsDrugRatio")
public class ClinicsDrugRatioController {
	@Autowired
	private ClinicsDrugRatioMapper clinicsDrugRatioMapper;
	@Autowired
	private ClinicsIncomeMapper clinicsIncomeMapper;
	@Autowired
	private ZmapRDeptMapper zmapRDeptMapper;
	@Autowired
	private ZmapRDoctorMapper zmapRDoctorSyncMapper;
	private float clinicsDrugFeeCount;
	private float clinicsIncomeFeeCount ;
	
	
	//当日药品收入=当日门急诊药品收入+当日住院药品收入
	@RequestMapping("/clinicsDrugRatioByDate")
	@ResponseBody
	public JSONPObject clinicsDrugRatioByDate(HttpServletRequest request,HttpServletResponse response) {
		String callbackFunName = request.getParameter("callbackparams");
		String hospitalCode = request.getParameter("hospitalCode");
		String curdate = request.getParameter("sdate");
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("hospitalCode", hospitalCode);
		params.put("curDate",curdate);
		List<ClinicsDrugRatioModel> result1 = clinicsDrugRatioMapper.selectClinicsDrugIncomeByDate(params);
		List<ClinicsIncomeModel> result2 = clinicsIncomeMapper.selectDayClinicsIncomeSum(params);
		for (ClinicsDrugRatioModel model1 : result1) {
			
			clinicsDrugFeeCount = Float.valueOf(model1.getClinicsDrugIncome());
		}
		
		for (ClinicsIncomeModel model2 : result2) {
			clinicsIncomeFeeCount  = Float.valueOf(model2.getFeeCount());
		}
		float clinicsDrugRatio = (clinicsDrugFeeCount / clinicsIncomeFeeCount) * 100;
		
		JSONObject json = new JSONObject();
		json.put("clinicsDrugRatio", clinicsDrugRatio);
		System.out.println(json.toJSONString());
		return new JSONPObject(callbackFunName , json);
	}
	
	@RequestMapping("/clinicsDrugRatioByDept")
	@ResponseBody
	public JSONPObject clinicsDrugRatioByDept(HttpServletRequest request,HttpServletResponse response){
		String callbackFunName = request.getParameter("callbackparamd");
		String hospitalCode = request.getParameter("hospitalCode");
		String curdate = request.getParameter("sdate");
		HashMap paramd = new HashMap();
		paramd.put("hospitalCode", hospitalCode);
		paramd.put("curDate", curdate);
		List<ClinicsDrugRatioModel> result = clinicsDrugRatioMapper.selectClinicsDrugIncomeByDept(paramd);
		List<ClinicsIncomeModel> result2 = clinicsIncomeMapper.selectDayClinicsIncomeSum(paramd);
		List<String> codes = new ArrayList<String>();
		HashMap<String, Float> kvs= new HashMap<String, Float>();
		
		for (ClinicsIncomeModel model2 : result2) {
			clinicsIncomeFeeCount  = Float.valueOf(model2.getFeeCount());
		}
		for (ClinicsDrugRatioModel model : result) {
			String deptCode = model.getDeptCode();
			codes.add(deptCode);
			float deptDrugIncome = Float.valueOf(model.getDeptDrugIncome());
			float deptDrugRatio = (deptDrugIncome / clinicsIncomeFeeCount) * 100;
			kvs.put(deptCode, deptDrugRatio);
		}
		paramd.put("codes",codes);
		List<ZmapRDeptModel> depts = zmapRDeptMapper.selectDetpsByDeptCodes(paramd);
		List<String> codeData = new ArrayList<String>();
		List<String> nameData = new ArrayList<String>();
		List<Float> valueData = new ArrayList<Float>();
		for (ZmapRDeptModel model : depts) {
			String deptCode = model.getDeptCode();
			String deptName = model.getDeptName();
			float ratio = kvs.get(deptCode);
			
			codeData.add(deptCode);
			nameData.add(deptName);
			valueData.add(ratio);
		}
		JSONObject json = new JSONObject();
		json.put("deptcode", codeData);
		json.put("name", nameData);
		json.put("value", valueData);
		System.out.println(json.toJSONString());
		return new JSONPObject(callbackFunName, json);		
	}
	
	@RequestMapping("clinicsDrugRatioByDoctor")
	@ResponseBody
	public JSONPObject clinicsDrugRatioByDoctor(HttpServletRequest request,HttpServletResponse response) {
		String callbackFunName = request.getParameter("callbackparamdd");
		String hospitalCode = request.getParameter("hospitalCode");
		String deptCode = request.getParameter("deptcode");
		String curdate = request.getParameter("sdate");
		
		HashMap paramdd = new HashMap();
		paramdd.put("hospitalCode", hospitalCode);
		paramdd.put("curDate", curdate);
		paramdd.put("deptCode",deptCode);
		
		List<ClinicsDrugRatioModel> result = clinicsDrugRatioMapper.selectClinicsDrugIncomeByDoctor(paramdd);
		List<ClinicsIncomeModel> result2 = clinicsIncomeMapper.selectDayClinicsIncomeSum(paramdd);
		List<String> codes = new ArrayList<String>();
		HashMap<String, Float> kvs = new HashMap<String, Float>();
		
		for (ClinicsIncomeModel model2 : result2) {
			clinicsIncomeFeeCount  = Float.valueOf(model2.getFeeCount());
		}
		
		for (ClinicsDrugRatioModel model : result) {
			String doctorCode = model.getDoctorCode();
			codes.add(doctorCode);
			float doctorDrugIncome = Float.valueOf(model.getDoctorDrugIncome());
			float doctorDrugRatio = (doctorDrugIncome / clinicsIncomeFeeCount) * 100;
			kvs.put(doctorCode, doctorDrugRatio);
		}
		
		paramdd.put("codes", codes);
		List<ZmapRDoctorModel> doctors = zmapRDoctorSyncMapper.selectDoctorsByDoctorCodes(paramdd);
		List<String> codeData = new ArrayList<String>();
		List<String> nameData = new ArrayList<String>();
		List<Float> valueData = new ArrayList<Float>();
 		for (ZmapRDoctorModel model : doctors) {
			String doctorCode = model.getDoctorCode();
			String doctorName = model.getDoctorName();
			float ratio = kvs.get(doctorCode);
			codeData.add(doctorCode);
			nameData.add(doctorName);
			valueData.add(ratio);
		}
 		
 		JSONObject json = new JSONObject();
 		json.put("doctorcode", codeData);
 		json.put("name", nameData);
 		json.put("value",valueData);
		System.out.println(json.toJSONString());
		return new JSONPObject(callbackFunName, json);	
	}

}
