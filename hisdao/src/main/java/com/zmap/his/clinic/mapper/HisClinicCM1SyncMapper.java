package com.zmap.his.clinic.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.zmap.his.clinic.model.HisClinicCM1SyncModel;

@Mapper
public interface HisClinicCM1SyncMapper {
	int countNum();//统计总数
	int countNumWithDate(String curDate);
	HisClinicCM1SyncModel selectTop1();
	List<HisClinicCM1SyncModel> selectTop50();
	List<HisClinicCM1SyncModel> selectByPagination(Map<String,Object> map);//超过50条后使用分页
	List<HisClinicCM1SyncModel> selectByPaginationWithDateTime(Map<String,Object> map);//超过50条后使用分页
}
