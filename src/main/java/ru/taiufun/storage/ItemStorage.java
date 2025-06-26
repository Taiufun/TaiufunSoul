// src/ru/taiufun/storage/ItemStorage.java
package ru.taiufun.storage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import ru.taiufun.TaiufunSoul;

public class ItemStorage {
    private final File file;
    private final FileConfiguration cfg;
    private final Map<String, String> items = new HashMap<>();

    public ItemStorage(TaiufunSoul plugin) {
        this.file = new File(plugin.getDataFolder(), "items.yml");
        if (!file.exists()) plugin.saveResource("items.yml", false);
        this.cfg = YamlConfiguration.loadConfiguration(file);

        if (cfg.isConfigurationSection("items")) {
            for (String key : cfg.getConfigurationSection("items").getKeys(false)) {
                items.put(key, cfg.getString("items." + key));
            }
        }
    }

    public void saveItem(String id, ItemStack item) {
        String data = ItemSerializer.toBase64(item);
        items.put(id, data);
        cfg.set("items." + id, data);
        save();
    }

    public ItemStack getItem(String id) {
        String data = items.get(id);
        return data == null ? null : ItemSerializer.fromBase64(data);
    }

    public void deleteItem(String id) {
        items.remove(id);
        cfg.set("items." + id, null);
        save();
    }

    public List<String> getAllIds() {
        return new ArrayList<>(items.keySet());
    }

    private void save() {
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
