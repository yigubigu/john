package com.zmap.his.basedata.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.zmap.his.basedata.model.LFeeItemModel;
@Mapper
public interface LFeeItemMapper {
	int countNum();//统计总数
	int countNumWithDate(String curDate);
	LFeeItemModel selectTop1();
	List<LFeeItemModel> selectTop50();
	List<LFeeItemModel> selectByPagination(Map<String,Object> map);
	List<LFeeItemModel> selectByPaginationWithDateTime(Map<String,Object> map);
}
