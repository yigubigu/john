package com.zmap.esb.task;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.zmap.esb.mq.MessageHandlerService;
import com.zmap.esb.service.ZmapClinicSyncService;
import com.zmap.esb.service.ZmapHospitalizeSyncService;
import com.zmap.framework.esb.model.SyncDataBaseModelVo;

@Component
public class EventPublisher {
    
	@Autowired
	private ZmapClinicSyncService zmapClinicSyncService;
	@Autowired
	private ZmapHospitalizeSyncService zmapHospitalizeSyncService;
	
//	@Autowired
//	private MessageHandlerService messageHandlerService;
	
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private Integer count0 = 1;
    private Integer count1 = 1;
    private Integer count2 = 1;
    

    @Scheduled(cron = "0 2 13 * * ?") 
    public void reportCurrentTime() throws InterruptedException {
        System.out.println(String.format("---第%s次执行开始，当前时间为：%s", count0, dateFormat.format(new Date())));
//        zmapClinicSyncService.zmapClincSync();//调用service启用同步
//        zmapHospitalizeSyncService.zmapHospitalizeSync();
       System.out.println(String.format("---第%s次执行结束，当前时间为：%s", count0, dateFormat.format(new Date())));
    }
    
    
   /* @Scheduled(cron = "0/5 * *  * * ? ") 
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
//
//    @Scheduled(fixedDelay = 5000)
//    public void reportCurrentTimeAfterSleep() throws InterruptedException {
//        System.out.println(String.format("===第%s次执行，当前时间为：%s", count1++, dateFormat.format(new Date())));
//    }
//    
//    @Scheduled(cron = "*/5 * * * * *")
//    public void reportCurrentTimeCron() throws InterruptedException {
//        System.out.println(String.format("+++第%s次执行，当前时间为：%s", count2++, dateFormat.format(new Date())));
//    }
//
//    @Scheduled(fixedRate = 5000)
//    public void reportCurrentTime() throws InterruptedException {
//    	System.out.println(String.format("---第%s次执行，当前时间为：%s", count0++, dateFormat.format(new Date())));
//    	com.zmap.his.model.PatientRegistration sourceObj =  this.patientRegistrationMapper.selectTop1();//1、select
//    	
//    	PatienRegistrationVO destObj = ValueObjectBuilder.buildPaientRegistration(sourceObj);//2、注入中间model
//    	RestTemplate restTemplate = new RestTemplate();
//    	
//    	restTemplate.postForObject("http://localhost:8080/api/patient/registrations", destObj, PatienRegistrationVO.class); //3、 写入zmap              
//    }
}
