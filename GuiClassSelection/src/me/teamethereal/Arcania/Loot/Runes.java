package me.teamethereal.Arcania.Loot;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Objects;

public class Runes implements Listener {

    public ItemStack getLeapRune()  {
        ItemStack leap = new ItemStack(Material.HEART_OF_THE_SEA, 1);
        ItemMeta leapMeta = leap.getItemMeta();
        leapMeta.setLocalizedName("Leap");
        leapMeta.setDisplayName(ChatColor.BLUE + "Leap" + ChatColor.WHITE + " Spell");
        leapMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        leapMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        leapMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        ArrayList<String> LeapLore = new ArrayList<>();
        LeapLore.add(ChatColor.BLUE + "Movement Rune");
        LeapLore.add(" ");
        LeapLore.add(ChatColor.WHITE + "Launches the player up and");
        LeapLore.add(ChatColor.WHITE + "over the direction they're looking");
        LeapLore.add(" ");
        LeapLore.add(ChatColor.WHITE + "Rarity: " + ChatColor.GRAY + "Common");
        leapMeta.setLore(LeapLore);
        leap.setItemMeta(leapMeta);

        return leap;
    }

    public void onInventoryClick(InventoryClickEvent e) {
        if (Objects.requireNonNull(Objects.requireNonNull(e.getCursor()).getItemMeta()).getLocalizedName().equals("Leap"))  {

        }
    }

}
