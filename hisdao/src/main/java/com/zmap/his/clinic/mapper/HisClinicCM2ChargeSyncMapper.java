package com.zmap.his.clinic.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.zmap.his.clinic.model.HisClinicCM1SyncModel;
import com.zmap.his.clinic.model.HisClinicCM2ChargeSyncModel;

@Mapper
public interface HisClinicCM2ChargeSyncMapper {
	int countNum();//统计总数
	int countNumWithDate(String curDate);
	HisClinicCM2ChargeSyncModel selectTop1();
	List<HisClinicCM2ChargeSyncModel> selectTop50();
	List<HisClinicCM2ChargeSyncModel> selectByPagination(Map<String,Object> map);//超过50条后使用分页
	List<HisClinicCM2ChargeSyncModel> selectByPaginationWithDateTime(Map<String,Object> map);//超过50条后使用分页
}
