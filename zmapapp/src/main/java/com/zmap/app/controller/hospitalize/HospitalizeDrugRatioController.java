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
import com.zmap.zmap.framework.base.mapper.ZmapRDeptMapper;
import com.zmap.zmap.framework.base.mapper.ZmapRDoctorMapper;
import com.zmap.zmap.framework.base.model.ZmapRDeptModel;
import com.zmap.zmap.framework.base.model.ZmapRDoctorModel;
import com.zmap.zmap.framework.income.mapper.HospitalizeIncomeMapper;
import com.zmap.zmap.framework.income.model.HospitalizeIncomeModel;
import com.zmap.zmap.hospitalize.mapper.HospitalizeDrugRatioMapper;
import com.zmap.zmap.hospitalize.model.HospitalizeDrugRatioModel;

@RestController
@RequestMapping("/api/service/hospitalizeDrugRatio")
public class HospitalizeDrugRatioController {
	@Autowired
	private HospitalizeDrugRatioMapper hospitalizeDrugRatioMapper;
	@Autowired
	private HospitalizeIncomeMapper hospitalizeIncomeMapper;
	@Autowired
	private ZmapRDeptMapper zmapRDeptMapper;
	@Autowired
	private ZmapRDoctorMapper zmapRDoctorSyncMapper;
	private float hospitalizeDrugFeeCount;
	private float hospitalizeIncomeFeeCount ;
	
	@RequestMapping("/hospitalizeDrugRatioByDate")
	@ResponseBody
	public JSONPObject hospitalizeDrugRatioByDate(HttpServletRequest request,HttpServletResponse response) {
		String callbackFunName = request.getParameter("callbackparams");
		String hospitalCode = request.getParameter("hospitalCode");
		String curdate = request.getParameter("sdate");
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("hospitalCode", hospitalCode);
		params.put("curDate",curdate);
		List<HospitalizeDrugRatioModel> result1 = hospitalizeDrugRatioMapper.selectHospitalizeDrugIncomeByDate(params);
		List<HospitalizeIncomeModel> result2 = hospitalizeIncomeMapper.selectDayHospitalIncomeSum(params);
		for (HospitalizeDrugRatioModel model1 : result1) {
			
			hospitalizeDrugFeeCount = Float.valueOf(model1.getHospitalizeDrugIncome());
		}
		
		for (HospitalizeIncomeModel model2 : result2) {
			hospitalizeIncomeFeeCount  = Float.valueOf(model2.getFeeCount());
		}
		float hospitalizeDrugRatio = (hospitalizeDrugFeeCount / hospitalizeIncomeFeeCount) * 100;
		
		JSONObject json = new JSONObject();
		json.put("hospitalizeDrugRatio", hospitalizeDrugRatio);
		System.out.println(json.toJSONString());
		return new JSONPObject(callbackFunName , json);
	}
	
	
	@RequestMapping("/hospitalizeDrugRatioByDept")
	@ResponseBody
	public JSONPObject hospitalizeDrugRatioByDept(HttpServletRequest request,HttpServletResponse response){
		String callbackFunName = request.getParameter("callbackparamd");
		String hospitalCode = request.getParameter("hospitalCode");
		String curdate = request.getParameter("sdate");
		HashMap paramd = new HashMap();
		paramd.put("hospitalCode", hospitalCode);
		paramd.put("curDate", curdate);
		List<HospitalizeDrugRatioModel> result = hospitalizeDrugRatioMapper.selectHospitalizeDrugIncomeByDept(paramd);
		List<HospitalizeIncomeModel> result2 = hospitalizeIncomeMapper.selectDayHospitalIncomeSum(paramd);
		List<String> codes = new ArrayList<String>();
		HashMap<String, Float> kvs= new HashMap<String, Float>();
		
		for (HospitalizeIncomeModel model2 : result2) {
			hospitalizeIncomeFeeCount  = Float.valueOf(model2.getFeeCount());
		}
		for (HospitalizeDrugRatioModel model : result) {
			String deptCode = model.getDeptCode();
			codes.add(deptCode);
			float deptDrugIncome = Float.valueOf(model.getDeptDrugIncome());
			float deptDrugRatio = (deptDrugIncome / hospitalizeIncomeFeeCount) * 100;
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
	
	
	@RequestMapping("/hospitalizeDrugRatioByDoctor")
	@ResponseBody
	public JSONPObject hospitalizeDrugRatioByDoctor(HttpServletRequest request,HttpServletResponse response) {
		String callbackFunName = request.getParameter("callbackparamdd");
		String hospitalCode = request.getParameter("hospitalCode");
		String deptCode = request.getParameter("deptcode");
		String curdate = request.getParameter("sdate");
		
		HashMap paramdd = new HashMap();
		paramdd.put("hospitalCode", hospitalCode);
		paramdd.put("curDate", curdate);
		paramdd.put("deptCode",deptCode);
		
		List<HospitalizeDrugRatioModel> result = hospitalizeDrugRatioMapper.selectHospitalizeDrugIncomeByDoctor(paramdd);
		List<HospitalizeIncomeModel> result2 = hospitalizeIncomeMapper.selectDayHospitalIncomeSum(paramdd);
		List<String> codes = new ArrayList<String>();
		HashMap<String, Float> kvs = new HashMap<String, Float>();
		
		for (HospitalizeIncomeModel model2 : result2) {
			hospitalizeIncomeFeeCount  = Float.valueOf(model2.getFeeCount());
		}
		
		for (HospitalizeDrugRatioModel model : result) {
			String doctorCode = model.getDoctorCode();
			codes.add(doctorCode);
			float doctorDrugIncome = Float.valueOf(model.getDoctorDrugIncome());
			float doctorDrugRatio = (doctorDrugIncome / hospitalizeIncomeFeeCount) * 100;
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
