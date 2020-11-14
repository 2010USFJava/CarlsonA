package com.revature.ui;

import java.util.ArrayList;
import java.util.Scanner;

import com.revature.Meta.StringCheck;

public class StringCheckUI {
	private static int[] forbiddenNumbers = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };

	public static String scannerStringCheck(String wantedInput) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Please enter your " + wantedInput);
		String stringAnswer = scan.nextLine().trim();

		boolean keepGoing = false;
		// Opted to try parsing instead of "0" since user could enter 0000000 or the
		// like
		do {
			boolean userEnteredNumber = true;
			int intAnswer = 9000;
			try {
				intAnswer = Integer.parseInt(stringAnswer);
				keepGoing = passForbbidenNumberCheck(intAnswer);

			} catch (NumberFormatException e) {
				keepGoing = true;
			}

		} while (!keepGoing);

		return stringAnswer;

	}

	protected static boolean passForbbidenNumberCheck(int intAnswer) {
		for (int i = 0; i < forbiddenNumbers.length; i++) {
			if (intAnswer == forbiddenNumbers[i]) {
				System.out.println("You cannot use the numbers 0 through 9 as an input here. Please try again.");
				return false;
			}
		}
		return true;
	}

	// checks if the name has spaces between it and breaks it up
	private String checkName(String name, String partOfName) {
		Scanner scan = new Scanner(System.in);
		boolean usableAnswer = false;

		do {
			String[] nameParts = StringCheck.splitName(name);
			int arraySize = nameParts.length;
			if (arraySize == 1) {
				return name;

			} else {
				// declare variables if I need to break down a name
				String dashOption = StringCheck.dashifyName(nameParts);
				String removeSpace = StringCheck.removeSpacesFromName(nameParts);

				System.out.println("There are extra spaces in the " + partOfName + " \"" + name
						+ "\". \nWhat would you like to do?\n");
				String[] output = new String[3];
				output[0] = "Re-enter Name";
				output[1] = "Use " + dashOption;
				output[2] = "Use " + removeSpace;

				int answerNumber = numberScanner(output);
//						If the catch block does not initiate, this will be true and the loop will break
				usableAnswer = true;

				switch (answerNumber) {
				case 0:
					usableAnswer = false;
					System.out.println("Please reenter the " + partOfName + ":\n");
					name = scan.nextLine();
					break;
				case 1:
					name = dashOption;
					System.out.println(partOfName + " has been set to " + dashOption);
					break;
				case 2:
					name = removeSpace;
					System.out.println(partOfName + " has been set to " + removeSpace);
					break;
				default:
					System.out.println(answerNumber + " is not a vailid choice. Please try again.");
					usableAnswer = false;
					break;
				}

			}
		} while (!usableAnswer);
		return name;
	}

	public static int numberScanner(String[] choice) {
		return numberScanner(choice, null, null, false);
	}

	public static int numberScanner(String[] options, String submenuName, String[] subMenuChoices,
			boolean runSubmunuCode) {
		Scanner scan = new Scanner(System.in);
		int num = 0;

		for (String i : options) {
			System.out.println(num++ + ": " + i);
		}

		// run this bit of code If I want to have a submenu
		if (runSubmunuCode) {
			// replace options for remainder of code
			options = numberSubMenuMerge(options, submenuName, subMenuChoices, num);

		}

		boolean keepGoing = true;
		// initlilizing this so that I don't throw an error, but making it too long to
		// be selected by accident
		int selection = options.length + 1;

		do {

			try {
				keepGoing = true;
				String stringAnswer = scan.nextLine().trim();

				selection = Integer.parseInt(stringAnswer);
				System.out.println("You chose \"" + options[selection] + "\"");
			} catch (ArrayIndexOutOfBoundsException e) {
				keepGoing = false;
				System.out.println("Unfortunently, that was not a listed choice.\nPlease try again.");
			} catch (NumberFormatException e) {
				keepGoing = false;
				System.out.println("Please enter a number and try again.");
			} catch (Exception e) {
				keepGoing = false;
				e.printStackTrace();
				System.err.println("Why do you do this to me?");
			}

		} while (!keepGoing);

		scan.close();
		return selection;

	}

	private static String[] numberSubMenuMerge(String[] options, String submenuName, String[] subMenuChoices, int num) {
		System.out.println(submenuName);
		for (String i : subMenuChoices) {
			System.out.println("\t" + num++ + ": " + i);
		}

		// merge menu and sub menu for selection
		int mainL = options.length;
		int subL = subMenuChoices.length;
		String[] newMenu = new String[mainL + subL];
		System.arraycopy(options, 0, newMenu, 0, mainL);
		System.arraycopy(subMenuChoices, 0, newMenu, mainL, subL);

		// replace options for remainder of code
		options = newMenu;
		return options;
	}

}
