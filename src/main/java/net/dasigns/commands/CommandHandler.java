package net.dasigns.commands;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandHandler implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Class<? extends Commands> c = new Commands().getClass(); 
		CommandEvent ce = new CommandEvent(sender,command,label,args);

		Class<?> commandClass = null;

		for(Class<?> cl : c.getClasses()) {
			if(cl.getSimpleName().equalsIgnoreCase(command.getName())) {
				commandClass = cl;
				break;
			}
		}

		if(commandClass == null) {
			error(sender,"super-command ("+command.getName()+") does not exist.");
			return false;
		}
		Method commandMethod = null;
		if(args.length>0) {
			for(Method m : commandClass.getMethods()) {
				if(m.getName().equalsIgnoreCase(args[0])) {
					commandMethod = m;
					break;
				}
			}
			if(commandMethod == null) {
				error(sender,"sub-command ("+args[0]+")does not exist.");
				return false;
			}
		}
		if(commandMethod == null) {
			error(sender, "No sub-command provided");
			return false;
		}

		Cmd commandAnnotation = null;
		for(Annotation a : commandMethod.getAnnotations()) if(a instanceof Cmd) commandAnnotation = (Cmd) a;

		if(commandAnnotation == null) {
			error(sender,"invalid command annotation.");
			return false;
		} 

		if(ce.getSenderType() != commandAnnotation.senderType() && commandAnnotation.senderType() != SenderType.ANY) {
			error(sender,"invalid sender type.");
			return false;
		}

		if(commandAnnotation.permissions() != "" && !sender.hasPermission(commandAnnotation.permissions())) {
			error(sender,"you do not have permissions to run this command.");
			return false;
		}

		if(Boolean.class.isAssignableFrom(commandMethod.getReturnType())) {
			try {
				return (Boolean) commandMethod.invoke(commandClass, ce);
			} catch (Exception e) {
				error(sender,"interal error running command boolean!");
				e.printStackTrace();
				return false;
			}
		} else {
			try {
				commandMethod.invoke(commandClass, ce);
			} catch (Exception e) {
				error(sender,"interal error running command non-boolean!");
				e.printStackTrace();
				return false;
			}
			return true;
		}
	}

	private static void error(CommandSender s, String m) {
		s.sendMessage(ChatColor.RED +"Error: "+ChatColor.DARK_RED+m);
	}
}
