package com.amit.rabbitmq.consumer.failure.dlq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.amit.rabbitmq.consumer.fanout.Match;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class EspnDlqMessageListener {

	@RabbitListener(queues = "${application.fifa.espn-failure-dlq}", autoStartup = "true")
	public void listenFailure1(@Payload Match match) {
		log.info("Message received on ESPN DLQ channel for FIFA update : " + match);
	}

}
