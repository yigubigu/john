package com.zmap.zmap.framework.income.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface DrugIncomeMapper {
	
	public List<HashMap<String,String>> selectDayClinicIncomeByDrug(HashMap params);
	public List<HashMap<String,String>> selectDayHospitalIncomeByDrug(HashMap params);
	public List<HashMap<String,String>>  selectDayDrugIncomeByDept(HashMap params);
	
	public List<HashMap<String, String>> selectDayDrugIncomeByDoctor(HashMap params);
	
	public List<HashMap<String,String>>  selectDaySumIncomeByDrug(HashMap params);
	
	public List<HashMap<String,String>> selectFeeCode(HashMap<String, String> params);
	public List<HashMap<String,String>> selectFeeName(HashMap<String, String> params);
  
}
