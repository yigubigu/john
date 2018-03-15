package com.zmap.his.clinic.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.zmap.his.clinic.model.HisClinicCM1SyncModel;
import com.zmap.his.clinic.model.HisClinicCM2DiagnoseSyncModel;
@Mapper
public interface HisClinicCM2DiagnoseSyncMapper {
	int countNum();//统计总数
	int updateNum();
	HisClinicCM2DiagnoseSyncModel selectTop1();
	List<HisClinicCM2DiagnoseSyncModel> selectTop50();
	List<HisClinicCM2DiagnoseSyncModel> selectCinicDiagnoseFromHis();
	List<HisClinicCM2DiagnoseSyncModel> selectClinicDiagnoseByPagination(Map<String,Object> map);//分页
	List<HisClinicCM2DiagnoseSyncModel> selectClinicDiagnoseByPaginationWithDateTime(Map<String,Object> map);//超过50条后使用分页
}
