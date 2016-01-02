package com.gmail.zahusek.tinyprotocolapi.wrapper;

import com.gmail.zahusek.tinyprotocol.Packet;
import com.gmail.zahusek.tinyprotocolapi.cover.ChatSerializer;

public class Chat extends ExpandPacket {
	
	public Chat(String msg, byte type) {
		super(Packet.PacketPlayOutChat);
		setParameter(0, ChatSerializer.toIChat(msg));
		setParameter(2, type);
	}
	public Chat(String msg) {
		super(Packet.PacketPlayOutChat);
		setParameter(0, ChatSerializer.toIChat(msg));
		setParameter(2, (byte) 2);
	}
}
