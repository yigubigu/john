package com.zmap.zmap.framework.base.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zmap.zmap.framework.base.model.Patient360Model;
@Mapper
public interface Patient360Mapper {
	List<Patient360Model> searchForPatients(HashMap<String, String> params);
	List<Patient360Model> viewDetails(HashMap<String, String> params);
	List<Patient360Model> visitingTimes(HashMap<String, String> params);
    
}
