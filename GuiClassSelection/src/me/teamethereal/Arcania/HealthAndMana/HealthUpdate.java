package me.teamethereal.Arcania.HealthAndMana;
import com.mysql.fabric.xmlrpc.base.Array;
import me.teamethereal.Arcania.Main;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

@SuppressWarnings("deprecation")
public class HealthUpdate implements Listener {

    private final ArrayList<Player> coolDown = new ArrayList<>();
    private final HashMap<Player, Integer> coolDown1 = new HashMap<>();
    public int timer = 10;

    @EventHandler
    public void onJoin(PlayerTeleportEvent e) {
        Player player = e.getPlayer();
        if (player.getWorld() == Bukkit.getWorld("Lobby"))  {
            return;
        }
        new BukkitRunnable() {
            @Override
            public void run() {
                if (!player.isOnline()) {
                    this.cancel();
                }
                int maxMana = 10;
                int mana = 0;
                int level = player.getLevel();
                String classSelection;
                classSelection = "none";
                if (player.hasMetadata("rogue"))   {
                    classSelection = "rogue";
                    mana = 0;
                }
                else if (player.hasMetadata("knight")) {
                    classSelection = "knight";
                    mana = 0;
                }
                else if (player.hasMetadata("ranger")) {
                    classSelection = "ranger";
                    mana = 0;
                }
                else if (player.hasMetadata("wizard")) {
                    classSelection = "wizard";
                    mana = (int) Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_LUCK)).getBaseValue();
                }
                else if (player.hasMetadata("druid")) {
                    classSelection = "druid";
                    mana = (int) Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_LUCK)).getBaseValue();
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
                }
                int health = (int) player.getHealth();
                int maxHealth = (int) player.getMaxHealth();
                String message = ChatColor.WHITE + "Health: " +
                        ChatColor.DARK_RED + health + ChatColor.WHITE + "/" + ChatColor.DARK_RED + maxHealth +
                        "                       " + ChatColor.WHITE + "Mana: " + ChatColor.BLUE + mana + ChatColor.WHITE + "/" + ChatColor.BLUE + maxMana;
                if (player.hasMetadata("wizard"))   {
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message
                    ));
                }
                if (player.hasMetadata("druid"))    {
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message
                    ));
                }
                if ((int) Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_LUCK)).getBaseValue() != maxMana)    {
                    int finalMana = mana;
                    Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, () -> {
                        Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_LUCK)).setBaseValue(finalMana +1);
                    }, 60);
                }
                if (!coolDown.contains(player) && classSelection.equals("knight")) {
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.DARK_RED + "❤ " + ChatColor.RED + health +
                            ChatColor.RED + "/" + ChatColor.RED + maxHealth + ChatColor.DARK_RED + " ❤" +
                            "                       " +
                            ChatColor.DARK_GREEN + "☠ " + ChatColor.GREEN + "||||||||||" + ChatColor.DARK_GREEN + " ☠"
                    ));
                }
                if (!coolDown.contains(player) && classSelection.equals("rogue")) {
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.DARK_RED + "❤ " + ChatColor.RED + health +
                            ChatColor.RED + "/" + ChatColor.RED + maxHealth + ChatColor.DARK_RED + " ❤" +
                            "                       " +
                            ChatColor.DARK_GREEN + "☠ " + ChatColor.GREEN + "||||||" + ChatColor.DARK_GREEN + " ☠"
                    ));
                }
            }
        }.runTaskTimer(Main.plugin, 0L, 2L);
    }

    @EventHandler
    public void onJoin(PlayerInteractEvent e) {
        Player player = e.getPlayer();

        int health = (int) player.getHealth();
        int maxHealth = (int) player.getMaxHealth();

        String classSelection = "none";
        if (player.hasMetadata("rogue")) {
            classSelection = "rogue";
        } else if (player.hasMetadata("knight")) {
            classSelection = "knight";
        } else if (player.hasMetadata("ranger")) {
            classSelection = "ranger";
        } else if (player.hasMetadata("wizard")) {
            classSelection = "wizard";
        } else if (player.hasMetadata("druid")) {
            classSelection = "druid";
        }
        String finalClassSelection = classSelection;
        if (player.getItemInHand().getType().equals(Material.AIR))  return;
        if (!finalClassSelection.equals("wizard") && !finalClassSelection.equals("druid")) {
            if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                if (finalClassSelection.equals("knight")) {
                    if (!coolDown.contains(player)) {
                        if (Objects.requireNonNull(player.getItemInHand().getItemMeta()).getLocalizedName().equals("melee weapon")) {
                            coolDown.add(player);
                            List<Entity> nearby = player.getNearbyEntities(3, 3, 3);
                            Location loc = player.getLocation();
                            Vector vec = loc.getDirection().multiply(0);
                            player.setVelocity(vec);
                            Location l = new Location(player.getWorld(), loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), -90);
                            Vector v = l.getDirection().multiply(3);
                            player.setVelocity(v);
                            player.setMetadata("trail", new FixedMetadataValue(Main.plugin, "trail"));
                            for (Entity tmp : nearby) {
                                if (tmp instanceof Damageable) {
                                    File file = new File(Main.plugin.getDataFolder().getAbsolutePath() + File.separator + "SkillPoints", player.getUniqueId() + "-" + classSelection + "skillPoints" + ".yml");
                                    FileConfiguration c = YamlConfiguration.loadConfiguration(file);
                                    int StrengthPoints = c.getInt("stats.proficiencies.strength");
                                    int multiplier = StrengthPoints / 2;
                                    ((Damageable) tmp).damage((Objects.requireNonNull(player.getItemInHand().getItemMeta()).getCustomModelData() * multiplier) * 2);
                                    player.playSound(player.getEyeLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 1.0f);
                                }
                            }
                            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, () -> {
                                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.DARK_RED + "❤ " + ChatColor.RED + health +
                                        ChatColor.RED + "/" + ChatColor.RED + maxHealth + ChatColor.DARK_RED + " ❤" +
                                        "                       " +
                                        ChatColor.DARK_GREEN + "☠ " + ChatColor.GRAY + "||||||||||" + ChatColor.DARK_GREEN + " ☠"
                                ));
                            }, 0);
                            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, () -> {
                                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.DARK_RED + "❤ " + ChatColor.RED + health +
                                        ChatColor.RED + "/" + ChatColor.RED + maxHealth + ChatColor.DARK_RED + " ❤" +
                                        "                       " +
                                        ChatColor.DARK_GREEN + "☠ " + ChatColor.DARK_RED + "|" + ChatColor.GRAY + "|||||||||" + ChatColor.DARK_GREEN + " ☠"
                                ));
                            }, 20);
                            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, () -> {
                                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.DARK_RED + "❤ " + ChatColor.RED + health +
                                        ChatColor.RED + "/" + ChatColor.RED + maxHealth + ChatColor.DARK_RED + " ❤" +
                                        "                       " +
                                        ChatColor.DARK_GREEN + "☠ " + ChatColor.DARK_RED + "||" + ChatColor.GRAY + "||||||||" + ChatColor.DARK_GREEN + " ☠"
                                ));
                            }, 40);
                            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, () -> {
                                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.DARK_RED + "❤ " + ChatColor.RED + health +
                                        ChatColor.RED + "/" + ChatColor.RED + maxHealth + ChatColor.DARK_RED + " ❤" +
                                        "                       " +
                                        ChatColor.DARK_GREEN + "☠ " + ChatColor.DARK_RED + "|||" + ChatColor.GRAY + "|||||||" + ChatColor.DARK_GREEN + " ☠"
                                ));
                            }, 60);
                            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, () -> {
                                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.DARK_RED + "❤ " + ChatColor.RED + health +
                                        ChatColor.RED + "/" + ChatColor.RED + maxHealth + ChatColor.DARK_RED + " ❤" +
                                        "                       " +
                                        ChatColor.DARK_GREEN + "☠ " + ChatColor.DARK_RED + "||||" + ChatColor.GRAY + "||||||" + ChatColor.DARK_GREEN + " ☠"
                                ));
                            }, 80);
                            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, () -> {
                                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.DARK_RED + "❤ " + ChatColor.RED + health +
                                        ChatColor.RED + "/" + ChatColor.RED + maxHealth + ChatColor.DARK_RED + " ❤" +
                                        "                       " +
                                        ChatColor.DARK_GREEN + "☠ " + ChatColor.DARK_RED + "|||||" + ChatColor.GRAY + "|||||" + ChatColor.DARK_GREEN + " ☠"
                                ));
                            }, 100);
                            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, () -> {
                                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.DARK_RED + "❤ " + ChatColor.RED + health +
                                        ChatColor.RED + "/" + ChatColor.RED + maxHealth + ChatColor.DARK_RED + " ❤" +
                                        "                       " +
                                        ChatColor.DARK_GREEN + "☠ " + ChatColor.DARK_RED + "||||||" + ChatColor.GRAY + "||||" + ChatColor.DARK_GREEN + " ☠"
                                ));
                            }, 120);
                            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, () -> {
                                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.DARK_RED + "❤ " + ChatColor.RED + health +
                                        ChatColor.RED + "/" + ChatColor.RED + maxHealth + ChatColor.DARK_RED + " ❤" +
                                        "                       " +
                                        ChatColor.DARK_GREEN + "☠ " + ChatColor.DARK_RED + "|||||||" + ChatColor.GRAY + "|||" + ChatColor.DARK_GREEN + " ☠"
                                ));
                            }, 140);
                            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, () -> {
                                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.DARK_RED + "❤ " + ChatColor.RED + health +
                                        ChatColor.RED + "/" + ChatColor.RED + maxHealth + ChatColor.DARK_RED + " ❤" +
                                        "                       " +
                                        ChatColor.DARK_GREEN + "☠ " + ChatColor.DARK_RED + "||||||||" + ChatColor.GRAY + "||" + ChatColor.DARK_GREEN + " ☠"
                                ));
                            }, 160);
                            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, () -> {
                                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.DARK_RED + "❤ " + ChatColor.RED + health +
                                        ChatColor.RED + "/" + ChatColor.RED + maxHealth + ChatColor.DARK_RED + " ❤" +
                                        "                       " +
                                        ChatColor.DARK_GREEN + "☠ " + ChatColor.DARK_RED + "|||||||||" + ChatColor.GRAY + "|" + ChatColor.DARK_GREEN + " ☠"
                                ));
                            }, 180);
                            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, () -> {
                                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f);
                                coolDown.remove(player);
                            }, 200);
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e)   {
        Player p = e.getPlayer();

        int health = (int) p.getHealth();
        int maxHealth = (int) p.getMaxHealth();

        String classSelection = "none";
        if (p.hasMetadata("rogue")) {
            classSelection = "rogue";
        } else if (p.hasMetadata("knight")) {
            classSelection = "knight";
        } else if (p.hasMetadata("ranger")) {
            classSelection = "ranger";
        } else if (p.hasMetadata("wizard")) {
            classSelection = "wizard";
        } else if (p.hasMetadata("druid")) {
            classSelection = "druid";
        }
        if (classSelection.equals("none")) return;
        String finalClassSelection = classSelection;
        if (p.getItemInHand().getType().equals(Material.AIR))  return;
        if (finalClassSelection.equals("rogue"))    {
            if (Objects.requireNonNull(p.getItemInHand().getItemMeta()).getLocalizedName().equals("melee weapon")) {
                if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                    if (!coolDown.contains(p))   {
                        coolDown.add(p);
                        e.setCancelled(true);
                        Location loc = p.getLocation();
                        Vector vec = loc.getDirection().multiply(0);
                        p.setVelocity(vec);
                        Location l = new Location(p.getWorld(), loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), 0);
                        Vector v = l.getDirection().multiply(5);
                        p.setVelocity(v);
                        AtomicInteger i = new AtomicInteger();
                        String finalClassSelection1 = classSelection;
                        runAbility(p, classSelection, health, maxHealth);
                        new BukkitRunnable()    {
                            public void run()   {
                                Location loc = new Location(p.getWorld(), p.getLocation().getX(), p.getEyeLocation().getY()-1, p.getLocation().getZ());
                                p.getWorld().spawnParticle(Particle.CAMPFIRE_COSY_SMOKE, loc, 3);
                                p.playSound(p.getLocation(), Sound.ENTITY_PHANTOM_FLAP, 1.0F, 1.0F);
                                if (i.get() <= 10)  {
                                    ArrayList<Entity> dashE = (ArrayList<Entity>) p.getNearbyEntities(1.5, 1.5, 1.5);
                                    dashE.remove(EntityType.ITEM_FRAME);
                                    dashE.remove(EntityType.DROPPED_ITEM);
                                    dashE.remove(EntityType.ARMOR_STAND);
                                    for (Entity mob : dashE)   {
                                        if (mob instanceof Damageable)  {
                                            File file = new File(Main.plugin.getDataFolder().getAbsolutePath() + File.separator + "SkillPoints", p.getUniqueId() + "-" + finalClassSelection1 + "skillPoints" + ".yml");
                                            FileConfiguration c = YamlConfiguration.loadConfiguration(file);
                                            int StrengthPoints = c.getInt("stats.proficiencies.strength");
                                            int multiplier = StrengthPoints / 2;
                                            ((Damageable) mob).damage((Objects.requireNonNull(p.getItemInHand().getItemMeta()).getCustomModelData() * multiplier));
                                            Location location = mob.getLocation();
                                            location.getWorld().spawnParticle(Particle.SWEEP_ATTACK, location, 1);
                                        }
                                    }
                                    i.addAndGet(1);
                                }
                                else    {
                                    this.cancel();
                                }
                            }
                        }.runTaskTimer(Main.plugin, 0L, 1L);
                    }
                }
            }
        }
    }

    public void runAbility(Player p, String classSelection, int health, int maxHealth)   {
        if (classSelection.equals("rogue")) {
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, () -> {
                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.DARK_RED + "❤ " + ChatColor.RED + health +
                        ChatColor.RED + "/" + ChatColor.RED + maxHealth + ChatColor.DARK_RED + " ❤" +
                        "                       " +
                        ChatColor.DARK_GREEN + "☠ " + ChatColor.GRAY + "||||||" + ChatColor.DARK_GREEN + " ☠"
                ));
            }, 0);
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, () -> {
                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.DARK_RED + "❤ " + ChatColor.RED + health +
                        ChatColor.RED + "/" + ChatColor.RED + maxHealth + ChatColor.DARK_RED + " ❤" +
                        "                       " +
                        ChatColor.DARK_GREEN + "☠ " + ChatColor.DARK_RED + "|" + ChatColor.GRAY + "|||||" + ChatColor.DARK_GREEN + " ☠"
                ));
            }, 20);
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, () -> {
                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.DARK_RED + "❤ " + ChatColor.RED + health +
                        ChatColor.RED + "/" + ChatColor.RED + maxHealth + ChatColor.DARK_RED + " ❤" +
                        "                       " +
                        ChatColor.DARK_GREEN + "☠ " + ChatColor.DARK_RED + "||" + ChatColor.GRAY + "||||" + ChatColor.DARK_GREEN + " ☠"
                ));
            }, 40);
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, () -> {
                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.DARK_RED + "❤ " + ChatColor.RED + health +
                        ChatColor.RED + "/" + ChatColor.RED + maxHealth + ChatColor.DARK_RED + " ❤" +
                        "                       " +
                        ChatColor.DARK_GREEN + "☠ " + ChatColor.DARK_RED + "|||" + ChatColor.GRAY + "|||" + ChatColor.DARK_GREEN + " ☠"
                ));
            }, 60);
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, () -> {
                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.DARK_RED + "❤ " + ChatColor.RED + health +
                        ChatColor.RED + "/" + ChatColor.RED + maxHealth + ChatColor.DARK_RED + " ❤" +
                        "                       " +
                        ChatColor.DARK_GREEN + "☠ " + ChatColor.DARK_RED + "||||" + ChatColor.GRAY + "||" + ChatColor.DARK_GREEN + " ☠"
                ));
            }, 80);
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, () -> {
                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.DARK_RED + "❤ " + ChatColor.RED + health +
                        ChatColor.RED + "/" + ChatColor.RED + maxHealth + ChatColor.DARK_RED + " ❤" +
                        "                       " +
                        ChatColor.DARK_GREEN + "☠ " + ChatColor.DARK_RED + "|||||" + ChatColor.GRAY + "|" + ChatColor.DARK_GREEN + " ☠"
                ));
            }, 100);
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, () -> {
                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.DARK_RED + "❤ " + ChatColor.RED + health +
                        ChatColor.RED + "/" + ChatColor.RED + maxHealth + ChatColor.DARK_RED + " ❤" +
                        "                       " +
                        ChatColor.DARK_GREEN + "☠ " + ChatColor.GREEN + "||||||" + ChatColor.GRAY + "" + ChatColor.DARK_GREEN + " ☠"
                ));
                p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f);
                coolDown.remove(p);
            }, 120);
        }
    }
}