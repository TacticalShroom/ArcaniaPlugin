package me.teamethereal.Arcania.Loot;

import org.bukkit.entity.Player;

public class Proficiencies {

    public boolean isProficient(Player p, String stat)    {
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
        if (stat.equals("strength")) {
            if (classSelection.equals("knight") || classSelection.equals("ranger")) {
                return true;
            }
            else    {
                return false;
            }
        }
        if (stat.equals("dexterity")) {
            if (classSelection.equals("rogue") || classSelection.equals("ranger")) {
                return true;
            }
            else    {
                return false;
            }
        }
        if (stat.equals("constitution")) {
            if (classSelection.equals("knight") || classSelection.equals("druid")) {
                return true;
            }
            else    {
                return false;
            }
        }
        if (stat.equals("intelligence")) {
            if (classSelection.equals("wizard") || classSelection.equals("druid") || classSelection.equals("rogue")) {
                return true;
            }
            else    {
                return false;
            }
        }
        return false;
    }

    public boolean isWeaponProficient(Player p, String weaponType)  {
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

        if (weaponType.equals("HeavyMelee"))  {
            if (classSelection.equals("knight"))    {
                return true;
            }
            else    {
                return false;
            }
        }
        if (weaponType.equals("LightMelee"))    {
            if (classSelection.equals("rogue") || classSelection.equals("ranger") || classSelection.equals("druid") || classSelection.equals("wizard"))    {
                return true;
            }
            else    {
                return false;
            }
        }
        if (weaponType.equals("HeavyRanged"))   {
            if (classSelection.equals("knight") || classSelection.equals("ranger")) {
                return true;
            }
            else    {
                return false;
            }
        }
        if (weaponType.equals("LightRanged"))   {
            if (classSelection.equals("wizard") || classSelection.equals("rogue"))  {
                return true;
            }
            else    {
                return false;
            }
        }
        if (weaponType.equals("Arcane"))    {
            if (classSelection.equals("wizard") || classSelection.equals("druid"))  {
                return true;
            }
            else    {
                return false;
            }
        }
        return false;
    }

}
