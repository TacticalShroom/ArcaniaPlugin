package me.teamethereal.Arcania.Books;

import me.teamethereal.Arcania.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

@SuppressWarnings("deprecation")
public class QuestBook implements Listener {

    Inventory zoneSelection = Bukkit.createInventory(null, 27, ChatColor.BLACK + "Zones");
    Inventory shadyGlen = Bukkit.createInventory(null, 54, ChatColor.GREEN + "Shady Glen Quests");
    Inventory newBerryFields = Bukkit.createInventory(null, 54, ChatColor.YELLOW + "Newberry Fields Quests");
    Inventory eliseProvince = Bukkit.createInventory(null, 54, ChatColor.DARK_RED + "Elise Province Quests");
    Inventory vorWood = Bukkit.createInventory(null, 54, ChatColor.DARK_GREEN + "Vorwood Quests");
    Inventory akkilith = Bukkit.createInventory(null, 54, ChatColor.DARK_GRAY + "Akkilith Quests");
    Inventory iceVales = Bukkit.createInventory(null, 54, ChatColor.BLUE + "Ice Vales Quests");
    Inventory landisIles = Bukkit.createInventory(null, 54, ChatColor.LIGHT_PURPLE + "LandisIles Quests");
    Inventory allQuests = Bukkit.createInventory(null, 54, ChatColor.WHITE + "All Quests");

    @EventHandler
    public void onInteract(PlayerInteractEvent e)    {
        Player p = e.getPlayer();

        String classSelection = "none";
        if (p.hasMetadata("rogue"))   {
            classSelection = "rogue";
        }
        else if (p.hasMetadata("knight")) {
            classSelection = "knight";
        }
        else if (p.hasMetadata("ranger")) {
            classSelection = "ranger";
        }
        else if (p.hasMetadata("wizard")) {
            classSelection = "wizard";
        }
        else if (p.hasMetadata("druid")) {
            classSelection = "druid";
        }
        ArrayList<File> questsC = new ArrayList<>();
        File fileQ = new File(Main.plugin.getDataFolder().getAbsolutePath() + File.separator + "Quests");
        File[] files = fileQ.listFiles();


//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        if (new File(Main.plugin.getDataFolder().getAbsolutePath() + File.separator + "StartedQuests", p.getUniqueId() + "-" + classSelection + "-" + "questTemplate" + ".yml").exists())  {
            File file = new File(Main.plugin.getDataFolder().getAbsolutePath() + File.separator + "StartedQuests", p.getUniqueId() + "-" + classSelection + "-" + "questTemplate" + ".yml");
            FileConfiguration config = YamlConfiguration.loadConfiguration(file);
            String complete = config.getString("complete");
            assert complete != null;
            if (complete.equals("true")) {
                questsC.add(file);
            }
        }
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

        int qC = questsC.size();
        assert files != null;
        int tC = files.length;
        int cP = (qC/tC)*100;


        ItemStack shadyGlenBook = new ItemStack(Material.ENCHANTED_BOOK, 1);
        ItemMeta shadyGlenBook_meta = shadyGlenBook.getItemMeta();
        shadyGlenBook_meta.setLocalizedName("shadyGlenBook");
        shadyGlenBook_meta.setDisplayName(ChatColor.GREEN + "Shady Glen Quests");
        shadyGlenBook_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        shadyGlenBook_meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        shadyGlenBook_meta.setUnbreakable(true);
        ArrayList<String> shadyGlenBook_lore = new ArrayList<>();
        shadyGlenBook_lore.add(" ");
        shadyGlenBook_lore.add(ChatColor.WHITE + "Quests Complete: " + "(" + qC + "/" + tC + ")");
        shadyGlenBook_lore.add(ChatColor.GOLD + "Completion: " + ChatColor.GOLD + cP + "%");
        shadyGlenBook_lore.add(" ");
        shadyGlenBook_meta.setLore(shadyGlenBook_lore);
        shadyGlenBook.setItemMeta(shadyGlenBook_meta);

        ItemStack newBerryFieldsBook = new ItemStack(Material.ENCHANTED_BOOK, 1);
        ItemMeta newBerryFieldsBook_meta = newBerryFieldsBook.getItemMeta();
        newBerryFieldsBook_meta.setDisplayName(ChatColor.YELLOW + "Newberry Fields Quests");
        newBerryFieldsBook_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        newBerryFieldsBook_meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        newBerryFieldsBook_meta.setUnbreakable(true);
        ArrayList<String> newBerryFieldsBook_lore = new ArrayList<>();
        newBerryFieldsBook_lore.add(" ");
        newBerryFieldsBook_lore.add(ChatColor.WHITE + "Quests Complete: " + "(0/20)");
        newBerryFieldsBook_lore.add(ChatColor.GOLD + "Completion: " + "0%");
        newBerryFieldsBook_lore.add(" ");
        newBerryFieldsBook_meta.setLore(newBerryFieldsBook_lore);
        newBerryFieldsBook.setItemMeta(newBerryFieldsBook_meta);

        ItemStack eliseProvinceBook = new ItemStack(Material.ENCHANTED_BOOK, 1);
        ItemMeta eliseProvinceBook_meta = eliseProvinceBook.getItemMeta();
        eliseProvinceBook_meta.setDisplayName(ChatColor.DARK_RED + "Elise Province Quests");
        eliseProvinceBook_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        eliseProvinceBook_meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        eliseProvinceBook_meta.setUnbreakable(true);
        ArrayList<String> eliseProvinceBook_lore = new ArrayList<>();
        eliseProvinceBook_lore.add(" ");
        eliseProvinceBook_lore.add(ChatColor.WHITE + "Quests Complete: " + "(0/20)");
        eliseProvinceBook_lore.add(ChatColor.GOLD + "Completion: " + "0%");
        eliseProvinceBook_lore.add(" ");
        eliseProvinceBook_meta.setLore(eliseProvinceBook_lore);
        eliseProvinceBook.setItemMeta(eliseProvinceBook_meta);

        ItemStack vorWoodBook = new ItemStack(Material.ENCHANTED_BOOK, 1);
        ItemMeta vorWoodBook_meta = vorWoodBook.getItemMeta();
        vorWoodBook_meta.setDisplayName(ChatColor.DARK_GREEN + "Vorwood Quests");
        vorWoodBook_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        vorWoodBook_meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        vorWoodBook_meta.setUnbreakable(true);
        ArrayList<String> vorWoodBook_lore = new ArrayList<>();
        vorWoodBook_lore.add(" ");
        vorWoodBook_lore.add(ChatColor.WHITE + "Quests Complete: " + "(0/20)");
        vorWoodBook_lore.add(ChatColor.GOLD + "Completion: " + "0%");
        vorWoodBook_lore.add(" ");
        vorWoodBook_meta.setLore(vorWoodBook_lore);
        vorWoodBook.setItemMeta(vorWoodBook_meta);

        ItemStack akkilithBook = new ItemStack(Material.ENCHANTED_BOOK, 1);
        ItemMeta akkilithBook_meta = akkilithBook.getItemMeta();
        akkilithBook_meta.setDisplayName(ChatColor.DARK_GRAY + "Akkilith Quests");
        akkilithBook_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        akkilithBook_meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        akkilithBook_meta.setUnbreakable(true);
        ArrayList<String> akkilithBook_lore = new ArrayList<>();
        akkilithBook_lore.add(" ");
        akkilithBook_lore.add(ChatColor.WHITE + "Quests Complete: " + "(0/20)");
        akkilithBook_lore.add(ChatColor.GOLD + "Completion: " + "0%");
        akkilithBook_lore.add(" ");
        akkilithBook_meta.setLore(akkilithBook_lore);
        akkilithBook.setItemMeta(akkilithBook_meta);

        ItemStack iceValesBook = new ItemStack(Material.ENCHANTED_BOOK, 1);
        ItemMeta iceValesBook_meta = iceValesBook.getItemMeta();
        iceValesBook_meta.setDisplayName(ChatColor.BLUE + "Ice Vales Quests");
        iceValesBook_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        iceValesBook_meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        iceValesBook_meta.setUnbreakable(true);
        ArrayList<String> iceValesBook_lore = new ArrayList<>();
        iceValesBook_lore.add(" ");
        iceValesBook_lore.add(ChatColor.WHITE + "Quests Complete: " + "(0/20)");
        iceValesBook_lore.add(ChatColor.GOLD + "Completion: " + "0%");
        iceValesBook_lore.add(" ");
        iceValesBook_meta.setLore(iceValesBook_lore);
        iceValesBook.setItemMeta(iceValesBook_meta);

        ItemStack landisIlesBook = new ItemStack(Material.ENCHANTED_BOOK, 1);
        ItemMeta landisIlesBook_meta = landisIlesBook.getItemMeta();
        landisIlesBook_meta.setDisplayName(ChatColor.LIGHT_PURPLE + "Landis Isles Quests");
        landisIlesBook_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        landisIlesBook_meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        landisIlesBook_meta.setUnbreakable(true);
        ArrayList<String> landisIlesBook_lore = new ArrayList<>();
        landisIlesBook_lore.add(" ");
        landisIlesBook_lore.add(ChatColor.WHITE + "Quests Complete: " + "(0/20)");
        landisIlesBook_lore.add(ChatColor.GOLD + "Completion: " + "0%");
        landisIlesBook_lore.add(" ");
        landisIlesBook_meta.setLore(landisIlesBook_lore);
        landisIlesBook.setItemMeta(landisIlesBook_meta);

        ItemStack allQuestsBook = new ItemStack(Material.ENCHANTED_BOOK, 1);
        ItemMeta allQuestsBook_meta = allQuestsBook.getItemMeta();
        allQuestsBook_meta.setDisplayName(ChatColor.WHITE + "All Quests");
        allQuestsBook_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        allQuestsBook_meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        allQuestsBook_meta.setUnbreakable(true);
        ArrayList<String> allQuestsBook_lore = new ArrayList<>();
        allQuestsBook_lore.add(" ");
        allQuestsBook_lore.add(ChatColor.WHITE + "Quests Complete: " + "(0/140)");
        allQuestsBook_lore.add(ChatColor.GOLD + "Completion: " + "0%");
        allQuestsBook_lore.add(" ");
        allQuestsBook_meta.setLore(allQuestsBook_lore);
        allQuestsBook.setItemMeta(allQuestsBook_meta);

        ItemStack[] zoneSelectionItems = new ItemStack[27];
        zoneSelectionItems[4] = allQuestsBook;
        zoneSelectionItems[10] = shadyGlenBook;
        zoneSelectionItems[11] = newBerryFieldsBook;
        zoneSelectionItems[12] = eliseProvinceBook;
        zoneSelectionItems[13] = vorWoodBook;
        zoneSelectionItems[14] = akkilithBook;
        zoneSelectionItems[15] = iceValesBook;
        zoneSelectionItems[16] = landisIlesBook;
        zoneSelection.setContents(zoneSelectionItems);

        if (p.getItemInHand().getType().equals(Material.AIR)) return;
        if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (Objects.requireNonNull(p.getItemInHand().getItemMeta()).getLocalizedName().equals("questBook")) {
                p.openInventory(zoneSelection);
            }
        }
    }

    @EventHandler
    public void onGUIClick(InventoryClickEvent e) throws IOException {
        Player p = (Player) e.getWhoClicked();

        String classSelection = "none";
        if (p.hasMetadata("rogue"))   {
            classSelection = "rogue";
        }
        else if (p.hasMetadata("knight")) {
            classSelection = "knight";
        }
        else if (p.hasMetadata("ranger")) {
            classSelection = "ranger";
        }
        else if (p.hasMetadata("wizard")) {
            classSelection = "wizard";
        }
        else if (p.hasMetadata("druid")) {
            classSelection = "druid";
        }

        File file = new File(Main.plugin.getDataFolder().getAbsolutePath() + File.separator + "StartedQuests", p.getUniqueId() + "-" + classSelection + "-" + "questTemplate" + ".yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);

        ItemStack questTemplate = new ItemStack(Material.WRITTEN_BOOK);
        ItemMeta questTemplate_meta = questTemplate.getItemMeta();
        questTemplate_meta.setLocalizedName("questTemplate");
        questTemplate_meta.setDisplayName(ChatColor.GREEN + "Quest Template");
        questTemplate_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        questTemplate_meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        questTemplate_meta.setUnbreakable(true);
        ArrayList<String> questTemplate_lore = new ArrayList<>();
        questTemplate_lore.add(" ");
        questTemplate_lore.add(ChatColor.BLUE + "Location: ");
        questTemplate_lore.add(ChatColor.BLUE + "□ WeaponSmith in Eldridge (Shady Glen)");
        questTemplate_lore.add(ChatColor.BLUE + "□ (169, 64, 430)");
        questTemplate_lore.add(" ");
        questTemplate_lore.add(ChatColor.YELLOW + "Length: Long");
        questTemplate_lore.add(" ");
        questTemplate_lore.add(ChatColor.GOLD + "Reward:");
        questTemplate_lore.add(ChatColor.GOLD + "□ 58 Gemeralds");
        questTemplate_lore.add(ChatColor.GOLD + "□ 25 Experience");
        questTemplate_lore.add(" ");
        if (file.exists())  {
            if (Objects.equals(config.getString("complete"), "true"))    {
                questTemplate_lore.add(ChatColor.GREEN + "> COMPLETE <");
            }
            else    {
                questTemplate_lore.add(ChatColor.YELLOW + "> CLICK TO CANCEL QUEST <");
            }
        }

        else {
            questTemplate_lore.add(ChatColor.YELLOW + "> CLICK TO START QUEST <");
        }
        questTemplate_lore.add(" ");
        questTemplate_meta.setLore(questTemplate_lore);
        questTemplate.setItemMeta(questTemplate_meta);

        ItemStack goBack = new ItemStack(Material.BARRIER, 1);
        ItemMeta goBack_meta = goBack.getItemMeta();
        goBack_meta.setDisplayName(ChatColor.WHITE + "Go Back");
        goBack_meta.setLocalizedName("goBack");
        goBack_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        goBack_meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        goBack_meta.setUnbreakable(true);
        ArrayList<String> goBack_lore = new ArrayList<>();
        goBack_lore.add(" ");
        goBack_meta.setLore(goBack_lore);
        goBack.setItemMeta(goBack_meta);

        ItemStack[] shadyGlenItems = new ItemStack[54];
        shadyGlenItems[10] = questTemplate;
        shadyGlenItems[45] = goBack;
        shadyGlen.setContents(shadyGlenItems);

        if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR) return;
        if (Objects.requireNonNull(Objects.requireNonNull(e.getCurrentItem()).getItemMeta()).getLocalizedName().equals("questBook"))   {
            e.setCancelled(true);
        }
        if (e.getCurrentItem().getItemMeta().getLocalizedName().equals("characterInfo"))    {
            e.setCancelled(true);
        }
        String title = e.getView().getTitle();
        String questName = "noQuest";
        if (Objects.requireNonNull(e.getCurrentItem().getItemMeta()).hasLocalizedName())    {
            questName = Objects.requireNonNull(e.getCurrentItem().getItemMeta()).getLocalizedName();
        }
        if (title.equals(ChatColor.BLACK + "Zones"))   {
            e.setCancelled(true);
            if (Objects.requireNonNull(e.getCurrentItem().getItemMeta()).getLocalizedName().equals("shadyGlenBook"))    {
                p.closeInventory();
                p.openInventory(shadyGlen);
            }
        }
        if (title.equals(ChatColor.GREEN + "Shady Glen Quests")) {
            e.setCancelled(true);
            if (Objects.requireNonNull(e.getCurrentItem().getItemMeta()).getLocalizedName().equals("goBack")) {
                p.closeInventory();
                p.openInventory(zoneSelection);
            }
            if (Objects.requireNonNull(e.getCurrentItem().getItemMeta()).getLocalizedName().equals("questTemplate")) {
                if (file.exists()) {
                    new File(Main.plugin.getDataFolder().getAbsolutePath() + File.separator + "StartedQuests", p.getUniqueId() + "-" + classSelection + "-" + "questTemplate" + ".yml").delete();
                }
                else    {
                    File f = new File(Main.plugin.getDataFolder().getAbsolutePath() + File.separator + "StartedQuests", p.getUniqueId() + "-" + classSelection + "-" + questName + ".yml");
                    FileConfiguration cf = YamlConfiguration.loadConfiguration(f);
                    cf.set("complete", "false");
                    cf.save(f);
                }
                p.closeInventory();
            }
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e)   {
        Player p = e.getPlayer();
        if (Objects.requireNonNull(e.getItemDrop().getItemStack().getItemMeta()).getLocalizedName().equals("questBook"))    {
            e.setCancelled(true);
        }
        if (Objects.requireNonNull(e.getItemDrop().getItemStack().getItemMeta()).getLocalizedName().equals("compendium"))    {
            e.setCancelled(true);
        }
        if (e.getItemDrop().getItemStack().getItemMeta().getLocalizedName().equals("characterInfo"))    {
            e.setCancelled(true);
        }
    }
}
