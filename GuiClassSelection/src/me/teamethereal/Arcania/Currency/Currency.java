package me.teamethereal.Arcania.Currency;

import me.teamethereal.Arcania.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

@SuppressWarnings("deprecation")
public class Currency implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR) return;
        if (e.getCurrentItem().getItemMeta().getLocalizedName().equals("gemeralds"))    {
            e.setCancelled(true);
        }
    }

//    @EventHandler
//    public void onItemPickUp(PlayerPickupItemEvent e) throws IOException {
//        if (e.getItem().getItemStack().getType().equals(Material.EMERALD))  {
//            Player p = e.getPlayer();
//            String classSelection = "none";
//            if (p.hasMetadata("rogue"))   {
//                classSelection = "Rogue";
//            }
//            else if (p.hasMetadata("knight")) {
//                classSelection = "Knight";
//            }
//            else if (p.hasMetadata("ranger")) {
//                classSelection = "Ranger";
//            }
//            else if (p.hasMetadata("wizard")) {
//                classSelection = "Wizard";
//            }
//            else if (p.hasMetadata("druid")) {
//                classSelection = "Druid";
//            }
//            if (classSelection.equals("none"))  return;
//            File f = new File(Main.plugin.getDataFolder().getAbsolutePath() + File.separator + "Profiles", p.getUniqueId() + "-" + classSelection + ".yml");
//            YamlConfiguration c = YamlConfiguration.loadConfiguration(f);
//            int money = c.getInt("stats.gemeralds");
//            int moneyPickedUp = e.getItem().getItemStack().getAmount();
//            money += moneyPickedUp;
//            c.set("stats.gemeralds", money);
//            c.save(f);
//        }
//    }

    public void addCurrency(int i, Player p) throws IOException {
        String classSelection = "none";
        if (p.hasMetadata("rogue"))   {
            classSelection = "Rogue";
        }
        else if (p.hasMetadata("knight")) {
            classSelection = "Knight";
        }
        else if (p.hasMetadata("ranger")) {
            classSelection = "Ranger";
        }
        else if (p.hasMetadata("wizard")) {
            classSelection = "Wizard";
        }
        else if (p.hasMetadata("druid")) {
            classSelection = "Druid";
        }
        if (classSelection.equals("none"))  return;

        File file = new File(Main.plugin.getDataFolder().getAbsolutePath() + File.separator + "Profiles", p.getUniqueId() + "-" + classSelection + ".yml");
        FileConfiguration fc = YamlConfiguration.loadConfiguration(file);
        int money = fc.getInt("stats.gemeralds");
        money += i;

        String moneyG = Integer.toString(money);

        ItemStack gemeralds = new ItemStack(Material.EMERALD, 1);
        ItemMeta gM = gemeralds.getItemMeta();
        gM.setDisplayName(ChatColor.GREEN + moneyG + ChatColor.WHITE + " Gemeralds");
        gM.setLocalizedName("gemeralds");
        gM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        gM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        gM.setUnbreakable(true);
        ArrayList<String> gL = new ArrayList<>();
        gM.setLore(gL);
        gemeralds.setItemMeta(gM);
        p.getInventory().setItem(17, gemeralds);

        fc.set("stats.gemeralds", money);
        fc.save(file);
    }
}
