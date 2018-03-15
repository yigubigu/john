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
import com.zmap.zmap.framework.income.mapper.HospitalizeIncomeMapper;
import com.zmap.zmap.framework.income.model.HospitalizeIncomeModel;
import com.zmap.zmap.framework.model.NameValueEntity;


@RestController
@RequestMapping("/api/income/hospitalize")
public class HospitalizeController {

	@Autowired
	private HospitalizeIncomeMapper hospitalizeIncomeMapper;
	
	/**
     * 医院一天住院收入总和
     */
	
	 @RequestMapping("/countDayHospitalizeIncomeSum")
	  @ResponseBody
	 public JSONPObject countDayHospitalizeIncomeSum(HttpServletRequest request,HttpServletResponse response){
		 String callbackFunName=request.getParameter("callbackparams");
		 String sdate =request.getParameter("sdate");
		 String hospital=request.getParameter("hospitalCode");
		 HashMap<String, String> params=new HashMap<String, String>();
		 params.put("curDate",sdate);
		 params.put("hospitalCode",hospital);
		 List<HospitalizeIncomeModel> clinics=hospitalizeIncomeMapper.selectDayHospitalIncomeSum(params);
		 List<Double>sumData=new ArrayList<Double>();
		 for (HospitalizeIncomeModel model : clinics) {
			double count=Long.valueOf( (long) model.getFeeCount())/10000.0;
			sumData.add(count);
		}
		 JSONObject json=new JSONObject();
		 json.put("sum",sumData);
		 System.out.println(json.toJSONString());
		 return new JSONPObject(callbackFunName, json);
	 }
	
	/**
	 * 统计分时住院收入
	 */
	@RequestMapping("/countDayHospitalByHour")
	@ResponseBody
	public JSONPObject countHourHospitalize(HttpServletRequest request,HttpServletResponse response){
		String callbackFunName =request.getParameter("callbackparamh");//得到js函数名称 
		JSONObject json=new JSONObject();
		String sdate=request.getParameter("sdate");
		String hospital=request.getParameter("hospitalCode");
		int step=2;
		List hours=DateUtil.getHoursByRule(2,7,17);
		int len=hours.size();
		//List<Integer> vals=new ArrayList<Integer>();
		HashMap<String, String> params=new HashMap<String, String>();
		List<Long> list=new ArrayList<Long>();
		for(int i=0;i<len;i++){
			int startHour=(Integer) hours.get(i);
			int endHour=startHour+step-1;
			String startTime=sdate+" "+DateUtil.parseHourToString(startHour)+":00:00";
			String endTime=sdate+" "+DateUtil.parseHourToString(endHour)+":59:59";
			params.put("startTime",startTime);
			params.put("endTime",endTime);
			params.put("hospitalCode", hospital);
			List<HospitalizeIncomeModel> income=hospitalizeIncomeMapper.selectDayHospitalIncomeByHour(params);
			for (HospitalizeIncomeModel model : income) {
				float count=0;
				if(model==null){
					count=0;
				}else{
					count=model.getFeeCount();
				}
				list.add(Long.valueOf((long) count));
			}
		}
		json.put("count",list);
		System.out.println(list);
		return new JSONPObject(callbackFunName, json);
	}
/**
 * 统计当日住院科室收入比
 */
	@RequestMapping("/countDayHospitalByDept")
	@ResponseBody
	public JSONPObject countDayHospitalByDept(HttpServletRequest request,HttpServletResponse response){
		String callbackFunName =request.getParameter("callbackparamd");//得到js函数名称 
		JSONObject json=new JSONObject();
		String date=request.getParameter("sdate");
		String hospital=request.getParameter("hospitalCode");
		//String deptcode=request.getParameter("deptcode");
		String startTime=date+" 00:00:00";
		String endTime=date+" 23:59:59";
		HashMap<String, String> params=new HashMap<String, String>();
		params.put("startTime",startTime);
		params.put("endTime",endTime);
		params.put("hospitalCode",hospital);
		//params.put("deptcode", deptcode);
		List<HospitalizeIncomeModel> deptList=hospitalizeIncomeMapper.selectHospitalSumByDept(params);
		List<NameValueEntity> valueData=new ArrayList<NameValueEntity>();
    	List<String> nameData=new ArrayList<String>();
    	List<String> icode=new ArrayList<String>();
    	for (HospitalizeIncomeModel model : deptList) {
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
	 * 统计当日住院科室收入
	 */
		@RequestMapping("/countDayHospitalIncomeByDept")
		@ResponseBody
		public JSONPObject countDayHospitalIncomeByDept(HttpServletRequest request,HttpServletResponse response){
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
			List<HospitalizeIncomeModel> deptList=hospitalizeIncomeMapper.selectDayHospitalIncomeByDept(params);
			List<Double> valueData=new ArrayList<Double>();
	    	List<String> nameData=new ArrayList<String>();
	    	List<String> icode=new ArrayList<String>();
	    	for (HospitalizeIncomeModel model : deptList) {
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
		@RequestMapping("/countDayHospitalIncomeByDoctor")
		@ResponseBody
		public JSONPObject countDayHospitalIncomeByDoctor(HttpServletRequest request,HttpServletResponse response){
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
			List<HospitalizeIncomeModel> deptList=hospitalizeIncomeMapper.selectDayHospitalIncomeByDoctor(params);
			List<Double> valueData=new ArrayList<Double>();
			List<String> nameData=new ArrayList<String>();
			for (HospitalizeIncomeModel model : deptList) {
				float count=Long.valueOf((long) model.getFeeCount());
				String deptname=model.getDoctorName();
				String deptCode=model.getDoctorCode();
				valueData.add((double) count);
				nameData.add(deptname);
			}
			json.put("name", nameData);
			json.put("value", valueData);
			System.out.println(json.toJSONString());
			
			return new JSONPObject(callbackFunName, json);
		}
	
}
