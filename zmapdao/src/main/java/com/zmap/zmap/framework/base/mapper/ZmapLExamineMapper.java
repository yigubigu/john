package com.zmap.zmap.framework.base.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.zmap.zmap.framework.base.model.ZmapLExamineModel;

@Mapper
public interface ZmapLExamineMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zmap_l_examine
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(String examId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zmap_l_examine
     *
     * @mbg.generated
     */
    int insert(ZmapLExamineModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zmap_l_examine
     *
     * @mbg.generated
     */
    int insertSelective(ZmapLExamineModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zmap_l_examine
     *
     * @mbg.generated
     */
    ZmapLExamineModel selectByPrimaryKey(String examId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zmap_l_examine
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(ZmapLExamineModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zmap_l_examine
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(ZmapLExamineModel record);
    ZmapLExamineModel selectByHisPrimaryKey(ZmapLExamineModel record);
    int deleteByHisPrimaryKey(ZmapLExamineModel record);
}