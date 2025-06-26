// src/ru/taiufun/listener/ItemPickupListener.java
package ru.taiufun.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.NamespacedKey;
import ru.taiufun.TaiufunSoul;

import ru.taiufun.manager.ConfigManager;
import ru.taiufun.util.MessageUtil;

public class ItemPickupListener implements Listener {
    private final ConfigManager cfg = TaiufunSoul.getInstance().getPluginConfig();
    private final NamespacedKey key = new NamespacedKey(TaiufunSoul.getInstance(), "tsoul-owner");

    @EventHandler
    public void onEntityPickup(EntityPickupItemEvent e) {
        if (!(e.getEntity() instanceof Player p)) return;
        ItemStack item = e.getItem().getItemStack();
        if (item == null || !item.hasItemMeta()) return;
        PersistentDataContainer c = item.getItemMeta().getPersistentDataContainer();
        if (c.has(key, PersistentDataType.STRING) &&
                !p.getUniqueId().toString().equals(c.get(key, PersistentDataType.STRING))) {
            if (cfg.isPreventPickup()) {
                e.setCancelled(true);
                MessageUtil.sendMessage(p, cfg.getMessage("not-owner"));
            }
        }
    }
}
