package com.zmap.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zmap.zmap.framework.base.mapper.ZmapLAcctItemMapper;
import com.zmap.zmap.framework.base.mapper.ZmapLExamineMapper;
import com.zmap.zmap.framework.base.mapper.ZmapLFeeItemMapper;
import com.zmap.zmap.framework.base.mapper.ZmapLOperationMapper;
import com.zmap.zmap.framework.base.mapper.ZmapRDoctorMapper;
import com.zmap.zmap.framework.base.mapper.ZmapRPatientMapper;
import com.zmap.zmap.framework.base.model.ZmapLAcctItemModel;
import com.zmap.zmap.framework.base.model.ZmapLExamineModel;
import com.zmap.zmap.framework.base.model.ZmapLFeeItemModel;
import com.zmap.zmap.framework.base.model.ZmapLOperationModel;
import com.zmap.zmap.framework.base.model.ZmapRDoctorModel;
import com.zmap.zmap.framework.base.model.ZmapRPatientModel;

@RestController
@RequestMapping("/api/basedata")
public class ZmapBaseDataSyncController {

	@Autowired
	private ZmapRDoctorMapper zmapRDoctorMapper;
	
	@Autowired
	private ZmapRPatientMapper zmapRPatientMapper;
	
	@Autowired
	private ZmapLAcctItemMapper zmapLAcctItemMapper;
	
	@Autowired
	private ZmapLExamineMapper zmapLExamineMapper;
	
	@Autowired
	private ZmapLFeeItemMapper zmapLFeeItemMapper;
	
	@Autowired
	private ZmapLOperationMapper zmapLOperationMapper;
	/**
	 * 基础数据 zmap_r_doctor
	 * @param input
	 * @return
	 */
	@RequestMapping("/RDoctorSync")
	boolean syncRDoctor(@RequestBody ZmapRDoctorModel input) {
		
		boolean insertResult = false;
		if (input == null){
			try {
				throw new Exception("api/basedata/RDoctorSync的输入参数model为空！");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			//*********以下代码段为存在的记录先删除后插入***********
			ZmapRDoctorModel  zmapSyncModel = new ZmapRDoctorModel();
			zmapSyncModel = this.zmapRDoctorMapper.selectByHisPrimaryKey(input);
			if (zmapSyncModel!=null){
				this.zmapRDoctorMapper.deleteByHisPrimaryKey(input);//删除
				input.setDoctorId(zmapSyncModel.getDoctorId());
				input.setZmapDeptCode(zmapSyncModel.getZmapDeptCode());
				input.setZmapDoctorCode(zmapSyncModel.getZmapDoctorCode());
				input.setZmapDoctorName(zmapSyncModel.getZmapDoctorName());
				System.out.println("zmap_r_doctor [doctor_code:" + zmapSyncModel.getDoctorCode() + "]");
			}
			insertResult = this.zmapRDoctorMapper.insert(input) > 0;
		}
		return insertResult;
	}
	
	/**
	 * 基础数据 zmap_r_patient
	 * @param input
	 * @return
	 */
	@RequestMapping("/RPatientSync")
	boolean syncRPatient(@RequestBody ZmapRPatientModel input) {
		
		boolean insertResult = false;
		if (input == null){
			try {
				throw new Exception("api/basedata/RPatientSync的输入参数model为空！");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			//*********以下代码段为存在的记录先删除后插入***********
			ZmapRPatientModel  zmapSyncModel = new ZmapRPatientModel();
			zmapSyncModel = this.zmapRPatientMapper.selectByHisPrimaryKey(input);
			if (zmapSyncModel!=null){
				this.zmapRPatientMapper.deleteByHisPrimaryKey(input);//删除
				input.setPatientId(zmapSyncModel.getPatientId());
				System.out.println("zmap_r_patient [patient_code:" + zmapSyncModel.getPatientCode() + "]");
			}
			insertResult = this.zmapRPatientMapper.insert(input) > 0;
		}
		return insertResult;
	}
	
	/**
	 * 基础数据 zmap_l_acct_item
	 * @param input
	 * @return
	 */
	@RequestMapping("/LAcctItemSync")
	boolean syncLAcctItem(@RequestBody ZmapLAcctItemModel input) {
		
		boolean insertResult = false;
		if (input == null){
			try {
				throw new Exception("api/basedata/LAcctItemSync的输入参数model为空！");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			//*********以下代码段为存在的记录先删除后插入***********
			ZmapLAcctItemModel  zmapSyncModel = new ZmapLAcctItemModel();
			zmapSyncModel = this.zmapLAcctItemMapper.selectByHisPrimaryKey(input);
			if (zmapSyncModel!=null){
				this.zmapLAcctItemMapper.deleteByHisPrimaryKey(input);//删除
				input.setAcctItemId(zmapSyncModel.getAcctItemId());
				System.out.println("zmap_l_acct_item [acct_item_code:" + zmapSyncModel.getAcctItemCode() + "]");
			}
			insertResult = this.zmapLAcctItemMapper.insert(input) > 0;
		}
		return insertResult;
	}
	
	/**
	 * 基础数据 zmap_l_examine
	 * @param input
	 * @return
	 */
	@RequestMapping("/LExamineSync")
	boolean syncLExamine(@RequestBody ZmapLExamineModel input) {
		
		boolean insertResult = false;
		if (input == null){
			try {
				throw new Exception("api/basedata/LExamineSync的输入参数model为空！");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			//*********以下代码段为存在的记录先删除后插入***********
			ZmapLExamineModel  zmapSyncModel = new ZmapLExamineModel();
			zmapSyncModel = this.zmapLExamineMapper.selectByHisPrimaryKey(input);
			if (zmapSyncModel!=null){
				this.zmapLExamineMapper.deleteByHisPrimaryKey(input);//删除
				input.setExamId(zmapSyncModel.getExamId());
				input.setZmapExamCode(zmapSyncModel.getZmapExamCode());
				input.setZmapExamName(zmapSyncModel.getZmapExamName());
				System.out.println("zmap_l_examine [exam_code:" + zmapSyncModel.getExamCode() + "]");
			}
			insertResult = this.zmapLExamineMapper.insert(input) > 0;
		}
		return insertResult;
	}
	
	/**
	 * 基础数据 zmap_l_fee_item
	 * @param input
	 * @return
	 */
	@RequestMapping("/LFeeItemSync")
	boolean syncLFeeItem(@RequestBody ZmapLFeeItemModel input) {
		
		boolean insertResult = false;
		if (input == null){
			try {
				throw new Exception("api/basedata/LFeeItemSync的输入参数model为空！");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			//*********以下代码段为存在的记录先删除后插入***********
			ZmapLFeeItemModel  zmapSyncModel = new ZmapLFeeItemModel();
			zmapSyncModel = this.zmapLFeeItemMapper.selectByHisPrimaryKey(input);
			if (zmapSyncModel!=null){
				this.zmapLFeeItemMapper.deleteByHisPrimaryKey(input);//删除
				input.setFeeItemId(zmapSyncModel.getFeeItemId());
				input.setZmapFeeItemCode(zmapSyncModel.getZmapFeeItemCode());
				input.setZmapFeeItemName(zmapSyncModel.getZmapFeeItemName());
				System.out.println("zmap_l_fee_item [fee_item_code:" + zmapSyncModel.getFeeItemCode() + "]");
			}
			insertResult = this.zmapLFeeItemMapper.insert(input) > 0;
		}
		return insertResult;
	}
	
	/**
	 * 基础数据 zmap_l_operation
	 * @param input
	 * @return
	 */
	@RequestMapping("/LOperationSync")
	boolean syncLOperation(@RequestBody ZmapLOperationModel input) {
		
		boolean insertResult = false;
		if (input == null){
			try {
				throw new Exception("api/basedata/LOperationSync的输入参数model为空！");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			//*********以下代码段为存在的记录先删除后插入***********
			ZmapLOperationModel  zmapSyncModel = new ZmapLOperationModel();
			zmapSyncModel = this.zmapLOperationMapper.selectByHisPrimaryKey(input);
			if (zmapSyncModel!=null){
				this.zmapLOperationMapper.deleteByHisPrimaryKey(input);//删除
				input.setOperId(zmapSyncModel.getOperId());
				input.setZmapOperCode(zmapSyncModel.getZmapOperCode());
				input.setZmapOperName(zmapSyncModel.getZmapOperName());
				System.out.println("zmap_l_operation [oper_code:" + zmapSyncModel.getOperCode() + "]");
			}
			insertResult = this.zmapLOperationMapper.insert(input) > 0;
		}
		return insertResult;
	}
}
