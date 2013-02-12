package net.dasigns.simpleslots;

import org.bukkit.Location;
import org.bukkit.block.Block;

public class SlotMachineFactory {
	Location if1;
	Location if2;
	Location if3;
	Block lever;
	String type;
	String name;
	
	public SlotMachineFactory(Location if1, Location if2, Location if3, Block lever, String type, String name) {
		this.if1 = if1;
		this.if2 = if2;
		this.if3 = if3;
		this.lever = lever;
		this.type = type;
		this.name = name;
	}
	
	public SlotMachineFactory(SlotMachine s) {
		this.if1 = s.getFirstItemFrame();
		this.if2 = s.getSecondItemFrame();
		this.if3 = s.getThirdItemFrame();
		this.lever = s.getLever().getBlock();
		this.type = s.getType();
		this.name = s.getName();
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public void setLever(Block lever) {
		this.lever = lever;
	}
	
	public void setFrames(Location if1, Location if2, Location if3) {
		this.if1 = if1;
		this.if2 = if2;
		this.if3 = if3;
	}
	
	private Location getFrame(Integer i) {
		switch(i) {
		case 1:
			return if1;
		case 2:
			return if2;
		case 3:
			return if3;
		default:
			return null;
		}
	}
	
	public void save() {
		String machineNode = "machines."+this.name;
		String worldName = if1.getWorld().getName();
		
		if(Global.config().contains(machineNode)) {
			Boolean changed = false;
			//Check type
			if(Global.config().getString(machineNode + ".type") != this.type) {
				Global.config().set(machineNode + ".type", this.type);
				changed = true;
			}
			
			//Check world
			if(Global.config().getString(machineNode + ".world") != worldName) {
				Global.config().set(machineNode + ".world", worldName);
				changed = true;
			}
			
			//Check lever
			String leverNode = machineNode + ".leverLoc";
			if(Global.config().getInt(leverNode + ".x") != this.lever.getX()) {
				Global.config().set(leverNode + ".x",this.lever.getX());
				changed = true;
			}
			if(Global.config().getInt(leverNode + ".y") != this.lever.getY()) {
				Global.config().set(leverNode + ".y",this.lever.getY());
				changed = true;
			}
			if(Global.config().getInt(leverNode + ".z") != this.lever.getZ()) {
				Global.config().set(leverNode + ".z",this.lever.getZ());
				changed = true;
			}
			
			//Check Frames
			String frameNode = machineNode + ".frameLocs";
			for(int i=1;i<=3;i++) {
				if(Global.config().getInt(frameNode + ".x" + i) != getFrame(i).getX()) {
					Global.config().set(frameNode + ".x" + i,getFrame(i).getX());
					changed = true;
				}
				if(Global.config().getInt(frameNode + ".y" + i) != getFrame(i).getY()) {
					Global.config().set(frameNode + ".y" + i,getFrame(i).getY());
					changed = true;
				}
				if(Global.config().getInt(frameNode + ".z" + i) != getFrame(i).getZ()) {
					Global.config().set(frameNode + ".z" + i,getFrame(i).getZ());
					changed = true;
				}
			}
			
			if(changed) Global.configa.saveConfig();
		} else {
			//Create node
			Global.config().createSection(machineNode);
			
			//Add type
			Global.config().set(machineNode + ".type", this.type);
			
			//Add world
			Global.config().set(machineNode + ".world", worldName);
			
			//Add lever
			String leverNode = machineNode + ".leverLoc";
			Global.config().set(leverNode + ".x",this.lever.getX());
			Global.config().set(leverNode + ".y",this.lever.getY());
			Global.config().set(leverNode + ".z",this.lever.getZ());
			
			//Add Frames
			String frameNode = machineNode + ".frameLocs";
			for(int i=1;i<=3;i++) {
				Global.config().set(frameNode + ".x" + i,getFrame(i).getX());
				Global.config().set(frameNode + ".y" + i,getFrame(i).getY());
				Global.config().set(frameNode + ".z" + i,getFrame(i).getZ());
			}
			
			Global.configa.saveConfig();
		}
		
		
	}
	
	public void delete() {
		String machineNode = "machines."+this.name;
		Global.config().set(machineNode, null);
		Global.configa.saveConfig();
	}
}
