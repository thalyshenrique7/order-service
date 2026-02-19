package com.ms.order.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.config.RetryInterceptorBuilder;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class RabbitMQConfig {

	@Value("${broker.exchange.order}")
	private String orderExchange;

	@Value("${broker.exchange.stock}")
	private String stockExchange;

	@Value("${order.stock.reserved.queue}")
	private String stockReservedQueue;

	@Value("${order.stock.failed.queue}")
	private String stockFailedQueue;

	@Bean
	public Jackson2JsonMessageConverter messageConverter() {

		ObjectMapper objectMapper = new ObjectMapper();
		return new Jackson2JsonMessageConverter(objectMapper);
	}

	@Bean
	public TopicExchange orderExchange() {

		return new TopicExchange(orderExchange, true, false);
	}

	@Bean(name = "orderListenerContainerFactory")
	public SimpleRabbitListenerContainerFactory orderListenerContainerFactory(ConnectionFactory connectionFactory,
			Jackson2JsonMessageConverter messageConverter) {

		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();

		factory.setConnectionFactory(connectionFactory);
		factory.setMessageConverter(messageConverter);

		factory.setAdviceChain(
				RetryInterceptorBuilder.stateless().maxAttempts(5).backOffOptions(1000, 2.0, 10000).build());

		return factory;
	}

	@Bean
	public Queue stockReservedQueue() {

		return new Queue(stockReservedQueue);
	}

	@Bean
	public Queue stockFailedQueue() {

		return new Queue(stockFailedQueue);
	}

	@Bean
	public Binding stockReservedBinding(Queue stockReservedQueue, TopicExchange stockExchange) {

		return BindingBuilder.bind(stockReservedQueue).to(stockExchange).with("stock.reserved");
	}

	@Bean
	public Binding stockFailedBinding(Queue stockFailedQueue, TopicExchange stockExchange) {

		return BindingBuilder.bind(stockFailedQueue).to(stockExchange).with("stock.failed");
	}

	@Bean
	public TopicExchange stockExchange() {

		return new TopicExchange(stockExchange, true, false);
	}

}
