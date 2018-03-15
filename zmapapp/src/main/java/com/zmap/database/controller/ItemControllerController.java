package com.zmap.database.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.zmap.zmap.database.mapper.ItemControllerMapper;


@RestController
@RequestMapping("/api/service/itemController")
public class ItemControllerController {
     @Autowired
     private ItemControllerMapper itemControllerMapper;
     
     @RequestMapping("/select")
     @ResponseBody
     public JSONPObject select(HttpServletRequest request,HttpServletResponse response) {
    	 String callbackFunName = request.getParameter("callbackparams");
    	 String hospitalCode = request.getParameter("hospitalCode");
    	 String itemTypeName = request.getParameter("itemTypeName");
    	 
    	 return null;
     }
}
