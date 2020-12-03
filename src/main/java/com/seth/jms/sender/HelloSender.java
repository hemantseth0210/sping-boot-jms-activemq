/**
 * 
 */
package com.seth.jms.sender;

import java.util.UUID;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.scheduling.annotation.Scheduled;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.seth.jms.config.JMSConfig;
import com.seth.jms.model.HelloWorldMessage;

/**
 * @author heseth
 *
 */
@Configuration
public class HelloSender {

	@Autowired
	private JmsTemplate jmsTemplate;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Scheduled(fixedRate = 2000)
	public void sendMessage() {
		System.out.println("I'm sending a message");
		
		HelloWorldMessage message = new HelloWorldMessage();
		message.setId(UUID.randomUUID());
		message.setMessage("Hello World");
		
		jmsTemplate.convertAndSend(JMSConfig.MY_QUEUE, message);
		
		System.out.println("Message Sent");
	}
	
	@Scheduled(fixedRate = 2000)
	public void sendAndReceiveMessage() throws JMSException {
		
		HelloWorldMessage message = new HelloWorldMessage();
		message.setId(UUID.randomUUID());
		message.setMessage("Hello World");
		
		Message receivedMsg = jmsTemplate.sendAndReceive(JMSConfig.MY_SEND_RECEIVE_QUEUE, new MessageCreator() {
			
			@Override
			public Message createMessage(Session session) throws JMSException {
				Message helloMessage;
				try {
					helloMessage = session.createTextMessage(objectMapper.writeValueAsString(message));
					helloMessage.setStringProperty("_type", "com.seth.jms.model.HelloWorldMessage");
					System.out.println("Sending Hello");
					return helloMessage;
				} catch (JsonProcessingException e) {
					throw new JMSException("Something went wrong with JMS");
				}
				
			}
		});
		
		System.out.println("Received reply back " + receivedMsg.getBody(String.class));
	}
}
