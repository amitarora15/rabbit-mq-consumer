package com.amit.rabbitmq.consumer.delayex;

import java.util.Map;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ImageCompressor {

	@RabbitListener(queues = "${application.image.compression.queue}", autoStartup = "true")
	public void listen(@Payload Image image, @Header(required = false, name = "x-delay") Map<String, String> xDelay) {
		log.info("Got message after delay " + image + " with delay " + xDelay);
	}
	
}
