package dev.quantumsoul.chat.commands;

import dev.quantumsoul.chat.ChatPlugin;
import dev.quantumsoul.chat.data.PlayerSettings;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static dev.quantumsoul.chat.Util.exchangeMessage;

public class ReplyCommand implements CommandExecutor {

    ChatPlugin plugin;

    public ReplyCommand(ChatPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can run this command");
            return false;
        }

        PlayerSettings settings = this.plugin.playerData.get((Player) sender);

        if (settings == null) {
            sender.sendMessage("You were not last messaged by anyone");
            return false;
        }

        String message = String.join(" ", args);

        if (message.length() == 0) {
            sender.sendMessage("Please include a message: /msg <player> <message>");
            return false;
        }

        Player recipient = settings.lastMessagedBy;

        return exchangeMessage(message, (Player) sender, recipient, this.plugin);
    }

}
