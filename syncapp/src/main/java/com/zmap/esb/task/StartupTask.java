package com.zmap.esb.task;

import java.util.UUID;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;


/*
@Component
  @Order(value=1)*/
public class StartupTask implements CommandLineRunner {
	
	
	@Override
	public void run(String... arg) throws Exception {
		new Thread(){
			@Override
			public void run() {
				super.run();
				 try {
						ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://118.178.125.163:61616");
						 
						 Connection connection = connectionFactory.createConnection();
						 connection.start();

						 // Create a Session
						 Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

						 // Create the destination (Topic or Queue)
						 Destination destination = session.createQueue("hospital.out");

						 // Create a MessageProducer from the Session to the Topic or Queue
						 MessageProducer producer = session.createProducer(destination);
						 producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

						 // Create a messages
						 for(int i=0;i<30000;i++){
							 String text = UUID.randomUUID()+"Hello world!";
							 TextMessage message = session.createTextMessage(text);
							 producer.send(message);
						 }
						 session.close();
						 connection.close();
					} catch (JMSException e) {
						e.printStackTrace();
					}
			}
			
		};
		
	}

}
