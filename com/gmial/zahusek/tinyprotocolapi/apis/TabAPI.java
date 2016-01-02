package com.gmail.zahusek.tinyprotocolapi.apis;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.PluginDisableEvent;

import com.gmail.zahusek.tinyprotocolapi.TinyProtocolAPI;
import com.google.common.collect.Maps;

public class TabAPI implements Listener {
	
	
	private static final Map<UUID, TabData> CELLS = Maps.newHashMap();
	
	private static TabData getPlayer(Player player) {
		if(!CELLS.containsKey(player.getUniqueId())) {
			CELLS.put(player.getUniqueId(), new TabData());
			base(player);
		}
		return CELLS.get(player.getUniqueId());
	}
	
	public static void setSlot(Player player, int x, int y, String msg, int ping) {
		getPlayer(player).setCell(x, y, msg, ping);
	}
	
	public static void setSkin(Player player, int x, int y, String skin) {
		getPlayer(player).setSkin(x, y, skin);
	}
	
	public static void setHeader(Player player, String header) {
		getPlayer(player).setHeader(header);
	}
	
	public static void setFooter(Player player, String footer) {
		getPlayer(player).setFooter(footer);
	}
	
	public static void setHnF(Player player, String header, String footer) {
		getPlayer(player).setHnF(header, footer);
	}
	
	public static void setHnF(Player player, List<String> header, List<String> footer) {
		getPlayer(player).setHnf(header, footer);
	}
	public static void setHeader(Player player, List<String> header) {
		getPlayer(player).setHeader(header);
	}
	
	public static void setFooter(Player player, List<String> footer) {
		getPlayer(player).setFooter(footer);
	}
	
	public static void refresh(Player player) {
		getPlayer(player).getRefresh().forEach((packet) -> packet.sendPacket(player));
	}
	
	public static void base(Player player) {
		getPlayer(player).getDefault().forEach((packet) -> packet.sendPacket(player));
	}
	
	private void remove(Player player) {
		getPlayer(player).getRemove().forEach((packet) -> packet.sendPacket(player));
		CELLS.remove(player.getUniqueId());
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void quit(PlayerQuitEvent e) {
		remove(e.getPlayer());
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void disable(PluginDisableEvent e) {
		if(e.getPlugin() != TinyProtocolAPI.getPlugin(TinyProtocolAPI.class)) return;
		Bukkit.getOnlinePlayers().forEach((player) -> remove(player));
	}
}
