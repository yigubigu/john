package com.zmap.his.basedata.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.zmap.his.basedata.model.RDoctorModel;

@Mapper
public interface RDoctorMapper {
	int countNum();//统计总数
	int countNumWithDate(String curDate);
	RDoctorModel selectTop1();
	List<RDoctorModel> selectTop50();
	List<RDoctorModel> selectByPagination(Map<String,Object> map);
	List<RDoctorModel> selectByPaginationWithDateTime(Map<String,Object> map);
}
