package com.github.mangoperson.weedeaterv2.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Hashtable;

public class ConfigFile {
	Hashtable<String, String> vars = new Hashtable<String, String>();
	
	public ConfigFile(String filePath) throws IOException {
		String text = Files.readString(Path.of(filePath));
		String[] args = text.split("\n");
		
		for (String str : args) {
			String key = str.split(": ")[0];
			String value = str.replaceFirst(key + ": ", "").strip();
			
			vars.put(key, value);
		}
	}
	
	public String valueOf(String key) {
		return vars.get(key);
	}
}
