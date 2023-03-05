package dev.quantumsoul.chat.data;

import org.bukkit.entity.Player;

public class PlayerSettings {

    public Player lastMessagedBy;
    public boolean dnd;

    public PlayerSettings() {
        this(false);
    }
    public PlayerSettings(boolean dnd) {
        this(null, dnd);
    }
    public PlayerSettings(Player lastMessagedBy, boolean dnd) {
        this.lastMessagedBy = lastMessagedBy;
        this.dnd = dnd ? dnd : false;
    }

}
