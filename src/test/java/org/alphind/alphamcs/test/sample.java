package org.alphind.alphamcs.test;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Objects;

public class sample {

	public static void main(String[] args) {
		String a = "102 Patient does not have a valid NC Tracks Benefit Plan for DX submitted in claim "
				+ " | ";
		
		//System.out.println(a);
		
		String [] reasons = null;
		
		//System.out.println(regex);
		
		String c = "|";
		
		byte ptext[] = c.getBytes();
		String value = null;
		try {
			value = new String(ptext, "UTF-8");
			System.out.println(value);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		System.out.println(c);
		

		
			System.out.println(a.lastIndexOf('|'));
		
			reasons = a.split(value);
			
		if(Objects.nonNull(reasons))
			Arrays.asList(reasons).forEach(rsn -> {System.out.println(rsn+" : ");});
		
	}
	
}
