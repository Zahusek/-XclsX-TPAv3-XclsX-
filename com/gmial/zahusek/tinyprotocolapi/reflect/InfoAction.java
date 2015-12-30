package com.gmail.zahusek.tinyprotocolapi.reflect;

import java.util.List;

import com.gmail.zahusek.tinyprotocol.Reflection;
import com.google.common.collect.Lists;

public enum InfoAction {
	
	ADD, GAMEMODE, PING, DISPLAYNAME, REMOVE;
	
	private final List<Object> actions = Lists.newArrayList(Reflection.getClass("{nms}.PacketPlayOutPlayerInfo$EnumPlayerInfoAction").getEnumConstants());
	private Object at;
	
	InfoAction(){
		this.at = actions.get(ordinal());
	}
	public Object getAction() {
		return at;
	}
	
}
