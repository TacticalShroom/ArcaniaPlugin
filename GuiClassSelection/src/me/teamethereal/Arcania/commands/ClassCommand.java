package me.teamethereal.Arcania.commands;

import me.teamethereal.Arcania.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.util.ArrayList;

public class ClassCommand implements CommandExecutor {

    String classSelection;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (sender instanceof Player)   {
            Player player = (Player) sender;

            classSelection = "none";
            if (player.hasMetadata("rogue"))   {
                classSelection = "rogue";
            }
            else if (player.hasMetadata("knight")) {
                classSelection = "knight";
            }
            else if (player.hasMetadata("ranger")) {
                classSelection = "ranger";
            }
            else if (player.hasMetadata("wizard")) {
                classSelection = "wizard";
            }
            else if (player.hasMetadata("druid")) {
                classSelection = "druid";
            }
            int level = 69;
            int maxHealth = 69;
            int strengthPoints = 69;
            int dexterityPoints = 69;
            int intelligencePoints = 69;
            int constitutionPoints = 69;
            int gemeralds = 69;

            int level1 = 69;
            int maxHealth1 = 69;
            int strengthPoints1 = 69;
            int dexterityPoints1 = 69;
            int intelligencePoints1 = 69;
            int constitutionPoints1 = 69;
            int gemeralds1 = 69;

            int level2 = 69;
            int maxHealth2 = 69;
            int strengthPoints2 = 69;
            int dexterityPoints2 = 69;
            int intelligencePoints2 = 69;
            int constitutionPoints2 = 69;
            int gemeralds2 = 69;

            int level3 = 69;
            int maxHealth3 = 69;
            int strengthPoints3 = 69;
            int dexterityPoints3 = 69;
            int intelligencePoints3 = 69;
            int constitutionPoints3 = 69;
            int gemeralds3 = 69;

            int level4 = 69;
            int maxHealth4 = 69;
            int strengthPoints4 = 69;
            int dexterityPoints4 = 69;
            int intelligencePoints4 = 69;
            int constitutionPoints4 = 69;
            int gemeralds4 = 69;
            if (player.hasMetadata("profileSelectionPermR") || player.hasMetadata("profileSelectionPermK") || player.hasMetadata("profileSelectionPermD") || player.hasMetadata("profileSelectionPermRA") || player.hasMetadata("profileSelectionPermW"))   {
                File file = new File(Main.plugin.getDataFolder().getAbsolutePath() + File.separator + "SkillPoints", player.getUniqueId() + "-" + "rogue" + "skillPoints" + ".yml");
                FileConfiguration fC = YamlConfiguration.loadConfiguration(file);
                File f = new File(Main.plugin.getDataFolder().getAbsolutePath() + File.separator + "Profiles", player.getUniqueId() + "-" + "rogue" + ".yml");
                FileConfiguration c = YamlConfiguration.loadConfiguration(f);
                level = c.getInt("stats.level");
                maxHealth = c.getInt("stats.maxHealth");
                strengthPoints = fC.getInt("stats.proficiencies.strength");
                dexterityPoints = fC.getInt("stats.proficiencies.dexterity");
                intelligencePoints = fC.getInt("stats.proficiencies.intelligence");
                constitutionPoints = fC.getInt("stats.proficiencies.constitution");
                gemeralds = c.getInt("stats.gemeralds");

                File file1 = new File(Main.plugin.getDataFolder().getAbsolutePath() + File.separator + "SkillPoints", player.getUniqueId() + "-" + "knight" + "skillPoints" + ".yml");
                FileConfiguration fC1 = YamlConfiguration.loadConfiguration(file1);
                File f1 = new File(Main.plugin.getDataFolder().getAbsolutePath() + File.separator + "Profiles", player.getUniqueId() + "-" + "knight" + ".yml");
                FileConfiguration c1 = YamlConfiguration.loadConfiguration(f1);
                level1 = c.getInt("stats.level");
                maxHealth1 = c.getInt("stats.maxHealth");
                strengthPoints1 = fC.getInt("stats.proficiencies.strength");
                dexterityPoints1 = fC.getInt("stats.proficiencies.dexterity");
                intelligencePoints1 = fC.getInt("stats.proficiencies.intelligence");
                constitutionPoints1 = fC.getInt("stats.proficiencies.constitution");
                gemeralds1 = c.getInt("stats.gemeralds");

                File file2 = new File(Main.plugin.getDataFolder().getAbsolutePath() + File.separator + "SkillPoints", player.getUniqueId() + "-" + "ranger" + "skillPoints" + ".yml");
                FileConfiguration fC2 = YamlConfiguration.loadConfiguration(file2);
                File f2 = new File(Main.plugin.getDataFolder().getAbsolutePath() + File.separator + "Profiles", player.getUniqueId() + "-" + "ranger" + ".yml");
                FileConfiguration c2 = YamlConfiguration.loadConfiguration(f2);
                level2 = c.getInt("stats.level");
                maxHealth2 = c.getInt("stats.maxHealth");
                strengthPoints2 = fC.getInt("stats.proficiencies.strength");
                dexterityPoints2 = fC.getInt("stats.proficiencies.dexterity");
                intelligencePoints2 = fC.getInt("stats.proficiencies.intelligence");
                constitutionPoints2 = fC.getInt("stats.proficiencies.constitution");
                gemeralds2 = c.getInt("stats.gemeralds");

                File file3 = new File(Main.plugin.getDataFolder().getAbsolutePath() + File.separator + "SkillPoints", player.getUniqueId() + "-" + "wizard" + "skillPoints" + ".yml");
                FileConfiguration fC3 = YamlConfiguration.loadConfiguration(file3);
                File f3 = new File(Main.plugin.getDataFolder().getAbsolutePath() + File.separator + "Profiles", player.getUniqueId() + "-" + "wizard" + ".yml");
                FileConfiguration c3 = YamlConfiguration.loadConfiguration(f3);
                level3 = c.getInt("stats.level");
                maxHealth3 = c.getInt("stats.maxHealth");
                strengthPoints3 = fC.getInt("stats.proficiencies.strength");
                dexterityPoints3 = fC.getInt("stats.proficiencies.dexterity");
                intelligencePoints3 = fC.getInt("stats.proficiencies.intelligence");
                constitutionPoints3 = fC.getInt("stats.proficiencies.constitution");
                gemeralds3 = c.getInt("stats.gemeralds");

                File file4 = new File(Main.plugin.getDataFolder().getAbsolutePath() + File.separator + "SkillPoints", player.getUniqueId() + "-" + "druid" + "skillPoints" + ".yml");
                FileConfiguration fC4 = YamlConfiguration.loadConfiguration(file4);
                File f4 = new File(Main.plugin.getDataFolder().getAbsolutePath() + File.separator + "Profiles", player.getUniqueId() + "-" + "druid" + ".yml");
                FileConfiguration c4 = YamlConfiguration.loadConfiguration(f4);
                level4 = c.getInt("stats.level");
                maxHealth4 = c.getInt("stats.maxHealth");
                strengthPoints4 = fC.getInt("stats.proficiencies.strength");
                dexterityPoints4 = fC.getInt("stats.proficiencies.dexterity");
                intelligencePoints4 = fC.getInt("stats.proficiencies.intelligence");
                constitutionPoints4 = fC.getInt("stats.proficiencies.constitution");
                gemeralds4 = c.getInt("stats.gemeralds");

            }

            Inventory classes = Bukkit.createInventory(player, 9, ChatColor.AQUA + "Class Selection");

            ItemStack rogue = new ItemStack(Material.LEATHER_BOOTS, 1);

            ItemMeta rogue_meta = rogue.getItemMeta();
            rogue_meta.setDisplayName(ChatColor.LIGHT_PURPLE + "Rogue");
            ArrayList<String> rogue_lore = new ArrayList<>();
            rogue_lore.add(" ");
            rogue_lore.add(ChatColor.DARK_PURPLE + "A sneaky class to get around unnoticed...");
            rogue_lore.add(ChatColor.DARK_PURPLE + "A sneaky class to get around unnoticed...");
            rogue_lore.add(ChatColor.DARK_PURPLE + "A sneaky class to get around unnoticed...");
            rogue_lore.add(ChatColor.DARK_PURPLE + "A sneaky class to get around unnoticed...");
            rogue_lore.add(ChatColor.DARK_PURPLE + "A sneaky class to get around unnoticed...");
            rogue_lore.add(" ");
            rogue_lore.add(ChatColor.RED + "Health - 8");
            rogue_lore.add(" ");
            rogue_lore.add(ChatColor.BLUE + "Weapon Proficiencies");
            rogue_lore.add(ChatColor.WHITE + "□ Light Melee");
            rogue_lore.add(ChatColor.WHITE + "□ Light Ranged");
            rogue_lore.add(ChatColor.BLUE + "Skill Proficiencies");
            rogue_lore.add(ChatColor.WHITE + "□ Dexterity");
            rogue_lore.add(ChatColor.WHITE + "□ Intelligence");
            rogue_lore.add(ChatColor.GREEN + "Environment");
            rogue_lore.add(ChatColor.WHITE + "□ Elise Province");
            rogue_meta.setLore(rogue_lore);
            rogue.setItemMeta(rogue_meta);

            ItemStack rogueP = new ItemStack(Material.LEATHER_BOOTS, 1);

            ItemMeta rogueP_meta = rogueP.getItemMeta();
            rogueP_meta.setDisplayName(ChatColor.LIGHT_PURPLE + "Rogue");
            ArrayList<String> rogueP_lore = new ArrayList<>();
            rogueP_lore.add(" ");
            rogueP_lore.add(ChatColor.DARK_PURPLE + "Level - " + level);
            rogueP_lore.add(ChatColor.RED + "Health - " + maxHealth);
            rogueP_lore.add(ChatColor.GREEN + "Gemeralds - " + gemeralds);
            rogueP_lore.add(" ");
            rogueP_lore.add(ChatColor.DARK_PURPLE + "Strength " + ChatColor.WHITE + strengthPoints);
            rogueP_lore.add(ChatColor.DARK_PURPLE + "Dexterity " + ChatColor.WHITE + dexterityPoints);
            rogueP_lore.add(ChatColor.DARK_PURPLE + "Constitution - " + ChatColor.WHITE + constitutionPoints);
            rogueP_lore.add(ChatColor.DARK_PURPLE + "Intelligence - " + ChatColor.WHITE + intelligencePoints);
            rogueP_lore.add(" ");
            rogueP_lore.add(ChatColor.BLUE + "Weapon Proficiencies");
            rogueP_lore.add(ChatColor.WHITE + "□ Light Melee");
            rogueP_lore.add(ChatColor.WHITE + "□ Light Ranged");
            rogueP_lore.add(ChatColor.BLUE + "Skill Proficiencies");
            rogueP_lore.add(ChatColor.WHITE + "□ Dexterity");
            rogueP_lore.add(ChatColor.WHITE + "□ Intelligence");
            rogueP_lore.add(ChatColor.GREEN + "Environment");
            rogueP_lore.add(ChatColor.WHITE + "□ Elise Province");
            rogueP_lore.add(ChatColor.WHITE + " ");
            rogueP_lore.add(ChatColor.RED + "> RIGHT CLICK TO DELETE <");
            rogueP_meta.setLore(rogueP_lore);
            rogueP.setItemMeta(rogueP_meta);

            ItemStack fighter = new ItemStack(Material.IRON_AXE, 1);

            ItemMeta fighter_meta = fighter.getItemMeta();
            fighter_meta.setDisplayName(ChatColor.LIGHT_PURPLE + "Knight");
            ArrayList<String> fighter_lore = new ArrayList<>();
            fighter_lore.add(" ");
            fighter_lore.add(ChatColor.DARK_PURPLE + "A noble warrior that fights on the front lines.");
            fighter_lore.add(ChatColor.DARK_PURPLE + "A noble warrior that fights on the front lines.");
            fighter_lore.add(ChatColor.DARK_PURPLE + "A noble warrior that fights on the front lines.");
            fighter_lore.add(ChatColor.DARK_PURPLE + "A noble warrior that fights on the front lines.");
            fighter_lore.add(ChatColor.DARK_PURPLE + "A noble warrior that fights on the front lines.");
            fighter_lore.add(" ");
            fighter_lore.add(ChatColor.RED + "Starting Health - 14");
            fighter_lore.add(" ");
            fighter_lore.add(ChatColor.BLUE + "Weapon Proficiencies");
            fighter_lore.add(ChatColor.WHITE + "□ Heavy Melee");
            fighter_lore.add(ChatColor.WHITE + "□ Heavy Ranged");
            fighter_lore.add(ChatColor.BLUE + "Skill Proficiencies");
            fighter_lore.add(ChatColor.WHITE + "□ Strength");
            fighter_lore.add(ChatColor.WHITE + "□ Constitution");
            fighter_lore.add(ChatColor.GREEN + "Environment");
            fighter_lore.add(ChatColor.WHITE + "□ Elise Province");
            fighter_meta.setLore(fighter_lore);
            fighter.setItemMeta(fighter_meta);

            ItemStack knight = new ItemStack(Material.IRON_AXE, 1);

            ItemMeta knight_meta = knight.getItemMeta();
            knight_meta.setDisplayName(ChatColor.LIGHT_PURPLE + "Knight");
            ArrayList<String> knight_lore = new ArrayList<>();
            knight_lore.add(" ");
            knight_lore.add(ChatColor.DARK_PURPLE + "Level - " + level1);
            knight_lore.add(ChatColor.RED + "Health - " + maxHealth1);
            knight_lore.add(ChatColor.GREEN + "Gemeralds - " + gemeralds1);
            knight_lore.add(" ");
            knight_lore.add(ChatColor.DARK_PURPLE + "Strength " + ChatColor.WHITE + strengthPoints1);
            knight_lore.add(ChatColor.DARK_PURPLE + "Dexterity " + ChatColor.WHITE + dexterityPoints1);
            knight_lore.add(ChatColor.DARK_PURPLE + "Constitution - " + ChatColor.WHITE + constitutionPoints1);
            knight_lore.add(ChatColor.DARK_PURPLE + "Intelligence - " + ChatColor.WHITE + intelligencePoints1);
            knight_lore.add(" ");
            knight_lore.add(ChatColor.BLUE + "Weapon Proficiencies");
            knight_lore.add(ChatColor.WHITE + "□ Heavy Melee");
            knight_lore.add(ChatColor.WHITE + "□ Heavy Ranged");
            knight_lore.add(ChatColor.BLUE + "Skill Proficiencies");
            knight_lore.add(ChatColor.WHITE + "□ Strength");
            knight_lore.add(ChatColor.WHITE + "□ Constitution");
            knight_lore.add(ChatColor.GREEN + "Environment");
            knight_lore.add(ChatColor.WHITE + "□ Elise Province");
            knight_lore.add(ChatColor.WHITE + " ");
            knight_lore.add(ChatColor.RED + "> RIGHT CLICK TO DELETE <");
            knight_meta.setLore(knight_lore);
            knight.setItemMeta(knight_meta);

            ItemStack ranger = new ItemStack(Material.BOW, 1);

            ItemMeta ranger_meta = ranger.getItemMeta();
            ranger_meta.setDisplayName(ChatColor.LIGHT_PURPLE + "Ranger");
            ArrayList<String> ranger_lore = new ArrayList<>();
            ranger_lore.add(" ");
            ranger_lore.add(ChatColor.DARK_PURPLE + "A tracker with a strong will and a long bow.");
            ranger_lore.add(ChatColor.DARK_PURPLE + "A tracker with a strong will and a long bow.");
            ranger_lore.add(ChatColor.DARK_PURPLE + "A tracker with a strong will and a long bow.");
            ranger_lore.add(ChatColor.DARK_PURPLE + "A tracker with a strong will and a long bow.");
            ranger_lore.add(ChatColor.DARK_PURPLE + "A tracker with a strong will and a long bow.");
            ranger_lore.add(" ");
            ranger_lore.add(ChatColor.RED + "Health - 12");
            ranger_lore.add(ChatColor.BLUE + " ");
            ranger_lore.add(ChatColor.BLUE + "Weapon Proficiencies");
            ranger_lore.add(ChatColor.WHITE + "□ Light Melee");
            ranger_lore.add(ChatColor.WHITE + "□ Heavy Ranged");
            ranger_lore.add(ChatColor.BLUE + "Skill Proficiencies");
            ranger_lore.add(ChatColor.WHITE + "□ Strength");
            ranger_lore.add(ChatColor.WHITE + "□ Dexterity");
            ranger_lore.add(ChatColor.GREEN + "Environment");
            ranger_lore.add(ChatColor.WHITE + "□ Vorwood");
            ranger_meta.setLore(ranger_lore);
            ranger.setItemMeta(ranger_meta);

            ItemStack rangerP = new ItemStack(Material.BOW, 1);

            ItemMeta rangerP_meta = rangerP.getItemMeta();
            rangerP_meta.setDisplayName(ChatColor.LIGHT_PURPLE + "Knight");
            ArrayList<String> rangerP_lore = new ArrayList<>();
            rangerP_lore.add(" ");
            rangerP_lore.add(ChatColor.DARK_PURPLE + "Level - " + level2);
            rangerP_lore.add(ChatColor.RED + "Health - " + maxHealth2);
            rangerP_lore.add(ChatColor.GREEN + "Gemeralds - " + gemeralds2);
            rangerP_lore.add(" ");
            rangerP_lore.add(ChatColor.DARK_PURPLE + "Strength " + ChatColor.WHITE + strengthPoints2);
            rangerP_lore.add(ChatColor.DARK_PURPLE + "Dexterity " + ChatColor.WHITE + dexterityPoints2);
            rangerP_lore.add(ChatColor.DARK_PURPLE + "Constitution - " + ChatColor.WHITE + constitutionPoints2);
            rangerP_lore.add(ChatColor.DARK_PURPLE + "Intelligence - " + ChatColor.WHITE + intelligencePoints2);
            rangerP_lore.add(" ");
            rangerP_lore.add(ChatColor.BLUE + "Weapon Proficiencies");
            rangerP_lore.add(ChatColor.WHITE + "□ Light Melee");
            rangerP_lore.add(ChatColor.WHITE + "□ Heavy Ranged");
            rangerP_lore.add(ChatColor.BLUE + "Skill Proficiencies");
            rangerP_lore.add(ChatColor.WHITE + "□ Strength");
            rangerP_lore.add(ChatColor.WHITE + "□ Dexterity");
            rangerP_lore.add(ChatColor.GREEN + "Environment");
            rangerP_lore.add(ChatColor.WHITE + "□ Vorwood");
            rangerP_lore.add(ChatColor.WHITE + " ");
            rangerP_lore.add(ChatColor.RED + "> RIGHT CLICK TO DELETE <");
            rangerP_meta.setLore(rangerP_lore);
            rangerP.setItemMeta(rangerP_meta);

            ItemStack wizardP = new ItemStack(Material.END_CRYSTAL, 1);

            ItemMeta wizardP_meta = wizardP.getItemMeta();
            wizardP_meta.setDisplayName(ChatColor.LIGHT_PURPLE + "Wizard");
            ArrayList<String> wizardP_lore = new ArrayList<>();
            wizardP_lore.add(" ");
            wizardP_lore.add(ChatColor.DARK_PURPLE + "Level - " + level3);
            wizardP_lore.add(ChatColor.RED + "Health - " + maxHealth3);
            wizardP_lore.add(ChatColor.GREEN + "Gemeralds - " + gemeralds3);
            wizardP_lore.add(" ");
            wizardP_lore.add(ChatColor.DARK_PURPLE + "Strength " + ChatColor.WHITE + strengthPoints3);
            wizardP_lore.add(ChatColor.DARK_PURPLE + "Dexterity " + ChatColor.WHITE + dexterityPoints3);
            wizardP_lore.add(ChatColor.DARK_PURPLE + "Constitution - " + ChatColor.WHITE + constitutionPoints3);
            wizardP_lore.add(ChatColor.DARK_PURPLE + "Intelligence - " + ChatColor.WHITE + intelligencePoints3);
            wizardP_lore.add(" ");
            wizardP_lore.add(ChatColor.BLUE + "Weapon Proficiencies");
            wizardP_lore.add(ChatColor.WHITE + "□ Arcane");
            wizardP_lore.add(ChatColor.BLUE + "Skill Proficiencies");
            wizardP_lore.add(ChatColor.WHITE + "□ Intelligence");
            wizardP_lore.add(ChatColor.WHITE + "□ Dexterity");
            wizardP_lore.add(ChatColor.GREEN + "Environment");
            wizardP_lore.add(ChatColor.WHITE + "□ Landis Isles");
            wizardP_lore.add(ChatColor.WHITE + " ");
            wizardP_lore.add(ChatColor.RED + "> RIGHT CLICK TO DELETE <");
            wizardP_meta.setLore(wizardP_lore);
            wizardP.setItemMeta(wizardP_meta);

            ItemStack wizard = new ItemStack(Material.END_CRYSTAL, 1);

            ItemMeta wizard_meta = wizard.getItemMeta();
            wizard_meta.setDisplayName(ChatColor.LIGHT_PURPLE + "Wizard");
            ArrayList<String> wizard_lore = new ArrayList<>();
            wizard_lore.add(" ");
            wizard_lore.add(ChatColor.DARK_PURPLE + "Wizard stuff is cool cuz magic blah blah...");
            wizard_lore.add(ChatColor.DARK_PURPLE + "Wizard stuff is cool cuz magic blah blah...");
            wizard_lore.add(ChatColor.DARK_PURPLE + "Wizard stuff is cool cuz magic blah blah...");
            wizard_lore.add(ChatColor.DARK_PURPLE + "Wizard stuff is cool cuz magic blah blah...");
            wizard_lore.add(ChatColor.DARK_PURPLE + "Wizard stuff is cool cuz magic blah blah...");
            wizard_lore.add(" ");
            wizard_lore.add(ChatColor.RED + "Health - 8");
            wizard_lore.add(ChatColor.BLUE + " ");
            wizard_lore.add(ChatColor.BLUE + "Weapon Proficiencies");
            wizard_lore.add(ChatColor.WHITE + "□ Light Melee");
            wizard_lore.add(ChatColor.WHITE + "□ Heavy Ranged");
            wizard_lore.add(ChatColor.BLUE + "Skill Proficiencies");
            wizard_lore.add(ChatColor.WHITE + "□ Intelligence");
            wizard_lore.add(ChatColor.WHITE + "□ Dexterity");
            wizard_lore.add(ChatColor.GREEN + "Environment");
            wizard_lore.add(ChatColor.WHITE + "□ Vorwood");
            wizard_meta.setLore(wizard_lore);
            wizard.setItemMeta(wizard_meta);

            ItemStack druid = new ItemStack(Material.APPLE, 1);

            ItemMeta druid_meta = druid.getItemMeta();
            druid_meta.setDisplayName(ChatColor.LIGHT_PURPLE + "Druid");
            ArrayList<String> druid_lore = new ArrayList<>();
            druid_lore.add(" ");
            druid_lore.add(ChatColor.DARK_PURPLE + "A magic user that takes its power from nature.");
            druid_lore.add(ChatColor.DARK_PURPLE + "A magic user that takes its power from nature.");
            druid_lore.add(ChatColor.DARK_PURPLE + "A magic user that takes its power from nature.");
            druid_lore.add(ChatColor.DARK_PURPLE + "A magic user that takes its power from nature.");
            druid_lore.add(ChatColor.DARK_PURPLE + "A magic user that takes its power from nature.");
            druid_lore.add(" ");
            druid_lore.add(ChatColor.RED + "Health - 10");
            druid_lore.add(ChatColor.BLUE + " ");
            druid_lore.add(ChatColor.BLUE + "Weapon Proficiencies");
            druid_lore.add(ChatColor.WHITE + "□ Arcane");
            druid_lore.add(ChatColor.WHITE + "□ Light Melee");
            druid_lore.add(ChatColor.BLUE + "Skill Proficiencies");
            druid_lore.add(ChatColor.WHITE + "□ Intelligence");
            druid_lore.add(ChatColor.WHITE + "□ Constitution");
            druid_lore.add(ChatColor.GREEN + "Environment");
            druid_lore.add(ChatColor.WHITE + "□ Shady Glen");
            druid_lore.add(ChatColor.WHITE + "□ Vorwood");
            druid_meta.setLore(druid_lore);
            druid.setItemMeta(druid_meta);

            ItemStack druidP = new ItemStack(Material.APPLE, 1);

            ItemMeta druidP_meta = druidP.getItemMeta();
            druidP_meta.setDisplayName(ChatColor.LIGHT_PURPLE + "Druid");
            ArrayList<String> druidP_lore = new ArrayList<>();
            druidP_lore.add(" ");
            druidP_lore.add(ChatColor.DARK_PURPLE + "Level - " + level4);
            druidP_lore.add(ChatColor.RED + "Health - " + maxHealth4);
            druidP_lore.add(ChatColor.GREEN + "Gemeralds - " + gemeralds4);
            druidP_lore.add(" ");
            druidP_lore.add(ChatColor.DARK_PURPLE + "Strength " + ChatColor.WHITE + strengthPoints4);
            druidP_lore.add(ChatColor.DARK_PURPLE + "Dexterity " + ChatColor.WHITE + dexterityPoints4);
            druidP_lore.add(ChatColor.DARK_PURPLE + "Constitution - " + ChatColor.WHITE + constitutionPoints4);
            druidP_lore.add(ChatColor.DARK_PURPLE + "Intelligence - " + ChatColor.WHITE + intelligencePoints4);
            druidP_lore.add(" ");
            druidP_lore.add(ChatColor.BLUE + "Weapon Proficiencies");
            druidP_lore.add(ChatColor.WHITE + "□ Arcane");
            druidP_lore.add(ChatColor.WHITE + "□ Light Melee");
            druidP_lore.add(ChatColor.BLUE + "Skill Proficiencies");
            druidP_lore.add(ChatColor.WHITE + "□ Intelligence");
            druidP_lore.add(ChatColor.WHITE + "□ Constitution");
            druidP_lore.add(ChatColor.GREEN + "Environment");
            druidP_lore.add(ChatColor.WHITE + "□ Shady Glen");
            druidP_lore.add(ChatColor.WHITE + "□ Vorwood");
            druidP_lore.add(ChatColor.WHITE + " ");
            druidP_lore.add(ChatColor.RED + "> RIGHT CLICK TO DELETE <");
            druidP_meta.setLore(druidP_lore);
            druidP.setItemMeta(druidP_meta);

            ItemStack placeHolder = new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1);

            ItemMeta placeHolder_meta = placeHolder.getItemMeta();
            placeHolder_meta.setDisplayName(" ");
            ArrayList<String> placeHolder_lore = new ArrayList<>();
            placeHolder_meta.setLore(placeHolder_lore);
            placeHolder.setItemMeta(placeHolder_meta);

            ItemStack[] Classgui_items = new ItemStack[9];

            if (player.hasMetadata("profileSelectionPermR"))    {
                Classgui_items[2] = rogueP;
            }
            else    {
                Classgui_items[2] = rogue;
            }
            if (player.hasMetadata("profileSelectionPermK"))   {
                Classgui_items[3] = knight;
            }
            else    {
                Classgui_items[3] = fighter;
            }
            if (player.hasMetadata("profileSelectionPermRA"))   {
                Classgui_items[4] = rangerP;
            }
            else    {
                Classgui_items[4] = ranger;
            }
            if (player.hasMetadata("profileSelectionPermW"))   {
                Classgui_items[6] = wizardP;
            }
            else    {
                Classgui_items[6] = wizard;
            }
            if (player.hasMetadata("profileSelectionPermD"))    {
                Classgui_items[5] = druidP;
            }
            else    {
                Classgui_items[5] = druid;
            }
            Classgui_items[0] = placeHolder;
            Classgui_items[1] = placeHolder;
            Classgui_items[7] = placeHolder;
            Classgui_items[8] = placeHolder;

            classes.setContents(Classgui_items);
            player.openInventory(classes);
        return true;
        }
    return true;
    }
}
