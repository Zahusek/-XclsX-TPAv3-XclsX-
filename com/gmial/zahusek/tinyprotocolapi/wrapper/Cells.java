package com.gmail.zahusek.tinyprotocolapi.wrapper;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.gmail.zahusek.tinyprotocol.Packet;
import com.gmail.zahusek.tinyprotocolapi.reflect.Info;
import com.gmail.zahusek.tinyprotocolapi.reflect.InfoAction;

public class Cells extends ExpandPacket {

	public Cells(InfoAction ia, Info... i) {
		super(Packet.PacketPlayOutPlayerInfo);
		setParameters(ia.getAction(), Arrays.asList(i).stream().map(r -> r.getReturned()).collect(Collectors.toList()));
	}
	public Cells(InfoAction ia) {
		super(Packet.PacketPlayOutPlayerInfo);
		setParameter(0, ia.getAction());
	}
	
	@SuppressWarnings("unchecked")
	public void add(Info... i) {
		for(Info z : i)
			((List<Object>)getParameter(1)).add(z.getReturned());
	}
}
