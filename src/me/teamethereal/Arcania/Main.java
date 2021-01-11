package me.teamethereal.Arcania;

import me.teamethereal.Arcania.Books.CharacterInfoBook;
import me.teamethereal.Arcania.Books.CompendiumBook;
import me.teamethereal.Arcania.Books.QuestBook;
import me.teamethereal.Arcania.Currency.Currency;
import me.teamethereal.Arcania.CustomMobs.PoisonSpider.SpiderDeathListener;
import me.teamethereal.Arcania.CustomMobs.RemoveDroppedItems;
import me.teamethereal.Arcania.CustomMobs.SpawnWand;
import me.teamethereal.Arcania.CustomMobs.SpawnWandListener;
import me.teamethereal.Arcania.CustomMobs.SpawnListener;
import me.teamethereal.Arcania.HealthAndMana.HealthUpdate;
import me.teamethereal.Arcania.Loot.Excalibur;
import me.teamethereal.Arcania.Tutorials.Knight.UponKnightEntry;
import me.teamethereal.Arcania.commands.ClassCommand;
import me.teamethereal.Arcania.commands.Parties.PartyCommands;
import me.teamethereal.Arcania.commands.QuestCreation;
import me.teamethereal.Arcania.commands.StaffMode;
import me.teamethereal.Arcania.events.GuiClickEvent;
import me.teamethereal.Arcania.events.InventorySaver;
import me.teamethereal.Arcania.HealthAndMana.Leveling;
import me.teamethereal.Arcania.events.onJoin;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class Main extends JavaPlugin {

    public static Main plugin;

    @Override
    public void onEnable() {
        plugin = this;

        getServer().getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + " ");
        getServer().getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "  ______ " + ChatColor.BLUE + "   Arcania");
        getServer().getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "  |    | " + ChatColor.WHITE + "   Minecraft's Professional");
        getServer().getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "  |----| " + ChatColor.WHITE + "   MMORPG");
        getServer().getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "  |    | " + ChatColor.RED + "   Developed by TacticalShroom_");
        getServer().getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + " ");


        getServer().getPluginManager().registerEvents(new GuiClickEvent(), this);

        getServer().getPluginManager().registerEvents(new InventorySaver(), this);

        getServer().getPluginManager().registerEvents(new Leveling(), this);

        getServer().getPluginManager().registerEvents(new HealthUpdate(), this);

        getServer().getPluginManager().registerEvents(new CompendiumBook(), this);

        getServer().getPluginManager().registerEvents(new QuestBook(), this);

        getServer().getPluginManager().registerEvents(new UponKnightEntry(), this);

        getServer().getPluginManager().registerEvents(new CharacterInfoBook(), this);

        getServer().getPluginManager().registerEvents(new onJoin(), this);

        getServer().getPluginManager().registerEvents(new Currency(), this);

        getServer().getPluginManager().registerEvents(new RemoveDroppedItems(), this);

        getServer().getPluginManager().registerEvents(new SpawnListener(), this);

        getServer().getPluginManager().registerEvents(new SpawnWandListener(), this);

        getServer().getPluginManager().registerEvents(new SpiderDeathListener(), this);

        Objects.requireNonNull(getCommand("Class")).setExecutor(new ClassCommand());

        Objects.requireNonNull(getCommand("excalibur")).setExecutor(new Excalibur());

        Objects.requireNonNull(getCommand("staff")).setExecutor(new StaffMode());

        Objects.requireNonNull(getCommand("spawnwand")).setExecutor(new SpawnWand());

        Objects.requireNonNull(getCommand("questCreate")).setExecutor(new QuestCreation());

        Objects.requireNonNull(getCommand("party")).setExecutor(new PartyCommands());
    }
    @Override
    public void onDisable() {


        plugin = null;
    }
}