package com.example.demo.application.port.queue;

public interface SqlCommandConsumerPort {
	void processarSqlCommand(String message);
}
