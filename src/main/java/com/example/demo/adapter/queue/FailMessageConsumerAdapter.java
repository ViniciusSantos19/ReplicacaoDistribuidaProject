package com.example.demo.adapter.queue;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.example.demo.application.port.queue.FailMessageConsumerPort;

@Component
public class FailMessageConsumerAdapter implements FailMessageConsumerPort{

	@RabbitListener(queues = "${instance.queue.notfy}")
	@Override
	public void processarFailMessage(String message) {
		System.out.println("A réplica de id:"+message+" foi removida do grupo");
	}

}
