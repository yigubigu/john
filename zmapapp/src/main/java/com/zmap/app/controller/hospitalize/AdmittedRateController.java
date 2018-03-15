package com.zmap.app.controller.hospitalize;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.mapping.ResultMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.zmap.zmap.clinic.mapper.ZmapClinicCM1SyncMapper;
import com.zmap.zmap.clinic.model.ZmapClinicCM1SyncModel;
import com.zmap.zmap.framework.base.mapper.ZmapRDeptMapper;
import com.zmap.zmap.framework.base.mapper.ZmapRDoctorMapper;
import com.zmap.zmap.framework.base.model.ZmapRDeptModel;
import com.zmap.zmap.framework.base.model.ZmapRDoctorModel;
import com.zmap.zmap.framework.hospitalize.mapper.AdmittedRateMapper;
import com.zmap.zmap.framework.hospitalize.model.AdmittedRateModel;



@RestController
@RequestMapping("/api/ratio/admittedrate")
public class AdmittedRateController {

	@Autowired
	private AdmittedRateMapper admittedRateMapper;
	
	@Autowired
	private ZmapClinicCM1SyncMapper zmapClinicCM1SyncMapper;
	
	@Autowired
	private ZmapRDeptMapper zmapRDeptMapper;
	
	@Autowired
	private ZmapRDoctorMapper zmapRDoctorSyncMapper;
	
	private int admitted;
	private int clinicSum;
	
	/**
	 * 统计当天收住率
	 */
	@RequestMapping("/countDayAdmittedRate")
	@ResponseBody
	public JSONPObject countDayAdmittedRate(HttpServletRequest request,HttpServletResponse response){
		String callbackFunName = request.getParameter("callbackparams");
		String curdate = request.getParameter("sdate");
		HashMap<String, String> params = new HashMap<String, String>();
		String sdate =request.getParameter("sdate");
		params.put("curDate",curdate);
		String hospital=request.getParameter("hospitalCode");
		String startTime=sdate+" 00:00:00";
		String endTime=sdate+" 23:59:59";
		params.put("startTime",startTime);
		params.put("endTime",endTime);
		params.put("hospitalCode",hospital);
		List<AdmittedRateModel> result1 = admittedRateMapper.selectDayAdmittedRate(params);
		List<ZmapClinicCM1SyncModel> result2 = zmapClinicCM1SyncMapper.selectSumByDayClinics(params);
		for (AdmittedRateModel model1 : result1) {
			
			admitted = Integer.valueOf(model1.getAdmittedNum());
		}
		for(ZmapClinicCM1SyncModel model2:result2){
			clinicSum = Integer.valueOf(model2.getClinicNum());
		}
		float ratio = (float)admitted/(float)clinicSum;
		JSONObject json = new JSONObject();
		DecimalFormat format=new DecimalFormat("#.##%");
		json.put("ratio", format.format(ratio));
		System.out.println(json.toJSONString());
		return new JSONPObject(callbackFunName, json);
	}
	
	/**
	 * 
	 * @param 统计科室收住率
	 * @param response
	 * @return
	 */
	@RequestMapping("/countDayAdmittedRateByDept")
	@ResponseBody
	public JSONPObject countDayAdmittedRateByDept(HttpServletRequest request,HttpServletResponse response){
		String callbackFunName = request.getParameter("callbackparamd");
		String curdate = request.getParameter("sdate");
		HashMap paramd = new HashMap();
		String sdate =request.getParameter("sdate");
		paramd.put("curDate",curdate);
		String hospital=request.getParameter("hospitalCode");
		String startTime=sdate+" 00:00:00";
		String endTime=sdate+" 23:59:59";
		paramd.put("startTime",startTime);
		paramd.put("endTime",endTime);
		paramd.put("hospitalCode",hospital);
		List<AdmittedRateModel> result1 = admittedRateMapper.selectAdmittedRateByDept(paramd);
		List<HashMap<String,String>> result2 = zmapClinicCM1SyncMapper.selectClinicsSumByDept(paramd);
		List<String> codes = new ArrayList<String>();
		HashMap<Long, Long> kvs2=new HashMap<Long, Long>();
		HashMap<String, Float> kvs= new HashMap<String, Float>();
		
		Long deptCount = null;
		Long admittedCount = null;
		for(HashMap<String, String> m:result2){
			deptCount = Long.valueOf(String.valueOf(m.get("deptCount")));
			kvs2.put(Long.valueOf(String.valueOf(m.get("deptCode"))),deptCount);
			codes.add(String.valueOf(m.get("deptCode")));
		}

		for (AdmittedRateModel model : result1) {
			if(model!=null){
				admittedCount = Long.valueOf(Integer.valueOf(model.getAdmittedNumd()));
				System.out.println(admittedCount);
				String deptCode = String.valueOf(model.getRespDept());
				codes.add(deptCode);
				float ratio = (float)admittedCount/(float)deptCount;
				kvs.put(deptCode, ratio);
			}
			
		}
		paramd.put("codes", codes);
		System.out.println(kvs.size());
		List<Float> valueData=new ArrayList<Float>();
		for (int i = 0; i < codes.size(); i++) {
			Float ratioCount=kvs.get(codes.get(i));
			valueData.add(ratioCount);
		}
		List<ZmapRDeptModel> depts = zmapRDeptMapper.selectDetpsByDeptCodes(paramd);
		
		List<String>  idData=new ArrayList<String>();
		List<String> nameData=new ArrayList<String>();
		
		for(ZmapRDeptModel model:depts){
			String deptCode=model.getDeptCode();
			String deptName=model.getDeptName();
			
			
//			DecimalFormat format=new DecimalFormat("#.##%");
//			format.format(new Float(ratioCount));
			nameData.add(deptName);
			
			idData.add(deptCode);
		}
		JSONObject json = new JSONObject();
		
		json.put("name",nameData);
		json.put("value",valueData);
		json.put("deptCodes", idData);
		System.out.println(json.toJSONString());
		return new JSONPObject(callbackFunName, json);
		
	}
	
	/*
	 * 统计医生收住率
	 */
	@RequestMapping("/countDayAdmittedRateByDoctor")
	@ResponseBody
	public JSONPObject countDayAdmittedRateByDoctor(HttpServletRequest request,HttpServletResponse response) {
		String callbackFunName = request.getParameter("callbackparamdd");
		String hospitalCode = request.getParameter("hospitalCode");
		String deptCode = request.getParameter("deptcode");
		String sdate = request.getParameter("sdate");
		HashMap paramdd = new HashMap();
		String startTime=sdate+" 00:00:00";
		String endTime=sdate+" 23:59:59";
		paramdd.put("startTime",startTime);
		paramdd.put("endTime",endTime);
		paramdd.put("hospitalCode", hospitalCode);
		paramdd.put("curDate", sdate);
		paramdd.put("deptCode",deptCode);

		List<HashMap<String,String>> result1 = zmapClinicCM1SyncMapper.selectClinicsSumByDeptDoctor(paramdd);
		List<AdmittedRateModel> result2 = admittedRateMapper.selectAdmittedRateByDoctor(paramdd);
		List<String> codes = new ArrayList<String>();
		HashMap<String, Float> kvs= new HashMap<String, Float>();
		
		Float doctorCount = 0.00f;
		Float admittedCount = 0.00f;
		for(HashMap<String, String> m:result1){
			doctorCount = Float.valueOf(String.valueOf(m.get("doctorCount")));
//			kvs.put(String.valueOf(m.get("doctorCode")),Float.valueOf(doctorCount));
//			codes.add(String.valueOf(m.get("doctorCode")));
			System.out.println(doctorCount);
		}
		
		for (AdmittedRateModel model : result2) {
			admittedCount =Float.valueOf(Integer.valueOf(model.getAdmittedNumdd()));
			String doctorCode = model.getDoctorCode();
			codes.add(doctorCode);
			System.out.println(doctorCode);

			float admittedRatio = admittedCount/doctorCount;
			kvs.put(doctorCode, admittedRatio);
		}
		
		paramdd.put("codes", codes);
		List<ZmapRDoctorModel> doctors = zmapRDoctorSyncMapper.selectDoctorsByDoctorCodes(paramdd);
		List<String> codeData = new ArrayList<String>();
		List<String> nameData = new ArrayList<String>();
		List<Float> valueData = new ArrayList<Float>();
 		for (ZmapRDoctorModel model : doctors) {
			String doctorCode = model.getDoctorCode();
			String doctorName = model.getDoctorName();
			Float admittedRatio = kvs.get(doctorCode);
			
			codeData.add(doctorCode);
			nameData.add(doctorName);
			valueData.add(admittedRatio);
		}
 		
 		JSONObject json = new JSONObject();
 		json.put("doctorcode", codeData);
 		json.put("name", nameData);
 		json.put("value",valueData);
		System.out.println(json.toJSONString());
		return new JSONPObject(callbackFunName, json);	
	}

}
