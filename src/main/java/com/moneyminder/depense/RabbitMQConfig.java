package com.moneyminder.depense;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    // ── Exchange unique pour toutes les notifications ──
    public static final String EXCHANGE = "notification.exchange";

    // ── Routing keys — une par événement ──
    public static final String KEY_REVENUE    = "notification.revenue.ajoute";
    public static final String KEY_DEPENSE    = "notification.depense.ajoutee";
    public static final String KEY_OBJECTIF   = "notification.objectif.atteint";
    public static final String KEY_RECLAMATION= "notification.reclamation.creee";
    public static final String KEY_POST       = "notification.post.cree";

    @Bean
    public TopicExchange notificationExchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        return template;
    }
}