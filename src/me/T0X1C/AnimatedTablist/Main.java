package me.T0X1C.AnimatedTablist;

import me.T0X1C.AnimatedTablist.Commands.animtablistCommand;
import me.T0X1C.AnimatedTablist.Events.playerjoinEvent;
import me.T0X1C.AnimatedTablist.Tablist.AnimTablist;
import me.T0X1C.AnimatedTablist.Tablist.AnimTablist2;
import me.T0X1C.AnimatedTablist.Tablist.AnimTablist3;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	public static Plugin pl;
	
	public void onEnable() {
		pl = this;
		saveDefaultConfig();
		reloadConfig();
		loadCommands();
		loadEvents();
		String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
		for(Player p : Bukkit.getOnlinePlayers()) {
			if(Bukkit.getOnlinePlayers().isEmpty()) {
				return;
			}
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
		if(Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
			Bukkit.getConsoleSender().sendMessage("§4Animated§cTablist §8> §7Found PlaceholderAPI!");
			Bukkit.getConsoleSender().sendMessage("§4Animated§cTablist §8> §7You can now use all PlaceholderAPI's placeholders (https://www.spigotmc.org/wiki/placeholderapi-placeholders/)");
		} else {
			Bukkit.getConsoleSender().sendMessage("§4Animated§cTablist §8> §7Didn't find PlaceholderAPI!");
		}
	}
	
	public void loadCommands() {
		getCommand("animtablist").setExecutor(new animtablistCommand(this));
	}
	
	public void loadEvents() {
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new playerjoinEvent(), this);
	}
}