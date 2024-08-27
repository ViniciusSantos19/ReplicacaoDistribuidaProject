package com.example.demo.adapter.queue;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.example.demo.application.port.queue.SqlCommandConsumerPort;

@Component
public class SqlCommandConsumerAdapter implements SqlCommandConsumerPort{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Value("${instance.id}")
	private String instanciaId;
	
	@Value("${instance.queue.name}")
	private String instanceQueueName;
	
	@RabbitListener(queues = "${instance.queue.name}")
	@Override
	public void processarSqlCommand(String message) {
			System.out.println(this.instanciaId);
			System.out.println(instanceQueueName);
			processarComandoSql(message);
	}
	
	
	private void processarComandoSql(String message) {
		System.out.println("O id da instância foi "+instanciaId);
		System.out.println("Received SQL Command: " + message);
		try {
			jdbcTemplate.execute(message);
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

}
