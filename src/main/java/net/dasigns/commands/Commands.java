package net.dasigns.commands;

import org.bukkit.Color;

import net.dasigns.simpleslots.Global;

public class Commands {
	public static class SimpleSlots {
		
		@Cmd(permissions = "", senderType = SenderType.ANY)
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
		public static void add(CommandEvent e) {
			e.getSender().sendMessage(e.getSubCommandName());
			//TODO: /ss add
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
