package com.github.mangoperson.weedeaterv2.commands;

import com.github.mangoperson.weedeaterv2.util.BotCommand;
import com.github.mangoperson.weedeaterv2.util.Comp;
import com.github.mangoperson.weedeaterv2.util.WCommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

@BotCommand
public class RandomImageCommand extends WCommand {

    public RandomImageCommand() {
        super("randomimage", "Generates an image");
        addOption(OptionType.INTEGER, "width", "Image width", true);
        addOption(OptionType.INTEGER, "height", "Image height", true);
    }

    public void run(SlashCommandInteractionEvent event) {
        BufferedImage image = new BufferedImage(event.getOption("width").getAsInt(), event.getOption("height").getAsInt(), BufferedImage.TYPE_INT_RGB);
        Random rand = new Random();

        for (int x : Comp.range(image.getWidth())) {
            for (int y : Comp.range(image.getHeight())) {
                image.setRGB(x, y, rgb(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255)));
            }
        }

        try {
            ImageIO.write(image, "PNG", new File("image.png"));
        } catch (IOException ignored) {}

        event.reply("Here's your image, " + event.getUser().getAsMention()).addFile(new File("image.png")).queue();
    }

    private static int rgb(int r, int g, int b) {
        return new Color(r, g, b).getRGB();
    }
}
