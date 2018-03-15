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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.zmap.zmap.framework.base.mapper.ZmapRDeptMapper;
import com.zmap.zmap.framework.base.model.ZmapRDeptModel;
import com.zmap.zmap.hospitalize.mapper.HospitalizeHighOwePersonMapper;
import com.zmap.zmap.hospitalize.model.HospitalizeHighOwePersonModel;


@RestController
@RequestMapping("/api/service/hospitalizeHighOwe")
public class HospitalizeHighOwePersonController {

	@Autowired
	private HospitalizeHighOwePersonMapper hospitalizeHighOwePersonMapper;
	@Autowired
	private ZmapRDeptMapper zmapRdeptMapper;
	float oweFee;
	float preFee ;
	private int sum;
	
	@RequestMapping("/sumPersonTime")
	@ResponseBody
	public JSONPObject sumPersonTime (HttpServletRequest request,HttpServletResponse response){
		String callbackFunName = request.getParameter("callbackparams");

		String hospitalCode = request.getParameter("hospitalCode");
		HashMap params = new HashMap();
		params.put("hospitalCode", hospitalCode);
		List<HospitalizeHighOwePersonModel> preModel = hospitalizeHighOwePersonMapper.selectSickmanPrepayFeeCount(params);
		List<String> sickmanCodes = new ArrayList<String>();
		HashMap<String, Float> kvs = new HashMap<String, Float>();
		for (HospitalizeHighOwePersonModel model : preModel) {
			if(model != null){
			String sickmanCode = model.getSickmanCode();
			params.put("sickmanCode", sickmanCode);
			preFee = Float.valueOf(model.getPrepayFeeCount());
			List<HospitalizeHighOwePersonModel> oweModel = hospitalizeHighOwePersonMapper.selectSickmanOweFeeCount(params);
			for (HospitalizeHighOwePersonModel model2 : oweModel) {
				if(model2 != null){
				   String sickmanCode2 = model2.getSickmanCode();
				   oweFee = Float.valueOf(model2.getOweFeeCount());
				   float count = oweFee - preFee;
				   if( count >= 5000){
					  sickmanCodes.add(sickmanCode2);
					  kvs.put(sickmanCode2, count);
					  }
				   }
				}
			}
		}
		sum = sickmanCodes.size();
		JSONObject json = new JSONObject();
		json.put("sum", sum);
		System.out.println(json.toJSONString());
		return new JSONPObject(callbackFunName, json);
	}
	
	@RequestMapping("/FeeCountByDept")
	@ResponseBody
	public JSONPObject hospitalizeHighOweFeeCountByDept(HttpServletRequest request,HttpServletResponse response) {
		String callbackFunName = request.getParameter("callbackparamf");

		String hospitalCode = request.getParameter("hospitalCode");
		HashMap paramf = new HashMap();
		paramf.put("hospitalCode", hospitalCode);
		List<HospitalizeHighOwePersonModel> preModel = hospitalizeHighOwePersonMapper.selectSickmanPrepayFeeCount(paramf);
		List<String> sickmanCodes = new ArrayList<String>();
		HashMap<String, Float> kvs = new HashMap<String, Float>();
		for (HospitalizeHighOwePersonModel model : preModel) {
			if(model != null){
			String sickmanCode = model.getSickmanCode();
			paramf.put("sickmanCode", sickmanCode);
			preFee = Float.valueOf(model.getPrepayFeeCount());
			List<HospitalizeHighOwePersonModel> oweModel = hospitalizeHighOwePersonMapper.selectSickmanOweFeeCount(paramf);
			for (HospitalizeHighOwePersonModel model2 : oweModel) {
				if(model2 != null){
				   String sickmanCode2 = model2.getSickmanCode();
				   oweFee = Float.valueOf(model2.getOweFeeCount());
				   float count = oweFee - preFee;
				   if( count >= 5000){
					  sickmanCodes.add(sickmanCode2);
					  kvs.put(sickmanCode2, count);
					  }
				   }
				}
			}
		}
		paramf.put("sickmanCodes", sickmanCodes);
		List<HospitalizeHighOwePersonModel> result = hospitalizeHighOwePersonMapper.selectSickmanOweFeeByDept(paramf);
		List<String> codes = new ArrayList<String>();
		HashMap<String, Float> kvf = new HashMap<String, Float>();
		for (HospitalizeHighOwePersonModel model : result) {
			if( model != null){
			  String deptCode = model.getDeptCode();
			  float fee = model.getFeeCount();
			  
			  if(fee >= 5000){
				  codes.add(deptCode);
				  System.out.println(fee);
			      kvf.put(deptCode, fee);
			  }
		  }
		}
		paramf.put("codes", codes);
		List<String> nameData = new ArrayList<String>();
		List<Float> valueData = new ArrayList<Float>();
		List<ZmapRDeptModel> depts = zmapRdeptMapper.selectDetpsByDeptCodes(paramf);
		for (ZmapRDeptModel model : depts) {
			if(model != null){
			   String deptCode = model.getDeptCode();
			   String deptName = model.getDeptName();
			   float count = kvf.get(deptCode);
			   nameData.add(deptName);
			   valueData.add(count);
			}
		}
		JSONObject json = new JSONObject();
		json.put("name", nameData);
		json.put("value", valueData);
		System.out.println(json.toJSONString());
		return new JSONPObject(callbackFunName, json);
	}
	
	

	@RequestMapping("/PersonTimeByDept")
	@ResponseBody
	public JSONPObject hospitalizeHighOwePersonTimeByDept(HttpServletRequest request,HttpServletResponse response) {

		String callbackFunName = request.getParameter("callbackparamp");

		String hospitalCode = request.getParameter("hospitalCode");
		HashMap paramp = new HashMap();
		paramp.put("hospitalCode", hospitalCode);
		List<HospitalizeHighOwePersonModel> preModel = hospitalizeHighOwePersonMapper.selectSickmanPrepayFeeCount(paramp);
		List<String> sickmanCodes = new ArrayList<String>();
		HashMap<String, Float> kvs = new HashMap<String, Float>();
		for (HospitalizeHighOwePersonModel model : preModel) {
			if( model != null){
			   String sickmanCode = model.getSickmanCode();
			   paramp.put("sickmanCode", sickmanCode);
			   preFee = Float.valueOf(model.getPrepayFeeCount());
			   List<HospitalizeHighOwePersonModel> oweModel = hospitalizeHighOwePersonMapper.selectSickmanOweFeeCount(paramp);
			   for (HospitalizeHighOwePersonModel model2 : oweModel) {
				   if(model2 != null){
				     String sickmanCode2 = model2.getSickmanCode();
				     oweFee = Float.valueOf(model2.getOweFeeCount());
				     float count = oweFee - preFee;
				     if( count >= 5000){
					    sickmanCodes.add(sickmanCode2);
					    kvs.put(sickmanCode2, count);
					    System.out.println(sickmanCode2);
					 }
				 }
			   }
			}
		}
		paramp.put("sickmanCodes", sickmanCodes);
		List<HospitalizeHighOwePersonModel> result = hospitalizeHighOwePersonMapper.selectSickmanTimesByDept(paramp);
		List<String> codes = new ArrayList<String>();
		HashMap<String, Double> kvf = new HashMap<String, Double>();
		for (HospitalizeHighOwePersonModel model : result) {
			if(model != null){
			   String deptCode = model.getDeptCode();
			   double personTime = model.getPersonTime();
			   codes.add(deptCode);
			   kvf.put(deptCode, personTime);
			}
		}
		paramp.put("codes", codes);
		List<String> nameData = new ArrayList<String>();
		List<Double> valueData = new ArrayList<Double>();
		List<ZmapRDeptModel> depts = zmapRdeptMapper.selectDetpsByDeptCodes(paramp);
		for (ZmapRDeptModel model : depts) {
			String deptCode = model.getDeptCode();
			String deptName = model.getDeptName();
			double personTime = kvf.get(deptCode);
			nameData.add(deptName);
			valueData.add(personTime);
		}
		JSONObject json = new JSONObject();
		json.put("name", nameData);
		json.put("value", valueData);
		System.out.println(json.toJSONString());
		
		return new JSONPObject(callbackFunName, json);
	
	}
}
