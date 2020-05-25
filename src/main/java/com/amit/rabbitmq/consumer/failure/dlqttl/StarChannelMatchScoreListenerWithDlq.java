package com.amit.rabbitmq.consumer.failure.dlqttl;

import java.util.Map;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.amit.rabbitmq.consumer.fanout.Match;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class StarChannelMatchScoreListenerWithDlq {

	/**
	 * First retry as per project configuration. Then send message to dlq
	 */
	@RabbitListener(queues = "${application.fifa.star-failure-withdlqttl-queue}", autoStartup = "true")
	public void listenFailure1(@Payload Match match, @Header(AmqpHeaders.DELIVERY_TAG) Long tag,
			@Header(required = false, name = "x-death") Map<String, String> xDeath) {
		
		log.info("Message received on STAR FAILURE WITH DLQ TTL channel for FIFA update : " + match + "with timestamp "
				+ System.currentTimeMillis() + " xdeath: " + xDeath);
		if(xDeath != null && Integer.parseInt(xDeath.get("count")) == 2) {
			log.info("Second time in got from DLQ, so now consuming this message"); //Can be used to raise alert
		} else {
			throw new RuntimeException("Failure in processing message, to be handled by STAR DLQ");
		}
		
	}

}
