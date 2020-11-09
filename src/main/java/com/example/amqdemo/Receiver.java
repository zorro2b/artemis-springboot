package com.example.amqdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class Receiver {
  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @JmsListener(destination = "topic1", containerFactory = "myFactory")
  public void receiveMessage(CustomMessage msg) {
    int transfer = (LocalDateTime.now().getNano() - msg.getSent().getNano())/1000;
    logger.info("Message read from topic 1 : " + msg
            + " transfer time: "
            + (transfer) + "µs");
  }

}