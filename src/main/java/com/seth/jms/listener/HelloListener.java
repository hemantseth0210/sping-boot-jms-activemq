package com.seth.jms.listener;

import java.util.UUID;

import javax.jms.JMSException;
import javax.jms.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.JmsException;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.seth.jms.config.JMSConfig;
import com.seth.jms.model.HelloWorldMessage;

/**
 * @author heseth
 *
 */

@Component
public class HelloListener {
	
	@Autowired
	private JmsTemplate jmsTemplate;

	@JmsListener(destination = JMSConfig.MY_QUEUE)
	public void listen(@Payload HelloWorldMessage helloWorldMessage, @Headers MessageHeaders headers, Message message) {
		System.out.println("I got a Message !!!");
		System.out.println(helloWorldMessage);
	}
	
	@JmsListener(destination = JMSConfig.MY_SEND_RECEIVE_QUEUE)
	public void listenForHello(@Payload HelloWorldMessage helloWorldMessage, @Headers MessageHeaders headers, Message message) throws JmsException, JMSException {
		
		HelloWorldMessage payloadMsg = new HelloWorldMessage();
		payloadMsg.setId(UUID.randomUUID());
		payloadMsg.setMessage("Received Hello World");
		
		jmsTemplate.convertAndSend(message.getJMSReplyTo(), payloadMsg);
	}
}
