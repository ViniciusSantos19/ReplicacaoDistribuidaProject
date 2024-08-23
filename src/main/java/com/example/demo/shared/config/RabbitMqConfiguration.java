package com.example.demo.shared.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class RabbitMqConfiguration {

	
	public static final String QUEUE1_NAME = "leader-heartbeat-queue";
	public static final String QUEUE2_NAME = "sql-replication-queue";
	
	@Bean
    public Queue leaderHeartbeatQueue() {
        return new Queue(QUEUE1_NAME, true);
    }
	
	@Bean
    public Queue sqlReplicationQueue() {
        return new Queue(QUEUE2_NAME, true);
    }
	
}
