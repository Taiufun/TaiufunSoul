// src/ru/taiufun/util/MessageUtil.java
package ru.taiufun.util;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class MessageUtil {
    public static void sendMessage(CommandSender sender, String msg) {
        if (msg == null) return;
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
    }
}
