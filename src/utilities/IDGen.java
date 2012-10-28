/*****************************************************************
Creates a unique ID number for each entry of either a rental
object or a user account. Both Methods are static so that no
IDGen needs to be instantiated

@author Nick Carter, Tyler Hutek, Tyler McCarthy, Thomas Verstraete
@version Fall 2012
 *****************************************************************/
package utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class IDGen {

	private static int rentalIDGen;
	
	private static int accountIDGen;
	
	/*****************************************************************
	Returns a unique ID int for a rental object.
	
	@return int Unique Id Number.
	 *****************************************************************/
	public static int getRentalID () {
		loadID();
		rentalIDGen ++;
		saveID();
		return rentalIDGen;
	}
	
	/*****************************************************************
	Returns a unique ID int for a user account.
	
	@return int Unique Id Number.
	 *****************************************************************/
	public static int getAccountID () {
		loadID();
		accountIDGen ++;
		saveID();
		return accountIDGen;
	}
	
	
	/*****************************************************************
	Loads the saved numbers.
	 *****************************************************************/
	private static void loadID () {
		//find the file
		Scanner scan;
		try {
			scan = new Scanner(new File (".RentalID.txt"));
		} 
		catch (FileNotFoundException e) {
			scan = new Scanner("SHIT");
		}

		rentalIDGen = scan.nextInt();
		accountIDGen = scan.nextInt();
		
	}

	/*****************************************************************
	Saves the ID numbers when the Class is not being used.
	 *****************************************************************/
	private static void saveID () {
		try {
			File bbfile = new File (".RentalID.txt");
			PrintWriter myoutput = new PrintWriter (bbfile);

			myoutput.println (rentalIDGen);
			myoutput.println (accountIDGen);
			
			myoutput.close(); 
		}
		catch (FileNotFoundException e)
		{
			System.err.println ("Could not open the file" + e.getMessage());
		}
	}
}
