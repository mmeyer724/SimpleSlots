package net.dasigns.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import net.dasigns.simpleslots.Events;
import net.dasigns.simpleslots.Global;
import net.dasigns.simpleslots.ItemFrameHelper;
import net.dasigns.simpleslots.SlotMachineFactory;

public class Commands {
	public static class SimpleSlots {
		
		@Cmd(permissions = "", senderType = SenderType.PLAYER)
		public static void __default(CommandEvent e) {
			e.getSender().sendMessage(e.getSuperCommandName());
			//TODO: /ss
		}
		
		@Cmd(permissions = "", senderType = SenderType.ANY)
		public static void help(CommandEvent e) {
			e.getSender().sendMessage(e.getSubCommandName());
			//TODO: /ss help
		}
		
		@Cmd(permissions = "simpleslots.add", senderType = SenderType.PLAYER)
		public static void add(final CommandEvent e) {
			if(e.getNumberArgs() != 2) {
				e.getSender().sendMessage(ChatColor.RED + "Error: " + ChatColor.DARK_RED + "invalid arguments. Usage: ss add <name> <type>");
				return;
			}
			
			final String name = e.getArgs()[0];
			final String type = e.getArgs()[1];
			
			Bukkit.getScheduler().runTaskAsynchronously(Global.plugin, new Runnable() {
			    @Override
				public void run() {
			    	e.getSender().sendMessage("Punch your first itemframe");
					Block last = Events.lastHitBlock.get((Player) e.getSender());
					while(last == Events.lastHitBlock.get((Player) e.getSender()) || !ItemFrameHelper.hasFrame(Events.lastHitBlock.get((Player) e.getSender()))) {}
					Block if1 = Events.lastHitBlock.get((Player) e.getSender());
					
					e.getSender().sendMessage("Punch your second itemframe");
					last = Events.lastHitBlock.get((Player) e.getSender());
					while(last == Events.lastHitBlock.get((Player) e.getSender()) || !ItemFrameHelper.hasFrame(Events.lastHitBlock.get((Player) e.getSender()))) {}
					Block if2 = Events.lastHitBlock.get((Player) e.getSender());
					
					e.getSender().sendMessage("Punch your third itemframe");
					last = Events.lastHitBlock.get((Player) e.getSender());
					while(last == Events.lastHitBlock.get((Player) e.getSender()) || !ItemFrameHelper.hasFrame(Events.lastHitBlock.get((Player) e.getSender()))) {}
					Block if3 = Events.lastHitBlock.get((Player) e.getSender());
					
					e.getSender().sendMessage("Punch your slot machines lever");
					last = Events.lastHitBlock.get((Player) e.getSender());
					while(last == Events.lastHitBlock.get((Player) e.getSender()) || Events.lastHitBlock.get((Player) e.getSender()).getType() != Material.LEVER) {}
					Block lever = Events.lastHitBlock.get((Player) e.getSender());
					
					SlotMachineFactory smf = new SlotMachineFactory(if1.getLocation(),if2.getLocation(),if3.getLocation(),lever,name,type);
					smf.save();
					e.getSender().sendMessage(ChatColor.AQUA + "Slot Machine "+ChatColor.GOLD+name+ChatColor.AQUA+"created as "+ChatColor.GOLD+type+ChatColor.AQUA+".");
			    }
			});
		}
		
		@Cmd(permissions = "simpleslots.remove", senderType = SenderType.ANY)
		public static void remove(CommandEvent e) {
			e.getSender().sendMessage(e.getSubCommandName());
			//TODO: /ss remove
		}
		
		@Cmd(permissions = "simpleslots.reload", senderType = SenderType.ANY)
		public static void reload(CommandEvent e) {
			Global.configa.reloadConfig();
			e.getSender().sendMessage(Color.AQUA + "Configuration reloaded.");
		}
	}
}
