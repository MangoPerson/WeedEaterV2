package com.github.mangoperson.weedeaterv2.commands;

import com.github.mangoperson.weedeaterv2.util.command.Cmd;
import com.github.mangoperson.weedeaterv2.util.command.Command;

import net.dv8tion.jda.api.EmbedBuilder;

public class Info extends Command {
	
	@Cmd("info")
	public void runCommand() {
		EmbedBuilder embed = new EmbedBuilder();

		event.getChannel().sendMessage(embed.build()).queue();
	}	
}
