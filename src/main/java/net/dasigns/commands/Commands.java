package net.dasigns.commands;

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
		
		@Cmd(permissions = "simpleslots.add", senderType = SenderType.ANY)
		public static void add(CommandEvent e) {
			e.getSender().sendMessage(e.getSubCommandName());
			//TODO: /ss add
		}
		
		@Cmd(permissions = "simpleslots.remove", senderType = SenderType.ANY)
		public static void remove(CommandEvent e) {
			e.getSender().sendMessage(e.getSubCommandName());
			//TODO: /ss remove
		}
	}
}
