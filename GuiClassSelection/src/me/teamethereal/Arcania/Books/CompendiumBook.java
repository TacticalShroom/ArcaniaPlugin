package me.teamethereal.Arcania.Books;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Objects;

@SuppressWarnings("deprecation")
public class CompendiumBook implements Listener {

    @EventHandler
    public void onCompendium(PlayerInteractEvent e) {

        Player p = e.getPlayer();
        Inventory compendium = Bukkit.createInventory(p, 9, ChatColor.DARK_BLUE + "Compendium");

        if (p.getItemInHand().getType().equals(Material.AIR)) return;
        if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK))  {
            if (Objects.requireNonNull(p.getItemInHand().getItemMeta()).getLocalizedName().equals("compendium")) {

                ItemStack completion = new ItemStack(Material.WRITABLE_BOOK, 1);
                ItemMeta completion_meta = completion.getItemMeta();
                completion_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                completion_meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
                completion_meta.setUnbreakable(true);
                completion_meta.setDisplayName(ChatColor.WHITE + "Completion");
                ArrayList<String> completion_lore = new ArrayList<>();
                completion_lore.add(" ");
                completion_lore.add(ChatColor.DARK_BLUE + "□ Achievements");
                completion_lore.add(ChatColor.DARK_BLUE + "    □ (0/72) Complete");
                completion_lore.add(" ");
                completion_lore.add(ChatColor.DARK_BLUE + "□ Discoveries");
                completion_lore.add(ChatColor.DARK_BLUE + "    □ (0/34) Complete");
                completion_lore.add(" ");
                completion_lore.add(ChatColor.DARK_BLUE + "□ Quests");
                completion_lore.add(ChatColor.DARK_BLUE + "    □ (0/140) Complete");
                completion_lore.add(" ");
                completion_lore.add(ChatColor.WHITE + "Completion: " + "0%");
                completion_meta.setLore(completion_lore);
                completion.setItemMeta(completion_meta);

                ItemStack placeholder = new ItemStack(Material.COMMAND_BLOCK, 1);
                ItemMeta placeholder_meta = placeholder.getItemMeta();
                placeholder_meta.setDisplayName(ChatColor.WHITE + "PlaceHolder");
                placeholder_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                placeholder_meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
                placeholder_meta.setUnbreakable(true);
                ArrayList<String> placeholder_lore = new ArrayList<>();
                placeholder_lore.add(" ");
                placeholder_meta.setLore(placeholder_lore);
                placeholder.setItemMeta(placeholder_meta);

                ItemStack factions = new ItemStack(Material.FILLED_MAP, 1);
                ItemMeta factions_meta = factions.getItemMeta();
                factions_meta.setDisplayName(ChatColor.WHITE + "Factions");
                factions_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                factions_meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
                factions_meta.setUnbreakable(true);
                ArrayList<String> factions_lore = new ArrayList<>();
                factions_lore.add(" ");
                factions_lore.add(ChatColor.DARK_BLUE + "□ High Sovereignty");
                factions_lore.add(ChatColor.DARK_BLUE + "□ Merchant guild");
                factions_lore.add(ChatColor.DARK_BLUE + "□ Kingdom Of Raelynn");
                factions_lore.add(ChatColor.DARK_BLUE + "□ Elise");
                factions_lore.add(ChatColor.DARK_BLUE + "□ Dirwind");
                factions_lore.add(ChatColor.DARK_BLUE + "□ Akkilith");
                factions_lore.add(ChatColor.DARK_BLUE + "□ Vorwood");
                factions_lore.add(" ");
                factions_meta.setLore(factions_lore);
                factions.setItemMeta(factions_meta);

                ItemStack lore = new ItemStack(Material.PAPER, 1);
                ItemMeta lore_meta = lore.getItemMeta();
                lore_meta.setDisplayName(ChatColor.GOLD + "Arcania Lore");
                lore_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                lore_meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
                lore_meta.setUnbreakable(true);
                ArrayList<String> lore_lore = new ArrayList<>();
                lore_lore.add(" ");
                lore_lore.add(ChatColor.WHITE + "Placeholder");
                lore_lore.add(" ");
                lore_meta.setLore(lore_lore);
                lore.setItemMeta(lore_meta);

                ItemStack help = new ItemStack(Material.BOOKSHELF, 1);
                ItemMeta help_meta = help.getItemMeta();
                help_meta.setDisplayName(ChatColor.LIGHT_PURPLE + "Help");
                help_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                help_meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
                help_meta.setUnbreakable(true);
                ArrayList<String> help_lore = new ArrayList<>();
                help_lore.add(" ");
                help_lore.add(ChatColor.YELLOW + "> CLICK FOR HELP <");
                help_lore.add(" ");
                help_meta.setLore(help_lore);
                help.setItemMeta(help_meta);

                ItemStack[] compendiumItems = new ItemStack[9];
                compendiumItems[2] = completion;
                compendiumItems[3] = placeholder;
                compendiumItems[4] = factions;
                compendiumItems[5] = lore;
                compendiumItems[6] = help;
                compendium.setContents(compendiumItems);
                p.openInventory(compendium);
            }
        }
    }
    @EventHandler
    public void onGUIClick(InventoryClickEvent e)   {
        Player p = (Player) e.getWhoClicked();
        String title = e.getView().getTitle();
        if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR) return;
        if (Objects.requireNonNull(e.getCurrentItem().getItemMeta()).getLocalizedName().equals("compendium")) {
            e.setCancelled(true);
        }
        if (title.equals(ChatColor.DARK_BLUE + "Compendium"))  {
            e.setCancelled(true);
        }
    }
}