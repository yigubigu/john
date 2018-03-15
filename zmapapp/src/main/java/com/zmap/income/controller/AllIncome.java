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
import com.zmap.zmap.framework.income.mapper.ClinicsIncomeMapper;
import com.zmap.zmap.framework.income.mapper.CostPrivateMapper;
import com.zmap.zmap.framework.income.mapper.DrugIncomeMapper;
import com.zmap.zmap.framework.income.mapper.HospitalizeIncomeMapper;
import com.zmap.zmap.framework.income.mapper.InsuranceMapper;
import com.zmap.zmap.framework.income.mapper.MaterialIncomeMapper;
import com.zmap.zmap.framework.income.mapper.MedicalIncomeMapper;
import com.zmap.zmap.framework.income.mapper.OtherIncomeMapper;
import com.zmap.zmap.framework.income.model.ClinicsIncomeModel;
import com.zmap.zmap.framework.income.model.HospitalizeIncomeModel;
import com.zmap.zmap.framework.income.model.MaterialIncomeModel;
import com.zmap.zmap.framework.income.model.OtherIncomeModel;

@RestController
@RequestMapping("/api/income/allIncome")
public class AllIncome {
	@Autowired
	private DrugIncomeMapper drugIncomeMapper;
	
	@Autowired
	private MedicalIncomeMapper medicalIncomeMapper;
	
	@Autowired
	private MaterialIncomeMapper materialIncomeMapper;
	
	@Autowired
	private OtherIncomeMapper otherIncomeMapper;
	
	@Autowired 
	private ClinicsIncomeMapper clinicsIncomeMapper;
	
	@Autowired
	private HospitalizeIncomeMapper hospitalizeIncomeMapper;
	
	@Autowired 
	private CostPrivateMapper costPrivateMapper;
	
	@Autowired
	private InsuranceMapper insuranceMapper;
	/**
	 * 当日药品收入 + 当日诊疗收入+ 当日卫材收入 + 当日其他收入
	 */
	@RequestMapping("/style1")
	@ResponseBody
	public JSONPObject selectAllIncome(HttpServletRequest request,HttpServletResponse response){
		String callbackFullName=request.getParameter("callbackparam1");
		String sdate=request.getParameter("sdate");
		String hospital=request.getParameter("hospitalCode");
		HashMap<String, String> params=new HashMap<String, String>();
		params.put("curDate", sdate);
		params.put("hospitalCode", hospital);
		List<HashMap<String, String>> drugData=drugIncomeMapper.selectDaySumIncomeByDrug(params);
		List<HashMap<String, String>> medicalData=medicalIncomeMapper.selectDaySumIncomeByMedical(params);
		List<MaterialIncomeModel> materialData=materialIncomeMapper.selectSumMaterialIncomeByDept(params);
		List<OtherIncomeModel> otherData=otherIncomeMapper.selectSum(params);
		List<FeeValueEntity> valueList=new ArrayList<FeeValueEntity>();
		List<String> nameList=new ArrayList<String>();
		
		double sum[]=new double[4];
		String name[]={"当日药品收入","当日诊疗收入","当日卫材收入","当日其他收入"};
		for (int i = 0; i < sum.length; i++) {
			 FeeValueEntity entity=new FeeValueEntity();
			if (i==0) {
				for (HashMap<String, String> result : drugData) {
					sum[i]=sum[i]+Double.valueOf(String.valueOf(result.get("feeCount")))/10000.0;
				}
				 nameList.add(name[i]);
			}else if(i==1){
				for (HashMap<String, String> model : medicalData) {
					sum[i]=sum[i]+Double.valueOf(String.valueOf(model.get("feeCount")) )/10000.0;
				}
				 nameList.add(name[i]);
			}else if(i==2) {
				for (MaterialIncomeModel model : materialData) {
					sum[i]=Long.valueOf( (long) model.getSum())/10000.0;
				}
				 nameList.add(name[i]);
			}else{

				 for (OtherIncomeModel model : otherData) {
						 sum[i]=Long.valueOf( (long) model.getSum())/10000.0;
					}
				 nameList.add(name[i]);
			}
			entity.setName(name[i]);
			entity.setValue(sum[i]);
			valueList.add(entity);
		}	
		JSONObject json=new JSONObject();
		json.put("nameData",nameList);
		json.put("valueData",valueList);
		System.out.println(json.toJSONString());
		return new JSONPObject(callbackFullName,json);
	}
	/*
	 * 2、当日总收入 = 当日门急诊收入 + 当日住院收入
	 */
	@RequestMapping("/style2")
	@ResponseBody
	public JSONPObject selectAllIncome2(HttpServletRequest request,HttpServletResponse response){
		String callbackFullName=request.getParameter("callbackparam2");
		String sdate=request.getParameter("sdate");
		String hospital=request.getParameter("hospitalCode");
		HashMap<String, String> params=new HashMap<String, String>();
		params.put("curDate", sdate);
		params.put("hospitalCode", hospital);
		List<ClinicsIncomeModel> clinicsData=clinicsIncomeMapper.selectDayClinicsIncomeSum(params);
		List<HospitalizeIncomeModel> hospitalData=hospitalizeIncomeMapper.selectDayHospitalIncomeSum(params);
		List<FeeValueEntity> valueList=new ArrayList<FeeValueEntity>();
		List<String> nameList=new ArrayList<String>();
		List<Double> value=new ArrayList<Double>();
		double sum[]=new double[2];
		String name[]={"当日门急诊收入","当日住院收入"};
		Double totalSum1=0.0;
		Double totalSum2=0.0;
		Double totalSum=0.0;
		for (int i = 0; i < sum.length; i++) {
			FeeValueEntity entity=new FeeValueEntity();
			if (i==0) {
				for (ClinicsIncomeModel model : clinicsData) {
					sum[i]=Long.valueOf( (long) model.getFeeCount())/10000.0;
					totalSum1=sum[i];
				}
				nameList.add(name[i]);
			}else if (i==1) {
				for (HospitalizeIncomeModel model : hospitalData) {
					sum[i]=Long.valueOf( (long) model.getFeeCount())/10000.0;
					totalSum2=sum[i];
				}
				nameList.add(name[i]);
			}
			entity.setName(name[i]);
			entity.setValue(sum[i]);
			valueList.add(entity);
		}
		totalSum=totalSum1+totalSum2;
		value.add(totalSum);
		 
		JSONObject json=new JSONObject();
		json.put("nameData",nameList);
		json.put("valueData",valueList);
		json.put("totalSum",totalSum);
		System.out.println(json.toJSONString());
		return new JSONPObject(callbackFullName,json);
	}
	
	/*
	 * 3、当日总收入 = 当日参保收入 + 当日自费收入
	 */
	@RequestMapping("/style3")
	@ResponseBody
	public JSONPObject selectAllIncome3(HttpServletRequest request,HttpServletResponse response){
		String callbackFullName=request.getParameter("callbackparam3");
		String sdate=request.getParameter("sdate");
		String hospital=request.getParameter("hospitalCode");
		HashMap<String, String> params=new HashMap<String, String>();
		params.put("curDate", sdate);
		params.put("hospitalCode", hospital);
		List<HashMap<String, String>> costData=costPrivateMapper.selectDaySumIncomeByPrivate(params);
		List<HashMap<String, String>> insuranceData=insuranceMapper.selectDaySumIncomeByInsurance(params);
		List<FeeValueEntity> valueList=new ArrayList<FeeValueEntity>();
		List<String> nameList=new ArrayList<String>();
		double sum[]=new double[2];
		String name[]={"当日自费收入","当日参保收入"};
		for (int i = 0; i < sum.length; i++) {
			FeeValueEntity entity=new FeeValueEntity();
			if (i==0) {
				for(HashMap<String,String> result:costData){
					sum[i]=sum[i]+Double.valueOf(String.valueOf(result.get("feeCount")))/10000.0;
				}
				nameList.add(name[i]);
			}else if (i==1) {
				for(HashMap<String,String> result:insuranceData){
					sum[i]=sum[i]+Double.valueOf(String.valueOf(result.get("feeCount")))/10000.0;
				}
				nameList.add(name[i]);
			}
			entity.setName(name[i]);
			entity.setValue(sum[i]);
			valueList.add(entity);
		}
		 
		JSONObject json=new JSONObject();
		json.put("nameData",nameList);
		json.put("valueData",valueList);
		System.out.println(json.toJSONString());
		return new JSONPObject(callbackFullName,json);
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
