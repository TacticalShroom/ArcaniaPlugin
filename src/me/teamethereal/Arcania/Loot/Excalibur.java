package me.teamethereal.Arcania.Loot;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Set;

public class Excalibur implements CommandExecutor {

    private static Set<Block> effectiveBlocks;

    @Override @SuppressWarnings("deprecation")
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args)  {
        if (sender instanceof Player)   {
            Player p = (Player) sender;
            int attackDamage = 4;

            ItemStack excalibur = new ItemStack(Material.IRON_SWORD, 1, (short) attackDamage);
            ItemMeta excalibur_meta = excalibur.getItemMeta();
            assert excalibur_meta != null;
            excalibur_meta.setCustomModelData(attackDamage);
            excalibur_meta.setDisplayName(ChatColor.GOLD + "Excalibur");
            excalibur_meta.setUnbreakable(true);
            excalibur_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            excalibur_meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
            ArrayList<String> excalibur_lore = new ArrayList<>();
            excalibur_meta.setLocalizedName("melee weapon");
            excalibur_lore.add(" ");
            excalibur_lore.add(ChatColor.WHITE + "Attack Damage: " + attackDamage);
            excalibur_lore.add(ChatColor.WHITE + " ");
            excalibur_lore.add(ChatColor.WHITE + "Melee Weapon");
            excalibur_lore.add(ChatColor.GOLD + "LEGENDARY");
            excalibur_meta.setLore(excalibur_lore);
            excalibur.setItemMeta(excalibur_meta);

            p.getInventory().setItemInMainHand(excalibur);
        }
        return true;
    }
}
