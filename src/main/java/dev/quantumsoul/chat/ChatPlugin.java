package dev.quantumsoul.chat;

import dev.quantumsoul.chat.commands.DndToggleCommand;
import dev.quantumsoul.chat.commands.MsgCommand;
import dev.quantumsoul.chat.commands.ReplyCommand;
import dev.quantumsoul.chat.data.PlayerSettings;
import dev.quantumsoul.chat.events.MoveListener;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class ChatPlugin extends JavaPlugin {

    public HashMap<Player, PlayerSettings> playerData = new HashMap<>();

    @Override
    public void onEnable() {
        this.getLogger().info("Starting up ChatPlugin");

        this.getCommand("msg").setExecutor(new MsgCommand(this));
        this.getCommand("r").setExecutor(new ReplyCommand(this));
        this.getCommand("dnd").setExecutor(new DndToggleCommand(this));

        this.getServer().getPluginManager().registerEvents(new MoveListener(), this);

        this.getLogger().info("Loaded ChatPlugin");
    }

    @Override
    public void onDisable() {
        this.getLogger().info("Shutting down ChatPlugin");
    }
}
