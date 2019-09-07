package com.github.overmighty.example.command.subcommand;

import com.github.overmighty.croissant.command.CommandExecutor;
import com.github.overmighty.croissant.command.CroissantCommand;
import com.github.overmighty.example.Example;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

/**
 * A subcommand that takes no arguments.
 */
class FriendsListCommand extends CroissantCommand {

    FriendsListCommand() {
        super("list");
        super.setDescription("Lists your friends.");
        super.setUsage("/friends <command>");
    }

    @CommandExecutor
    public void run(Player sender) {
        List<UUID> friendList = Example.getFriendLists().get(sender.getUniqueId());

        if (friendList == null) {
            sender.sendMessage(ChatColor.RED + "You have no friends!");
            return;
        }

        sender.sendMessage(ChatColor.GOLD + "Friends (" + friendList.size() + "):");

        for (UUID friendId : friendList) {
            OfflinePlayer friend = Bukkit.getOfflinePlayer(friendId);
            sender.sendMessage(ChatColor.YELLOW + friend.getName());
        }
    }

}
