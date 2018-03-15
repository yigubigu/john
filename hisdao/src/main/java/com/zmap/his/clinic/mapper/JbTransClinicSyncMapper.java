package com.zmap.his.clinic.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.zmap.his.clinic.model.JbTransClinicSyncModel;
@Mapper
public interface JbTransClinicSyncMapper {
	int countNum();//统计总数
	int countNumWithDate(String curDate);
	JbTransClinicSyncModel selectTop1();
	List<JbTransClinicSyncModel> selectTop50();
	List<JbTransClinicSyncModel> selectByPagination(Map<String,Object> map);//超过50条后使用分页
	List<JbTransClinicSyncModel> selectByPaginationWithDateTime(Map<String,Object> map);//超过50条后使用分页
}
