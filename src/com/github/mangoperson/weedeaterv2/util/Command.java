package com.github.mangoperson.weedeaterv2.util;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public abstract class Command {
	
	public String command;
	
	public GuildMessageReceivedEvent event;
		
	public Command() {	
		command = this.getClass().getSimpleName().toLowerCase();
	}
	
	public void run(GuildMessageReceivedEvent event) {
		this.event = event;
		runCommand();
	}
	
	public abstract void runCommand();
}
