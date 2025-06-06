package com.aditya.notificationservice.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducer {

    //This is used to send message to rabbitmq broker
    /*
    rabbitmq.queue.name=notification_queue
    rabbitmq.exchange.name=notication_exchange
    rabbitmq.routing.key=notification_routing_key
*/
    @Value("${rabbitmq.exchange.name}")
    private String exchange;
    @Value("${rabbitmq.routing.key}")
    private String routingKey;


    private RabbitTemplate rabbitTemplate;


    public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

  //  private static final Logger LOGGER= LoggerFactory.getLogger(RabbitMQProducer.class);
    public void sendMessage(String message)
    {
   // LOGGER.info(String.format("Message Sent --> %s", message));
    rabbitTemplate.convertAndSend(exchange,routingKey,message);
    }
}
