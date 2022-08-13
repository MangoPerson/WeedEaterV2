package com.github.mangoperson.weedeaterv2.util;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

public abstract class WCommand extends ListenerAdapter {

    private SlashCommandData data;

    public WCommand(String name, String description) {
        data = Commands.slash(name, description);
    }

    protected void addOption(OptionType type, String name, String description, boolean required) {
        data = data.addOption(type, name, description, required);
    }

    protected void addOption(OptionType type, String name, String description) {
        addOption(type, name, description, false);
    }

    public SlashCommandData getData() {
        return data;
    }

    public abstract void run(SlashCommandInteractionEvent event);
}
