package com.github.overmighty.example.command.gui;

import com.github.overmighty.croissant.command.CommandExecutor;
import com.github.overmighty.croissant.command.CroissantCommand;
import com.github.overmighty.croissant.command.argument.Optional;
import com.github.overmighty.croissant.gui.GUI;
import com.github.overmighty.croissant.util.ItemBuilder;
import com.github.overmighty.example.Kit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

/**
 * A command that uses {@link GUI}s and {@link Optional} arguments.
 */
public class KitCommand extends CroissantCommand {

    private final GUI kitSelectorGui = new GUI("Select a kit", 1);

    public KitCommand() {
        super("kit");
        super.setDescription("Opens the kit selector GUI or loads the given kit.");
        super.setUsage("/<command> [kit]");
        super.setPlayerOnly(true);

        // Add a button for each kit to the GUI
        for (Kit kit : Kit.values()) {
            ItemStack kitItem = new ItemBuilder(kit.getItems()[0].getType())
                .setDisplayName(kit.getName())
                .build();

            this.kitSelectorGui.addButton(kitItem, event -> {
                this.loadKit(kit, event.getWhoClicked().getInventory());
                event.getWhoClicked().closeInventory();
            });
        }
    }

    @CommandExecutor
    public void run(Player sender, @Optional Kit kit) {
        if (kit == null) {
            this.kitSelectorGui.openTo(sender);
        } else {
            this.loadKit(kit, sender.getInventory());
        }
    }

    private void loadKit(Kit kit, PlayerInventory inventory) {
        inventory.clear();
        inventory.addItem(kit.getItems());
        inventory.setArmorContents(kit.getArmor());
    }

}
