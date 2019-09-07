package com.github.overmighty.example.command.subcommand;

import com.github.overmighty.croissant.command.CommandExecutor;
import com.github.overmighty.croissant.command.CroissantCommand;
import com.github.overmighty.example.Example;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

/**
 * A subcommand that uses regular arguments.
 */
class FriendsRemoveCommand extends CroissantCommand {

    FriendsRemoveCommand() {
        super("remove");
        super.setDescription("Removes a player from your friend list.");
        super.setUsage("/friends <command> <player>");
    }

    @CommandExecutor
    public void run(Player sender, Player player) {
        List<UUID> friendList = Example.getFriendLists().get(sender.getUniqueId());

        if (friendList == null || !friendList.contains(player.getUniqueId())) {
            sender.sendMessage(ChatColor.RED + player.getName() + " is already not your friend!");
            return;
        }

        friendList.remove(player.getUniqueId());
        sender.sendMessage(ChatColor.GREEN + player.getName() + " is no longer your friend!");

        if (friendList.size() == 0) {
            Example.getFriendLists().remove(sender.getUniqueId());
        }
    }

}
