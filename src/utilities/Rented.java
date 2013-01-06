/*****************************************************************
This is the Super class to have an object be rented.

@author Nick Carter, Tyler Hutek, Tyler McCarthy, Thomas Verstraete
@version Fall 2012
 *****************************************************************/
package utilities;

import java.io.Serializable;
import java.util.GregorianCalendar;

import utilities.Rental;

import goEntBeta2.*;

public class Rented implements Comparable, Serializable{
	
	private static final long serialVersionUID = 1L;

	/** The Rental that is being rented*/
	public final Rental rental;
	
	/** The Account that is renting rental*/
	public final Account renter;
    
	/** Time rental is rented*/
	private GregorianCalendar rented;
	
	/** Time rental is returned*/
	private GregorianCalendar returned;
    
	/** Time between rented and returned in days*/
	private int daysRented;
	
	/** rental's costPerDay times daysRented*/
	private double costForRent;
	
	/*****************************************************************
	Creates a Rented class and sets the date it was rented.
	@param rental Defines what rental is rented.
	@param renter Defines who is renting the rental.
	 *****************************************************************/
	public Rented (Rental rental, Account renter) {
		this.rental = rental;
		this.renter = renter;
		rented = new GregorianCalendar();
	}

	/*****************************************************************
	Returns the time that the Rented class was created, or when its
	corresponding Rental was rented.
	@return GregorianCalendar
	 *****************************************************************/
	public GregorianCalendar getRented() {
		return rented;
	}
	
	/*****************************************************************
	Returns the Rental that has been rented to form the Rented class.
	@return Rental
	 *****************************************************************/
	public Rental getRental() {
		return rental;
	}	
	
	/*****************************************************************
	Sets the time that the Rented class was created, or when its
	corresponding Rental was rented.
	@param rented GregorianCalendar of when the Rental was rented.
	 *****************************************************************/
	public void setRented(GregorianCalendar rented) {
		this.rented = rented;
	}

	/*****************************************************************
	Returns the time that the Rental was returned to the store.
	@return GregorianCalendar
	 *****************************************************************/
	public GregorianCalendar getReturned() {
		return returned;
	}

	/*****************************************************************
	Sets the time the Rental was returned to the store.
	@param returned GregorianCalendar Time the Rental was returned.
	 *****************************************************************/
	public void setReturned(GregorianCalendar returned) {
		this.returned = returned;
	}

	/*****************************************************************
	Returns number of days between rented and returned, rounding up.
	Used for calculating the cost of a rental.
	@return int
	 *****************************************************************/
	public int getDaysRented() {
		return daysRented;
	}

	/*****************************************************************
	Sets number of days between rented and returned, used for 
	calculating the cost of a rental.
	@param daysRented Number of days between returned and rented.
	 *****************************************************************/
	public void setDaysRented(int daysRented) {
		this.daysRented = daysRented;
	}

	/*****************************************************************
	Returns the cost per day of the Rented's Rental.
	@return double
	 *****************************************************************/
	public double getCostForRent() {
		return costForRent;
	}
	
	/*****************************************************************
	Defaults to 0 days, otherwise subtracts the beginning time from the
	end time to determine the length of the rental period, then rounds
	up to the nearest day. 
	 *****************************************************************/
	public void countDays(){
		
		int timeDiff = returned.compareTo(rented);
		double second = 1000;
		double minute = 60;
		double hour = 60;
		double day = 24;
		
		double fDays = ((((timeDiff/second)/minute)/hour)/day);
		daysRented = ((int) fDays);
		if (daysRented <= 0)
			daysRented = 1;
		System.out.println(daysRented);
		
//		if (returned.before(rented))
//			setDaysRented(1);


//		if (returned.before(rented))
//			setDaysRented(1);
//		else{
//			GregorianCalendar startCal = rented;
//			long start = startCal.getTimeInMillis();
//			
//			GregorianCalendar endCal = rented;
//			long end = endCal.getTimeInMillis();
//			
//			setDaysRented((int) Math.ceil((end - start)/(1000*60*60*24)));
//			System.out.println(daysRented);
//		}	
	}
	
	/*****************************************************************
	Calculates and sets costForRent based on the costPerDay ofRented's
	Rental and the number of days it has been rented. 
	 *****************************************************************/
	public void calculateCost(){
		countDays();
		costForRent = ((getRental().getCostPerDay())*(getDaysRented()));
	}
	
	/*****************************************************************
	Calculates cost and unrents Rented's Rental
	 *****************************************************************/
	public void returnRental(){
		rental.setChecked(false);
		returned = new GregorianCalendar();
		returned.set(2012, 10, 29);
		calculateCost();
	}
	/*****************************************************************
	Comparable method. Return -1 if this Rented's Rental's title comes 
	before the parameter object's Rental's title, 1 if this object's 
	Rental's title comes after the parameter object's Rental's title. 
	0 if both titles are equal.
	@param other Object to compare this one to.
	@return int
	 *****************************************************************/
	@Override
	public int compareTo(Object o) {
		
		Rented other = (Rented) o;
		int compare = this.getRental().getTitle().compareTo(other.getRental().getTitle());
		return compare;
		
	}
	
	public boolean equals(Object obj){
		if (obj instanceof Rental)
			return (this.rental.equals((Rental)obj));
		else if (obj instanceof Rented)
			return (this.rental.equals(((Rented)obj).rental));
		else
			return false;
	}
	
	
	public String toString () {
		String str = rental.getTitle();
		str = str + " | checked out: " + rental.isChecked();
		str = str + " | rented by: " + renter.geteMail();
		str = str + " | days rented: " + daysRented;
		str = str + " | cost for rent: " + costForRent;
		return str;

	}
}
