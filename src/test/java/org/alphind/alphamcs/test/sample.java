package org.alphind.alphamcs.test;

import org.testng.annotations.Test;

public class sample {
	
	@Test
	public void test() {
		System.out.println(new Object(){}.getClass().getSimpleName());
		System.out.println(this.getClass().getSimpleName());
	}
	
}
