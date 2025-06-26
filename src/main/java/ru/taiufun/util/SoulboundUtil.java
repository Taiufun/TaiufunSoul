// src/ru/taiufun/util/SoulboundUtil.java
package ru.taiufun.util;

import java.util.Collections;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.entity.Player;
import ru.taiufun.TaiufunSoul;
import ru.taiufun.manager.ConfigManager;


public class SoulboundUtil {
    private static final NamespacedKey ownerKey =
            new NamespacedKey(TaiufunSoul.getInstance(), "tsoul-owner");

    public static void applySoulbound(ItemStack item, Player player) {
        ItemMeta meta = item.getItemMeta();
        PersistentDataContainer c = meta.getPersistentDataContainer();
        c.set(ownerKey, PersistentDataType.STRING, player.getUniqueId().toString());
        ConfigManager cfg = TaiufunSoul.getInstance().getPluginConfig();
        String lore = ChatColor.translateAlternateColorCodes(
                '&', cfg.getSoulboundLore().replace("%player%", player.getName())
        );
        meta.setLore(Collections.singletonList(lore));
        item.setItemMeta(meta);
    }

    public static boolean isOwner(ItemStack item, Player player) {
        if (!item.hasItemMeta()) return true;
        PersistentDataContainer c = item.getItemMeta().getPersistentDataContainer();
        if (!c.has(ownerKey, PersistentDataType.STRING)) return true;
        return player.getUniqueId().toString().equals(
                c.get(ownerKey, PersistentDataType.STRING)
        );
    }
}
