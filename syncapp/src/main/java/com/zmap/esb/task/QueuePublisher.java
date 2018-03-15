package com.zmap.esb.task;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.zmap.esb.mq.MessageHandlerService;
import com.zmap.esb.service.ZmapBaseDataSyncService;
import com.zmap.esb.service.ZmapClinicSyncService;
import com.zmap.esb.service.ZmapHospitalizeSyncService;
import com.zmap.framework.esb.model.SyncDataBaseModelVo;
import com.zmap.his.hospitalize.mapper.HisHospitalizeHM2ChargeSyncMapper;
import com.zmap.his.hospitalize.model.HisHospitalizeHM2ChargeSyncModel;
import com.zmap.his.hospitalize.model.ZmapHospitalizeHM2ChargeSyncVO;


@Component
public class QueuePublisher {
	
	private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//定义日期格式
//	private String curDate=sdf.format(new Date());//获取系统当前日期
//	private String whichDay="2016-10-09";
//	private String whichDay="2016-10";
	private String whichDay="";
	
	@Autowired
	private ZmapClinicSyncService zmapClinicSyncService;
	@Autowired
	private ZmapHospitalizeSyncService zmapHospitalizeSyncService;
	@Autowired
	private ZmapBaseDataSyncService zmapBaseDataSyncService;

	@Scheduled(cron = "0 24 16 3 3 ?") 
	public void publishEventToQueue() throws InterruptedException {
		String startTime = sdf.format(new Date());
		System.out.println("read to mq开始执行，当前时间为：" + startTime);
		try {
//			zmapClinicSyncService.readCln2MQ(whichDay);
//			zmapHospitalizeSyncService.readHos2MQ(whichDay);
//			zmapClinicSyncService.readClnCharge2MQ(whichDay);
//			zmapClinicSyncService.readMOrder2MQ(whichDay);
			zmapHospitalizeSyncService.readHosCharge2MQ(whichDay);
//			zmapClinicSyncService.readJbTransCln2MQ(whichDay);
//			zmapClinicSyncService.readJbPrepayCharge2MQ(whichDay);
			System.out.println("read to mq本次执行结束，开始时间为："+ startTime + ", 当前时间为：" + sdf.format(new Date()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("本次同步异常：" + e.getMessage());
			return;
		}
	}
	
//	@Scheduled(fixedRate = 8*60*60*1000)
	@Scheduled(cron = "0 5 17 1 3 ?") 
	public void publishBaseData2Queue() throws InterruptedException {
		String startTime = sdf.format(new Date());
		System.out.println("base data read to mq开始执行，当前时间为：" + startTime);
		try {
//			zmapBaseDataSyncService.readRDoctor2MQ(whichDay);
//			zmapBaseDataSyncService.readRPatient2MQ(whichDay);
//			zmapBaseDataSyncService.readLAcctItem2MQ(whichDay);
			System.out.println("base data read to mq本次执行结束，开始时间为："+ startTime + ", 当前时间为：" + sdf.format(new Date()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("本次同步异常：" + e.getMessage());
			return;
		}
	}
    /*@Scheduled(cron = "0/5 * *  * * ? ") 
    public void testMq() throws InterruptedException {

    	SyncDataBaseModelVo vo=new SyncDataBaseModelVo();
    	SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-hh hh:ss:mm");
    	String time=format.format(new Date());
    	vo.setCreate_time(time);
    	vo.setMsg("select * from salary");
    	vo.setMsg_des("salary query");
    	vo.setMsg_from("HIS");
    	vo.setMsg_result("1");
    	vo.setMsg_type("1001");
    	vo.setMsg_status("0");
    	try {
			String msg=JSON.toJSONString(vo);
			messageHandlerService.sendTextToQueue(msg);
		} catch (Exception e) {
		
			e.printStackTrace();
		}
    	
    }*/


}
