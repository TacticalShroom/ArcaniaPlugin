package me.teamethereal.Arcania.CustomMobs;

import me.teamethereal.Arcania.CustomMobs.PoisonSpider.CustomSpider;
import me.teamethereal.Arcania.Main;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class SpawnListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (new File(Main.plugin.getDataFolder().getAbsolutePath() + File.separator + "SpawnLocations", "spider" + "SpawningLocations" + ".yml").exists()) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    File f = new File(Main.plugin.getDataFolder().getAbsolutePath() + File.separator + "SpawnLocations", "spider" + "SpawningLocations" + ".yml");
                    FileConfiguration c = YamlConfiguration.loadConfiguration(f);
                    ArrayList<Location> spawnLocations = (ArrayList<Location>) c.get("locations");
                    assert spawnLocations != null;
                    for (Location spawnLocation : spawnLocations) {
                        ArrayList<Entity> nearbyEntities1 = (ArrayList<Entity>) Objects.requireNonNull(spawnLocation.getWorld()).getNearbyEntities(spawnLocation, 50, 50, 50);
                        if (!nearbyEntities1.contains(p)) {
                            if (!p.isOnline()) {
                                this.cancel();
                            }
                        } else {
                            ArrayList<Entity> nearbyEntities = (ArrayList<Entity>) p.getNearbyEntities(80, 80, 80);
                            nearbyEntities.remove(EntityType.DROPPED_ITEM);

                            if (nearbyEntities.size() > 50) return;

                            Random rand = new Random();
                            if (rand.nextInt(100) <= 10) {
                                Location spawnLocation1 = new Location(spawnLocation.getWorld(), spawnLocation.getX() + 0.5, spawnLocation.getY() + 1.1, spawnLocation.getZ() + 0.5);
                                CustomSpider spider = new CustomSpider(spawnLocation1);
                                spider.spawnSpider(spawnLocation1);
                            }
                        }
                    }
                }
            }.runTaskTimer(Main.plugin, 0L, 100L);
        }
    }
}
