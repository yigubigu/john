package com.zmap.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

//import org.intelligentsia.hirondelle.date4j.DateTime;

public class DateUtil {
	
	public static List getHoursByRule(int hour,int start,int end){
		List hours=new ArrayList();
		for(int i=start;i<=end;){
			hours.add(i);
			i=i+hour;
		}
		return hours;
	}
	
	public static List<String> getHoursByRule(String date,int step){
		
		List<String> list=new ArrayList<String>();
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat ft=new SimpleDateFormat("yyyy-MM-dd HH:ss:mm");
		Date curDate=null;
		try {
			curDate = format.parse(date);
			for(int i=0;i<=24;){
				Calendar calender=Calendar.getInstance();
				calender.setTime(curDate);
				calender.add(Calendar.HOUR_OF_DAY, i);
				String time=ft.format(calender.getTime());
				i=i+step;
				list.add(time);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	
	public static String parseHourToString(int hour){
		String result=null;
		if(hour<10){
			result=String.valueOf("0"+hour);
		}else{
			result=String.valueOf(hour);
		}
		return result;
	}
	
	

}
