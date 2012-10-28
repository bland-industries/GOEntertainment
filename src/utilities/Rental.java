/*****************************************************************
Parent class for all the rental items. Contains the variables that
all items contain. Does most of the baisc lifting

@author Nick Carter, Tyler Hutek, Tyler McCarthy, Thomas Verstraete
@version Fall 2012
 *****************************************************************/
package utilities;

import goEntBeta2.Account;
import goEntBeta2.DVD; 
import goEntBeta2.VideoGame;

import java.util.ArrayList;
import java.util.GregorianCalendar;


public abstract class Rental implements Comparable{

	/**The unique ID number for each Rental Object */
	private int rentalID;
	
	/**Title of the Rental Unit */
	protected String title;
		
	/**Year Rental Unit was made */
	protected int year;
	
	/**Date and time the rental Unit was added */
	private GregorianCalendar dateAdded;
	
	/**Cost of the Rental Unit per day */
	private double costPerDay;
	
	/**Type of Rental unit from Enum RentalCategory */
	protected RentalCategory type;
	
	/**Content rating based on MPAA or ESRB */
	protected ContentRating content;
	
	/**Whether it is checked out or not*/
	protected boolean checked;
	
	/*****************************************************************
	Basic constructor sets the rental ID of the IDGen class and sets 
	the dateAdded. The other variables can be added as parameters or 
	use other methods for setting the variables
	 *****************************************************************/
	public Rental (){
		rentalID = IDGen.getRentalID();
		this.dateAdded = new GregorianCalendar();
	}
	
	public Rental (String title){
		rentalID = IDGen.getRentalID();
		this.title = title;
		this.dateAdded = new GregorianCalendar();
	}
	
	public Rental (String title, int year, RentalCategory type, 
			ContentRating content) {
				rentalID = IDGen.getRentalID();
				this.title = title;
				this.year = year;
				this.dateAdded = new GregorianCalendar();
				this.type = type;
				this.content = content;
	}

	/*****************************************************************
	this needs to take all the info from this object and return a 
	rented object 
	 *****************************************************************/
	public Rented rent(Account renter){
		this.checked = true;
		Rented newrent = new Rented(this,renter);
		return newrent;
	}
	
	public double getCostPerDay() {
		return costPerDay;
	}

	public void setCostPerDay(double costPerDay) {
		this.costPerDay = costPerDay;
	}

	public int getRentalID() {
		return rentalID;
	}

	public String getTitle() {
		return title;
	}

	public int getYear() {
		return year;
	}

	public GregorianCalendar getDateAdded() {
		return dateAdded;
	}

	public RentalCategory getType() {
		return type;
	}

	/*****************************************************************
	Comparable method. Return -1 if this object's title comes 
	before the parameter object's title, 1 if this object's title after 
	the	parameter object's title. 0 if both titles are equal.
	However movies should come before games, so a game starting with
	a comes after a movie starting with a y.

	@param other Object to compare this one to.
	@return int
	 *****************************************************************/
	@Override
	public int compareTo(Object o) {
		
//		Rental other = (Rental) o;
//		
//		//this doesn't seem to compile try using the .getclass method.
//		int compare = this.title.compareTo(other.getTitle());
//		if ((this instanceof DVD) && (other instanceof VideoGame))
//			return -1;
//		else if ((this instanceof VideoGame) && (other instanceof DVD))
//			return 1;
//		else 
//			return compare;
//		
		//**********************
		
		Rental other = (Rental) o;
		
		//among its other parameters, DVD's need arraylists created 
		//before they can be used to construct a new DVD.
		ArrayList<String> testlist = new ArrayList() ;
		
		//Creates simple classes so as to test whether 
		//this and other are DVD's or VideoGames
		DVD testdvd = new DVD("TEST",0000,RentalCategory.COMEDY,
		ContentRating.NR,"Joe Mama",testlist);
		
		VideoGame testgame = new VideoGame("TEST",0000,
		RentalCategory.COMEDY,ContentRating.NR,"JoeSoft",256);
		
		//this doesn't seem to compile try using the .getclass method.
		//Nick- Should work now, but there is probably a better way.
		
		int compare = this.title.compareTo(other.getTitle());
		// If this is a DVD and other is a VideoGame
		if ((this.getClass().equals(testdvd.getClass())) && 
		(other.getClass().equals(testgame.getClass())))
			return -1;
		// Else if this is a VideoGame and other is a DVD
		else if ((this.getClass().equals(testgame.getClass())) && 
		(other.getClass().equals(testdvd.getClass())))
			return 1;
		else 
			return compare;
		
		
		
		
		
	}
	
	

}
