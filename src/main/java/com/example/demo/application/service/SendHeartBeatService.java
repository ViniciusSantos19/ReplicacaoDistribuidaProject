package com.example.demo.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.demo.application.port.queue.HeartBeatProducerPort;
import com.example.demo.application.usecase.SendHeartBeatUseCase;

@Service
public class SendHeartBeatService implements SendHeartBeatUseCase{

	@Autowired
	private HeartBeatProducerPort beatProducerPort;
	
	@Value("${instance.id}")
	private String instaciaId;
	
	@Scheduled(fixedRate = 30000)
	@Override
	public void sendHeartbeat() {
			beatProducerPort.sendHeartbeat(instaciaId);
	}

}
