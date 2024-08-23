package com.example.demo.adapter.queue;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.example.demo.application.port.queue.SqlCommandConsumerPort;
import com.example.demo.shared.config.RabbitMqConfiguration;

@Component
public class SqlCommandConsumerAdapter implements SqlCommandConsumerPort{

	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@RabbitListener(queues = RabbitMqConfiguration.QUEUE2_NAME)
	@Override
	public void processarSqlCommand(String message) {
		// TODO Auto-generated method stub
		System.out.println("Received SQL Command: " + message);
		try {
			jdbcTemplate.execute(message);
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

}
