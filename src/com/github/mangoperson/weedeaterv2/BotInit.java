package com.github.mangoperson.weedeaterv2;

import java.io.IOException;

import javax.security.auth.login.LoginException;

import com.github.mangoperson.weedeaterv2.util.ConfigFile;
import com.github.mangoperson.weedeaterv2.util.command.CommandListener;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

public class BotInit {
	
	public static JDA jda;
	
	public static ConfigFile cfg;
	
	public static String prefix;
	
	public static String token;
	
	public static void main(String[] args) {
		try {
			cfg = new ConfigFile("config.txt");
			prefix = cfg.valueOf("prefix");
			token = cfg.valueOf("token");
			startBot();
		} catch (LoginException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void startBot() throws LoginException, IOException {
		jda = JDABuilder.createDefault(token).build();
		jda.getPresence().setStatus(OnlineStatus.ONLINE);
		jda.getPresence().setActivity(Activity.listening(prefix + "info"));
		
		jda.addEventListener(new CommandListener());
	}
}
