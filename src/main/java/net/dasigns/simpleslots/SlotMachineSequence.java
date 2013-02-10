package net.dasigns.simpleslots;

import java.util.HashMap;

import org.bukkit.block.Block;

public class SlotMachineSequence {
	private static HashMap<Block,Boolean> running;
	
	public static void start(Block b) {
		setRunning(b,true);
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
