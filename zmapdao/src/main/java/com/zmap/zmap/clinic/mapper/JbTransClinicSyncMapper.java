package com.zmap.zmap.clinic.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.zmap.zmap.clinic.model.JbTransClinicSyncModel;

@Mapper
public interface JbTransClinicSyncMapper {
	int deleteByPrimaryKey(String clinicId);
	JbTransClinicSyncModel selectByPrimaryKey(JbTransClinicSyncModel record);
	JbTransClinicSyncModel selectByHisPrimaryKey(JbTransClinicSyncModel record);
	int deleteByPrimaryKey(JbTransClinicSyncModel record);
	int deleteByHisPrimaryKey(JbTransClinicSyncModel record);
	int insert(JbTransClinicSyncModel record);
}
