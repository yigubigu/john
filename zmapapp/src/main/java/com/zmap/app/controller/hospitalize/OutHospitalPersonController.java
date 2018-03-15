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
import com.zmap.zmap.framework.model.NameValueEntity;
import com.zmap.zmap.hospitalize.mapper.OutHospitalPersonMapper;
import com.zmap.zmap.hospitalize.model.OutHospitalPersonModel;



@RestController
@RequestMapping("/api/service/outHospital")
public class OutHospitalPersonController {
	
	@Autowired 
	private OutHospitalPersonMapper outHospitalPersonMapper;
	private double sum;
	
	/**
	 * 医院一天的出院人次总和
	 * 
	 */
	@RequestMapping("/countDayOutHospitalPersonSum")
	@ResponseBody
	public JSONPObject countDayOutHospitalPersonSum(HttpServletRequest request , HttpServletResponse response) {
		String callbackFunName = request.getParameter("callbackparams");
		String sdate = request.getParameter("sdate");
		String hospital = request.getParameter("hospitalCode");
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("curDate", sdate);
		params.put("hospitalCode", hospital);
		List<OutHospitalPersonModel> people = outHospitalPersonMapper.selectOutHospitalPersonByDate(params);
		for (OutHospitalPersonModel model : people) {
			double count = Long.valueOf((long) model.getOutPeopleCount());
			sum = count;
		}
		JSONObject json = new JSONObject();
		json.put("sum", sum);
		System.out.println(json.toJSONString());
		return new JSONPObject(callbackFunName, json);
	}
	
	
	/**
	 * 分科室统计一天的出院人次
	 * 
	 **/
	@RequestMapping("/countDayOutHospitalPersonByDept")
	@ResponseBody
	public JSONPObject countDayOutHospitalPersonByDept(HttpServletRequest request , HttpServletResponse response) {
		String callbackFunName = request.getParameter("callbackparamd");
		String sdate = request.getParameter("sdate");
		String hospital = request.getParameter("hospitalCode");
		HashMap<String , String> params = new HashMap<String , String>();
		params.put("curDate", sdate);
		params.put("hospitalCode",hospital);
		List<OutHospitalPersonModel> deptList = outHospitalPersonMapper.selectOutHospitalPersonByDept(params);
		List<Double> valueData = new ArrayList<Double>(); 
		List<String> nameData = new ArrayList<String>();
		List<String> icode = new ArrayList<String>();
		for (OutHospitalPersonModel model : deptList) {
			float count = Long.valueOf((long) model.getOutPeopleCount());
			String code = model.getRespDept();
			String deptname = model.getDeptName();
			System.out.println(deptname);
//			NameValueEntity entity = new NameValueEntity();
//			entity.setName(deptname);
//			entity.setValue((long) count);
			valueData.add((double) count);
			nameData.add(deptname);
			icode.add(code);
		}
		
		JSONObject json = new JSONObject();
		json.put("name", nameData);
		json.put("deptcode", icode);
		json.put("value", valueData);
		System.out.println(json.toJSONString());
		return new JSONPObject(callbackFunName, json);
	}
	
	/**
	 * 
	 * 分科室统计一天的出院人次比
	 * 
	 * */
	@RequestMapping("/countDayOutHospitalPersonByDeptProportion")
	@ResponseBody
	public JSONPObject countDayOutHospitalPersonByDeptProportion(HttpServletRequest request , HttpServletResponse response) {
		String callbackFunName = request.getParameter("callbackparamp");
		String sdate = request.getParameter("sdate");
		String hospital = request.getParameter("hospitalCode");
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("curDate", sdate);
		params.put("hospitalCode", hospital);
		List<OutHospitalPersonModel> deptList = outHospitalPersonMapper.selectOutHospitalPersonByDept(params);
		List<String> nameData = new ArrayList<String>();
		List<NameValueEntity> valueData = new ArrayList<NameValueEntity>();
		List<String> icode = new ArrayList<String>();
		for (OutHospitalPersonModel model : deptList) {
			float count = Long.valueOf((long) model.getOutPeopleCount());
			String deptname = model.getDeptName();
			NameValueEntity entity = new NameValueEntity();
			entity.setName(deptname);
			entity.setValue((long) count);
			valueData.add(entity);
			String code = model.getRespDept();
			icode.add(code);
			
			nameData.add(deptname);
		}
		JSONObject json = new JSONObject();
		json.put("deptcode", icode);
		json.put("name", nameData);
		json.put("value", valueData);	
		return new JSONPObject(callbackFunName, json);
		
	}
}
