package net.dasigns.simpleslots;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class Events implements Listener {

	@EventHandler
	public void onPull(PlayerInteractEvent e) {
		Block b = e.getClickedBlock();
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK && b.getType() == Material.LEVER && SlotMachine.isSlotMachinePart(e.getClickedBlock())){
			if(SlotMachineSequence.isRunning(b)) {
				e.getPlayer().sendMessage(ChatColor.RED + "This slot machine is currently in use.");
				e.setCancelled(true);
				return;
			}
			if ((b.getState().getRawData() & 0x8) == 0) SlotMachineSequence.start(b);
		}
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		if(SlotMachine.isSlotMachinePart(e.getBlock())) {
			e.setCancelled(true);
			return;
		}
	}
}
