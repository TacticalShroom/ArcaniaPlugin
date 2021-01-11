package me.teamethereal.Arcania.events;

import me.teamethereal.Arcania.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.metadata.FixedMetadataValue;

import java.io.File;
import java.io.IOException;
import java.util.Objects;


public class InventorySaver implements Listener {



    public Player player;
    public int mana;
    public String classSelection;

    @EventHandler
    public void saveInventoryEvent(PlayerQuitEvent e) throws IOException {
        player = e.getPlayer();
        int lvl1Health = 0;
        classSelection = "none";
        if (player.hasMetadata("rogue"))   {
            classSelection = "rogue";
            lvl1Health = 10;
        }
        else if (player.hasMetadata("knight")) {
            classSelection = "knight";
            lvl1Health = 14;
        }
        else if (player.hasMetadata("ranger")) {
            classSelection = "ranger";
            lvl1Health = 12;
        }
        else if (player.hasMetadata("wizard")) {
            classSelection = "wizard";
            lvl1Health = 8;
        }
        else if (player.hasMetadata("druid")) {
            classSelection = "druid";
            lvl1Health = 10;
        }
        if (classSelection.equals("none"))  return;
        this.player = (Player) e.getPlayer();
        int health = (int) player.getHealth();
        switch (classSelection) {
            case "rogue":
            case "knight":
            case "ranger":
                mana = 0;
                break;
            case "druid":
            case "wizard":
                mana = (int) Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_LUCK)).getBaseValue();
                break;
        }
        File file = new File(Main.plugin.getDataFolder().getAbsolutePath() + File.separator + "SkillPoints", player.getUniqueId() + "-" + classSelection + "skillPoints" + ".yml");
        FileConfiguration cf = YamlConfiguration.loadConfiguration(file);
        int constitutionPoints = (int) cf.get("stats.proficiencies.constitution");
        int level = player.getLevel();
        int maxHealth = (level * lvl1Health) + ((constitutionPoints * level)/2);
        int xp = (int) player.getExp() * 10;
        int maxMana = 0;
        switch (level)  {
            case (0):
            case (1):
            case (2):
            case (3):
            case (4):
                maxMana = 10;
            case (5):
            case (6):
            case (7):
            case (8):
            case (9):
                maxMana = 12;
            case (10):
            case (11):
            case (12):
            case (13):
            case (14):
                maxMana = 14;
            case (15):
            case (16):
            case (17):
            case (18):
            case (19):
                maxMana = 16;
            case (20):
            case (21):
            case (22):
            case (23):
            case (24):
                maxMana = 18;
            case (25):
            case (26):
            case (27):
            case (28):
            case (29):
                maxMana = 20;
            case (30):
            case (31):
            case (32):
            case (33):
            case (34):
                maxMana = 22;
            case (35):
            case (36):
            case (37):
            case (38):
            case (39):
                maxMana = 24;
            case (40):
            case (41):
            case (42):
            case (43):
            case (44):
                maxMana = 26;
            case (45):
            case (46):
            case (47):
            case (48):
            case (49):
                maxMana = 28;
            case (50):
            case (51):
            case (52):
            case (53):
            case (54):
                maxMana = 30;
            case (55):
            case (56):
            case (57):
            case (58):
            case (59):
                maxMana = 32;
            case (60):
            case (61):
            case (62):
            case (63):
            case (64):
                maxMana = 34;
            case (65):
            case (66):
            case (67):
            case (68):
            case (69):
                maxMana = 36;
            case (70):
                maxMana = 40;
        }

        if (player.getWorld() != Bukkit.getWorld("Lobby")) {
            World world = Bukkit.getWorld("world");

            Location spawn = new Location(world, 0.5, 4, 0.5);

            File f = new File(Main.plugin.getDataFolder().getAbsolutePath() + File.separator + "Profiles", player.getUniqueId() + "-" + classSelection + ".yml");
            FileConfiguration c = YamlConfiguration.loadConfiguration(f);
            c.set("Inventory.armor", player.getInventory().getArmorContents());
            c.set("Inventory.content", player.getInventory().getContents());
            c.set("stats.level", level);
            c.set("stats.xp", xp);
            c.set("stats.mana", mana);
            c.set("stats.maxMana", maxMana);
            c.set("stats.maxHealth", maxHealth);
            c.set("location.place", player.getLocation());
            c.set("location.world", player.getWorld().getName());
            c.save(f);
            player.getInventory().clear();
            player.setLevel(0);
            player.setExp(0);
            Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(20);
            player.teleport(spawn);

            player.removeMetadata("rogue", Main.plugin);
            player.removeMetadata("knight", Main.plugin);
            player.removeMetadata("ranger", Main.plugin);
            player.removeMetadata("druid", Main.plugin);
            player.removeMetadata("wizard", Main.plugin);
            player.removeMetadata("KnightTutorial1", Main.plugin);

            switch (classSelection) {
                case "rogue":
                    player.setMetadata("profileSelectionPermR", new FixedMetadataValue(Main.plugin, "profileSelectionPermR"));
                    break;
                case "knight":
                    player.setMetadata("profileSelectionPermK", new FixedMetadataValue(Main.plugin, "profileSelectionPermK"));
                    break;
                case "ranger":
                    player.setMetadata("profileSelectionPermRA", new FixedMetadataValue(Main.plugin, "profileSelectionPermRA"));
                    break;
                case "druid":
                    player.setMetadata("profileSelectionPermD", new FixedMetadataValue(Main.plugin, "profileSelectionPermD"));
                    break;
                case "wizard":
                    player.setMetadata("profileSelectionPermW", new FixedMetadataValue(Main.plugin, "profileSelectionPermW"));
                    break;
            }
        }

    }
    public Player getPlayer()   {
        return this.player;
    }
}
