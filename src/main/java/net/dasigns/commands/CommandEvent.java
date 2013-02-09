package net.dasigns.commands;

import java.util.Arrays;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class CommandEvent {	
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
        
        public Command getRawCommand() {
        	return command;
        }
        
        public String getSuperCommandLabel() {
        	return commandLabel;
        }
        
        public String getSuperCommandName() {
                return command.getName();
        }
        
        public String getSubCommandName() {
                return args[0];
        }
        
        public String[] getArgs() {
                return Arrays.copyOfRange(args, 1, args.length);
        }
        
        public Integer getNumberArgs() {
                return args.length-1;
        }
}
