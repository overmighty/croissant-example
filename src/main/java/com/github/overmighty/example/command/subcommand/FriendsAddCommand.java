package com.github.overmighty.example.command.subcommand;

import com.github.overmighty.croissant.command.CommandExecutor;
import com.github.overmighty.croissant.command.CroissantCommand;
import com.github.overmighty.example.Example;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * A subcommand that uses regular arguments.
 */
class FriendsAddCommand extends CroissantCommand {

    FriendsAddCommand() {
        super("add");
        super.setDescription("Adds a player to your friend list.");
        super.setUsage("/friends <command> <player>");
    }

    @CommandExecutor
    public void run(Player sender, Player player) {
        List<UUID> friendList = Example.getFriendLists()
            .computeIfAbsent(sender.getUniqueId(), key -> new ArrayList<>());

        if (friendList.contains(player.getUniqueId())) {
            sender.sendMessage(ChatColor.RED + player.getName() + " is already your friend!");
            return;
        }

        // We do not check if the player is trying to add themselves to their
        // own friend list, so that this command can be tested even when there
        // is only one online player
        friendList.add(player.getUniqueId());
        sender.sendMessage(ChatColor.GREEN + player.getName() + " is now your friend!");
    }

}
