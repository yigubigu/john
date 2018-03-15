package com.zmap.zmap.framework.income.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;


import com.zmap.zmap.framework.income.model.HospitalizeIncomeModel;

@Mapper
public interface HospitalizeIncomeMapper {
	 List<HospitalizeIncomeModel> selectDayHospitalIncomeByHour(HashMap params);
	 List<HospitalizeIncomeModel> selectHospitalSumByDept(HashMap params);
	 List<HospitalizeIncomeModel> selectDayHospitalIncomeByDept(HashMap params);
	 List<HospitalizeIncomeModel> selectDayHospitalIncomeByDoctor(HashMap params);
	 List<HospitalizeIncomeModel> selectDayHospitalIncomeSum(HashMap params);
}
