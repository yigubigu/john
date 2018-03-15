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
import com.zmap.zmap.hospitalize.mapper.HospitalizeNumberMapper;
import com.zmap.zmap.hospitalize.model.HospitalizeNumberModel;

@RestController
@RequestMapping("/api/hospitalize/hospitalizeNumber")
public class HospitalizeNumberController {
	
	@Autowired
	private HospitalizeNumberMapper hospitalizeNumberMapper;
	
	/**
     * 医院当日住院人数
     */
	
	 @RequestMapping("/countDayhospitalizeNumberSum")
	 @ResponseBody
	 public JSONPObject countDayhospitalizeNumberSum(HttpServletRequest request,HttpServletResponse response){
		 String callbackFunName=request.getParameter("callbackparams");
		 String sdate =request.getParameter("sdate");
		 String hospital=request.getParameter("hospitalCode");
		 HashMap<String, String> params=new HashMap<String, String>();
		 params.put("curDate",sdate);
		 params.put("hospitalCode",hospital);
		 List<HospitalizeNumberModel> hospitalizeNum = hospitalizeNumberMapper.selectDayHospitalizeNumberSum(params);
		 List<Double>sumData=new ArrayList<Double>();
		 for (HospitalizeNumberModel model : hospitalizeNum) {
			 double count=0;
			if(model==null){
				count=0;
			}else{
				count=Long.valueOf((long) model.getHospitalizeNum());
			}
			
			sumData.add(count);
		}
		 JSONObject json=new JSONObject();
		 json.put("sum",sumData);
		 System.out.println(sumData);
		 System.out.println(json.toJSONString());
		 return new JSONPObject(callbackFunName, json);
	 }
	 	
	 /**
	  * 科室当日住院人数
	  * 
	  */
	    @RequestMapping("/hospitalizeNumberByDepts")
	    @ResponseBody
	    public JSONPObject selectHospitalizeNumByDept(HttpServletRequest request,HttpServletResponse response){
	    	String callbackFunName=request.getParameter("callbackparam");
	    	//String code=request.getParameter("code");
	    	//HashMap<String, String> map=new HashMap<String, String>();
	    	String hospital=request.getParameter("hospitalCode");
	    	String date=request.getParameter("sdate");
			String startTime=date+" 00:00:00";
			String endTime=date+" 23:59:59";
			HashMap<String, String> params=new HashMap<String, String>();
			params.put("startTime",startTime);
			params.put("endTime",endTime);
			params.put("hospitalCode", hospital);
	    	List<HospitalizeNumberModel> HNumberByDept=hospitalizeNumberMapper.selectHospitalizeNumByDept(params);
	    	List<Float> valueData=new ArrayList<Float>();
	    	List<String> nameData=new ArrayList<String>();
	    	List<String> icode=new ArrayList<String>();
	    	//List<String>  idData=new ArrayList<String>();
	    	for (HospitalizeNumberModel model:HNumberByDept) {
	    		String deptName=model.getDeptName();
	    		float num = model.getHospitalizeNum();
	    		String codeData=model.getDeptCode();
	    		nameData.add(deptName);
	    		valueData.add(num);
	    		icode.add(codeData);
			}
	    	JSONObject json=new JSONObject();
	       json.put("dcode", icode);
	    	json.put("name", nameData);
	    	json.put("value", valueData);
	    	System.out.println(json.toJSONString());
	    	return new JSONPObject(callbackFunName, json);
	    }
	    
		 /**
		  * 医生当日住院人数
		  * 
		  */
	    @RequestMapping("/hospitalizeNumberByDoctor")
	    @ResponseBody
	    public JSONPObject selectHospitalizeNumByDoctor(HttpServletRequest request,HttpServletResponse response){
	    	String callbackFunName=request.getParameter("callbackparamd");
	    	
	    	//String code=request.getParameter("code");
	    	String hospital=request.getParameter("hospitalCode");
	    	String deptcode=request.getParameter("deptcode");
	    	String date=request.getParameter("sdate");
			String startTime=date+" 00:00:00";
			String endTime=date+" 23:59:59";
	    	HashMap<String, String> params=new HashMap<String, String>();
			params.put("startTime",startTime);
			params.put("endTime",endTime);
			params.put("deptcode", deptcode);
			params.put("hospitalCode", hospital);
	    	List<HospitalizeNumberModel> HNumberByDoctor=hospitalizeNumberMapper.selectHospitalizeNumByDoctor(params);
	    	List<Float> valueData=new ArrayList<Float>();
	    	List<String> nameData=new ArrayList<String>();
	    	for (HospitalizeNumberModel model:HNumberByDoctor) {
				String doctorName=model.getDoctorName();
				float num = model.getHospitalizeNum();
				nameData.add(doctorName);
				valueData.add(num);
			}
	    	JSONObject json=new JSONObject();
	    	json.put("name", nameData);
	    	json.put("value", valueData);
	    	System.out.println(json.toJSONString());
	    	return new JSONPObject(callbackFunName, json);
	    }

}
