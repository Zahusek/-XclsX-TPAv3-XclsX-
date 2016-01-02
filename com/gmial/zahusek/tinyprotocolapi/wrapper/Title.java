package com.gmail.zahusek.tinyprotocolapi.wrapper;

import com.gmail.zahusek.tinyprotocol.Packet;
import com.gmail.zahusek.tinyprotocolapi.cover.ChatSerializer;
import com.gmail.zahusek.tinyprotocolapi.cover.TitleAction;

public class Title extends ExpandPacket {
	
	public Title(TitleAction ta) {
		super(Packet.PacketPlayOutTitle);
		setParameters(ta.getAction(), null, -1, -1, -1);
	}
	public Title(int fadein, int stay, int fadeout) {
		super(Packet.PacketPlayOutTitle);
		setParameters(TitleAction.TIMES.getAction(), null, fadein, stay, fadeout);
	}
	public Title(TitleAction ta, String msg) {
		super(Packet.PacketPlayOutTitle);
		setParameters(ta.getAction(), ChatSerializer.toIChat(msg), -1, -1, -1);
	}
}
