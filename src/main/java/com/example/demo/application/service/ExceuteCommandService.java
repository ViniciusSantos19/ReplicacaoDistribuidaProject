package com.example.demo.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.application.port.queue.CommandProducerPort;
import com.example.demo.application.usecase.ExecuteCommandUseCase;
import com.example.demo.domain.Command;

@Service
public class ExceuteCommandService implements ExecuteCommandUseCase{

	@Autowired
	private CommandProducerPort commandProducerPort;

	@Override
	public void executeCommand(Command command) {
		commandProducerPort.sendSqlCommand(command.getCommandSql());
	}

	

}
