package com.example.demo.application.usecase;

import com.example.demo.domain.Command;

public interface SendSqlCommandUseCase {
	void executeCommand(Command command);
}
