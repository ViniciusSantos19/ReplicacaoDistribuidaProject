package com.example.demo.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.application.port.queue.HeartBeatConsumerPort;
import com.example.demo.application.usecase.ProcessarHeartBeatUseCase;
import com.example.demo.application.usecase.ProcessarSqlCommandUseCase;
@Service
public class ProcessCommandService implements ProcessarHeartBeatUseCase, ProcessarSqlCommandUseCase{

	@Autowired
	private HeartBeatConsumerPort heartBeatConsumerPort;
	
	@Override
	public void processarSqlCommad(String command) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void processHeartbeat(String message) {
		// TODO Auto-generated method stub
		
	}

}
