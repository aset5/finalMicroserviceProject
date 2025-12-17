package com.example.orderservice.service;
import com.example.orderservice.dto.OrderDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OrderPublisher {

    private static final Logger logger = LoggerFactory.getLogger(OrderPublisher.class);

    private final RabbitTemplate rabbitTemplate;

    @Value("${app.rabbitmq.exchange}")
    private String exchangeName;

    public OrderPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendOrder(OrderDTO order) {
        try {
            String routingKey = "order." + order.getRegion().toLowerCase();
            rabbitTemplate.convertAndSend(exchangeName, routingKey, order);
            logger.info("Order sent successfully to region: {} with routing key: {}", order.getRegion(), routingKey);
            logger.info("Order details: {}", order);
        } catch (Exception e) {
            logger.error("Failed to send order to RabbitMQ: {}", e.getMessage());
            throw new RuntimeException("Failed to send order", e);
        }
    }
}