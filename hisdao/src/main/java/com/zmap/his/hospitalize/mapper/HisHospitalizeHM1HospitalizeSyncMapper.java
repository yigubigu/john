package com.zmap.his.hospitalize.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.zmap.his.clinic.model.HisClinicCM1SyncModel;
import com.zmap.his.hospitalize.model.HisHospitalizeHM1HospitalizeSyncModel;

@Mapper
public interface HisHospitalizeHM1HospitalizeSyncMapper {
	int countNum();//统计总数
	int countNumWithDate(String curDate);
	List<HisHospitalizeHM1HospitalizeSyncModel> selectTop50();
	List<HisHospitalizeHM1HospitalizeSyncModel> selectByPagination(Map<String,Object> map);//分页
	List<HisHospitalizeHM1HospitalizeSyncModel> selectByPaginationWithDateTime(Map<String,Object> map);//超过50条后使用分页
}
