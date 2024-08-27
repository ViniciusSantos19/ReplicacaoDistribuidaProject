package com.example.demo.application.usecase;

public interface CheckIfReplicaIsAliveUseCase {
	void cleanupExpiredReplicas();
	void updateReplicaStatus(String replicaId, Long miliseconds);
}
