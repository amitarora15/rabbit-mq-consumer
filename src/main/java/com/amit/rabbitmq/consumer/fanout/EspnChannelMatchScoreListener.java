package com.amit.rabbitmq.consumer.fanout;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class EspnChannelMatchScoreListener {

	@RabbitListener(queues = "${application.fifa.espn-queue}", autoStartup = "true")
	public void listen(@Payload Match match) {
		log.info("Message received on ESPN channel for FIFA update : " + match);
	}

}
