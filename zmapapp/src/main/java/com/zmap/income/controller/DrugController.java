package com.zmap.income.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.zmap.income.controller.AllIncome.FeeValueEntity;
import com.zmap.zmap.framework.income.mapper.DrugIncomeMapper;
import com.zmap.zmap.framework.income.mapper.OtherIncomeMapper;
import com.zmap.zmap.framework.income.model.OtherIncomeModel;


@RestController
@RequestMapping("/api/income/drug")
public class DrugController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@Autowired
	private DrugIncomeMapper drugIncomeMapper;
	
	@Autowired
	private OtherIncomeMapper otherIncomeMapper;
	
	
	/**
	 * 统计当天医院药品收入
	 */
	@RequestMapping("/countDayDrugSumIncome")
	@ResponseBody
	public JSONPObject countDayDrugSumIncome(HttpServletRequest request,HttpServletResponse response){
		String callbackFunName =request.getParameter("callbackparams");//得到js函数名称 
		String sdate =request.getParameter("sdate");
		String hospital=request.getParameter("hospitalCode");
		JSONObject json=new JSONObject();
		HashMap params=new HashMap();
		params.put("curDate",sdate);
		params.put("hospitalCode",hospital);
		double sum=0.0;
//		Double result=Double.valueOf(drugIncomeMapper.selectDayIncomeByDrug(params).get(0).get("feeCount"));
		List<HashMap<String, String>> resultmap=drugIncomeMapper.selectDaySumIncomeByDrug(params);
		for (HashMap<String, String> result : resultmap) {
			sum=sum+Double.valueOf(String.valueOf(result.get("feeCount")));
		}
		//sum=Double.valueOf(result);
		json.put("sum", sum/10000);
		System.out.println(json.toJSONString());
		return new JSONPObject(callbackFunName, json);
		
	
	}
	
	

	/**
	 * 统计当天医院药品收入率
	 */
	@RequestMapping("/countDayDrugIncome")
	@ResponseBody
	public JSONPObject countDayDrugIncome(HttpServletRequest request,HttpServletResponse response){
		String callbackFunName =request.getParameter("callbackparam");//得到js函数名称 
		String sdate =request.getParameter("sdate");
		String hospital=request.getParameter("hospitalCode");
		JSONObject json=new JSONObject();
		HashMap params=new HashMap();
		params.put("curDate",sdate);
		params.put("hospitalCode",hospital);
		List<HashMap<String,String>> result=drugIncomeMapper.selectFeeCode(params);
		
		List<FeeValueEntity> valueList=new ArrayList<FeeValueEntity>();
		List<String> nameList=new ArrayList<String>();
		HashMap<String, Double> kvs=new HashMap<String, Double>();
		
		
		for (HashMap<String, String> model : result) {
			FeeValueEntity entity=new FeeValueEntity();
			String feeCode=model.get("feeCode");
			
			if (!kvs.containsKey(feeCode)) {
				params.put("feeTypeCode", feeCode);
				List<HashMap<String,String>> name=drugIncomeMapper.selectFeeName(params);
				for (HashMap<String, String> hashMap : name) {
					String feeName=hashMap.get("feeName");
					nameList.add(feeName);
					entity.setName(feeName);
				}
			}
			nameList.add(entity.getName());
			System.out.println(nameList);
			params.put("feeTypeCode", feeCode);
		    double count1=0;
		    double count2=0;
		    double sum=0;
		List<HashMap<String,String>> data1=drugIncomeMapper.selectDayClinicIncomeByDrug(params);
		List<HashMap<String,String>> data2=drugIncomeMapper.selectDayHospitalIncomeByDrug(params);
		
		for (HashMap<String, String> model2 : data1) {
			if (model2!=null) {
				count1=Double.valueOf(String.valueOf(model2.get("feeCount")));
			}else{
				count1=0;
			}
		}
		for (HashMap<String, String> model3 : data2) {
			if (model3!=null) {
				count2=Double.valueOf(String.valueOf(model3.get("feeCount")));
			}else{
				count2=0;
			}
		}
		
		sum=count1+count2;
		System.out.println(sum);
		entity.setValue(sum);
		valueList.add(entity);
//		System.out.println(valueList);
	}
		
		json.put("valueData", valueList);
	    json.put("nameData", nameList);
		System.out.println(json.toJSONString());
		return new JSONPObject(callbackFunName, json);
		
	
	}
	
	/**
	 * 统计当天医院各部门药品收入率
	 */
	@RequestMapping("/countDayDrugIncomeByDept")
	@ResponseBody
	public JSONPObject countDayDrugIncomByDept(HttpServletRequest request,HttpServletResponse response){
		String callbackFunName =request.getParameter("callbackparamt");//得到js函数名称 
		String sdate =request.getParameter("sdate");
		String hospital=request.getParameter("hospitalCode");
		JSONObject json=new JSONObject();
		HashMap params=new HashMap();
		params.put("curDate",sdate);
		params.put("hospitalCode",hospital);
		List<HashMap<String,String>> result=drugIncomeMapper.selectDayDrugIncomeByDept(params);
		List<Double> valueList=new ArrayList<Double>();
		List<String> nameList=new ArrayList<String>();
		List<String> codes=new ArrayList<String>();
		HashMap<String, Double> kvs=new HashMap<String, Double>();
		for(HashMap<String,String> dataMap:result){
			String deptCode=dataMap.get("deptCode");
			//String deptName=dataMap.get("deptName");
			
			double feeCount=Double.valueOf(String.valueOf(dataMap.get("feeCount")));
			if(kvs.containsKey(deptCode)){
				kvs.put(deptCode, feeCount+kvs.get(deptCode));
			}else{
				kvs.put(deptCode, feeCount);
				codes.add(deptCode);	
				params.put("deptCode",deptCode);
				List<OtherIncomeModel> dcode=otherIncomeMapper.selectDeptName(params);
				for (OtherIncomeModel otherIncomeModel : dcode) {
					String deptName=otherIncomeModel.getDeptName();
					nameList.add(deptName);
				}
			}
		}
		for (int i = 0; i <codes.size(); i++) {
			valueList.add(kvs.get(codes.get(i)));
		}
		
		
		System.out.println(kvs);
		json.put("valueData", valueList);
		json.put("nameData", nameList);
		json.put("codeData", codes);
		System.out.println(json.toJSONString());
		return new JSONPObject(callbackFunName, json);
		
	
	}
	
	/**
	 * 科室医生当日药品收入统计表
	 */
	@RequestMapping("/countDayDrugIncomeByDoctor")
	@ResponseBody
	public JSONPObject countDayDrugIncomByDoctor(HttpServletRequest request,HttpServletResponse response){
		String callbackFunName =request.getParameter("callbackparamd");//得到js函数名称 
		String curDeptCode=request.getParameter("deptCode");
		String sdate =request.getParameter("sdate");
		String hospital=request.getParameter("hospitalCode");
		JSONObject json=new JSONObject();
		
		HashMap params=new HashMap();

		params.put("curDate",sdate);
		params.put("curDeptCode",curDeptCode);
		params.put("hospitalCode",hospital);
		List<HashMap<String,String>> result=drugIncomeMapper.selectDayDrugIncomeByDoctor(params);
		List<Double> valueList=new ArrayList<Double>();
		List<String> nameList=new ArrayList<String>();
		List<String> codes=new ArrayList<String>();
		HashMap<String, Double> kvs=new HashMap<String, Double>();
		for(HashMap<String,String> dataMap:result){
			String doctorCode=dataMap.get("doctorCode");
			
			//String doctorName=dataMap.get("doctorName");
			double feeCount=Double.valueOf(String.valueOf(dataMap.get("feeCount")));
			if(kvs.containsKey(doctorCode)){
				kvs.put(doctorCode, feeCount+kvs.get(doctorCode));
			}else{
				kvs.put(doctorCode, feeCount);
				codes.add(doctorCode);
				params.put("doctorCode", doctorCode);
				List<OtherIncomeModel> dname=otherIncomeMapper.selectDoctorName(params);
				for (OtherIncomeModel otherIncomeModel : dname) {
					String doctorName=otherIncomeModel.getDoctorName();
					nameList.add(doctorName);
				}
				}
		}
		for (int i = 0; i <codes.size(); i++) {
			valueList.add(kvs.get(codes.get(i)));
		}
		json.put("valueData", valueList);
		json.put("nameData", nameList);
		System.out.println(json.toJSONString());
		return new JSONPObject(callbackFunName, json);
		
	
	}
	
	class FeeValueEntity{
		private String name;
		private Double value;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Double getValue() {
			return value;
		}
		public void setValue(Double value) {
			this.value = value;
		}
		@Override
		public String toString() {
			return "FeeValueEntity [name=" + name + ", value=" + value + "]";
		}
		
	}
	

}
