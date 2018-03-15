package com.zmap.app.controller.clinics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.zmap.zmap.clinic.mapper.ServiceNumberMapper;
import com.zmap.zmap.clinic.mapper.ServiceNumberMapper;
import com.zmap.zmap.clinic.model.ServiceNumberModel;
import com.zmap.zmap.framework.base.mapper.ZmapRDeptMapper;
import com.zmap.zmap.framework.base.model.ZmapRDeptModel;
import com.zmap.zmap.framework.base.model.ZmapRDoctorModel;
import com.zmap.zmap.framework.base.mapper.ZmapRDoctorMapper;


@RestController
@RequestMapping("/api/clinics/serviceNumber")
public class ServiceNumberController {
	
	@Autowired
	private ServiceNumberMapper serviceNumberMapper;
	
	@Autowired
	private ZmapRDeptMapper zmapRDeptMapper;
	
	@Autowired
	private ZmapRDoctorMapper zmapRDoctorSyncMapper;
	
	/**
	 * 统计当月服务人次
	 */
	@RequestMapping("/countMonthServiceNumberSum")
	@ResponseBody
	public JSONPObject countMonthServiceNumberSum(HttpServletRequest request,HttpServletResponse response){
	
		String callbackFunName =request.getParameter("callbackparam");//得到js函数名称 
		String sdate =request.getParameter("sdate");
		String hospital=request.getParameter("hospitalCode");
		JSONObject json=new JSONObject();
		HashMap params=new HashMap();
		params.put("curDate",sdate);
		params.put("hospitalCode",hospital);
		int sum = 0;
		List<HashMap<String,String>> resultMap=serviceNumberMapper.selectMonthServiceNumberSum(params);
		for(HashMap<String,String> result:resultMap){
			sum=Integer.valueOf(String.valueOf(result.get("serviceCount")));
		}
		json.put("sum", sum);
		System.out.println(json.toJSONString());
		return new JSONPObject(callbackFunName, json);
	}
	
	/**
	 * 统计当月门诊:住院服务人数比例
	 */
	@RequestMapping("/countMonthServiceNumberByCategory")
	@ResponseBody
	public JSONPObject countMonthServiceNumber(HttpServletRequest request,HttpServletResponse response){
		String callbackFunName =request.getParameter("callbackparams");//得到js函数名称 
		String sdate =request.getParameter("sdate");
		String hospital=request.getParameter("hospitalCode");
		JSONObject json=new JSONObject();
		HashMap params=new HashMap();
		params.put("curDate",sdate);
		params.put("hospitalCode",hospital);
		List<Double> datas=new ArrayList<Double>();
		List<HashMap<String,String>> resultMap=serviceNumberMapper.selectMonthServiceNumberSumByCategory(params);
		List<HashMap<String,String>> resultData=new ArrayList<HashMap<String,String>>();
		for(int i=0;i<resultMap.size();i++){
			if(i==0){
				HashMap<String,String> map=new HashMap<String, String>();
				map.put("name","门诊");
				map.put("value",String.valueOf(resultMap.get(0).get("serviceCount")));
				resultData.add(map);
			}
			if(i==1){
				HashMap<String,String> map=new HashMap<String, String>();
				map.put("name","住院");
				map.put("value",String.valueOf(resultMap.get(1).get("serviceCount")));
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
	 * 统计当月各科室服务人次
	 */
	@RequestMapping("/countMonthServiceNumberByDept")
	@ResponseBody
	public JSONPObject countMonthServiceNumberByDept(HttpServletRequest request,HttpServletResponse response){
		String callbackFunName =request.getParameter("callbackparamd");//得到js函数名称 
		String sdate =request.getParameter("sdate");
		String hospital=request.getParameter("hospitalCode");
		JSONObject json=new JSONObject();
		HashMap params=new HashMap();
		params.put("curDate",sdate);
		params.put("hospitalCode",hospital);
		List<HashMap<String,String>> result=serviceNumberMapper.selectMonthServiceNumberByDept(params);
		HashMap<String, Long> kvs=new HashMap<String, Long>();
		List<Long> codes=new ArrayList<Long>(); 
		for(Map<String,String> m:result){
			String dcode=String.valueOf(m.get("deptCode"));
			Long dCount=Long.valueOf(String.valueOf(m.get("serviceCount")));
			if(kvs.containsKey(dcode)){
				kvs.put(dcode, kvs.get(dcode)+dCount);
			}else{
					kvs.put(dcode,dCount);
					codes.add(Long.valueOf(String.valueOf(m.get("deptCode"))));
				}
		
		}
		params.put("codes", codes);
		System.out.println(codes.size());
		List<ZmapRDeptModel> depts=zmapRDeptMapper.selectDetpsByDeptCodes(params);
		List<Long> valueData=new ArrayList<Long>();
		List<String>  idData=new ArrayList<String>();
		List<String> nameData=new ArrayList<String>();
		for(ZmapRDeptModel model:depts){
			String deptCode=model.getDeptCode();
			String deptName=model.getDeptName();
			Long sum=kvs.get(deptCode);
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
	 * 科室医生服务人数
	 */
	@RequestMapping("/countMonthServiceNumberByDoctor")
	@ResponseBody
	public JSONPObject countMonthServiceNumberByDoctor(HttpServletRequest request,HttpServletResponse response){
		String code=request.getParameter("deptCode");
		String callbackFunName =request.getParameter("callbackparamt");//得到js函数名称 
		String hospital=request.getParameter("hospitalCode");
		JSONObject json=new JSONObject();
		String date=request.getParameter("sdate");
		HashMap params=new HashMap();
		params.put("curDate",date);
		params.put("deptCode",code);
		params.put("hospitalCode",hospital);
		List<ServiceNumberModel> doctorList=serviceNumberMapper.selectMonthServiceNumberByDoctor(params);
		HashMap<String, Integer> kvs=new HashMap<String, Integer>();
		List<String> codes=new ArrayList<String>(); 
		List<Integer> valueData=new ArrayList<Integer>();
		List<String> nameData=new ArrayList<String>();
		//if(doctorList!=null){
			for(ServiceNumberModel m:doctorList){
				if(m!=null){
					String docCode=m.getDoctorCode();
					int docCount=m.getServiceCount();
					if(kvs.containsKey(docCode)){
						kvs.put(docCode,kvs.get(docCode)+docCount);
					}else{
						kvs.put(docCode,docCount);
						codes.add(docCode);
//						nameData.add(String.valueOf(docCode));
					}
				}
				
			}
			params.put("codes", codes);
			System.out.println(codes.size());
			System.out.println(kvs);
			List<ZmapRDoctorModel> doctors=zmapRDoctorSyncMapper.selectDoctorsByDoctorCodes(params);
			for(ZmapRDoctorModel model:doctors){
				String docName=model.getDoctorName();
				System.out.println(docName);
					nameData.add(docName);
				
			}
			for (int i = 0; i < codes.size(); i++) {
				int sum=kvs.get(codes.get(i));
				valueData.add(sum);
				if(codes.get(i)=="null"){
					nameData.add("其他");
				}
			}
		json.put("name",nameData);
		json.put("value", valueData);
		System.out.println(json.toJSONString());
		return new JSONPObject(callbackFunName, json);
	}
	
}
