package de.bestora.silkyspawners;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("SilkySpawners by Bestora enabled!");

        Bukkit.getPluginManager().registerEvents(new BlockBreakEventListener(), this);
        getLogger().info("SilkySpawners BlockBreak registered!");

        Bukkit.getPluginManager().registerEvents(new BlockPlaceEventListener(), this);
        getLogger().info("SilkySpawners BlockPlace registered!");

    }

    @Override
    public void onDisable() {
        getLogger().info("SilkySpawners by Bestora disabled! Good bye...");
    }

}
