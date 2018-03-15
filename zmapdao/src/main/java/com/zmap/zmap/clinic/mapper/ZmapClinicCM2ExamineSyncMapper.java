package com.zmap.zmap.clinic.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zmap.zmap.clinic.model.ZmapClinicCM2DrugUseSyncModel;
import com.zmap.zmap.clinic.model.ZmapClinicCM2ExamineSyncModel;

@Mapper
public interface ZmapClinicCM2ExamineSyncMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zmap_m2_examine
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(String examId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zmap_m2_examine
     *
     * @mbg.generated
     */
    int insert(ZmapClinicCM2ExamineSyncModel record);

    ZmapClinicCM2ExamineSyncModel selectByHisPrimaryKey(String value);//根据his库的业务主键判断是否已存在
    int batchInsert(List<ZmapClinicCM2ExamineSyncModel> record);//批量插入
    int batchUpdate(List<ZmapClinicCM2ExamineSyncModel> record);//批量更新
    int updateClinicID();//update clinic_id
    int deleteByHisPrimaryKey(String value);
    int updateByHisPrimaryKey(ZmapClinicCM2ExamineSyncModel record);
    
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zmap_m2_examine
     *
     * @mbg.generated
     */
    int insertSelective(ZmapClinicCM2ExamineSyncModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zmap_m2_examine
     *
     * @mbg.generated
     */
    ZmapClinicCM2ExamineSyncModel selectByPrimaryKey(String examId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zmap_m2_examine
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(ZmapClinicCM2ExamineSyncModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zmap_m2_examine
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(ZmapClinicCM2ExamineSyncModel record);
}