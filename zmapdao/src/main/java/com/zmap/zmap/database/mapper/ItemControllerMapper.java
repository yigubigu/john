package com.zmap.zmap.database.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zmap.zmap.database.model.ItemControllerModel;

@Mapper
public interface ItemControllerMapper {
	
	List<ItemControllerModel> selectItemType (HashMap<String, String>paramt);
	List<ItemControllerModel> selectItemFromZmapByInfro( HashMap<String, String> params);

}
