package com.zmap.app.controller.clinics;


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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.zmap.common.DateUtil;
import com.zmap.zmap.clinic.mapper.ZmapClinicCM1SyncMapper;
import com.zmap.zmap.clinic.model.ZmapClinicCM1SyncModel;
import com.zmap.zmap.framework.base.mapper.ZmapRDeptMapper;
import com.zmap.zmap.framework.base.mapper.ZmapRDoctorMapper;
import com.zmap.zmap.framework.base.model.ZmapRDeptModel;
import com.zmap.zmap.framework.base.model.ZmapRDoctorModel;
import com.zmap.zmap.framework.model.NameValueEntity;


@RestController
@RequestMapping("/api/clinics")
public class ClinicsController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@Autowired
	private ZmapClinicCM1SyncMapper zmapClinicCM1SyncMapper;
	
	@Autowired
	private ZmapRDeptMapper zmapRDeptMapper;
	
	@Autowired
	private ZmapRDoctorMapper zmapRDoctorSyncMapper;
		
	
	/**
	 * 统计当天日期的门诊人数
	 */
	@RequestMapping("/countDayClinics")
	@ResponseBody
	public JSONPObject countDayClinics(HttpServletRequest request,HttpServletResponse response){
		String callbackFunName =request.getParameter("callbackparam");//得到js函数名称 
		String sdate =request.getParameter("sdate");
		JSONObject json=new JSONObject();
		String hospital=request.getParameter("hospitalCode");
		String startTime=sdate+" 00:00:00";
		String endTime=sdate+" 23:59:59";
		HashMap params=new HashMap();
		params.put("startTime",startTime);
		params.put("endTime",endTime);
		params.put("hospitalCode",hospital);
		List<ZmapClinicCM1SyncModel> result=zmapClinicCM1SyncMapper.selectSumByDayClinics(params);
		int count=0;
		for (ZmapClinicCM1SyncModel zmapClinicCM1SyncModel : result) {
			 count=zmapClinicCM1SyncModel.getClinicNum();
		}
//		int count = zmapClinicCM1SyncMapper.selectSumByDayClinics(params).size();
		json.put("count",count);
		return new JSONPObject(callbackFunName, json);
	}
	
	/**
	 * 统计分时挂号门诊人数
	 */
	@RequestMapping("/countDayClinicsByHour")
	@ResponseBody
	public JSONPObject countHourClinics(HttpServletRequest request,HttpServletResponse response){
		String callbackFunName =request.getParameter("callbackparamh");//得到js函数名称 
		JSONObject json=new JSONObject();
		String sdate=request.getParameter("sdate");
		String hospital=request.getParameter("hospitalCode");
		int step=2;
		List hours=DateUtil.getHoursByRule(2,7,17);
		int len=hours.size();
		List<Integer> vals=new ArrayList<Integer>();
		for(int i=0;i<len;i++){
			int startHour=(Integer) hours.get(i);
			int endHour=startHour+step-1;
			String startTime=sdate+" "+DateUtil.parseHourToString(startHour)+":00:00";
			String endTime=sdate+" "+DateUtil.parseHourToString(endHour)+":59:59";
			HashMap params=new HashMap();
			params.put("startTime",startTime);
			params.put("endTime",endTime);
			params.put("hospitalCode", hospital);
//			int count=zmapClinicCM1SyncMapper.selectSumByDayClinics(params).size();
			List<ZmapClinicCM1SyncModel> result=zmapClinicCM1SyncMapper.selectSumByDayClinics(params);
			int count=0;
			for (ZmapClinicCM1SyncModel zmapClinicCM1SyncModel : result) {
				 count=zmapClinicCM1SyncModel.getClinicNum();
			}
			vals.add(count);
			
		}
		json.put("count",JSON.toJSONString(vals));
		System.out.println(JSON.toJSONString(vals));
		return new JSONPObject(callbackFunName, json);
		
	
	}
	
	/**
	 * 统计科室挂号门诊人数
	 */
	@RequestMapping("/countDayClinicsByDept")
	@ResponseBody
	public JSONPObject countDayClinicsByDept(HttpServletRequest request,HttpServletResponse response){
		String callbackFunName =request.getParameter("callbackparamd");//得到js函数名称 
		JSONObject json=new JSONObject();
		String date=request.getParameter("sdate");
		String hospital=request.getParameter("hospitalCode");
		String startTime=date+" 00:00:00";
		String endTime=date+" 23:59:59";
		HashMap params=new HashMap();
		params.put("startTime",startTime);
		params.put("endTime",endTime);
		params.put("hospitalCode",hospital);
		List<HashMap<String,String>> deptList = zmapClinicCM1SyncMapper.selectClinicsSumByDept(params);

		HashMap<Long, Long> kvs=new HashMap<Long, Long>();
		List<String> codes=new ArrayList<String>(); 
		for(HashMap<String,String> m:deptList){
			kvs.put(Long.valueOf(String.valueOf(m.get("deptCode"))),Long.valueOf(String.valueOf(m.get("deptCount"))));
			codes.add(String.valueOf(m.get("deptCode")));
		}
		params.put("codes",codes);
		List<ZmapRDeptModel> depts=zmapRDeptMapper.selectDetpsByDeptCodes(params);
		List<NameValueEntity> valueData=new ArrayList<NameValueEntity>();
		List<String>  idData=new ArrayList<String>();
		List<String> nameData=new ArrayList<String>();
		for(ZmapRDeptModel model:depts){
			String deptCode=model.getDeptCode();
			String deptName=model.getDeptName();
			Long count=kvs.get(Long.valueOf(String.valueOf(deptCode)));
			nameData.add(deptName);
			NameValueEntity entity=new NameValueEntity();
			NameValueEntity iden=new NameValueEntity();
			entity.setName(deptName);
			entity.setValue(count);
			valueData.add(entity);
			idData.add(deptCode);
		}
		json.put("name",nameData);
		json.put("value", valueData);
		json.put("deptCodes", idData);
		System.out.println(json.toJSONString());
		return new JSONPObject(callbackFunName, json);
		
	
	}
	
	
	/**
	 * 科室医生门诊人数
	 */
	@RequestMapping("/countDayDoctorClinicsByDept")
	@ResponseBody
	public JSONPObject countDayDoctorClinicsByDept(HttpServletRequest request,HttpServletResponse response){
		String code=request.getParameter("code");
		String callbackFunName =request.getParameter("callbackparamt");//得到js函数名称 
		JSONObject json=new JSONObject();
		String date=request.getParameter("sdate");
		String hospital=request.getParameter("hospitalCode");
		String startTime=date+" 00:00:00";
		String endTime=date+" 23:59:59";
		HashMap params=new HashMap();
		params.put("startTime",startTime);
		params.put("endTime",endTime);
		params.put("deptCode",code);
		params.put("hospitalCode",hospital);
		List<HashMap<String,String>> deptList=zmapClinicCM1SyncMapper.selectClinicsSumByDeptDoctor(params);
		HashMap<Long, Long> kvs=new HashMap<Long, Long>();
		List<String> codes=new ArrayList<String>(); 
		for(HashMap<String,String> m:deptList){
			kvs.put(Long.valueOf(String.valueOf(m.get("doctorCode"))),Long.valueOf(String.valueOf(m.get("doctorCount"))));
			codes.add(String.valueOf(m.get("doctorCode")));
		}
		params.put("codes", codes);
		List<ZmapRDoctorModel> doctors=zmapRDoctorSyncMapper.selectDoctorsByDoctorCodes(params);
		List<Long> valueData=new ArrayList<Long>();
		List<String> nameData=new ArrayList<String>();
		for(ZmapRDoctorModel model:doctors){
			String docName=model.getDoctorName();
			Long count=kvs.get(Long.valueOf(model.getDoctorCode()));
			nameData.add(docName);
			valueData.add(count);
		}
		json.put("name",nameData);
		json.put("value", valueData);
		System.out.println(json.toJSONString());
		return new JSONPObject(callbackFunName, json);
		
	
	}
	

}
