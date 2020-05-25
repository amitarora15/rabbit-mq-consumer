package com.amit.rabbitmq.consumer;

import org.springframework.amqp.rabbit.config.RetryInterceptorBuilder;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.retry.RejectAndDontRequeueRecoverer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.interceptor.RetryOperationsInterceptor;

@Configuration("Configuration")
public class Config {

	@Bean
	public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean("configured_connection_factory")
	public SimpleRabbitListenerContainerFactory myRabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		factory.setConnectionFactory(connectionFactory);
		factory.setConcurrentConsumers(10);
		factory.setMaxConcurrentConsumers(20);
		factory.setMessageConverter(jackson2JsonMessageConverter());
		factory.setAdviceChain(workMessagesRetryInterceptor());
		return factory;
	}

	@Bean
	public RetryOperationsInterceptor workMessagesRetryInterceptor() {
		return RetryInterceptorBuilder.stateless().maxAttempts(2).backOffOptions(1000, 2, 10000)
				.recoverer(new RejectAndDontRequeueRecoverer()).build();
	}

}
