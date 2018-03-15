package com.zmap.esb.task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.jms.JMSException;

import org.apache.activemq.command.ActiveMQObjectMessage;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.zmap.framework.esb.model.SyncDataBaseModelVo;
import com.zmap.his.basedata.model.ZmapLAcctItemVO;
import com.zmap.his.basedata.model.ZmapLExamineVO;
import com.zmap.his.basedata.model.ZmapLFeeItemVO;
import com.zmap.his.basedata.model.ZmapLOperationVO;
import com.zmap.his.basedata.model.ZmapRDoctorVO;
import com.zmap.his.basedata.model.ZmapRPatientVO;
import com.zmap.his.clinic.model.JbPrepayChargeSyncVO;
import com.zmap.his.clinic.model.JbTransClinicSyncVO;
import com.zmap.his.clinic.model.ZmapClinicCM1SyncVO;
import com.zmap.his.clinic.model.ZmapClinicCM2ChargeSyncVO;
import com.zmap.his.clinic.model.ZmapClinicCM2MOrderSyncVO;
import com.zmap.his.hospitalize.model.ZmapHospitalizeHM1HospitalizeSyncVO;
import com.zmap.his.hospitalize.model.ZmapHospitalizeHM2ChargeSyncVO;


@Component
public class MessageReceiverTask {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private SimpleDateFormat sdfWithTime=new SimpleDateFormat("yyyy-MM-dd hh:ss:mm");//定义日期格式
	RestTemplate restTemplate = new RestTemplate();
	ActiveMQTextMessage message = new ActiveMQTextMessage();
	
	Map<String,Object> deptMap =  new HashMap<String,Object>();
	int countNum = 1;
	String zmapDeptCode = "";
	
	
	@JmsListener(destination = "sqlserver.data.queue", concurrency="10")
	public void receiveQueue(Object obj) {
		try {
			message=(ActiveMQTextMessage) obj;
			String msg = message.getText();
			String errLogMsg = "";
			Object object = null;
			System.out.println("收到的第" + countNum++ + "条\n消息为：" + msg);
			if(msg == null || "".equals(msg)) {
				System.out.println("本次消息的text为空 ");

			}else {
				boolean insrtResult = false;
				
				SyncDataBaseModelVo modelMQVO = (SyncDataBaseModelVo)JSON.parseObject(msg, SyncDataBaseModelVo.class); 
				String msg_type = modelMQVO.getMsg_type();
				
				try {
					//待 mq定义标准xml格式后，读取xml获取， to do
					if("hos_hos".equals(msg_type)) {
						ZmapHospitalizeHM1HospitalizeSyncVO modelVO = (ZmapHospitalizeHM1HospitalizeSyncVO)JSON.parseObject(modelMQVO.getMsg(), ZmapHospitalizeHM1HospitalizeSyncVO.class);
						insrtResult = restTemplate.postForObject("http://localhost:9098/api/hospitalize/insert2ZmapHos?input={input}", modelVO, Boolean.class, modelVO);
						modelMQVO.setId(UUID.randomUUID().toString());
						modelMQVO.setMsg_des("write hospital to zmap");
						modelMQVO.setMsg_ds("zmap.zmap_h_m1_hospitalize");
						modelMQVO.setCreate_time(sdfWithTime.format(new Date()));
						object = modelVO;
					}else if("hos_charge".equals(msg_type)) {
						ZmapHospitalizeHM2ChargeSyncVO modelVO = (ZmapHospitalizeHM2ChargeSyncVO)JSON.parseObject(modelMQVO.getMsg(), ZmapHospitalizeHM2ChargeSyncVO.class);
						//插入到 zmap hos_charge表
						insrtResult = restTemplate.postForObject("http://localhost:9098/api/hospitalize/insert2ZmapHosCharge?input={input}", modelVO, Boolean.class, modelVO);
						modelMQVO.setId(UUID.randomUUID().toString());
						modelMQVO.setMsg_des("write hospital_charge to zmap");
						modelMQVO.setMsg_ds("zmap.zmap_h_m2_charge");
						modelMQVO.setCreate_time(sdfWithTime.format(new Date()));
						object = modelVO;
					}else if("cln_cln".equals(msg_type)) {
						ZmapClinicCM1SyncVO modelVO = (ZmapClinicCM1SyncVO)JSON.parseObject(modelMQVO.getMsg(), ZmapClinicCM1SyncVO.class);
						insrtResult = restTemplate.postForObject("http://localhost:9098/api/clinic/insert2ZmapClinic?input={input}", modelVO, Boolean.class, modelVO);
						modelMQVO.setId(UUID.randomUUID().toString());
						modelMQVO.setMsg_des("write clinic to zmap");
						modelMQVO.setMsg_ds("zmap.zmap_c_m1_clinics");
						modelMQVO.setCreate_time(sdfWithTime.format(new Date()));
						object = modelVO;
					}else if("cln_charge".equals(msg_type)) {
						ZmapClinicCM2ChargeSyncVO modelVO = (ZmapClinicCM2ChargeSyncVO)JSON.parseObject(modelMQVO.getMsg(), ZmapClinicCM2ChargeSyncVO.class);
						insrtResult = restTemplate.postForObject("http://localhost:9098/api/clinic/insert2ZmapClinicCharge?input={input}", modelVO, Boolean.class, modelVO);
						modelMQVO.setId(UUID.randomUUID().toString());
						modelMQVO.setMsg_des("write clinic_charge to zmap");
						modelMQVO.setMsg_ds("zmap.zmap_c_m2_charge");
						modelMQVO.setCreate_time(sdfWithTime.format(new Date()));
						object = modelVO;
					}else if("morder".equals(msg_type)) {
						ZmapClinicCM2MOrderSyncVO modelVO = (ZmapClinicCM2MOrderSyncVO)JSON.parseObject(modelMQVO.getMsg(), ZmapClinicCM2MOrderSyncVO.class);
						insrtResult = restTemplate.postForObject("http://localhost:9098/api/clinic/insert2ZmapMOrder?input={input}", modelVO, Boolean.class, modelVO);
						modelMQVO.setId(UUID.randomUUID().toString());
						modelMQVO.setMsg_des("write morder to zmap");
						modelMQVO.setMsg_ds("zmap.zmap_c_m2_morder");
						modelMQVO.setCreate_time(sdfWithTime.format(new Date()));
						object = modelVO;
					}else if("jiangbei_trans_clinic".equals(msg_type)) {
						JbTransClinicSyncVO modelVO = (JbTransClinicSyncVO)JSON.parseObject(modelMQVO.getMsg(), JbTransClinicSyncVO.class);
						insrtResult = restTemplate.postForObject("http://localhost:9098/api/clinic/insert2JbTransClinic?input={input}", modelVO, Boolean.class, modelVO);
						modelMQVO.setId(UUID.randomUUID().toString());
						modelMQVO.setMsg_des("write jiangbei_trans_clinic to zmap");
						modelMQVO.setMsg_ds("zmap.jb_trans_clinic");
						modelMQVO.setCreate_time(sdfWithTime.format(new Date()));
						object = modelVO;
					}else if("jb_prepay_charge".equals(msg_type)) {
						JbPrepayChargeSyncVO modelVO = (JbPrepayChargeSyncVO)JSON.parseObject(modelMQVO.getMsg(), JbPrepayChargeSyncVO.class);
						insrtResult = restTemplate.postForObject("http://localhost:9098/api/clinic/insert2JbPrepayCharge?input={input}", modelVO, Boolean.class, modelVO);
						modelMQVO.setId(UUID.randomUUID().toString());
						modelMQVO.setMsg_des("write jb_prepay_charge to zmap");
						modelMQVO.setMsg_ds("zmap.jb_prepay_charge");
						modelMQVO.setCreate_time(sdfWithTime.format(new Date()));
						object = modelVO;
					}
					
					if (insrtResult) {
						modelMQVO.setMsg(object.toString());
						modelMQVO.setRead_or_write("w");
						modelMQVO.setMsg_status("1");
						errLogMsg = JSON.toJSONString(modelMQVO);
						restTemplate.postForObject("http://localhost:9098/api/esb/syncdata/savedata?input={input}", errLogMsg, String.class, errLogMsg);
//						restTemplate.postForObject("http://localhost:9098/api/esb/syncdata/updateMsg_Status?input={input}", modelMQVO, Integer.class,modelMQVO);
					}
				}catch (Exception e) {
					e.printStackTrace();
					modelMQVO.setRead_or_write("w");
					modelMQVO.setMsg_status("2");//同步失败
					modelMQVO.setMsg_error(e.getMessage());
					errLogMsg = JSON.toJSONString(modelMQVO);
					restTemplate.postForObject("http://localhost:9098/api/esb/syncdata/insert2ErrLog?input={input}", errLogMsg, String.class, errLogMsg);
				}
			}
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@JmsListener(destination = "base.data.queue", concurrency="10")
	public void receiveBaseDataQueue(Object obj) {
		try {
			message=(ActiveMQTextMessage) obj;
			String msg = message.getText();
			String errLogMsg = "";
			Object object = null;
			System.out.println("收到的第" + countNum++ + "条\n消息为：" + msg);
			if(msg == null || "".equals(msg)) {
				System.out.println("本次消息的text为空 ");

			}else {
				boolean insrtResult = false;
				
				SyncDataBaseModelVo modelMQVO = (SyncDataBaseModelVo)JSON.parseObject(msg, SyncDataBaseModelVo.class); 
				String msg_type = modelMQVO.getMsg_type();
				
				try {
					//待 mq定义标准xml格式后，读取xml获取， to do
					if("rDoctor".equals(msg_type)) {
						ZmapRDoctorVO modelVO = (ZmapRDoctorVO)JSON.parseObject(modelMQVO.getMsg(), ZmapRDoctorVO.class);
						insrtResult = restTemplate.postForObject("http://localhost:9098/api/basedata/RDoctorSync?input={input}", modelVO, Boolean.class, modelVO);
						modelMQVO.setId(UUID.randomUUID().toString());
						modelMQVO.setMsg_des("write doctor to zmap");
						modelMQVO.setMsg_ds("zmap.zmap_r_doctor");
						modelMQVO.setCreate_time(sdfWithTime.format(new Date()));
						object = modelVO;
					}else if("rPatient".equals(msg_type)) {
						ZmapRPatientVO modelVO = (ZmapRPatientVO)JSON.parseObject(modelMQVO.getMsg(), ZmapRPatientVO.class);
						//插入到 zmap hos_charge表
						insrtResult = restTemplate.postForObject("http://localhost:9098/api/basedata/RPatientSync?input={input}", modelVO, Boolean.class, modelVO);
						modelMQVO.setId(UUID.randomUUID().toString());
						modelMQVO.setMsg_des("write patient to zmap");
						modelMQVO.setMsg_ds("zmap.zmap_r_patient");
						modelMQVO.setCreate_time(sdfWithTime.format(new Date()));
						object = modelVO;
					}else if("lAcctItem".equals(msg_type)) {
						ZmapLAcctItemVO modelVO = (ZmapLAcctItemVO)JSON.parseObject(modelMQVO.getMsg(), ZmapLAcctItemVO.class);
						//插入到 zmap hos_charge表
						insrtResult = restTemplate.postForObject("http://localhost:9098/api/basedata/LAcctItemSync?input={input}", modelVO, Boolean.class, modelVO);
						modelMQVO.setId(UUID.randomUUID().toString());
						modelMQVO.setMsg_des("write acct_item to zmap");
						modelMQVO.setMsg_ds("zmap.zmap_l_acct_item");
						modelMQVO.setCreate_time(sdfWithTime.format(new Date()));
						object = modelVO;
					}else if("lExamine".equals(msg_type)) {
						ZmapLExamineVO modelVO = (ZmapLExamineVO)JSON.parseObject(modelMQVO.getMsg(), ZmapLExamineVO.class);
						//插入到 zmap hos_charge表
						insrtResult = restTemplate.postForObject("http://localhost:9098/api/basedata/LExamineSync?input={input}", modelVO, Boolean.class, modelVO);
						modelMQVO.setId(UUID.randomUUID().toString());
						modelMQVO.setMsg_des("write examine to zmap");
						modelMQVO.setMsg_ds("zmap.zmap_l_examine");
						modelMQVO.setCreate_time(sdfWithTime.format(new Date()));
						object = modelVO;
					}else if("lFeeItem".equals(msg_type)) {
						ZmapLFeeItemVO modelVO = (ZmapLFeeItemVO)JSON.parseObject(modelMQVO.getMsg(), ZmapLFeeItemVO.class);
						//插入到 zmap hos_charge表
						insrtResult = restTemplate.postForObject("http://localhost:9098/api/basedata/LFeeItemSync?input={input}", modelVO, Boolean.class, modelVO);
						modelMQVO.setId(UUID.randomUUID().toString());
						modelMQVO.setMsg_des("write fee_item to zmap");
						modelMQVO.setMsg_ds("zmap.zmap_l_fee_item");
						modelMQVO.setCreate_time(sdfWithTime.format(new Date()));
						object = modelVO;
					}else if("lOperation".equals(msg_type)) {
						ZmapLOperationVO modelVO = (ZmapLOperationVO)JSON.parseObject(modelMQVO.getMsg(), ZmapLOperationVO.class);
						//插入到 zmap hos_charge表
						insrtResult = restTemplate.postForObject("http://localhost:9098/api/basedata/LOperationSync?input={input}", modelVO, Boolean.class, modelVO);
						modelMQVO.setId(UUID.randomUUID().toString());
						modelMQVO.setMsg_des("write operation to zmap");
						modelMQVO.setMsg_ds("zmap.zmap_l_operation");
						modelMQVO.setCreate_time(sdfWithTime.format(new Date()));
						object = modelVO;
					}
					
					if (insrtResult) {
						modelMQVO.setMsg(object.toString());
						modelMQVO.setRead_or_write("w");
						modelMQVO.setMsg_status("1");
						errLogMsg = JSON.toJSONString(modelMQVO);
						restTemplate.postForObject("http://localhost:9098/api/esb/syncdata/savedata?input={input}", errLogMsg, String.class, errLogMsg);
//						restTemplate.postForObject("http://localhost:9098/api/esb/syncdata/updateMsg_Status?input={input}", modelMQVO, Integer.class,modelMQVO);
					}
				}catch (Exception e) {
					e.printStackTrace();
					modelMQVO.setRead_or_write("w");
					modelMQVO.setMsg_status("2");//同步失败
					modelMQVO.setMsg_error(e.getMessage());
					errLogMsg = JSON.toJSONString(modelMQVO);
					restTemplate.postForObject("http://localhost:9098/api/esb/syncdata/insert2ErrLog?input={input}", errLogMsg, String.class, errLogMsg);
				}
			}
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*@JmsListener(destination = "hospital.out")
	public void receiveQueue(Object obj) {
		ActiveMQTextMessage message=(ActiveMQTextMessage) obj;
		//数据解析服务
		//存入消息集成交换数据表用于数据同步交换检查和消息统计（调用数据服务层接口）
		try {
			String msg=message.getText();
			if(msg!=null&&!"".equals(msg)){
				System.out.println("activeMQ"+msg);
				RestTemplate restTemplate=new RestTemplate();
				restTemplate.postForObject("http://localhost:9098/api/esb/syncdata/savedata?input={input}", null, String.class,msg);
			}

			
		} catch (JMSException e) {
			logger.debug("error"+e.toString());
		}
		logger.debug("obj type" + obj.getClass());
		logger.debug("obj content " + obj.toString());
		//todo -- call rest api to store data into mysql		
	}*/
	
}
