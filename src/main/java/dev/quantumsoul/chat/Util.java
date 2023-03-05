package dev.quantumsoul.chat;

import dev.quantumsoul.chat.data.PlayerSettings;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class Util {
    public static boolean exchangeMessage(String message, Player sender, Player recipient, ChatPlugin plugin) {
        PlayerSettings recipientData = plugin.playerData.get(recipient);

        recipient.sendMessage(sender.getName() + ": " + message);
        if (recipientData != null && !recipientData.dnd) {
            recipient.playSound(recipient.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5F, 0.5F);
        }
        sender.sendMessage("Me to " + recipient.getName() + ": " + message);

        if (recipientData != null) {
            recipientData.lastMessagedBy = sender;
        } else {
            PlayerSettings newRecipientData = new PlayerSettings(sender, false);
            plugin.playerData.put(recipient, newRecipientData);
        }

        return true;
    }
}
