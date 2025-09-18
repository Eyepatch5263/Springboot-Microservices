package com.eyepatch.works.orderservice.config;

import com.eyepatch.works.orderservice.ApplicationProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    private final ApplicationProperties applicationProperties;
    public RabbitMQConfig(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }


    // Defining exchanges

    @Bean
    DirectExchange exchange(){
        return new DirectExchange(applicationProperties.orderEventsExchange());
    }

    // Defining queues
    @Bean
    Queue newOrderQueue(){
        return QueueBuilder.durable(applicationProperties.newOrdersQueue()).build();
    }

    @Bean
    Binding newOrdersQueueBinding(){
        return BindingBuilder.bind(newOrderQueue())
                .to(exchange())
                .with(applicationProperties.newOrdersQueue()); //routing key same as queue name
    }
    @Bean
    Queue deliveredOrdersQueue(){
        return QueueBuilder.durable(applicationProperties.deliveredOrdersQueue()).build();
    }

    @Bean
    Binding deliveredOrdersQueueBinding(){
        return BindingBuilder.bind(deliveredOrdersQueue())
                .to(exchange())
                .with(applicationProperties.deliveredOrdersQueue()); //routing key same as queue name
    }


    @Bean
    Queue cancelledOrdersQueue(){
        return QueueBuilder.durable(applicationProperties.cancelledOrdersQueue()).build();
    }

    @Bean
    Binding cancelledOrdersQueueBinding(){
        return BindingBuilder.bind(cancelledOrdersQueue())
                .to(exchange())
                .with(applicationProperties.cancelledOrdersQueue()); //routing key same as queue name
    }


    @Bean
    Queue errorOrdersQueue(){
        return QueueBuilder.durable(applicationProperties.errorOrdersQueue()).build();
    }

    @Bean
    Binding errorOrdersQueueBinding(){
        return BindingBuilder.bind(errorOrdersQueue())
                .to(exchange())
                .with(applicationProperties.errorOrdersQueue()); //routing key same as queue name
    }

    // --- RabbitAdmin (forces declaration) ---
    // Important !!! else the RabbitMQ server won't configure this file onto the RabbitMQ server
    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        rabbitAdmin.setAutoStartup(true); // make sure it runs at startup
        return rabbitAdmin;
    }

    @Bean
    public InitializingBean forceRabbitAdmin(RabbitAdmin rabbitAdmin) {
        return rabbitAdmin::initialize;
    }


    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, ObjectMapper objectMapper){
        final var rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jacksonConverter(objectMapper));
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter jacksonConverter(ObjectMapper objectMapper){
        return new Jackson2JsonMessageConverter(objectMapper);
    }
}
