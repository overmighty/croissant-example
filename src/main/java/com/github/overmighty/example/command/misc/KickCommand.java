package com.github.overmighty.example.command.misc;

import com.github.overmighty.croissant.command.CommandExecutor;
import com.github.overmighty.croissant.command.CroissantCommand;
import com.github.overmighty.croissant.command.argument.Default;
import com.github.overmighty.croissant.command.argument.Rest;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * A command that uses regular arguments, {@link Rest} arguments and
 * {@link Default} arguments.
 */
public class KickCommand extends CroissantCommand {

    public KickCommand() {
        super("kick");
        super.setDescription("Kicks a player out of the server.");
        super.setUsage("/<command> <player> [reason ...]");
        super.setPermission("example.kick");
    }

    @CommandExecutor
    public void run(CommandSender sender, Player player, @Default("Unknown") @Rest String reason) {
        player.kickPlayer(ChatColor.RED + "You were kicked out of the server!\n" +
            ChatColor.GRAY + "Reason: " + ChatColor.RESET + reason);
        Bukkit.broadcastMessage(ChatColor.RED + sender.getName() + " kicked " + player.getName());
    }

}
