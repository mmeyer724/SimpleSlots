package net.dasigns.commands;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandHandler implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Class<? extends Commands> c = new Commands().getClass(); 
		//CommandEvent ce = new CommandEvent(sender,command,label,args);
		
		Class<?> commandClass = null;
		
		for(Class<?> cl : c.getClasses()) {
			if(cl.getName().equalsIgnoreCase(label)) {
				commandClass = cl;
				break;
			}
		}
		
		if(commandClass == null) {
			error(sender,"super-command ("+label+") does not exist");
			return false;
		}
		Method commandMethod = null;
		for(Method m : commandClass.getMethods()) {
			if(m.getName().equalsIgnoreCase(args[0])) {
				commandMethod = m;
				break;
			}
		}
		
		if(commandMethod == null) {
			error(sender,"sub-command ("+args[0]+")does not exist");
			return false;
		}
		
		Cmd commandAnnotation = null;
		for(Annotation a : commandMethod.getAnnotations()) if(a instanceof Cmd) commandAnnotation = (Cmd) a;
		
		if(commandAnnotation == null) {
			error(sender,"invalid command annotation");
			return false;
		}
		
		//TODO: Execute the command and check pers and st00f 
		
		return false;
	}
	
	private static void error(CommandSender s, String m) {
		s.sendMessage(ChatColor.RED +"Error: "+ChatColor.DARK_RED+m);
	}
}
