package com.example.demo.shared.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class RabbitMqConfiguration {

	
	@Bean
    public Queue leaderHeartbeatQueue() {
        return new Queue("leader-heartbeat-queue", true);
    }
	
	@Bean
    public Queue sqlReplicationQueue() {
        return new Queue("sql-replication-queue", true);
    }
	
}
