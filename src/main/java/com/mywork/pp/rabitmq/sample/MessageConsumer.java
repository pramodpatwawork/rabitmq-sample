package com.mywork.pp.rabitmq.sample;

import javax.jms.ConnectionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.listener.adapter.MessageListenerAdapter;

@Configuration
public class MessageConsumer {
	
	@Autowired
	ConnectionFactory connectionFactory;
	
	@Bean
	public DefaultMessageListenerContainer jmsListener() {

		System.out.println("connectionFactory => " + connectionFactory);

		DefaultMessageListenerContainer jmsListener = new DefaultMessageListenerContainer();
		jmsListener.setConnectionFactory(connectionFactory);
		jmsListener.setDestinationName("demoqueue");
		jmsListener.setPubSubDomain(false);

		MessageListenerAdapter adapter = new MessageListenerAdapter(new Receiver());
		adapter.setDefaultListenerMethod("receive");

		jmsListener.setMessageListener(adapter);
		return jmsListener;
	}

	static class Receiver {
		public void receive(String message) {
			System.out.println("Received " + message);
		}
	}

}
