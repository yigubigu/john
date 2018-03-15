package com.zmap.his.basedata.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.zmap.his.basedata.model.LAcctItemModel;

@Mapper
public interface LAcctItemMapper {
	int countNum();//统计总数
	int countNumWithDate(String curDate);
	LAcctItemModel selectTop1();
	List<LAcctItemModel> selectTop50();
	List<LAcctItemModel> selectByPagination(Map<String,Object> map);
	List<LAcctItemModel> selectByPaginationWithDateTime(Map<String,Object> map);
}
