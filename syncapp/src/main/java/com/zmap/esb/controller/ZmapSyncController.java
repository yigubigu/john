package com.zmap.esb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zmap.esb.service.ZmapClinicSyncService;
import com.zmap.esb.service.ZmapHospitalizeSyncService;

@RestController
@RequestMapping("/api/sync")
public class ZmapSyncController {
	@Autowired
	private ZmapClinicSyncService zmapClinicSyncService;
	@Autowired
	private ZmapHospitalizeSyncService zmapHospitalizeSyncService;
	
	@RequestMapping("/startSync")
	public void startSync(@RequestBody String whichDay) {
		try {
			zmapClinicSyncService.readCln2MQ(whichDay);
			zmapHospitalizeSyncService.readHos2MQ(whichDay);
			zmapClinicSyncService.readClnCharge2MQ(whichDay);
			zmapHospitalizeSyncService.readHosCharge2MQ(whichDay);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
