/*****************************************************************
The flint to start the whole program.
Then the controller takes over.


@author Nick Carter, Tyler Hutek, Tyler McCarthy, Thomas Verstraete
@version Fall 2012
 *****************************************************************/
package goEntBeta2;


public class Ignition {

	/**
	 * @param args
	 */
	public static void main(String[] cheese) {
		
		Controller control;
		
		try{
			
		control = new Controller();
		
		}
		catch (Exception e) {
			
		control = new Controller();
		e.printStackTrace();
		
		}
	}

}
