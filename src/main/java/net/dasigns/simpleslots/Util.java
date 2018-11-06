package net.dasigns.simpleslots;

import java.io.File;

import org.bukkit.Location;

public class Util {
	public static File createDir(File f) {
		if(f.exists() == false) {
			f.mkdirs();
		}

		return f;
	}
	
	public static File createDir(String f) {
		File fl = new File(f);

		if(fl.exists() == false) {
			fl.mkdirs();
		}

		return fl;
	}
	
	public static Boolean locationEqualsXYZ(Location l, Integer x, Integer y, Integer z) {
		if(l.getX() != x) {
			return false;
		}

		if(l.getY() != y) {
			return false;
		}

		if(l.getZ() != z) {
			return false;
		}

		return true;
	}
	
	public static Boolean locationEqualsXYZW(Location l, Integer x, Integer y, Integer z,String w) {
		if(l.getX() != x) {
			return false;
		}

		if(l.getY() != y) {
			return false;
		}

		if(l.getZ() != z) {
			return false;
		}

		if(l.getWorld().getName() != w) {
			return false;
		}

		return true;
	}
}
