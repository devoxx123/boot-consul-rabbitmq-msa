package com.msg.consul.one.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.msg.consul.one.model.NameValueTO;
import com.msg.consul.one.queue.ServiceOneRabbitMQBean;

@Service("serviceOneRabbitMessageProducer")
public class ServiceOneRabbitMessageProducer {

	private Logger logger = LoggerFactory.getLogger(ServiceOneRabbitMessageProducer.class);

	@Autowired
	private RabbitTemplate rabbitTemplate;

	public void sendMessageToQueue(final NameValueTO message) {

		try {
			logger.info("Sending message (" + message + ") to RabbitMQ's exchange ("
					+ ServiceOneRabbitMQBean.exchangeName + ")");
			this.rabbitTemplate.convertAndSend(ServiceOneRabbitMQBean.exchangeName,
					ServiceOneRabbitMQBean.routingKeyName, new ObjectMapper().writeValueAsString(message));
		} catch (MessagingException | JsonProcessingException e) {

		}
	}
}
