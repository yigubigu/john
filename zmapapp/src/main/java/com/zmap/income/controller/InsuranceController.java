package com.zmap.income.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.zmap.zmap.framework.base.mapper.ZmapRDeptMapper;
import com.zmap.zmap.framework.base.mapper.ZmapRDoctorMapper;
import com.zmap.zmap.framework.base.model.ZmapRDeptModel;
import com.zmap.zmap.framework.base.model.ZmapRDoctorModel;
import com.zmap.zmap.framework.income.mapper.InsuranceMapper;


@RestController
@RequestMapping("/api/income/insurance")
public class InsuranceController {
	
	
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@Autowired
	private InsuranceMapper insuranceMapper;
	
	
	@Autowired
	private ZmapRDeptMapper zmapRDeptMapper;
	
	@Autowired
	private ZmapRDoctorMapper zmapRDoctorSyncMapper;
	
	
	
	/**
	 * 统计当天参保收入
	 */
	@RequestMapping("/countDayInsuranceSumIncome")
	@ResponseBody
	public JSONPObject countDayInsuranceSumIncome(HttpServletRequest request,HttpServletResponse response){
		String callbackFunName =request.getParameter("callbackparam");//得到js函数名称 
		String sdate =request.getParameter("sdate");
		String hospital=request.getParameter("hospitalCode");
		JSONObject json=new JSONObject();
		HashMap params=new HashMap();
		params.put("curDate",sdate);
		params.put("hospitalCode",hospital);
		double sum=0.0;
		List<HashMap<String,String>> resultMap=insuranceMapper.selectDaySumIncomeByInsurance(params);
		for(HashMap<String,String> result:resultMap){
			sum=sum+Double.valueOf(String.valueOf(result.get("feeCount")));
		}
//		String result=String.valueOf(drugIncomeMapper.selectDayIncomeByDrug(params).get(0).get("feeCount"));
//		sum=Double.valueOf(result);
		DecimalFormat format=new DecimalFormat("#.##");
		json.put("sum", format.format(sum/10000));
		System.out.println(json.toJSONString());
		return new JSONPObject(callbackFunName, json);
		
	
	}
	
	
	/**
	 * 统计当天门诊:住院收入比例
	 */
	@RequestMapping("/countDayInsuranceIncome")
	@ResponseBody
	public JSONPObject countDayInsuranceIncome(HttpServletRequest request,HttpServletResponse response){
		String callbackFunName =request.getParameter("callbackparams");//得到js函数名称 
		String sdate =request.getParameter("sdate");
		String hospital=request.getParameter("hospitalCode");
		JSONObject json=new JSONObject();
		HashMap params=new HashMap();
		params.put("curDate",sdate);
		params.put("hospitalCode",hospital);
		List<Double> datas=new ArrayList<Double>();
		List<HashMap<String,String>> resultMap=insuranceMapper.selectDaySumIncomeByInsurance(params);
		List<HashMap<String,String>> resultData=new ArrayList<HashMap<String,String>>();
		for(int i=0;i<resultMap.size();i++){
			if(i==0){
				HashMap<String,String> map=new HashMap<String, String>();
				map.put("name","门诊");
				map.put("value",String.valueOf(resultMap.get(i).get("feeCount")));
				resultData.add(map);
			}
			if(i==1){
				HashMap<String,String> map=new HashMap<String, String>();
				map.put("name","住院");
				map.put("value",String.valueOf(resultMap.get(i).get("feeCount")));
				resultData.add(map);
			}
		}
		List<String> namedata=new ArrayList<String>();
		namedata.add("门诊");
		namedata.add("住院");
		json.put("resultdata",resultData);
		json.put("namedata",namedata);
		System.out.println(json.toJSONString());
		return new JSONPObject(callbackFunName, json);
		
	
	}
	
	
	/**
	 * 统计当天各部门参保收入
	 */
	@RequestMapping("/countDayInsuranceIncomeByDept")
	@ResponseBody
	public JSONPObject countDayInsuranceIncomByDept(HttpServletRequest request,HttpServletResponse response){
		String callbackFunName =request.getParameter("callbackparamd");//得到js函数名称 
		String sdate =request.getParameter("sdate");
		String hospital=request.getParameter("hospitalCode");
		JSONObject json=new JSONObject();
		HashMap params=new HashMap();
		params.put("curDate",sdate);
		params.put("hospitalCode",hospital);
		List<HashMap<String,String>> result=insuranceMapper.selectDayInsuranceIncomeByDept(params);
		HashMap<Long, Double> kvs=new HashMap<Long, Double>();
		List<Long> codes=new ArrayList<Long>(); 
		for(Map<String,String> m:result){
			Long dcode=Long.valueOf(String.valueOf(m.get("deptCode")));
			Double dCount=Double.valueOf(String.valueOf(m.get("feeCount")));
			if(kvs.containsKey(dcode)){
				kvs.put(dcode, kvs.get(dcode)+dCount);
			}else{
				kvs.put(dcode,dCount);
				codes.add(Long.valueOf(String.valueOf(m.get("deptCode"))));
			}
			
			
		}
		params.put("codes", codes);
		List<ZmapRDeptModel> depts=zmapRDeptMapper.selectDetpsByDeptCodes(params);
		List<Double> valueData=new ArrayList<Double>();
		List<String>  idData=new ArrayList<String>();
		List<String> nameData=new ArrayList<String>();
		for(ZmapRDeptModel model:depts){
			String deptCode=model.getDeptCode();
			String deptName=model.getDeptName();
			Double sum=kvs.get(Long.valueOf(deptCode));
			valueData.add(sum);
			nameData.add(deptName);
			idData.add(deptCode);
		}
		json.put("name",nameData);
		json.put("value", valueData);
		json.put("deptCodes", idData);
		System.out.println(json.toJSONString());
		return new JSONPObject(callbackFunName, json);
		
	
	}
	
	
	
	/**
	 * 科室医生参保收入
	 */
	@RequestMapping("/countDayDoctorInsuranceByDept")
	@ResponseBody
	public JSONPObject countDayDoctorInsuranceByDept(HttpServletRequest request,HttpServletResponse response){
		String code=request.getParameter("deptCode");
		String callbackFunName =request.getParameter("callbackparamt");//得到js函数名称 
		String hospital=request.getParameter("hospitalCode");
		JSONObject json=new JSONObject();
		String date=request.getParameter("sdate");
		HashMap params=new HashMap();
		params.put("curDate",date);
		params.put("deptCode",code);
		params.put("hospitalCode",hospital);
		List<HashMap<String,String>> doctorIncomeList=insuranceMapper.selectDayInsuranceIncomeByDoctor(params);
		HashMap<Long, Double> kvs=new HashMap<Long, Double>();
		List<Long> codes=new ArrayList<Long>(); 
		List<Double> valueData=new ArrayList<Double>();
		List<String> nameData=new ArrayList<String>();
		if(doctorIncomeList!=null){
			for(Map<String,String> m:doctorIncomeList){
				Long docCode=Long.valueOf(String.valueOf(m.get("doctorCode")));
				Double docCount=Double.valueOf(String.valueOf(m.get("feeCount")));
				if(kvs.containsKey(docCode)){
					kvs.put(docCode,kvs.get(docCode)+docCount);
				}else{
					kvs.put(docCode,docCount);
					codes.add(Long.valueOf(String.valueOf(m.get("doctorCode"))));
				}
			}
			params.put("codes",codes);
			List<ZmapRDoctorModel> doctors=zmapRDoctorSyncMapper.selectDoctorsByDoctorCodes(params);
			for(ZmapRDoctorModel model:doctors){
				String docName=model.getDoctorName();
				Double sum=kvs.get(Long.valueOf(model.getDoctorCode()));
				nameData.add(docName);
				valueData.add(sum);
			}
		}
		
		json.put("name",nameData);
		json.put("value", valueData);
		System.out.println(json.toJSONString());
		return new JSONPObject(callbackFunName, json);
		
	
	}

}
