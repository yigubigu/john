package com.zmap.esb.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.zmap.framework.esb.model.SyncDataBaseModelVo;
import com.zmap.his.hospitalize.mapper.HisHospitalizeHM1HospitalizeSyncMapper;
import com.zmap.his.hospitalize.mapper.HisHospitalizeHM2ChargeSyncMapper;
import com.zmap.his.hospitalize.mapper.HisHospitalizeHM2DrugUseSyncMapper;
import com.zmap.his.hospitalize.mapper.HisHospitalizeHM2ExamineSyncMapper;
import com.zmap.his.hospitalize.mapper.HisHospitalizeHM2OperationSyncMapper;
import com.zmap.his.hospitalize.model.HisHospitalizeHM1HospitalizeSyncModel;
import com.zmap.his.hospitalize.model.HisHospitalizeHM2ChargeSyncModel;
import com.zmap.his.hospitalize.model.HisHospitalizeHM2DrugUseSyncModel;
import com.zmap.his.hospitalize.model.HisHospitalizeHM2ExamineSyncModel;
import com.zmap.his.hospitalize.model.HisHospitalizeHM2OperationSyncModel;
import com.zmap.his.hospitalize.model.ZmapHospitalizeHM1HospitalizeSyncVO;
import com.zmap.his.hospitalize.model.ZmapHospitalizeHM2ChargeSyncVO;
import com.zmap.his.hospitalize.model.ZmapHospitalizeHM2DrugUseSyncVO;
import com.zmap.his.hospitalize.model.ZmapHospitalizeHM2ExamineSyncVO;
import com.zmap.his.hospitalize.model.ZmapHospitalizeHM2OperationSyncVO;

@Service
public class ZmapHospitalizeSyncService {
	private final int PAGESIZE = 1000;//每页大小
	
	private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//定义日期格式
	private SimpleDateFormat sdfWithTime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//定义日期格式
	private String curDate=sdf.format(new Date());//获取系统当前日期
	@Autowired
	private Queue queue;
	@Autowired
	private JmsMessagingTemplate jmsMessagingTemplate;
	
	@Autowired
	private HisHospitalizeHM1HospitalizeSyncMapper hisHospitalizeHM1HospitalizeSyncMapper; 
	@Autowired
	private HisHospitalizeHM2ChargeSyncMapper hisHospitalizeHM2ChargeSyncMapper; 
	@Autowired
	private HisHospitalizeHM2DrugUseSyncMapper hisHospitalizeHM2DrugUseSyncMapper;
	@Autowired
	private HisHospitalizeHM2ExamineSyncMapper hisHospitalizeHM2ExamineSyncMapper;
	@Autowired
	private HisHospitalizeHM2OperationSyncMapper hisHospitalizeHM2OperationSyncMapper;
	
	/******************************** reader to mq *****************************************/
	/**
	 *  read  hospitalize 
	 * @param 
	 * @throws Exception 
	 */
	public void readHos2MQ(String whichDay) {
		int totalPageNum = 0;//总页数 countNum/PAGESIZE + 1
		int totalPageNumWithDate = 0;
		int countNumWithDate = 0;
		
		RestTemplate restTemplate = new RestTemplate();
		SyncDataBaseModelVo vo=new SyncDataBaseModelVo();
		List<HisHospitalizeHM1HospitalizeSyncModel> sourceObj = new ArrayList<HisHospitalizeHM1HospitalizeSyncModel>();//sourceList
		Map<String,Object> map = new HashMap<String,Object>();
		
		List<String> msgLst = new LinkedList<String>();
		String objMsg = "";
		String msg= "";
		
		try{
			if ("".equals(whichDay)) {
				countNumWithDate = hisHospitalizeHM1HospitalizeSyncMapper.countNum();
			}else {
				countNumWithDate = hisHospitalizeHM1HospitalizeSyncMapper.countNumWithDate(whichDay);
			}
			totalPageNumWithDate = countNumWithDate/PAGESIZE + 1;
			System.out.println("zmap_h_m1_hospitalize 总数:" + countNumWithDate);
			System.out.println("zmap_h_m1_hospitalize 每页:" + PAGESIZE + ",共:" + totalPageNumWithDate + "页");
		}catch(Exception e) {
			e.printStackTrace();
			vo.setId(UUID.randomUUID().toString());
			vo.setMsg_type("hos_hos");
			vo.setMsg_des("read hospitalize from his");
			vo.setMsg_ds("ZLHIS.门诊费用记录,ZLHIS.病人挂号记录,ZLHIS.人员表");
			vo.setMsg_result("1");
			vo.setMsg_status("0");
			vo.setMsg_error("分页读取异常：" + e.getMessage());
			vo.setMsg(objMsg);
			vo.setCreate_time(sdfWithTime.format(new Date()));
			vo.setRead_or_write("r");
			msg = JSON.toJSONString(vo);
			restTemplate.postForObject("http://localhost:9098/api/esb/syncdata/savedata?input={input}", msg, String.class, msg);
			return;
		}
		
		for (int i = 0; i<totalPageNumWithDate; i++){
			map.put("curPageRow", PAGESIZE * (i + 1));
			map.put("prePageRow", PAGESIZE * i);
			map.put("curDate", whichDay);
			
			if ("".equals(whichDay)) {
				sourceObj = hisHospitalizeHM1HospitalizeSyncMapper.selectByPagination(map);
			}else {
				sourceObj = hisHospitalizeHM1HospitalizeSyncMapper.selectByPaginationWithDateTime(map);
			}
			int sourceObjSize = sourceObj.size();
			if(sourceObjSize==0){
				vo.setId(UUID.randomUUID().toString());
				vo.setMsg_type("hos_hos");
				vo.setMsg_des("read hospitalize from his");
				vo.setMsg_ds("ZLHIS.病案主页,ZLHIS.病人信息");
				vo.setMsg_result("1");
				vo.setMsg_status("0");
				vo.setMsg_error("分页读取数据为空，当前页数为：" + (i + 1));
				vo.setMsg(objMsg);
				vo.setCreate_time(sdfWithTime.format(new Date()));
				vo.setRead_or_write("r");
				msg = JSON.toJSONString(vo);
				restTemplate.postForObject("http://localhost:9098/api/esb/syncdata/savedata?input={input}", msg, String.class, msg);
				break;
			}
			for (int j =0; j<sourceObjSize; j++) {
				vo.setId(UUID.randomUUID().toString());
				vo.setMsg_type("hos_hos");
				vo.setMsg_des("read hospitalize from his");
				vo.setMsg_ds("ZLHIS.病案主页,ZLHIS.病人信息");
				vo.setMsg_result("1");
				vo.setMsg_status("0");
				vo.setCreate_time(sdfWithTime.format(new Date()));
				vo.setMsg(sourceObj.get(j).toVo().toString());
				vo.setRead_or_write("r");
				msg = JSON.toJSONString(vo);
				restTemplate.postForObject("http://localhost:9098/api/esb/syncdata/savedata?input={input}", msg, String.class, msg);
				objMsg = JSON.toJSONString(sourceObj.get(j).toVo());                 
				vo.setMsg(objMsg);
				msgLst.add(JSON.toJSONString(vo));
			}
			for (int k=0; k<msgLst.size(); k++) {
				this.jmsMessagingTemplate.convertAndSend(this.queue, msgLst.get(k));
			}
			msgLst.clear();
		}
	}
	
	/**
	 *  reader  hospitalize charge
	 * @param 
	 * @throws Exception 
	 */
	public void readHosCharge2MQ(String whichDay) {
		int totalPageNum = 0;//总页数 countNum/PAGESIZE + 1
		int totalPageNumWithDate = 0;
		int countNumWithDate = 0;
		String objMsg = "";
		String msg= "";
		
		RestTemplate restTemplate = new RestTemplate();
		SyncDataBaseModelVo vo=new SyncDataBaseModelVo();
		List<HisHospitalizeHM2ChargeSyncModel> sourceObj = new ArrayList<HisHospitalizeHM2ChargeSyncModel>();//sourceList
		Map<String,Object> map = new HashMap<String,Object>();
		List<String> msgLst = new LinkedList<String>();
		
		try {
			if ("".equals(whichDay)) {
				countNumWithDate = hisHospitalizeHM2ChargeSyncMapper.countNum();
			}else {
				countNumWithDate = hisHospitalizeHM2ChargeSyncMapper.countNumWithDate(whichDay);
			}
			totalPageNumWithDate = countNumWithDate/PAGESIZE + 1;
			System.out.println("zmap_h_m2_charge 总数:" + countNumWithDate);
			System.out.println("zmap_h_m2_charge 每页:" + PAGESIZE + ",共:" + totalPageNumWithDate + "页");
		}catch(Exception e) {
			e.printStackTrace();
			vo.setId(UUID.randomUUID().toString());
			vo.setMsg_type("hos_charge");
			vo.setMsg_des("read hospitalize_charge from his");
			vo.setMsg_ds("ZLHIS.住院费用记录,ZLHIS.人员表");
			vo.setMsg_result("1");
			vo.setMsg_status("0");
			vo.setMsg_error("分页读取异常：" + e.getMessage());
			vo.setMsg(objMsg);
			vo.setCreate_time(sdfWithTime.format(new Date()));
			vo.setRead_or_write("r");
			msg = JSON.toJSONString(vo);
			restTemplate.postForObject("http://localhost:9098/api/esb/syncdata/savedata?input={input}", msg, String.class, msg);
			return;
		}
		
		for (int i = 0; i<totalPageNumWithDate; i++){
			map.put("curPageRow", PAGESIZE*(i+1));
			map.put("prePageRow", PAGESIZE*i);
			map.put("curDate", whichDay);
			
			if ("".equals(whichDay)) {
				sourceObj = hisHospitalizeHM2ChargeSyncMapper.selectByPagination(map);
			}else {
				sourceObj = hisHospitalizeHM2ChargeSyncMapper.selectByPaginationWithDateTime(map);
			}
			int sourceObjSize = sourceObj.size();
			if(sourceObjSize==0){
				vo.setId(UUID.randomUUID().toString());
				vo.setMsg_type("hos_charge");
				vo.setMsg_des("read hospitalize_charge from his");
				vo.setMsg_ds("ZLHIS.住院费用记录,ZLHIS.人员表");
				vo.setMsg_result("1");
				vo.setMsg_status("0");
				vo.setMsg_error("分页读取的源数据为空！当前页数为：" + (i+1));
				vo.setMsg(objMsg);
				vo.setCreate_time(sdfWithTime.format(new Date()));
				vo.setRead_or_write("r");
				msg = JSON.toJSONString(vo);
				restTemplate.postForObject("http://localhost:9098/api/esb/syncdata/savedata?input={input}", msg, String.class, msg);
				break;
			}
			for (int j =0; j<sourceObjSize; j++) {
				vo.setId(UUID.randomUUID().toString());
				vo.setMsg_type("hos_charge");
				vo.setMsg_des("read hospitalize_charge from his");
				vo.setMsg_ds("ZLHIS.住院费用记录,ZLHIS.人员表");
				vo.setMsg_result("1");
				vo.setMsg_status("0");
				vo.setCreate_time(sdfWithTime.format(new Date()));
				vo.setRead_or_write("r");
				vo.setMsg(sourceObj.get(j).toVo().toString());
				msg = JSON.toJSONString(vo);
				restTemplate.postForObject("http://localhost:9098/api/esb/syncdata/savedata?input={input}", msg, String.class, msg);
				objMsg = JSON.toJSONString(sourceObj.get(j).toVo());                 
				vo.setMsg(objMsg);
				msgLst.add(JSON.toJSONString(vo));
			}
			for (int k=0; k<msgLst.size(); k++) {
				this.jmsMessagingTemplate.convertAndSend(this.queue, msgLst.get(k));
			}
			msgLst.clear();
		}
	}
}
