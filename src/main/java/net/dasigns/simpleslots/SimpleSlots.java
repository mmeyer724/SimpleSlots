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
		//Setup logger
		Global.log = this.getLogger();
		
		//Create config dirs
		Global.dataFolder = Util.createDir(this.getDataFolder());
		
		//Setup config
		ConfigAccessor ca = new ConfigAccessor(this,"config.yml");
		Global.config = ca.getConfig();
		
		//Setup Vault
		VaultManager vm = new VaultManager(this);
		vm.setupChat();
		vm.setupEconomy();
		vm.setupPermissions();
		Global.vault = vm;
		
		//Setup command handler
		CommandHandler ch = new CommandHandler();
		this.getCommand("simpleslots").setExecutor(ch);
		
		//Setup events handler
		this.getServer().getPluginManager().registerEvents(new Events(), this);
	}

}
