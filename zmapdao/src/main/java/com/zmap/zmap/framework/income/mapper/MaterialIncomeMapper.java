package com.zmap.zmap.framework.income.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zmap.zmap.framework.income.model.MaterialIncomeModel;

@Mapper
public interface MaterialIncomeMapper {

	List<MaterialIncomeModel> selectMaterialIncomeByDeptCode(HashMap<String, String> param);
	List<MaterialIncomeModel> selectMaterialIncomeByDoctorCode (HashMap<String, String> param);
	List<MaterialIncomeModel> selectClinicMaterialIncomeByDept(HashMap<String, String> param);
	List<MaterialIncomeModel> selectClinicMaterialIncomeByDoctor(HashMap<String, String> param);
	List<MaterialIncomeModel> selectHospitalMaterialIncomeByDoctor(HashMap<String, String> param);
	List<MaterialIncomeModel> selectHospitalizeMaterialIncomeByDept(HashMap<String, String> param);
	List<MaterialIncomeModel> selectSumMaterialIncomeByDept(HashMap<String, String> param);
	
}
