package com.github.mangoperson.weedeaterv2;

import com.github.mangoperson.weedeaterv2.listeners.CommandManager;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

import javax.security.auth.login.LoginException;

public class BotInit {

    private final ShardManager manager;

    public BotInit() throws LoginException {
        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(Config.get("token"));
        builder.setStatus(OnlineStatus.ONLINE);
        builder.setActivity(Activity.watching("you"));
        manager = builder.build();

        manager.addEventListener(new CommandManager());
    }

    public ShardManager getManager() {
        return manager;
    }

    public static void main(String[] args) {
        try {
            BotInit bot = new BotInit();

            ShardManager manager = bot.getManager();
        } catch(LoginException e) {
            System.out.println("ERROR: Invalid Token");
        }
    }
}
