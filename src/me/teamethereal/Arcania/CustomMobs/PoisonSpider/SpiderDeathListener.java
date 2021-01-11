package me.teamethereal.Arcania.CustomMobs.PoisonSpider;

import me.teamethereal.Arcania.Currency.Currency;
import me.teamethereal.Arcania.Loot.Loot;
import me.teamethereal.Arcania.Main;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

@SuppressWarnings("deprecation")
public class SpiderDeathListener implements Listener {

    Currency gems = new Currency();

    @EventHandler
    public void onEntityDeath(EntityDeathEvent e) throws IOException {
        if (e.getEntity().getKiller() != null)    {
            Player p = e.getEntity().getKiller();
            assert p != null;
            Entity entity = e.getEntity();
            if (Objects.equals(entity.getCustomName(), ChatColor.RED + "Poison Spider")) {

                Location loc = entity.getLocation();
                ArmorStand stand = e.getEntity().getWorld().spawn(loc, ArmorStand.class);
                stand.setVisible(false);
                stand.setBasePlate(false);
                stand.setArms(true);
                stand.setInvulnerable(true);
                stand.setGravity(false);

                Random rand = new Random();

                if (rand.nextInt(100) <= 33)    {
                    gems.addCurrency(rand.nextInt(6), p);
                }

                if (rand.nextInt(100) <= 10)  {
                    Loot loot = new Loot();
                    ItemStack longSword = loot.GetLongsword();

                    stand.setMetadata("loot", new FixedMetadataValue(Main.plugin, "loot"));
                    stand.setItemInHand(longSword);

                    Location beeL = new Location(stand.getWorld(), stand.getLocation().getX(), stand.getLocation().getY()+90, stand.getLocation().getZ());
                    Bee bee = (Bee) stand.getWorld().spawnEntity(beeL, EntityType.BEE);
                    bee.setAI(false);
                    bee.setInvulnerable(true);

                    new BukkitRunnable()    {
                        public void run()   {
                            if (!stand.isDead())   {
                                Location dest = new Location(bee.getWorld(), bee.getLocation().getX(), bee.getLocation().getY()-90, bee.getLocation().getZ());
                                stand.teleport(dest);
                            }
                            else    {
                                this.cancel();
                            }
                        }
                    }.runTaskTimer(Main.plugin, 0L, 1L);
                }
                else    {
                    stand.remove();
                }
            }
        }
    }

    @EventHandler
    public void onInteract(EntityDamageByEntityEvent e)   {
        if (e.getDamager() instanceof Player)   {
            Player p = (Player) e.getDamager();
            Entity entity = e.getEntity();
            if (entity.getType().equals(EntityType.ARMOR_STAND))    {
                e.setCancelled(true);
                if (entity.hasMetadata("loot")) {
                    ArrayList<ItemStack> loots = new ArrayList<>();
                    ArmorStand stand = (ArmorStand) entity;
                    loots.add(stand.getChestplate());
                    loots.add(stand.getLeggings());
                    loots.add(stand.getHelmet());
                    loots.add(stand.getBoots());
                    loots.add(stand.getItemInHand());
                    for (ItemStack loot : loots)  {
                        p.getInventory().addItem(loot);
                    }
                    entity.remove();
                }
            }
        }
    }
}