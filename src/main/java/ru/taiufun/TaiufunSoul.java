// src/ru/taiufun/TaiufunSoul.java
package ru.taiufun;

import org.bukkit.plugin.java.JavaPlugin;
import ru.taiufun.command.TSoulCommand;
import ru.taiufun.listener.ItemDropListener;
import ru.taiufun.listener.ItemInventoryListener;
import ru.taiufun.listener.ItemPickupListener;
import ru.taiufun.listener.ItemUseListener;
import ru.taiufun.manager.ConfigManager;
import ru.taiufun.storage.ItemStorage;


public class TaiufunSoul extends JavaPlugin {

    private static TaiufunSoul instance;
    private ItemStorage itemStorage;
    private ConfigManager pluginConfig;

    public static TaiufunSoul getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        pluginConfig = new ConfigManager(this);
        itemStorage = new ItemStorage(this);

        getCommand("tsoul").setExecutor(new TSoulCommand());
        getServer().getPluginManager().registerEvents(new ItemUseListener(), this);
        getServer().getPluginManager().registerEvents(new ItemPickupListener(), this);
        getServer().getPluginManager().registerEvents(new ItemDropListener(), this);
        getServer().getPluginManager().registerEvents(new ItemInventoryListener(), this);

    }

    public ItemStorage getItemStorage() {
        return itemStorage;
    }

    public ConfigManager getPluginConfig() {
        return pluginConfig;
    }
}
