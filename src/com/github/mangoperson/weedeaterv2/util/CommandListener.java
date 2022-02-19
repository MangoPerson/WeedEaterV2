package com.github.mangoperson.weedeaterv2.util;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

import org.reflections.Reflections;

import com.github.mangoperson.weedeaterv2.BotInit;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandListener extends ListenerAdapter {
	
	Reflections reflections = new Reflections();
	
	Set<Class<? extends Command>> commandList;
	
	public CommandListener() {
		commandList = reflections.getSubTypesOf(Command.class);
	}
	
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		String[] args = event.getMessage().getContentRaw().split("\\s+");
		
		System.out.println(args);
		
		for(Class<? extends Command> commandClass : commandList) {
			try {
				Command command = (Command) commandClass.getConstructors()[0].newInstance((Object[])null);
				
				if(args[0] == BotInit.prefix + command.command) {
					command.run(event);
				}
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | SecurityException e) {
				e.printStackTrace();
			}
		}
	}
}
