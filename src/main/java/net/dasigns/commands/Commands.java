package net.dasigns.commands;

public class Commands {
	public static class SimpleSlots {
		@Cmd(permissions = "test.node", senderType = SenderType.ANY)
		public static void test(CommandEvent e) {
			
		}
	}
}
