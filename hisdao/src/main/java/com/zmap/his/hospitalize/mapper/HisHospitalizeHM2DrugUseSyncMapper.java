package com.zmap.his.hospitalize.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.zmap.his.hospitalize.model.HisHospitalizeHM1HospitalizeSyncModel;
import com.zmap.his.hospitalize.model.HisHospitalizeHM2DrugUseSyncModel;

@Mapper
public interface HisHospitalizeHM2DrugUseSyncMapper {

	int countNum();//统计总数
	int updateNum();
	HisHospitalizeHM2DrugUseSyncModel selectOneResult();
	List<HisHospitalizeHM2DrugUseSyncModel> selectTop50();
	List<HisHospitalizeHM2DrugUseSyncModel> selectByPagination(Map<String,Object> map);//分页
	List<HisHospitalizeHM2DrugUseSyncModel> selectHM2DrugUseByPaginationWithDateTime(Map<String,Object> map);//超过50条后使用分页

}
