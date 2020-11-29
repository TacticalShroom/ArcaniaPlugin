package me.teamethereal.Arcania.HealthAndMana;

import me.teamethereal.Arcania.Main;
import org.bukkit.ChatColor;
import org.bukkit.EntityEffect;
import org.bukkit.attribute.Attribute;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerLevelChangeEvent;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Leveling implements Listener {

    public int level;
    public String classSelection;
    int skillPoints;

    @EventHandler
    public void onLevelUp(PlayerLevelChangeEvent e) throws IOException {
        Player p = e.getPlayer();
        if (p.getLevel() == 0)  return;
        level = p.getLevel();
        classSelection = "none";
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
        if (classSelection.equals("none"))  return;
        File f = new File(Main.plugin.getDataFolder().getAbsolutePath() + File.separator + "SkillPoints", p.getUniqueId() + "-" + classSelection + "skillPoints" + ".yml");
        FileConfiguration c = YamlConfiguration.loadConfiguration(f);
        skillPoints = (int) c.get("stats.proficiencies.points");
        skillPoints += 1;
        c.set("stats.proficiencies.points", skillPoints);
        c.save(f);
        p.playEffect(EntityEffect.TOTEM_RESURRECT);
        p.sendMessage(ChatColor.DARK_PURPLE + p.getName() + ChatColor.GREEN + " REACHED " + " LEVEL " + level + "!");
        switch (classSelection) {
            case "rogue":
                int constitutionPointsR = c.getInt("stats.proficiencies.constitution");
                Objects.requireNonNull(p.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue((level * 10) + ((constitutionPointsR * level)/2));
            case "knight":
                int constitutionPointsK = c.getInt("stats.proficiencies.constitution");
                Objects.requireNonNull(p.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue((level * 14) + ((constitutionPointsK * level)/2));
                break;
            case "ranger":
                int constitutionPointsRA = c.getInt("stats.proficiencies.constitution");
                Objects.requireNonNull(p.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue((level * 12) + ((constitutionPointsRA * level)/2));
                break;
            case "druid":
                int constitutionPointsD = c.getInt("stats.proficiencies.constitution");
                Objects.requireNonNull(p.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue((level * 10) + ((constitutionPointsD * level)/2));
                break;
            case "wizard":
                int constitutionPointsW = c.getInt("stats.proficiencies.constitution");
                Objects.requireNonNull(p.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue((level * 8) + ((constitutionPointsW * level)/2));
                break;
        }
    }
    @EventHandler
    public void onEXPChange(PlayerExpChangeEvent e) {
        Player p = e.getPlayer();
        if (p.getLevel() == 70)  {
            p.setExp(0);
        }
    }
}