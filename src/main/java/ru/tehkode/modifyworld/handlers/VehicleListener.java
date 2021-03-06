/*
 * Modifyworld - PermissionsEx ruleset plugin for Bukkit
 * Copyright (C) 2011 t3hk0d3 http://www.tehkode.ru
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package ru.tehkode.modifyworld.handlers;

import org.bukkit.entity.Player;
import org.bukkit.event.Event.Type;
import org.bukkit.event.vehicle.VehicleDamageEvent;
import org.bukkit.event.vehicle.VehicleEnterEvent;
import org.bukkit.event.vehicle.VehicleEntityCollisionEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.config.ConfigurationNode;
import ru.tehkode.modifyworld.EventHandler;
import ru.tehkode.modifyworld.ModifyworldListener;

/**
 *
 * @author t3hk0d3
 */
public class VehicleListener extends ModifyworldListener {

    public VehicleListener(Plugin plugin, ConfigurationNode config) {
        super(plugin, config);
    }

    @EventHandler(Type.VEHICLE_DAMAGE)
    public void onVehicleDamage(VehicleDamageEvent event) {
        if (!(event.getAttacker() instanceof Player)) {
            return;
        }

        Player player = (Player) event.getAttacker();
        if (!permissionsManager.has(player, "modifyworld.vehicle.destroy")) {
            informPlayerAboutDenial(player);
            event.setCancelled(true);
        }
    }

    @EventHandler(Type.VEHICLE_ENTER)
    public void onVehicleEnter(VehicleEnterEvent event) {
        if (!(event.getEntered() instanceof Player)) {
            return;
        }

        Player player = (Player) event.getEntered();
        if (!permissionsManager.has(player, "modifyworld.vehicle.enter")) {
            informPlayerAboutDenial(player);
            event.setCancelled(true);
        }
    }

    @EventHandler(Type.VEHICLE_COLLISION_ENTITY)
    public void onVehicleEntityCollision(VehicleEntityCollisionEvent event) {
        if (!(event.getEntity() instanceof Player)) {
            return;
        }

        Player player = (Player) event.getEntity();
        if (!permissionsManager.has(player, "modifyworld.vehicle.collide")) {
            event.setCancelled(true);
            event.setCollisionCancelled(true);
            event.setPickupCancelled(true);
        }
    }
}