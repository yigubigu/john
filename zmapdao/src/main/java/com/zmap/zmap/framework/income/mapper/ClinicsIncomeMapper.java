package com.zmap.zmap.framework.income.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zmap.zmap.framework.income.model.ClinicsIncomeModel;

@Mapper
public interface ClinicsIncomeMapper {
	List<ClinicsIncomeModel> selectDayClinicsIncomeByHour(HashMap<String, String> params);
	List<ClinicsIncomeModel> selectDayClinicsIncomeByDept(HashMap<String, String> params);
	List<ClinicsIncomeModel> selectDayClinicsIncomeByDeptz(HashMap<String, String> params);
	List<ClinicsIncomeModel> selectDayClinicsIncomeByDoctor(HashMap<String, String> params);
	List<ClinicsIncomeModel> selectDayClinicsIncomeSum(HashMap<String, String> params);

}
