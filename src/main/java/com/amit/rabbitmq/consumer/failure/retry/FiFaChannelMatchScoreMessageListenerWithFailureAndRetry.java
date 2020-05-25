package com.amit.rabbitmq.consumer.failure.retry;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.amit.rabbitmq.consumer.fanout.Match;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class FiFaChannelMatchScoreMessageListenerWithFailureAndRetry {

	/**
	 * Failure handled through default configuration. default flow is to re-queue. 
	 * if retry is defined, then tried till it exhausted, and then print
	 * Retries exhausted for message (Body:'{"opponent1":"BRAZIL","opponent2":"PORTUGAL","score":"2-3","tim
	 */
	@RabbitListener(queues = "${application.fifa.espn-failure-queue}", autoStartup = "true", concurrency = "3-10")
	public void listenFailure1(@Payload Match match) {
		
		int random = ThreadLocalRandom.current().nextInt(2);
		log.info("Message received on ESPN FAILURE channel for FIFA update : " + match + " with random " + random);
		if (random == 0) {
			log.error("Failure in processing message for ESPN FAILURE : " + match);
			throw new RuntimeException("Failure in processing message"); 
		}
		if (random == 1) {
			log.info("Successfully processed message this time for ESPN FAILURE : " + match);
		}
	}
	
	/**
	 * Failure handled through factory configuration. default flow is to re-queue. 
	 * if retry is defined, then tried till it exhausted, and then print
	 * Retries exhausted for message (Body:'{"opponent1":"BRAZIL","opponent2":"PORTUGAL","score":"2-3","tim
	 */
	@RabbitListener(queues = "${application.fifa.star-failure-queue}", autoStartup = "true", containerFactory = "configured_connection_factory")
	public void listenFailure2(@Payload Match match) {
		
		int random = ThreadLocalRandom.current().nextInt(2);
		log.info("Message received on STAR FAILURE channel for FIFA update : " + match + " with random " + random);
		if (random == 0) {
			log.error("Failure in processing message for STAR FAILURE : " + match);
			throw new RuntimeException("Failure in processing message"); 
		}
		if (random == 1) {
			log.info("Successfully processed message this time for STAR FAILURE : " + match);
		}
	}
	
}
