package ru.tinkoff.TestAPI;

import java.io.FileReader;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Config {
	public Map<String,Integer> readConfig(FileReader name) {
		Map<String,Integer> testData = new HashMap<String, Integer>();
		Scanner in = new Scanner(name);
		in.useDelimiter(":");
		while(in.hasNextLine()){
			String data[] = in.nextLine().split(":");
			testData.put(data[0], Integer.parseInt(data[1]));
		}
		return testData;
	}
}