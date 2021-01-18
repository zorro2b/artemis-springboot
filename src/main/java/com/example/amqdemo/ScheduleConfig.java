package com.example.amqdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Random;

@Component
@Profile("publisher")
public class ScheduleConfig {
    private final JmsTemplate jmsTemplate;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private static int sequence1 = 0;
    private static int sequence2 = 0;
    private Random random = new Random();

    public ScheduleConfig(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;

        jmsTemplate.setPubSubDomain(true);
    }

    @Scheduled(fixedRate = 5000)
    public void sender() {
        // Send a message with a POJO - the template reuses the message converter
        logger.info("Sending a message.");
        jmsTemplate.convertAndSend("topic1",
                new CustomMessage("Topic 1 message", sequence1++, false, LocalDateTime.now()));
        jmsTemplate.convertAndSend("topic2",
                new CustomMessage("Topic 2 message", sequence2++, false, LocalDateTime.now()));
    }
}
