package com.zmap.drugratio.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.zmap.zmap.clinic.mapper.ClinicsDrugRatioMapper;
import com.zmap.zmap.clinic.model.ClinicsDrugRatioModel;
import com.zmap.zmap.framework.income.mapper.ClinicsIncomeMapper;
import com.zmap.zmap.framework.income.mapper.HospitalizeIncomeMapper;
import com.zmap.zmap.framework.income.model.ClinicsIncomeModel;
import com.zmap.zmap.framework.income.model.HospitalizeIncomeModel;
import com.zmap.zmap.hospitalize.mapper.HospitalizeDrugRatioMapper;
import com.zmap.zmap.hospitalize.model.HospitalizeDrugRatioModel;


@RestController
@RequestMapping("/api/service/sumDrugRatio")
public class SumDrugRatioController {
	
	@Autowired
	private ClinicsDrugRatioMapper clinicsDrugRatioMapper;
	
	@Autowired
	private HospitalizeDrugRatioMapper hospitalizeDrugRatioMapper;
	
	@Autowired
	private ClinicsIncomeMapper clinicsIncomeMapper;
	
	@Autowired
	private HospitalizeIncomeMapper hospitalizeIncomeMapper;
	
	private float sumIncome;      //当日总收入
	private float clinicsIncome;
	private float hospitalizeIncome;
	private float sumDrugIncome;  //当日药品总收入
	private float clinicsDrugIncome;
	private float hospitalizeDrugIncome;
	
	@RequestMapping("/sumDrugRatioByDate")
	public JSONPObject sumDrugRatioByDate (HttpServletRequest request, HttpServletResponse response){
		String callbackFunName = request.getParameter("callbackparams");
		String hospitalCode = request.getParameter("hospitalCode");
		String curdate = request.getParameter("sdate");
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("hospitalCode", hospitalCode);
		params.put("curDate",curdate);
		//当日门诊总收入
		List<ClinicsIncomeModel> result1 = clinicsIncomeMapper.selectDayClinicsIncomeSum(params);
		for (ClinicsIncomeModel model : result1) {
		     clinicsIncome = Float.valueOf(model.getFeeCount());
		}
		//当日住院总收入
		List<HospitalizeIncomeModel> result2 = hospitalizeIncomeMapper.selectDayHospitalIncomeSum(params);
		for (HospitalizeIncomeModel model : result2) {
			hospitalizeIncome = Float.valueOf(model.getFeeCount());
		}
		//当日总收入
		sumIncome = clinicsIncome + hospitalizeIncome;
		
		//当日门诊药品收入
		List<ClinicsDrugRatioModel> result3 = clinicsDrugRatioMapper.selectClinicsDrugIncomeByDate(params);
		for (ClinicsDrugRatioModel model : result3) {
			clinicsDrugIncome = Float.valueOf(model.getClinicsDrugIncome());
		}
		//当日住院药品收入
		List<HospitalizeDrugRatioModel> result4 = hospitalizeDrugRatioMapper.selectHospitalizeDrugIncomeByDate(params);
		for (HospitalizeDrugRatioModel model : result4) {
			hospitalizeDrugIncome = Float.valueOf(model.getHospitalizeDrugIncome());
		}
		//当日药品总收入
		sumDrugIncome = clinicsDrugIncome + hospitalizeDrugIncome;
		float sumDrugRatio = (sumDrugIncome / sumIncome) * 100;
		JSONObject json = new JSONObject();
		json.put("sumDrugRatio", sumDrugRatio);
		System.out.println(json.toJSONString());
		return new JSONPObject(callbackFunName, json);
		
	}

}
