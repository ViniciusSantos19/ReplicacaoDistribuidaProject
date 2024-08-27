package com.example.demo.adapter.queue;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.example.demo.application.port.queue.CommandProducerPort;

@Component
public class CommandProcucerAdapter implements CommandProducerPort{

	private final RabbitTemplate rabbitTemplate;
	
	private  final FanoutExchange fanoutExchange;
    
	public CommandProcucerAdapter(RabbitTemplate rabbitTemplate, FanoutExchange myFanoutExchange) {
		super();
		this.rabbitTemplate = rabbitTemplate;
		this.fanoutExchange = myFanoutExchange;
	}


	@Override
	public void sendSqlCommand(String sqlCommand) {
			System.out.println("Enviando comando sql para filas");
			rabbitTemplate.convertAndSend(fanoutExchange.getName(), "", sqlCommand);
	}

}
