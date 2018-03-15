package com.zmap.zmap.clinic.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zmap.zmap.clinic.model.ClinicsInfusionPersonModel;

@Mapper
public interface ClinicsInfusionPersonMapper {
	List<ClinicsInfusionPersonModel> selectPersonTimeByDateSum (HashMap<String, String> params);
	List<ClinicsInfusionPersonModel> selectPersonTimeByDatefromClinics (HashMap<String, String> paramc);
	List<ClinicsInfusionPersonModel> selectPersonTimeByDateFromEmergency (HashMap<String, String> parame);
	List<ClinicsInfusionPersonModel> selectPersonTimeByDateFromDept (HashMap<String, String> paramd);
	List<ClinicsInfusionPersonModel> selectPersonTimeByDateFromDoctor (HashMap<String, String> paramdd);

}
