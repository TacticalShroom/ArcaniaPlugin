import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Dialogue {
    public void dialogue(Player p, String i1, String i2, String i3, String i4, String i5){
        ItemStack opt1 = new ItemStack(Material.BRICK, 1);
        ItemStack opt2 = new ItemStack(Material.BRICK, 1);
        ItemStack opt3 = new ItemStack(Material.BRICK, 1);
        ItemStack opt4 = new ItemStack(Material.BRICK, 1);
        ItemStack opt5 = new ItemStack(Material.BRICK, 1);

        ItemMeta opt1_meta = opt1.getItemMeta();
        ItemMeta opt2_meta = opt2.getItemMeta();
        ItemMeta opt3_meta = opt3.getItemMeta();
        ItemMeta opt4_meta = opt4.getItemMeta();
        ItemMeta opt5_meta = opt5.getItemMeta();

        opt1_meta.setDisplayName(i1);
        opt2_meta.setDisplayName(i2);
        opt3_meta.setDisplayName(i3);
        opt4_meta.setDisplayName(i4);
        opt5_meta.setDisplayName(i5);

        opt1.setItemMeta(opt1_meta);
        opt2.setItemMeta(opt2_meta);
        opt3.setItemMeta(opt3_meta);
        opt4.setItemMeta(opt4_meta);
        opt5.setItemMeta(opt5_meta);

        Inventory box = Bukkit.createInventory(p, InventoryType.HOPPER, "");

        if(!i1.equals("")){
            box.setItem(0, opt1);
        }
        if(!i2.equals("")){
            box.setItem(1, opt2);
        }
        if(!i3.equals("")){
            box.setItem(2, opt3);
        }
        if(!i4.equals("")){
            box.setItem(3, opt4);
        }
        if(!i5.equals("")){
            box.setItem(4, opt5);
        }
    }
}