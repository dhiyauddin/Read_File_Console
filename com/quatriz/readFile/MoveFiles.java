package com.quatriz.readFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import common.Constant;

public class MoveFiles {

	// static final Logger logger = Logger.getLogger(MoveFiles.class);

	public String execute() {
		boolean flag = false;
		String situation = "";
		Constant obj = new Constant();
		Properties prop = new Properties();
		try {
			// FileInputStream access = new
			// FileInputStream("/opt/tomcat/conf/config.propeties");
			String propFileName = "config.properties";
			InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
			prop.load(inputStream);
			String temp = prop.getProperty("tempUpload");
			// logger.debug(temp);
			System.out.println(temp);
			String archive = prop.getProperty("archive");
			// logger.debug(archive);
			System.out.println(archive);

			File folder = new File(temp);
			ArrayList<String> recordList = new ArrayList<String>();
			recordList = listFilesForFolder(folder);
			for (int i = 0; i < recordList.size(); i++) {

				String pathFrom = temp;
				String pathTo = archive;

				 File fromFile = new File(temp + "/" + recordList.get(i));
				 File toFile = new File(archive + "/" + recordList.get(i));

				//File fromFile = new File(temp + "\\" + recordList.get(i));
				//File toFile = new File(archive + "\\" + recordList.get(i));

				Path path = Files.move(fromFile.toPath(), toFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

				// flag = fromFile.renameTo(toFile);

				if (!"".equals(path.toString())) {
					flag = true;
				} else {
					flag = false;
					break;
				}
			}
		} catch (Exception e) {
			// logger.debug(e);
			System.out.println(e);
		}

		if (flag) {
			// logger.debug("flag situation: " + flag);
			System.out.println("flag situation: " + flag);
			situation = obj.ReadFileConsole_SuccessMoveFile;
		} else {
			// logger.debug("flag situation: " + flag);
			System.out.println("flag situation: " + flag);
			situation = obj.ReadFileConsole_UnsuccesfulMoveFile;
		}
		return situation;
	}

	static ArrayList<String> listFilesForFolder(final File folder) {
		ArrayList<String> fileNameWrap = new ArrayList<String>();
		String fileName = "";
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				listFilesForFolder(fileEntry);
			} else {
				// logger.debug(fileEntry.getName());
				System.out.println(fileEntry.getName());
				fileName = fileEntry.getName();
				fileNameWrap.add(fileName);
			}
		}
		return fileNameWrap;
	}

}
