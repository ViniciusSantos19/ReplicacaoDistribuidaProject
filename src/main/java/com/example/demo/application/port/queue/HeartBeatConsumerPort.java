package com.example.demo.application.port.queue;

public interface HeartBeatConsumerPort {
	void processarHeartBeat(String message);
	boolean isReplicaAlive(String replicaId);
}
