package com.zmap.zmap.framework.base.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zmap.zmap.framework.base.model.ZmapRPatientModel;
@Mapper
public interface ZmapRPatientMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zmap_r_patient
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(String patientId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zmap_r_patient
     *
     * @mbg.generated
     */
    int insert(ZmapRPatientModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zmap_r_patient
     *
     * @mbg.generated
     */
    int insertSelective(ZmapRPatientModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zmap_r_patient
     *
     * @mbg.generated
     */
    ZmapRPatientModel selectByPrimaryKey(String patientId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zmap_r_patient
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(ZmapRPatientModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zmap_r_patient
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(ZmapRPatientModel record);
    
    ZmapRPatientModel selectByHisPrimaryKey(ZmapRPatientModel record);//根据his库的业务主键判断是否已存在
    int deleteByHisPrimaryKey(ZmapRPatientModel record);
}