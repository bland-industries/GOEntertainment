/*****************************************************************
This is the user account object that contains all the information
for each user. This includes all the typical, address, email, 
and account id. This also has the password String. Last it contains
the rental history of the account, and the favorite lists and 
reserved lists.

@author Nick Carter, Tyler Hutek, Tyler McCarthy, Thomas Verstraete
@version Fall 2012
 *****************************************************************/
package goEntBeta2;

import java.io.Serializable;

import utilities.*;

public class Account implements Comparable, Serializable{
	
	private static final long serialVersionUID = 1L;

	/**This is the unique accountID*/
	int accountID;

	/**First name on the account*/
	String firstName;

	/**Last name on the account*/
	String lastName;

	/** the username for an account */
	String eMail; 

	/**The street address of an account */
	String address;

	/** the password of an account */
	String password;

	/***/
	RentedList checkedOutList;

	/** the history of DVD's/Movies rented */
	RentedList rentalHistory;

	/** a reserved list of DVD's and Movies */
	RentalList reservedList;

	/** A list of favorite Movies and DVD's */
	RentalList favoritesList;

	/*****************************************************************
	 *Returns the list of checked out items
	 *****************************************************************/
	public RentedList getCheckedOutList() {
		return checkedOutList;
	}

	/*****************************************************************
	 *sets the list of checked out items to the passed checked items
	 *@param checkedOutList the list of checked out items
	 *****************************************************************/
	public void setCheckedOutList(RentedList checkedOutList) {
		this.checkedOutList = checkedOutList;
	}

	/*****************************************************************
	 *sets a reserved list parameter equal to the reserved list
	 *@param reservedList the new reserved list to be set
	 *****************************************************************/
	public void setReservedList(RentalList reservedList) {
		this.reservedList = reservedList;
	}

	/** The amount owed on an account */
	double amountOwed;

	/*****************************************************************
	 *The constructor for an account
	 *****************************************************************/
	public Account () {
		
	}
	
	public void newAccount () {
		rentalHistory = new RentedList("Rental History");//("HISTORY");
		reservedList = new RentalList("Game");//("RENT LIST");
		favoritesList = new RentalList("TV");//("FAVORITES");
		checkedOutList = new RentedList("Checked Out");

		amountOwed = 0;
	}

	/*****************************************************************
	 * This checks to see if the submitted password matches the account
	 * password.
	 * @param password the submitted password.
	 *****************************************************************/
	public boolean checkPassword(String password){
		if(this.password.equals(password))
			return true;

		else
			return false;	}

	/*****************************************************************
	 * Adds a rental to the rental history.
	 * @param rental the real to be added.
	 *****************************************************************/
	public Rented rent(Rental rental){
		if (!rental.isChecked()) {
			Rented rented = rental.rent(this);
			rentalHistory.add(rented);
			checkedOutList.add(rented);
			return rented;
		}
		else
			return null;
	}

	/*****************************************************************
	This is set to return a movie where it will figure the cost and
	return the price of the time rented.

	@param rental Rented object that is to be returned.
	@return Rented unit that has been returned. 
	 *****************************************************************/
	public Rented returnRental(Rental rental){
		Rented rented = checkedOutList.getItem(rental);
		rented.returnRental();
		amountOwed += rented.getCostForRent();
		checkedOutList.remove(rented);
		return rented;
	}

	/*****************************************************************
	 * This returns the rental history for the account.
	 *****************************************************************/
	public RentedList getRentalHistory() {
		return rentalHistory;
	}

	/*****************************************************************
	 * This adds the rental to the account reserve list.
	 * @param FIGURE OUT WHAT THE PARAMTER ACTUALLY IS!!!!
	 *****************************************************************/
	public boolean addToReserve(Rental rental){
		boolean duplicate = false;
		for(int i = 0; i<reservedList.getSize();++i){
			if(reservedList.getIndex(i).equals(rental))
				duplicate = true;

		}
		if (duplicate)
			return false;
		else {
			reservedList.add(rental);
			return true;
		}
	}

	/*****************************************************************
	 * This removes a rental from a list.
	 * @param rental the rental to be removed from the reserved list.
	 *****************************************************************/
	public void removeReserve(Rental rental){
		for(int i = 0; i<reservedList.getSize(); i++)
			if(reservedList.getIndex(i).equals(rental))
				reservedList.removeIndex(i);

	}

	/*****************************************************************
	 * This adds the rental to the account reserve list.
	 * @param FIGURE OUT WHAT THE PARAMTER ACTUALLY IS!!!!
	 *****************************************************************/
	public boolean addToFavorites(Rental rental){

		boolean duplicate = false;
		for(int i = 0; i<favoritesList.getSize();++i){
			if(favoritesList.getIndex(i).equals(rental))
				duplicate = true;

		}
		if (duplicate)
			return false;
		else {
			favoritesList.add(rental);
			return true;
		}

	}

	/*****************************************************************
	 * This removes a rental from a list.
	 * @param rental the rental to be removed from the reserved list.
	 *****************************************************************/
	public void removeFavorites(Rental rental){
		for(int i = 0; i<reservedList.getSize(); i++)
			if(favoritesList.getIndex(i).equals(rental))
				favoritesList.removeIndex(i);

	}

	/*****************************************************************
	 * This returns the reserved list.
	 *****************************************************************/
	public RentalList getReservedList() {
		return reservedList;
	}

	/*****************************************************************
	 * This returns the favorites list.
	 *****************************************************************/
	public RentalList getFavoritesList() {
		return favoritesList;
	}

	/*****************************************************************
	 * This returns the account ID.
	 *****************************************************************/
	public int getAccountID() {
		return accountID;
	}

	/*****************************************************************
	 * This returns the account user's first name.
	 *****************************************************************/
	public String getFirstName() {
		return firstName;
	}

	/*****************************************************************
	 * This sets the user's first name.
	 * @param firstName the first name for the user.
	 *****************************************************************/
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	/*****************************************************************
	 * This returns user's last name.
	 *****************************************************************/
	public String getLastName() {
		return lastName;
	}

	/*****************************************************************
	 * This sets the user's last name.
	 * @param lastName the last name for the user.
	 *****************************************************************/
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/*****************************************************************
	 * This returns the email for the current user.
	 *****************************************************************/
	public String geteMail() {
		return eMail;
	}

	/*****************************************************************
	 * This sets the email for the user.
	 * @param eMail the email for the user
	 *****************************************************************/
	public void seteMail(String eMail) {
		this.eMail = eMail.toLowerCase();
	}

	/*****************************************************************
	 * This returns the street address for the user.
	 *****************************************************************/
	public String getAddress() {
		return address;
	}

	/*****************************************************************
	 * This sets the street address for the user.
	 * @param address the street address for the user.
	 *****************************************************************/
	public void setAddress(String address) {
		this.address = address;
	}

	/*****************************************************************
	 * This returns the password for the user.
	 *****************************************************************/
	public String getPassword() {
		return password;
	}

	/*****************************************************************
	 * This sets the password for the user.
	 * @param password the password for the user.
	 *****************************************************************/
	public void setPassword(String password) {
		this.password = password;
	}

	/*****************************************************************
	This returns the amount an account owes
	 *****************************************************************/
	public double getAmountOwed() {
		return amountOwed;
	}
	/*****************************************************************
	 this sets the amount owned to 0
	 *****************************************************************/
	public void amountPayed () {
		amountOwed = 0;
	}

	/*****************************************************************
	 * This checks to see if the submitted email matches the account 
	 * email.
	 * @param eMailAddress the email to compare to.
	 *****************************************************************/
	public int compareTo(Object eMailAddress) {

		return this.compareTo(eMailAddress);

	}

	/*****************************************************************
	 * This adds a rental to the favorites list.
	 * @param movie the rental to be added to the list.
	 *****************************************************************/
	public void addToFavorite(Rental movie){
		favoritesList.add(movie);

	}

	/*****************************************************************
	 * This removes a rental from the favorites list
	 * @param rental the rental to be removed
	 *****************************************************************/
	public void removeFavorite(Rental rental){
		for(int i = 0; i<favoritesList.getSize();i++){
			if(favoritesList.getIndex(i).equals(rental))
				favoritesList.removeIndex(i);
		}
	}


	/*****************************************************************
	 * This sees if two emails are equal. If they are return true;
	 *****************************************************************/
	public boolean equals(Object obj){
		return (this.eMail.equals(((Account) obj).geteMail()));

	}

	/*****************************************************************
	 * This sees if two emails are equal. If they are return true;
	 *****************************************************************/
	public String toString () {
		return ("" + eMail + ":  " + lastName + 
				": # out " + checkedOutList.getSize() +
				": total rented " + rentalHistory.getSize());



	}
}
