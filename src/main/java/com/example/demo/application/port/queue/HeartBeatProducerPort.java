package com.example.demo.application.port.queue;

public interface HeartBeatProducerPort {
	void sendHeartbeat(String message);
}
