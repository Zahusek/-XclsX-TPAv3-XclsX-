package com.gmail.zahusek.tinyprotocolapi.cover;

import org.apache.commons.lang.StringUtils;

import com.gmail.zahusek.tinyprotocol.Reflection;
import com.gmail.zahusek.tinyprotocol.Reflection.MethodInvoker;

public class ChatSerializer {
	
	private static final MethodInvoker ichat = Reflection.getTypedMethod(
			Reflection.getClass("{nms}.IChatBaseComponent$ChatSerializer"),
			"a", Reflection.getClass("{nms}.IChatBaseComponent"),
			String.class);
	private static final MethodInvoker chat = Reflection.getTypedMethod(
			Reflection.getClass("{nms}.IChatBaseComponent$ChatSerializer"),
			"a", String.class,
			Reflection.getClass("{nms}.IChatBaseComponent"));

	public static Object toIChat(String msg) {
		return ichat.invoke(null, String.format("{text:'%s'}", msg));
	}

	public static String toChat(Object obs) {
		return StringUtils.strip(((String)chat.invoke(null, obs)), "\"");
	}
}

