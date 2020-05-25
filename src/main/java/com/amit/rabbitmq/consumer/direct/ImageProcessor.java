package com.amit.rabbitmq.consumer.direct;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ImageProcessor {

	@RabbitListener(queues = "${application.image.operation.queue}", autoStartup = "true")
	public void listen(@Payload ImageType image) {
		log.info("Processsing: " + image);
	}
	
}
