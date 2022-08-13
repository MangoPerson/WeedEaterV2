package com.github.mangoperson.weedeaterv2.commands;

import com.github.mangoperson.weedeaterv2.util.BotCommand;
import com.github.mangoperson.weedeaterv2.util.Comp;
import com.github.mangoperson.weedeaterv2.util.WCommand;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

@BotCommand
public class IterativeImageCommand extends WCommand {

    public IterativeImageCommand() {
        super("image", "Generates an iterative image");
        addOption(OptionType.INTEGER, "width", "Image width", true);
        addOption(OptionType.INTEGER, "height", "Image height", true);
        addOption(OptionType.INTEGER, "power", "Generator", true);
    }

    @Override
    public void run(SlashCommandInteractionEvent event) {
        BufferedImage image = new BufferedImage(event.getOption("width").getAsInt(), event.getOption("height").getAsInt(), BufferedImage.TYPE_INT_RGB);
        Random rand = new Random();

        for (int x : Comp.range(image.getWidth())) {
            for (int y : Comp.range(image.getHeight())) {
                image.setRGB(x, y, (int) (event.getOption("power").getAsInt()*Math.sin((image.getRGB(Math.max(x - 1, 0), y) + image.getRGB(x, Math.max(y - 1, 0))))) + event.getOption("power").getAsInt()/2);
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
