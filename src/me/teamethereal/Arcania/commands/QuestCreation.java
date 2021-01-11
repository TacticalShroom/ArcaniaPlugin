package me.teamethereal.Arcania.commands;

import me.teamethereal.Arcania.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class QuestCreation implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (commandSender instanceof Player)    {
            String quest = args[0];
            Player p = (Player) commandSender;
            if (quest == null)   {
                p.sendMessage(ChatColor.DARK_RED + "You must name the quest!");
                return false;
            }
            else    {
                File f = new File(Main.plugin.getDataFolder().getAbsolutePath() + File.separator + "Quests", quest.toLowerCase() + "-quest" + ".yml");
                FileConfiguration c = YamlConfiguration.loadConfiguration(f);
                try {
                    c.save(f);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                p.sendMessage(ChatColor.YELLOW + quest.toUpperCase() + ChatColor.GREEN + " Quest initialized.");
            }
        }
        return false;
    }
}
