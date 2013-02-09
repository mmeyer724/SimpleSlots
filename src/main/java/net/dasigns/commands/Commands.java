package net.dasigns.commands;

public class Commands {
	public static class SimpleSlots {
		
		@Cmd(permissions = "", senderType = SenderType.ANY)
		public static void __default(CommandEvent e) {
			e.getSender().sendMessage(e.getSuperCommandName());
		}
		
		@Cmd(permissions = "test.node", senderType = SenderType.ANY)
		public static void test(CommandEvent e) {
			e.getSender().sendMessage(e.getSubCommandName() + "Hi");
		}
		
	}
}
