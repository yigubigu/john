package com.zmap.config;

import javax.jms.Queue;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageQueconfig {
	
	@Bean
	public Queue queue() {
		return new ActiveMQQueue("sqlserver.data.queue");
	}

	@Bean(name="baseDataQueue")
	public Queue baseDataQueue() {
		return new ActiveMQQueue("base.data.queue");
	}
	
	/*@Bean(name="hospitalQueue")
	public Queue hospitalQueue() {
		return new ActiveMQQueue("hospital.out");
	}
	
	@Bean(name="hospitalTopic")
	public Topic hospitalTopic(){
		return new ActiveMQTopic("hospital.topic.out");
	}
	
	@Bean(name="activeMQConnectionFactory")
	public ActiveMQConnectionFactory activeMQConnectionFactory (){
	        ActiveMQConnectionFactory activeMQConnectionFactory =
	                new ActiveMQConnectionFactory(
	                        ActiveMQConnectionFactory.DEFAULT_USER,
	                        ActiveMQConnectionFactory.DEFAULT_PASSWORD,
	                        "tcp://118.178.125.163:61616");
//	                        ActiveMQConnectionFactory.DEFAULT_BROKER_URL);
	        return activeMQConnectionFactory;
   }
	
	@Bean(name="jmsMessagingTemplate")
	public  JmsMessagingTemplate jmsMessagingTemplate(@Qualifier("activeMQConnectionFactory") ActiveMQConnectionFactory activeMQConnectionFactory){
		JmsMessagingTemplate jmsMessagingTemplate=new JmsMessagingTemplate();
		jmsMessagingTemplate.setConnectionFactory(activeMQConnectionFactory);
		return jmsMessagingTemplate;
	}*/

}
