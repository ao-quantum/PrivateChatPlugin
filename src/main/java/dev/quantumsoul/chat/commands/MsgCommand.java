package dev.quantumsoul.chat.commands;

import dev.quantumsoul.chat.ChatPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

import static dev.quantumsoul.chat.Util.exchangeMessage;

public class MsgCommand implements CommandExecutor {

    ChatPlugin plugin;

    public MsgCommand(ChatPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can run this command");
            return false;
        }

        if (args.length < 2) {
            sender.sendMessage("Incorrect usage: /msg <player> <message>");
            return false;
        }

        Player recipient = this.plugin.getServer().getPlayer(args[0]);

        if (recipient == null) {
            sender.sendMessage("Player" + args[0] + "not found: /msg <player> <message>");
            return false;
        }

        String[] messageArray = Arrays.copyOfRange(args, 1, args.length);

        if (messageArray.length == 0) {
            sender.sendMessage("Please include a message: /msg <player> <message>");
            return false;
        }

        String message = String.join(" ", messageArray);

        return exchangeMessage(message, (Player) sender, recipient, this.plugin);
    }
}
