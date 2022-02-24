package com.github.mangoperson.weedeaterv2.commands;

import java.lang.reflect.InvocationTargetException;

import com.github.mangoperson.weedeaterv2.BotInit;
import com.github.mangoperson.weedeaterv2.util.command.Command;
import com.github.mangoperson.weedeaterv2.util.command.CommandListener;
import com.google.common.reflect.ClassPath.ClassInfo;

import net.dv8tion.jda.api.EmbedBuilder;

public class Info extends Command {
	
	public Info() {
		super("Gets this list");
	}

	@Override
	public void runCommand() {
		EmbedBuilder embed = new EmbedBuilder();
		
		embed.setTitle("Commands");
		embed.setColor(0xb00b69);
		
		for (ClassInfo cmdClass : CommandListener.commandList) {
			try {
				embed.addField(
						BotInit.prefix + cmdClass.getSimpleName().toLowerCase(),
						((Command)cmdClass.load().getConstructors()[0].newInstance()).description,
						false);
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException| InvocationTargetException | SecurityException e) {
				e.printStackTrace();
			}
		}
		
		event.getChannel().sendMessage(embed.build()).queue();
	}	
}
