package com.gmail.zahusek.tinyprotocolapi.apis;

import org.bukkit.entity.Player;

import com.gmail.zahusek.tinyprotocolapi.cover.TitleAction;
import com.gmail.zahusek.tinyprotocolapi.wrapper.Chat;
import com.gmail.zahusek.tinyprotocolapi.wrapper.Title;

public class TitleAPI {
	
	public static void sendTitle(Player player, String title, int start, int stay, int end) {
		new Title(TitleAction.CLEAR).sendPacket(player);
		new Title(start, stay, end).sendPacket(player);
		new Title(TitleAction.TITLE, title).sendPacket(player);
	}
	public static void sendSubtitle(Player player, String subtitle, int start, int stay, int end) {
		new Title(TitleAction.CLEAR).sendPacket(player);
		new Title(start, stay, end).sendPacket(player);
		new Title(TitleAction.TITLE, "").sendPacket(player);
		new Title(TitleAction.SUBTITLE, subtitle).sendPacket(player);
	}
	public static void sendActionbar(Player player, String msg) {
		new Chat(msg).sendPacket(player);
	}
	public static void sendTnS(Player player, String title, String subtitle, int start, int stay, int end) {
		new Title(TitleAction.CLEAR).sendPacket(player);
		new Title(start, stay, end).sendPacket(player);
		new Title(TitleAction.TITLE, title).sendPacket(player);
		new Title(TitleAction.SUBTITLE, subtitle).sendPacket(player);
	}
}
