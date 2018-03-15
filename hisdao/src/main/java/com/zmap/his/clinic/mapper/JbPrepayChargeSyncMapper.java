package com.zmap.his.clinic.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.zmap.his.clinic.model.JbPrepayChargeSyncModel;

@Mapper
public interface JbPrepayChargeSyncMapper {
	int countNum();//统计总数
	int countNumWithDate(String curDate);
	JbPrepayChargeSyncModel selectTop1();
	List<JbPrepayChargeSyncModel> selectTop50();
	List<JbPrepayChargeSyncModel> selectByPagination(Map<String,Object> map);
	List<JbPrepayChargeSyncModel> selectByPaginationWithDateTime(Map<String,Object> map);
}
