package com.example.amqdemo;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

  @JmsListener(destination = "topic1", containerFactory = "myFactory")
  public void receiveMessage(CustomMessage msg) {
    System.out.println("Received <" + msg + ">");
  }

}