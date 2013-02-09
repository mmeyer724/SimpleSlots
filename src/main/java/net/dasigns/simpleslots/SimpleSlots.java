package net.dasigns.simpleslots;

import net.dasigns.commands.CommandHandler;

import org.bukkit.plugin.java.JavaPlugin;

public class SimpleSlots extends JavaPlugin {

	@Override
	public void onDisable() {
		// TODO Auto-generated method stub
		super.onDisable();
	}

	@Override
	public void onEnable() {
		//Handle Commands
		CommandHandler ch = new CommandHandler();
		this.getCommand("simpleslots").setExecutor(ch);
	}

}
