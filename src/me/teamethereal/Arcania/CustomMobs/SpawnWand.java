package me.teamethereal.Arcania.CustomMobs;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class SpawnWand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player)   {
            Player p = (Player) sender;
            if (args[0].equals("Spider"))   {

                ItemStack spawnWand = new ItemStack(Material.STICK, 1);
                ItemMeta spawnWandItemMeta = spawnWand.getItemMeta();
                spawnWandItemMeta.setDisplayName(ChatColor.DARK_PURPLE + "Spawn Wand");
                spawnWandItemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                spawnWandItemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
                spawnWandItemMeta.setUnbreakable(true);
                spawnWandItemMeta.setLocalizedName("spider");
                spawnWandItemMeta.setCustomModelData(20);
                ArrayList<String> spawnWandLore = new ArrayList<>();
                spawnWandLore.add(" ");
                spawnWandLore.add(ChatColor.YELLOW + ">  RIGHT CLICK TO SET LOCATION  <");
                spawnWandLore.add(ChatColor.YELLOW + "> LEFT CLICK TO REMOVE LOCATION <");
                spawnWandLore.add(" ");
                spawnWandLore.add(ChatColor.YELLOW + "MOB: " + ChatColor.GREEN + args[0]);
                spawnWandLore.add(" ");
                spawnWandItemMeta.setLore(spawnWandLore);
                spawnWand.setItemMeta(spawnWandItemMeta);

                p.getInventory().setItem(0, spawnWand);
            }
        }
        return false;
    }
}
