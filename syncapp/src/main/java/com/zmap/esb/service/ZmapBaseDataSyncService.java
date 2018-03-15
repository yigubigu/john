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
import com.zmap.his.basedata.mapper.LAcctItemMapper;
import com.zmap.his.basedata.mapper.LExamineMapper;
import com.zmap.his.basedata.mapper.LFeeItemMapper;
import com.zmap.his.basedata.mapper.LOperationMapper;
import com.zmap.his.basedata.mapper.RDoctorMapper;
import com.zmap.his.basedata.mapper.RPatientMapper;
import com.zmap.his.basedata.model.LAcctItemModel;
import com.zmap.his.basedata.model.LExamineModel;
import com.zmap.his.basedata.model.LFeeItemModel;
import com.zmap.his.basedata.model.LOperationModel;
import com.zmap.his.basedata.model.RDoctorModel;
import com.zmap.his.basedata.model.RPatientModel;

@Service
public class ZmapBaseDataSyncService {
private final int PAGESIZE = 1000;//每页大小
	
	private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//定义日期格式
	private SimpleDateFormat sdfWithTime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//定义日期格式
	private String curDate=sdf.format(new Date());//获取系统当前日期
	@Autowired
	private Queue baseDataQueue;
	@Autowired
	private JmsMessagingTemplate jmsMessagingTemplate;
	
	@Autowired
	private RDoctorMapper rDoctorMapper;
	@Autowired
	private RPatientMapper rPatientMapper;
	@Autowired
	private LAcctItemMapper lAcctItemMapper;
	@Autowired
	private LExamineMapper lExamineMapper;
	@Autowired
	private LFeeItemMapper lFeeItemMapper;
	@Autowired
	private LOperationMapper lOperationMapper;
	
	/******************************** reader to mq *****************************************/
	/**
	 *  doctor 
	 * @param 
	 * @throws Exception 
	 */
	public void readRDoctor2MQ(String whichDay) {
		int totalPageNum = 0;//总页数 countNum/PAGESIZE + 1
		int totalPageNumWithDate = 0;
		int countNumWithDate = 0;
		String objMsg = "";
		String msg= "";
		
		RestTemplate restTemplate = new RestTemplate();
		SyncDataBaseModelVo vo=new SyncDataBaseModelVo();
		List<RDoctorModel> sourceObj = new ArrayList<RDoctorModel>();//sourceList
		Map<String,Object> map = new HashMap<String,Object>();
		List<String> msgLst = new LinkedList<String>();
		
		try {
			if ("".equals(whichDay)) {
				countNumWithDate = rDoctorMapper.countNum();
			}else {
				countNumWithDate = rDoctorMapper.countNumWithDate(whichDay);
			}
			totalPageNumWithDate = countNumWithDate/PAGESIZE + 1;
			System.out.println("zmap_r_doctor 总数:" + countNumWithDate);
			System.out.println("zmap_r_doctor 每页:" + PAGESIZE + ",共:" + totalPageNumWithDate + "页");
		}catch (Exception e) {
			e.printStackTrace();
			vo.setId(UUID.randomUUID().toString());
			vo.setMsg_type("rDoctor");
			vo.setMsg_des("read doctor from his");
			vo.setMsg_ds("ZLHIS.人员表");
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
				whichDay = curDate;
				sourceObj = rDoctorMapper.selectByPagination(map);
			}else {
				sourceObj = rDoctorMapper.selectByPaginationWithDateTime(map);
			}
			int sourceObjSize = sourceObj.size();
			if(sourceObjSize == 0){
				vo.setId(UUID.randomUUID().toString());
				vo.setMsg_type("rDoctor");
				vo.setMsg_des("read doctor from his");
				vo.setMsg_ds("ZLHIS.人员表");
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
				vo.setMsg_type("rDoctor");
				vo.setMsg_des("read doctor from his");
				vo.setMsg_ds("ZLHIS.人员表");
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
				this.jmsMessagingTemplate.convertAndSend(this.baseDataQueue, msgLst.get(k));
			}
			msgLst.clear();
		}
	}
	/**
	 *  patient 
	 * @param 
	 * @throws Exception 
	 */
	public void readRPatient2MQ(String whichDay) {
		int totalPageNum = 0;//总页数 countNum/PAGESIZE + 1
		int totalPageNumWithDate = 0;
		int countNumWithDate = 0;
		String objMsg = "";
		String msg= "";
		
		RestTemplate restTemplate = new RestTemplate();
		SyncDataBaseModelVo vo=new SyncDataBaseModelVo();
		List<RPatientModel> sourceObj = new ArrayList<RPatientModel>();//sourceList
		Map<String,Object> map = new HashMap<String,Object>();
		List<String> msgLst = new LinkedList<String>();
		
		try {
			if ("".equals(whichDay)) {
				countNumWithDate = rPatientMapper.countNum();
			}else {
				countNumWithDate = rPatientMapper.countNumWithDate(whichDay);
			}
			totalPageNumWithDate = countNumWithDate/PAGESIZE + 1;
			System.out.println("zmap_r_patient 总数:" + countNumWithDate);
			System.out.println("zmap_r_patient 每页:" + PAGESIZE + ",共:" + totalPageNumWithDate + "页");
		}catch (Exception e) {
			e.printStackTrace();
			vo.setId(UUID.randomUUID().toString());
			vo.setMsg_type("rPatient");
			vo.setMsg_des("read patient from his");
			vo.setMsg_ds("ZLHIS.病人信息");
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
				sourceObj = rPatientMapper.selectByPagination(map);
			}else {
				sourceObj = rPatientMapper.selectByPaginationWithDateTime(map);
			}
			int sourceObjSize = sourceObj.size();
			if(sourceObjSize == 0){
				vo.setId(UUID.randomUUID().toString());
				vo.setMsg_type("rPatient");
				vo.setMsg_des("read patient from his");
				vo.setMsg_ds("ZLHIS.病人信息");
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
				vo.setMsg_type("rPatient");
				vo.setMsg_des("read patient from his");
				vo.setMsg_ds("ZLHIS.病人信息");
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
				this.jmsMessagingTemplate.convertAndSend(this.baseDataQueue, msgLst.get(k));
			}
			msgLst.clear();
		}
	}
	
	/**
	 *  zmap_l_acct_item 
	 * @param 
	 * @throws Exception 
	 */
	public void readLAcctItem2MQ(String whichDay) {
		int totalPageNum = 0;//总页数 countNum/PAGESIZE + 1
		int totalPageNumWithDate = 0;
		int countNumWithDate = 0;
		String objMsg = "";
		String msg= "";
		
		RestTemplate restTemplate = new RestTemplate();
		SyncDataBaseModelVo vo=new SyncDataBaseModelVo();
		List<LAcctItemModel> sourceObj = new ArrayList<LAcctItemModel>();//sourceList
		Map<String,Object> map = new HashMap<String,Object>();
		List<String> msgLst = new LinkedList<String>();
		
		try {
			if ("".equals(whichDay)) {
				countNumWithDate = lAcctItemMapper.countNum();
			}else {
				countNumWithDate = lAcctItemMapper.countNumWithDate(whichDay);
			}
			totalPageNumWithDate = countNumWithDate/PAGESIZE + 1;
			System.out.println("zmap_l_acct_item 总数:" + countNumWithDate);
			System.out.println("zmap_l_acct_item 每页:" + PAGESIZE + ",共:" + totalPageNumWithDate + "页");
		}catch (Exception e) {
			e.printStackTrace();
			vo.setId(UUID.randomUUID().toString());
			vo.setMsg_type("lAcctItem");
			vo.setMsg_des("read acct_item from his");
			vo.setMsg_ds("ZLHIS.待定");
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
				sourceObj = lAcctItemMapper.selectByPagination(map);
			}else {
				sourceObj = lAcctItemMapper.selectByPaginationWithDateTime(map);
			}
			int sourceObjSize = sourceObj.size();
			if(sourceObjSize == 0){
				vo.setId(UUID.randomUUID().toString());
				vo.setMsg_type("lAcctItem");
				vo.setMsg_des("read acct_item from his");
				vo.setMsg_ds("ZLHIS.待定");
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
				vo.setMsg_type("lAcctItem");
				vo.setMsg_des("read acct_item from his");
				vo.setMsg_ds("ZLHIS.待定");
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
				this.jmsMessagingTemplate.convertAndSend(this.baseDataQueue, msgLst.get(k));
			}
			msgLst.clear();
		}
	}
	
	/**
	 *  zmap_l_examine 
	 * @param 
	 * @throws Exception 
	 */
	public void readLExamine2MQ(String whichDay) {
		int totalPageNum = 0;//总页数 countNum/PAGESIZE + 1
		int totalPageNumWithDate = 0;
		int countNumWithDate = 0;
		String objMsg = "";
		String msg= "";
		
		RestTemplate restTemplate = new RestTemplate();
		SyncDataBaseModelVo vo=new SyncDataBaseModelVo();
		List<LExamineModel> sourceObj = new ArrayList<LExamineModel>();//sourceList
		Map<String,Object> map = new HashMap<String,Object>();
		List<String> msgLst = new LinkedList<String>();
		
		try {
			if ("".equals(whichDay)) {
				countNumWithDate = lExamineMapper.countNum();
			}else {
				countNumWithDate = lExamineMapper.countNumWithDate(whichDay);
			}
			totalPageNumWithDate = countNumWithDate/PAGESIZE + 1;
			System.out.println("zmap_l_examine 总数:" + countNumWithDate);
			System.out.println("zmap_l_examine 每页:" + PAGESIZE + ",共:" + totalPageNumWithDate + "页");
		}catch (Exception e) {
			e.printStackTrace();
			vo.setId(UUID.randomUUID().toString());
			vo.setMsg_type("lExamine");
			vo.setMsg_des("read examine from his");
			vo.setMsg_ds("ZLHIS.待定");
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
				sourceObj = lExamineMapper.selectByPagination(map);
			}else {
				sourceObj = lExamineMapper.selectByPaginationWithDateTime(map);
			}
			int sourceObjSize = sourceObj.size();
			if(sourceObjSize == 0){
				vo.setId(UUID.randomUUID().toString());
				vo.setMsg_type("lExamine");
				vo.setMsg_des("read examine from his");
				vo.setMsg_ds("ZLHIS.待定");
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
				vo.setMsg_type("lExamine");
				vo.setMsg_des("read examine from his");
				vo.setMsg_ds("ZLHIS.待定");
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
				this.jmsMessagingTemplate.convertAndSend(this.baseDataQueue, msgLst.get(k));
			}
			msgLst.clear();
		}
	}
	
	/**
	 *  zmap_l_fee_item 
	 * @param 
	 * @throws Exception 
	 */
	public void readLFeeItem2MQ(String whichDay) {
		int totalPageNum = 0;//总页数 countNum/PAGESIZE + 1
		int totalPageNumWithDate = 0;
		int countNumWithDate = 0;
		String objMsg = "";
		String msg= "";
		
		RestTemplate restTemplate = new RestTemplate();
		SyncDataBaseModelVo vo=new SyncDataBaseModelVo();
		List<LFeeItemModel> sourceObj = new ArrayList<LFeeItemModel>();//sourceList
		Map<String,Object> map = new HashMap<String,Object>();
		List<String> msgLst = new LinkedList<String>();
		
		try {
			if ("".equals(whichDay)) {
				countNumWithDate = lFeeItemMapper.countNum();
			}else {
				countNumWithDate = lFeeItemMapper.countNumWithDate(whichDay);
			}
			totalPageNumWithDate = countNumWithDate/PAGESIZE + 1;
			System.out.println("zmap_l_fee_item 总数:" + countNumWithDate);
			System.out.println("zmap_l_fee_item 每页:" + PAGESIZE + ",共:" + totalPageNumWithDate + "页");
		}catch (Exception e) {
			e.printStackTrace();
			vo.setId(UUID.randomUUID().toString());
			vo.setMsg_type("lFeeItem");
			vo.setMsg_des("read fee_item from his");
			vo.setMsg_ds("ZLHIS.待定");
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
				sourceObj = lFeeItemMapper.selectByPagination(map);
			}else {
				sourceObj = lFeeItemMapper.selectByPaginationWithDateTime(map);
			}
			int sourceObjSize = sourceObj.size();
			if(sourceObjSize == 0){
				vo.setId(UUID.randomUUID().toString());
				vo.setMsg_type("lFeeItem");
				vo.setMsg_des("read fee_item from his");
				vo.setMsg_ds("ZLHIS.待定");
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
				vo.setMsg_type("lFeeItem");
				vo.setMsg_des("read fee_item from his");
				vo.setMsg_ds("ZLHIS.待定");
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
				this.jmsMessagingTemplate.convertAndSend(this.baseDataQueue, msgLst.get(k));
			}
			msgLst.clear();
		}
	}
	
	/**
	 *  zmap_l_operation 
	 * @param 
	 * @throws Exception 
	 */
	public void readLOperation2MQ(String whichDay) {
		int totalPageNum = 0;//总页数 countNum/PAGESIZE + 1
		int totalPageNumWithDate = 0;
		int countNumWithDate = 0;
		String objMsg = "";
		String msg= "";
		
		RestTemplate restTemplate = new RestTemplate();
		SyncDataBaseModelVo vo=new SyncDataBaseModelVo();
		List<LOperationModel> sourceObj = new ArrayList<LOperationModel>();//sourceList
		Map<String,Object> map = new HashMap<String,Object>();
		List<String> msgLst = new LinkedList<String>();
		
		try {
			if ("".equals(whichDay)) {
				countNumWithDate = lOperationMapper.countNum();
			}else {
				countNumWithDate = lOperationMapper.countNumWithDate(whichDay);
			}
			totalPageNumWithDate = countNumWithDate/PAGESIZE + 1;
			System.out.println("zmap_l_operation 总数:" + countNumWithDate);
			System.out.println("zmap_l_operation 每页:" + PAGESIZE + ",共:" + totalPageNumWithDate + "页");
		}catch (Exception e) {
			e.printStackTrace();
			vo.setId(UUID.randomUUID().toString());
			vo.setMsg_type("lOperation");
			vo.setMsg_des("read operation from his");
			vo.setMsg_ds("ZLHIS.待定");
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
				sourceObj = lOperationMapper.selectByPagination(map);
			}else {
				sourceObj = lOperationMapper.selectByPaginationWithDateTime(map);
			}
			int sourceObjSize = sourceObj.size();
			if(sourceObjSize == 0){
				vo.setId(UUID.randomUUID().toString());
				vo.setMsg_type("lOperation");
				vo.setMsg_des("read operation from his");
				vo.setMsg_ds("ZLHIS.待定");
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
				vo.setMsg_type("lOperation");
				vo.setMsg_des("read operation from his");
				vo.setMsg_ds("ZLHIS.待定");
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
				this.jmsMessagingTemplate.convertAndSend(this.baseDataQueue, msgLst.get(k));
			}
			msgLst.clear();
		}
	}
}
