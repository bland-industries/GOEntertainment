/*****************************************************************


@author Nick Carter, Tyler Hutek, Tyler McCarthy, Thomas Verstraete
@version Fall 2012
 *****************************************************************/
package goEntBeta2;

import java.util.GregorianCalendar;


import utilities.*;


public class VideoGameRented extends Rented{

	public final VideoGame rental;
	
	public VideoGameRented(Rental rental, Account renter) {
		super(renter);
		this.rental = (VideoGame) rental;
		
		
	}
	//think of what else there is needed for this
	

}
