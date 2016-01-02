package com.gmail.zahusek.tinyprotocolapi.reflect;

import org.apache.commons.lang.StringUtils;

import com.gmail.zahusek.tinyprotocol.Reflection;

public class ChatSerializer {

	public static Object toIChat(String msg) {
		return Reflection.getTypedMethod(
				Reflection.getClass("{nms}.IChatBaseComponent$ChatSerializer"),
				"a", Reflection.getClass("{nms}.IChatBaseComponent"),
				String.class).invoke(null, String.format("{text:'%s'}", msg));
	}

	public static String toChat(Object obs) {
		String plain = (String) Reflection.getTypedMethod(
				Reflection.getClass("{nms}.IChatBaseComponent$ChatSerializer"),
				"a", String.class,
				Reflection.getClass("{nms}.IChatBaseComponent")).invoke(null,
				obs);
		return StringUtils.strip(plain, "\"");
	}
}
