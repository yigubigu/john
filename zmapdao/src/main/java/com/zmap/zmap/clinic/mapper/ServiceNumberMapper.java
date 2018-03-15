package com.zmap.zmap.clinic.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zmap.zmap.clinic.model.ServiceNumberModel;


@Mapper
public interface ServiceNumberMapper {

	List<ServiceNumberModel> selectMonthServiceNumberByDoctor(HashMap<String,String> params);
	List<HashMap<String,String>> selectMonthServiceNumberByDept(HashMap<String,String> params);
	List<HashMap<String,String>> selectMonthServiceNumberSum(HashMap<String,String> params);
	List<HashMap<String,String>> selectMonthServiceNumberSumByCategory(HashMap<String,String> params);
}
