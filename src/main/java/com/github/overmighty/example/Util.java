package com.github.overmighty.example;

import com.github.overmighty.croissant.util.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

/**
 * Provides miscellaneous utility methods.
 */
public class Util {

    /**
     * Finds a {@link Sound} by its name.
     *
     * @param name         the sound's name
     * @param fallbackName the fallback name to search the sound with, if it
     *                     could not be found with the other name
     * @return the sound
     */
    public static Sound getSound(String name, String fallbackName) {
        try {
            return Sound.valueOf(name);
        } catch (IllegalArgumentException e) {
            return Sound.valueOf(fallbackName);
        }
    }

    /**
     * Returns the given player's head. The item's display name will be set to
     * the player's name.
     *
     * @param player the player to get the head of
     * @return the player's head
     */
    @SuppressWarnings("deprecation")
    public static ItemStack getPlayerHead(Player player) {
        try {
            return new ItemBuilder(Material.PLAYER_HEAD)
                .setDisplayName(player.getName())
                .handleMeta(meta -> ((SkullMeta) meta).setOwningPlayer(player))
                .build();
        } catch (NoSuchFieldError e) {
            // For compatibility with versions of Bukkit before Minecraft 1.13
            return new ItemBuilder(Material.valueOf("SKULL_ITEM"))
                .setDurability((short) 3)
                .setDisplayName(player.getName())
                .handleMeta(meta -> ((SkullMeta) meta).setOwner(player.getName()))
                .build();
        }
    }

}
