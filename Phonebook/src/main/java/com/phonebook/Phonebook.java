package com.phonebook;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class Phonebook {
	private static final String REGEX_LAST_SIX_DIGIT = "[0-9]+";
	private static final int REGULAR_PHONE_LENGTH = 10;
	private static final String REGEX_FOURTH_DIGIT = "[2-9]";

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
				String name = parts[0];
				String phoneNumber = parts[1];
				if (phoneValidation(phoneNumber)) {
					String normalPhone = phoneNumber.replace(phoneNumber.substring(0, 1), "+359");
					peoples.put(name, normalPhone);
				}
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
		System.out.println("\n" + "Dobaveni v Map-a nomera sled validirane i normalizirane:");
		for (Map.Entry<String, String> entry : peoples.entrySet()) {
			System.out.println(entry.getKey() + " " + entry.getValue());
		}

	}

	public static boolean phoneValidation(String number) {
		if ((number.length() == REGULAR_PHONE_LENGTH)
				&& (number.startsWith("087") || (number.startsWith("088") || (number.startsWith("089"))))) {
			if ((number.substring(3, 4).matches(REGEX_FOURTH_DIGIT)
					&& (number.substring(4, 10).matches(REGEX_LAST_SIX_DIGIT)))) {
				System.out.println("Validen nomer "  + " " +  number); // za proverka
				return true;
			} else {
				System.out.println("Nevalidna 4-ta cira ot nomera za nomer " + " " + number); // za proverka
				return false;
			}

		} else {
			System.out.println("nevaliden nomer" + " " + number);  // za proverka
			return false;
		}

	}
}
