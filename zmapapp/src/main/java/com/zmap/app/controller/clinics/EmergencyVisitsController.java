package com.zmap.app.controller.clinics;

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
import com.zmap.zmap.clinic.mapper.EmergencyVisitsMapper;
import com.zmap.zmap.clinic.model.EmergencyVisitsModel;

@RestController
@RequestMapping("/api/clinics/emergencyVisits")
public class EmergencyVisitsController {

	@Autowired
	private EmergencyVisitsMapper emergencyVisitsMapper;
	
	/**
	 * 当日急诊人次总数
	 */
	@RequestMapping("/countDayEmergencyVisitsSum")
    @ResponseBody
	public JSONPObject countDayEmergencyVisitsSum(HttpServletRequest request,HttpServletResponse response){
		String callbackFunName=request.getParameter("callbackparams");
		String sdate =request.getParameter("sdate");
		String hospital=request.getParameter("hospitalCode");
		HashMap<String, String> params=new HashMap<String, String>();
		params.put("curDate",sdate);
		params.put("hospitalCode",hospital);
		List<EmergencyVisitsModel> visits=emergencyVisitsMapper.selectDayEmergencyVisitsSum(params);
		List<Integer>sumData=new ArrayList<Integer>();
		for (EmergencyVisitsModel model : visits) {
			int count = Integer.valueOf(model.getVisitsCount());
			sumData.add(count);
		}
		 JSONObject json=new JSONObject();
		 json.put("sum",sumData);
		 System.out.println(json.toJSONString());
		 return new JSONPObject(callbackFunName, json);
	 }
	
	                  
	/**
	 *分时统计急诊人次
	 */
	@RequestMapping("/countDayEmergencyVisitsByHour")
	@ResponseBody
	public JSONPObject countDayyEmergencyVisitsByHour(HttpServletRequest request,HttpServletResponse response){
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
			List<EmergencyVisitsModel> visits=emergencyVisitsMapper.selectDayEmergencyVisitsByHour(params);
			for (EmergencyVisitsModel model : visits) {
				float count=0;
				if(model==null){
					count=0;
				}else{
					count=model.getVisitsCount();
				}
				list.add((long) count);
			}
		}
		   json.put("count",list);
		   System.out.println(list);
		return new JSONPObject(callbackFunName, json);
	}

	
}
