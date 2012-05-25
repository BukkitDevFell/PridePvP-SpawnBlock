package com.pridepvp.spawnblock;

import org.bukkit.plugin.java.JavaPlugin;

public class Core extends JavaPlugin {
	
	private static Core instance;
	
	public static Core getInstance() {
		return instance;
	}
	
	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(new EnderPearlListener(), this);
		
		getLogger().info("has been enabled");
	}
	
	@Override
	public void onLoad() {
		Core.instance = this;
	}
}