package com.zmap.zmap.clinic.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zmap.zmap.clinic.model.EmergencyVisitsModel;

@Mapper
public interface EmergencyVisitsMapper {

	List<EmergencyVisitsModel> selectDayEmergencyVisitsByHour(HashMap<String, String> params);
	List<EmergencyVisitsModel> selectDayEmergencyVisitsSum(HashMap<String, String> params);
}
