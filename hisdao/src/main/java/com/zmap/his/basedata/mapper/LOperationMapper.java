package com.zmap.his.basedata.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.zmap.his.basedata.model.LOperationModel;
@Mapper
public interface LOperationMapper {
	int countNum();//统计总数
	int countNumWithDate(String curDate);
	LOperationModel selectTop1();
	List<LOperationModel> selectTop50();
	List<LOperationModel> selectByPagination(Map<String,Object> map);
	List<LOperationModel> selectByPaginationWithDateTime(Map<String,Object> map);
}
