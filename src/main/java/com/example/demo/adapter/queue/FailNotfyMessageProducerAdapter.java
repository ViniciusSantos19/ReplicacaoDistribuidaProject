package com.example.demo.adapter.queue;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.example.demo.application.port.queue.FailsNotfyMessageProducerPort;

@Component
public class FailNotfyMessageProducerAdapter implements FailsNotfyMessageProducerPort{

	private final RabbitTemplate rabbitTemplate;
	
	private  final FanoutExchange fanoutExchange;
	
	
	
	public FailNotfyMessageProducerAdapter(RabbitTemplate rabbitTemplate, FanoutExchange myFanoutExchangeFails) {
		super();
		this.rabbitTemplate = rabbitTemplate;
		this.fanoutExchange = myFanoutExchangeFails;
	}



	@Override
	public void notfyFail(String message) {
		rabbitTemplate.convertAndSend(fanoutExchange.getName(), "", message);
	}

}
