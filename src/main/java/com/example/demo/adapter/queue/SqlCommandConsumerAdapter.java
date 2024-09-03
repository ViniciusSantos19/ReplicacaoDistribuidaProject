package com.example.demo.adapter.queue;

import java.util.List;
import java.util.Map;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.example.demo.application.port.queue.SqlCommandConsumerPort;

@Component
public class SqlCommandConsumerAdapter implements SqlCommandConsumerPort{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@RabbitListener(queues = "${instance.queue.name}")
	@Override
	public void processarSqlCommand(String message) {
			processarComandoSql(message);
	}
	
	private void processarComandoSql(String message) {
		System.out.println("Received SQL Command: " + message);
		try {
			if (message.trim().toUpperCase().startsWith("SELECT")) {
				List<Map<String, Object>> results = jdbcTemplate.queryForList(message);

	            // Print the results
	            for (Map<String, Object> row : results) {
	                System.out.println(row);
	            }
	            return;
			}
			
			jdbcTemplate.execute(message);
		} catch (Exception e) {
			e.printStackTrace();;
		}
	}

}
