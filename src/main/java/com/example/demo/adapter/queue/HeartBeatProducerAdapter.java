package com.example.demo.adapter.queue;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.demo.application.port.queue.HeartBeatProducerPort;
@Component
public class HeartBeatProducerAdapter implements HeartBeatProducerPort{

	private final RabbitTemplate rabbitTemplate;
	private final String heartbeatQueueName = "leader-heartbeat-queue";
	@Value("${instance.id}")
	private  String instanceId;
	
	public HeartBeatProducerAdapter(RabbitTemplate rabbitTemplate) {
		super();
		this.rabbitTemplate = rabbitTemplate;
	}


	@Override
	public void sendHeartbeat() {
		String message = instanceId + ":heartbeat";
		rabbitTemplate.convertAndSend(heartbeatQueueName, message);
		
	}
	
}
