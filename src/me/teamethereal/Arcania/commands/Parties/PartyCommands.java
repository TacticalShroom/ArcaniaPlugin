package me.teamethereal.Arcania.commands.Parties;

import com.comphenix.protocol.PacketType;
import me.teamethereal.Arcania.Main;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class PartyCommands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (args[0].equals("invite")) {
                if (new File(Main.plugin.getDataFolder().getAbsolutePath() + File.separator + "Parties", p.getUniqueId() + "-Party.yml").exists())  {
                    File f = new File(Main.plugin.getDataFolder().getAbsolutePath() + File.separator + "Parties", p.getUniqueId() + "-Party.yml");
                    FileConfiguration c = YamlConfiguration.loadConfiguration(f);
                    Player invited = Main.plugin.getServer().getPlayer(args[1]);
                    if (invited == null) {
                        p.sendMessage(ChatColor.RED + args[1] + " is not a valid player or they are not online!");
                    }
                    assert invited != null;
                    if (invited.isOnline()) {
                        TextComponent clickMe = new TextComponent();
                        clickMe.setColor(net.md_5.bungee.api.ChatColor.YELLOW);
                        clickMe.setText("ClICK HERE");
                        clickMe.setBold(false);
                        clickMe.setUnderlined(true);
                        clickMe.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "party accept " + p.getName()));
                        clickMe.addExtra(ChatColor.YELLOW + " to join or do /party accept " + p.getName());
                        invited.sendMessage(ChatColor.GOLD + "______________________________________");
                        invited.sendMessage(ChatColor.GOLD + "Party Invite from " + p.getName());
                        invited.sendMessage(ChatColor.GOLD + "You have 60 seconds to accept");
                        invited.spigot().sendMessage(clickMe);
                        invited.sendMessage(ChatColor.GOLD + "______________________________________");
                        invited.setMetadata("invited", new FixedMetadataValue(Main.plugin, "invited"));
                        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, () -> {
                            if (invited.hasMetadata("invited")) {
                                invited.removeMetadata("invited", Main.plugin);
                            }
                        }, 1200);
                    } else {
                        p.sendMessage(ChatColor.RED + args[0] + " is not online!");
                    }
                }
                else    {
                    p.sendMessage(ChatColor.RED + "You must create a party first with /party create");
                }
            }
            else if (args[0].equals("create"))   {
                File f = new File(Main.plugin.getDataFolder().getAbsolutePath() + File.separator + "Parties", p.getUniqueId() + "-Party.yml");
                FileConfiguration c = YamlConfiguration.loadConfiguration(f);
                c.set("Leader", p);
                ArrayList<Player> members = new ArrayList<>();
                members.add(p);
                c.set("members", members);
                try {
                    c.save(f);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                p.sendMessage(ChatColor.GOLD + "You have created a party!");
            }
            else if (args[0].equals("accept"))  {
                if (p.hasMetadata("invited"))   {
                    if (args.length == 2)   {
                        if (Main.plugin.getServer().getPlayer(args[1]) != null) {
                            Player leader = Main.plugin.getServer().getPlayer(args[1]);
                            File f = new File(Main.plugin.getDataFolder().getAbsolutePath() + File.separator + "Parties", leader.getUniqueId() + "-Party.yml");
                            FileConfiguration c = YamlConfiguration.loadConfiguration(f);
                            ArrayList<Player> members = (ArrayList<Player>) c.get("members");
                            assert members != null;
                            for (Player player : members)   {
                                player.sendMessage(ChatColor.GOLD + "______________________________________");
                                player.sendMessage(ChatColor.GREEN + p.getName() + " has joined the party!");
                                player.sendMessage(ChatColor.GOLD + "______________________________________");
                            }
                            members.add(p);
                            c.set("members", members);
                            try {
                                c.save(f);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            p.sendMessage(ChatColor.GOLD + "______________________________________");
                            p.sendMessage(ChatColor.GREEN + "You have joined the party");
                            p.sendMessage(ChatColor.GOLD + "______________________________________");

                        }
                        else    {
                            p.sendMessage(ChatColor.RED + args[1] + " is not a valid player!");
                        }
                    }
                }
            }
        }
        return false;
    }
}
