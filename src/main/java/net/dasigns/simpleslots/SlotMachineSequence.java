package net.dasigns.simpleslots;

import java.util.HashMap;
import java.util.Random;

import org.bukkit.block.Block;
import org.bukkit.entity.ItemFrame;
import org.bukkit.inventory.ItemStack;

public class SlotMachineSequence {
	private static HashMap<Block,Boolean> running = new HashMap<Block,Boolean>();
	
	public static void start(Block b) {
		if(!SlotMachine.isSlotMachinePart(b)) return;
		setRunning(b,true);
		
		final SlotMachine slot = SlotMachine.getFromLever(b);
		final SlotType type = new SlotType(slot.getType());
		
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
		
		ItemStack item1 = ItemFrameHelper.getFrame(slot.getFirstItemFrame().getBlock()).getItem();
		ItemStack item2 = ItemFrameHelper.getFrame(slot.getSecondItemFrame().getBlock()).getItem();
		ItemStack item3 = ItemFrameHelper.getFrame(slot.getThirdItemFrame().getBlock()).getItem();
		
		if(item1.equals(item2) && item2.equals(item3)) {
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
