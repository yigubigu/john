package com.zmap.framework.syncdata.controller;



import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.zmap.zmap.framework.base.mapper.SyncDataBaseMapper;
import com.zmap.zmap.framework.base.model.SyncDataBaseModel;



@RestController
@RequestMapping("/api/esb/syncdata")
public class SyncDataBaseController {
	
	
private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@Autowired
	private SyncDataBaseMapper syncDataBaseMapper;
	
	
	
	/**
	 * 向mq发送消息
	 */
	@RequestMapping("/savedata")
	@ResponseBody		
	public String saveData(String input){
		//String data =request.getParameter("input");
//		System.out.println("mq消息内容是"+input);
		SyncDataBaseModel model=JSON.parseObject(input, SyncDataBaseModel.class);
		int result=syncDataBaseMapper.insert(model);
		return String.valueOf(result);
		
	
	}
	
	/**
	 * 向mq发送消息
	 */
	@RequestMapping("/listdata")
	@ResponseBody
	public JSONPObject listData(HttpServletRequest request,HttpServletResponse response){
			String callbackFunName =request.getParameter("callbackparam");//得到js函数名称 
			JSONObject json=new JSONObject();
			List<SyncDataBaseModel> list=syncDataBaseMapper.selectSyncDataModeList();
			json.put("list",list);
			return new JSONPObject(callbackFunName, json);
	}
	
	/**
	 * update msg_status
	 */
	@RequestMapping("/updateMsg_Status")
	public int updateMsg_Status(@RequestBody SyncDataBaseModel input){
		int result=syncDataBaseMapper.updateStatusByPrimaryKey(input);
		return result;
	}
	/**
	 * insert2ErrLog 
	 */
	@RequestMapping("/insert2ErrLog")
	@ResponseBody	
	public int insert2ErrLog(String input){
		SyncDataBaseModel model=JSON.parseObject(input, SyncDataBaseModel.class);
		int result=syncDataBaseMapper.insert2ErrLog(model);
		return result;
	}
}
