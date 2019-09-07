package com.github.overmighty.example.command.gui;

import com.github.overmighty.croissant.command.CommandExecutor;
import com.github.overmighty.croissant.command.CroissantCommand;
import com.github.overmighty.croissant.gui.NavigationButtonType;
import com.github.overmighty.croissant.gui.ScrollableGUI;
import com.github.overmighty.croissant.util.ItemBuilder;
import com.github.overmighty.example.Util;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import static com.github.overmighty.croissant.util.CroissantUtil.slotAt;

/**
 * A command that uses {@link ScrollableGUI}s.
 */
public class PlayersCommand extends CroissantCommand {

    private static final Sound CLICK_SOUND = Util.getSound("UI_BUTTON_CLICK", "CLICK");

    public PlayersCommand() {
        super("players");
        super.setDescription("Opens a GUI listing online players.");
        super.setPlayerOnly(true);
    }

    @CommandExecutor
    public void run(Player sender) {
        // A hardcoded page count is used for the GUI so that the navigation
        // buttons can be tested without requiring a ton of online players
        int rows = 6;
        int pageCount = 10;
        ScrollableGUI gui = new ScrollableGUI("Online players (Page {page}/10)", rows, pageCount);

        this.prepareGUI(gui, rows);
        this.addPlayerHeads(gui, rows);

        gui.openTo(sender);
    }

    private void prepareGUI(ScrollableGUI gui, int rows) {
        ItemStack arrow = new ItemBuilder(Material.ARROW)
            .setDisplayName(ChatColor.DARK_GREEN + "Page {page}")
            .build();

        // Set navigation buttons on the last inventory row
        gui.setNavigationButton(slotAt(rows - 1, 0), arrow, NavigationButtonType.FIRST_PAGE);
        gui.setNavigationButton(slotAt(rows - 1, 1), arrow, NavigationButtonType.PREVIOUS_PAGE);
        gui.setNavigationButton(slotAt(rows - 1, 7), arrow, NavigationButtonType.NEXT_PAGE);
        gui.setNavigationButton(slotAt(rows - 1, 8), arrow, NavigationButtonType.LAST_PAGE);

        gui.setScrollHandler(event -> ((Player) event.getWhoClicked()).playSound(
            event.getWhoClicked().getLocation(), CLICK_SOUND, 10, 1
        ));
    }

    private void addPlayerHeads(ScrollableGUI gui, int rows) {
        Player[] players = Bukkit.getOnlinePlayers().toArray(new Player[0]);

        for (int i = 0; i < players.length; i++) {
            Player player = players[i];
            // Find on which page of the GUI the player head should be added
            // Reminder: the last inventory row is used for navigation buttons
            int page = i / ((rows - 2) * 9);

            if (page >= gui.getPages().size()) {
                // The GUI is full
                break;
            }

            gui.getPages().get(page).getInventory().addItem(Util.getPlayerHead(player));
        }
    }

}
