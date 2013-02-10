package net.dasigns.simpleslots;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.World;

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
		
		type = Global.config.getString(parentNode+".type");
		world = Global.plugin.getServer().getWorld(Global.config.getString(parentNode+".world"));
		
		for(int i=1;i<=3;i++) {
			ArrayList<Integer> c = new ArrayList<Integer>();
			for(String s:xyz) c.add(Global.config.getInt(frameNode+"."+s+i));
			locs.add(new Location(world,c.get(0),c.get(1),c.get(2)));
		}
		
		ArrayList<Integer> c = new ArrayList<Integer>();
		for(String s:xyz) c.add(Global.config.getInt(leverNode+"."+s));
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
		return Global.config.contains("machines."+name);
	}
}
