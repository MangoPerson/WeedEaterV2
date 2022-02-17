package com.github.mangoperson.weedeaterv2;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

public class BotInit {
	
	public static JDA jda;
	
	public static String prefix = "w!";
	
	public static void main(String[] args) {
		try {
			startBot();
		} catch (LoginException e) {
			e.printStackTrace();
		}
	}
	
	public static void startBot() throws LoginException {
		jda = JDABuilder.createDefault("TOKEN").build();
		jda.getPresence().setStatus(OnlineStatus.ONLINE);
		jda.getPresence().setActivity(Activity.listening(prefix + "info"));
	}
}
