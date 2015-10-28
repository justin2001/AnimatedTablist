package me.T0X1C.AnimatedTablist.Tablist;

import java.lang.reflect.InvocationTargetException;

import me.T0X1C.AnimatedTablist.Main;
import me.clip.placeholderapi.PlaceholderAPI;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Statistic;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class AnimTablist3 {
	
	static String toMinecraftTextJSON(String input) {
		return "{\"text\":\"" + input + "\"}";
	}
	
	static void updateTab(String h, String f) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException, InstantiationException, NoSuchFieldException {
		for(Player p : Bukkit.getOnlinePlayers()) {
			CraftPlayer p2 = (CraftPlayer)p;
			
			PacketPlayOutPlayerListHeaderFooter tab = new PacketPlayOutPlayerListHeaderFooter(ChatSerializer.a(toMinecraftTextJSON(h)));
			try {
				java.lang.reflect.Field b = tab.getClass().getDeclaredField("b");
				b.setAccessible(true);
				b.set(tab, ChatSerializer.a(toMinecraftTextJSON(f)));
				b.setAccessible(false);
			} catch(Exception ex) {
				ex.printStackTrace();
			}
			p2.getHandle().playerConnection.sendPacket(tab);
		}
	}
	
	static int headernumber = 0;
	static int footernumber = 0;
	
	public static void repeatingTab(final Player p) {
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.pl, new Runnable() {
			public void run() {
				String header = null;
				String footer = null;
				if(headernumber >= Main.pl.getConfig().getStringList("Headers").size()) {
					headernumber = 0;
				}
				
				if(footernumber >= Main.pl.getConfig().getStringList("Footers").size()) {
					footernumber = 0;
				}
				
				header = ChatColor.translateAlternateColorCodes('&', Main.pl.getConfig().getStringList("Headers").get(headernumber)).replaceAll("%n", "\n").replaceAll("%player_name%", p.getName()).replaceAll("%player_health%", String.valueOf(p.getHealth())).replaceAll("%player_hunger%", String.valueOf(p.getFoodLevel())).replaceAll("%player_displayname%%", p.getDisplayName()).replaceAll("%player_world%", p.getWorld().getName()).replaceAll("%player_level%", String.valueOf(p.getLevel())).replaceAll("%player_xp%", String.valueOf(p.getExp())).replaceAll("%player_gamemode%", String.valueOf(p.getGameMode())).replaceAll("%player_kills%", String.valueOf(p.getStatistic(Statistic.PLAYER_KILLS))).replaceAll("%player_deaths%", String.valueOf(p.getStatistic(Statistic.DEATHS))).replaceAll("%player_ping%", String.valueOf(((CraftPlayer) p).getHandle().ping)).replaceAll("%server_name%", Bukkit.getServerName()).replaceAll("%server_motd%", Bukkit.getServer().getMotd()).replaceAll("%online_players%", String.valueOf(Bukkit.getServer().getOnlinePlayers().size())).replaceAll("%max_players%", String.valueOf(Bukkit.getServer().getMaxPlayers()));
				footer = ChatColor.translateAlternateColorCodes('&', Main.pl.getConfig().getStringList("Footers").get(footernumber)).replaceAll("%n", "\n").replaceAll("%player_name%", p.getName()).replaceAll("%player_health%", String.valueOf(p.getHealth())).replaceAll("%player_hunger%", String.valueOf(p.getFoodLevel())).replaceAll("%player_displayname%%", p.getDisplayName()).replaceAll("%player_world%", p.getWorld().getName()).replaceAll("%player_level%", String.valueOf(p.getLevel())).replaceAll("%player_xp%", String.valueOf(p.getExp())).replaceAll("%player_gamemode%", String.valueOf(p.getGameMode())).replaceAll("%player_kills%", String.valueOf(p.getStatistic(Statistic.PLAYER_KILLS))).replaceAll("%player_deaths%", String.valueOf(p.getStatistic(Statistic.DEATHS))).replaceAll("%player_ping%", String.valueOf(((CraftPlayer) p).getHandle().ping)).replaceAll("%server_name%", Bukkit.getServerName()).replaceAll("%server_motd%", Bukkit.getServer().getMotd()).replaceAll("%online_players%", String.valueOf(Bukkit.getServer().getOnlinePlayers().size())).replaceAll("%max_players%", String.valueOf(Bukkit.getServer().getMaxPlayers()));
				if(Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
					header = PlaceholderAPI.setPlaceholders(p, header);
					footer = PlaceholderAPI.setPlaceholders(p, footer);
				}
				
				headernumber++;
				footernumber++;
				
				try {
					updateTab(header, footer);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException| SecurityException | ClassNotFoundException | InstantiationException | NoSuchFieldException e) {
					e.printStackTrace();
				}
			}
		}, 0, Main.pl.getConfig().getInt("UpdateTime"));
	}
}