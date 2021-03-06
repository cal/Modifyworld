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

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Type;
import org.bukkit.event.block.*;
import org.bukkit.event.painting.PaintingBreakByEntityEvent;
import org.bukkit.event.painting.PaintingBreakEvent;
import org.bukkit.event.painting.PaintingPlaceEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.config.ConfigurationNode;
import ru.tehkode.modifyworld.EventHandler;
import ru.tehkode.modifyworld.ModifyworldListener;

/**
 *
 * @author t3hk0d3
 */
public class BlockListener extends ModifyworldListener {

	public BlockListener(Plugin plugin, ConfigurationNode config) {
		super(plugin, config);
	}

	@EventHandler(Type.BLOCK_BREAK)
	public void onBlockBreak(BlockBreakEvent event) {
		if (!canInteractWithBlock(event.getPlayer(), "modifyworld.blocks.destroy.", event.getBlock())) {
			informPlayerAboutDenial(event.getPlayer());
			event.setCancelled(true);
		}
	}

	@EventHandler(Type.BLOCK_PLACE)
	public void onBlockPlace(BlockPlaceEvent event) {
		if (!canInteractWithBlock(event.getPlayer(), "modifyworld.blocks.place.", event.getBlock())) {
			informPlayerAboutDenial(event.getPlayer());
			event.setCancelled(true);
		}
	}

	@EventHandler(Type.PAINTING_BREAK)
	public void onPaintingBreak(PaintingBreakEvent event) {
		if (!(event instanceof PaintingBreakByEntityEvent)) {
			return;
		}

		PaintingBreakByEntityEvent pbee = (PaintingBreakByEntityEvent) event;
		if (pbee.getRemover() instanceof Player
				&& !canInteractWithMaterial((Player) pbee.getRemover(), "modifyworld.blocks.destroy.", Material.PAINTING)) {

			informPlayerAboutDenial((Player) pbee.getRemover());
			event.setCancelled(true);
		}
	}

	@EventHandler(Type.PAINTING_PLACE)
	public void onPaintingPlace(PaintingPlaceEvent event) {
		if (!canInteractWithMaterial(event.getPlayer(), "modifyworld.blocks.place.", Material.PAINTING)) {
			informPlayerAboutDenial(event.getPlayer());
			event.setCancelled(true);
		}
	}
}
