package net.dasigns.simpleslots;

import java.util.ArrayList;
import java.util.Random;

public class WeightedRandomGenerator {
	private Random r = new Random();
	
	private ArrayList<Object> selection = new ArrayList<Object>();
	private ArrayList<Integer> weight = new ArrayList<Integer>();
	private ArrayList<Integer> weightsum = new ArrayList<Integer>();
	
	public WeightedRandomGenerator() {
		selection = new ArrayList<Object>();
		weight = new ArrayList<Integer>();
		weightsum = new ArrayList<Integer>();
	}
	
	public void add(Object item, Integer weight) {
		this.selection.add(item);
		this.weight.add(weight);
		if(this.weightsum.size() == 0) this.weightsum.add(weight); 
		else this.weightsum.add(this.weightsum.get(this.weightsum.size()-1) + weight);
	}
	
	public Object next() {
		if(this.selection.size() == 0 || this.weight.size() == 0 || this.weightsum.size() == 0) return null;
		Integer j = r.nextInt(this.weightsum.get(this.weightsum.size()-1));
		Integer i;
		for(i = 0; j > this.weightsum.get(i); i++);
		return this.selection.get(i);
	}
	
	public ArrayList<Object> getItems() {
		return selection;
	}
	
	public void clear() {
		selection = new ArrayList<Object>();
		weight = new ArrayList<Integer>();
		weightsum = new ArrayList<Integer>();
	}
}
