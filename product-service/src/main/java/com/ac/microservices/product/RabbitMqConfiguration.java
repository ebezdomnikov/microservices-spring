package com.ac.microservices.product;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${rabbitmq.addresses}")
    private String addresses;

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
        rabbitTemplate.setExchange(exchangeName);
        return rabbitTemplate;
    }

    @Bean
    Queue productQueue() {
        return new Queue(queueProductName);
    }

    @Bean
    DirectExchange directExchange() {
        return new DirectExchange(exchangeName);
    }

    @Bean
    Binding orderCommandBinding() {
        return BindingBuilder.bind(productQueue()).to(directExchange()).with("product");
    }

}
