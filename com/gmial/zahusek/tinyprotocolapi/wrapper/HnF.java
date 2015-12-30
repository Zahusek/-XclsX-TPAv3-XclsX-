package com.gmail.zahusek.tinyprotocolapi.wrapper;

import com.gmail.zahusek.tinyprotocol.Packet;
import com.gmail.zahusek.tinyprotocolapi.reflect.ChatSerializer;

public class HnF extends ExpandPacket {

	public HnF(String header, String footer) {
		super(Packet.PacketPlayOutPlayerListHeaderFooter);
		setParameters(ChatSerializer.toIChat(header), ChatSerializer.toIChat(footer));
	}

}
