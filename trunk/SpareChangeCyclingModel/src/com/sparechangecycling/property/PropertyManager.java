package com.sparechangecycling.property;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyManager {
	
	private static final long serialVersionUID = 7273347007638125359L;
	
	private String directory;
	
	private Properties props;
	
	public PropertyManager(String file) {
		props = new Properties();
		directory = System.getProperty("scc.path", "/home/lucas/javastuff");
	    try {
	        props.load(new FileInputStream(directory + File.separator + file));
	    } catch (IOException e) {
	    	throw new RuntimeException("Error loading properties file: " + file+" directory is "+directory);
	    }
	}
	
	public int getInt(String key) {
		return Integer.parseInt(props.getProperty(key));
	}
	
	public String get(String key) {
		return props.getProperty(key);
	}
	
	

}
