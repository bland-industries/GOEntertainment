/*****************************************************************
Parent class for all the rental items. Contains the variables that
all items contain. Does most of the baisc lifting

@author Nick Carter, Tyler Hutek, Tyler McCarthy, Thomas Verstraete
@version Fall 2012
 *****************************************************************/
package utilities;

import goEntBeta2.Account;
import goEntBeta2.DVD; 


import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.swing.ImageIcon;


public abstract class Rental implements Comparable, Serializable{
	
	private static final long serialVersionUID = 1L;

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
	
	/**Content rating based on MPAA or FCC */
	protected ContentRating content;
	
	/**Whether it is checked out or not*/
	protected boolean checked;
	
	/**Location of Image Icon*/
	protected String iconLocation;
	
	/**Names of Primary Actors*/
	protected String actor1;
	protected String actor2;
	protected String actor3;
	protected String actor4;
	protected String actor5;

	/*****************************************************************
	Basic constructor sets the rental ID of the IDGen class and sets 
	the dateAdded. The other variables can be set using setters
	 *****************************************************************/
	public Rental (){
		rentalID = IDGen.getRentalID();
		this.dateAdded = new GregorianCalendar();
	}
	
	/*****************************************************************
	Title constructor sets the rental ID of the IDGen class, sets 
	the dateAdded, and sets the title. The other variables can be set 
	using setters
	@param title Title of the Rental
	 *****************************************************************/
	public Rental (String title){
		rentalID = IDGen.getRentalID();
		this.title = title;
		this.dateAdded = new GregorianCalendar();
	}
	
	/*****************************************************************
	Full constructor sets all properties of the Rental besides those
	that are instance specific between DVD's and TVSeasons. Those are
	determined in the constructors for the respective classes
	@param title Title of the Rental
	@param year Year Rental Unit was made
	@param type Type of Rental unit from Enum RentalCategory
	@param content Content rating based on MPAA or FCC
	@param actor1 Name of the first primary actor.
	@param actor2 Name of the second primary actor.
	@param actor3 Name of the third primary actor.
	@param actor4 Name of the fourth primary actor.
	@param actor5 Name of the fifth primary actor.
	 *****************************************************************/
	public Rental (String title, int year, RentalCategory type, 
			ContentRating content, String actor1 , String actor2 , 
			String actor3 , String actor4 , String actor5, String iconLocal){
				rentalID = IDGen.getRentalID();
				this.title = title;
				this.year = year;
				this.dateAdded = new GregorianCalendar();
				this.type = type;
				this.content = content;
				this.actor1 = actor1;
				this.actor2 = actor2;
				this.actor3 = actor3;
				this.actor4 = actor4;
				this.actor5 = actor5;
				iconLocation = iconLocal;
	}

	/*****************************************************************
	"Rents" this instance of Rental and creates a Rented class using
	itself and renter as parameters. Also sets this Rental as checked
	out
	@param renter Account renting this Rental
	@return Rented
	 *****************************************************************/
	public Rented rent(Account renter){
		this.checked = true;
		Rented newrent = new Rented(this,renter);
		return newrent;
	}
	
	/*****************************************************************
	Returns the amount of money the account must pay per day this
	Rental is rented
	@return double
	 *****************************************************************/
	public double getCostPerDay() {
		return costPerDay;
	}

	/*****************************************************************
	Sets the amount of money the account must pay per day thisRental is
	rented
	@param costPerDay amount of money
	 *****************************************************************/
	public void setCostPerDay(double costPerDay) {
		this.costPerDay = costPerDay;
	}

//	/*****************************************************************
//	Returns the unique ID of this Rental
//	@return int
//	 *****************************************************************/
//	public int getRentalID() {
//		return rentalID;
//	}

	/*****************************************************************
	Returns the title of this Rental
	@return String
	 *****************************************************************/
	public String getTitle() {
		return title;
	}

	/*****************************************************************
	Returns the time that the Rental was originally created
	@return int
	 *****************************************************************/
	public int getYear() {
		return year;
	}

	/*****************************************************************
	Returns time Rental was added to the system
	@return GregorianCalendar
	 *****************************************************************/
	public GregorianCalendar getDateAdded() {
		return dateAdded;
	}

	/*****************************************************************
	Returns the genre or type of the rental
	@return RentalCategory
	 *****************************************************************/
	public RentalCategory getType() {
		return type;
	}
	
	/*****************************************************************
	Returns the content of the Rental according to MPAA or FCC 
	@return ContentRating
	 *****************************************************************/
	public ContentRating getContent() {
		return content;
	}

	/*****************************************************************
	Sets the content of the Rental according to MPAA or FCC 
	@param content contentRating ENUM of rating of Rental
	 *****************************************************************/
	public void setContent(ContentRating content) {
		this.content = content;
	}

	/*****************************************************************
	Returns whether the Rental is rented or not
	@return boolean
	 *****************************************************************/
	public boolean isChecked() {
		return checked;
	}

	/*****************************************************************
	Sets whether the Rental is rented or not
	@param rchecked true if checked out, false if not checked out
	 *****************************************************************/
	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	/*****************************************************************
	Returns the address of the location of this Rental's icon
	@return String
	 *****************************************************************/
	public String getIconLocation() {
		return iconLocation;
	}

	/*****************************************************************
	Sets the address of the location of this Rental's icon
	@param  iconLocation location of this Rental's icon
	 *****************************************************************/
	public void setIconLocation(String iconLocation) {
		this.iconLocation = iconLocation;
	}

	/*****************************************************************
	Sets the Title of the Rental
	@param title Title of the Rental
	 *****************************************************************/
	public void setTitle(String title) {
		this.title = title;
	}

	/*****************************************************************
	Sets the year the Rental was originally created
	@param year year Rental was originally created
	 *****************************************************************/
	public void setYear(int year) {
		this.year = year;
	}

//	/*****************************************************************
//	Sets the date and time the Rental added to the store.
//	@param dateAdded GregorianCalendar Time the Rental was added.
//	 *****************************************************************/
//	public void setDateAdded(GregorianCalendar dateAdded) {
//		this.dateAdded = dateAdded;
//	}

	/*****************************************************************
	Sets the genre of the Rental
	@param type Genre of the Rental
	 *****************************************************************/
	public void setType(RentalCategory type) {
		this.type = type;
	}
	
	/*****************************************************************
	Returns the Name of the first primary actor.
	@return String
	 *****************************************************************/
	public String getActor1() {
		return actor1;
	}

	/*****************************************************************
	Sets the Name of the first primary actor.
	@param actor1 Name of the first primary actor.
	 *****************************************************************/
	public void setActor1(String actor1) {
		this.actor1 = actor1;
	}

	/*****************************************************************
	Returns the Name of the second primary actor.
	@return String
	 *****************************************************************/
	public String getActor2() {
		return actor2;
	}

	/*****************************************************************
	Sets the Name of the second primary actor.
	@param actor2 Name of the second primary actor.
	 *****************************************************************/
	public void setActor2(String actor2) {
		this.actor2 = actor2;
	}

	/*****************************************************************
	Returns the Name of the third primary actor.
	@return String
	 *****************************************************************/
	public String getActor3() {
		return actor3;
	}

	/*****************************************************************
	Sets the Name of the third primary actor.
	@param actor3 Name of the third primary actor.
	 *****************************************************************/
	public void setActor3(String actor3) {
		this.actor3 = actor3;
	}

	/*****************************************************************
	Returns the Name of the fourth primary actor.
	@return String
	 *****************************************************************/
	public String getActor4() {
		return actor4;
	}

	/*****************************************************************
	Sets the Name of the fourth primary actor.
	@param actor4 Name of the fourth primary actor.
	 *****************************************************************/
	public void setActor4(String actor4) {
		this.actor4 = actor4;
	}

	/*****************************************************************
	Returns the Name of the fifth primary actor.
	@return String
	 *****************************************************************/
	public String getActor5() {
		return actor5;
	}

	/*****************************************************************
	Sets the Name of the fifth primary actor.
	@param actor5 Name of the fifth primary actor.
	 *****************************************************************/
	public void setActor5(String actor5) {
		this.actor5 = actor5;
	}

	/*****************************************************************
	Sets the unique numerical ID of the rental
	@param rentalID ID of the rental
	 *****************************************************************/
	public ImageIcon getImage() {
		return new ImageIcon ("Images/MovieImages/" + iconLocation);
	}

	/*****************************************************************
	Comparable method. Return -1 if this object's title comes 
	before the parameter object's title, 1 if this object's title after 
	the	parameter object's title. 0 if both titles are equal.

	@param other Object to compare this one to.
	@return int
	 *****************************************************************/
	@Override
	public int compareTo(Object o) {

		Rental other = (Rental) o;
		int compare = this.title.compareTo(other.getTitle());
		return compare;
		
	}
	
	public abstract String saveString ();
	

	public boolean equals(Object obj){
		return (this.title.equals(((Rental) obj).getTitle()));

	}
}
