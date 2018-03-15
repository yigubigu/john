package com.zmap.zmap.hospitalize.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zmap.zmap.hospitalize.model.HospitalizeNumberModel;

@Mapper
public interface HospitalizeNumberMapper {
	
	List<HospitalizeNumberModel> selectHospitalizeNumByDept(HashMap<String,String> param);
	List<HospitalizeNumberModel> selectHospitalizeNumByDoctor(HashMap<String,String> param);
	List<HospitalizeNumberModel> selectDayHospitalizeNumberSum(HashMap<String,String> param);

}
