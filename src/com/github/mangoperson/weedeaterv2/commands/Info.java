package com.github.mangoperson.weedeaterv2.commands;

import com.github.mangoperson.weedeaterv2.util.Command;

public class Info extends Command {

	@Override
	public void runCommand() {
		event.getChannel().sendMessage("Hello").queue();
	}
	
}
