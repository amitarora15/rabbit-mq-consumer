package com.amit.rabbitmq.consumer.fanout;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class StarChannelMatchScoreListener {
	
	@RabbitListener(queues = "${application.fifa.star-queue}", autoStartup = "true")
	public void listen(@Payload Match match) {
		log.info("Message received on STAR channel for FIFA update : " + match);
	}
	
}
