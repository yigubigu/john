package com.zmap.his.basedata.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.zmap.his.basedata.model.RPatientModel;
@Mapper
public interface RPatientMapper{
	int countNum();//统计总数
	int countNumWithDate(String curDate);
	RPatientModel selectTop1();
	List<RPatientModel> selectTop50();
	List<RPatientModel> selectByPagination(Map<String,Object> map);
	List<RPatientModel> selectByPaginationWithDateTime(Map<String,Object> map);
}
