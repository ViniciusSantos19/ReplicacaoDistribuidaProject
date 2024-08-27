package com.example.demo.shared.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.example.demo.application.service.CheckIfReplicaIsAliveService;
import com.example.demo.application.usecase.CheckIfReplicaIsAliveUseCase;

@Configuration
@EnableScheduling
public class JobConfig {

		@Bean
	    @ConditionalOnProperty(name = "instance.id", havingValue = "1")
	    public CheckIfReplicaIsAliveUseCase heartbeatScheduler() {
	        return new CheckIfReplicaIsAliveService();
	    }
	
}
