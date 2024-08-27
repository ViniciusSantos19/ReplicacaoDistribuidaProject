package com.example.demo.adapter.queue;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.example.demo.application.port.queue.HeartBeatProducerPort;
import com.example.demo.shared.config.RabbitMqConfiguration;
@Component
public class HeartBeatProducerAdapter implements HeartBeatProducerPort{

	private final RabbitTemplate rabbitTemplate;
	
	
	public HeartBeatProducerAdapter(RabbitTemplate rabbitTemplate) {
		super();
		this.rabbitTemplate = rabbitTemplate;
	}


	@Override
	public void sendHeartbeat(String message) {
			System.out.println("Replica " + message + " enviando heartbeat");
			rabbitTemplate.convertAndSend(RabbitMqConfiguration.QUEUE1_NAME, message);
	}
	
}
