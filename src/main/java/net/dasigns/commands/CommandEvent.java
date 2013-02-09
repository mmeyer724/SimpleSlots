package net.dasigns.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class CommandEvent {
        //TODO: Modify for new system
	
        private CommandSender sender;
        private Command command;
        private String commandLabel;
        private String[] args;
        
        public CommandEvent(CommandSender s,Command c,String cl, String[] a) {
                sender = s;
                command = c;
                commandLabel = cl;
                args = a;
        }
        
        public CommandSender getSender() {
                return sender;
        }
        
        public SenderType getSenderType() {
                if(sender instanceof ConsoleCommandSender) return SenderType.CONSOLE;
                if(sender instanceof Player) return SenderType.PLAYER;
                return SenderType.UNKNOWN;
        }
        
        public Command getCommand() {
                return command;
        }
        
        public String getCommandLabel() {
                return commandLabel;
        }
        
        public String[] getArgs() {
                return args;
        }
        
        public Integer getNumberArgs() {
                return args.length;
        }
}
