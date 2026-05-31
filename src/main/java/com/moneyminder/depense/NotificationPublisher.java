package com.moneyminder.depense;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
public class NotificationPublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * @param routingKey  RabbitMQConfig.KEY_REVENUE, KEY_DEPENSE, etc.
     * @param userId      l'utilisateur concerné
     * @param message     texte de la notification
     * @param data        données métier supplémentaires (optionnel)
     */
    public void publier(String routingKey, Long userId, String message, Map<String, Object> data) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("userId",    userId);
        payload.put("message",   message);
        payload.put("type",      routingKey);
        payload.put("timestamp", LocalDateTime.now().toString());
        payload.put("data",      data);

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE,
                routingKey,
                payload
        );
    }
}
