package com.zmap.his.hospitalize.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.zmap.his.hospitalize.model.HisHospitalizeHM1HospitalizeSyncModel;
import com.zmap.his.hospitalize.model.HisHospitalizeHM2ChargeSyncModel;

@Mapper
public interface HisHospitalizeHM2ChargeSyncMapper {
	int countNum();//统计总数
	int countNumWithDate(String curDate);
	HisHospitalizeHM2ChargeSyncModel selectTop1();
	List<HisHospitalizeHM2ChargeSyncModel> selectTop50();
	List<HisHospitalizeHM2ChargeSyncModel> selectByPagination(Map<String,Object> map);//分页
	List<HisHospitalizeHM2ChargeSyncModel> selectByPaginationWithDateTime(Map<String,Object> map);//超过50条后使用分页

}
