package com.zmap.zmap.hospitalize.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zmap.zmap.hospitalize.model.HospitalizeHighOwePersonModel;


@Mapper
public interface HospitalizeHighOwePersonMapper {
	
	List<HospitalizeHighOwePersonModel> selectSickmanOweFeeCount(HashMap<String, String> paramo);
	List<HospitalizeHighOwePersonModel> selectSickmanPrepayFeeCount(HashMap<String, String> paramp);
	List<HospitalizeHighOwePersonModel> selectSickmanOweFeeByDept(HashMap<String, String> paramd);
	List<HospitalizeHighOwePersonModel> selectSickmanTimesByDept (HashMap<String, String> paramp);
	
}
