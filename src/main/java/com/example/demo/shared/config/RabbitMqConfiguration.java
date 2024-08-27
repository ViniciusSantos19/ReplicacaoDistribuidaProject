package com.example.demo.shared.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;



@Configuration
public class RabbitMqConfiguration {

	@Value("${instance.queue.heartbeat.name}")
	public static final String QUEUE1_NAME = "heart-beat-queue";
	
	@Value("${instance.queue.name}")
	private  String QUEUE2_NAME;
	
	public static final String EXCHANGE_NAME1 = "my_fanout_exchange_sql_command";
	
    public static  String QUEUE2_NAME_PUBLIC;
    
    @PostConstruct
    public void init() {
        QUEUE2_NAME_PUBLIC = this.QUEUE2_NAME;
    }
	
	@Bean
	public FanoutExchange myFanoutExchange()  {
		return new FanoutExchange(EXCHANGE_NAME1);
	}
	
	
	@Bean
    public Queue leaderHeartbeatQueue() {
        return new Queue(QUEUE1_NAME, true);
    }
	
	@Bean
    public Queue replica() {
        return new Queue(QUEUE2_NAME, true);
    }
	
	
	@Bean
	public Binding bindToReplica() {
		return BindingBuilder.bind(replica()).to(myFanoutExchange());
	}
	
	
	
}
