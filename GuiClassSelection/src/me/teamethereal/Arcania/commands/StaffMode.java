package me.teamethereal.Arcania.commands;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StaffMode implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (sender instanceof Player)   {
            Player p = (Player) sender;
            if (!p.getGameMode().equals(GameMode.CREATIVE)) {
                if (p.getAllowFlight()) {
                    p.sendMessage(ChatColor.DARK_RED + "You are no longer in Staff Mode.");
                    p.setAllowFlight(false);
                    p.setInvulnerable(false);
                }
                else {
                    p.sendMessage(ChatColor.GREEN + "You are now in Staff Mode.");
                    p.setAllowFlight(true);
                    p.setInvulnerable(true);
                }
            }
        }
        return false;
    }
}
