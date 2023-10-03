package org.alphind.alphamcs.runner;

import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;

public class runner {

	public static void main(String[] args) {
		TestNG testng = new TestNG();
		List<String> suitefiles = new ArrayList<String>();
		suitefiles.add((".\\CreateAndSubmitCMS1500.xml"));
		testng.setTestSuites(suitefiles);
		testng.run();
	}
	
}
