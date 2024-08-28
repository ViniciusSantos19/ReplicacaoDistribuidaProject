package com.example.demo.adapter.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.application.usecase.SendSqlCommandUseCase;
import com.example.demo.domain.Command;

@RestController

@RequestMapping("/command")
public class SqlCommandController {

	
	@Autowired
	private SendSqlCommandUseCase sendSqlCommandUseCase;
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public void enviarComando(@RequestBody CommandDto comando) {
		var command = new Command(comando.command());
		sendSqlCommandUseCase.executeCommand(command);
	}
	
}
