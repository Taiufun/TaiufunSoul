// src/ru/taiufun/listener/ItemInventoryListener.java
package ru.taiufun.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.NamespacedKey;
import ru.taiufun.TaiufunSoul;
import ru.taiufun.manager.ConfigManager;
import ru.taiufun.util.MessageUtil;

public class ItemInventoryListener implements Listener {
    private final ConfigManager cfg = TaiufunSoul.getInstance().getPluginConfig();
    private final NamespacedKey key = new NamespacedKey(TaiufunSoul.getInstance(), "tsoul-owner");

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player p)) return;

        if (e.getClickedInventory() == null ||
                e.getClickedInventory().getType() == InventoryType.PLAYER) {
            return;
        }

        ItemStack item = e.getCurrentItem();
        if (item == null || !item.hasItemMeta()) return;
        PersistentDataContainer c = item.getItemMeta().getPersistentDataContainer();
        if (!c.has(key, PersistentDataType.STRING)) return;

        String owner = c.get(key, PersistentDataType.STRING);

        if (p.getUniqueId().toString().equals(owner)) return;

        InventoryAction action = e.getAction();
        switch (action) {
            case PICKUP_ALL, PICKUP_ONE, PICKUP_HALF, PICKUP_SOME, MOVE_TO_OTHER_INVENTORY -> {
                e.setCancelled(true);
                MessageUtil.sendMessage(p, cfg.getMessage("cannot-store"));
            }
            default -> {

            }
        }
    }

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent e) {
        if (!(e.getWhoClicked() instanceof Player p)) return;

        if (e.getInventory().getType() != InventoryType.PLAYER) return;

        for (ItemStack item : e.getNewItems().values()) {
            if (item == null || !item.hasItemMeta()) continue;
            PersistentDataContainer c = item.getItemMeta().getPersistentDataContainer();
            if (!c.has(key, PersistentDataType.STRING)) continue;

            String owner = c.get(key, PersistentDataType.STRING);
            if (!p.getUniqueId().toString().equals(owner)) {
                e.setCancelled(true);
                MessageUtil.sendMessage(p, cfg.getMessage("cannot-store"));
                break;
            }
        }
    }
}
