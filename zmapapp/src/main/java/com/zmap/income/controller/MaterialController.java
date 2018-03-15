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
import com.zmap.zmap.framework.income.mapper.MaterialIncomeMapper;
import com.zmap.zmap.framework.income.mapper.OtherIncomeMapper;
import com.zmap.zmap.framework.income.model.MaterialIncomeModel;
import com.zmap.zmap.framework.income.model.OtherIncomeModel;

@RestController
@RequestMapping("/api/income/material")
public class MaterialController {
	
	@Autowired
	private MaterialIncomeMapper materialIncomeMapper;
	
	@Autowired
	private OtherIncomeMapper otherIncomeMapper;
	
     /**                                                                 
      * 医院一天材料收入总和
      */
	
	 @RequestMapping("/countDayMaterialSumIncome")
	  @ResponseBody
	 public JSONPObject countDayMaterialSumIncome(HttpServletRequest request,HttpServletResponse response){
		 String callbackFunName=request.getParameter("callbackparams");
		 String sdate =request.getParameter("sdate");
		 String hospital=request.getParameter("hospitalCode");
		 HashMap<String, String> params=new HashMap<String, String>();
		 params.put("curDate",sdate);
		 params.put("hospitalCode",hospital);
		 List<MaterialIncomeModel> material=materialIncomeMapper.selectSumMaterialIncomeByDept(params);
		 List<Double>sumData=new ArrayList<Double>();
		 for (MaterialIncomeModel model : material) {
			double count=Long.valueOf( (long) model.getSum())/10000.0;
			sumData.add(count);
		}
		 JSONObject json=new JSONObject();
		 json.put("sum",sumData);
		 System.out.println(json.toJSONString());
		 return new JSONPObject(callbackFunName, json);
	 }
	   
	/**
	 * 科室卫生材料收入
	 * 
	 */
    @RequestMapping("/materialIncomeByDepts")
    @ResponseBody
    public JSONPObject selectMaterialIncomeByDept(HttpServletRequest request,HttpServletResponse response){
    	String callbackFunName=request.getParameter("callbackparamd");
    	String hospital=request.getParameter("hospitalCode");
    	String date=request.getParameter("sdate");
		String startTime=date+" 00:00:00";
		String endTime=date+" 23:59:59";
		HashMap<String, String> params=new HashMap<String, String>();
		params.put("startTime",startTime);
		params.put("endTime",endTime);
		params.put("hospitalCode", hospital);
		params.put("curDate", date);
    	List<MaterialIncomeModel> material=materialIncomeMapper.selectMaterialIncomeByDeptCode(params);
    	List<Double> value=new ArrayList<Double>();
    	List<String> name=new ArrayList<String>();
    	List<String> icode=new ArrayList<String>();
    	for (MaterialIncomeModel model:material) {
    		double feeCount1=0;
			double feeCount2=0;
    		String codeData=model.getDeptCode();
    		params.put("deptCode", codeData);
    		icode.add(codeData);
    	List<MaterialIncomeModel> clinisDeptFee=materialIncomeMapper.selectClinicMaterialIncomeByDept(params);
    	List<MaterialIncomeModel> hospitalDeptFee=materialIncomeMapper.selectHospitalizeMaterialIncomeByDept(params);
    	List<OtherIncomeModel> deptname=otherIncomeMapper.selectDeptName(params);
    	for (MaterialIncomeModel model2 : clinisDeptFee) {
    		if (model2!=null) {
    			feeCount1=model2.getFeeCount();
			}else {
				feeCount1=0;
			}
			System.out.println(feeCount1);
		}
    	
    	for (MaterialIncomeModel model3 : hospitalDeptFee) {
    		if (model3!=null) {
    			feeCount2=model3.getFeeCount();
			}else {
				feeCount2=0;
			}
			System.out.println(feeCount2);
		}
    	    double sum=feeCount1+feeCount2;
    	    value.add(sum);
    	    
    	    //获取科室名称
    	    for (OtherIncomeModel model4 : deptname) {
				String deptName=model4.getDeptName();
				name.add(deptName);
			}
    	    
		}
    	JSONObject json=new JSONObject();
    	json.put("name", name);
    	json.put("value", value);
    	json.put("icode", icode);
    	System.out.println(json.toJSONString());
    	return new JSONPObject(callbackFunName, json);
    }
    
    @RequestMapping("/materialIncomeByDoctor")
    @ResponseBody
    public JSONPObject selectMaterialIncomeByDoctor(HttpServletRequest request,HttpServletResponse response){
    	String callbackFunName=request.getParameter("callbackparamdd");
    	String hospital=request.getParameter("hospitalCode");
    	String deptcode=request.getParameter("deptcode");
    	String date=request.getParameter("sdate");
		String startTime=date+" 00:00:00";
		String endTime=date+" 23:59:59";
    	HashMap<String, String> params=new HashMap<String, String>();
		params.put("startTime",startTime);
		params.put("endTime",endTime);
		params.put("deptCode", deptcode);
		params.put("hospitalCode", hospital);
		params.put("curDate", date);
    	List<MaterialIncomeModel> doctorcode=materialIncomeMapper.selectMaterialIncomeByDoctorCode(params);
    	List<Double> value=new ArrayList<Double>();
    	List<String> name=new ArrayList<String>();
    	for (MaterialIncomeModel model:doctorcode) {
    		double feeCount1=0;
			double feeCount2=0;
			String doctorCode=model.getDoctorCode();
			params.put("doctorCode", doctorCode);
			System.out.println(doctorCode);
		    List<OtherIncomeModel> doctorname=otherIncomeMapper.selectDoctorName(params);
		    for (OtherIncomeModel model2 : doctorname) {
				String doctorName=model2.getDoctorName();
				name.add(doctorName);
			}
		    List<MaterialIncomeModel> clinicDoctorFee=materialIncomeMapper.selectClinicMaterialIncomeByDoctor(params);
		    for (MaterialIncomeModel model3 : clinicDoctorFee) {
		    	if (model3!=null) {
	    			feeCount1=model3.getFeeCount();
				}else {
					feeCount1=0;
				}
			}
		    List<MaterialIncomeModel> hospitalDoctorFee=materialIncomeMapper.selectHospitalMaterialIncomeByDoctor(params);
		    for (MaterialIncomeModel model4 : hospitalDoctorFee) {
		    	if (model4!=null) {
	    			feeCount2=model4.getFeeCount();
	    			
				}else {
					feeCount2=0;
				}
			}
		    double sum=feeCount1+feeCount2;
		    value.add(sum);
		}
    	JSONObject json=new JSONObject();
    	json.put("name", name);
    	json.put("value", value);
    	System.out.println(json.toJSONString());
    	return new JSONPObject(callbackFunName, json);
    }

}
