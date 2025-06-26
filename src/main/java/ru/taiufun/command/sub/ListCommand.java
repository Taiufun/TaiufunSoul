// src/ru/taiufun/command/sub/ListCommand.java
package ru.taiufun.command.sub;

import org.bukkit.command.CommandSender;
import ru.taiufun.storage.ItemStorage;
import ru.taiufun.TaiufunSoul;
import ru.taiufun.util.MessageUtil;

import java.util.List;

public class ListCommand {
    private final ItemStorage storage = TaiufunSoul.getInstance().getItemStorage();

    public void execute(CommandSender sender, String[] args) {
        List<String> ids = storage.getAllIds();
        if (ids.isEmpty()) {
            MessageUtil.sendMessage(sender, "&сВ листе нет предметов :9");
            return;
        }
        MessageUtil.sendMessage(sender, "&aSaved soul IDs: " + String.join(", ", ids));
    }
}
