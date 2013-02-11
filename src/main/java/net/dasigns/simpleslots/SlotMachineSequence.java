package net.dasigns.simpleslots;

import java.util.HashMap;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SlotMachineSequence {
	private static HashMap<Block,Boolean> running = new HashMap<Block,Boolean>();
	
	public static void start(Block b,Player p) {
		if(!SlotMachine.isSlotMachinePart(b)) return;
		if(!p.hasPermission("slotmachines.use")) return;
		
		final SlotMachine slot = SlotMachine.getFromLever(b);
		final SlotType type = new SlotType(slot.getType());
		
		if(!Global.vault.economy.has(p.getName(),(double) type.getCost())) {
			p.sendMessage(ChatColor.AQUA + "You don't have enough for this slot machine, it costs " + ChatColor.GOLD + type.getCost().toString() + " " + Global.vault.economy.currencyNamePlural() + ChatColor.AQUA + ".");
			return;
		}
		setRunning(b,true);
		
		if(!Global.vault.economy.bankWithdraw(p.getName(),(double) type.getCost()).transactionSuccess()) {
			p.sendMessage(ChatColor.RED + " Failed to withdraw money.");
			return;
		}
		p.sendMessage(ChatColor.GOLD + type.getCost().toString() + ChatColor.AQUA + " taken by slot machine.");
		
		for(int i=1;i<=3;i++) {
			Long tickDelay = 20L;
			ItemStack finalItem = type.getNextItem();
			for(int j=0;j<10;j++) {
				final ItemFrame frame = ItemFrameHelper.getFrame(slot.getItemFrame(i).getBlock());
				Global.plugin.getServer().getScheduler().runTaskLater(Global.plugin,new Runnable() {
				    public void run() {
				    	Random r = new Random();
				    	Integer random = r.nextInt(type.getItems().size());
				    	ItemStack randomItem = type.getItems().get(random);
				    	frame.setItem(randomItem);
				    }
				}, tickDelay);
				if(j >= 5) tickDelay = (long) (tickDelay * 0.25);
				frame.setItem(finalItem);
			}
		}
		
		if(!ItemFrameHelper.hasFrame(slot.getFirstItemFrame().getBlock()) 
				|| !ItemFrameHelper.hasFrame(slot.getSecondItemFrame().getBlock()) 
				|| !ItemFrameHelper.hasFrame(slot.getThirdItemFrame().getBlock()) ) {
			p.sendMessage(ChatColor.RED + "Slot machine item frames not found!");
			return;
		}
			
		ItemStack item1 = ItemFrameHelper.getFrame(slot.getFirstItemFrame().getBlock()).getItem();
		ItemStack item2 = ItemFrameHelper.getFrame(slot.getSecondItemFrame().getBlock()).getItem();
		ItemStack item3 = ItemFrameHelper.getFrame(slot.getThirdItemFrame().getBlock()).getItem();
		
		if(item1.equals(item2) && item2.equals(item3)) {
			p.sendMessage(ChatColor.GREEN + "Congratulations! You win.");
			//TODO: Do something if when they win
		}
		
		b.setData((byte)(b.getData()+8),true);
		setRunning(b,false);
	}
	
	
	private static void setRunning(Block b,Boolean tf) {
		if(running.containsKey(b)) running.remove(b);
		running.put(b, tf);
	}
	
	public static Boolean isRunning(Block b) {
		if(!running.containsKey(b)) return false;
		return running.get(b);
	}
}
