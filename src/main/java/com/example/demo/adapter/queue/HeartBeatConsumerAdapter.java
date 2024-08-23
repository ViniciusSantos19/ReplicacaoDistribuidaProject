package com.example.demo.adapter.queue;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.example.demo.application.port.queue.HeartBeatConsumerPort;
import com.example.demo.shared.config.RabbitMqConfiguration;

@Component
public class HeartBeatConsumerAdapter implements HeartBeatConsumerPort{
	
	
	private final Map<String, Long> replicaStatus = new ConcurrentHashMap<>();

	@RabbitListener(queues = RabbitMqConfiguration.QUEUE1_NAME)
	@Override
	public void processarHeartBeat(String message) {
		String replicaId = extractReplicaId(message);
		replicaStatus.put(replicaId, System.currentTimeMillis());
	}

	private String extractReplicaId(String message) {
        try {
            String[] parts = message.split(":");
            return parts[0];
        } catch (Exception e) {
            return "unknown";
        }
    }

	@Override
	public boolean isReplicaAlive(String replicaId) {
		Long lastHeartbeat = replicaStatus.get(replicaId);
        return lastHeartbeat != null && (System.currentTimeMillis() - lastHeartbeat) < 10000;
	}

}
