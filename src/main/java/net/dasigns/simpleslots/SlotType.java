package net.dasigns.simpleslots;

import java.util.ArrayList;

import org.bukkit.inventory.ItemStack;

public class SlotType {
	private Integer cost;
	private Integer reward;
	private Integer fireworks;
	private ArrayList<String> cmds;
	private WeightedRandomGenerator wrg;


	public SlotType(String type) {
		wrg = new WeightedRandomGenerator();
		String typeNode = "types."+type;
		String rewardNode = typeNode+".reward";

		cost = Global.config.getInt(typeNode + ".cost");
		reward = Global.config.getInt(rewardNode+".money");
		fireworks = Global.config.getInt(rewardNode+".fireworks");
		cmds = new ArrayList<String>(Global.config.getStringList(rewardNode+".cmd"));
		
		for(Integer w : Global.config.getIntegerList(typeNode+".items")) {
			for(String s: Global.config.getStringList(typeNode+".items."+w)) {
				String[] data = s.split(",");
				ItemStack i = null;
				if(data.length < 2) break;
				if(data.length == 2) i = new ItemStack(Integer.parseInt(data[0]),1,Short.parseShort(data[1]));
				wrg.put(i, w);
			}
		}
	}

	public Integer getCost() {
		return cost;
	}

	public Integer getRewardMoney() {
		return reward;
	}

	public ArrayList<String> getRewardCmds() {
		return cmds;
	}

	public Integer getNumFireworks() {
		return fireworks;
	}
	
	public ArrayList<ItemStack> getItems() {
		ArrayList<ItemStack> i = new ArrayList<ItemStack>();
		for(Object o : wrg.getItems()) i.add((ItemStack) o);
		return i;
	}

	public ItemStack getNextItem() {
		return (ItemStack) wrg.next();
	}
}
