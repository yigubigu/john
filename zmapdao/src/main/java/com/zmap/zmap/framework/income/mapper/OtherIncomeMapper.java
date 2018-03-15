package com.zmap.zmap.framework.income.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zmap.zmap.framework.income.model.OtherIncomeModel;

@Mapper
public interface OtherIncomeMapper {

	List<OtherIncomeModel> selectDeptcode(HashMap<String, String> param);
	List<OtherIncomeModel> selectClinicOtherIncome(HashMap<String, String> param);
	List<OtherIncomeModel> selectHospitalIncome(HashMap<String, String> param);
	List<OtherIncomeModel> selectDeptName(HashMap<String, String> param);
	List<OtherIncomeModel> selectDoctorcode(HashMap<String, String> param);
	List<OtherIncomeModel> selectClinicOtherIncomeByDoctor(HashMap<String, String> param);
	List<OtherIncomeModel> selectHospitalIncomeByDoctor(HashMap<String, String> param);
	List<OtherIncomeModel> selectDoctorName(HashMap<String, String> param);
	
	List<OtherIncomeModel> selectSum(HashMap<String, String> param);
}
