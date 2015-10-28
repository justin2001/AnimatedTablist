package me.T0X1C.AnimatedTablist.Commands;

import me.T0X1C.AnimatedTablist.Main;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class animtablistCommand implements CommandExecutor {
	
	private final Main pl;
	public animtablistCommand(Main pl) {
		this.pl = pl;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', pl.getConfig().getString("Messages.OnlyPlayers")));
			return true;
		}
		Player p = (Player) sender;
		if(cmd.getName().equalsIgnoreCase("animtablist")) {
			if(args.length == 0) {
				if(p.hasPermission("AnimTablist.version")) {
					p.sendMessage("§4§m---------------");
					p.sendMessage("§7Version: §c" + pl.getDescription().getVersion());
					p.sendMessage("§7Author: §cT0X1C");
					p.sendMessage("§4§m---------------");
				} else {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', pl.getConfig().getString("Messages.NoPermissions")));
				}
				return true;
			}
			if(args[0].equalsIgnoreCase("reload")) {
				if(p.hasPermission("AnimTablist.reload")) {
					pl.reloadConfig();
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', pl.getConfig().getString("Messages.ReloadedConfig")));
				} else {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', pl.getConfig().getString("Messages.NoPermissions")));
				}
			}
		}
		return true;
	}
}