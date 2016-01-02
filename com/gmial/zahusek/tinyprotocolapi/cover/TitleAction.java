package com.gmail.zahusek.tinyprotocolapi.cover;

import java.util.List;

import com.gmail.zahusek.tinyprotocol.Reflection;
import com.google.common.collect.Lists;

public enum TitleAction {
	
	TITLE, SUBTITLE, TIMES, CLEAR, RESET;
	
	private final List<Object> actions = Lists.newArrayList(Reflection.getClass("{nms}.PacketPlayOutTitle$EnumTitleAction").getEnumConstants());
	private Object at;
	
	TitleAction(){
		this.at = actions.get(ordinal());
	}
	public Object getAction() {
		return at;
	}
	
}
