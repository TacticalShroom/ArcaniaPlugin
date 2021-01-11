package me.teamethereal.Arcania.CustomMobs;

import me.teamethereal.Arcania.Main;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class RemoveDroppedItems implements Listener {

    @EventHandler
    public void onMove(EntitySpawnEvent e) {
        if (e.getEntityType().equals(EntityType.DROPPED_ITEM)) {
            new BukkitRunnable()    {
                @Override
                public void run()   {
                    Entity entity = e.getEntity();
                    if (entity.getTicksLived() >= 400)  {
                        entity.remove();
                    }
                }
            }.runTaskTimer(Main.plugin, 1L, 1L);
        }
    }
}