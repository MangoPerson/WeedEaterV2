package com.github.mangoperson.weedeaterv2.util.command;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public abstract class Command {
		
	protected GuildMessageReceivedEvent event;
	
	protected String[] args;
	
	public void run(GuildMessageReceivedEvent event) {
		this.event = event;
		runCommand();
	}
	
	public GuildMessageReceivedEvent getEvent() {
		return event;
	}
	
	public Guild getGuild() {
		return event.getGuild();
	}
	
	public Member getMember() {
		return event.getMember();
	}
	
	public User getAuthor() {
		return event.getAuthor();
	}
	
	public Message getMessage() {
		return event.getMessage();
	}
	
	public TextChannel getChannel() {
		return event.getChannel();
	}
	
	protected abstract void runCommand();
}
