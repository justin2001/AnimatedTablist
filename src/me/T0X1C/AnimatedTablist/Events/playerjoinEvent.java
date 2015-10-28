package me.T0X1C.AnimatedTablist.Events;

import me.T0X1C.AnimatedTablist.Tablist.AnimTablist;
import me.T0X1C.AnimatedTablist.Tablist.AnimTablist2;
import me.T0X1C.AnimatedTablist.Tablist.AnimTablist3;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class playerjoinEvent implements Listener {
	
	@EventHandler
	public void PlayerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
		if(version.startsWith("v1_8_R1")) {
			AnimTablist.repeatingTab(p);
		} else if(version.startsWith("v1_8_R2")) {
			AnimTablist2.repeatingTab(p);
		} else if(version.startsWith("v1_8_R3")) {
			AnimTablist3.repeatingTab(p);
		} else if(version.startsWith("v1_8_R4")) {
			AnimTablist3.repeatingTab(p);
		} else if(version.startsWith("v1_8_R5")) {
			AnimTablist3.repeatingTab(p);
		} else if(version.startsWith("v1_8_R6")) {
			AnimTablist3.repeatingTab(p);
		} else if(version.startsWith("v1_8_R7")) {
			AnimTablist3.repeatingTab(p);
		} else if(version.startsWith("v1_8_R8")) {
			AnimTablist3.repeatingTab(p);
		}
	}
}