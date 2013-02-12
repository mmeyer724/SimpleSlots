package net.dasigns.simpleslots;

import java.util.ArrayList;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;

public class SlotMachine {
	private ArrayList<Location> locs;
	private String name;
	private String type;
	private World world;
	
	public SlotMachine(String name) {
		this.name = name;
		
		String parentNode = "machines."+name;
		String frameNode = parentNode+".frameLocs";
		String leverNode = parentNode+".leverLoc";
		String[] xyz = new String[] {"x","y","z"};
		
		type = Global.config().getString(parentNode+".type");
		world = Global.plugin.getServer().getWorld(Global.config().getString(parentNode+".world"));
		
		for(int i=1;i<=3;i++) {
			ArrayList<Integer> c = new ArrayList<Integer>();
			for(String s:xyz) c.add(Global.config().getInt(frameNode+"."+s+i));
			locs.add(new Location(world,c.get(0),c.get(1),c.get(2)));
		}
		
		ArrayList<Integer> c = new ArrayList<Integer>();
		for(String s:xyz) c.add(Global.config().getInt(leverNode+"."+s));
		locs.add(new Location(world,c.get(0),c.get(1),c.get(2)));
	}
	
	public Location getFirstItemFrame() {
		return locs.get(0);
	}
	
	public Location getSecondItemFrame() {
		return locs.get(1);
	}
	
	public Location getThirdItemFrame() {
		return locs.get(2);
	}
	
	public Location getItemFrame(Integer i) {
		switch(i) {
		case 1:
			return getFirstItemFrame();
		case 2:
			return getSecondItemFrame();
		case 3:
			return getThirdItemFrame();
		default:
			return null;
		}
	}
	
	public Location getLever() {
		return locs.get(3);
	}
	
	public String getType() {
		return type;
	}
	
	public String getName() {
		return name;
	}
	
	public static Boolean slotMachineExists(String name) {
		return Global.config().contains("machines."+name);
	}
	
	public static SlotMachine getFromLever(Block b) {
		Set<String> keys = null;
		try {
			keys = Global.config().getConfigurationSection("machines").getKeys(false);
		} catch(Exception e) { return null; }
		for(String k : keys) {
			String parentNode = "machines."+k;
			String leverNode = parentNode+".leverLoc";
			World world = Global.plugin.getServer().getWorld(Global.config().getString(parentNode+".world"));
			ArrayList<Integer> c = new ArrayList<Integer>();
			String[] xyz = new String[] {"x","y","z"};
			for(String s:xyz) c.add(Global.config().getInt(leverNode+"."+s));
			if(new Location(world,c.get(0),c.get(1),c.get(2)).equals(b.getLocation())) return new SlotMachine(k);
		}
		return null;
	}
	
	public static Boolean isSlotMachinePart(Block b) {
		Set<String> keys = null;
		try {
			keys = Global.config().getConfigurationSection("machines").getKeys(false);
		} catch(Exception e) { return false; }
		for(String k : keys) {
			SlotMachine s = new SlotMachine(k);
			if(s.getFirstItemFrame().equals(b.getLocation())) return true;
			if(s.getSecondItemFrame().equals(b.getLocation())) return true;
			if(s.getThirdItemFrame().equals(b.getLocation())) return true;
			if(s.getLever().equals(b.getLocation())) return true;
		}
		return false;
	}
}
