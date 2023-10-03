package org.alphind.alphamcs.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigurationReader {

	private static Properties p = new Properties();
	
	static {
		InputStream fis;
		try {
			fis = new FileInputStream(
					System.getProperty("user.dir") + "\\config\\" + "configuration" + ".properties");
			p.load(fis);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static String getEnvironment() {
		return p.get("ENV").toString();
	}
	
	public static String getBrowser() {
		return p.get("BROWSER").toString();
	}
	
}
