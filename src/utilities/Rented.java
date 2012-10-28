/*****************************************************************
This is the Super class to have an object be rented.

@author Nick Carter, Tyler Hutek, Tyler McCarthy, Thomas Verstraete
@version Fall 2012
 *****************************************************************/
package utilities;

import java.util.GregorianCalendar;

import utilities.Rental;

import goEntBeta2.*;

public class Rented implements Comparable{

	private Rental rental;
	
	private Account renter;

	private GregorianCalendar rented;
	
	private GregorianCalendar returned;

	private int daysRented;
	
	private double costForRent;
	
	/*****************************************************************
	
	 *****************************************************************/
	public Rented (Rental rental, Account renter) {
		this.setRental(rental);
		this.renter = renter;
		rented = new GregorianCalendar();
	}

	/*****************************************************************
	
	 *****************************************************************/
	public GregorianCalendar getRented() {
		return rented;
	}
	
	/*****************************************************************
	
	 *****************************************************************/
	public void setRented(GregorianCalendar rented) {
		this.rented = rented;
	}

	/*****************************************************************
	
	 *****************************************************************/
	public GregorianCalendar getReturned() {
		return returned;
	}

	/*****************************************************************
	
	 *****************************************************************/
	public void setReturned(GregorianCalendar returned) {
		this.returned = returned;
	}

	/*****************************************************************
	
	 *****************************************************************/
	public int getDaysRented() {
		return daysRented;
	}

	/*****************************************************************
	
	 *****************************************************************/
	public void setDaysRented(int daysRented) {
		this.daysRented = daysRented;
	}

	/*****************************************************************
	
	 *****************************************************************/
	public double getCostForRent() {
		return costForRent;
	}
	
	public void countDays(){
		if (returned.before(rented))
			setDaysRented(0);
		else{
			GregorianCalendar startCal = rented;
			startCal.set(1, 0);
			startCal.set(10, 0);
			startCal.set(11, 0);
			startCal.set(12, 0);
			startCal.set(13, 0);
			startCal.set(14, 0);
			long start = startCal.getTimeInMillis();
			
			GregorianCalendar endCal = rented;
			endCal.set(1, 0);
			endCal.set(10, 0);
			endCal.set(11, 0);
			endCal.set(12, 0);
			endCal.set(13, 0);
			endCal.set(14, 0);
			long end = endCal.getTimeInMillis();
			
			setDaysRented((int) Math.ceil((end - start)/(1000*60*60*24)));
		}	
	}
	
	public void calculateCost(){
		countDays();
		costForRent = ((getRental().getCostPerDay())*(getDaysRented()));
	}
	
	@Override
	public int compareTo(Object o) {
		
		Rental other = (Rental) o;
		return 0;
		
		//this doesn't seem to compile try using the .getclass method.
//		int compare = this.title.compareTo(other.getTitle());
//		if ((this instanceof DVD) && (other instanceof VideoGame))
//			return -1;
//		else if ((this instanceof VideoGame) && (other instanceof DVD))
//			return 1;
//		else 
//			return compare;
	}

	public Rental getRental() {
		return rental;
	}

	public void setRental(Rental rental) {
		this.rental = rental;
	}
	

	
}
