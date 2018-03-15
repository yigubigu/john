package com.zmap.zmap.framework.income.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface InsuranceMapper {
	
	
	List<HashMap<String,String>> selectDayInsuranceIncomeByDoctor(HashMap<String,String> params);
	List<HashMap<String,String>> selectDayInsuranceIncomeByDept(HashMap<String,String> params);
	List<HashMap<String,String>> selectDaySumIncomeByInsurance(HashMap<String,String> params);

}
