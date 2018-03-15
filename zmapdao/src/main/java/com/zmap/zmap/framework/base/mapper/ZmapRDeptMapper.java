package com.zmap.zmap.framework.base.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.zmap.zmap.framework.base.model.ZmapRDeptModel;



@Mapper
public interface ZmapRDeptMapper {
	
	
	   /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zmap_r_dept
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(String deptId);

    /**
     * This method was generated by MyBatis Generator.
     *
     * @mbg.generated
     */
    int insert(ZmapRDeptModel record);
    
    
    ZmapRDeptModel selectByHisPrimaryKey(String value);//根据his库的业务主键判断是否已存在
    String getZmapDeptCodeByDeptCodeAndHosCode(Map<String,Object> map);
    int batchInsert(List<ZmapRDeptModel> record);//批量插入
    int batchUpdate(List<ZmapRDeptModel> record);//批量更新
    int deleteByHisPrimaryKey(String value);
    int updateByHisPrimaryKey(ZmapRDeptModel record);

    /**
     * This method was generated by MyBatis Generator.
     *
     * @mbg.generated
     */
    int insertSelective(ZmapRDeptModel record);

    /**
     * This method was generated by MyBatis Generator.
     *
     * @mbg.generated
     */
    ZmapRDeptModel selectByPrimaryKey(String deptId);

    /**
     * This method was generated by MyBatis Generator.
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(ZmapRDeptModel record);

    /**
     * This method was generated by MyBatis Generator.
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(ZmapRDeptModel record);
    
    List<ZmapRDeptModel> selectDetpsByDeptCodes(HashMap prams);

}