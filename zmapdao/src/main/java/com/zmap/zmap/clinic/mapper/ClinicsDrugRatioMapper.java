package com.zmap.zmap.clinic.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zmap.zmap.clinic.model.ClinicsDrugRatioModel;

@Mapper
public interface ClinicsDrugRatioMapper {
	
	List<ClinicsDrugRatioModel> selectClinicsDrugIncomeByDate(HashMap<String, String> params);
	List<ClinicsDrugRatioModel> selectClinicsDrugIncomeByDept(HashMap<String, String>parmas);
	List<ClinicsDrugRatioModel> selectClinicsDrugIncomeByDoctor(HashMap<String, String>parmas);

}
