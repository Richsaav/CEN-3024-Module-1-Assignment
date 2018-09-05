package Module1;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Rich Saavedra
 * 
 * CEN 3024 Module 1 Assignment
 */
public class CallAttendant {

	private static Scanner keyboard; //Use a Scanner object for all user input.
	private static final int CALL_ATTENDANT = 1;
	private static final int DISPLAY_ACTIVE_CALL_BUTTONS = 2;
	private static final int TURN_OFF_ALL_ACTIVE_CALL_BUTTONS = 3;
	private static final int EXIT = 4;
	
	public static void main(String[] args) {
		
		int userChoice = 0;
		double seatNumber = 0;
		CallingSeat callingSeat1 = null;
		
		ArrayList<CallingSeat> callingSeat = new ArrayList<CallingSeat>();
		keyboard = new Scanner(System.in); 
		
		
		while (userChoice != EXIT)	
		{
			userChoice = getUserChoice();
			switch (userChoice)
			{
				case CALL_ATTENDANT:
					/*
					 * Call the flight attendant for service
					 */
					try 
					{
					seatNumber = getUniqueSeatNumber(callingSeat);
					callingSeat1 = new CallingSeat(seatNumber);
					} 
					catch (IllegalArgumentException exception) {
						System.out.println(exception);
					}
					if (seatNumber != 0)
					{
						callingSeat.add(callingSeat1);
					}
					break;
				case DISPLAY_ACTIVE_CALL_BUTTONS:
					/*
					 * Display all objects in the ArrayList 
					 */
					displaySeatsCalled(callingSeat);
					break;
				case TURN_OFF_ALL_ACTIVE_CALL_BUTTONS:
					/*
					 * Clear all objects from the ArrayList 
					 */
					clearAll(callingSeat);
					break;
				case EXIT:

					System.out.println("Thank you for using Call Attendant");
					keyboard.close();
					break;
			}

		}//end while
	}//end main

	

/**Menu displayed to the passenger
 * @return User choice
 * @throws NumberFormatException Exception thrown for invalid input
 */
public static int getUserChoice() throws NumberFormatException {
	int userResponse = 0;
	
	/*
	 * Redisplay the menu if an invalid value (not 1 through 5) is 
	 * entered and continue the application.
	 */
	String userInput;
	try{
		do 
		{
			System.out.println("Enter your choice: ");
			System.out.println(CALL_ATTENDANT + ". Call Flight Attendant");
			System.out.println(DISPLAY_ACTIVE_CALL_BUTTONS + ". Display all activated call buttons");
			System.out.println(TURN_OFF_ALL_ACTIVE_CALL_BUTTONS  + ". Turn off all activated call buttons");
			System.out.println(EXIT + ". Exit");
			userInput = keyboard.nextLine();
			if (userInput == null || userInput.equals("")) {
				System.out.println("You must enter a value.");
				return 99;
			}
			userResponse = Integer.parseInt(userInput);
			if (userResponse < CALL_ATTENDANT || userResponse > EXIT){
				System.out.println("Invalid value. Enter a value " + CALL_ATTENDANT + " - " + EXIT);
			}
		} while (userResponse < CALL_ATTENDANT || userResponse > EXIT);
	}
	catch (NumberFormatException n) {
		System.out.println("Enter numeric values only");
		System.out.println(n.getMessage());
	}
	return userResponse;
}

/**Displays all seats that have requested assistance
 * @param callingSeats Arraylist passed to method.
 */
public static void displaySeatsCalled(ArrayList<CallingSeat> callingSeats) {
	if (callingSeats.size() < 1){
		System.out.println("There are no activated call buttons.");
		return;
	}
	for (CallingSeat callingSeat : callingSeats){
		System.out.println(callingSeat);

	}
}

/**Turns off (clears) all active passenger call buttons
 * @param callingSeats callingSeats Arraylist passed to method.
 */
public static void clearAll(ArrayList<CallingSeat> callingSeats) {
	if (callingSeats.size() < 1){
		System.out.println("There are no activated call buttons.");
		return;
	}
	callingSeats.clear();

	}

/**Checks to see if the passenger has already pushed the button for assistance,
 * if not, returns the seat number to be added to the list of seats to be waited on.
 * If the seat is already on the list displays a message accordingly.
 * @param callingSeats callingSeats Arraylist passed to method.
 * @return the seatNumber
 */
public static int getUniqueSeatNumber (ArrayList<CallingSeat> callingSeats){
	
	int seatNumber = 0;
	boolean isFound = false;

	do {
		
		seatNumber = getInteger("Do you need the flight attendants assistance? Enter your seat number");
		isFound = false;
		for (CallingSeat seat : callingSeats) {
			if (seat.getSeatNumber() == seatNumber){
				isFound =  true;
			}
		}
		if (isFound){
			System.out.println("You have already requested assistance.  Please be patient and the attendant will be with you shortly.");
			seatNumber = 0;
			break;
		}
	} while (isFound);
	
	return seatNumber;
}

/**Accepts the passenger seat number input
 * @param prompt Message displayed to the passenger
 * @return Seat Number
 * @throws NumberFormatException Exception thrown for invalid input
 */
public static Integer getInteger(String prompt) throws NumberFormatException {
	int userInt = 0;
	boolean seatNum = true;
	
	do {
		try{
			
			System.out.println(prompt);
			String userValue = keyboard.nextLine();
			userInt =  Integer.parseInt(userValue);
			if ( userInt < 1 || userInt > 5) {
				seatNum = true;
			}
			else if (userInt >= 0) {
				seatNum = false;
			}
		} 

		catch (NumberFormatException n) {
			System.out.println("You must enter a value");
			seatNum = true;
		}
	} while (seatNum);	
	return userInt;
}

}
