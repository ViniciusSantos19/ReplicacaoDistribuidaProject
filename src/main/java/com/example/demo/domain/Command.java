package com.example.demo.domain;

public class Command {
	private String commandSql;
	
	public Command(String commandSql) {
		this.commandSql = commandSql;
	}

	public String getCommandSql() {
		return commandSql;
	}

	public void setCommandSql(String commandSql) {
		this.commandSql = commandSql;
	}
	
	
}
