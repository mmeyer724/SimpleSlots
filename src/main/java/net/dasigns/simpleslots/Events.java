package net.dasigns.simpleslots;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class Events implements Listener {
	public static HashMap<Player,Block> lastHitBlock = new HashMap<Player,Block>();
	public static ArrayList<Player> selectingBlock= new ArrayList<Player>();
	
	@EventHandler
	public void onPull(PlayerInteractEvent e) {
		Block b = e.getClickedBlock();
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK && b.getType() == Material.LEVER && SlotMachine.isSlotMachinePart(e.getClickedBlock())){
			if(!e.getPlayer().hasPermission("slotmachines.use")) {
				e.getPlayer().sendMessage(ChatColor.RED + "You do not have permission to use this slot machine.");
				e.setCancelled(true);
				return;
			} else if(SlotMachineSequence.isRunning(b)) {
				e.getPlayer().sendMessage(ChatColor.RED + "This slot machine is currently in use.");
				e.setCancelled(true);
				return;
			}
			if ((b.getState().getRawData() & 0x8) == 0) SlotMachineSequence.start(b,e.getPlayer());
		}
		if(e.getAction() == Action.LEFT_CLICK_BLOCK && selectingBlock.contains(e.getPlayer())) lastHitBlock.put(e.getPlayer(), e.getClickedBlock());
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		if(SlotMachine.isSlotMachinePart(e.getBlock())) {
			e.setCancelled(true);
			return;
		}
	}
}
