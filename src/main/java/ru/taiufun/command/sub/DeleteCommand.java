// src/ru/taiufun/command/sub/DeleteCommand.java
package ru.taiufun.command.sub;

import org.bukkit.command.CommandSender;
import ru.taiufun.TaiufunSoul;
import ru.taiufun.storage.ItemStorage;
import ru.taiufun.util.MessageUtil;

public class DeleteCommand {
    private final ItemStorage storage = TaiufunSoul.getInstance().getItemStorage();

    public void execute(CommandSender sender, String[] args) {
        if (args.length < 2) {
            MessageUtil.sendMessage(sender, "&cИспользуй: /tsoul delete <id>");
            return;
        }
        String id = args[1];
        if (storage.getItem(id) == null) {
            MessageUtil.sendMessage(sender, TaiufunSoul.getInstance()
                    .getPluginConfig().getMessage("item-not-found")
                    .replace("%id%", id));
            return;
        }
        storage.deleteItem(id);
        MessageUtil.sendMessage(sender, TaiufunSoul.getInstance()
                .getPluginConfig().getMessage("item-deleted")
                .replace("%id%", id));
    }
}
