package com.zmap.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zmap.zmap.framework.base.mapper.ZmapRDeptMapper;
import com.zmap.zmap.hospitalize.mapper.ZmapHospitalizeHM1HospitalizeSyncMapper;
import com.zmap.zmap.hospitalize.mapper.ZmapHospitalizeHM2ChargeSyncMapper;
import com.zmap.zmap.hospitalize.mapper.ZmapHospitalizeHM2DrugUseSyncMapper;
import com.zmap.zmap.hospitalize.mapper.ZmapHospitalizeHM2ExamineSyncMapper;
import com.zmap.zmap.hospitalize.mapper.ZmapHospitalizeHM2OperationSyncMapper;
import com.zmap.zmap.hospitalize.model.ZmapHospitalizeHM1HospitalizeSyncModel;
import com.zmap.zmap.hospitalize.model.ZmapHospitalizeHM2ChargeSyncModel;
import com.zmap.zmap.hospitalize.model.ZmapHospitalizeHM2DrugUseSyncModel;
import com.zmap.zmap.hospitalize.model.ZmapHospitalizeHM2ExamineSyncModel;
import com.zmap.zmap.hospitalize.model.ZmapHospitalizeHM2OperationSyncModel;

@RestController
@RequestMapping("/api/hospitalize")
public class ZmapHospitalizeSyncController {

	@Autowired
	private ZmapHospitalizeHM1HospitalizeSyncMapper zmapHospitalizeHM1HospitalizeSyncMapper;
	@Autowired
	private ZmapHospitalizeHM2ChargeSyncMapper zmapHospitalizeHM2ChargeSyncMapper;
	
	@Autowired
	private ZmapHospitalizeHM2DrugUseSyncMapper zmapHospitalizeHM2DrugUseSyncMapper;
	
	@Autowired
	private ZmapHospitalizeHM2ExamineSyncMapper zmapHospitalizeHM2ExamineSyncMapper;
	
	@Autowired
	private ZmapHospitalizeHM2OperationSyncMapper zmapHospitalizeHM2OperationSyncMapper;
	
	@Autowired
	private ZmapRDeptMapper zmapRDeptMapper;
	
	private int countHospitalizeHM1Hospitalize = 0;
	private int countHospitalizeHM2Charge = 0;
	private int countHospitalizeHM2DrugUse = 0;
	private int countHospitalizeHM2Examine = 0;
	private int countHospitalizeHM2Operation = 0;
	/**
	 * 更新zmap_h_m1_hospitalize表
	 * @param input
	 * @return
	 */
	@RequestMapping("/hospitalizeHM1HospitalizeSync")
	int hospitalizeHM1HospitalizeSync(@RequestBody List<ZmapHospitalizeHM1HospitalizeSyncModel> input) {
		
		int inputsize = input.size();
		int num = 0;
		if (inputsize==0){
			try {
				throw new Exception("api/hospitalize/hospitalizeHM1HospitalizeSync的输入参数size为空！");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			//*********以下代码段为存在的记录先删除后插入，记录clinic_id***********
			String hisPrimaryKey = "";
			ZmapHospitalizeHM1HospitalizeSyncModel  zmapSyncModel = new ZmapHospitalizeHM1HospitalizeSyncModel();
			boolean deleteSuccess = false;
			//根据his主键先判断是否已存在，存在的先删除再插入or直接跳过？
			for (int i =0; i<inputsize; i++){
				hisPrimaryKey = input.get(i).getSickmanCode().toString();
				zmapSyncModel = this.zmapHospitalizeHM1HospitalizeSyncMapper.selectByHisPrimaryKey(hisPrimaryKey);
				if (zmapSyncModel!=null){
					deleteSuccess = this.zmapHospitalizeHM1HospitalizeSyncMapper.deleteByHisPrimaryKey(hisPrimaryKey) > 0;//删除
					if(deleteSuccess){
						input.get(i).setInHosId(zmapSyncModel.getInHosId());//set zmap primary key
					}
				}
				System.out.println(++countHospitalizeHM1Hospitalize +" zmap_h_m1_hospitalize[sickman_code:" + hisPrimaryKey + "]");
			}
			num = this.zmapHospitalizeHM1HospitalizeSyncMapper.batchInsert(input);
		}
		return num;
	}
	
	/**
	 * 更新zmap_h_m2_charge表
	 * @param input
	 * @return
	 */
	@RequestMapping("/hospitalizeHM2ChargeSync")
	int hospitalizeHM2ChargeSync(@RequestBody List<ZmapHospitalizeHM2ChargeSyncModel> input) {
		
		int inputsize = input.size();
		int num = 0;
		if (inputsize==0){
			try {
				throw new Exception("api/hospitalize/hospitalizeHM2ChargeSync的输入参数size为空！");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			//*********以下代码段为存在的记录先删除后插入，记录clinic_id***********
			String hisPrimaryKey = "";
			ZmapHospitalizeHM2ChargeSyncModel  zmapSyncModel = new ZmapHospitalizeHM2ChargeSyncModel();
			boolean deleteSuccess = false;
			//根据his主键先判断是否已存在，存在的先删除再插入or直接跳过？
			for (int i =0; i<inputsize; i++){
				hisPrimaryKey = input.get(i).getSickFeeFlowCode().toString();
				zmapSyncModel = this.zmapHospitalizeHM2ChargeSyncMapper.selectByHisPrimaryKey(hisPrimaryKey);
				if (zmapSyncModel!=null){
					deleteSuccess = this.zmapHospitalizeHM2ChargeSyncMapper.deleteByHisPrimaryKey(hisPrimaryKey) > 0;//删除
					if(deleteSuccess){
						input.get(i).setSickFeeFlowId(zmapSyncModel.getSickFeeFlowId());//set zmap primary key
					}
				}
				System.out.println(++countHospitalizeHM2Charge +" zmap_h_m2_charge[sick_fee_flow_code:" + hisPrimaryKey + "]");
			}
			num = this.zmapHospitalizeHM2ChargeSyncMapper.batchInsert(input);
		}
		return num;
	}
	
	
	@RequestMapping("/hospitalizeHM2DrugUseSync")
	int hospitalizeHM2DrugUseSync(@RequestBody List<ZmapHospitalizeHM2DrugUseSyncModel> input) {
		
		int inputsize = input.size();
		int num = 0;
		if (inputsize==0){
			try {
				throw new Exception("api/hospitalize/hospitalizeHM2DrugUseSync的输入参数size为空！");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			//*********以下代码段为存在的记录先删除后插入，记录clinic_id***********
			String hisPrimaryKey = "";
			ZmapHospitalizeHM2DrugUseSyncModel  zmapSyncModel = new ZmapHospitalizeHM2DrugUseSyncModel();
			boolean deleteSuccess = false;
			//根据his主键先判断是否已存在，存在的先删除再插入or直接跳过？
			for (int i =0; i<inputsize; i++){
				hisPrimaryKey = input.get(i).getDrugUseCode().toString();
				zmapSyncModel = this.zmapHospitalizeHM2DrugUseSyncMapper.selectByHisPrimaryKey(hisPrimaryKey);
				if (zmapSyncModel!=null){
					deleteSuccess = this.zmapHospitalizeHM2DrugUseSyncMapper.deleteByHisPrimaryKey(hisPrimaryKey) > 0;//删除
					if(deleteSuccess){
						input.get(i).setDrugUseId(zmapSyncModel.getDrugUseId());//set zmap primary key
						System.out.println(zmapSyncModel.getDrugUseCode());
					}
				}
				System.out.println(++countHospitalizeHM2DrugUse +" zmap_h_m2_drug_use[drug_use_code:" + hisPrimaryKey + "]");
			}
			num = this.zmapHospitalizeHM2DrugUseSyncMapper.batchInsert(input);
		}
		return num;
	}
	
	
	@RequestMapping("/hospitalizeHM2ExamineSync")
	int hospitalizeHM2ExamineSync(@RequestBody List<ZmapHospitalizeHM2ExamineSyncModel> input) {
		
		int inputsize = input.size();
		int num = 0;
		if (inputsize==0){
			try {
				throw new Exception("api/hospitalize/hospitalizeHM2ExamineSync的输入参数size为空！");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			//*********以下代码段为存在的记录先删除后插入，记录clinic_id***********
			String hisPrimaryKey = "";
			ZmapHospitalizeHM2ExamineSyncModel  zmapSyncModel = new ZmapHospitalizeHM2ExamineSyncModel();
			boolean deleteSuccess = false;
			//根据his主键先判断是否已存在，存在的先删除再插入or直接跳过？
			for (int i =0; i<inputsize; i++){
				hisPrimaryKey = input.get(i).getExamFlowCode().toString();
				zmapSyncModel = this.zmapHospitalizeHM2ExamineSyncMapper.selectByHisPrimaryKey(hisPrimaryKey);
				if (zmapSyncModel!=null){
					deleteSuccess = this.zmapHospitalizeHM2ExamineSyncMapper.deleteByHisPrimaryKey(hisPrimaryKey) > 0;//删除
					if(deleteSuccess){
						input.get(i).setExamId(zmapSyncModel.getExamId());//set zmap primary key
					}
				}
				System.out.println(++countHospitalizeHM2Examine +" zmap_h_m2_examine[exam_flow_code:" + hisPrimaryKey + "]");
			}
			num = this.zmapHospitalizeHM2ExamineSyncMapper.batchInsert(input);
		}
		return num;
	}
	
	/**
	 * 更新zmap_h_m2_operation表
	 * @param input
	 * @return
	 */
	@RequestMapping("/hospitalizeHM2OperationSync")
	int hospitalizeHM2OperationSync(@RequestBody List<ZmapHospitalizeHM2OperationSyncModel> input) {
		
		int inputsize = input.size();
		int num = 0;
		if (inputsize==0){
			try {
				throw new Exception("api/hospitalize/hospitalizeHM2OperationSync的输入参数size为空！");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			//*********以下代码段为存在的记录先删除后插入，记录clinic_id***********
			String hisPrimaryKey = "";
			ZmapHospitalizeHM2OperationSyncModel  zmapSyncModel = new ZmapHospitalizeHM2OperationSyncModel();
			boolean deleteSuccess = false;
			//根据his主键先判断是否已存在，存在的先删除再插入or直接跳过？
			for (int i =0; i<inputsize; i++){
				hisPrimaryKey = input.get(i).getOperFlowCode().toString();
				zmapSyncModel = this.zmapHospitalizeHM2OperationSyncMapper.selectByHisPrimaryKey(hisPrimaryKey);
				if (zmapSyncModel!=null){
					deleteSuccess = this.zmapHospitalizeHM2OperationSyncMapper.deleteByHisPrimaryKey(hisPrimaryKey) > 0;//删除
					if(deleteSuccess){
						input.get(i).setOperFlowId(zmapSyncModel.getOperFlowId());//set zmap primary key
					}
				}
				System.out.println(++countHospitalizeHM2Operation +" zmap_h_m2_operation[oper_flow_code:" + hisPrimaryKey + "]");
			}
			num = this.zmapHospitalizeHM2OperationSyncMapper.batchInsert(input);
		}
		return num;
	}
	
	/**
	 * updateHospitalizeID
	 * 各表可统一update
	 * @return
	 */
	@RequestMapping("/updateHospitalizeID")
	boolean updateHospitalizeID() {
		boolean isSuccess = this.zmapHospitalizeHM2ChargeSyncMapper.updateHospitalizeID() > 0;
		isSuccess = isSuccess & this.zmapHospitalizeHM2DrugUseSyncMapper.updateHospitalizeID() > 0;
		isSuccess = isSuccess & this.zmapHospitalizeHM2ExamineSyncMapper.updateHospitalizeID() > 0;
		isSuccess = isSuccess & this.zmapHospitalizeHM2OperationSyncMapper.updateHospitalizeID() > 0;
		return isSuccess;
	}
	
	/**
	 * update ChargeHospitalizeIDWithDate
	 * @param curDate
	 * @return
	 */
	@RequestMapping("/updateChargeHospitalizeIDWithDate")
	boolean updateChargeHospitalizeIDWithDate(String curDate) {
		boolean isSuccess = this.zmapHospitalizeHM2ChargeSyncMapper.updateHospitalizeIDWithDate(curDate) > 0;
		return isSuccess;
	}
	/**
	 * update ChargeHospitalizeID
	 * @param curDate
	 * @return
	 */
	@RequestMapping("/updateChargeHospitalizeID")
	boolean updateChargeHospitalizeID() {
		boolean isSuccess = this.zmapHospitalizeHM2ChargeSyncMapper.updateHospitalizeID() > 0;
		return isSuccess;
	}
	
	/********************************* 以下为单条插入 *************************************/
	/**
	 * 单条插入到zmap hos 一级hos表
	 * @param input
	 * @return
	 */
	@RequestMapping("/insert2ZmapHos")
	boolean insert2ZmapHos(@RequestBody ZmapHospitalizeHM1HospitalizeSyncModel input) {
		
		boolean insertResult = false;
		int num = 0;
		if (input == null){
			try {
				throw new Exception("api/hospitalize/insert2ZmapHos的输入参数size为空！");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			//*********以下代码段为存在的记录先删除后插入，记录clinic_id***********
			String hisPrimaryKey = "";
			ZmapHospitalizeHM1HospitalizeSyncModel  zmapSyncModel = new ZmapHospitalizeHM1HospitalizeSyncModel();
			boolean deleteSuccess = false;
			//根据his主键先判断是否已存在，存在的先删除再插入or直接跳过？
			hisPrimaryKey = input.getInHosFlowCode().toString();
			zmapSyncModel = this.zmapHospitalizeHM1HospitalizeSyncMapper.selectByHisPrimaryKey(hisPrimaryKey);
			if (zmapSyncModel!=null){
				deleteSuccess = this.zmapHospitalizeHM1HospitalizeSyncMapper.deleteByHisPrimaryKey(hisPrimaryKey) > 0;//删除
				if(deleteSuccess){
					input.setInHosId(zmapSyncModel.getInHosId());//set zmap primary key
				}
			}
			System.out.println(" zmap_h_m1_hospitalize[sickman_code:" + hisPrimaryKey + "]");
			insertResult = this.zmapHospitalizeHM1HospitalizeSyncMapper.insert(input) > 0;
		}
		return insertResult;
	}
	/**
	 * 单条插入到zmap hos charge
	 * @param input
	 * @return
	 */
	@RequestMapping("/insert2ZmapHosCharge")
	boolean insert2ZmapHosCharge(@RequestBody ZmapHospitalizeHM2ChargeSyncModel input) {
		
		boolean insertResult = false;
		if (input == null){
			try {
				throw new Exception("api/hospitalize/insert2ZmapHosCharge的输入参数model为空！");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			//*********以下代码段为存在的记录先删除后插入，记录clinic_id***********
			String hisPrimaryKey = "";
			ZmapHospitalizeHM1HospitalizeSyncModel zmapHosSyncModel = new ZmapHospitalizeHM1HospitalizeSyncModel();
			ZmapHospitalizeHM2ChargeSyncModel  zmapSyncModel = new ZmapHospitalizeHM2ChargeSyncModel();
			boolean deleteSuccess = false;
			//根据his主键先判断是否已存在，存在的先删除再插入or直接跳过？
			hisPrimaryKey = input.getSickFeeFlowCode().toString();
			zmapSyncModel = this.zmapHospitalizeHM2ChargeSyncMapper.selectByHisPrimaryKey(hisPrimaryKey);
			if (zmapSyncModel!=null){
				deleteSuccess = this.zmapHospitalizeHM2ChargeSyncMapper.deleteByHisPrimaryKey(hisPrimaryKey) > 0;//删除
				if(deleteSuccess){
					input.setSickFeeFlowId(zmapSyncModel.getSickFeeFlowId());//set zmap primary key
				}
			}
			//getHosByHisPrimaryKey
			String hisHosCode = input.getInHosFlowCode();
			zmapHosSyncModel = this.zmapHospitalizeHM1HospitalizeSyncMapper.selectByHisPrimaryKey(hisHosCode);//通过in_hos_flow_code
			if (zmapHosSyncModel!=null) {
				input.setInHosCode(zmapHosSyncModel.getInHosId());
			}
			//getZmapDeptCodeByDeptCodeAndHosCode
			Map<String,Object> deptMap =  new HashMap<String,Object>();
			deptMap.put("deptCode", input.getDeptCode());
			deptMap.put("hospitalCode", input.getHospitalCode());
			String zmapDeptCode = this.zmapRDeptMapper.getZmapDeptCodeByDeptCodeAndHosCode(deptMap);
			input.setZmapDeptCode(zmapDeptCode);
			
			System.out.println(" zmap_h_m2_charge[sick_fee_flow_code:" + hisPrimaryKey + "]");
			insertResult = this.zmapHospitalizeHM2ChargeSyncMapper.insert(input) > 0;
		}
		return insertResult;
	}
}
