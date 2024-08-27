package com.example.demo.adapter.queue;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.application.port.queue.HeartBeatConsumerPort;
import com.example.demo.application.usecase.CheckIfReplicaIsAliveUseCase;
import com.example.demo.shared.config.RabbitMqConfiguration;

@Component
public class HeartBeatConsumerAdapter implements HeartBeatConsumerPort{
	
	@Autowired
	private final CheckIfReplicaIsAliveUseCase checkIfReplicaIsAliveUseCase;
	
	

	public HeartBeatConsumerAdapter(CheckIfReplicaIsAliveUseCase heartbeatScheduler) {
		super();
		this.checkIfReplicaIsAliveUseCase = heartbeatScheduler;
	}



	@RabbitListener(queues = RabbitMqConfiguration.QUEUE1_NAME)
	@Override
	public void processarHeartBeat(String message) {
		System.out.println("Testando se a replica "+ message + " esta viva");
		checkIfReplicaIsAliveUseCase.updateReplicaStatus(message, System.currentTimeMillis());
	}



}
