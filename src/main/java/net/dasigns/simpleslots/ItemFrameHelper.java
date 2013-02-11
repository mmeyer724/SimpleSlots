package net.dasigns.simpleslots;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemFrame;

public class ItemFrameHelper {
	public static Boolean hasFrame(Block b) {
		for(Entity e : b.getWorld().getEntities()) 
			if(e.getLocation().equals(b.getLocation()) && (e instanceof ItemFrame)) return true;
		return false;
	}
	
	public static ItemFrame getFrame(Block b) {
		for(Entity e : b.getWorld().getEntities()) 
			if(e.getLocation().equals(b.getLocation()) && (e instanceof ItemFrame)) return (ItemFrame) e;
		return null;
	}
}
