package com.zmap.his.basedata.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.zmap.his.basedata.model.LExamineModel;

@Mapper
public interface LExamineMapper {
	int countNum();//统计总数
	int countNumWithDate(String curDate);
	LExamineModel selectTop1();
	List<LExamineModel> selectTop50();
	List<LExamineModel> selectByPagination(Map<String,Object> map);
	List<LExamineModel> selectByPaginationWithDateTime(Map<String,Object> map);
}
