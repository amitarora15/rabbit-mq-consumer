package com.amit.rabbitmq.consumer.failure.dlq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.amit.rabbitmq.consumer.fanout.Match;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class EspnChannelMatchScoreListenerWithDlq {


	/**
	 * First retry as per project configuration. Then send message to dlq
	 */
	@RabbitListener(queues = "${application.fifa.espn-failure-withdlq-queue}", autoStartup = "true")
	public void listenFailure1(@Payload Match match) {
		
		log.info("Message received on ESPN FAILURE WITH DLQ channel for FIFA update : " + match);
		throw new RuntimeException("Failure in processing message, to be handled by ESPN DLQ"); 
	}
	
}
