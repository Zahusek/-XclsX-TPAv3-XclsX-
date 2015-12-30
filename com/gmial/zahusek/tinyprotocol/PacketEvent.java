package com.gmail.zahusek.tinyprotocol;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;

import com.gmail.zahusek.tinyprotocol.Reflection.Accessor;

public class PacketEvent implements Cancellable {

	private Player player;
	private Object packet;
	private Accessor[] accessor;
	private boolean cancel;

	public PacketEvent(Player player, Object packet) {
		this.player = player;
		this.packet = packet;
		this.accessor = Reflection.getField(this.packet.getClass());
	}

	public Player getPlayer() {
		return player;
	}

	public Object getPacket() {
		return packet;
	}
	public void setParameter(int id, Object value) {
		accessor[id].set(packet, value);
	}

	@SuppressWarnings("unchecked")
	public <T> T getParameter(int id) {
		return (T) accessor[id].get(packet);
	}
	
	public void setParameters(Object... values) {
		for(int i = 0; i < values.length; i++) 
			accessor[i].set(packet, values[i]);
	}
	public void setPacket(Object packet) {
		this.packet = packet;
	}

	@Override
	public boolean isCancelled() {
		return cancel;
	}

	@Override
	public void setCancelled(boolean cancel) {
		this.cancel = cancel;
		
	}
}
