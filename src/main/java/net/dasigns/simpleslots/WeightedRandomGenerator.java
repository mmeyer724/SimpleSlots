package net.dasigns.simpleslots;

import java.util.ArrayList;

public class WeightedRandomGenerator {
	private Integer weight;
	private ArrayList<Object> items;
	private ArrayList<Integer> weights;
	
	public WeightedRandomGenerator() {
		this.weight = 0;
		this.items = new ArrayList<Object>();
		this.weights = new ArrayList<Integer>();
	}
	
	public Integer put(Object i,Integer w) {
		items.add(i);
		weights.add(w);
		weight += w;
		return items.size() - 1;
	}
	
	public void remove(Integer i) {
		items.remove(i);
		weight -= weights.get(i);
		weights.remove(i);
	}
	
	public Integer getTotalWeight() {
		return weight;
	}
	
	public ArrayList<Object> getItems() {
		return items;
	}
	
	public Object next() {
		double random = Math.random() * getTotalWeight();
		for (int i=0;i<items.size();++i) {
		    random -= weights.get(i);
		    if (random <= 0.0D) return items.get(i);
		}
		return null;
	}
}
