package net.dasigns.simpleslots;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import org.bukkit.inventory.ItemStack;

public class SlotType {
	private Integer cost;
	
	private HashMap<ItemStack,Integer> reward;
	private HashMap<ItemStack,Integer> fireworks;
	private HashMap<ItemStack,ArrayList<String>> cmds;
	
	private WeightedRandomGenerator wrg;


	public SlotType(String type) {
		reward = new HashMap<ItemStack,Integer>();
		fireworks = new HashMap<ItemStack,Integer>();
		wrg = new WeightedRandomGenerator();
		
		String typeNode = "types."+type;
		String rewardNode = typeNode+".reward";
		
		cost = Global.config().getInt(typeNode + ".cost");
		
		Set<String> keys = null;
		keys = Global.config().getConfigurationSection(rewardNode).getKeys(false);
		for(String k : keys) {
			String[] data = k.split(",");
			ItemStack match = null;
			if(data.length < 2) break;
			if(data.length == 2) match = new ItemStack(Integer.parseInt(data[0]),1,Short.parseShort(data[1]));
			
			reward.put(match,Global.config().getInt(rewardNode+"."+k+".money"));
			fireworks.put(match,Global.config().getInt(rewardNode+"."+k+".fireworks"));
			cmds.put(match,new ArrayList<String>(Global.config().getStringList(rewardNode+"."+k+".cmd")));
		}
		
		for(Integer w : Global.config().getIntegerList(typeNode+".items")) {
			for(String s: Global.config().getStringList(typeNode+".items."+w)) {
				String[] data = s.split(",");
				ItemStack i = null;
				if(data.length < 2) break;
				if(data.length == 2) i = new ItemStack(Integer.parseInt(data[0]),1,Short.parseShort(data[1]));
				wrg.add(i, w);
			}
		}
	}

	public Integer getCost() {
		return cost;
	}

	public Integer getRewardMoney(ItemStack i) {
		return reward.get(i);
	}

	public ArrayList<String> getRewardCmds(ItemStack i) {
		return cmds.get(i);
	}

	public Integer getNumFireworks(ItemStack i) {
		return fireworks.get(i);
	}
	
	public ArrayList<ItemStack> getItems() {
		ArrayList<ItemStack> i = new ArrayList<ItemStack>();
		for(Object o : wrg.getItems()) i.add((ItemStack) o);
		return i;
	}

	public ItemStack getNextItem() {
		return (ItemStack) wrg.next();
	}
	
	public static Boolean slotTypeExists(String type) {
		return Global.config().contains("types."+type);
	}
}
