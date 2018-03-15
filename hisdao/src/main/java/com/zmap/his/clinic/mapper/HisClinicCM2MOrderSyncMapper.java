package com.zmap.his.clinic.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.zmap.his.clinic.model.HisClinicCM2MOrderSyncModel;

@Mapper
public interface HisClinicCM2MOrderSyncMapper {
	int countNum();//统计总数
	int countNumWithDate(String curDate);
	HisClinicCM2MOrderSyncModel selectTop1();
	List<HisClinicCM2MOrderSyncModel> selectTop50();
	List<HisClinicCM2MOrderSyncModel> selectByPagination(Map<String,Object> map);//超过50条后使用分页
	List<HisClinicCM2MOrderSyncModel> selectByPaginationWithDateTime(Map<String,Object> map);//超过50条后使用分页
}
