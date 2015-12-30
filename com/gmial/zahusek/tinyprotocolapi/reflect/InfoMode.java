package com.gmail.zahusek.tinyprotocolapi.reflect;

import java.util.List;

import com.gmail.zahusek.tinyprotocol.Reflection;
import com.google.common.collect.Lists;

public enum InfoMode {
	
	NOT_SET, SURVIVAL, CREATIVE, ADVENTURE, SPECTATOR;
	
	private final List<Object> modes = Lists.newArrayList(Reflection.getClass("{nms}.WorldSettings$EnumGamemode").getEnumConstants());
	private Object gm;
	
	InfoMode(){
		this.gm = modes.get(ordinal());
	}
	public Object getMode() {
		return gm;
	}
	
}
