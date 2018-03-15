package com.zmap.his.clinic.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.zmap.his.clinic.model.HisClinicCM1SyncModel;
import com.zmap.his.clinic.model.HisClinicCM2ExamineSyncModel;

@Mapper
public interface HisClinicCM2ExamineSyncMapper {
	int countNum();//统计总数
	int updateNum();
	HisClinicCM2ExamineSyncModel selectTop1();
	List<HisClinicCM2ExamineSyncModel> selectTop50();
	List<HisClinicCM2ExamineSyncModel> selectCinicExamineFromHis();
	List<HisClinicCM2ExamineSyncModel> selectClinicExamineByPagination(Map<String,Object> map);//分页
	List<HisClinicCM2ExamineSyncModel> selectClinicExamineByPaginationWithDateTime(Map<String,Object> map);//超过50条后使用分页
}
