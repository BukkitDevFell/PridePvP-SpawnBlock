package pridepvp.com.antispawnreturn;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.plugin.Plugin;
import static com.sk89q.worldguard.bukkit.BukkitUtil.toVector;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldguard.bukkit.ConfigurationManager;
import com.sk89q.worldguard.bukkit.WorldConfiguration;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

public class EnderPearlListener implements Listener{
	
	boolean CantTP;
	
	@EventHandler
	public void onPlayerInteract(ProjectileHitEvent event){
		
		if(event.getEntity() instanceof EnderPearl){
		
				Plugin p = Bukkit.getServer().getPluginManager().getPlugin("WorldGuard");
				
				if( p != null ) {
					
					EnderPearl ep = (EnderPearl) event.getEntity();
					
					Block block = ep.getLocation().getBlock().getRelative(0, 1, 0);
					
					WorldGuardPlugin worldGuard = (WorldGuardPlugin) p;
					
					Location location = block.getLocation();
					
					ConfigurationManager cfg = worldGuard.getGlobalStateManager();
					
					WorldConfiguration wcfg = cfg.get(event.getEntity().getWorld());

			        if (wcfg.useRegions) {
			        	
			            Vector pt = toVector(location);
			            
			            RegionManager mgr = worldGuard.getGlobalRegionManager().get(event.getEntity().getWorld());
			            
			            ApplicableRegionSet set = mgr.getApplicableRegions(pt);

			            for(Iterator<ProtectedRegion> i = set.iterator(); i.hasNext();) {
			            	
			            	final ProtectedRegion region = i.next();
					
			            	if(region.contains(location.getBlockX(), location.getBlockY(), location.getBlockZ())){
			            		
			            		System.out.println("YES!");
			            		
			            		CantTP = true;
			            		
			            }
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onPlayerTeleport(PlayerTeleportEvent event){
		
		if(event.getCause().equals(TeleportCause.ENDER_PEARL)){
			
			System.out.println(CantTP);
			
			if(CantTP){
				
				event.setCancelled(true);
				
				CantTP = false;
				
			}
		}
	}
}
