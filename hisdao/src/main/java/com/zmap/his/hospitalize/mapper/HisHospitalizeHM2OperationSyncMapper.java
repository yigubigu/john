package com.zmap.his.hospitalize.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;


import com.zmap.his.hospitalize.model.HisHospitalizeHM2DrugUseSyncModel;
import com.zmap.his.hospitalize.model.HisHospitalizeHM2OperationSyncModel;

@Mapper
public interface HisHospitalizeHM2OperationSyncMapper {
	
	int countNum();//统计总数
	int updateNum();
	HisHospitalizeHM2OperationSyncModel selectOneResult();
	List<HisHospitalizeHM2OperationSyncModel> selectTop50();
	List<HisHospitalizeHM2OperationSyncModel> selectByPagination(Map<String,Object> map);//分页
	List<HisHospitalizeHM2OperationSyncModel> selectHM2OperationByPaginationWithDateTime(Map<String,Object> map);//超过50条后使用分页

}
