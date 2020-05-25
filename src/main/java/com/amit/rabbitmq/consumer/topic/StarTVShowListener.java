package com.amit.rabbitmq.consumer.topic;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class StarTVShowListener {

	@RabbitListener(queues = "${application.tvshow.star-queue}", autoStartup = "true")
	public void listen(@Payload Telecast show) {
		log.info("STAR LOGO : " + show);
	}
	
}
