package me.teamethereal.Arcania.events;

import me.teamethereal.Arcania.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.metadata.FixedMetadataValue;

import java.io.File;

public class onJoin implements Listener {

    @EventHandler
    public void onJoinEvent(PlayerJoinEvent e)   {
        Player p = e.getPlayer();
        if (new File(Main.plugin.getDataFolder().getAbsolutePath() + File.separator + "Profiles", p.getUniqueId() + "-" + "rogue" + ".yml").exists()) {
            if (!p.hasMetadata("profileSelectionPermR"))    {
                p.setMetadata("profileSelectionPermR", new FixedMetadataValue(Main.plugin, "profileSelectionPermR"));
            }
        }
        if (!p.hasMetadata("profileSelectionPermK"))    {
            if (new File(Main.plugin.getDataFolder().getAbsolutePath() + File.separator + "Profiles", p.getUniqueId() + "-" + "knight" + ".yml").exists())   {
                p.setMetadata("profileSelectionPermK", new FixedMetadataValue(Main.plugin, "profileSelectionPermK"));
            }
        }
        if (new File(Main.plugin.getDataFolder().getAbsolutePath() + File.separator + "Profiles", p.getUniqueId() + "-" + "ranger" + ".yml").exists())   {
            if (!p.hasMetadata("profileSelectionPermRA"))   {
                p.setMetadata("profileSelectionPermRA", new FixedMetadataValue(Main.plugin, "profileSelectionPermRA"));
            }
        }
        if (new File(Main.plugin.getDataFolder().getAbsolutePath() + File.separator + "Profiles", p.getUniqueId() + "-" + "druid" + ".yml").exists())   {
            if (!p.hasMetadata("profileSelectionPermD"))    {
                p.setMetadata("profileSelectionPermD", new FixedMetadataValue(Main.plugin, "profileSelectionPermD"));
            }
        }
        if (new File(Main.plugin.getDataFolder().getAbsolutePath() + File.separator + "Profiles", p.getUniqueId() + "-" + "wizard" + ".yml").exists())   {
            if (!p.hasMetadata("profileSelectionPermW"))    {
                p.setMetadata("profileSelectionPermW", new FixedMetadataValue(Main.plugin, "profileSelectionPermW"));
            }
        }
    }
}