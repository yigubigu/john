package com.zmap.zmap.framework.hospitalize.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zmap.zmap.framework.hospitalize.model.AdmittedRateModel;

@Mapper
public interface AdmittedRateMapper {
	List<AdmittedRateModel> selectDayAdmittedRate(HashMap<String, String> params);
	List<AdmittedRateModel> selectAdmittedRateByDept(HashMap<String, String> params);
	List<AdmittedRateModel> selectAdmittedRateByDoctor(HashMap<String, String> params);
}
