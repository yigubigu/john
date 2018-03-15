package com.zmap.his.clinic.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.zmap.his.clinic.model.HisClinicCM1SyncModel;
import com.zmap.his.clinic.model.HisClinicCM2DrugUseSyncModel;

@Mapper
public interface HisClinicCM2DrugUseSyncMapper {
	int countNum();//统计总数
	int updateNum();
	HisClinicCM2DrugUseSyncModel selectTop1();
	List<HisClinicCM2DrugUseSyncModel> selectTop50();
	List<HisClinicCM2DrugUseSyncModel> selectCinicDrugUseFromHis();
	List<HisClinicCM2DrugUseSyncModel> selectClinicDrugUseByPagination(Map<String,Object> map);//分页
	List<HisClinicCM2DrugUseSyncModel> selectClinicDrugUseByPaginationWithDateTime(Map<String,Object> map);//超过50条后使用分页
}
