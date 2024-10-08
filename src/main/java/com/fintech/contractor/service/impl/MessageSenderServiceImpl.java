package com.fintech.contractor.service.impl;

import com.fintech.contractor.service.MessageSenderService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

/**
 * An implementation of {@link MessageSenderService} interface
 * @author Matushkin Anton
 */
@Service
@RequiredArgsConstructor
public class MessageSenderServiceImpl implements MessageSenderService {

    @NonNull
    private AmqpTemplate amqpTemplate;

    @Override
    public void send(String exchange, String routingKey, String content) {
        amqpTemplate.convertAndSend(exchange, routingKey, content, message -> {
            message.getMessageProperties().setHeader("timestamp", System.currentTimeMillis());
            return message;
        });
    }

}
