package com.example.demo.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.demo.application.port.queue.CommandProducerPort;
import com.example.demo.application.usecase.SendSqlCommandUseCase;
import com.example.demo.domain.Command;

@Service
public class SendSqlCommandService implements SendSqlCommandUseCase{

	@Autowired
	private CommandProducerPort commandProducerPort;

	@Value("${instance.lider}")
	private String liderId;
	
	@Value("${instance.id}")
	private String instaciaId;

	@Override
	public void executeCommand(Command command) {
		if (this.instaciaId.equals(this.liderId)) {
			commandProducerPort.sendSqlCommand(command.getCommandSql());
		}
	}

}
