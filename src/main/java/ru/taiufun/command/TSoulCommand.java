// src/ru/taiufun/command/TSoulCommand.java
package ru.taiufun.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import ru.taiufun.command.sub.CreateCommand;
import ru.taiufun.command.sub.GiveCommand;
import ru.taiufun.command.sub.DeleteCommand;
import ru.taiufun.command.sub.ListCommand;
import ru.taiufun.util.MessageUtil;

public class TSoulCommand implements CommandExecutor {
    private final CreateCommand create = new CreateCommand();
    private final GiveCommand give = new GiveCommand();
    private final DeleteCommand delete = new DeleteCommand();
    private final ListCommand list = new ListCommand();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            MessageUtil.sendMessage(sender, "&cИспользовать: /tsoul give <id> <player>");
            return true;
        }
        switch (args[0].toLowerCase()) {
            case "create" -> create.execute(sender, args);
            case "give"   -> give.execute(sender, args);
            case "delete" -> delete.execute(sender, args);
            case "list"   -> list.execute(sender, args);
            default       -> sender.sendMessage("§cUnknown subcommand");
        }
        return true;
    }
}
