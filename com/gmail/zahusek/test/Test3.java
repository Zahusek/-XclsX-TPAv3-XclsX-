package com.gmail.zahusek.test;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.gmail.zahusek.tinyprotocolapi.apis.TitleAPI;

public class Test extends JavaPlugin {
	
	public static void main(String[] args) {
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		Player player = (Player) sender;
		TitleAPI.sendTnS(player, "§6§lWelcome", "§e§l" + player.getName(), 0, 20*2, 20);
		TitleAPI.sendActionbar(player, "§9§l# §a§lTiny§b§lProtocol§d§lAPI §9§l#");
		return false;
	}
}
