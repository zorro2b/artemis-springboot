package com.example.amqdemo;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.Scheduled;

import javax.jms.ConnectionFactory;
import java.time.LocalDateTime;
import java.util.Random;

@SpringBootApplication
@EnableJms
@EnableScheduling
public class AmqdemoApplication {
	private final ConfigurableApplicationContext context;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private static int sequence1 = 0;
	private static int sequence2 = 0;
	private Random random = new Random();

	public AmqdemoApplication(ConfigurableApplicationContext context) {
		this.context = context;
	}

	public static void main(String[] args) {
		SpringApplication.run(AmqdemoApplication.class, args);
	}

	@Bean
	public JmsListenerContainerFactory<?> myFactory(CachingConnectionFactory connectionFactory,
													DefaultJmsListenerContainerFactoryConfigurer configurer) {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		// This provides all boot's default to this factory, including the message converter
		configurer.configure(factory, connectionFactory);
		// You could still override some of Boot's default if necessary.
		return factory;
	}

	@Bean // Serialize message content to json using TextMessage
	public MessageConverter jacksonJmsMessageConverter() {
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		converter.setTargetType(MessageType.TEXT);
		converter.setTypeIdPropertyName("_type");
		return converter;
	}

	@Scheduled(fixedRate = 5000)
	public void sender() {
		JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);

		// Send a message with a POJO - the template reuse the message converter
		System.out.println("Sending a message.");
		jmsTemplate.convertAndSend("topic1",
				new CustomMessage("Topic 1 message", sequence1++, false));
//				new CustomMessage("Topic 1 message", sequence1++, false, LocalDateTime.now()));
	}
}
