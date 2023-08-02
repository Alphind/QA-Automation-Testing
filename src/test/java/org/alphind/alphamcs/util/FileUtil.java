package org.alphind.alphamcs.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

;

public class FileUtil {

	public FileUtil() {

	}

	public void copyFile(String sourceFileName, String destinationFileName) {

		File source = new File(sourceFileName);
		File dest = new File(destinationFileName);
		try {
			FileUtils.copyFile(source, dest);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void moveFile(String sourceFileName, String destinationFileName) {

		File source = new File(sourceFileName);
		File dest = new File(destinationFileName);
		try {
			FileUtils.moveFile(source, dest);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
