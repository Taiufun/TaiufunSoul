// src/ru/taiufun/command/sub/GiveCommand.java
package ru.taiufun.command.sub;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import ru.taiufun.TaiufunSoul;
import ru.taiufun.storage.ItemStorage;
import ru.taiufun.util.MessageUtil;
import ru.taiufun.util.SoulboundUtil;

public class GiveCommand {
    private final ItemStorage storage = TaiufunSoul.getInstance().getItemStorage();

    public void execute(CommandSender sender, String[] args) {
        if (args.length < 3) {
            MessageUtil.sendMessage(sender, "&cИспользовать: /tsoul give <id> <player>");
            return;
        }
        String id = args[1];
        Player target = Bukkit.getPlayerExact(args[2]);
        if (target == null) {
            MessageUtil.sendMessage(sender, "&cИгрок не найден");
            return;
        }
        ItemStack item = storage.getItem(id);
        if (item == null) {
            MessageUtil.sendMessage(sender, TaiufunSoul.getInstance()
                    .getPluginConfig().getMessage("item-not-found")
                    .replace("%id%", id));
            return;
        }
        ItemStack clone = item.clone();
        SoulboundUtil.applySoulbound(clone, target);
        target.getInventory().addItem(clone);
        MessageUtil.sendMessage(sender, TaiufunSoul.getInstance()
                .getPluginConfig().getMessage("item-given")
                .replace("%id%", id)
                .replace("%player%", target.getName()));
    }
}
