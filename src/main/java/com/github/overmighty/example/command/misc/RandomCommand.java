package com.github.overmighty.example.command.misc;

import com.github.overmighty.croissant.command.CommandExecutor;
import com.github.overmighty.croissant.command.CroissantCommand;
import org.bukkit.command.CommandSender;

import java.util.concurrent.ThreadLocalRandom;

/**
 * A command that uses variable arguments (varargs).
 */
public class RandomCommand extends CroissantCommand {

    public RandomCommand() {
        super("random");
        super.setDescription("Picks a random word from the given list.");
        super.setUsage("/<command> <words ...>");
    }

    @CommandExecutor
    public void run(CommandSender sender, String... words) {
        int randomIndex = ThreadLocalRandom.current().nextInt(words.length);
        sender.sendMessage(words[randomIndex]);
    }

}
