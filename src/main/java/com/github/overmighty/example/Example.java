package com.github.overmighty.example;

import com.github.overmighty.croissant.Croissant;
import com.github.overmighty.croissant.command.CommandHandler;
import com.github.overmighty.croissant.command.argument.ArgumentResolver;
import com.github.overmighty.croissant.command.argument.ArgumentType;
import com.github.overmighty.croissant.gui.GUIHandler;
import com.github.overmighty.example.command.gui.KitCommand;
import com.github.overmighty.example.command.gui.PlayersCommand;
import com.github.overmighty.example.command.gui.RewardCommand;
import com.github.overmighty.example.command.misc.KickCommand;
import com.github.overmighty.example.command.misc.MessageCommand;
import com.github.overmighty.example.command.misc.PingCommand;
import com.github.overmighty.example.command.misc.RandomCommand;
import com.github.overmighty.example.command.subcommand.FriendsCommand;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * The Bukkit plugin's main class.
 */
public class Example extends JavaPlugin {

    // Since this plugin is only meant to showcase Croissant, we will store
    // friend lists in memory instead of storing them in a database
    private static final Map<UUID, List<UUID>> friendLists = new HashMap<>();

    @Override
    public void onEnable() {
        // Initializing Croissant
        Croissant.setPlugin(this);

        CommandHandler commandHandler = new CommandHandler();
        GUIHandler.registerEvents(); // This is required if the plugin uses the GUI framework

        // Setting a custom invalid value error message for the built-in
        // argument type bound to the Player class
        commandHandler.getArgumentTypes().get(Player.class)
            .setErrorMessage(ChatColor.RED + "Error: No player matching \"{value}\" was found");

        // Binding a custom argument type to our Kit enum, because the built-in
        // argument type bound to the Enum class uses the name of the enum
        // constants while we have display names for kits
        commandHandler.getArgumentTypes().put(
            Kit.class,
            new ArgumentType((ArgumentResolver<Kit>) argument -> {
                for (Kit kit : Kit.values()) {
                    if (argument.getValue().equals(kit.getName())) {
                        return kit;
                    }
                }

                return null;
            }, argument -> {
                List<String> completions = new ArrayList<>();

                for (Kit kit : Kit.values()) {
                    if (StringUtil.startsWithIgnoreCase(kit.getName(), argument.getValue())) {
                        completions.add(kit.getName());
                    }
                }

                completions.sort(String.CASE_INSENSITIVE_ORDER);
                return completions;
            })
        );

        // Registering miscellaneous/basic commands
        commandHandler.registerCommand(new PingCommand());
        commandHandler.registerCommand(new RandomCommand());
        commandHandler.registerCommand(new MessageCommand());
        commandHandler.registerCommand(new KickCommand());
        // Registering commands with subcommands
        commandHandler.registerCommand(new FriendsCommand());
        // Registering commands that make use of the GUI framework
        commandHandler.registerCommand(new KitCommand());
        commandHandler.registerCommand(new RewardCommand());
        commandHandler.registerCommand(new PlayersCommand());
    }

    /**
     * Returns all friend lists, mapped by the UUID of the player whose friend
     * list it is.
     *
     * @return all friend lists
     */
    public static Map<UUID, List<UUID>> getFriendLists() {
        return friendLists;
    }

}
