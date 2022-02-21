package com.github.mangoperson.weedeaterv2.util;

import java.io.IOException;
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
		
		System.out.println(args);
		
		for(ClassInfo commandClass : commandList) {
			try {
				Command command = (Command) commandClass.getClass().getConstructors()[0].newInstance((Object[])null);
				
				if(args[0] == BotInit.prefix + command.command) {
					command.run(event);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
