/*****************************************************************


@author Nick Carter, Tyler Hutek, Tyler McCarthy, Thomas Verstraete
@version Fall 2012
 *****************************************************************/
package goEntBeta2;

import java.util.GregorianCalendar;

import utilities.*;

public class DVDRented extends Rented {
	
	public final DVD rental;
	
	
	public DVDRented(Rental rental, Account renter) {
		super(renter);
		this.rental = (DVD) rental;
		
	}
	
	//think of what else there is needed for this
	
	
}
