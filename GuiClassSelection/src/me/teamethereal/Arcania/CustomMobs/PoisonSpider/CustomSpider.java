package me.teamethereal.Arcania.CustomMobs.PoisonSpider;

import net.minecraft.server.v1_16_R2.*;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_16_R2.CraftWorld;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class CustomSpider extends EntitySpider {

    public CustomSpider(Location loc) {
        super(EntityTypes.SPIDER, ((CraftWorld) Objects.requireNonNull(loc.getWorld())).getHandle());
        this.setPosition(loc.getX(), loc.getY(), loc.getZ());

        this.setHealth(2);
        this.setCustomNameVisible(true);
        this.setCustomName(new ChatComponentText(ChatColor.RED + "Poison Spider"));
        this.setPersistent();
    }

    public void initPathfinder()    {
        super.initPathfinder();
        this.goalSelector.a(0, new PathfinderGoalNearestAttackableTarget<EntityHuman>(this, EntityHuman.class, true));
        this.goalSelector.a(1, new PathfinderGoalRandomStrollLand(this, 1));
    }

    public void spawnSpider(Location l) {
        List<CustomSpider> spiders = Collections.singletonList(
                new CustomSpider(l)
        );
        WorldServer worldServer = ((CraftWorld) Objects.requireNonNull(l.getWorld())).getHandle();
        for (CustomSpider spider : spiders) {
            worldServer.addEntity(spider);
        }
    }

}
