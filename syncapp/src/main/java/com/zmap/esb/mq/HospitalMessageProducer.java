package com.zmap.esb.mq;


import javax.jms.Queue;
import javax.jms.Topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;



//@Component("hospitalMessageProducer")
public class HospitalMessageProducer  {


	/*@Autowired
	private   Queue hospitalQueue;
	
	@Autowired
	private   Topic hospitalTopic;

	@Autowired
	private   JmsMessagingTemplate jmsMessagingTemplate;

	
	public   void sendTextToQueue(String text){
		jmsMessagingTemplate.convertAndSend(hospitalQueue, text);
	}
	
	public   void sendTextToTopic(String text){
		jmsMessagingTemplate.convertAndSend(hospitalTopic, text);
	}
	
	

//	@Scheduled(fixedRate = 5000)
//	public void publishEventToQueue() throws InterruptedException {
//			this.jmsMessagingTemplate.convertAndSend(this.hospitalQueue, "ddd");
//		
//
//	}
*/}
