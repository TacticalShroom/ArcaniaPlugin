package me.teamethereal.Arcania.events;

import me.teamethereal.Arcania.Main;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class GuiClickEvent implements Listener {

    int strengthPoints;
    int dexterityPoints;
    int intelligencePoints;
    int constitutionPoints;
    int skillPoints;

    @EventHandler(priority = EventPriority.HIGH)
    public void onClassClick(final InventoryClickEvent event) throws IOException {
        Player p = (Player) event.getWhoClicked();

        ItemStack compendium = new ItemStack(Material.BOOK, 1);
        ItemMeta compendiumMeta = compendium.getItemMeta();
        compendiumMeta.setDisplayName(ChatColor.DARK_BLUE + "Compendium");
        compendiumMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        compendiumMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        compendiumMeta.setUnbreakable(true);
        compendiumMeta.setLocalizedName("compendium");
        ArrayList<String> compendiumLore = new ArrayList<>();
        compendiumLore.add(" ");
        compendiumLore.add(ChatColor.WHITE + "This book contains all knowledge of");
        compendiumLore.add(ChatColor.WHITE + "Arcania and explanations to all ");
        compendiumMeta.setLore(compendiumLore);
        compendium.setItemMeta(compendiumMeta);

        ItemStack questBook = new ItemStack(Material.ENCHANTED_BOOK, 1);
        ItemMeta questBook_Meta = questBook.getItemMeta();
        questBook_Meta.setDisplayName(ChatColor.GOLD + "QUESTS");
        questBook_Meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        questBook_Meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        questBook_Meta.setUnbreakable(true);
        questBook_Meta.setLocalizedName("questBook");
        ArrayList<String> questBook_lore = new ArrayList<>();
        questBook_lore.add(" ");
        questBook_lore.add(ChatColor.WHITE + "Quests Complete: " + "(0/140)");
        questBook_lore.add(" ");
        questBook_Meta.setLore(questBook_lore);
        questBook.setItemMeta(questBook_Meta);

        ItemStack characterInfo = new ItemStack(Material.COMPASS, 1);
        ItemMeta characterInfo_meta = characterInfo.getItemMeta();
        characterInfo_meta.setDisplayName(ChatColor.WHITE + "Character Info");
        characterInfo_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        characterInfo_meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        characterInfo_meta.setUnbreakable(true);
        characterInfo_meta.setLocalizedName("characterInfo");
        ArrayList<String> characterInfo_lore = new ArrayList<>();
        characterInfo_lore.add(" ");
        characterInfo_lore.add(ChatColor.WHITE + "Right Click to access your character info");
        characterInfo_lore.add(" ");
        characterInfo_meta.setLore(characterInfo_lore);
        characterInfo.setItemMeta(characterInfo_meta);


        String classSelection;
        String title = event.getView().getTitle();
        if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR) return;
        if (title.startsWith(ChatColor.AQUA + "Class Selection")) {
            event.setCancelled(true);
            if (event.getCurrentItem().getType().equals(Material.LEATHER_BOOTS)) {
                if (p.hasMetadata("profileSelectionPermR")) {
                    if (event.getClick().equals(ClickType.LEFT)) {
                        p.setMetadata("rogue", new FixedMetadataValue(Main.plugin, "rogue"));

                        classSelection = "rogue";

                        p.getInventory().clear();
                        File f = new File(Main.plugin.getDataFolder().getAbsolutePath() + File.separator + "Profiles", p.getUniqueId() + "-" + classSelection + ".yml");
                        YamlConfiguration c = YamlConfiguration.loadConfiguration(f);
                        ItemStack[] content = Objects.requireNonNull(c.getList("Inventory.content")).toArray(new ItemStack[36]);
                        p.getInventory().setContents(content);
                        content = Objects.requireNonNull(c.getList("Inventory.armor")).toArray(new ItemStack[4]);
                        p.getInventory().setArmorContents(content);
                        Location location = c.getLocation("location.place");
                        String worldName = (String) c.get("location.world");
                        assert worldName != null;
                        World world = Bukkit.getWorld(worldName);
                        assert location != null;
                        double x = location.getX();
                        double y = location.getY();
                        double z = location.getZ();
                        Location pLocation = new Location(world, x, y, z);
                        p.teleport(pLocation);
                        int level = c.getInt("stats.level");
                        int xp = c.getInt("stats.xp");
                        int maxHealth = c.getInt("stats.maxHealth");
                        int mana = c.getInt("stats.mana");
                        p.setExp(xp/10);
                        Objects.requireNonNull(p.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(maxHealth);
                        p.setLevel(level);
                        p.setFoodLevel(mana);
                        p.closeInventory();
                    }
                    else if (event.getClick().equals(ClickType.RIGHT)) {
                        classSelection = "rogue";
                        p.removeMetadata("rogue", Main.plugin);
                        p.removeMetadata("profileSelectionPermR", Main.plugin);
                        File f = new File(Main.plugin.getDataFolder().getAbsolutePath() + File.separator + "Profiles", p.getUniqueId() + "-" + classSelection + ".yml");
                        File file = new File(Main.plugin.getDataFolder().getAbsolutePath() + File.separator + "SkillPoints", p.getUniqueId() + "-" + classSelection + "skillPoints" + ".yml");

                        file.delete();
                        f.delete();
                        p.sendMessage(ChatColor.DARK_RED + "Deleting Rogue profile...");
                        p.closeInventory();
                    }
                }
                else if (event.getCurrentItem().getType().equals(Material.LEATHER_BOOTS))  {
                    classSelection = "rogue";
                    p.setMetadata("profileSelectionPermR", new FixedMetadataValue(Main.plugin, "profileSelectionPermR"));
                    p.setMetadata("rogue", new FixedMetadataValue(Main.plugin, "rogue"));
                    p.sendMessage(ChatColor.GREEN + "You've created a new Rogue character!");
                    Objects.requireNonNull(p.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(10);
                    p.setLevel(1);
                    p.getInventory().clear();
                    strengthPoints = 1;
                    constitutionPoints = 1;
                    intelligencePoints = 5;
                    dexterityPoints = 5;
                    skillPoints = 0;

                    File f = new File(Main.plugin.getDataFolder().getAbsolutePath() + File.separator + "SkillPoints", p.getUniqueId() + "-" + classSelection + "skillPoints" + ".yml");
                    FileConfiguration c = YamlConfiguration.loadConfiguration(f);
                    c.set("stats.proficiencies.strength", strengthPoints);
                    c.set("stats.proficiencies.dexterity", dexterityPoints);
                    c.set("stats.proficiencies.intelligence", intelligencePoints);
                    c.set("stats.proficiencies.constitution", constitutionPoints);
                    c.set("stats.proficiencies.points", skillPoints);
                    c.save(f);

                    File file = new File(Main.plugin.getDataFolder().getAbsolutePath() + File.separator + "Profiles", p.getUniqueId() + "-" + classSelection + ".yml");
                    FileConfiguration fc = YamlConfiguration.loadConfiguration(file);
                    int money = 10;
                    fc.set("stats.gemeralds", money);
                    fc.save(file);
                    String moneyG = Integer.toString(money);

                    ItemStack gemeralds = new ItemStack(Material.EMERALD, 1);
                    ItemMeta gM = gemeralds.getItemMeta();
                    gM.setDisplayName(ChatColor.GREEN + moneyG + ChatColor.WHITE + " Gemeralds");
                    gM.setLocalizedName("gemeralds");
                    gM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
                    gM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                    gM.setUnbreakable(true);
                    ArrayList<String> gL = new ArrayList<>();
                    gM.setLore(gL);
                    gemeralds.setItemMeta(gM);

                    p.getInventory().setItem(17, gemeralds);
                    p.getInventory().setItem(8, compendium);
                    p.getInventory().setItem(7, questBook);
                    p.getInventory().setItem(6, characterInfo);
                    Location location = new Location(Bukkit.getWorld("world"), -383.5, 5, 136.5);
                    p.teleport(location);
                    p.closeInventory();
                }
            }
            else if (event.getCurrentItem().getType().equals(Material.IRON_AXE)) {
                if (p.hasMetadata("profileSelectionPermK")) {
                    if (event.getClick().equals(ClickType.LEFT)) {
                        p.setMetadata("knight", new FixedMetadataValue(Main.plugin, "knight"));

                        classSelection = "knight";

                        p.getInventory().clear();
                        File f = new File(Main.plugin.getDataFolder().getAbsolutePath() + File.separator + "Profiles", p.getUniqueId() + "-" + classSelection + ".yml");
                        YamlConfiguration c = YamlConfiguration.loadConfiguration(f);
                        ItemStack[] content = Objects.requireNonNull(c.getList("Inventory.content")).toArray(new ItemStack[36]);
                        p.getInventory().setContents(content);
                        content = Objects.requireNonNull(c.getList("Inventory.armor")).toArray(new ItemStack[4]);
                        p.getInventory().setArmorContents(content);
                        Location location = c.getLocation("location.place");
                        String worldName = (String) c.get("location.world");
                        assert worldName != null;
                        World world = Bukkit.getWorld(worldName);
                        assert location != null;
                        double x = location.getX();
                        double y = location.getY();
                        double z = location.getZ();
                        Location pLocation = new Location(world, x, y, z);
                        p.teleport(pLocation);
                        int level = c.getInt("stats.level");
                        int xp = c.getInt("stats.xp");
                        int maxHealth = c.getInt("stats.maxHealth");
                        int mana = c.getInt("stats.mana");
                        p.setExp(xp/10);
                        Objects.requireNonNull(p.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(maxHealth);
                        p.setLevel(level);
                        p.setFoodLevel(mana);
                        p.closeInventory();
                    }
                    else if (event.getClick().equals(ClickType.RIGHT)) {
                        classSelection = "knight";
                        p.removeMetadata("knight", Main.plugin);
                        p.removeMetadata("profileSelectionPermK", Main.plugin);
                        File f = new File(Main.plugin.getDataFolder().getAbsolutePath() + File.separator + "Profiles", p.getUniqueId() + "-" + classSelection + ".yml");
                        File file = new File(Main.plugin.getDataFolder().getAbsolutePath() + File.separator + "SkillPoints", p.getUniqueId() + "-" + classSelection + "skillPoints" + ".yml");
                        file.delete();
                        f.delete();
                        p.sendMessage(ChatColor.DARK_RED + "Deleting Knight profile...");
                        p.closeInventory();
                    }
                }
                else if (event.getCurrentItem().getType().equals(Material.IRON_AXE)) {
                    classSelection = "knight";
                    p.setMetadata("profileSelectionPermK", new FixedMetadataValue(Main.plugin, "profileSelectionPermK"));
                    p.setMetadata("knight", new FixedMetadataValue(Main.plugin, "knight"));
                    p.sendMessage(ChatColor.GREEN + "You've created a new Knight character!");
                    Objects.requireNonNull(p.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(14);
                    p.setLevel(1);
                    p.getInventory().clear();
                    strengthPoints = 5;
                    constitutionPoints = 5;
                    dexterityPoints = 1;
                    intelligencePoints = 1;
                    skillPoints = 0;

                    File f = new File(Main.plugin.getDataFolder().getAbsolutePath() + File.separator + "SkillPoints", p.getUniqueId() + "-" + classSelection + "skillPoints" + ".yml");
                    FileConfiguration c = YamlConfiguration.loadConfiguration(f);
                    c.set("stats.proficiencies.strength", strengthPoints);
                    c.set("stats.proficiencies.dexterity", dexterityPoints);
                    c.set("stats.proficiencies.intelligence", intelligencePoints);
                    c.set("stats.proficiencies.constitution", constitutionPoints);
                    c.set("stats.proficiencies.points", skillPoints);
                    c.save(f);

                    File file = new File(Main.plugin.getDataFolder().getAbsolutePath() + File.separator + "Profiles", p.getUniqueId() + "-" + classSelection + ".yml");
                    FileConfiguration fc = YamlConfiguration.loadConfiguration(file);
                    int money = 10;
                    fc.set("stats.gemeralds", money);
                    fc.save(file);
                    String moneyG =  Integer.toString(money);

                    ItemStack gemeralds = new ItemStack(Material.EMERALD, 1);
                    ItemMeta gM = gemeralds.getItemMeta();
                    gM.setDisplayName(ChatColor.GREEN + moneyG + ChatColor.WHITE + " Gemeralds");
                    gM.setLocalizedName("gemeralds");
                    gM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
                    gM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                    gM.setUnbreakable(true);
                    ArrayList<String> gL = new ArrayList<>();
                    gM.setLore(gL);
                    gemeralds.setItemMeta(gM);

                    p.getInventory().setItem(17, gemeralds);

                    p.getInventory().setItem(8, compendium);
                    p.getInventory().setItem(7, questBook);
                    p.getInventory().setItem(6, characterInfo);
                    Location location = new Location(Bukkit.getWorld("world"), -383.5, 5, 136.5);
                    p.teleport(location);
                    p.closeInventory();
                }
            }
            else if (event.getCurrentItem().getType().equals(Material.BOW)) {
                if (p.hasMetadata("profileSelectionPermRA")) {
                    if (event.getClick().equals(ClickType.LEFT)) {
                        p.setMetadata("ranger", new FixedMetadataValue(Main.plugin, "ranger"));

                        classSelection = "ranger";

                        p.getInventory().clear();
                        File f = new File(Main.plugin.getDataFolder().getAbsolutePath() + File.separator + "Profiles", p.getUniqueId() + "-" + classSelection + ".yml");
                        YamlConfiguration c = YamlConfiguration.loadConfiguration(f);
                        ItemStack[] content = Objects.requireNonNull(c.getList("Inventory.content")).toArray(new ItemStack[36]);
                        p.getInventory().setContents(content);
                        content = Objects.requireNonNull(c.getList("Inventory.armor")).toArray(new ItemStack[4]);
                        p.getInventory().setArmorContents(content);
                        Location location = c.getLocation("location.place");
                        String worldName = (String) c.get("location.world");
                        assert worldName != null;
                        World world = Bukkit.getWorld(worldName);
                        assert location != null;
                        double x = location.getX();
                        double y = location.getY();
                        double z = location.getZ();
                        Location pLocation = new Location(world, x, y, z);
                        p.teleport(pLocation);
                        int level = c.getInt("stats.level");
                        int xp = c.getInt("stats.xp");

                        int maxHealth = c.getInt("stats.maxHealth");
                        int mana = c.getInt("stats.mana");
                        p.setExp(xp/10);
                        Objects.requireNonNull(p.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(maxHealth);
                        p.setLevel(level);

                        p.setFoodLevel(mana);
                        p.closeInventory();
                    }
                    else if (event.getClick().equals(ClickType.RIGHT)) {
                        classSelection = "ranger";
                        p.removeMetadata("ranger", Main.plugin);
                        p.removeMetadata("profileSelectionPermRA", Main.plugin);
                        File f = new File(Main.plugin.getDataFolder().getAbsolutePath() + File.separator + "Profiles", p.getUniqueId() + "-" + classSelection + ".yml");
                        f.delete();
                        File file = new File(Main.plugin.getDataFolder().getAbsolutePath() + File.separator + "SkillPoints", p.getUniqueId() + "-" + classSelection + "skillPoints" + ".yml");
                        file.delete();
                        p.sendMessage(ChatColor.DARK_RED + "Deleting Ranger profile...");
                        p.closeInventory();
                    }
                }
                else if (event.getCurrentItem().getType().equals(Material.BOW)) {
                    classSelection = "ranger";
                    p.setMetadata("profileSelectionPermRA", new FixedMetadataValue(Main.plugin, "profileSelectionPermRA"));
                    p.setMetadata("ranger", new FixedMetadataValue(Main.plugin, "ranger"));
                    p.sendMessage(ChatColor.GREEN + "You've created a new Ranger character!");
                    Objects.requireNonNull(p.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(12);
                    p.getInventory().clear();
                    strengthPoints = 1;
                    dexterityPoints = 5;
                    intelligencePoints = 1;
                    constitutionPoints = 5;
                    skillPoints = 0;

                    File f = new File(Main.plugin.getDataFolder().getAbsolutePath() + File.separator + "SkillPoints", p.getUniqueId() + "-" + classSelection + "skillPoints" + ".yml");
                    FileConfiguration c = YamlConfiguration.loadConfiguration(f);
                    c.set("stats.proficiencies.strength", strengthPoints);
                    c.set("stats.proficiencies.dexterity", dexterityPoints);
                    c.set("stats.proficiencies.intelligence", intelligencePoints);
                    c.set("stats.proficiencies.constitution", constitutionPoints);
                    c.set("stats.proficiencies.points", skillPoints);
                    c.save(f);

                    File file = new File(Main.plugin.getDataFolder().getAbsolutePath() + File.separator + "Profiles", p.getUniqueId() + "-" + classSelection + ".yml");
                    FileConfiguration fc = YamlConfiguration.loadConfiguration(file);
                    int money = 10;
                    fc.set("stats.gemeralds", money);
                    fc.save(file);
                    String moneyG =  Integer.toString(money);

                    ItemStack gemeralds = new ItemStack(Material.EMERALD, 1);
                    ItemMeta gM = gemeralds.getItemMeta();
                    gM.setDisplayName(ChatColor.GREEN + moneyG + ChatColor.WHITE + " Gemeralds");
                    gM.setLocalizedName("gemeralds");
                    gM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
                    gM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                    gM.setUnbreakable(true);
                    ArrayList<String> gL = new ArrayList<>();
                    gM.setLore(gL);
                    gemeralds.setItemMeta(gM);

                    p.getInventory().setItem(17, gemeralds);
                    p.getInventory().setItem(8, compendium);
                    p.getInventory().setItem(7, questBook);
                    p.getInventory().setItem(6, characterInfo);
                    Location location = new Location(Bukkit.getWorld("world"), -383.5, 5, 136.5);
                    p.teleport(location);
                    p.closeInventory();
                    // teleport to ranger tutorial...
                }
            }
            else if (event.getCurrentItem().getType().equals(Material.APPLE)) {
                if (p.hasMetadata("profileSelectionPermD")) {
                    if (event.getClick().equals(ClickType.LEFT)) {
                        p.setMetadata("druid", new FixedMetadataValue(Main.plugin, "druid"));

                        classSelection = "druid";

                        p.getInventory().clear();
                        File f = new File(Main.plugin.getDataFolder().getAbsolutePath() + File.separator + "Profiles", p.getUniqueId() + "-" + classSelection + ".yml");
                        YamlConfiguration c = YamlConfiguration.loadConfiguration(f);
                        ItemStack[] content = Objects.requireNonNull(c.getList("Inventory.content")).toArray(new ItemStack[36]);
                        p.getInventory().setContents(content);
                        content = Objects.requireNonNull(c.getList("Inventory.armor")).toArray(new ItemStack[4]);
                        p.getInventory().setArmorContents(content);
                        Location location = c.getLocation("location.place");
                        String worldName = (String) c.get("location.world");
                        assert worldName != null;
                        World world = Bukkit.getWorld(worldName);
                        assert location != null;
                        double x = location.getX();
                        double y = location.getY();
                        double z = location.getZ();
                        Location pLocation = new Location(world, x, y, z);
                        p.teleport(pLocation);
                        int level = c.getInt("stats.level");
                        int xp = c.getInt("stats.xp");

                        int maxHealth = c.getInt("stats.maxHealth");
                        int mana = c.getInt("stats.mana");
                        p.setExp(xp/10);
                        Objects.requireNonNull(p.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(maxHealth);
                        p.setLevel(level);

                        p.setFoodLevel(mana);
                        p.closeInventory();
                    }
                    else if (event.getClick().equals(ClickType.RIGHT)) {
                        classSelection = "druid";
                        p.removeMetadata("druid", Main.plugin);
                        p.removeMetadata("profileSelectionPermD", Main.plugin);
                        File f = new File(Main.plugin.getDataFolder().getAbsolutePath() + File.separator + "Profiles", p.getUniqueId() + "-" + classSelection + ".yml");
                        f.delete();
                        File file = new File(Main.plugin.getDataFolder().getAbsolutePath() + File.separator + "SkillPoints", p.getUniqueId() + "-" + classSelection + "skillPoints" + ".yml");
                        file.delete();
                        p.sendMessage(ChatColor.DARK_RED + "Deleting Druid profile...");
                        p.closeInventory();
                    }
                }
                else if (event.getCurrentItem().getType().equals(Material.APPLE)) {
                    classSelection = "druid";
                    p.setMetadata("profileSelectionPermD", new FixedMetadataValue(Main.plugin, "profileSelectionPermD"));
                    p.setMetadata("druid", new FixedMetadataValue(Main.plugin, "druid"));
                    p.sendMessage(ChatColor.GREEN + "You've created a new Druid character!");
                    Objects.requireNonNull(p.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(10);
                    p.getInventory().clear();
                    strengthPoints = 1;
                    dexterityPoints = 1;
                    intelligencePoints = 5;
                    constitutionPoints = 5;
                    skillPoints = 0;

                    File f = new File(Main.plugin.getDataFolder().getAbsolutePath() + File.separator + "SkillPoints", p.getUniqueId() + "-" + classSelection + "skillPoints" + ".yml");
                    FileConfiguration c = YamlConfiguration.loadConfiguration(f);
                    c.set("stats.proficiencies.strength", strengthPoints);
                    c.set("stats.proficiencies.dexterity", dexterityPoints);
                    c.set("stats.proficiencies.intelligence", intelligencePoints);
                    c.set("stats.proficiencies.constitution", constitutionPoints);
                    c.set("stats.proficiencies.points", skillPoints);
                    c.save(f);

                    File file = new File(Main.plugin.getDataFolder().getAbsolutePath() + File.separator + "Profiles", p.getUniqueId() + "-" + classSelection + ".yml");
                    FileConfiguration fc = YamlConfiguration.loadConfiguration(file);
                    int money = 10;
                    fc.set("stats.gemeralds", money);
                    fc.save(file);
                    String moneyG =  Integer.toString(money);

                    ItemStack gemeralds = new ItemStack(Material.EMERALD, 1);
                    ItemMeta gM = gemeralds.getItemMeta();
                    gM.setDisplayName(ChatColor.GREEN + moneyG + ChatColor.WHITE + " Gemeralds");
                    gM.setLocalizedName("gemeralds");
                    gM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
                    gM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                    gM.setUnbreakable(true);
                    ArrayList<String> gL = new ArrayList<>();
                    gM.setLore(gL);
                    gemeralds.setItemMeta(gM);

                    p.getInventory().setItem(17, gemeralds);
                    p.getInventory().setItem(8, compendium);
                    p.getInventory().setItem(7, questBook);
                    p.getInventory().setItem(6, characterInfo);
                    Location location = new Location(Bukkit.getWorld("world"), -383.5, 5, 136.5);
                    p.teleport(location);
                    p.closeInventory();
                    // teleport to druid tutorial...
                }
            }
            else if (event.getCurrentItem().getType().equals(Material.END_CRYSTAL))    {
                if (p.hasMetadata("profileSelectionPermW")) {
                    if (event.getClick().equals(ClickType.LEFT))   {
                        p.setMetadata("wizard", new FixedMetadataValue(Main.plugin, "wizard"));

                        classSelection = "wizard";

                        p.getInventory().clear();
                        File f = new File(Main.plugin.getDataFolder().getAbsolutePath() + File.separator + "Profiles", p.getUniqueId() + "-" + classSelection + ".yml");
                        YamlConfiguration c = YamlConfiguration.loadConfiguration(f);
                        ItemStack[] content = Objects.requireNonNull(c.getList("Inventory.content")).toArray(new ItemStack[36]);
                        p.getInventory().setContents(content);
                        content = Objects.requireNonNull(c.getList("Inventory.armor")).toArray(new ItemStack[4]);
                        p.getInventory().setArmorContents(content);
                        Location location = c.getLocation("location.place");
                        String worldName = (String) c.get("location.world");
                        assert worldName != null;
                        World world = Bukkit.getWorld(worldName);
                        assert location != null;
                        double x = location.getX();
                        double y = location.getY();
                        double z = location.getZ();
                        Location pLocation = new Location(world, x, y, z);
                        p.teleport(pLocation);
                        int level = c.getInt("stats.level");
                        int xp = c.getInt("stats.xp");

                        int maxHealth = c.getInt("stats.maxHealth");
                        int mana = c.getInt("stats.mana");
                        p.setExp(xp/10);
                        Objects.requireNonNull(p.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(maxHealth);
                        p.setLevel(level);

                        p.setFoodLevel(mana);
                        p.closeInventory();
                    }
                    else if (event.getClick().equals(ClickType.RIGHT))  {
                        classSelection = "wizard";
                        p.removeMetadata("wizard", Main.plugin);
                        p.removeMetadata("profileSelectionPermW", Main.plugin);
                        File f = new File(Main.plugin.getDataFolder().getAbsolutePath() + File.separator + "Profiles", p.getUniqueId() + "-" + classSelection + ".yml");
                        f.delete();
                        File file = new File(Main.plugin.getDataFolder().getAbsolutePath() + File.separator + "SkillPoints", p.getUniqueId() + "-" + classSelection + "skillPoints" + ".yml");
                        file.delete();
                        p.sendMessage(ChatColor.DARK_RED + "Deleting Wizard profile...");
                        p.closeInventory();
                        p.setMetadata("wizard", new FixedMetadataValue(Main.plugin, "wizard"));
                    }
                }
                else if (event.getCurrentItem().getType().equals(Material.END_CRYSTAL))    {
                    classSelection = "wizard";
                    p.setMetadata("profileSelectionPermW", new FixedMetadataValue(Main.plugin, "profileSelectionPermW"));
                    p.setMetadata("wizard", new FixedMetadataValue(Main.plugin, "wizard"));
                    p.sendMessage(ChatColor.GREEN + "You've created a new wizard character!");
                    p.getInventory().clear();
                    strengthPoints = 1;
                    dexterityPoints = 5;
                    intelligencePoints = 5;
                    constitutionPoints = 1;
                    skillPoints = 0;

                    File f = new File(Main.plugin.getDataFolder().getAbsolutePath() + File.separator + "SkillPoints", p.getUniqueId() + "-" + classSelection + "skillPoints" + ".yml");
                    FileConfiguration c = YamlConfiguration.loadConfiguration(f);
                    c.set("stats.proficiencies.strength", strengthPoints);
                    c.set("stats.proficiencies.dexterity", dexterityPoints);
                    c.set("stats.proficiencies.intelligence", intelligencePoints);
                    c.set("stats.proficiencies.constitution", constitutionPoints);
                    c.set("stats.proficiencies.points", skillPoints);
                    c.save(f);

                    File file = new File(Main.plugin.getDataFolder().getAbsolutePath() + File.separator + "Profiles", p.getUniqueId() + "-" + classSelection + ".yml");
                    FileConfiguration fc = YamlConfiguration.loadConfiguration(file);
                    int money = 10;
                    fc.set("stats.gemeralds", money);
                    fc.save(file);
                    String moneyG =  Integer.toString(money);

                    ItemStack gemeralds = new ItemStack(Material.EMERALD, 1);
                    ItemMeta gM = gemeralds.getItemMeta();
                    gM.setDisplayName(ChatColor.GREEN + moneyG + ChatColor.WHITE + " Gemeralds");
                    gM.setLocalizedName("gemeralds");
                    gM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
                    gM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                    gM.setUnbreakable(true);
                    ArrayList<String> gL = new ArrayList<>();
                    gM.setLore(gL);
                    gemeralds.setItemMeta(gM);

                    p.getInventory().setItem(17, gemeralds);
                    p.getInventory().setItem(8, compendium);
                    p.getInventory().setItem(7, questBook);
                    p.getInventory().setItem(6, characterInfo);
                    Location location = new Location(Bukkit.getWorld("world"), -383.5, 5, 136.5);
                    p.teleport(location);
                    p.closeInventory();
                    Objects.requireNonNull(p.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(8);
                }
            }
        }
    }
}