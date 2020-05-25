package com.amit.rabbitmq.consumer.header;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class NotificationListeners {

	@RabbitListener(queues = "${application.notification.slack-queue}", autoStartup = "true")
	public void listenSlackMessage(@Payload Notification notification) throws InterruptedException {
		log.info("Got message on slack channel " + notification);
		Thread.sleep(10000);
	}
	
	@RabbitListener(queues = "${application.notification.hangout-queue}", autoStartup = "true", concurrency = "3-10")
	public void listenHangoutMessage(@Payload Notification notification) throws InterruptedException {
		log.info("Got message on hangout channel " + notification);
		Thread.sleep(20000);
	}
	
	@RabbitListener(queues = "${application.notification.all-queue}", autoStartup = "true")
	public void listenAllMessage(@Payload Notification notification) {
		log.info("Got messssage on all header match channel " + notification);
	}
	
	@RabbitListener(queues = "${application.notification.any-queue}", autoStartup = "true")
	public void listenAnyMessage(@Payload Notification notification) {
		log.info("Got messssage on any header match channel " + notification);
	}
	
	
}
