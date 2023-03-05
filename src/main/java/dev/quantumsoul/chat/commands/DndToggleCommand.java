package dev.quantumsoul.chat.commands;

import dev.quantumsoul.chat.ChatPlugin;
import dev.quantumsoul.chat.data.PlayerSettings;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DndToggleCommand implements CommandExecutor {

    ChatPlugin plugin;

    public DndToggleCommand(ChatPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can run this command");
            return false;
        }

        PlayerSettings data = plugin.playerData.get((Player) sender);

        if (data != null) {
            data.dnd = !data.dnd;

            sender.sendMessage("DND has been " + (data.dnd ? "enabled" : "disabled"));
        } else {
            boolean dnd = true;
            sender.sendMessage("DND has been enabled");

            plugin.playerData.put((Player) sender, new PlayerSettings(true));
        }

        return true;
    }
}
