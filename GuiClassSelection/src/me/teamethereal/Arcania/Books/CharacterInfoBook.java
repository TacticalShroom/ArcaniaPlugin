package me.teamethereal.Arcania.Books;

import me.teamethereal.Arcania.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.attribute.Attribute;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
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
import org.bukkit.inventory.meta.SkullMeta;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

@SuppressWarnings("deprecation")
public class CharacterInfoBook implements Listener {

    int strengthPoints;
    int dexterityPoints;
    int intelligencePoints;
    int constitutionPoints;
    int skillPoints;

    @EventHandler
    public void onInteract(PlayerInteractEvent e)   {
        Player p = e.getPlayer();
        Inventory characterInfo = Bukkit.createInventory(p, 9, ChatColor.BLACK + "Character Info");

        String classSelection = "none";
        if (p.hasMetadata("rogue"))   {
            classSelection = "Rogue";
        }
        else if (p.hasMetadata("knight")) {
            classSelection = "Knight";
        }
        else if (p.hasMetadata("ranger")) {
            classSelection = "Ranger";
        }
        else if (p.hasMetadata("wizard")) {
            classSelection = "Wizard";
        }
        else if (p.hasMetadata("druid")) {
            classSelection = "Druid";
        }
        if (classSelection.equals("none"))  return;


        int level = p.getLevel();
        int health = (int) p.getMaxHealth();
        int mana = (int) Objects.requireNonNull(p.getAttribute(Attribute.GENERIC_LUCK)).getBaseValue();

        ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD, 1, (short) SkullType.PLAYER.ordinal());
        SkullMeta headMeta = (SkullMeta) Bukkit.getItemFactory().getItemMeta(Material.PLAYER_HEAD);
        assert headMeta != null;
        headMeta.setOwner(p.getName());
        headMeta.setDisplayName(ChatColor.ITALIC + p.getName() + "'s " + classSelection + " Stats");
        headMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        headMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        headMeta.setUnbreakable(true);
        headMeta.setLocalizedName("goToStats");
        ArrayList<String> headLore = new ArrayList<>();
        headLore.add(ChatColor.GREEN + classSelection + "Stats");
        headMeta.setLore(headLore);
        playerHead.setItemMeta(headMeta);

        ItemStack otherProfiles = new ItemStack(Material.PAPER, 1);
        ItemMeta oM = otherProfiles.getItemMeta();
        oM.setDisplayName(ChatColor.AQUA + "Other Profiles");
        oM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        oM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        oM.setUnbreakable(true);
        ArrayList<String> oL = new ArrayList<>();
        oL.add(ChatColor.GREEN + "Profile Classes:");
        if (p.hasMetadata("profileSelectionPermR") && !p.hasMetadata("rogue")) {
            oL.add(ChatColor.WHITE + "  Rogue");
        }
        if (p.hasMetadata("profileSelectionPermK") && !p.hasMetadata("knight")) {
            oL.add(ChatColor.WHITE + "  Knight");
        }
        if (p.hasMetadata("profileSelectionPermRA") && !p.hasMetadata("ranger")) {
            oL.add(ChatColor.WHITE + "  Ranger");
        }
        if (p.hasMetadata("profileSelectionPermD") && !p.hasMetadata("druid")) {
            oL.add(ChatColor.WHITE + "  Druid");
        }
        if (p.hasMetadata("profileSelectionPermW") && !p.hasMetadata("wizard")) {
            oL.add(ChatColor.WHITE + "  Wizard");
        }
        oM.setLore(oL);
        otherProfiles.setItemMeta(oM);


        ItemStack[] characterInfoItems = new ItemStack[9];
        characterInfoItems[3] = playerHead;
        characterInfoItems[5] = otherProfiles;
        characterInfo.setContents(characterInfoItems);

        if (p.getItemInHand().getType().equals(Material.AIR)) return;
        if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || e.getAction().equals(Action.RIGHT_CLICK_AIR)) {
            if (Objects.requireNonNull(p.getItemInHand().getItemMeta()).getLocalizedName().equals("characterInfo"))  {
                p.openInventory(characterInfo);
            }
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) throws IOException {
        Player p = (Player) e.getWhoClicked();
        Inventory characterInfo = Bukkit.createInventory(p, 27, ChatColor.BLACK + "Character Info");

        String classSelection = "none";
        if (p.hasMetadata("rogue"))   {
            classSelection = "Rogue";
        }
        else if (p.hasMetadata("knight")) {
            classSelection = "Knight";
        }
        else if (p.hasMetadata("ranger")) {
            classSelection = "Ranger";
        }
        else if (p.hasMetadata("wizard")) {
            classSelection = "Wizard";
        }
        else if (p.hasMetadata("druid")) {
            classSelection = "Druid";
        }
        if (classSelection.equals("none"))  return;
        File f = new File(Main.plugin.getDataFolder().getAbsolutePath() + File.separator + "SkillPoints", p.getUniqueId() + "-" + classSelection + "skillPoints" + ".yml");
        FileConfiguration c = YamlConfiguration.loadConfiguration(f);

        strengthPoints = (int) c.get("stats.proficiencies.strength");
        dexterityPoints = (int) c.get("stats.proficiencies.dexterity");
        intelligencePoints = (int) c.get("stats.proficiencies.intelligence");
        constitutionPoints = (int) c.get("stats.proficiencies.constitution");
        skillPoints = (int) c.get("stats.proficiencies.points");

        int level = p.getLevel();
        int health = (int) p.getMaxHealth();
        int mana = (int) Objects.requireNonNull(p.getAttribute(Attribute.GENERIC_LUCK)).getBaseValue();


        ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD, 1, (short) SkullType.PLAYER.ordinal());
        SkullMeta headMeta = (SkullMeta) Bukkit.getItemFactory().getItemMeta(Material.PLAYER_HEAD);
        assert headMeta != null;
        headMeta.setOwner(p.getName());
        headMeta.setDisplayName(ChatColor.ITALIC + p.getName() + "'s " + classSelection + " Stats");
        headMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        headMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        headMeta.setUnbreakable(true);
        ArrayList<String> headLore = new ArrayList<>();
        headLore.add(" ");
        headLore.add(ChatColor.RED + "Health:     " + health);
        headLore.add(ChatColor.GOLD + "Level:     " + level);
        headLore.add(ChatColor.WHITE + "PlaceHolder");
        headMeta.setLore(headLore);
        playerHead.setItemMeta(headMeta);

        ItemStack strength = new ItemStack(Material.IRON_SWORD, strengthPoints);
        ItemMeta strengthMeta = strength.getItemMeta();
        strengthMeta.setDisplayName(ChatColor.DARK_RED + "Strength");
        strengthMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        strengthMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        strengthMeta.setUnbreakable(true);
        ArrayList<String> strengthLore = new ArrayList<>();
        strengthLore.add(" ");
        if (classSelection.equals("Knight") || classSelection.equals("Ranger"))    {
            strengthLore.add(ChatColor.GREEN + "PROFICIENT");
        }
        else {
            strengthLore.add(ChatColor.RED + "Not PROFICIENT");
        }
        strengthLore.add(strengthPoints + " Strength Points");
        strengthLore.add(" ");
        strengthMeta.setLore(strengthLore);
        strength.setItemMeta(strengthMeta);

        ItemStack dexterity = new ItemStack(Material.FEATHER, dexterityPoints);
        ItemMeta dexterityMeta = dexterity.getItemMeta();
        dexterityMeta.setDisplayName(ChatColor.BLUE + "Dexterity");
        dexterityMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        dexterityMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        dexterityMeta.setUnbreakable(true);
        ArrayList<String> dexterityLore = new ArrayList<>();
        dexterityLore.add(" ");
        if (classSelection.equals("Rogue") || classSelection.equals("Ranger"))    {
            dexterityLore.add(ChatColor.GREEN + "PROFICIENT");
        }
        else {
            dexterityLore.add(ChatColor.RED + "Not PROFICIENT");
        }
        dexterityLore.add(dexterityPoints + " Dexterity Points");
        dexterityLore.add(" ");
        dexterityMeta.setLore(dexterityLore);
        dexterity.setItemMeta(dexterityMeta);

        ItemStack intelligence = new ItemStack(Material.WRITABLE_BOOK, intelligencePoints);
        ItemMeta intelligenceMeta = intelligence.getItemMeta();
        intelligenceMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Intelligence");
        intelligenceMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        intelligenceMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        intelligenceMeta.setUnbreakable(true);
        ArrayList<String> intelligenceLore = new ArrayList<>();
        intelligenceLore.add(" ");
        if (classSelection.equals("Rogue") || classSelection.equals("Wizard"))    {
            intelligenceLore.add(ChatColor.GREEN + "PROFICIENT");
        }
        else {
            intelligenceLore.add(ChatColor.RED + "Not PROFICIENT");
        }
        intelligenceLore.add(intelligencePoints + " Intelligence Points");
        intelligenceLore.add(" ");
        intelligenceMeta.setLore(intelligenceLore);
        intelligence.setItemMeta(intelligenceMeta);

        ItemStack constitution = new ItemStack(Material.IRON_CHESTPLATE, constitutionPoints);
        ItemMeta constitutionMeta = constitution.getItemMeta();
        constitutionMeta.setDisplayName(ChatColor.DARK_GREEN + "Constitution");
        constitutionMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        constitutionMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        constitutionMeta.setUnbreakable(true);
        ArrayList<String> constitutionLore = new ArrayList<>();
        constitutionLore.add(" ");
        if (classSelection.equals("Knight") || classSelection.equals("Wizard"))    {
            constitutionLore.add(ChatColor.GREEN + "PROFICIENT");
        }
        else {
            constitutionLore.add(ChatColor.RED + "Not PROFICIENT");
        }
        constitutionLore.add(constitutionPoints + "Constitution Points");
        constitutionLore.add(" ");
        constitutionMeta.setLore(constitutionLore);
        constitution.setItemMeta(constitutionMeta);

        ItemStack skillPointsInt = new ItemStack(Material.PAPER, 1);
        ItemMeta pointsMeta = skillPointsInt.getItemMeta();
        pointsMeta.setDisplayName(skillPoints + " SkillPoints");
        pointsMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        pointsMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        pointsMeta.setUnbreakable(true);
        ArrayList<String> pointsLore = new ArrayList<>();
        pointsMeta.setLore(pointsLore);
        skillPointsInt.setItemMeta(pointsMeta);



        ItemStack[] characterInfoItems = new ItemStack[27];
        characterInfoItems[4] = playerHead;
        characterInfoItems[10] = strength;
        characterInfoItems[12] = dexterity;
        characterInfoItems[14] = intelligence;
        characterInfoItems[16] = constitution;
        characterInfoItems[22] = skillPointsInt;
        characterInfo.setContents(characterInfoItems);


        String title = e.getView().getTitle();
        if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR) return;
        if (Objects.requireNonNull(Objects.requireNonNull(e.getCurrentItem()).getItemMeta()).getLocalizedName().equals("characterInfo"))    {
            e.setCancelled(true);
        }
        if (title.equals(ChatColor.BLACK + "Character Info"))   {
            e.setCancelled(true);
            if (Objects.requireNonNull(e.getCurrentItem().getItemMeta()).getLocalizedName().equals("goToStats"))    {
                p.closeInventory();
                p.openInventory(characterInfo);
                return;
            }
            if (skillPoints > 0) {
                if (e.getCurrentItem().getType().equals(Material.IRON_SWORD)) {
                    strengthPoints += 1;
                    skillPoints -= 1;

                    ItemStack strength1 = new ItemStack(Material.IRON_SWORD, strengthPoints);
                    ItemMeta strengthMeta1 = strength1.getItemMeta();
                    strengthMeta1.setDisplayName(ChatColor.DARK_RED + "Strength");
                    strengthMeta1.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
                    strengthMeta1.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                    strengthMeta1.setUnbreakable(true);
                    ArrayList<String> strengthLore1 = new ArrayList<>();
                    strengthLore1.add(" ");
                    if (classSelection.equals("Knight") || classSelection.equals("Ranger"))    {
                        strengthLore1.add(ChatColor.GREEN + "PROFICIENT");
                    }
                    else {
                        strengthLore1.add(ChatColor.RED + "Not PROFICIENT");
                    }
                    strengthLore1.add(strengthPoints + " Strength Points");
                    strengthLore1.add(" ");
                    strengthMeta1.setLore(strengthLore1);
                    strength1.setItemMeta(strengthMeta1);

                    characterInfo.setItem(10, strength1);
                    p.closeInventory();
                    p.openInventory(characterInfo);
                }
                if (e.getCurrentItem().getType().equals(Material.FEATHER))  {
                    dexterityPoints += 1;
                    skillPoints -= 1;

                    ItemStack dexterity1 = new ItemStack(Material.FEATHER, dexterityPoints);
                    ItemMeta dexterityMeta1 = dexterity1.getItemMeta();
                    dexterityMeta1.setDisplayName(ChatColor.DARK_RED + "Dexterity");
                    dexterityMeta1.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
                    dexterityMeta1.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                    dexterityMeta1.setUnbreakable(true);
                    ArrayList<String> dexterityLore1 = new ArrayList<>();
                    dexterityLore1.add(" ");
                    if (classSelection.equals("Rogue") || classSelection.equals("Ranger"))    {
                        dexterityLore1.add(ChatColor.GREEN + "PROFICIENT");
                    }
                    else {
                        dexterityLore1.add(ChatColor.RED + "Not PROFICIENT");
                    }
                    dexterityLore1.add(dexterityPoints + " Dexterity Points");
                    dexterityLore1.add(" ");
                    dexterityMeta1.setLore(dexterityLore1);
                    dexterity1.setItemMeta(dexterityMeta1);

                    characterInfo.setItem(12, dexterity1);
                    p.closeInventory();
                    p.openInventory(characterInfo);
                }
                if (e.getCurrentItem().getType().equals(Material.WRITABLE_BOOK))    {
                    intelligencePoints += 1;
                    skillPoints -= 1;

                    ItemStack intelligence1 = new ItemStack(Material.WRITABLE_BOOK, intelligencePoints);
                    ItemMeta intelligenceMeta1 = intelligence1.getItemMeta();
                    intelligenceMeta1.setDisplayName(ChatColor.DARK_RED + "Intelligence");
                    intelligenceMeta1.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
                    intelligenceMeta1.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                    intelligenceMeta1.setUnbreakable(true);
                    ArrayList<String> intelligenceLore1 = new ArrayList<>();
                    intelligenceLore1.add(" ");
                    if (classSelection.equals("Rogue") || classSelection.equals("Wizard"))    {
                        intelligenceLore1.add(ChatColor.GREEN + "PROFICIENT");
                    }
                    else {
                        intelligenceLore1.add(ChatColor.RED + "Not PROFICIENT");
                    }
                    intelligenceLore1.add(intelligencePoints + "Intelligence Points");
                    intelligenceLore1.add(" ");
                    intelligenceMeta1.setLore(intelligenceLore1);
                    intelligence1.setItemMeta(intelligenceMeta1);

                    characterInfo.setItem(14, intelligence1);
                    p.closeInventory();
                    p.openInventory(characterInfo);
                }
                if (e.getCurrentItem().getType().equals(Material.IRON_CHESTPLATE))  {
                    constitutionPoints += 1;
                    skillPoints -= 1;

                    ItemStack constitution1 = new ItemStack(Material.IRON_CHESTPLATE, constitutionPoints);
                    ItemMeta constitutionMeta1 = constitution1.getItemMeta();
                    constitutionMeta1.setDisplayName(ChatColor.DARK_RED + "Constitution");
                    constitutionMeta1.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
                    constitutionMeta1.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                    constitutionMeta1.setUnbreakable(true);
                    ArrayList<String> constitutionLore1 = new ArrayList<>();
                    constitutionLore1.add(" ");
                    if (classSelection.equals("Knight") || classSelection.equals("Wizard"))    {
                        constitutionLore1.add(ChatColor.GREEN + "PROFICIENT");
                    }
                    else {
                        constitutionLore1.add(ChatColor.RED + "Not PROFICIENT");
                    }
                    constitutionLore1.add(constitutionPoints + "Constitution Points");
                    constitutionLore1.add(" ");
                    constitutionMeta1.setLore(constitutionLore1);
                    constitution1.setItemMeta(constitutionMeta1);

                    characterInfo.setItem(16, constitution1);
                    p.closeInventory();
                    p.openInventory(characterInfo);
                }
            }
            c.set("stats.proficiencies.strength", strengthPoints);
            c.set("stats.proficiencies.dexterity", dexterityPoints);
            c.set("stats.proficiencies.intelligence", intelligencePoints);
            c.set("stats.proficiencies.constitution", constitutionPoints);
            c.set("stats.proficiencies.points", skillPoints);
            c.save(f);
        }
    }
}