package com.zmap.zmap.framework.income.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zmap.zmap.framework.income.model.MedicalIncomeModel;



@Mapper
public interface MedicalIncomeMapper {

	
	public List<HashMap<String,String>> selectDayIncomeByMedical(HashMap params);
	public List<HashMap<String,String>> selectDaySumIncomeByMedical(HashMap params);
	public List<MedicalIncomeModel>  selectDayMedicalIncomeByDept(HashMap params);
	
	public List<HashMap<String, String>> selectDayMedicalIncomeByDoctor(HashMap params);
	
	public List<HashMap<String,String>>  selectClinicDayIncomeByMedical(HashMap params);
	public List<HashMap<String,String>>  selectHospitalDayIncomeByMedical(HashMap params);
	public List<HashMap<String,String>>  selectAcctItemCode(HashMap params);
	public List<HashMap<String,String>>  selectAcctItemName(HashMap params);
	
	
}
