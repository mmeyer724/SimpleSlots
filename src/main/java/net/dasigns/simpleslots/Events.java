package net.dasigns.simpleslots;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class Events implements Listener {

	@EventHandler
	public void onPull(PlayerInteractEvent e) {
		Block b = e.getClickedBlock();
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK && b.getType() == Material.LEVER){
			if(SlotMachineSequence.isRunning(b)) {
				e.getPlayer().sendMessage(ChatColor.RED + "This slot machine is currently in use.");
				e.setCancelled(true);
				if ((b.getState().getRawData() & 0x8) == 0) flipLever(b);
				return;
			}
			if ((b.getState().getRawData() & 0x8) == 0) {
				e.getPlayer().sendMessage("Down");
				SlotMachineSequence.start(b);
				flipLever(b);
			}
		}
	}
	
	public static void flipLever(final Block b) {
		Global.plugin.getServer().getScheduler().runTaskLaterAsynchronously(Global.plugin, new Runnable() {
		    @Override 
		    public void run() {
		    	b.setData((byte)(b.getData()+8),true);
		    }
		}, 20L);
	}
}
