package org.alphind.alphamcs.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.Properties;

import org.alphind.alphamcs.base.TestBase;
import org.alphind.alphamcs.util.DBUtil;
import org.testng.annotations.Test;

/** Copyright (C) 2023  Alphind Solution Software Pvt. Ltd. - All Rights Reserved.
 * 
 *  created by  Nandhalala.
 *  
 *  You may use, distribute and modify this code for internal purpose,  however, distribution outside the organization     
 *  is prohibited without prior and proper license agreement
 *  
 */

public class dbtest extends TestBase{
    
    
    public static void main(String[] args) throws IOException, ParseException {
    	Properties envConfig;
    	
    	InputStream configFile = new FileInputStream(
				System.getProperty("user.dir") + "\\config\\" + "dev" + ".properties");
		envConfig = new Properties();
		envConfig.load(configFile);
    	
    	String conStr = envConfig.getProperty("devDBConnectionString");

        DBUtil dbutil = new DBUtil();

        //System.out.println(db.executeQuery(conStr, "select * from tb_cms1500s where clm_num="+mcsNumber));

        Map<String, String> map = dbutil.
        		executeQuery(conStr, "select * from tb_cms1500s where clm_num="+"250625");

     
        Map<String, String> map2 = dbutil.
        		executeQuery(conStr, "select Taxonomy_cd from tb_taxonomy_codes where Taxonomy_id = "
        				+map.get("Bd_prv_taxonomy_id_33b"));
        
        String patlastName=map.get("pat_ln_02");
        String RendNPI=map.get("srvc_ren_npi_32");
        String patFirstName=map.get("pat_fn_02");
        String patSignDate=map.get("pat_sign_dt_12");
        String diagCode=map.get("diag_1_21");
        String phySignDate=map.get("phy_sign_dt_31");
        String phoneNo=map.get("srvc_ren_ph_32");
        String billPrvTaxon=map.get("Bd_prv_taxonomy_id_33b");
        
        String[] a = phySignDate.split(" ");
        
        System.out.println(a[0]);
        
        System.out.println(a[1]);
        
        //System.out.println(java.util.Date.parse(phySignDate));;
        
        System.out.println(patlastName);
        
        System.out.println(Date.valueOf(a[0]));
        
        SimpleDateFormat sd = new SimpleDateFormat("MM/dd/YYYY");
        
        System.out.println(sd.format(Date.valueOf(a[0])));
        
       
       
        
	}
    
    @Test
    public void sample() {

		for (int i = 0; i < dataMapList.size(); i++) {

			dataMap = dataMapList.get(i);

			// Your test logic goes here

			System.out.println("Test execution #" + (i + 1));

			System.out.println(dataMap.get("gender"));

			// test logic ends here

		}

	}
	
	
}
