package com.zmap.zmap.hospitalize.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zmap.zmap.hospitalize.model.InHospitalPersonModel;



@Mapper
public interface InHospitalPersonMapper {
	
	List<InHospitalPersonModel> selectInHospitalPersonByDateSum(HashMap<String, String> params);
	List<InHospitalPersonModel> selectInHospitalPersonByDept(HashMap<String, String> params);
	List<InHospitalPersonModel> selectInHospitalPersonByDoctor(HashMap<String, String> params);
	
}
