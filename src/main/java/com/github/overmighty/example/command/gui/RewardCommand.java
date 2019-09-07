package com.github.overmighty.example.command.gui;

import com.github.overmighty.croissant.Croissant;
import com.github.overmighty.croissant.command.CommandExecutor;
import com.github.overmighty.croissant.command.CroissantCommand;
import com.github.overmighty.croissant.gui.GUI;
import com.github.overmighty.croissant.util.CroissantUtil;
import com.github.overmighty.example.Util;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.concurrent.ThreadLocalRandom;

/**
 * A command that uses {@link GUI}s.
 */
public class RewardCommand extends CroissantCommand {

    private static final Sound SLOT_MACHINE_SOUND =
        Util.getSound("ENTITY_ARROW_HIT_PLAYER", "SUCCESSFUL_HIT");
    private static final Sound JACKPOT_SOUND =
        Util.getSound("ENTITY_FIREWORK_ROCKET_TWINKLE", "FIREWORK_TWINKLE");

    private static final ItemStack[] REWARDS = new ItemStack[] {
        new ItemStack(Material.DIAMOND_SWORD),
        new ItemStack(Material.DIAMOND_HELMET),
        new ItemStack(Material.DIAMOND_CHESTPLATE),
        new ItemStack(Material.DIAMOND_LEGGINGS),
        new ItemStack(Material.DIAMOND_BOOTS),
        new ItemStack(Material.GOLDEN_APPLE),
        new ItemStack(Material.ENDER_PEARL, 4),
        new ItemStack(Material.DIAMOND, 16),
        new ItemStack(Material.GOLD_INGOT, 16),
        new ItemStack(Material.EMERALD, 16)
    };

    public RewardCommand() {
        super("reward");
        super.setDescription("Rewards you with a random item stack.");
        super.setPlayerOnly(true);
    }

    @CommandExecutor
    public void run(Player sender) {
        GUI gui = new GUI("Claim your reward!", 3);
        gui.openTo(sender);

        int rewardSlot = CroissantUtil.slotAt(1, 4);

        new BukkitRunnable() {
            @Override
            public void run() {
                sender.playSound(sender.getLocation(), SLOT_MACHINE_SOUND, 10, 1);
                // Set the item in the reward slot to a random reward
                gui.getInventory().setItem(
                    rewardSlot,
                    REWARDS[ThreadLocalRandom.current().nextInt(REWARDS.length)]
                );

                // 1 chance out of 10
                if (ThreadLocalRandom.current().nextInt(10) == 0) {
                    this.cancel();
                    sender.playSound(sender.getLocation(), JACKPOT_SOUND, 10, 1);
                    // Let the player take the reward
                    gui.getIgnoredSlots().add(rewardSlot);
                }
            }
        }.runTaskTimer(Croissant.getPlugin(), 0, 2);
    }

}
