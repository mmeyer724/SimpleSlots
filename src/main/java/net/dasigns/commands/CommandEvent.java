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
	private Boolean def = false;

	public CommandEvent(CommandSender s,Command c,String cl, String[] a) {
		sender = s;
		command = c;
		commandLabel = cl;
		args = a;
	}

	public CommandEvent(CommandSender s,Command c,String cl, String[] a,Boolean d) {
		sender = s;
		command = c;
		commandLabel = cl;
		args = a;
		def = d;
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
		if(def) return null;
		return args[0];
	}

	public String[] getArgs() {
		if(def) return args;
		return Arrays.copyOfRange(args, 1, args.length);
	}

	public Integer getNumberArgs() {
		if(def) return args.length;
		return args.length-1;
	}
}
