import java.io.*;
import java.util.*;

public class mainUI {

	// Retrieve data to the end of the line as an argument for a method call
	// Include two special kinds of arguments:
	//   "null" asks us to return no string
	//   "empty" asks us to return an empty string
	
	private static String getEndingString(Scanner userInput ) {
		String userArgument = null;

		userArgument = userInput.nextLine();
		userArgument = userArgument.trim();

		// Include a "hack" to provide null and empty strings for testing
		if (userArgument.equalsIgnoreCase("empty")) {
			userArgument = "";
		} else if (userArgument.equalsIgnoreCase("null")) {
			userArgument = null;
		}

		return userArgument;
	}

	// We're often returning a HashObject from the user.  Centralize the code to create that 
	// object.

	private static HashObject getHashObjectArgument( Scanner userInput, int selectedHashFunction ) {
		String userArgument;
		HashObject itemToHandle;

		// Get the parameters.
				
		userArgument = getEndingString( userInput );
						
		// Call the method
		
		itemToHandle = new HashObject( userArgument, selectedHashFunction );

		return itemToHandle;
	}

	// Main program to process user commands.
	// This method is not robust.  When it asks for a command, it expects all arguments to be there.
	// It is a quickly-done test harness rather than a full solution for an assignment.

	public static void main(String[] args) {
		// Command options

		String addCommand = "add";
		String findCommand = "find";
		String newCommand = "new";
		String deleteCommand = "delete";
		String printCommand = "print";
		String quitCommand = "quit";

		// Define variables to manage user input

		String userCommand = "";
		String userArgument = "";
		Scanner userInput = new Scanner( System.in );

		// Define the recommender that we will be testing.

		MyHashTable testTable = null;

		// Define variables to catch the return values of the methods

		boolean booleanOutcome;
		Integer findOutcome;
		HashObject itemToHandle;

		// Let you choose a hash function at the start

		int selectedHashFunction = -1;
		
		while ((selectedHashFunction < HashObject.FirstLetterHash) || 
		       (selectedHashFunction > HashObject.StringHashCode)) {
			System.out.println( "Which hash function type do you want to use?" );
			System.out.println( "  " + HashObject.FirstLetterHash + " - first letter of the string" );
			System.out.println( "  " + HashObject.FirstWordAsHash + " - integer value of the first string word" );
			System.out.println( "  " + HashObject.StringHashCode + " - String hashCode() method" );

			selectedHashFunction = Integer.parseInt( getEndingString( userInput ) );
		}

		// Let the user know how to use this interface
			
		System.out.println("Commands available:");
		System.out.println("  " + newCommand + " <int as size> <int as maxSize>" );
		System.out.println("  " + addCommand + " <string to the end of line>");
		System.out.println("  " + findCommand + " <string to the end of line>");
		System.out.println("  " + deleteCommand + " <string to the end of line>" );
		System.out.println("  " + printCommand );
		System.out.println("  " + quitCommand);
			
		// Process the user input until they provide the command "quit"

		do {
			// Find out what the user wants to do
			userCommand = userInput.next();
				
			/* Do what the user asked for. */

			if (userCommand.equalsIgnoreCase(newCommand)) {
				// Get the parameters.
				int size = userInput.nextInt();
				int maxSize = Integer.parseInt( getEndingString( userInput ) );
					
				// Call the method
					
				try {	
					testTable = new MyHashTable( size, maxSize );
					System.out.println( "new object created" );
				} catch (Exception e) {
					System.out.println( "constructor failed" );
				}
			} else if (userCommand.equalsIgnoreCase(addCommand)) {
				itemToHandle = getHashObjectArgument( userInput, selectedHashFunction );
				booleanOutcome = testTable.add( itemToHandle );
				System.out.println(userCommand + " \""+itemToHandle.getString()+"\" outcome " + booleanOutcome );
			} else if (userCommand.equalsIgnoreCase(findCommand)) {
				HashObject returnedObject;

				itemToHandle = getHashObjectArgument( userInput, selectedHashFunction );
				returnedObject = testTable.find( itemToHandle );
				if (returnedObject != null) {
					System.out.println(userCommand + " \"" + itemToHandle.getString() + "\" outcome \"" + returnedObject.getString() + "\"" );
				} else {
					System.out.println(userCommand + " \"" + itemToHandle.getString() + "\" outcome null object" );
				}
			} else if (userCommand.equalsIgnoreCase(deleteCommand)) {
				itemToHandle = getHashObjectArgument( userInput, selectedHashFunction );
				booleanOutcome = testTable.delete( itemToHandle );
				System.out.println(userCommand + " \""+itemToHandle.getString()+"\" outcome " + booleanOutcome );
			} else if (userCommand.equalsIgnoreCase(printCommand)) {
				// Clear the rest of the line text.

				userArgument = getEndingString( userInput );

				// Call the method

				String tableSizes;
				tableSizes = testTable.printSizes( );
				System.out.println(userCommand + " \""+userArgument+"\" outcome " );
				if (tableSizes != null) {
					System.out.println(tableSizes);
				} else {
					System.out.println( "null string retrned" );
				}
			} else if (userCommand.equalsIgnoreCase(quitCommand)) {
				System.out.println ( userCommand );
			} else {
				System.out.println ("Bad command: " + userCommand);
			}
		} while (!userCommand.equalsIgnoreCase("quit"));

		// The user is done so close the stream of user input before ending.

		userInput.close();
	}
}

