package com.example.demo.application.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.example.demo.application.port.queue.FailsNotfyMessageProducerPort;
import com.example.demo.application.usecase.CheckIfReplicaIsAliveUseCase;

public class CheckIfReplicaIsAliveService implements CheckIfReplicaIsAliveUseCase{

	private final Map<String, Long> replicaStatus = new ConcurrentHashMap<>();
	
	@Autowired
	private FailsNotfyMessageProducerPort failsNotfyMessageProducerPort;
	
	@Override
	public void updateReplicaStatus(String replicaId, Long miliseconds) {
		replicaStatus.put(replicaId, System.currentTimeMillis());
	}



	@Scheduled(fixedRate = 50000)
	@Override
	public void cleanupExpiredReplicas() {
		 long currentTime = System.currentTimeMillis();
	        replicaStatus.entrySet().removeIf(entry -> {
	        	if ((currentTime - entry.getValue()) > 60000) {
	        		failsNotfyMessageProducerPort.notfyFail(entry.getKey());
	                return true;
	            }
	        	return false;
	        });
	}
}
