package com.github.overmighty.example.command.misc;

import com.github.overmighty.croissant.command.CommandExecutor;
import com.github.overmighty.croissant.command.CroissantCommand;
import com.github.overmighty.croissant.command.argument.Rest;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * A command that uses regular arguments and {@link Rest} arguments.
 */
public class MessageCommand extends CroissantCommand {

    private static final String MESSAGE_FORMAT = ChatColor.GRAY + "(%s %s) %s";

    public MessageCommand() {
        super("message");
        super.setDescription("Sends a message to a player.");
        super.setUsage("/<command> <player> <message ...>");
        super.setAliases("msg", "m");
        super.setPlayerOnly(true);
    }

    @CommandExecutor
    public void run(Player sender, Player player, @Rest String message) {
        player.sendMessage(String.format(MESSAGE_FORMAT, "From", sender.getName(), message));
        sender.sendMessage(String.format(MESSAGE_FORMAT, "To",   player.getName(), message));
    }

}
