package com.zmap.income.controller;

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
import com.zmap.zmap.framework.income.mapper.OtherIncomeMapper;
import com.zmap.zmap.framework.income.model.OtherIncomeModel;

@RestController
@RequestMapping("/api/income/otherIncome")
public class OtherIncomeController {

	@Autowired
	private OtherIncomeMapper otherIncomeMapper;
	
	@RequestMapping("/byDept")
	@ResponseBody
	public JSONPObject selectOtherIncomeByDept(HttpServletRequest request,HttpServletResponse response){
		String callbackFullName=request.getParameter("callbackparamt");
		String sdate=request.getParameter("sdate");
		String hospitalCode=request.getParameter("hospitalCode");
		HashMap<String, String> params=new HashMap<String, String>();
		params.put("curDate", sdate);
		params.put("hospitalCode",hospitalCode);
		List<OtherIncomeModel> deptresult=otherIncomeMapper.selectDeptcode(params);
		//HashMap<String, Double> kvs=new HashMap<String, Double>();
		List<String> name=new ArrayList<String>();
		List<Double> count=new ArrayList<Double>();
		List<String> dcode=new ArrayList<String>();
		
		for (OtherIncomeModel model : deptresult) {
			String code=model.getDeptCode();
			params.put("deptCode", code);
			dcode.add(code);
			List<OtherIncomeModel> clinicresult=otherIncomeMapper.selectClinicOtherIncome(params);
			List<OtherIncomeModel> hospitalresult=otherIncomeMapper.selectHospitalIncome(params);
			List<OtherIncomeModel> deptName=otherIncomeMapper.selectDeptName(params);
			double feecount1=0;
			double feecount2=0;
			for (OtherIncomeModel model2 : clinicresult) {
				if(model2!=null){
					feecount1=model2.getFeeCount();
				}else {
					feecount1=0;
				}
			}
			for (OtherIncomeModel model3 : hospitalresult) {
				if(model3!=null){
					feecount2=model3.getFeeCount();
				}else {
					feecount2=0;
				}
			}
			double sum=feecount1+feecount2;
			count.add(sum);
			
			for (OtherIncomeModel model4 : deptName) {
				String deptname=model4.getDeptName();
				 name.add(deptname);
			}
//			double dCount=model.getFeeCount();
//			if(kvs.containsKey(dcode)){
//				kvs.put(dcode, kvs.get(dcode));
//				count.add(kvs.get(dcode)+dCount);
//			}else{
//				kvs.put(dcode, dCount);
//				count.add(dCount);
//			}
//			code.add(dcode);
		}
		
		JSONObject json=new JSONObject();
		json.put("name", name);
		json.put("value", count);
		json.put("dcode", dcode);
		System.out.println(json.toJSONString());
		return new JSONPObject(callbackFullName,json);
	}
	
	@RequestMapping("/byDoctor")
	@ResponseBody
	public JSONPObject selectOtherIncomeByDoctor(HttpServletRequest request,HttpServletResponse response){
		String callbackFullName=request.getParameter("callbackparamd");
		String sdate=request.getParameter("sdate");
		String hospitalCode=request.getParameter("hospitalCode");
		String deptCode=request.getParameter("deptcode");
		HashMap<String, String> params=new HashMap<String, String>();
		params.put("curDate", sdate);
		params.put("hospitalCode", hospitalCode);
		params.put("deptCode", deptCode);
		List<String> name=new ArrayList<String>();
		List<Double> value=new ArrayList<Double>();
		List<OtherIncomeModel> doctorcode=otherIncomeMapper.selectDoctorcode(params);
		
		for (OtherIncomeModel model : doctorcode) {
			double feecount1=0;
			double feecount2=0;
			String doctorCode=model.getDoctorCode();
			params.put("doctorCode", doctorCode);
			List<OtherIncomeModel> doctorname=otherIncomeMapper.selectDoctorName(params);
			List<OtherIncomeModel> clinicfeecount=otherIncomeMapper.selectClinicOtherIncomeByDoctor(params);
			List<OtherIncomeModel> hospitalfeecount=otherIncomeMapper.selectHospitalIncomeByDoctor(params);
			for (OtherIncomeModel model2 : doctorname) {
				String doctorName=model2.getDoctorName();
				name.add(doctorName);
			}
			for (OtherIncomeModel model3 : clinicfeecount) {
				if(model3!=null){
					feecount1=model3.getFeeCount();
				}else {
					feecount1=0;
				}
			}
			for (OtherIncomeModel model4 : hospitalfeecount) {
				if(model4!=null){
					feecount2=model4.getFeeCount();
				}else {
					feecount2=0;
				}
			}
			double sum=feecount1+feecount2;
			value.add(sum);
		}
		
		JSONObject json=new JSONObject();
		json.put("name", name);
		json.put("value", value);
		System.out.println(json.toJSONString());
		return new JSONPObject(callbackFullName,json);
				
	}
	
	@RequestMapping("/bySum")
	@ResponseBody
	public JSONPObject selectSum(HttpServletRequest request,HttpServletResponse response){
		String callbackFullName=request.getParameter("callbackparams");
		String hospitalCode=request.getParameter("hospitalCode");
		String sdate=request.getParameter("sdate");
		HashMap<String, String> params=new HashMap<String, String>();
		params.put("hospitalCode", hospitalCode);
		params.put("curDate", sdate);
		List<Double> value=new ArrayList<Double>();
		List<OtherIncomeModel> summodel=otherIncomeMapper.selectSum(params);
		for (OtherIncomeModel model : summodel) {
			double sum=Long.valueOf( (long) model.getSum())/10000;
			value.add(sum);
		}
		JSONObject json=new JSONObject();
		json.put("value", value);
		return new JSONPObject(callbackFullName,json);
	}
}
