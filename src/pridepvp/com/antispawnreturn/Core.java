package pridepvp.com.antispawnreturn;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

public class Core extends JavaPlugin {

	 public WorldGuardPlugin WorldGuard = null;
	
	    private Plugin loadPlugin(String pluginName) {
	    	
            Plugin plugin = this.getServer().getPluginManager().getPlugin(pluginName);
            
            if (plugin == null) {
            	
                    return null;
            }
           
            return plugin;
    }
	
	public void onEnable(){
		
		this.WorldGuard = (WorldGuardPlugin) this.loadPlugin("WorldGuard");
		
		getLogger().info("has been enabled");
		
		getServer().getPluginManager().registerEvents(new EnderPearlListener(), this); 
		
	}public void onDisable(){
		
		getLogger().info("has been disabled");
		
	}
}
