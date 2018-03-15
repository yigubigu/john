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
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.zmap.framework.esb.model.SyncDataBaseModelVo;
import com.zmap.his.clinic.mapper.HisClinicCM1SyncMapper;
import com.zmap.his.clinic.mapper.HisClinicCM2ChargeSyncMapper;
import com.zmap.his.clinic.mapper.HisClinicCM2DiagnoseSyncMapper;
import com.zmap.his.clinic.mapper.HisClinicCM2DrugUseSyncMapper;
import com.zmap.his.clinic.mapper.HisClinicCM2ExamineSyncMapper;
import com.zmap.his.clinic.mapper.HisClinicCM2MOrderSyncMapper;
import com.zmap.his.clinic.mapper.JbPrepayChargeSyncMapper;
import com.zmap.his.clinic.mapper.JbTransClinicSyncMapper;
import com.zmap.his.clinic.model.HisClinicCM1SyncModel;
import com.zmap.his.clinic.model.HisClinicCM2ChargeSyncModel;
import com.zmap.his.clinic.model.HisClinicCM2MOrderSyncModel;
import com.zmap.his.clinic.model.JbPrepayChargeSyncModel;
import com.zmap.his.clinic.model.JbTransClinicSyncModel;

@Service
public class ZmapClinicSyncService {
	private final int PAGESIZE = 1000;//每页大小
	
	private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//定义日期格式
	private SimpleDateFormat sdfWithTime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//定义日期格式
	private String curDate=sdf.format(new Date());//获取系统当前日期
	
	@Autowired
	private Queue queue;
	@Autowired
	private JmsMessagingTemplate jmsMessagingTemplate;
	
	@Autowired
	private HisClinicCM1SyncMapper hisClinicCM1SyncMapper; 
	@Autowired
	private HisClinicCM2ChargeSyncMapper hisClinicCM2ChargeSyncMapper; 
	@Autowired
	private HisClinicCM2DiagnoseSyncMapper hisClinicCM2DiagnoseSyncMapper; 
	@Autowired
	private HisClinicCM2DrugUseSyncMapper hisClinicCM2DrugUseSyncMapper; 
	@Autowired
	private HisClinicCM2ExamineSyncMapper hisClinicCM2ExamineSyncMapper; 
	@Autowired
	private HisClinicCM2MOrderSyncMapper hisClinicCM2MOrderSyncMapper; 
	@Autowired
	private JbTransClinicSyncMapper jbTransClinicSyncMapper; 
	@Autowired
	private JbPrepayChargeSyncMapper jbPrepayChargeSyncMapper; 
	
	/******************************** reader to mq *****************************************/
	/**
	 *  reader  clinic 
	 * @param 
	 * @throws Exception 
	 */
	public void readCln2MQ(String whichDay) {
		int totalPageNum = 0;//总页数 countNum/PAGESIZE + 1
		int totalPageNumWithDate = 0;
		int countNumWithDate = 0;
		String objMsg = "";
		String msg= "";
		
		RestTemplate restTemplate = new RestTemplate();
		SyncDataBaseModelVo vo=new SyncDataBaseModelVo();
		List<HisClinicCM1SyncModel> sourceObj = new ArrayList<HisClinicCM1SyncModel>();//sourceList
		Map<String,Object> map = new HashMap<String,Object>();
		List<String> msgLst = new LinkedList<String>();
		
		try {
			if ("".equals(whichDay)) {
				countNumWithDate = hisClinicCM1SyncMapper.countNum();
			}else {
				countNumWithDate = hisClinicCM1SyncMapper.countNumWithDate(whichDay);
			}
			totalPageNumWithDate = countNumWithDate/PAGESIZE + 1;
			System.out.println("zmap_c_m1_clinics 总数:" + countNumWithDate);
			System.out.println("zmap_c_m1_clinics 每页:" + PAGESIZE + ",共:" + totalPageNumWithDate + "页");
		}catch (Exception e) {
			e.printStackTrace();
			vo.setId(UUID.randomUUID().toString());
			vo.setMsg_type("cln_cln");
			vo.setMsg_des("read clinic from his");
			vo.setMsg_ds("ZLHIS.病人挂号记录");
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
		
		for (int i = 0; i<totalPageNumWithDate; i++) {
			map.put("curPageRow", PAGESIZE * (i + 1));
			map.put("prePageRow", PAGESIZE * i);
			map.put("curDate", whichDay);
			
			if ("".equals(whichDay)) {
				sourceObj = hisClinicCM1SyncMapper.selectByPagination(map);
			}else {
				sourceObj = hisClinicCM1SyncMapper.selectByPaginationWithDateTime(map);
			}
			int sourceObjSize = sourceObj.size();
			if(sourceObjSize == 0){
				vo.setId(UUID.randomUUID().toString());
				vo.setMsg_type("cln_cln");
				vo.setMsg_des("read clinic from his");
				vo.setMsg_ds("ZLHIS.病人挂号记录");
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
				vo.setMsg_type("cln_cln");
				vo.setMsg_des("read clinic from his");
				vo.setMsg_ds("ZLHIS.病人挂号记录");
				vo.setMsg_result("1");
				vo.setMsg_status("0");
				vo.setMsg(sourceObj.get(j).toVo().toString());
				vo.setRead_or_write("r");
				vo.setCreate_time(sdfWithTime.format(new Date()));
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
	 *  reader  clinic charge
	 * @param 
	 * @throws Exception 
	 */
	public void readClnCharge2MQ(String whichDay) {
		int totalPageNum = 0;//总页数 countNum/PAGESIZE + 1
		int totalPageNumWithDate = 0;
		int countNumWithDate = 0;
		
		RestTemplate restTemplate = new RestTemplate();
		SyncDataBaseModelVo vo=new SyncDataBaseModelVo();
		List<HisClinicCM2ChargeSyncModel> sourceObj = new ArrayList<HisClinicCM2ChargeSyncModel>();//sourceList
		Map<String,Object> map = new HashMap<String,Object>();
		
		List<String> msgLst = new LinkedList<String>();
		String objMsg = "";
		String msg= "";
		try{
			if ("".equals(whichDay)) {
				countNumWithDate = hisClinicCM2ChargeSyncMapper.countNum();
			}else {
				countNumWithDate = hisClinicCM2ChargeSyncMapper.countNumWithDate(whichDay);
			}
			totalPageNumWithDate = countNumWithDate/PAGESIZE + 1;
			System.out.println("zmap_c_m2_charge 总数:" + countNumWithDate);
			System.out.println("zmap_c_m2_charge 每页:" + PAGESIZE + ",共:" + totalPageNumWithDate + "页");
		}catch (Exception e){
			e.printStackTrace();
			vo.setId(UUID.randomUUID().toString());
			vo.setMsg_type("cln_cln");
			vo.setMsg_des("read clinic_charge from his");
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
		
		for (int i = 0; i<totalPageNumWithDate; i++) {
			map.put("curPageRow", PAGESIZE * (i + 1));
			map.put("prePageRow", PAGESIZE * i);
			map.put("curDate", whichDay);
			if ("".equals(whichDay)) {
				sourceObj = hisClinicCM2ChargeSyncMapper.selectByPagination(map);
			}else {
				sourceObj = hisClinicCM2ChargeSyncMapper.selectByPaginationWithDateTime(map);
			}
			int sourceObjSize = sourceObj.size();
			if(sourceObjSize == 0){
				vo.setId(UUID.randomUUID().toString());
				vo.setMsg_type("cln_charge");
				vo.setMsg_des("read clinic_charge from his");
				vo.setMsg_ds("ZLHIS.门诊费用记录,ZLHIS.病人挂号记录,ZLHIS.人员表");
				vo.setMsg_result("1");
				vo.setMsg_status("0");
				vo.setMsg_error("分页读取数据为空，当前页数为：" + (i + 1));
				vo.setMsg(objMsg);
				vo.setCreate_time(sdfWithTime.format(new Date()));
				vo.setRead_or_write("r");
				msg = JSON.toJSONString(vo);
				restTemplate.postForObject("http://localhost:9098/api/esb/syncdata/savedata?input={input}", msg, String.class,msg);
				break;
			}
			for (int j =0; j<sourceObjSize; j++) {
				vo.setId(UUID.randomUUID().toString());
				vo.setMsg_type("cln_charge");
				vo.setMsg_des("read clinic_charge from his");
				vo.setMsg_ds("ZLHIS.门诊费用记录,ZLHIS.病人挂号记录,ZLHIS.人员表");
				vo.setMsg_result("1");
				vo.setMsg_status("0");
				vo.setMsg(sourceObj.get(j).toVo().toString());
				vo.setCreate_time(sdfWithTime.format(new Date()));
				vo.setRead_or_write("r");
				msg = JSON.toJSONString(vo);
				restTemplate.postForObject("http://localhost:9098/api/esb/syncdata/savedata?input={input}", msg, String.class,msg);
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
	 *  reade  zmap_c_m2_morder
	 * @param 
	 * @throws Exception 
	 */
	public void readMOrder2MQ(String whichDay) {
		int totalPageNum = 0;//总页数 countNum/PAGESIZE + 1
		int totalPageNumWithDate = 0;
		int countNumWithDate = 0;
		
		RestTemplate restTemplate = new RestTemplate();
		SyncDataBaseModelVo vo=new SyncDataBaseModelVo();
		List<HisClinicCM2MOrderSyncModel> sourceObj = new ArrayList<HisClinicCM2MOrderSyncModel>();//sourceList
		Map<String,Object> map = new HashMap<String,Object>();
		
		List<String> msgLst = new LinkedList<String>();
		String objMsg = "";
		String msg= "";
		try{
			if ("".equals(whichDay)) {
				countNumWithDate = hisClinicCM2MOrderSyncMapper.countNum();
			}else {
				countNumWithDate = hisClinicCM2MOrderSyncMapper.countNumWithDate(whichDay);
			}
			totalPageNumWithDate = countNumWithDate/PAGESIZE + 1;
			System.out.println("zmap_c_m2_morder 总数:" + countNumWithDate);
			System.out.println("zmap_c_m2_morder 每页:" + PAGESIZE + ",共:" + totalPageNumWithDate + "页");
		}catch (Exception e){
			e.printStackTrace();
			vo.setId(UUID.randomUUID().toString());
			vo.setMsg_type("morder");
			vo.setMsg_des("read morder from his");
			vo.setMsg_ds("ZLHIS.病人医嘱记录");
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
		
		for (int i = 0; i<totalPageNumWithDate; i++) {
			map.put("curPageRow", PAGESIZE * (i + 1));
			map.put("prePageRow", PAGESIZE * i);
			map.put("curDate", whichDay);
			if ("".equals(whichDay)) {
				sourceObj = hisClinicCM2MOrderSyncMapper.selectByPagination(map);
			}else {
				sourceObj = hisClinicCM2MOrderSyncMapper.selectByPaginationWithDateTime(map);
			}
			int sourceObjSize = sourceObj.size();
			if(sourceObjSize == 0){
				vo.setId(UUID.randomUUID().toString());
				vo.setMsg_type("morder");
				vo.setMsg_des("read morder from his");
				vo.setMsg_ds("ZLHIS.病人医嘱记录");
				vo.setMsg_result("1");
				vo.setMsg_status("0");
				vo.setMsg_error("分页读取数据为空，当前页数为：" + (i + 1));
				vo.setMsg(objMsg);
				vo.setCreate_time(sdfWithTime.format(new Date()));
				vo.setRead_or_write("r");
				msg = JSON.toJSONString(vo);
				restTemplate.postForObject("http://localhost:9098/api/esb/syncdata/savedata?input={input}", msg, String.class,msg);
				break;
			}
			for (int j =0; j<sourceObjSize; j++) {
				vo.setId(UUID.randomUUID().toString());
				vo.setMsg_type("morder");
				vo.setMsg_des("read morder from his");
				vo.setMsg_ds("ZLHIS.病人医嘱记录");
				vo.setMsg_result("1");
				vo.setMsg_status("0");
				vo.setMsg(sourceObj.get(j).toVo().toString());
				vo.setCreate_time(sdfWithTime.format(new Date()));
				vo.setRead_or_write("r");
				msg = JSON.toJSONString(vo);
				restTemplate.postForObject("http://localhost:9098/api/esb/syncdata/savedata?input={input}", msg, String.class,msg);
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
	 *  江北 转诊记录数据
	 * @param 
	 * @throws Exception 
	 */
	public void readJbTransCln2MQ(String whichDay) {
		int totalPageNum = 0;//总页数 countNum/PAGESIZE + 1
		int totalPageNumWithDate = 0;
		int countNumWithDate = 0;
		
		RestTemplate restTemplate = new RestTemplate();
		SyncDataBaseModelVo vo=new SyncDataBaseModelVo();
		List<JbTransClinicSyncModel> sourceObj = new ArrayList<JbTransClinicSyncModel>();//sourceList
		Map<String,Object> map = new HashMap<String,Object>();
		
		List<String> msgLst = new LinkedList<String>();
		String objMsg = "";
		String msg= "";
		try{
			if ("".equals(whichDay)) {
				countNumWithDate = jbTransClinicSyncMapper.countNum();
			}else {
				countNumWithDate = jbTransClinicSyncMapper.countNumWithDate(whichDay);
			}
			totalPageNumWithDate = countNumWithDate/PAGESIZE + 1;
			System.out.println("jb_trans_clinic 总数:" + countNumWithDate);
			System.out.println("jb_trans_clinic 每页:" + PAGESIZE + ",共:" + totalPageNumWithDate + "页");
		}catch (Exception e){
			e.printStackTrace();
			vo.setId(UUID.randomUUID().toString());
			vo.setMsg_type("jiangbei_trans_clinic");
			vo.setMsg_des("read jiangbei_trans_clinic from his");
			vo.setMsg_ds("ZLHIS.病人转诊记录");
			vo.setMsg_result("1");
			vo.setMsg_status("0");
			vo.setMsg_error("分页读取异常：" + e.getMessage());
			vo.setMsg(objMsg);
			vo.setCreate_time(sdfWithTime.format(new Date()));
			vo.setRead_or_write("r");
			msg = JSON.toJSONString(vo);
			restTemplate.postForObject("http://localhost:9098/api/esb/syncdata/insert2ErrLog?input={input}", msg, String.class, msg);
			return;
		}
		
		for (int i = 0; i<totalPageNumWithDate; i++) {
			map.put("curPageRow", PAGESIZE * (i + 1));
			map.put("prePageRow", PAGESIZE * i);
			map.put("curDate", whichDay);
			if ("".equals(whichDay)) {
				sourceObj = jbTransClinicSyncMapper.selectByPagination(map);
			}else {
				sourceObj = jbTransClinicSyncMapper.selectByPaginationWithDateTime(map);
			}
			int sourceObjSize = sourceObj.size();
			if(sourceObjSize == 0){
				vo.setId(UUID.randomUUID().toString());
				vo.setMsg_type("jiangbei_trans_clinic");
				vo.setMsg_des("read jiangbei_trans_clinic from his");
				vo.setMsg_ds("ZLHIS.病人转诊记录");
				vo.setMsg_result("1");
				vo.setMsg_status("0");
				vo.setMsg_error("分页读取数据为空，当前页数为：" + (i + 1));
				vo.setMsg(objMsg);
				vo.setCreate_time(sdfWithTime.format(new Date()));
				vo.setRead_or_write("r");
				msg = JSON.toJSONString(vo);
				restTemplate.postForObject("http://localhost:9098/api/esb/syncdata/savedata?input={input}", msg, String.class,msg);
				break;
			}
			for (int j =0; j<sourceObjSize; j++) {
				vo.setId(UUID.randomUUID().toString());
				vo.setMsg_type("jiangbei_trans_clinic");
				vo.setMsg_des("read jiangbei_trans_clinic from his");
				vo.setMsg_ds("ZLHIS.病人转诊记录");
				vo.setMsg_result("1");
				vo.setMsg_status("0");
				vo.setMsg(sourceObj.get(j).toVo().toString());
				vo.setCreate_time(sdfWithTime.format(new Date()));
				vo.setRead_or_write("r");
				msg = JSON.toJSONString(vo);
				restTemplate.postForObject("http://localhost:9098/api/esb/syncdata/savedata?input={input}", msg, String.class,msg);
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
	 *  江北 病人预交记录
	 * @param 
	 * @throws Exception 
	 */
	public void readJbPrepayCharge2MQ(String whichDay) {
		int totalPageNum = 0;//总页数 countNum/PAGESIZE + 1
		int totalPageNumWithDate = 0;
		int countNumWithDate = 0;
		
		RestTemplate restTemplate = new RestTemplate();
		SyncDataBaseModelVo vo=new SyncDataBaseModelVo();
		List<JbPrepayChargeSyncModel> sourceObj = new ArrayList<JbPrepayChargeSyncModel>();//sourceList
		Map<String,Object> map = new HashMap<String,Object>();
		
		List<String> msgLst = new LinkedList<String>();
		String objMsg = "";
		String msg= "";
		try{
			if ("".equals(whichDay)) {
				countNumWithDate = jbPrepayChargeSyncMapper.countNum();
			}else {
				countNumWithDate = jbPrepayChargeSyncMapper.countNumWithDate(whichDay);
			}
			totalPageNumWithDate = countNumWithDate/PAGESIZE + 1;
			System.out.println("jb_prepay_charge 总数:" + countNumWithDate);
			System.out.println("jb_prepay_charge 每页:" + PAGESIZE + ",共:" + totalPageNumWithDate + "页");
		}catch (Exception e){
			e.printStackTrace();
			vo.setId(UUID.randomUUID().toString());
			vo.setMsg_type("jb_prepay_charge");
			vo.setMsg_des("read jb_prepay_charge from his");
			vo.setMsg_ds("ZLHIS.病人预交记录");
			vo.setMsg_result("1");
			vo.setMsg_status("0");
			vo.setMsg_error("分页读取异常：" + e.getMessage());
			vo.setMsg(objMsg);
			vo.setCreate_time(sdfWithTime.format(new Date()));
			vo.setRead_or_write("r");
			msg = JSON.toJSONString(vo);
			restTemplate.postForObject("http://localhost:9098/api/esb/syncdata/insert2ErrLog?input={input}", msg, String.class, msg);
			return;
		}
		
		for (int i = 0; i<totalPageNumWithDate; i++) {
			map.put("curPageRow", PAGESIZE * (i + 1));
			map.put("prePageRow", PAGESIZE * i);
			map.put("curDate", whichDay);
			if ("".equals(whichDay)) {
				sourceObj = jbPrepayChargeSyncMapper.selectByPagination(map);
			}else {
				sourceObj = jbPrepayChargeSyncMapper.selectByPaginationWithDateTime(map);
			}
			int sourceObjSize = sourceObj.size();
			if(sourceObjSize == 0){
				vo.setId(UUID.randomUUID().toString());
				vo.setMsg_type("jb_prepay_charge");
				vo.setMsg_des("read jb_prepay_charge from his");
				vo.setMsg_ds("ZLHIS.病人预交记录");
				vo.setMsg_result("1");
				vo.setMsg_status("0");
				vo.setMsg_error("分页读取数据为空，当前页数为：" + (i + 1));
				vo.setMsg(objMsg);
				vo.setCreate_time(sdfWithTime.format(new Date()));
				vo.setRead_or_write("r");
				msg = JSON.toJSONString(vo);
				restTemplate.postForObject("http://localhost:9098/api/esb/syncdata/savedata?input={input}", msg, String.class,msg);
				break;
			}
			for (int j =0; j<sourceObjSize; j++) {
				vo.setId(UUID.randomUUID().toString());
				vo.setMsg_type("jb_prepay_charge");
				vo.setMsg_des("read jb_prepay_charge from his");
				vo.setMsg_ds("ZLHIS.病人预交记录");
				vo.setMsg_result("1");
				vo.setMsg_status("0");
				vo.setMsg(sourceObj.get(j).toVo().toString());
				vo.setCreate_time(sdfWithTime.format(new Date()));
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
}
