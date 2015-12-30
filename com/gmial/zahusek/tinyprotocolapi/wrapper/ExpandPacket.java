package com.gmail.zahusek.tinyprotocolapi.wrapper;

import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.gmail.zahusek.tinyprotocol.Packet;
import com.gmail.zahusek.tinyprotocol.Reflection;
import com.gmail.zahusek.tinyprotocol.Reflection.Accessor;
import com.gmail.zahusek.tinyprotocolapi.TinyProtocolAPI;

public abstract class ExpandPacket {
	
	private Packet id;
	private Object packet;
	private Accessor[] accessor;
	
	public ExpandPacket(Packet packet){
		this.id = packet;
		this.packet = Reflection.getConstructor("{nms}." + packet.name()).invoke();
		this.accessor = Reflection.getField(this.packet.getClass());
	}
	
	protected void setParameter(int id, Object value) {
		accessor[id].set(packet, value);
	}

	@SuppressWarnings("unchecked")
	protected <T> T getParameter(int id) {
		return (T) accessor[id].get(packet);
	}
	
	protected void setParameters(Object... values) {
		for(int i = 0; i < values.length; i++) 
			accessor[i].set(packet, values[i]);
	}
	
	public void sendPacket(Player player) {
		switch (id.getSender()) {
			case CLIENT:
				TinyProtocolAPI.getTinyProtocol().receivePacket(player, packet);;
			case SERVER:
				TinyProtocolAPI.getTinyProtocol().sendPacket(player, packet);;
				break;
			default:
				break;
		}
	}
	public void sendPacketAll() {
		Collection<? extends Player> players = Bukkit.getOnlinePlayers();
		synchronized (players) {
			players.forEach(this::sendPacket);
		}
		return;
	}
}
