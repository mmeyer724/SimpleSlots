package net.dasigns.simpleslots;

import java.io.File;

import org.bukkit.Location;

public class Util {
	public static File createDir(File f) {
		if(!f.exists()) f.mkdirs();
		return f;
	}
	
	public static File createDir(String f) {
		File fl = new File(f);
		if(!fl.exists()) fl.mkdirs();
		return fl;
	}
	
	public static Boolean locationEqualsXYZ(Location l, Integer x, Integer y, Integer z) {
		if(l.getX() == x && l.getY() == y && l.getZ() == z) return true;
		return false;
	}
	
	public static Boolean locationEqualsXYZW(Location l, Integer x, Integer y, Integer z,String w) {
		if(l.getX() == x && l.getY() == y && l.getZ() == z && l.getWorld().getName() == w) return true;
		return false;
	}
}
