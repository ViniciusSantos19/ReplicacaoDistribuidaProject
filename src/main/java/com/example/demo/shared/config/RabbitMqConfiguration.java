package com.example.demo.shared.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import jakarta.annotation.PostConstruct;



@Configuration
public class RabbitMqConfiguration {

	@Value("${instance.queue.heartbeat.name}")
	public static final String QUEUE1_NAME = "heart-beat-queue";
	
	@Value("${instance.queue.name}")
	private  String QUEUE2_NAME;
	
	@Value("${instance.queue.notfy}")
	private String Queue3Name;
	
	public static final String EXCHANGE_NAME1 = "my_fanout_exchange_sql_command";
	
	public static final String EXCHANGE_NAME2 = "my_fanout_exchange_notfy_fail";
	
    public static  String QUEUE2_NAME_PUBLIC;
    
    public static String QUEUE3_NAME_PUBLIC;
    
    @Bean
    @ConditionalOnProperty(name = "instance.id", havingValue = "1")
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }
    
    @PostConstruct
    public void init() {
        QUEUE2_NAME_PUBLIC = this.QUEUE2_NAME;
        QUEUE3_NAME_PUBLIC = this.Queue3Name;
    }
	
	@Bean
	public FanoutExchange myFanoutExchange()  {
		return new FanoutExchange(EXCHANGE_NAME1);
	}
	
	@Bean
	public FanoutExchange myFanoutExchangeFails()  {
		return new FanoutExchange(EXCHANGE_NAME2);
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
    public Queue failQueue() {
        return new Queue(Queue3Name, true);
    }
	
	
	@Bean
	public Binding bindToReplica() {
		return BindingBuilder.bind(replica()).to(myFanoutExchange());
	}
	
	@Bean
	public Binding bindToFailExchange() {
		return BindingBuilder.bind(failQueue()).to(myFanoutExchangeFails());
	}
	
	
}
