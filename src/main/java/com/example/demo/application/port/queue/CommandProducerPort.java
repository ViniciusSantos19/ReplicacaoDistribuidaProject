package com.example.demo.application.port.queue;

public interface CommandProducerPort {
	void sendSqlCommand(String sqlCommand);
}
