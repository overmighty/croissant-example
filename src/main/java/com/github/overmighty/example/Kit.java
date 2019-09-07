package com.github.overmighty.example;

import com.github.overmighty.croissant.util.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

/**
 * A kit of in-game items and armor.
 */
public enum Kit {

    WARRIOR(
        "Warrior",
        new ItemStack[] { new ItemStack(Material.DIAMOND_SWORD) },
        new ItemStack[] {
            new ItemStack(Material.IRON_BOOTS),
            new ItemStack(Material.IRON_LEGGINGS),
            new ItemStack(Material.IRON_CHESTPLATE),
            new ItemStack(Material.IRON_HELMET),
        }
    ),
    ARCHER(
        "Archer",
        new ItemStack[] {
            new ItemBuilder(Material.BOW)
                .addEnchantment(Enchantment.ARROW_INFINITE, 1)
                .build(),
            new ItemStack(Material.ARROW)
        },
        new ItemStack[] {
            new ItemStack(Material.LEATHER_BOOTS),
            new ItemStack(Material.LEATHER_LEGGINGS),
            new ItemStack(Material.LEATHER_CHESTPLATE),
            new ItemStack(Material.LEATHER_HELMET),
        }
    ),
    TANK(
        "Tank",
        new ItemStack[] { new ItemStack(Material.STONE_AXE) },
        new ItemStack[] {
            new ItemStack(Material.DIAMOND_BOOTS),
            new ItemStack(Material.DIAMOND_LEGGINGS),
            new ItemStack(Material.DIAMOND_CHESTPLATE),
            new ItemStack(Material.DIAMOND_HELMET),
        }
    );

    private final String name;
    private final ItemStack[] items;
    private final ItemStack[] armor;

    Kit(String name, ItemStack[] items, ItemStack[] armor) {
        this.name = name;
        this.items = items;
        this.armor = armor;
    }

    /**
     * Returns the kit's name.
     *
     * @return the kit's name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the items included in the kit.
     *
     * @return the kit's items
     */
    public ItemStack[] getItems() {
        return items;
    }

    /**
     * Returns the armor included in the kit.
     *
     * @return the kit's armor
     */
    public ItemStack[] getArmor() {
        return armor;
    }

}
