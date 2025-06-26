// src/ru/taiufun/config/PluginConfig.java
package ru.taiufun.manager;

import org.bukkit.configuration.file.FileConfiguration;
import ru.taiufun.TaiufunSoul;

public class ConfigManager {
    private final FileConfiguration cfg;

    public ConfigManager(TaiufunSoul plugin) {
        this.cfg = plugin.getConfig();
    }

    public String getString(String path) {
        return cfg.getString(path);
    }

    public boolean getBoolean(String path) {
        return cfg.getBoolean(path);
    }

    public String getMessage(String key) {
        return getString("messages." + key);
    }

    public String getSoulboundLore() {
        return getString("soulbound-lore");
    }

    public boolean isPreventPickup() {
        return getBoolean("prevent-pickup");
    }

    public boolean isPreventDrop() {
        return getBoolean("prevent-drop");
    }
}
