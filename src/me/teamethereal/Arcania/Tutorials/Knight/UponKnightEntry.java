package me.teamethereal.Arcania.Tutorials.Knight;

import me.teamethereal.Arcania.Main;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import java.util.Objects;

public class UponKnightEntry implements Listener {

    @EventHandler
    public void onTeleport(PlayerTeleportEvent e)   {
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
        if (classSelection.equals("none"))  return;
        Location destination = new Location(Bukkit.getWorld("world"), -383.5, 5, 136.5);

        if (p.getWorld().equals(Bukkit.getWorld("world")))  {
            if (classSelection.equals("knight"))   {
                if (!p.hasMetadata("KnightTutorial1"))  {
                    p.setMetadata("KnightTutorial1", new FixedMetadataValue(Main.plugin, "KnightTutorial1"));
                    PotionEffect blindness = new PotionEffect(PotionEffectType.BLINDNESS, 100, 2);
                    p.addPotionEffect(blindness);
                    p.sendMessage(ChatColor.WHITE + "You might be wondering where you are.");
                    Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, () -> {
                        p.sendMessage(ChatColor.WHITE + "This is your first academy, one of many structures that will help guide you through Arcania.");
                    }, 40);
                    Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, () -> {
                        p.sendMessage(ChatColor.WHITE + "This academy is located in the High Realm, one of Arcania's three dimensions.");
                    }, 80);
                    Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, () -> {
                        p.sendMessage(ChatColor.WHITE + " Once you complete your training you will be sent to the Mortal Realm, where you will be spending most of your time.");
                    }, 120);
                    Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, () -> {
                        p.sendMessage(ChatColor.WHITE + " Follow the passage to begin!");
                    }, 160);
                }
            }
        }
    }

    int i = 0;

    @EventHandler
    public void onMove(PlayerMoveEvent e)    {
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

        Location stringLocation1 = new Location(Bukkit.getWorld("world"), -403, 11, 148);
        Location stringLocation2 = new Location(Bukkit.getWorld("world"), -403, 11, 149);
        Location stringLocation3 = new Location(Bukkit.getWorld("world"), -403, 11, 150);
        Location stringLocation4 = new Location(Bukkit.getWorld("world"), -403, 10, 148);
        Location stringLocation5 = new Location(Bukkit.getWorld("world"), -403, 10, 149);
        Location stringLocation6 = new Location(Bukkit.getWorld("world"), -403, 10, 150);
        final Block block1 = p.getLocation().getBlock().getRelative(BlockFace.EAST);
        final Block block2 = p.getLocation().getBlock().getRelative(BlockFace.NORTH);
        final Block block3 = p.getLocation().getBlock().getRelative(BlockFace.SOUTH);
        final Block block4 = p.getLocation().getBlock().getRelative(BlockFace.WEST);
            if (p.getWorld().equals(Bukkit.getWorld("world"))) {
                if (classSelection.equals("knight"))    {
                    if (i == 0) {
                        if (block1.getType() == Material.BARRIER || block2.getType() == Material.BARRIER || block3.getType() == Material.BARRIER || block4.getType() == Material.BARRIER || block3.getType() == Material.BARRIER) {
                            if (block1.getLocation().equals(stringLocation1) || block1.getLocation().equals(stringLocation2) || block1.getLocation().equals(stringLocation3)) {

                                i = 1;

                                e.setCancelled(true);
                                PotionEffect blindness = new PotionEffect(PotionEffectType.BLINDNESS, 180, 2);
                                p.addPotionEffect(blindness);
                                p.sendMessage("Let's introduce you to your tools. In your hotbar are the questbook, the compendium, and your character info.");
                                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, () -> {
                                    p.sendMessage("Character Info - Shows your characters stats and information.");
                                }, 40);
                                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, () -> {
                                    p.sendMessage("Questbook - Outlines and tracks all your active quests.");
                                }, 80);
                                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, () -> {
                                    p.sendMessage("Compendium - Guide to all gameplay aspects.");
                                }, 120);
                                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, () -> {
                                    p.sendMessage("Click on Character Info to show your stats.");
                                }, 160);
                                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, () -> {
                                    p.setMetadata("KnightTutorial2", new FixedMetadataValue(Main.plugin, "KnightTutorial2"));
                                }, 180);
                                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, () -> {
                                    stringLocation1.getBlock().setType(Material.AIR);
                                    stringLocation2.getBlock().setType(Material.AIR);
                                    stringLocation3.getBlock().setType(Material.AIR);
                                    stringLocation4.getBlock().setType(Material.AIR);
                                    stringLocation5.getBlock().setType(Material.AIR);
                                    stringLocation6.getBlock().setType(Material.AIR);
                                }, 200);

                                i = 0;
                            }
                        }
                    }
                }
            }
    }
}
