/**
 * Listener made by MrDev139
 * GitHub: @mrdev139 (https://github.com/mrdev139)
 */

package dev.quantumsoul.chat.events;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class MoveListener implements Listener {
    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        /*TODO:
         *  -Get nearby players by a specified radius
         *  -Create a vector with the direction of the player's eye location and a random selected nearby entity
         *  -Create particles between the two locs*/

        List<LivingEntity> nearby = player.getNearbyEntities(5, 5 ,5).stream()
                .filter(e -> e instanceof LivingEntity)
                .map(e -> (LivingEntity) e)
                .collect(Collectors.toList());
        LivingEntity entity = !nearby.isEmpty() ? nearby.get(ThreadLocalRandom.current().nextInt(nearby.size())) : null;
        if(entity == null || entity.getType() == EntityType.ARMOR_STAND) return;
        Location loc = player.getLocation().clone(); //clone to avoid overwriting player's eye loc
        Location loc1 = entity.getLocation().clone();
        double distance = loc1.distance(loc);
        Vector direction = loc1.subtract(loc).toVector();
        List<ArmorStand> stands = new ArrayList<>();
        for (double i = 0.1; i < distance; i += 0.5) {
            direction.multiply(i);
            loc.add(direction);
            Particle random = Particle.values()[ThreadLocalRandom.current().nextInt(Particle.values().length)];
            player.getWorld().spawnParticle(random, loc, 1);
            loc.subtract(direction);
            direction.normalize();
        }

    }

    private ArmorStand spawnAS(Location location) {
        ArmorStand as = location.getWorld().spawn(location, ArmorStand.class);
        as.setGravity(false);
        as.setVisible(false);
        as.setHelmet(new ItemStack(Material.DIAMOND_BLOCK));
        return as;
    }

}