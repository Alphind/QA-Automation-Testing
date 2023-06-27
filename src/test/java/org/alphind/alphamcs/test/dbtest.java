package org.alphind.alphamcs.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.alphind.alphamcs.util.DBUtil;

public class dbtest {

	public static void main(String[] args) throws IOException {
		
		Properties envConfig;
		
		String ENV = "dev";
		
		InputStream configFile = new FileInputStream(
				System.getProperty("user.dir") + "\\config\\" + ENV + ".properties");
		
		envConfig = new Properties();
		
		envConfig.load(configFile);
		
		String conStr = envConfig.getProperty("devDBConnectionString");
		
		DBUtil dbUtil = new DBUtil();

		dbUtil.executeSP(conStr, "asp_portal_claims_processing_wrapper");
		
	}
	
}
