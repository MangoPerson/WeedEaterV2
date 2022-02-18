package com.github.mangoperson.weedeaterv2;

import java.io.IOException;

import javax.security.auth.login.LoginException;

import com.github.mangoperson.weedeaterv2.util.ConfigFile;

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
			System.out.println(token);
			System.out.println("NzcwMzA3MTUwMzExNDU2Nzcw.X5bqcA.lf1W1PkhoNeF1FSgS2mHxw_4N3Y");
			startBot();
		} catch (LoginException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void startBot() throws LoginException, IOException {
		jda = JDABuilder.createDefault(token).build();
		jda.getPresence().setStatus(OnlineStatus.ONLINE);
		jda.getPresence().setActivity(Activity.listening(prefix + "info"));
	}
}
