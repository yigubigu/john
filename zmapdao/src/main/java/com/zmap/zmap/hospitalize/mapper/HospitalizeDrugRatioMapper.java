package com.zmap.zmap.hospitalize.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zmap.zmap.hospitalize.model.HospitalizeDrugRatioModel;

@Mapper
public interface HospitalizeDrugRatioMapper {
	
	
	List<HospitalizeDrugRatioModel> selectHospitalizeDrugIncomeByDate(HashMap<String, String>params);
	List<HospitalizeDrugRatioModel> selectHospitalizeDrugIncomeByDept(HashMap<String, String>parmas);
	List<HospitalizeDrugRatioModel> selectHospitalizeDrugIncomeByDoctor(HashMap<String, String>parmas);
}
