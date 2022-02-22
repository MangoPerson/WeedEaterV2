package com.github.mangoperson.weedeaterv2.util.command;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;

import com.github.mangoperson.weedeaterv2.BotInit;
import com.google.common.reflect.ClassPath;
import com.google.common.reflect.ClassPath.ClassInfo;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandListener extends ListenerAdapter {
	
	Set<ClassInfo> commandList;
		
	public CommandListener() throws IOException {
		commandList = ClassPath.from(this.getClass().getClassLoader()).getTopLevelClasses("com.github.mangoperson.weedeaterv2.commands");
	}
	
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		String[] args = event.getMessage().getContentRaw().split("\\s+");
		
		for(ClassInfo commandClass : commandList) {
			
			if ((BotInit.prefix + commandClass.getSimpleName().toLowerCase()).equalsIgnoreCase(args[0])) {
				try {
					((Command) commandClass.load().getConstructors()[0].newInstance()).run(event);
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException| InvocationTargetException | SecurityException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
