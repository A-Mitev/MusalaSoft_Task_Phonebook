package com.phonebook;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class Phonebook {
	public static void main(String[] args) throws FileNotFoundException {

		File phoneBook = new File("PhoneBook.txt");
		if (!phoneBook.exists()) {
			throw new FileNotFoundException();
		}
		// polzvam TreeMap za da se nalivat sortirani po key (ime).
		TreeMap<String, String> peoples = new TreeMap<String, String>();
		String[] parts;
		BufferedReader br = new BufferedReader(new FileReader(phoneBook));
		try {
			String line = br.readLine();
			while ((line = br.readLine()) != null) {
				parts = line.split(", ");
				
				
				
				
				peoples.put(parts[0], parts[1]);

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		for (Map.Entry<String, String> entry : peoples.entrySet()) {
			System.out.println(entry.getKey() + " " + entry.getValue());
		}

	}
}
