package com.zmap.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zmap.zmap.clinic.mapper.JbPrepayChargeSyncMapper;
import com.zmap.zmap.clinic.mapper.JbTransClinicSyncMapper;
import com.zmap.zmap.clinic.mapper.ZmapClinicCM1SyncMapper;
import com.zmap.zmap.clinic.mapper.ZmapClinicCM2ChargeSyncMapper;
import com.zmap.zmap.clinic.mapper.ZmapClinicCM2DiagnoseSyncMapper;
import com.zmap.zmap.clinic.mapper.ZmapClinicCM2DrugUseSyncMapper;
import com.zmap.zmap.clinic.mapper.ZmapClinicCM2ExamineSyncMapper;
import com.zmap.zmap.clinic.mapper.ZmapClinicCM2MOrderSyncMapper;
import com.zmap.zmap.clinic.model.JbPrepayChargeSyncModel;
import com.zmap.zmap.clinic.model.JbTransClinicSyncModel;
import com.zmap.zmap.clinic.model.ZmapClinicCM1SyncModel;
import com.zmap.zmap.clinic.model.ZmapClinicCM2ChargeSyncModel;
import com.zmap.zmap.clinic.model.ZmapClinicCM2DiagnoseSyncModel;
import com.zmap.zmap.clinic.model.ZmapClinicCM2DrugUseSyncModel;
import com.zmap.zmap.clinic.model.ZmapClinicCM2ExamineSyncModel;
import com.zmap.zmap.clinic.model.ZmapClinicCM2MOrderSyncModel;
import com.zmap.zmap.framework.base.mapper.ZmapLFeeItemMapper;
import com.zmap.zmap.framework.base.mapper.ZmapRDeptMapper;
import com.zmap.zmap.framework.base.mapper.ZmapRDoctorMapper;
import com.zmap.zmap.framework.base.mapper.ZmapRPatientMapper;
import com.zmap.zmap.framework.base.model.ZmapLFeeItemModel;

@RestController
@RequestMapping("/api/clinic")
public class ZmapClinicSyncController {
	
	@Autowired
	private ZmapClinicCM1SyncMapper zmapClinicCM1SyncMapper;
	@Autowired
	private ZmapClinicCM2ChargeSyncMapper zmapClinicCM2ChargeSyncMapper;
//	@Autowired
//	private ZmapClinicCM2DiagnoseSyncMapper zmapClinicCM2DiagnoseSyncMapper;
	@Autowired
	private ZmapClinicCM2DiagnoseSyncMapper zmapClinicCM2DiagnoseSyncMapper;

	@Autowired
	private ZmapClinicCM2DrugUseSyncMapper zmapClinicCM2DrugUseSyncMapper;
	@Autowired
	private ZmapClinicCM2ExamineSyncMapper zmapClinicCM2ExamineSyncMapper;
	
	@Autowired
	private ZmapRDeptMapper zmapRDeptMapper;
	
	@Autowired
	private ZmapRDoctorMapper zmapRDoctorSyncMapper;
	
	@Autowired
	private  ZmapRPatientMapper zmapRPatientSyncMapper;
	
	@Autowired
	private  ZmapLFeeItemMapper zmapLFeeItemMapper;
	
	@Autowired
	private  ZmapClinicCM2MOrderSyncMapper zmapClinicCM2MOrderSyncMapper;
	
	@Autowired
	private  JbTransClinicSyncMapper jbTransClinicSyncMapper;//先放在此处
	@Autowired
	private  JbPrepayChargeSyncMapper jbPrepayChargeSyncMapper;
	
	private int countClinic;
	private int countClinicCharge;
	private int countClinicDiagnose;
	private int countClinicDrugUse;
	private int countClinicExamine;
	private int countClinicRDoctor;
	private int countClinicRPatient;
	/**
	 * update zmap_m1_clinics
	 * @param input
	 * @return
	 */
	@RequestMapping("/clinicSync")
	int clinicSync(@RequestBody List<ZmapClinicCM1SyncModel> input) {
		
		/*
		 * http://localhost:9090/api/clinic/clinicsync
		 * curl -H "Content-Type: application/json" -X POST  -d '{"originalId":"111", "registrationNumber":111, "doctorId":"docker li", "servicedepartmentId":"xin zang ke", "patientCardNumber":"103432"}' 
		 */
		int inputsize = input.size();
		int num = 0;
		if (inputsize==0){
			try {
				throw new Exception("api/clinic/clinicsync的输入参数size为空！");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			//*********以下代码段为存在的记录先删除后插入，记录clinic_id***********
			String regcode = "";
			ZmapClinicCM1SyncModel  zmapSyncModel = new ZmapClinicCM1SyncModel();
			boolean deleteSuccess = false;
			//先判断是否已存在reg_code，存在的先删除再插入or直接跳过？
			for (int i =0; i<inputsize; i++){
				regcode = input.get(i).getRegCode().toString();
				zmapSyncModel = this.zmapClinicCM1SyncMapper.selectByHisPrimaryKey(regcode);
				if (zmapSyncModel!=null){
					deleteSuccess = this.zmapClinicCM1SyncMapper.deleteByHisPrimaryKey(regcode) > 0;//删除
					if(deleteSuccess){
						input.get(i).setClinicId(zmapSyncModel.getClinicId());//记录clinic_id
					}
				}
				
				System.out.println(++countClinic +" zmap_c_m1_clinics [reg_code:" + regcode + "]");
			}
			num = this.zmapClinicCM1SyncMapper.batchInsert(input);
			
			/*//*********以下代码段已存在的直接update，不存在的直接insert***********
			String regcode = "";
			ZmapClinicCM1SyncModel  ZmapClinicCM1SyncModel = new ZmapClinicCM1SyncModel();
			List<ZmapClinicCM1SyncModel> updateZmapClinicSyncModel = new ArrayList<ZmapClinicCM1SyncModel>();
			List<ZmapClinicCM1SyncModel> insertZmapClinicSyncModel = new ArrayList<ZmapClinicCM1SyncModel>();
			for (int i =0; i<inputsize; i++){
				regcode = input.get(i).getRegCode().toString();
				ZmapClinicCM1SyncModel = this.ZmapClinicCM1SyncMapper.selectByRegcode(regcode);
				if (ZmapClinicCM1SyncModel!=null){
					//已存在，update,同时从inputlist中删除
					updateZmapClinicSyncModel.add(input.get(i));
				}else{
					//不存在，insert
					insertZmapClinicSyncModel.add(input.get(i));
				}
			}
			if (updateZmapClinicSyncModel.size()!=0){
				num = this.ZmapClinicCM1SyncMapper.batchUpdate(updateZmapClinicSyncModel);
			}
			if (insertZmapClinicSyncModel.size()!=0){
				num = this.ZmapClinicCM1SyncMapper.insertBatch(insertZmapClinicSyncModel) + num;
			}*/
		}
		return countClinic;
		
	}
	/**
	 * 更新zmap_m2_charge表
	 * @param input
	 * @return
	 */
	@RequestMapping("/clinicCM2ChargeSync")
	int clinicCM2ChargeSync(@RequestBody List<ZmapClinicCM2ChargeSyncModel> input) {
		
		int inputsize = input.size();
		int num = 0;
		if (inputsize==0){
			try {
				throw new Exception("api/clinic/clinicChargeSync的输入参数size为空！");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			//*********以下代码段为存在的记录先删除后插入，记录clinic_id***********
			String regcode = "";
			ZmapClinicCM2ChargeSyncModel  zmapSyncModel = new ZmapClinicCM2ChargeSyncModel();
			boolean deleteSuccess = false;
			//先判断是否已存在reg_code，存在的先删除再插入or直接跳过？
			for (int i =0; i<inputsize; i++){
				regcode = input.get(i).getChargeCode().toString();
				zmapSyncModel = this.zmapClinicCM2ChargeSyncMapper.selectByHisPrimaryKey(regcode);
				if (zmapSyncModel!=null){
					deleteSuccess = this.zmapClinicCM2ChargeSyncMapper.deleteByHisPrimaryKey(regcode) > 0;//删除
					if(deleteSuccess){
						input.get(i).setClinicCode(zmapSyncModel.getClinicCode());//记录clinic_id
					}
				}
				System.out.println(++countClinicCharge +" zmap_c_m2_charge [reg_code:" + regcode + "]");
			}
			num = this.zmapClinicCM2ChargeSyncMapper.batchInsert(input);
			
			/*//*********已存在的直接update，不存在的直接insert***********
			String hisBusinessCode = "";
			ZmapClinicCM2ChargeSyncModel  zmapSyncModel = new ZmapClinicCM2ChargeSyncModel();
			List<ZmapClinicCM2ChargeSyncModel> updateZmapSyncModelList = new LinkedList<ZmapClinicCM2ChargeSyncModel>();
			List<ZmapClinicCM2ChargeSyncModel> insertZmapSyncModelList = new LinkedList<ZmapClinicCM2ChargeSyncModel>();
			for (int i =0; i<inputsize; i++){
				hisBusinessCode = input.get(i).getChargeCode();
				zmapSyncModel = this.ZmapClinicCM2ChargeSyncMapper.selectByChargecode(hisBusinessCode);
				if (zmapSyncModel!=null){
					//已存在，update,同时从inputlist中删除
					updateZmapSyncModelList.add(input.get(i));
				}else{
					//不存在，insert
					insertZmapSyncModelList.add(input.get(i));
				}
			}
			if (updateZmapSyncModelList.size()!=0){
				num = this.ZmapClinicCM2ChargeSyncMapper.batchUpdate(updateZmapSyncModelList);
			}
			if (insertZmapSyncModelList.size()!=0){
				num = this.ZmapClinicCM2ChargeSyncMapper.batchInsert(insertZmapSyncModelList) + num;
			}*/
		}
		return num;
		
	}
	/**
	 * 更新zmap_m2_diagnose表
	 * @param input
	 * @return
	 */
	@RequestMapping("/clinicDiagnoseSync")
	int clinicDiagnoseSync(@RequestBody List<ZmapClinicCM2DiagnoseSyncModel> input) {
		
		int inputsize = input.size();
		int num = 0;
		if (inputsize==0){
			try {
				throw new Exception("api/clinic/clinicDiagnoseSync的输入参数size为空！");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			//*********以下代码段为存在的记录先删除后插入，记录clinic_id***********
			String regcode = "";
			ZmapClinicCM2DiagnoseSyncModel  zmapSyncModel = new ZmapClinicCM2DiagnoseSyncModel();
			boolean deleteSuccess = false;
			//先判断是否已存在reg_code，存在的先删除再插入or直接跳过？
			for (int i =0; i<inputsize; i++){
				regcode = input.get(i).getDiagFlowCode().toString();
				zmapSyncModel = this.zmapClinicCM2DiagnoseSyncMapper.selectByHisPrimaryKey(regcode);
				if (zmapSyncModel!=null){
					deleteSuccess = this.zmapClinicCM2DiagnoseSyncMapper.deleteByHisPrimaryKey(regcode) > 0;//删除
					if(deleteSuccess){
						input.get(i).setClinicCode(zmapSyncModel.getClinicCode());//记录clinic_id
					}
				}
				System.out.println(++countClinicDiagnose +" zmap_m2_diagnose [reg_code:" + regcode + "]");
			}
			num = this.zmapClinicCM2DiagnoseSyncMapper.batchInsert(input);
			
			/*//*********已存在的直接update，不存在的直接insert***********
			String hisBusinessCode = "";
			ZmapClinicCM2DiagnoseSyncModel  zmapSyncModel = new ZmapClinicCM2DiagnoseSyncModel();
			List<ZmapClinicCM2DiagnoseSyncModel> updateZmapSyncModelList = new LinkedList<ZmapClinicCM2DiagnoseSyncModel>();
			List<ZmapClinicCM2DiagnoseSyncModel> insertZmapSyncModelList = new LinkedList<ZmapClinicCM2DiagnoseSyncModel>();
			for (int i =0; i<inputsize; i++){
				hisBusinessCode = input.get(i).getDiagFlowCode();//his primary key 
				zmapSyncModel = this.ZmapClinicCM2DiagnoseSyncMapper.selectByHisPrimaryKey(hisBusinessCode);
				if (zmapSyncModel!=null){
					//已存在，update
					updateZmapSyncModelList.add(input.get(i));
				}else{
					//不存在，insert
					insertZmapSyncModelList.add(input.get(i));
				}
			}
			if (updateZmapSyncModelList.size()!=0){
				num = this.ZmapClinicCM2DiagnoseSyncMapper.batchUpdate(updateZmapSyncModelList);
			}
			if (insertZmapSyncModelList.size()!=0){
				num = this.ZmapClinicCM2DiagnoseSyncMapper.batchInsert(insertZmapSyncModelList) + num;
			}*/
		}
		return num;
		
	}
	/**
	 * 更新zmap_m2_drug_use
	 * @param input
	 * @return
	 */
	@RequestMapping("/clinicDrugUseSync")
	int clinicDrugUseSync(@RequestBody List<ZmapClinicCM2DrugUseSyncModel> input) {
		
		int inputsize = input.size();
		int num = 0;
		if (inputsize==0){
			try {
				throw new Exception("api/clinic/clinicDrugUseSync的输入参数size为空！");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			//*********以下代码段为存在的记录先删除后插入，记录clinic_id***********
			String regcode = "";
			ZmapClinicCM2DrugUseSyncModel  zmapSyncModel = new ZmapClinicCM2DrugUseSyncModel();
			boolean deleteSuccess = false;
			//先判断是否已存在reg_code，存在的先删除再插入or直接跳过？
			for (int i =0; i<inputsize; i++){
				regcode = input.get(i).getDrugUseCode().toString();
				zmapSyncModel = this.zmapClinicCM2DrugUseSyncMapper.selectByHisPrimaryKey(regcode);
				if (zmapSyncModel!=null){
					deleteSuccess = this.zmapClinicCM2DrugUseSyncMapper.deleteByHisPrimaryKey(regcode) > 0;//删除
					if(deleteSuccess){
						input.get(i).setClinicCode(zmapSyncModel.getClinicCode());//记录clinic_id
					}
				}
				
				System.out.println(++countClinicDrugUse +" zmap_m2_drug_use [reg_code:" + regcode + "]");
			}
			num = this.zmapClinicCM2DrugUseSyncMapper.batchInsert(input);
			
			/*//*********已存在的直接update，不存在的直接insert***********
			String hisBusinessCode = "";
			ZmapClinicCM2DrugUseSyncModel  zmapSyncModel = new ZmapClinicCM2DrugUseSyncModel();
			List<ZmapClinicCM2DrugUseSyncModel> updateZmapSyncModelList = new LinkedList<ZmapClinicCM2DrugUseSyncModel>();
			List<ZmapClinicCM2DrugUseSyncModel> insertZmapSyncModelList = new LinkedList<ZmapClinicCM2DrugUseSyncModel>();
			for (int i =0; i<inputsize; i++){
				hisBusinessCode = input.get(i).getDrugUseCode();//his primary key 
				zmapSyncModel = this.ZmapClinicCM2DrugUseSyncMapper.selectByHisPrimaryKey(hisBusinessCode);
				if (zmapSyncModel!=null){
					//已存在，update
					updateZmapSyncModelList.add(input.get(i));
				}else{
					//不存在，insert
					insertZmapSyncModelList.add(input.get(i));
				}
			}
			if (updateZmapSyncModelList.size()!=0){
				num = this.ZmapClinicCM2DrugUseSyncMapper.batchUpdate(updateZmapSyncModelList);
			}
			if (insertZmapSyncModelList.size()!=0){
				num = this.ZmapClinicCM2DrugUseSyncMapper.batchInsert(insertZmapSyncModelList) + num;
			}*/
		}
		return num;
		
	}
	/**
	 * 更新zmap_m2_examine
	 * @param input
	 * @return
	 */
	@RequestMapping("/clinicExamineSync")
	int clinicExamineSync(@RequestBody List<ZmapClinicCM2ExamineSyncModel> input) {
		
		int inputsize = input.size();
		int num = 0;
		if (inputsize==0){
			try {
				throw new Exception("api/clinic/clinicExamineSync的输入参数size为空！");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			//*********以下代码段为存在的记录先删除后插入，记录clinic_id***********
			String regcode = "";
			ZmapClinicCM2ExamineSyncModel  zmapSyncModel = new ZmapClinicCM2ExamineSyncModel();
			boolean deleteSuccess = false;
			//先判断是否已存在reg_code，存在的先删除再插入or直接跳过？
			for (int i =0; i<inputsize; i++){
				regcode = input.get(i).getExamFlowCode().toString();
				zmapSyncModel = this.zmapClinicCM2ExamineSyncMapper.selectByHisPrimaryKey(regcode);
				if (zmapSyncModel!=null){
					deleteSuccess = this.zmapClinicCM2ExamineSyncMapper.deleteByHisPrimaryKey(regcode) > 0;//删除
					if(deleteSuccess){
						input.get(i).setClinicCode(zmapSyncModel.getClinicCode());//记录clinic_id
					}
				}
				System.out.println(++countClinicExamine +" zmap_m2_examine [reg_code:" + regcode + "]");
			}
			num = this.zmapClinicCM2ExamineSyncMapper.batchInsert(input);
			
			/*//*********已存在的直接update，不存在的直接insert***********
			String hisBusinessCode = "";
			ZmapClinicCM2ExamineSyncModel  zmapSyncModel = new ZmapClinicCM2ExamineSyncModel();
			List<ZmapClinicCM2ExamineSyncModel> updateZmapSyncModelList = new LinkedList<ZmapClinicCM2ExamineSyncModel>();
			List<ZmapClinicCM2ExamineSyncModel> insertZmapSyncModelList = new LinkedList<ZmapClinicCM2ExamineSyncModel>();
			for (int i =0; i<inputsize; i++){
				hisBusinessCode = input.get(i).getExamFlowCode();//his primary key 
				zmapSyncModel = this.ZmapClinicCM2ExamineSyncMapper.selectByHisPrimaryKey(hisBusinessCode);
				if (zmapSyncModel!=null){
					//已存在，update
					updateZmapSyncModelList.add(input.get(i));
				}else{
					//不存在，insert
					insertZmapSyncModelList.add(input.get(i));
				}
			}
			if (updateZmapSyncModelList.size()!=0){
				num = this.ZmapClinicCM2ExamineSyncMapper.batchUpdate(updateZmapSyncModelList);
			}
			if (insertZmapSyncModelList.size()!=0){
				num = this.ZmapClinicCM2ExamineSyncMapper.batchInsert(insertZmapSyncModelList) + num;
			}*/
		}
		return num;
		
	}
	
	
	/**
	 * update clinic_id
	 * 各表可统一update
	 * @return
	 */
	@RequestMapping("/updateClinicID")
	boolean updateClinicID() {
		boolean isSuccess = this.zmapClinicCM2ChargeSyncMapper.updateClinicID() > 0;
		isSuccess = isSuccess & this.zmapClinicCM2DiagnoseSyncMapper.updateClinicID() > 0;
		isSuccess = isSuccess & this.zmapClinicCM2DrugUseSyncMapper.updateClinicID() > 0;
		isSuccess = isSuccess & this.zmapClinicCM2ExamineSyncMapper.updateClinicID() > 0;
		return isSuccess;
	}
	/**
	 * update ChargeClinicIDWithDate
	 * @param curDate
	 * @return
	 */
	@RequestMapping("/updateChargeClinicIDWithDate")
	boolean updateChargeClinicIDWithDate(String curDate) {
		boolean isSuccess = this.zmapClinicCM2ChargeSyncMapper.updateClinicIDWithDate(curDate) > 0;
		return isSuccess;
	}
	/**
	 * update ChargeClinicID
	 * @param curDate
	 * @return
	 */
	@RequestMapping("/updateChargeClinicID")
	boolean updateChargeClinicID() {
		boolean isSuccess = this.zmapClinicCM2ChargeSyncMapper.updateClinicID() > 0;
		return isSuccess;
	}
	@RequestMapping("/sayhello")
	String sayhello() {
		return String.format("hello world");
	} 
	
	/********************************* 以下为单条插入 *************************************/
	/**
	 * 单条插入到zmap clinic 一级clinic表
	 * @param input
	 * @return
	 */
	@RequestMapping("/insert2ZmapClinic")
	boolean insert2ZmapClinic(@RequestBody ZmapClinicCM1SyncModel input) {
		
		boolean insertResult = false;
		int num = 0;
		if (input == null){
			try {
				throw new Exception("api/clinic/insert2ZmapClinic的输入参数size为空！");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			//*********以下代码段为存在的记录先删除后插入，记录clinic_id***********
			String hisPrimaryKey = "";
			ZmapClinicCM1SyncModel  zmapSyncModel = new ZmapClinicCM1SyncModel();
			boolean deleteSuccess = false;
			//根据his主键先判断是否已存在，存在的先删除再插入or直接跳过？
			hisPrimaryKey = input.getRegCode().toString();
			zmapSyncModel = this.zmapClinicCM1SyncMapper.selectByHisPrimaryKey(hisPrimaryKey);
			if (zmapSyncModel!=null){
				deleteSuccess = this.zmapClinicCM1SyncMapper.deleteByHisPrimaryKey(hisPrimaryKey) > 0;//删除
				if(deleteSuccess){
					input.setClinicId(zmapSyncModel.getClinicId());//记录clinic_id
				}
			}
			//getZmapDeptCodeByDeptCodeAndHosCode
			Map<String,Object> deptMap =  new HashMap<String,Object>();
			deptMap.put("deptCode", input.getDeptCode());
			deptMap.put("hospitalCode", input.getHospitalCode());
			String zmapDeptCode = this.zmapRDeptMapper.getZmapDeptCodeByDeptCodeAndHosCode(deptMap);
			input.setZmapDeptCode(zmapDeptCode);
			
			System.out.println(" zmap_c_m1_clinics [reg_code:" + hisPrimaryKey + "]");
			insertResult = this.zmapClinicCM1SyncMapper.insert(input) > 0;
		}
		return insertResult;
	}
	/**
	 * 单条插入到zmap clinic charge
	 * @param input
	 * @return
	 */
	@RequestMapping("/insert2ZmapClinicCharge")
	boolean insert2ZmapClinicCharge(@RequestBody ZmapClinicCM2ChargeSyncModel input) {
		
		boolean insertResult = false;
		if (input == null){
			try {
				throw new Exception("api/clinic/insert2ZmapClinicCharge的输入参数model为空！");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			//*********以下代码段为存在的记录先删除后插入，记录clinic_id***********
			String hisPrimaryKey = "";
			ZmapClinicCM1SyncModel zmapClnSyncModel = new ZmapClinicCM1SyncModel();
			ZmapClinicCM2ChargeSyncModel  zmapSyncModel = new ZmapClinicCM2ChargeSyncModel();
			boolean deleteSuccess = false;
			//根据his主键先判断是否已存在，存在的先删除再插入or直接跳过？
			hisPrimaryKey = input.getChargeCode().toString();
			zmapSyncModel = this.zmapClinicCM2ChargeSyncMapper.selectByHisPrimaryKey(hisPrimaryKey);
			if (zmapSyncModel!=null){
				deleteSuccess = this.zmapClinicCM2ChargeSyncMapper.deleteByHisPrimaryKey(hisPrimaryKey) > 0;//删除
				if(deleteSuccess){
					input.setChargeId(zmapSyncModel.getChargeId());//记录zmap 主键id
				}
			}
			//getClinicByHisPrimaryKey
			String hisClinicCode = input.getRegCode();
			zmapClnSyncModel = this.zmapClinicCM1SyncMapper.selectByHisPrimaryKey(hisClinicCode);
			if (zmapClnSyncModel!=null) {
				input.setClinicCode(zmapClnSyncModel.getClinicId());
			}
			//getZmapDeptCodeByDeptCodeAndHosCode
			Map<String,Object> deptMap =  new HashMap<String,Object>();
			deptMap.put("deptCode", input.getDeptCode());
			deptMap.put("hospitalCode", input.getHospitalCode());
			String zmapDeptCode = this.zmapRDeptMapper.getZmapDeptCodeByDeptCodeAndHosCode(deptMap);
			input.setZmapDeptCode(zmapDeptCode);
			//get zmap_fee_item_code  selectByItemCodeAndHosCode
			ZmapLFeeItemModel zmapLFeeItemModel = new ZmapLFeeItemModel();
			zmapLFeeItemModel.setHospitalCode(input.getHospitalCode());
			zmapLFeeItemModel.setFeeItemCode(input.getFeeItemCode());
			zmapLFeeItemModel = this.zmapLFeeItemMapper.selectByItemCodeAndHosCode(zmapLFeeItemModel);
			if (zmapLFeeItemModel!=null) {
				input.setZmapFeeItemCode(zmapLFeeItemModel.getZmapFeeItemCode());
			}
			System.out.println("  zmap_c_m2_charge [reg_code:" + hisPrimaryKey + "]");
			insertResult = this.zmapClinicCM2ChargeSyncMapper.insert(input) > 0;
		}
		return insertResult;
	}
	
	/**
	 * 医嘱表 zmap_c_m2_morder
	 * @param input
	 * @return
	 */
	@RequestMapping("/insert2ZmapMOrder")
	boolean insert2ZmapMOrder(@RequestBody ZmapClinicCM2MOrderSyncModel input) {
		
		boolean insertResult = false;
		if (input == null){
			try {
				throw new Exception("api/clinic/insert2ZmapMOrder的输入参数model为空！");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			//*********以下代码段为存在的记录先删除后插入，记录clinic_id***********
			String hisPrimaryKey = "";
			ZmapClinicCM2MOrderSyncModel  zmapSyncModel = new ZmapClinicCM2MOrderSyncModel();
			boolean deleteSuccess = false;
			//根据his主键先判断是否已存在，存在的先删除再插入or直接跳过？
			hisPrimaryKey = input.getMorderCode().toString();
			zmapSyncModel = this.zmapClinicCM2MOrderSyncMapper.selectByHisPrimaryKey(hisPrimaryKey);
			if (zmapSyncModel!=null){
				deleteSuccess = this.zmapClinicCM2MOrderSyncMapper.deleteByHisPrimaryKey(hisPrimaryKey) > 0;//删除
				if(deleteSuccess){
					input.setMorderId(zmapSyncModel.getMorderId());//记录zmap 主键id
				}
			}

			System.out.println("zmap_c_m2_morder [morder_code:" + hisPrimaryKey + "]");
			insertResult = this.zmapClinicCM2MOrderSyncMapper.insert(input) > 0;
		}
		return insertResult;
	}
	
	/**
	 * 针对江北 转诊记录单条插入
	 * @param input
	 * @return
	 */
	@RequestMapping("/insert2JbTransClinic")
	boolean insert2JbTransClinic(@RequestBody JbTransClinicSyncModel input) {
		
		boolean insertResult = false;
		if (input == null){
			try {
				throw new Exception("api/clinic/insert2JbTransClinic的输入参数model为空！");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			//*********以下代码段为存在的记录先删除后插入，记录clinic_id***********
			JbTransClinicSyncModel  zmapSyncModel = new JbTransClinicSyncModel();
			//根据his主键先判断是否已存在，存在的先删除再插入or直接跳过？
			zmapSyncModel = this.jbTransClinicSyncMapper.selectByHisPrimaryKey(input);
			if (zmapSyncModel!=null){
				this.jbTransClinicSyncMapper.deleteByHisPrimaryKey(input);//删除
				System.out.println("  jb_trans_clinic [reg_code:" + zmapSyncModel.getRegCode() + "]");
			}
			insertResult = this.jbTransClinicSyncMapper.insert(input) > 0;
		}
		return insertResult;
	}
	/**
	 * 针对江北 病人预交记录表
	 * @param input
	 * @return
	 */
	@RequestMapping("/insert2JbPrepayCharge")
	boolean insert2JbPrepayCharge(@RequestBody JbPrepayChargeSyncModel input) {
		
		boolean insertResult = false;
		if (input == null){
			try {
				throw new Exception("api/clinic/JbPrepayChargeSyncModel的输入参数model为空！");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			//*********以下代码段为存在的记录先删除后插入，记录clinic_id***********
			JbPrepayChargeSyncModel  zmapSyncModel = new JbPrepayChargeSyncModel();
			zmapSyncModel = this.jbPrepayChargeSyncMapper.selectByHisPrimaryKey(input);
			if (zmapSyncModel!=null){
				this.jbPrepayChargeSyncMapper.deleteByHisPrimaryKey(input);//删除
				System.out.println("jb_prepay_charge [prepay_code:" + zmapSyncModel.getPrepayCode() + "]");
			}
			insertResult = this.jbPrepayChargeSyncMapper.insert(input) > 0;
		}
		return insertResult;
	}
}
