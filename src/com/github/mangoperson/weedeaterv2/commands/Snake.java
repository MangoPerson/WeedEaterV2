package com.github.mangoperson.weedeaterv2.commands;

import com.github.mangoperson.weedeaterv2.util.command.Command;
import com.github.mangoperson.weedeaterv2.util.game.Game;

public class Snake extends Command {

	public Snake() {
		super("Allows you to play the game Snake in your discord chat. Not recommended to have multiple games running at the same time");
	}

	@Override
	protected void runCommand() {
		Game game = new Game(event);
		game.start();
	}

}
