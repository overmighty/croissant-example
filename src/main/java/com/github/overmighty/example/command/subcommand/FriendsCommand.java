package com.github.overmighty.example.command.subcommand;

import com.github.overmighty.croissant.command.CommandExecutor;
import com.github.overmighty.croissant.command.CroissantCommand;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * A command with subcommands.
 */
public class FriendsCommand extends CroissantCommand {

    public FriendsCommand() {
        super("friends");
        super.setDescription("Lets you manage your friend list.");
        super.setAliases("friend", "f");
        super.setPlayerOnly(true);

        super.addSubcommand(new FriendsAddCommand());
        super.addSubcommand(new FriendsListCommand());
        super.addSubcommand(new FriendsRemoveCommand());
    }

    @CommandExecutor
    public void run(Player sender) {
        sender.sendMessage(ChatColor.YELLOW + "Friend commands:");

        for (CroissantCommand subcommand : super.getSubcommands().values()) {
            sender.sendMessage(ChatColor.GOLD + subcommand.getUsage(subcommand.getName()) + ": " +
                ChatColor.RESET + subcommand.getDescription());
        }
    }

}
