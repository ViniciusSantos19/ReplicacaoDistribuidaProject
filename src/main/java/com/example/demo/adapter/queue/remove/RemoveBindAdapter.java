package com.example.demo.adapter.queue.remove;

import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import com.example.demo.application.port.queue.remove.RemoveBindPort;

@ConditionalOnProperty(name = "instance.id", havingValue = "1")
@Component
public class RemoveBindAdapter implements RemoveBindPort{

	
	@Autowired
	private RabbitAdmin rabbitAdmin;
	
	@Override
	public void unbindQueue(String queueName) {
		rabbitAdmin.deleteQueue(queueName);
	}

}
