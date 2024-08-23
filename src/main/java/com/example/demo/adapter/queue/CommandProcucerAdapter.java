package com.example.demo.adapter.queue;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.example.demo.application.port.queue.CommandProducerPort;

@Component
public class CommandProcucerAdapter implements CommandProducerPort{

	private final RabbitTemplate rabbitTemplate;
    
	public CommandProcucerAdapter(RabbitTemplate rabbitTemplate) {
		super();
		this.rabbitTemplate = rabbitTemplate;
	}


	@Override
	public void sendSqlCommand(String sqlCommand) {
		rabbitTemplate.convertAndSend("sql-replication-queue", sqlCommand);
	}

}
