package com.gmail.zahusek.test;

package com.gmail.zahusek.test;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.gmail.zahusek.tinyprotocolapi.apis.TabAPI;

public class Test extends JavaPlugin {
	
	public static void main(String[] args) {
	}
	
	@Override
	public void onEnable() {
		new BukkitRunnable() {
			
			@Override
			public void run() {
				Bukkit.getOnlinePlayers().forEach((player) -> task(player));
			}
		}.runTaskTimer(this, 10, 10);
	}
	public void task(Player player) {
		long time = System.currentTimeMillis();
		TabAPI.setHnF(player, "§c§l#§9" + String.format("%1$tF", time) + "§c§l#", "§4§l#§3" + String.format("%1$tT", time) + "§4§l#");
		TabAPI.setSlot(player, 1, 1, "§6§lWelcome", 9999);
		TabAPI.setSlot(player, 2, 1, "§e§l" + player.getName(), 9999);
		TabAPI.setSkin(player, 1, 1, "MHF_Exclamation");
		TabAPI.setSkin(player, 2, 1, "MHF_ArrowRight");
		for(int x = 0; x < 4; x++) {
			for(int y = 0; y < 20; y++) {
				if((x == 1 && y == 1) || (x == 2 && y ==1)) continue;
				TabAPI.setSkin(player, x, y, "MHF_Pumpkin");
			}
		}
		TabAPI.refresh(player);
	}
}
