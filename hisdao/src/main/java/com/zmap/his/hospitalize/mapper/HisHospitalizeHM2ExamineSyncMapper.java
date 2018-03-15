package com.zmap.his.hospitalize.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.zmap.his.hospitalize.model.HisHospitalizeHM2DrugUseSyncModel;
import com.zmap.his.hospitalize.model.HisHospitalizeHM2ExamineSyncModel;
@Mapper
public interface HisHospitalizeHM2ExamineSyncMapper {
	int countNum();//统计总数
	int updateNum();
	HisHospitalizeHM2ExamineSyncModel selectOneResult();
	List<HisHospitalizeHM2ExamineSyncModel> selectTop50();
	List<HisHospitalizeHM2ExamineSyncModel> selectByPagination(Map<String,Object> map);//分页
	List<HisHospitalizeHM2ExamineSyncModel> selectHM2ExamineByPaginationWithDateTime(Map<String,Object> map);//超过50条后使用分页

}
