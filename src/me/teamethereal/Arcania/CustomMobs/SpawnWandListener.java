package me.teamethereal.Arcania.CustomMobs;

import me.teamethereal.Arcania.Main;
import net.minecraft.server.v1_16_R2.BlockBase;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;

@SuppressWarnings("deprecation")
public class SpawnWandListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) throws IOException {
        if (Objects.equals(e.getHand(), EquipmentSlot.OFF_HAND)) return;
        Player p = e.getPlayer();
        if (p.getItemInHand().getType().equals(Material.AIR))  return;
        if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (Objects.requireNonNull(p.getItemInHand().getItemMeta()).getLocalizedName().equals("spider")) {
                Location l = Objects.requireNonNull(e.getClickedBlock()).getLocation();
                String mob = p.getItemInHand().getItemMeta().getLocalizedName();
                File f = new File(Main.plugin.getDataFolder().getAbsolutePath() + File.separator + "SpawnLocations", mob + "SpawningLocations" + ".yml");
                FileConfiguration c = YamlConfiguration.loadConfiguration(f);
                ArrayList<Location> spawnLocations;
                if (c.contains("locations"))    {
                    spawnLocations = (ArrayList<Location>) c.get("locations");
                }
                else {
                    spawnLocations = new ArrayList<>();
                }
                assert spawnLocations != null;
                if (!spawnLocations.contains(l))    {
                    spawnLocations.add(l);
                    p.sendMessage(ChatColor.GREEN + mob.toUpperCase() + " Spawn Location is set to " + "(" + l.getX() + "," + l.getY() + "," + l.getZ() + ")");
                }
                else    {
                    p.sendMessage(ChatColor.RED + "This is already a spawning location for this entity!");
                }
                c.set("locations", spawnLocations);
                c.save(f);
            }
        }
        else if (e.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
            if (Objects.requireNonNull(p.getItemInHand().getItemMeta()).getLocalizedName().equals("spider")) {
                e.setCancelled(true);
                Location l = Objects.requireNonNull(e.getClickedBlock()).getLocation();
                String mob = p.getItemInHand().getItemMeta().getLocalizedName();
                File f = new File(Main.plugin.getDataFolder().getAbsolutePath() + File.separator + "SpawnLocations", mob + "SpawningLocations" + ".yml");
                FileConfiguration c = YamlConfiguration.loadConfiguration(f);
                ArrayList<Location> spawnLocations = (ArrayList<Location>) c.get("locations");
                assert spawnLocations != null;
                if (spawnLocations.contains(l)) {
                    p.sendMessage(ChatColor.DARK_RED + "Removing spawn location.");
                    spawnLocations.remove(l);
                }
                else    {
                    p.sendMessage(ChatColor.DARK_RED + "This is not a spawn location.");
                }

                c.set("locations", spawnLocations);
                c.save(f);
            }
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e)    {
        Player p = e.getPlayer();
        if (e.getItemDrop().getItemStack().getItemMeta().hasCustomModelData())  {
            if (Objects.requireNonNull(e.getItemDrop().getItemStack().getItemMeta()).getCustomModelData() == 20) {
                e.setCancelled(true);
                String mob = "broken";
                if (Objects.requireNonNull(e.getItemDrop().getItemStack().getItemMeta()).hasLocalizedName()) {
                    mob = e.getItemDrop().getItemStack().getItemMeta().getLocalizedName();
                }
                File f = new File(Main.plugin.getDataFolder().getAbsolutePath() + File.separator + "SpawnLocations", mob + "SpawningLocations" + ".yml");
                FileConfiguration c = YamlConfiguration.loadConfiguration(f);
                ArrayList<Location> spawnLocations = (ArrayList<Location>) c.get("locations");
                if (!p.hasMetadata("spawnLocations"))   {
                    p.setMetadata("spawnLocations", new FixedMetadataValue(Main.plugin, "spawnLocations"));
                    assert spawnLocations != null;
                    for (Location spawnLocation : spawnLocations)   {
                        new BukkitRunnable()    {
                            @Override
                            public void run()   {
                                if (p.hasMetadata("spawnLocations"))    {
                                    World world = Bukkit.getWorld("world");
                                    Location spawnLocation1 = new Location(world, spawnLocation.getX()+0.5, spawnLocation.getY()+1, spawnLocation.getZ()+0.5);
                                    p.spawnParticle(Particle.SMOKE_NORMAL, spawnLocation1, 0);
                                }
                                else    {
                                    this.cancel();
                                }
                            }
                        }.runTaskTimer(Main.plugin, 0, 5);
                    }
                }
                else   {
                    p.removeMetadata("spawnLocations", Main.plugin);
                }
            }
            PlayerInteractEvent event;
        }
    }
}