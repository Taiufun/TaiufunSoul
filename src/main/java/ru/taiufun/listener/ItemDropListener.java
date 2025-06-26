// src/ru/taiufun/listener/ItemDropListener.java
package ru.taiufun.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.NamespacedKey;
import ru.taiufun.TaiufunSoul;

import ru.taiufun.manager.ConfigManager;
import ru.taiufun.util.MessageUtil;

public class ItemDropListener implements Listener {
    private final ConfigManager cfg = TaiufunSoul.getInstance().getPluginConfig();
    private final NamespacedKey key = new NamespacedKey(TaiufunSoul.getInstance(), "tsoul-owner");

    @EventHandler
    public void onPlayerDrop(PlayerDropItemEvent e) {
        Player p = e.getPlayer();
        ItemStack item = e.getItemDrop().getItemStack();
        if (item == null || !item.hasItemMeta()) return;
        PersistentDataContainer c = item.getItemMeta().getPersistentDataContainer();
        if (c.has(key, PersistentDataType.STRING) &&
                !p.getUniqueId().toString().equals(c.get(key, PersistentDataType.STRING))) {
            if (cfg.isPreventDrop()) {
                e.setCancelled(true);
                MessageUtil.sendMessage(p, cfg.getMessage("not-owner"));
            }
        }
    }
}
