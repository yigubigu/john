package com.zmap.zmap.framework.income.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zmap.zmap.framework.income.model.CostPrivateModel;


@Mapper
public interface CostPrivateMapper {
	
	
	List<CostPrivateModel> selectDayPrivateIncomeByDoctor(HashMap<String,String> params);
	List<CostPrivateModel> selectDayPrivateIncomeByDept(HashMap<String,String> params);
	List<HashMap<String,String>> selectDaySumIncomeByPrivate(HashMap<String,String> params);

}
