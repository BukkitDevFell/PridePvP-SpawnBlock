package com.pridepvp.spawnblock;

import java.util.Iterator;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

public class EnderPearlListener implements Listener {
	
	private final Core plugin;
	
	public EnderPearlListener() {
		this.plugin = Core.getInstance();
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerTeleport(PlayerTeleportEvent event) {
		if (!event.getCause().equals(TeleportCause.ENDER_PEARL))
			return;
		
		Location to = event.getTo();
		
		if (plugin.getServer().getPluginManager().getPlugin("WorldGuard") == null)
			return;
		
		WorldGuardPlugin worldGuard = (WorldGuardPlugin) plugin.getServer().getPluginManager().getPlugin("WorldGuard");
		
		if (!worldGuard.getGlobalStateManager().get(to.getWorld()).useRegions)
			return;
		
		RegionManager rgnMgr = worldGuard.getGlobalRegionManager().get(to.getWorld());
		
		for (Iterator<ProtectedRegion> regions = rgnMgr.getApplicableRegions(to).iterator(); regions.hasNext();) {
			if (regions.next().contains(to.getBlockX(), to.getBlockY(), to.getBlockZ())) {
				event.setCancelled(true);
				break;
			}
		}
	}
}