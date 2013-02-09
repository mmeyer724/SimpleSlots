package net.dasigns.simpleslots;

import java.io.File;

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
}
