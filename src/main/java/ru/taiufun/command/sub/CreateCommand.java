// src/ru/taiufun/command/sub/CreateCommand.java
package ru.taiufun.command.sub;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import ru.taiufun.TaiufunSoul;
import ru.taiufun.storage.ItemStorage;
import ru.taiufun.util.MessageUtil;

public class CreateCommand {
    private final ItemStorage storage = TaiufunSoul.getInstance().getItemStorage();

    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof Player player)) {
            MessageUtil.sendMessage(sender, "&cТолько игроку соизволено так делать!");
            return;
        }
        if (args.length < 2) {
            MessageUtil.sendMessage(sender, "&cИспользуй: /tsoul create <id>");
            return;
        }
        String id = args[1];
        ItemStack item = player.getInventory().getItemInMainHand();
        if (item == null || item.getType().isAir()) {
            MessageUtil.sendMessage(sender, TaiufunSoul.getInstance()
                    .getPluginConfig().getMessage("item-not-found"));
            return;
        }
        storage.saveItem(id, item);
        MessageUtil.sendMessage(sender, TaiufunSoul.getInstance()
                .getPluginConfig().getMessage("item-saved").replace("%id%", id));
    }
}
