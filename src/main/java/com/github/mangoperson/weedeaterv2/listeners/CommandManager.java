package com.github.mangoperson.weedeaterv2.listeners;

import com.github.mangoperson.weedeaterv2.util.BotCommand;
import com.github.mangoperson.weedeaterv2.util.WCommand;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import org.jetbrains.annotations.NotNull;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class CommandManager extends ListenerAdapter {
    private final Map<CommandData, Consumer<SlashCommandInteractionEvent>> commands;
    public CommandManager() {
        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forJavaClassPath())
                .setScanners(Scanners.TypesAnnotated));

        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(BotCommand.class);

         commands = classes.stream()
                 .filter(c -> c.getSuperclass().equals(WCommand.class))
                 .map(c -> {
                     try {
                         return (WCommand) c.getConstructors()[0].newInstance();
                     } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                         return null;
                     }
                 })
                 .filter(Objects::nonNull)
                 .collect(Collectors.toMap(WCommand::getData, cmd -> cmd::run));
    }

    private void registerCommands(Guild guild) {
        guild.updateCommands().addCommands(commands.keySet()).queue();
    }

    @Override
    public void onGuildReady(@NotNull GuildReadyEvent event) {
        registerCommands(event.getGuild());
    }

    @Override
    public void onGuildJoin(@NotNull GuildJoinEvent event) {
        System.out.println(event.getGuild().getName());
        registerCommands(event.getGuild());
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        commands.forEach((k, v) -> {
            if (k.getName().equalsIgnoreCase(event.getName())) {
                v.accept(event);
            }
        });
    }

    private static class Pair<A, B> {
        A a;
        B b;
        public Pair(A a, B b) {
            this.a = a;
            this.b = b;
        }
    }
}
