package com.zmap.zmap.framework.base.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;



import com.zmap.zmap.framework.base.model.SyncDataBaseModel;



@Mapper
public interface SyncDataBaseMapper {
	
	  int insert(SyncDataBaseModel record);
	  int insert2ErrLog(SyncDataBaseModel record);
	  List<SyncDataBaseModel> selectSyncDataModeList();
	  int updateStatusByPrimaryKey(SyncDataBaseModel record);

}
