package com.zmap.zmap.hospitalize.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zmap.zmap.hospitalize.model.OutHospitalPersonModel;
@Mapper
public interface OutHospitalPersonMapper {

	List<OutHospitalPersonModel> selectOutHospitalPersonByDate(HashMap<String, String> params);
	List<OutHospitalPersonModel> selectOutHospitalPersonByDept(HashMap<String, String> params);
	List<OutHospitalPersonModel> selectOutHospitalPersonByDateSum(HashMap<String, String> params);
}
