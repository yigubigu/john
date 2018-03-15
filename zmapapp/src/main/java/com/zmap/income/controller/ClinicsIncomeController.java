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
import com.zmap.common.DateUtil;
import com.zmap.zmap.framework.income.mapper.ClinicsIncomeMapper;
import com.zmap.zmap.framework.income.model.ClinicsIncomeModel;
import com.zmap.zmap.framework.model.NameValueEntity;


@RestController
@RequestMapping("/api/income/clinicsIncome")
public class ClinicsIncomeController {
	
	@Autowired
	private ClinicsIncomeMapper clinicsIncomeMapper;
	
	/**
     * 医院一天门诊收入总和
     */
	
	 @RequestMapping("/countDayClinicsIncomeSum")
	  @ResponseBody
	 public JSONPObject countDayClinicsIncomeSum(HttpServletRequest request,HttpServletResponse response){
		 String callbackFunName=request.getParameter("callbackparams");
		 String sdate =request.getParameter("sdate");
		 String hospital=request.getParameter("hospitalCode");
		 HashMap<String, String> params=new HashMap<String, String>();
		 params.put("curDate",sdate);
		 params.put("hospitalCode",hospital);
		 List<ClinicsIncomeModel> clinics=clinicsIncomeMapper.selectDayClinicsIncomeSum(params);
		 List<Double>sumData=new ArrayList<Double>();
		 for (ClinicsIncomeModel model : clinics) {
			double count=Long.valueOf( (long) model.getFeeCount())/10000.0;
			sumData.add(count);
		}
		 JSONObject json=new JSONObject();
		 json.put("sum",sumData);
		 System.out.println(json.toJSONString());
		 return new JSONPObject(callbackFunName, json);
	 }
	
	                  
	/**
	 *分时统计门诊收入
	 */
	@RequestMapping("/countDayClinicsIncomeByHour")
	@ResponseBody
	public JSONPObject countDayClinicsIncomeByHour(HttpServletRequest request,HttpServletResponse response){
		String callbackFunName=request.getParameter("callbackparamh");
		String sdate=request.getParameter("sdate");
		String hospitalCode=request.getParameter("hospitalCode");
		JSONObject json=new JSONObject();
		int step=2;
		List hours=DateUtil.getHoursByRule(2,7,17);
		int len=hours.size();
		HashMap<String, String> params=new HashMap<String, String>();
		List<Long> list=new ArrayList<Long>();
		for(int i=0;i<len;i++){
			int startHour=(Integer) hours.get(i);
			int endHour=startHour+step-1;
			String startTime=sdate+" "+DateUtil.parseHourToString(startHour)+":00:00";
			String endTime=sdate+" "+DateUtil.parseHourToString(endHour)+":59:59";
			params.put("startTime",startTime);
			params.put("endTime",endTime);
			params.put("hospitalCode", hospitalCode);
			List<ClinicsIncomeModel> income=clinicsIncomeMapper.selectDayClinicsIncomeByHour(params);
			for (ClinicsIncomeModel model : income) {
				float count=0;
				if(model==null){
					count=0;
				}else{
					count=model.getFeeCount();
				}
				list.add((long) count);
			}
		}
		   json.put("count",list);
		   System.out.println(list);
		return new JSONPObject(callbackFunName, json);
	}

	
	/**
	 * 统计当日门诊科室收入比
	 */
		@RequestMapping("/countDayClinicsIncomeByDept")
		@ResponseBody
		public JSONPObject countDayClinicsIncomeByDept(HttpServletRequest request,HttpServletResponse response){
			String callbackFunName =request.getParameter("callbackparamd");//得到js函数名称 
			JSONObject json=new JSONObject();
			String date=request.getParameter("sdate");
			String hospital=request.getParameter("hospitalCode");
			String startTime=date+" 00:00:00";
			String endTime=date+" 23:59:59";
			HashMap<String, String> params=new HashMap<String, String>();
			params.put("startTime",startTime);
			params.put("endTime",endTime);
			params.put("hospitalCode",hospital);
			List<ClinicsIncomeModel> deptList=clinicsIncomeMapper.selectDayClinicsIncomeByDept(params);
			List<NameValueEntity> valueData=new ArrayList<NameValueEntity>();
	    	List<String> nameData=new ArrayList<String>();
	    	List<String> icode=new ArrayList<String>();
	    	for (ClinicsIncomeModel model : deptList) {
				float count=Long.valueOf((long) model.getFeeCount());
				String code=model.getDeptCode();
				String deptname=model.getDeptName();
				NameValueEntity entity=new NameValueEntity();
				entity.setName(deptname);
				entity.setValue((long) count);
				valueData.add(entity);
				nameData.add(deptname);
				icode.add(code);
			}
	    	json.put("name", nameData);
	    	json.put("value", valueData);
	    	json.put("deptcode", icode);
			System.out.println(json.toJSONString());
			return new JSONPObject(callbackFunName, json);
		}
		/**
		 * 统计当日门诊科室收入
		 */
			@RequestMapping("/countDayClinicsIncomeByDeptz")
			@ResponseBody
			public JSONPObject countDayClinicsIncomeByDeptz(HttpServletRequest request,HttpServletResponse response){
				String callbackFunName =request.getParameter("callbackparamt");//得到js函数名称 
				JSONObject json=new JSONObject();
				String date=request.getParameter("sdate");
				String hospital=request.getParameter("hospitalCode");
				String startTime=date+" 00:00:00";
				String endTime=date+" 23:59:59";
				HashMap<String, String> params=new HashMap<String, String>();
				params.put("startTime",startTime);
				params.put("endTime",endTime);
				params.put("hospitalCode",hospital);
				List<ClinicsIncomeModel> deptList=clinicsIncomeMapper.selectDayClinicsIncomeByDeptz(params);
				List<Double> valueData=new ArrayList<Double>();
		    	List<String> nameData=new ArrayList<String>();
		    	List<String> icode=new ArrayList<String>();
		    	for (ClinicsIncomeModel model : deptList) {
					float count=Long.valueOf((long) model.getFeeCount());
					String code=model.getDeptCode();
					String deptname=model.getDeptName();
					valueData.add((double) count);
					nameData.add(deptname);
					icode.add(code);
				}
		    	json.put("name", nameData);
		    	json.put("value", valueData);
		    	json.put("deptcode", icode);
				System.out.println(json.toJSONString());
				return new JSONPObject(callbackFunName, json);
			}
			/**
			 * 统计当日住院科室医生收入
			 */
			@RequestMapping("/countDayClinicsIncomeByDoctor")
			@ResponseBody
			public JSONPObject countDayClinicsIncomeByDoctor(HttpServletRequest request,HttpServletResponse response){
				String callbackFunName =request.getParameter("callbackparamr");//得到js函数名称 
				JSONObject json=new JSONObject();
				String date=request.getParameter("sdate");
				String hospital=request.getParameter("hospitalCode");
				String deptcode=request.getParameter("deptcode");
				String startTime=date+" 00:00:00";
				String endTime=date+" 23:59:59";
				HashMap<String, String> params=new HashMap<String, String>();
				params.put("startTime",startTime);
				params.put("endTime",endTime);
				params.put("hospitalCode",hospital);
				params.put("deptcode", deptcode);
				List<ClinicsIncomeModel> deptList=clinicsIncomeMapper.selectDayClinicsIncomeByDoctor(params);
				List<Double> valueData=new ArrayList<Double>();
				List<String> nameData=new ArrayList<String>();
				for (ClinicsIncomeModel model : deptList) {
					float count=Long.valueOf((long) model.getFeeCount());
					String deptname=model.getDoctorName();
					valueData.add((double) count);
					nameData.add(deptname);
				}
				json.put("name", nameData);
				json.put("value", valueData);
				System.out.println(json.toJSONString());
				
				return new JSONPObject(callbackFunName, json);
			}		

}
