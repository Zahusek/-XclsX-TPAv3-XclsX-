package com.gmail.zahusek.tinyprotocolapi;

import org.bukkit.plugin.java.JavaPlugin;

import com.gmail.zahusek.tinyprotocol.TinyProtocol;
import com.gmail.zahusek.tinyprotocolapi.tabapi.TabAPI;

public class TinyProtocolAPI extends JavaPlugin {
	
	public static void main(String[] args) {
	}
	
	private static TinyProtocol tinyprotocol;
	
	@Override
	public void onEnable() { 
		tinyprotocol = new TinyProtocol();
		getServer().getPluginManager().registerEvents(new TabAPI(), this);
	}
	
	public static TinyProtocol getTinyProtocol() {
		return tinyprotocol; 
	}
}
