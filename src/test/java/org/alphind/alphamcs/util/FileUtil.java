package org.alphind.alphamcs.util;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

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

	public String findFile(String partialFileName, String folderName) {
		File[] listFiles = new File(folderName).listFiles();
		Arrays.sort(listFiles, Comparator.comparingLong(File::lastModified).reversed());
		String fullFileName = "File Not Found";
		for (int i = 0; i < listFiles.length; i++) {

			if (listFiles[i].isFile()) {
				String fileName = listFiles[i].getName();
				// if (fileName.startsWith(partialFileName) && fileName.endsWith(".txt"))
				if (fileName.contains(partialFileName)) {
					System.out.println("found file" + " " + fileName);
					fullFileName = fileName;
				}
			}
		}
		return fullFileName;
	}
}
