package com.github.overmighty.example.command.misc;

import com.github.overmighty.croissant.command.CommandExecutor;
import com.github.overmighty.croissant.command.CroissantCommand;
import org.bukkit.command.CommandSender;

/**
 * A basic command that takes no arguments.
 */
public class PingCommand extends CroissantCommand {

    public PingCommand() {
        super("ping");
        super.setDescription("Pong!");
    }

    @CommandExecutor
    public void run(CommandSender sender) {
        sender.sendMessage("Pong!");
    }

}
