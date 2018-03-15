package com.zmap.income.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;
import com.zmap.income.controller.DrugController.FeeValueEntity;
import com.zmap.zmap.framework.income.mapper.MedicalIncomeMapper;
import com.zmap.zmap.framework.income.mapper.OtherIncomeMapper;
import com.zmap.zmap.framework.income.model.MedicalIncomeModel;
import com.zmap.zmap.framework.income.model.OtherIncomeModel;

/**
 * 
 * 类描述：医疗收入逻辑处理Controller类
 * 创建者： fanmm
 * 项目名称：zmapapp
 * 创建时间: 2016年12月28日 下午5:30:03
 * 版本号: v1.0
 */
@RestController
@RequestMapping("/api/income/medical")
public class MedicalController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@Autowired
	private MedicalIncomeMapper medicalIncomeMapper;
	@Autowired
	private OtherIncomeMapper otherIncomeMapper;
	
	
	/**
	 * 统计当天医院医疗收入
	 */
	@RequestMapping("/countDayMedicalSumIncome")
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
//		String result=String.valueOf(medicalIncomeMapper.selectDaySumIncomeByMedical(params).get(0).get("feeCount"));
		List<HashMap<String, String>> resultMap=medicalIncomeMapper.selectDaySumIncomeByMedical(params);
		for (HashMap<String, String> model : resultMap) {
			sum=sum+Double.valueOf(String.valueOf(model.get("feeCount")) );
		}
//		sum=Double.valueOf(result);
		json.put("sum", sum/10000);
		System.out.println(json.toJSONString());
		return new JSONPObject(callbackFunName, json);
		
	
	}
	
	

	/**
	 * 统计当天医院医疗收入率
	 */
	@RequestMapping("/countDayMedicalIncome")
	@ResponseBody
	public JSONPObject countDayDrugIncome(HttpServletRequest request,HttpServletResponse response){
		String callbackFunName =request.getParameter("callbackparam");//得到js函数名称 
		String sdate =request.getParameter("sdate");
		String hospital=request.getParameter("hospitalCode");
		JSONObject json=new JSONObject();
		HashMap params=new HashMap();
		params.put("curDate",sdate);
		params.put("hospitalCode",hospital);
		List<FeeValueEntity> valueList=new ArrayList<FeeValueEntity>();
		List<String> nameList=new ArrayList<String>();
		List<HashMap<String,String>> code=medicalIncomeMapper.selectAcctItemCode(params);
		for (HashMap<String, String> acode : code) {
			String feeCode=acode.get("feeCode");
			params.put("acctItemCode", feeCode);
			FeeValueEntity entity=new FeeValueEntity();
			 double count1=0;
			 double count2=0;
			 double sum=0;
		List<HashMap<String,String>> name=medicalIncomeMapper.selectAcctItemName(params);
		for (HashMap<String, String> aname : name) {
			String feeName=aname.get("feeName");
			nameList.add(feeName);
			entity.setName(feeName);
		}
		List<HashMap<String,String>> clinicResult=medicalIncomeMapper.selectClinicDayIncomeByMedical(params);
		
		for(HashMap<String,String> model1:clinicResult){
			if (model1!=null) {
				count1=Double.valueOf(String.valueOf(model1.get("feeCount")));
			}else{
				count1=0;
			}
			
		}
		List<HashMap<String,String>> hospitalResult=medicalIncomeMapper.selectHospitalDayIncomeByMedical(params);
		for (HashMap<String, String> model2 : hospitalResult) {
			if (model2!=null) {
				count2=Double.valueOf(String.valueOf(model2.get("feeCount")));
			}else{
				count2=0;
			}
		}
		sum=count1+count2;
		entity.setValue(sum);
	    valueList.add(entity);
	}
		json.put("valueData", valueList);
		json.put("nameData", nameList);
		System.out.println(json.toJSONString());
		return new JSONPObject(callbackFunName, json);
		
	
	}
	
	/**
	 * 统计当天医院各部门医疗收入
	 */
	@RequestMapping("/countDayMedicalIncomeByDept")
	@ResponseBody
	public JSONPObject countDayDrugIncomByDept(HttpServletRequest request,HttpServletResponse response){
		String callbackFunName =request.getParameter("callbackparamt");//得到js函数名称 
		String sdate =request.getParameter("sdate");
		String hospital=request.getParameter("hospitalCode");
		JSONObject json=new JSONObject();
		HashMap params=new HashMap();

		params.put("curDate",sdate);
		params.put("hospitalCode",hospital);
		List<MedicalIncomeModel> result=medicalIncomeMapper.selectDayMedicalIncomeByDept(params);
		
		List<Double> valueList=new ArrayList<Double>();
		List<String> nameList=new ArrayList<String>();
		List<String> codes=new ArrayList<String>();
		HashMap<String, Double> kvs=new HashMap<String, Double>();
		for(MedicalIncomeModel dataMap:result){
			String deptCode=dataMap.getDeptCode();
			double feeCount=dataMap.getFeeCount();
//			if (!dataMap.get("feeCount").equals("null")) {
//				feeCount=Double.valueOf(String.valueOf(dataMap.get("feeCount")).toString());
//			}else {
//				feeCount=0;
//			}
			 
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
		System.out.println(kvs);
		for (int i = 0; i <codes.size(); i++) {
			valueList.add(kvs.get(codes.get(i)));
			System.out.println(valueList);
		}
		json.put("valueData", valueList);
		json.put("nameData", nameList);
		json.put("codeData", codes);
		System.out.println(json.toJSONString());
		return new JSONPObject(callbackFunName, json);
		
	
	}
	
	/**
	 * 科室医生当日医疗收入统计表
	 */
	@RequestMapping("/countDayMedicalIncomeByDoctor")
	@ResponseBody
	public JSONPObject countDayMedicalIncomByDoctor(HttpServletRequest request,HttpServletResponse response){
		String callbackFunName =request.getParameter("callbackparamd");//得到js函数名称 
		String curDeptCode=request.getParameter("deptCode");
		String sdate =request.getParameter("sdate");
		String hospital=request.getParameter("hospitalCode");
		JSONObject json=new JSONObject();
		HashMap params=new HashMap();
		params.put("curDate",sdate);
		params.put("deptCode",curDeptCode);
		params.put("hospitalCode",hospital);
		List<HashMap<String,String>> result=medicalIncomeMapper.selectDayMedicalIncomeByDoctor(params);
		List<Double> valueList=new ArrayList<Double>();
		List<String> nameList=new ArrayList<String>();
		List<String> codes=new ArrayList<String>();
		HashMap<String, Double> kvs=new HashMap<String, Double>();
		for(HashMap<String,String> dataMap:result){
			String doctorCode=dataMap.get("doctorCode");
						
			double feeCount=Double.valueOf(String.valueOf(dataMap.get("feeCount")));
			if(kvs.containsKey(doctorCode)){
				kvs.put(doctorCode, feeCount+kvs.get(doctorCode));
			}else{
				kvs.put(doctorCode, feeCount);
				codes.add(doctorCode);	
				params.put("doctorCode",doctorCode);
				List<OtherIncomeModel> dcode=otherIncomeMapper.selectDoctorName(params);
				for (OtherIncomeModel otherIncomeModel : dcode) {
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
	}
	

}