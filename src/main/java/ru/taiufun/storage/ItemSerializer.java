// src/ru/taiufun/storage/ItemSerializer.java
package ru.taiufun.storage;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

public class ItemSerializer {
    public static String toBase64(ItemStack item) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            BukkitObjectOutputStream out = new BukkitObjectOutputStream(baos);
            out.writeObject(item);
            out.close();
            return Base64.getEncoder().encodeToString(baos.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("Unable to serialize item", e);
        }
    }

    public static ItemStack fromBase64(String data) {
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(Base64.getDecoder().decode(data));
            BukkitObjectInputStream in = new BukkitObjectInputStream(bais);
            ItemStack item = (ItemStack) in.readObject();
            in.close();
            return item;
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Unable to deserialize item", e);
        }
    }
}
