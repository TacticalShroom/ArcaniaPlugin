package me.teamethereal.Arcania.Loot;

import com.comphenix.protocol.PacketType;
import me.teamethereal.Arcania.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

@SuppressWarnings("deprecation")
public class Loot implements Listener {

    public ItemStack GetLongsword()    {
        ItemStack longSword = new ItemStack(Material.BLACK_DYE, 1);
        ItemMeta swordMeta = longSword.getItemMeta();
        swordMeta.setDisplayName(ChatColor.GRAY + "LongSword");
        swordMeta.setUnbreakable(true);
        swordMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        swordMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        swordMeta.setCustomModelData(6);
        swordMeta.setLocalizedName("melee weapon");
        ArrayList<String> swordLore = new ArrayList<>();
        swordLore.add(ChatColor.GRAY + "--------STATS------------");
        swordLore.add(ChatColor.DARK_GREEN + "Weapon Type: " + ChatColor.WHITE + "Heavy Melee");
        swordLore.add(ChatColor.AQUA + "Attack Speed: " + ChatColor.WHITE + "Normal");
        swordLore.add(ChatColor.RED + "Attack Damage: " + ChatColor.WHITE + swordMeta.getCustomModelData());
        swordLore.add(ChatColor.YELLOW + "Ability Damage: " + ChatColor.WHITE + swordMeta.getCustomModelData()*2);
        swordLore.add(ChatColor.GRAY + "-------REQUIRED----------");
        swordLore.add(ChatColor.RED + "Strength: " + ChatColor.WHITE + "1");
        swordLore.add(ChatColor.GRAY + "-------------------------");
        swordLore.add(" ");
        swordLore.add(ChatColor.GRAY + "Rarity: " + ChatColor.WHITE + "COMMON");
        swordLore.add(" ");
        swordLore.add(ChatColor.ITALIC + "The first steps to becoming a great");
        swordLore.add(ChatColor.ITALIC + "warrior are done with a longsword...");

        return longSword;
    }
    @EventHandler
    public void onLongswordHit(EntityDamageByEntityEvent e)    {
        if (e.getDamager() instanceof Player)   {
            Player p = (Player) e.getDamager();
            Proficiencies player = new Proficiencies();
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
            File file = new File(Main.plugin.getDataFolder().getAbsolutePath() + File.separator + "SkillPoints", p.getUniqueId() + "-" + classSelection + "skillPoints" + ".yml");
            FileConfiguration c = YamlConfiguration.loadConfiguration(file);
            if (p.getItemInHand().getType().equals(Material.AIR))  return;
            if (Objects.requireNonNull(p.getItemInHand().getItemMeta()).getDisplayName().equals(ChatColor.GRAY + "LongSword"))  {
                if (player.isWeaponProficient(p, "HeavyMelee")) {
                    int StrengthPoints = c.getInt("stats.proficiencies.strength");
                    int multiplier = StrengthPoints/2;
                    e.setDamage(p.getItemInHand().getItemMeta().getCustomModelData()*multiplier);
                }
                else    {
                    int StrengthPoints = c.getInt("stats.proficiencies.strength");
                    int multiplier = StrengthPoints/2;
                    e.setDamage((float) p.getItemInHand().getItemMeta().getCustomModelData()/multiplier);
                }
            }
        }
    }
}
