package com.ac.microservices.dispatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfiguration {

    @Value("${rabbitmq.username}")
    private String username;

    @Value("${rabbitmq.password}")
    private String password;

    @Value("${rabbitmq.queue.product.name}")
    private String queueProductName;

    @Value("${rabbitmq.queue.product.update.name}")
    private String queueProductUpdateName;

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${rabbitmq.addresses}")
    private String addresses;

    @Autowired
    private ObjectMapper objectMapper;

    @Bean
    ConnectionFactory connectionFactory() {
        CachingConnectionFactory factory = new CachingConnectionFactory();
        factory.setUsername(username);
        factory.setPassword(password);
        factory.setAddresses(addresses);
        return factory;
    }

    @Bean
    RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
        converter.setJsonObjectMapper(objectMapper);
        rabbitTemplate.setMessageConverter(converter);
        rabbitTemplate.setExchange(exchangeName);
        return rabbitTemplate;
    }

    @Bean
    Queue productQueue() {
        return new Queue(queueProductName);
    }

    @Bean
    Queue productUpdateQueue() {
        return new Queue(queueProductUpdateName);
    }

    @Bean
    DirectExchange directExchange() {
        return new DirectExchange(exchangeName);
    }

    @Bean
    Binding productCommandBinding() {
        return BindingBuilder.bind(productQueue()).to(directExchange()).with("product");
    }

    @Bean
    Binding productUpdateCommandBinding() {
        return BindingBuilder.bind(productUpdateQueue()).to(directExchange()).with("product-update");
    }
}
