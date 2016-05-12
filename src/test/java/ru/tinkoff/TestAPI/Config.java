package ru.tinkoff.TestAPI;

import java.io.FileReader;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Config {
	public Map<Integer,String> readConfig(FileReader name) {
		Map<Integer,String> testData = new HashMap<Integer, String>();
		Scanner in = new Scanner(name);
		in.useDelimiter(":");
		while(in.hasNextLine()){
			String data[] = in.nextLine().split(":");
			testData.put(Integer.parseInt(data[0]), data[1]);
		}
		return testData;
	}
}