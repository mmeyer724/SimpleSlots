package net.dasigns.simpleslots;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class Events implements Listener {

	@EventHandler
	public void onPull(PlayerInteractEvent e) {
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getClickedBlock().getType() == Material.LEVER){
			Block b = e.getClickedBlock();
			e.getPlayer().sendMessage(Integer.toHexString(b.getState().getRawData() & 0x8));
			if ((b.getState().getRawData() & 0x8) == 1) {
				e.getPlayer().sendMessage("ON");
			}
		}
	}
}
